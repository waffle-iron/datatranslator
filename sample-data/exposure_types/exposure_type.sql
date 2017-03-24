-- Format
-- exposure_type,description,units,has_values,has_score,schema_version
-- pm25,particulate matter 2.5,ugm3,true,true,1.0.0

-- create a temporary table for holding the raw data
CREATE TEMP TABLE tmp (
  exposure_type TEXT,
  description TEXT,
  units TEXT,
  has_values TEXT,
  has_scores TEXT,
  schema_version TEXT
);

-- copy the raw data from sample csv file
COPY tmp FROM '/exposure_type.csv' DELIMITER ',' CSV HEADER ;

-- create a table to load data into named cmaq
CREATE TABLE IF NOT EXISTS exposure_type (
  id SERIAL UNIQUE PRIMARY KEY,
  exposure_type TEXT,
  description TEXT,
  units TEXT,
  has_values BOOLEAN,
  has_scores BOOLEAN,
  schema_version TEXT
);

-- load the exposure_type table with properly formatted data
INSERT INTO exposure_type (exposure_type, description, units, has_values, has_scores, schema_version)
  SELECT
    exposure_type,
    description,
    units,
    cast(has_values as BOOLEAN),
    cast(has_scores as BOOLEAN),
    schema_version
  FROM tmp;

-- drop the temporary table
DROP TABLE tmp;

-- set owner to datatrans user
ALTER TABLE exposure_type OWNER TO datatrans;

-- display a sample of contents to user
SELECT * FROM exposure_type ORDER BY exposure_type ASC LIMIT 10;