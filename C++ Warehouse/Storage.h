#include <map>
#include <vector>
#include "abstractProduct.h"
#include <algorithm>


#ifndef STORAGE_H
#define STORAGE_H

using namespace std;

class Storage
{
    public:
    //Default Constructor:
        Storage();
    //Specific Constructor:
        Storage(map<string,double>,vector<abstractProduct*>);

        void restoreInitialStorage();

        void restoreStorage(Storage);

        void printStorage();

        void addElement(abstractProduct*);

        void setElement(abstractProduct*);

        string productOrdering(string,double&,double&);

        bool productInStock(string);

        abstractProduct* getProduct(string);

        bool Contains(string);

        void addQuantity(string,double);

        void removeQuantity(string,double);

        double howMuch(string);


    private:
        map<string,double>nameQuantity;
        vector<abstractProduct*>productObjects;

};

#endif // STORAGE_H
