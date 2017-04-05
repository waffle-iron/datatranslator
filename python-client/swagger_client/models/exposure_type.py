# coding: utf-8

"""
    Environmental Exposures API

    Environmental Exposures API

    OpenAPI spec version: 1.0.0
    Contact: stealey@renci.org
    Generated by: https://github.com/swagger-api/swagger-codegen.git

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
"""

from pprint import pformat
from six import iteritems
import re


class ExposureType(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self, exposure_type=None, description=None, units=None, has_values=None, has_scores=None, schema_version=None):
        """
        ExposureType - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'exposure_type': 'str',
            'description': 'str',
            'units': 'str',
            'has_values': 'bool',
            'has_scores': 'bool',
            'schema_version': 'str'
        }

        self.attribute_map = {
            'exposure_type': 'exposure_type',
            'description': 'description',
            'units': 'units',
            'has_values': 'has_values',
            'has_scores': 'has_scores',
            'schema_version': 'schema_version'
        }

        self._exposure_type = exposure_type
        self._description = description
        self._units = units
        self._has_values = has_values
        self._has_scores = has_scores
        self._schema_version = schema_version


    @property
    def exposure_type(self):
        """
        Gets the exposure_type of this ExposureType.


        :return: The exposure_type of this ExposureType.
        :rtype: str
        """
        return self._exposure_type

    @exposure_type.setter
    def exposure_type(self, exposure_type):
        """
        Sets the exposure_type of this ExposureType.


        :param exposure_type: The exposure_type of this ExposureType.
        :type: str
        """

        self._exposure_type = exposure_type

    @property
    def description(self):
        """
        Gets the description of this ExposureType.


        :return: The description of this ExposureType.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description):
        """
        Sets the description of this ExposureType.


        :param description: The description of this ExposureType.
        :type: str
        """

        self._description = description

    @property
    def units(self):
        """
        Gets the units of this ExposureType.


        :return: The units of this ExposureType.
        :rtype: str
        """
        return self._units

    @units.setter
    def units(self, units):
        """
        Sets the units of this ExposureType.


        :param units: The units of this ExposureType.
        :type: str
        """

        self._units = units

    @property
    def has_values(self):
        """
        Gets the has_values of this ExposureType.


        :return: The has_values of this ExposureType.
        :rtype: bool
        """
        return self._has_values

    @has_values.setter
    def has_values(self, has_values):
        """
        Sets the has_values of this ExposureType.


        :param has_values: The has_values of this ExposureType.
        :type: bool
        """

        self._has_values = has_values

    @property
    def has_scores(self):
        """
        Gets the has_scores of this ExposureType.


        :return: The has_scores of this ExposureType.
        :rtype: bool
        """
        return self._has_scores

    @has_scores.setter
    def has_scores(self, has_scores):
        """
        Sets the has_scores of this ExposureType.


        :param has_scores: The has_scores of this ExposureType.
        :type: bool
        """

        self._has_scores = has_scores

    @property
    def schema_version(self):
        """
        Gets the schema_version of this ExposureType.


        :return: The schema_version of this ExposureType.
        :rtype: str
        """
        return self._schema_version

    @schema_version.setter
    def schema_version(self, schema_version):
        """
        Sets the schema_version of this ExposureType.


        :param schema_version: The schema_version of this ExposureType.
        :type: str
        """

        self._schema_version = schema_version

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()

    def __eq__(self, other):
        """
        Returns true if both objects are equal
        """
        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
