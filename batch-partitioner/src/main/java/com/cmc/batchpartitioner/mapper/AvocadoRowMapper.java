package com.cmc.batchpartitioner.mapper;

import com.cmc.batchpartitioner.entity.Avocado;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvocadoRowMapper implements RowMapper<Avocado> {

    @Override
    public Avocado mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Avocado.builder()
                .id(rs.getInt("id"))
                .date(rs.getString("date"))
                .averagePrice(rs.getFloat("average_price"))
                .totalVolume(rs.getFloat("total_volume"))
                .totalBags(rs.getFloat("total_bags"))
                .type(rs.getString("type"))
                .year(rs.getInt("year"))
                .region(rs.getString("region"))
                .build();
    }
}
