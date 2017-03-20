-- Format
-- ID,Lat,Lon,Col,Row,Date,O3_ppb,PM25_Primary_ugm3,PM25_Secondary_ugm3
-- Raleigh,35.7795897,-78.6381787,122,50,2010-01-01 00:00:00,4.67542219161987,10.2763252258301,10.2763252258301

-- create a temporary table for holding the raw data
CREATE TEMP TABLE tmp (
    id TEXT,
    lat TEXT,
    lon TEXT,
    col TEXT,
    row TEXT,
    date TEXT,
    o3_ppb TEXT,
    pm25_primary_ugm3 TEXT,
    pm25_secondary_ugm3 TEXT
);

-- copy the raw data from sample csv file
COPY tmp FROM '/cmaq_sample.csv' DELIMITER ',' CSV HEADER ;

-- create a table to load data into named cmaq
CREATE TABLE IF NOT EXISTS cmaq (
  id SERIAL PRIMARY KEY,
  city_name TEXT,
  latitude FLOAT,
  longitude FLOAT,
  location GEOGRAPHY(POINT,4326),
  date_time TIMESTAMP,
  ozone FLOAT,
  pm25_primary FLOAT,
  pm25_secondary FLOAT
);

-- load the cmaq table with properly formatted data
INSERT INTO cmaq (city_name, latitude, longitude, location, date_time, ozone, pm25_primary, pm25_secondary)
    SELECT
      ID,
      cast(LAT as FLOAT),
      cast(LON as FLOAT),
      ST_GeographyFromText('SRID=4326;POINT('||LAT||' '||LON||')'),
      cast(DATE as TIMESTAMP),
      cast(O3_PPB as FLOAT),
      cast(PM25_PRIMARY_UGM3 as FLOAT),
      cast(PM25_SECONDARY_UGM3 as FLOAT)
    FROM tmp;

-- drop the temporary table
DROP TABLE tmp;

-- set owner to datatrans user
ALTER TABLE cmaq OWNER TO datatrans;

-- display a sample of contents to user
SELECT * FROM cmaq ORDER BY date_time ASC LIMIT 10;