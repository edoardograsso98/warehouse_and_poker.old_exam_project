#include <iostream>
#include <string>
#include <map>

using namespace std;

#ifndef KILOSPRODUCT_H
#define KILOSPRODUCT_H

#include "abstractProduct.h"

class kilosProduct : public abstractProduct
{
    public:
        kilosProduct(string,double,double);
        void printPrice() override;
        void printStock() override;
        const string getMeasureUnit();

    private:
        const string measureUnit = "kg";
};

#endif // KILOSPRODUCT_H
