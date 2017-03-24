from sqlalchemy import create_engine, exists, and_
from sqlalchemy.orm import sessionmaker
from models import Cmaq, ExposureType
from flask import jsonify
import importlib
import sys
sys.path.append('/Users/stealey/Github/datatranslator/python-flask-server/exposures')

engine = create_engine('postgres://datatrans:somepassword@192.168.56.101:5432/bdtgreen')
Session = sessionmaker(bind=engine)
session = Session()


def exposures_exposure_type_scores_get(exposure_type, start_date, end_date, exposure_point, \
                                       temporal_resolution=None, score_type=None) -> str:
    ret = session.query(exists().where(and_(ExposureType.exposure_type == exposure_type,
                                            ExposureType.has_values))).scalar()
    if not session.query(exists().where(ExposureType.exposure_type == exposure_type)).scalar():
        return 'Bad Request', 400, {'x-error': 'Invalid exposure parameters'}
    elif not session.query(exists().where(and_(ExposureType.exposure_type == exposure_type,
                                               ExposureType.has_scores))).scalar():
        return 'Not Found', 404, {'x-error': 'Values not found for exposure type'}

    mod = importlib.import_module(exposure_type)
    data = mod.get_scores(locals())
    return data


def exposures_exposure_type_values_get(exposure_type, start_date, end_date, exposure_point, \
                                       temporal_resolution=None, statistical_type=None) -> str:
    ret = session.query(exists().where(and_(ExposureType.exposure_type == exposure_type,
                                            ExposureType.has_values))).scalar()
    if not session.query(exists().where(ExposureType.exposure_type == exposure_type)).scalar():
        return 'Bad Request', 400, {'x-error': 'Invalid exposure parameters'}
    elif not session.query(exists().where(and_(ExposureType.exposure_type == exposure_type,
                                            ExposureType.has_values))).scalar():
        return 'Not Found', 404, {'x-error': 'Values not found for exposure type'}

    mod = importlib.import_module(exposure_type)
    data = mod.get_values(locals())
    return data


def exposures_get() -> str:
    results = session.query(ExposureType).all()
    data = jsonify([dict(exposure_type=o.exposure_type, description=o.description, units=o.units,
                         has_values=o.has_values, has_scores=o.has_scores, schema_version=o.schema_version)
                    for o in results])
    return data
