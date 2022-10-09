CREATE TABLE public.new_avocado (
	id int4 NOT NULL,
	average_price float8 NULL,
	total_volume float8 NULL,
	"year" int4 NULL,
	CONSTRAINT new_avocado_pkey PRIMARY KEY (id)
);

SELECT COUNT(id)
FROM avocado a;

SELECT COUNT(id)
FROM new_avocado na;