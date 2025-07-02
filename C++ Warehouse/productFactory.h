#include <iostream>
#include <string>
#include <map>
#include "abstractProduct.h"

using namespace std;

#ifndef PRODUCTFACTORY_H
#define PRODUCTFACTORY_H


class productFactory
{
    public:
        abstractProduct* automaticProduct(string,string,double,double);
};

#endif // PRODUCTFACTORY_H
