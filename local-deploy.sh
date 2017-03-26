#!/usr/bin/env bash

LOCAL_HOME=$(pwd)

# Launch database backend
./dbctl start

# Setup database bdtgreen and user datatrans
cd ${LOCAL_HOME}/sample-data/
./setup-bdtgreen

# Load Cmaq data
cd ${LOCAL_HOME}/sample-data/cmaq/
./load-cmaq-sample

# Load ExposureType data
cd ${LOCAL_HOME}/sample-data/exposure_types
./load-exposure-type

exit 0;