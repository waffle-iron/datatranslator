from basemodule import GetExposureData

class GetPm25ExposureData(GetExposureData):

    def get_values(self, **kwargs):
        # {'kwargs': {'statistical_type': 'max', 'temporal_resolution': 'day', 'exposure_point': 'alkd',\
        #  'end_date': '2001-02-01', 'start_date': '2001-01-02', 'exposure_type': 'pm25'}}
        return 'pm25 values'

    def get_scores(self, **kwargs):
        # {'kwargs': {'statistical_type': 'max', 'temporal_resolution': 'day', 'exposure_point': 'alkd',\
        #  'end_date': '2001-02-01', 'start_date': '2001-01-02', 'exposure_type': 'pm25'}}
        return 'pm25 scores'

# Define valid parameter sets
temporal_resolution_set = {'hour', 'day'}
score_type_set = {'7dayrisk', '14dayrisk'}
statistical_type_set = {'max', 'mean', 'median'}

exp = GetPm25ExposureData()


def get_values(**kwargs):
    print(kwargs)
    args = {'date_table': 'cmaq', 'date_column': 'utc_date_time', 'start_date': kwargs.get('start_date'),
            'end_date': kwargs.get('end_date')}
    (valid_date, message) = exp.date_validation(**args)
    if not valid_date:
        return message
    if kwargs.get('temporal_resolution') not in temporal_resolution_set:
        return 'Not Found', 400, {'x-error': 'Invalid temporal resolution'}
    if kwargs.get('statistical_type') not in statistical_type_set:
        return 'Not Found', 400, {'x-error': 'Invalid statistical type'}

    return 'Hi pm25 values'


def get_scores(**kwargs):
    print(kwargs)
    args = {'date_table': 'cmaq', 'date_column': 'utc_date_time', 'start_date': kwargs.get('start_date'),
            'end_date': kwargs.get('end_date')}
    (valid_date, message) = exp.date_validation(**args)
    if not valid_date:
        return message
    if kwargs.get('temporal_resolution') not in temporal_resolution_set:
        return 'Not Found', 400, {'x-error': 'Invalid temporal resolution'}
    if kwargs.get('score_type') not in score_type_set:
        return 'Not Found', 400, {'x-error': 'Invalid score type'}

    return 'Hi pm25 scores'
