#include <iostream>
#include <string>
#include <map>
#include <algorithm>

using namespace std;

#ifndef ABSTRACTPRODUCT_H
#define ABSTRACTPRODUCT_H


class abstractProduct
{
    public:
    //Getter
        string getName();
        double getPrice();
        double getQuantity();

        double getOrderPrice(double);
    //Setter
        void setName(string);
        void setPrice(double);
        void setQuantity(double);

        void removeUnits(double);
        void addUnits(double);

        bool isAvailable(double);

    //Abstract Functions:

        virtual void printPrice() = 0;
        virtual void printStock() = 0;
        virtual const string getMeasureUnit() = 0;

    private:
        double price;
        string name;
        double quantity;
};

#endif // ABSTRACTPRODUCT_H
