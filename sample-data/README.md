## Sample data

Example of loading sample data into a database named **bdtgreen** for use by a user named **datatrans**.

1. Start a new instance of backend

    ```
    $ ./dbctl start
    Creating backend
    ```
2. Run the `sample-etl` script

    ```
    $ cd sample-data/
    $ ./sample-etl
    CREATE ROLE
    CREATE DATABASE
    GRANT
    CREATE EXTENSION
    CREATE EXTENSION
    CREATE EXTENSION
    You are now connected to database "bdtgreen" as user "postgres".
    CREATE TABLE
    COPY 216
    CREATE TABLE
    INSERT 0 216
    DROP TABLE
    ALTER TABLE
          id       | col | row |    date    |   hour   |        o3        |   pm25_primary   |  pm25_secondary
    ---------------+-----+-----+------------+----------+------------------+------------------+------------------
     Sample_Site_1 | 123 |  51 | 2010-01-01 | 00:00:00 | 29.5110778808594 | 3.76756572723389 | 3.76756572723389
     Sample_Site_1 | 123 |  51 | 2010-01-01 | 01:00:00 | 24.9244899749756 | 4.85282373428345 | 4.85282373428345
     ...
     Sample_Site_3 | 125 |  50 | 2010-01-03 | 23:00:00 | 35.1841697692871 | 8.84106540679932 | 8.84106540679932
    (216 rows)
    ```
3. Connect to the database as the **datatrans** user and run a query.

    ```
    $ cd ../
    $ ./dbctl psql datatrans somepassword bdtgreen
    psql (9.5.6)
    Type "help" for help.

    bdtgreen=>select distinct id from cmaq ;
          id
    ---------------
    Sample_Site_1
    Sample_Site_3
    Sample_Site_2
    (3 rows)
    ```
