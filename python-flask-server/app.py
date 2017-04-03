#!/usr/bin/env python3

import connexion
from configparser import ConfigParser
from gevent import monkey
monkey.patch_all()

if __name__ == '__main__':
    parser = ConfigParser()
    parser.read('ini/connexion.ini')
    app = connexion.App(__name__, specification_dir='./swagger/', server=parser.get('connexion', 'server'))
    app.add_api('swagger.yaml', arguments={'title': 'Environmental Exposures API'})
    app.run(port=int(parser.get('connexion', 'port')),
            debug=parser.get('connexion', 'debug'),
            keyfile=parser.get('connexion', 'keyfile'),
            certfile=parser.get('connexion', 'certfile')
            )
