#!/usr/bin/env python3

import connexion
from configparser import ConfigParser

if __name__ == '__main__':
    parser = ConfigParser()
    parser.read('ini/connexion.ini')
    CONNEXION_SERVER = parser.get('connexion', 'server')
    CONNEXION_DEBUG = parser.get('connexion', 'debug')
    app = connexion.App(__name__, specification_dir='./swagger/', server=CONNEXION_SERVER)
    app.add_api('swagger.yaml', arguments={'title': 'Environmental Exposures API'})
    app.run(port=8080, debug=CONNEXION_DEBUG)
