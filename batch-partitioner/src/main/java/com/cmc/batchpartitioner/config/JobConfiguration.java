package com.cmc.batchpartitioner.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.cmc.batchpartitioner.entity.Avocado;
import com.cmc.batchpartitioner.mapper.AvocadoRowMapper;
import com.cmc.batchpartitioner.partitioner.ColumnRangePartitioner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class JobConfiguration extends DefaultBatchConfigurer{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(null);
    }

    @Bean
    public ColumnRangePartitioner partitioner()
    {
        ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
        columnRangePartitioner.setColumn("id");
        columnRangePartitioner.setDataSource(dataSource);
        columnRangePartitioner.setTable("avocado");
        return columnRangePartitioner;
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Avocado> pagingItemReader(
            @Value("#{stepExecutionContext['minValue']}") Long minValue,
            @Value("#{stepExecutionContext['maxValue']}") Long maxValue)
    {
        System.out.println("reading " + minValue + " to " + maxValue);

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, date, average_price, total_volume, total_bags, type, year, region");
        queryProvider.setFromClause("from avocado");
        queryProvider.setWhereClause("where id >= " + minValue + " and id <= " + maxValue);
        queryProvider.setSortKeys(sortKeys);

        JdbcPagingItemReader<Avocado> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(1000);
        reader.setRowMapper(new AvocadoRowMapper());
        reader.setQueryProvider(queryProvider);

        return reader;
    }


    @Bean
    @StepScope
    public JdbcBatchItemWriter<Avocado> avocadoItemWriter()
    {
        JdbcBatchItemWriter<Avocado> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("insert into new_avocado(id, average_price, total_volume, year) values (:id, :averagePrice, :totalVolume, :year)");

        itemWriter.setItemSqlParameterSourceProvider
                (new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    // Master
    @Bean
    public Step step1()
    {
        return stepBuilderFactory.get("step1")
                .partitioner(slaveStep().getName(), partitioner())
                .step(slaveStep())
                .gridSize(4)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    // slave step
    @Bean
    public Step slaveStep()
    {
        return stepBuilderFactory.get("slaveStep")
                .<Avocado, Avocado>chunk(1000)
                .reader(pagingItemReader(null, null))
                .writer(avocadoItemWriter())
                .build();
    }

    @Bean
    public Job job()
    {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
