# scrape-exposure-data
java app to scrape exposure data from Open AQ (https://openaq.org/#/?_k=97314b)
using Open AQ API (https://docs.openaq.org)

requires Java 8

To run app:

```
java -jar scrape-exposure-data.jar o3 2017-03-22T00:00:00.000Z 2017-03-23T00:00:00.000Z
```

Where o3 represents exposure type ozone. (currently only o3 amd pm25 supported)
Date ranges are optional and must be specified in the format shown, in UTC time zone.
If end date is omitted, current time is assumed

