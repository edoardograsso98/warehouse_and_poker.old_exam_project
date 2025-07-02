#include <iostream>
#include <string>
#include <map>
#include "piecesProduct.h"

using namespace std;

piecesProduct::piecesProduct(string productName, double productPrice, double initialQuantity) {
    setName(productName);
    setPrice(productPrice);
    setQuantity(initialQuantity);
}

void piecesProduct::printPrice() {
    cout << getPrice() << "$";
}

void piecesProduct::printStock() {
    cout << getQuantity() << "";
}

const string piecesProduct::getMeasureUnit() {
    return measureUnit;
}

