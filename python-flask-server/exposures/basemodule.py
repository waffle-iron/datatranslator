from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from datetime import *

engine = create_engine('postgres://datatrans:somepassword@192.168.56.101:5432/bdtgreen')
Session = sessionmaker(bind=engine)
session = Session()


class GetExposureData(object):

    def before_date_range(self, *args):
        date_table = args[0]
        date_column = args[1]
        date_to_compare = datetime.strptime(args[2], '%Y-%m-%d')
        sql = ('select min(' + date_column + ') from ' + date_table + ';')
        min_date = datetime.strftime(session.execute(sql).scalar(), '%Y-%m-%d')
        min_date = datetime.strptime(min_date, '%Y-%m-%d')
        if min_date > date_to_compare:
            return True

        return False

    def after_date_range(self, *args):
        date_table = args[0]
        date_column = args[1]
        date_to_compare = datetime.strptime(args[2], '%Y-%m-%d')
        sql = ('select max(' + date_column + ') from ' + date_table + ';')
        max_date = datetime.strftime(session.execute(sql).scalar(), '%Y-%m-%d')
        max_date = datetime.strptime(max_date, '%Y-%m-%d')
        if max_date < date_to_compare:
            return True

        return False

    def invalid_date_range(self, *args):
        start_date = datetime.strptime(args[0], '%Y-%m-%d')
        end_date = datetime.strptime(args[1], '%Y-%m-%d')
        if start_date > end_date:
            return True

        return False

    def date_validation(self, **args):
        if self.invalid_date_range(args.get('start_date'), args.get('end_date')):
            return False, ('Not Found', 400, {'x-error': 'Invalid date range'})
        if self.before_date_range(args.get('date_table'), args.get('date_column'), args.get('end_date')):
            return False, ('Not Found', 404, {'x-error': 'end_date precedes date range'})
        if self.after_date_range(args.get('date_table'), args.get('date_column'), args.get('start_date')):
            return False, ('Not Found', 404, {'x-error': 'start_date occurs after date range'})

        return True, ''

    def serialize_exposure_point(self, **args):
        weekday_set = {'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'other'}
        start_time_set = {'00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11',
                          '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'}
        end_time_set = {'00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11',
                        '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'}

        points = [{'point': p.split(',')} for p in args.get('exposure_point').split(';')]
        for p in points:
            if len(p.get('point')) == 3:
                duration = p.get('point')[2]
                if len(duration) == 4:
                    start_time = duration[0:2]
                    end_time = duration[2:4]
                elif len(duration) == 6:
                    weekday = duration[0:2]
                    start_time = duration[2:4]
                    end_time = duration[4:6]
                    if weekday not in weekday_set:
                        return False, ('Not Found', 400, {'x-error': 'Invalid exposure_point weekday'}), []
                elif duration == 'other':
                    start_time = '00'
                    end_time = '23'
                else:
                    return False, ('Not Found', 400, {'x-error': 'Invalid exposure_point'}), []
                if int(start_time) >= int(end_time):
                    return False, ('Not Found', 400, {'x-error': 'Invalid exposure_point start_time >= end_time'}), []
                if start_time not in start_time_set:
                    return False, ('Not Found', 400, {'x-error': 'Invalid exposure_point start_time'}), []
                if end_time not in end_time_set:
                    return False, ('Not Found', 400, {'x-error': 'Invalid exposure_point end_time'}), []

        return True, '', points

    def get_values(self, **kwargs):
        raise NotImplemented

    def get_scores(self, **kwargs):
        raise NotImplemented
