#include <iostream>
#include <string>
#include <map>

using namespace std;

#ifndef PIECESPRODUCT_H
#define PIECESPRODUCT_H

#include "abstractProduct.h"

class piecesProduct : public abstractProduct
{
    public:
        piecesProduct(string,double,double);
        void printPrice() override;
        void printStock() override;
        const string getMeasureUnit();

    private:
        const string measureUnit = "";
};

#endif // PIECESPRODUCT_H
