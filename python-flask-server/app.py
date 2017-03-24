#!/usr/bin/env python3

import connexion

if __name__ == '__main__':
    app = connexion.App(__name__, specification_dir='./swagger/', server='gevent')
    app.add_api('swagger.yaml', arguments={'title': 'Environmental Exposures API'})
    app.run(port=8080)