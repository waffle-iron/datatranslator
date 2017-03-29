# swagger_client.DefaultApi

All URIs are relative to *https://localhost:8080/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**controllers_default_controller_exposures_exposure_type_scores_get**](DefaultApi.md#controllers_default_controller_exposures_exposure_type_scores_get) | **GET** /exposures/{exposure_type}/scores | Get exposure score for a given environmental factor
[**controllers_default_controller_exposures_exposure_type_values_get**](DefaultApi.md#controllers_default_controller_exposures_exposure_type_values_get) | **GET** /exposures/{exposure_type}/values | Get exposure value for a given environmental factor
[**controllers_default_controller_exposures_get**](DefaultApi.md#controllers_default_controller_exposures_get) | **GET** /exposures | Get list of exposure types


# **controllers_default_controller_exposures_exposure_type_scores_get**
> list[Exposure] controllers_default_controller_exposures_exposure_type_scores_get(exposure_type, start_date, end_date, exposure_point, temporal_resolution=temporal_resolution, score_type=score_type)

Get exposure score for a given environmental factor

Retrieve the computed exposure score for a given environmental exposure factor, time period, and set of locations

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
exposure_type = 'exposure_type_example' # str | The name of the exposure factor (currently limited to pm25, ozone).
start_date = '2013-10-20' # date | The starting date to obtain exposures for (example 1985-04-12 is April 12th 1985). Currently time of day is ignored.
end_date = '2013-10-20' # date | The ending date to obtain exposures for (example 1985-04-13 is April 13th 1985). Currently time of day is ignored.
exposure_point = 'exposure_point_example' # str | A description of the location(s) to retrieve the exposure for. Locaton may be a single geocoordinate (example '35.720278,-79.176389') or a semicomma separated list of geocoord:dayhours giving the start and ending hours on specific days of the week at that location (example '35.720278,-79.176389,Sa0813;35.720278,-79.176389,other') indicates Saturdays from 8am to 1pm is at one location and all other times are at another location. Hours should be in 24 hours time using 2 digits, days of the week should be the first two characters of the day.If the day of the week does not appear then the time periods apply to all days (example '35.720278,-79.176389,0614,35.731944,-78.852778,1424') gives two time periods for all days. If hours do not appear then the time period applies to all hours of the day (example '35.720278,-79.176389,Sa,35.731944,-78.852778,Su').
temporal_resolution = 'day' # str | The temporal resolution to use for results, should be one of 'hour' or 'day'. Default is 'day' (optional) (default to day)
score_type = '7dayrisk' # str | The exposure score type to return. The accepted values vary by exposure factor. For pm25 values are '7dayrisk', '14dayrisk' (NOT COMPLETE) (optional) (default to 7dayrisk)

try: 
    # Get exposure score for a given environmental factor
    api_response = api_instance.controllers_default_controller_exposures_exposure_type_scores_get(exposure_type, start_date, end_date, exposure_point, temporal_resolution=temporal_resolution, score_type=score_type)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->controllers_default_controller_exposures_exposure_type_scores_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **exposure_type** | **str**| The name of the exposure factor (currently limited to pm25, ozone). | 
 **start_date** | **date**| The starting date to obtain exposures for (example 1985-04-12 is April 12th 1985). Currently time of day is ignored. | 
 **end_date** | **date**| The ending date to obtain exposures for (example 1985-04-13 is April 13th 1985). Currently time of day is ignored. | 
 **exposure_point** | **str**| A description of the location(s) to retrieve the exposure for. Locaton may be a single geocoordinate (example &#39;35.720278,-79.176389&#39;) or a semicomma separated list of geocoord:dayhours giving the start and ending hours on specific days of the week at that location (example &#39;35.720278,-79.176389,Sa0813;35.720278,-79.176389,other&#39;) indicates Saturdays from 8am to 1pm is at one location and all other times are at another location. Hours should be in 24 hours time using 2 digits, days of the week should be the first two characters of the day.If the day of the week does not appear then the time periods apply to all days (example &#39;35.720278,-79.176389,0614,35.731944,-78.852778,1424&#39;) gives two time periods for all days. If hours do not appear then the time period applies to all hours of the day (example &#39;35.720278,-79.176389,Sa,35.731944,-78.852778,Su&#39;). | 
 **temporal_resolution** | **str**| The temporal resolution to use for results, should be one of &#39;hour&#39; or &#39;day&#39;. Default is &#39;day&#39; | [optional] [default to day]
 **score_type** | **str**| The exposure score type to return. The accepted values vary by exposure factor. For pm25 values are &#39;7dayrisk&#39;, &#39;14dayrisk&#39; (NOT COMPLETE) | [optional] [default to 7dayrisk]

### Return type

[**list[Exposure]**](Exposure.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **controllers_default_controller_exposures_exposure_type_values_get**
> list[Exposure] controllers_default_controller_exposures_exposure_type_values_get(exposure_type, start_date, end_date, exposure_point, temporal_resolution=temporal_resolution, statistical_type=statistical_type)

Get exposure value for a given environmental factor

Retrieve the computed exposure value for a given environmental exposure factor, time period, and set of locations

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()
exposure_type = 'exposure_type_example' # str | The name of the exposure factor (currently limited to pm25, ozone).
start_date = '2013-10-20' # date | The starting date to obtain exposures for (example 1985-04-12 is April 12th 1985). Currently time of day is ignored.
end_date = '2013-10-20' # date | The ending date to obtain exposures for (example 1985-04-13 is April 13th 1985). Currently time of day is ignored.
exposure_point = 'exposure_point_example' # str | A description of the location(s) to retrieve the exposure for. Locaton may be a single geocoordinate (example '35.720278,-79.176389') or a semicomma separated list of geocoord:dayhours giving the start and ending hours on specific days of the week at that location (example '35.720278,-79.176389,Sa0813;35.720278,-79.176389,other') indicates Saturdays from 8am to 1pm is at one location and all other times are at another location. Hours should be in 24 hours time using 2 digits, days of the week should be the first two characters of the day.If the day of the week does not appear then the time periods apply to all days (example '35.720278,-79.176389,0614,35.731944,-78.852778,1424') gives two time periods for all days. If hours do not appear then the time period applies to all hours of the day (example '35.720278,-79.176389,Sa,35.731944,-78.852778,Su').
temporal_resolution = 'day' # str | The temporal resolution to use for results, should be one of 'hour' or 'day'. Default is 'day' (optional) (default to day)
statistical_type = 'max' # str | The statistic to use for results, should be one of 'max', 'mean', or 'median'. Default is 'max' (optional) (default to max)

try: 
    # Get exposure value for a given environmental factor
    api_response = api_instance.controllers_default_controller_exposures_exposure_type_values_get(exposure_type, start_date, end_date, exposure_point, temporal_resolution=temporal_resolution, statistical_type=statistical_type)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->controllers_default_controller_exposures_exposure_type_values_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **exposure_type** | **str**| The name of the exposure factor (currently limited to pm25, ozone). | 
 **start_date** | **date**| The starting date to obtain exposures for (example 1985-04-12 is April 12th 1985). Currently time of day is ignored. | 
 **end_date** | **date**| The ending date to obtain exposures for (example 1985-04-13 is April 13th 1985). Currently time of day is ignored. | 
 **exposure_point** | **str**| A description of the location(s) to retrieve the exposure for. Locaton may be a single geocoordinate (example &#39;35.720278,-79.176389&#39;) or a semicomma separated list of geocoord:dayhours giving the start and ending hours on specific days of the week at that location (example &#39;35.720278,-79.176389,Sa0813;35.720278,-79.176389,other&#39;) indicates Saturdays from 8am to 1pm is at one location and all other times are at another location. Hours should be in 24 hours time using 2 digits, days of the week should be the first two characters of the day.If the day of the week does not appear then the time periods apply to all days (example &#39;35.720278,-79.176389,0614,35.731944,-78.852778,1424&#39;) gives two time periods for all days. If hours do not appear then the time period applies to all hours of the day (example &#39;35.720278,-79.176389,Sa,35.731944,-78.852778,Su&#39;). | 
 **temporal_resolution** | **str**| The temporal resolution to use for results, should be one of &#39;hour&#39; or &#39;day&#39;. Default is &#39;day&#39; | [optional] [default to day]
 **statistical_type** | **str**| The statistic to use for results, should be one of &#39;max&#39;, &#39;mean&#39;, or &#39;median&#39;. Default is &#39;max&#39; | [optional] [default to max]

### Return type

[**list[Exposure]**](Exposure.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **controllers_default_controller_exposures_get**
> list[ExposureType] controllers_default_controller_exposures_get()

Get list of exposure types

Returns a list of all available exposure types

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DefaultApi()

try: 
    # Get list of exposure types
    api_response = api_instance.controllers_default_controller_exposures_get()
    pprint(api_response)
except ApiException as e:
    print("Exception when calling DefaultApi->controllers_default_controller_exposures_get: %s\n" % e)
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**list[ExposureType]**](ExposureType.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

