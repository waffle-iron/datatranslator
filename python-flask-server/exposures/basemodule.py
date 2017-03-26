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
            return False, ('Not Found', 404, {'x-error': 'End date precedes date range'})
        if self.after_date_range(args.get('date_table'), args.get('date_column'), args.get('start_date')):
            return False, ('Not Found', 404, {'x-error': 'Start date occurs after date range'})
        return True, ''

    def get_values(self, **kwargs):
        raise NotImplemented

    def get_scores(self, **kwargs):
        raise NotImplemented
