#include <iostream>
#include <string>
#include <map>
#include "productFactory.h"
#include "abstractProduct.h"
#include "literProduct.h"
#include "kilosProduct.h"
#include "piecesProduct.h"

using namespace std;

abstractProduct* productFactory::automaticProduct(string Type, string Name, double Price, double Quantity){
    if(Type == "liter")
        return new literProduct(Name,Price,Quantity);
    else if(Type == "kilos")
        return new kilosProduct(Name,Price,Quantity);
    else if(Type == "pieces")
        return new piecesProduct(Name,Price,Quantity);
    else   return nullptr;
}
