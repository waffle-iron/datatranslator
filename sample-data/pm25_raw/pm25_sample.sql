-- Format
-- utc_date,local_date,country,city,x,y,location,parameter,value,unit
-- 2017-03-16T19:00:00.000Z,2017-03-16T14:00:00-05:00,US,Asheville,-82.5833,35.6075,BDED,pm25,7.9,µg/m³

-- create a temporary table for holding the raw data
CREATE TEMP TABLE tmp (
    utc_date TEXT,
    local_date TEXT,
    country TEXT,
    city TEXT,
    x TEXT,
    y TEXT,
    location TEXT,
    parameter TEXT,
    value TEXT,
    unit TEXT DEFAULT 'µg/m³'
);

-- copy the raw data from sample csv file
COPY tmp FROM '/pm25_sample.csv' DELIMITER ',' CSV HEADER ;

-- create a table to load data into named pm25
CREATE TABLE IF NOT EXISTS pm25 (
  id SERIAL PRIMARY KEY,
  city_name TEXT,
  latitude FLOAT,
  longitude FLOAT,
  location GEOGRAPHY(POINT,4326),
  utc_date_time TIMESTAMP,
  local_date_time TIMESTAMP,
  site TEXT,
  pm25 FLOAT,
  unit TEXT
);

-- load the pm25 table with properly formatted data
INSERT INTO pm25 (city_name, latitude, longitude, location, utc_date_time, local_date_time, site, pm25, unit)
    SELECT
      city,
      cast(x as FLOAT),
      cast(y as FLOAT),
      ST_GeographyFromText('SRID=4326;POINT('||x||' '||y||')'),
      cast(utc_date as TIMESTAMP),
      cast(local_date as TIMESTAMP),
      location,
      cast(value as FLOAT),
      unit
    FROM tmp;

-- drop the temporary table
DROP TABLE tmp;

-- set owner to datatrans user
ALTER TABLE pm25 OWNER TO datatrans;

-- display a sample of contents to user
SELECT * FROM pm25 ORDER BY local_date_time ASC LIMIT 10;