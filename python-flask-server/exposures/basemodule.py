from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

engine = create_engine('postgres://datatrans:somepassword@192.168.56.101:5432/bdtgreen')
Session = sessionmaker(bind=engine)
session = Session()


def get_values(*args, **kwargs):
    return 'basemodule values'

def get_scores(*args, **kwargs):
    return 'basemodule scores'
