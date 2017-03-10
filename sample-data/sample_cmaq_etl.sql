\c bdtgreen;
CREATE TEMP TABLE tmp (
    ID text,
    COL text,
    ROW text,
    DATE text,
    HOUR text,
    O3 text,
    PM25_PRIMARY text,
    PM25_SECONDARY text
);
COPY tmp FROM '/sample_cmaq_output.csv' delimiter ',' CSV HEADER ;
create table cmaq (
    ID text,
    COL integer,
    ROW integer,
    DATE date,
    HOUR time,
    O3 float,
    PM25_PRIMARY float,
    PM25_SECONDARY float
);
INSERT INTO cmaq (ID, COL, ROW, DATE, HOUR, O3, PM25_PRIMARY, PM25_SECONDARY)
SELECT
    ID,
    cast(COL as integer),
    cast(ROW as integer),
    to_date(DATE, 'YYYYMMDD'),
    to_timestamp(LPAD(HOUR::text, 6, '0'), 'HH24MISS'),
    cast(O3 as float),
    cast(PM25_PRIMARY as float),
    cast(PM25_SECONDARY as float)
FROM tmp;
DROP TABLE tmp;
ALTER TABLE cmaq OWNER TO datatrans;
SELECT * FROM cmaq;