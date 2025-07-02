#include <iostream>
#include <string>
#include <map>

using namespace std;

#ifndef LITERPRODUCT_H
#define LITERPRODUCT_H

#include "abstractProduct.h"

class literProduct : public abstractProduct
{
    public:
        literProduct(string,double,double);
        void printPrice() override;
        void printStock() override;
        const string getMeasureUnit();

    private:
        const string measureUnit = "l";
};

#endif // LITERPRODUCT_H
