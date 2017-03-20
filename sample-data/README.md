## Sample data

Example of loading **cmaq** sample data into a database named **bdtgreen** for use by a user named **datatrans**.

1. Start a new instance of backend

    ```
    $ ./dbctl start
    Creating backend
    ```
2. Run the `sample-data/setup-bdtgreen` script

    ```
    $ cd sample-data/
    $ ./setup-bdtgreen
    CREATE ROLE
    CREATE DATABASE
    GRANT
    CREATE EXTENSION
    CREATE EXTENSION
    CREATE EXTENSION    
    ```
3. Run the `sample-data/cmaq/load-cmaq-sample` script to load cmaq sample data into database bdtgreen

    ```
    $ cd cmaq/
    $ ./load-cmaq-sample
    CREATE TABLE
    COPY 216
    CREATE TABLE
    INSERT 0 216
    DROP TABLE
    ALTER TABLE
     id  |  city_name  |  latitude  |  longitude  |                      location                      |      date_time      |      ozone       |   pm25_primary   |  pm25_secondary
    -----+-------------+------------+-------------+----------------------------------------------------+---------------------+------------------+------------------+------------------
       1 | Raleigh     | 35.7795897 | -78.6381787 | 0101000020E610000034E66498C9E341403E6079EBD7A853C0 | 2010-01-01 00:00:00 | 4.67542219161987 | 10.2763252258301 | 10.2763252258301
      73 | Durham      | 35.9940329 |  -78.898619 | 0101000020E6100000F35256783CFF41401C2444F982B953C0 | 2010-01-01 00:00:00 |  3.8005747795105 | 6.51982355117798 | 6.51982355117798
     145 | Chapel Hill | 35.9131996 | -79.0558445 | 0101000020E6100000325C78B9E3F44140564ACFF492C353C0 | 2010-01-01 00:00:00 |  11.511438369751 | 6.60837078094482 | 6.60837078094482
       2 | Raleigh     | 35.7795897 | -78.6381787 | 0101000020E610000034E66498C9E341403E6079EBD7A853C0 | 2010-01-01 01:00:00 | 10.6597690582275 |  8.6428804397583 |  8.6428804397583
      74 | Durham      | 35.9940329 |  -78.898619 | 0101000020E6100000F35256783CFF41401C2444F982B953C0 | 2010-01-01 01:00:00 | 9.16897773742676 | 7.06193399429321 | 7.06193399429321
     146 | Chapel Hill | 35.9131996 | -79.0558445 | 0101000020E6100000325C78B9E3F44140564ACFF492C353C0 | 2010-01-01 01:00:00 | 15.7613143920898 |  6.9754753112793 |  6.9754753112793
      75 | Durham      | 35.9940329 |  -78.898619 | 0101000020E6100000F35256783CFF41401C2444F982B953C0 | 2010-01-01 02:00:00 | 14.1381168365479 | 6.93615484237671 | 6.93615484237671
       3 | Raleigh     | 35.7795897 | -78.6381787 | 0101000020E610000034E66498C9E341403E6079EBD7A853C0 | 2010-01-01 02:00:00 | 11.7991485595703 | 8.97025680541992 | 8.97025680541992
     147 | Chapel Hill | 35.9131996 | -79.0558445 | 0101000020E6100000325C78B9E3F44140564ACFF492C353C0 | 2010-01-01 02:00:00 | 19.2165870666504 | 6.86179161071777 | 6.86179161071777
       4 | Raleigh     | 35.7795897 | -78.6381787 | 0101000020E610000034E66498C9E341403E6079EBD7A853C0 | 2010-01-01 03:00:00 | 15.6943092346191 | 8.31261157989502 | 8.31261157989502
    (10 rows)
    ```
4. Connect to the database as the **datatrans** user and run a query.

    ```
    $ cd ../../
    $ ./dbctl psql datatrans somepassword bdtgreen
    psql (9.5.6)
    Type "help" for help.
    
    bdtgreen=> select distinct city_name from cmaq;
      city_name
    -------------
     Raleigh
     Durham
     Chapel Hill
    (3 rows)
    
    bdtgreen=> \q
    ```
