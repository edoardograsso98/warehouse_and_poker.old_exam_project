#include <iostream>
#include <string>
#include <map>
#include "literProduct.h"

using namespace std;

literProduct::literProduct(string productName, double productPrice, double initialQuantity) {
    setName(productName);
    setPrice(productPrice);
    setQuantity(initialQuantity);
}

void literProduct::printPrice() {
    cout << getPrice() << "$/l";
}

void literProduct::printStock() {
    cout << getQuantity() << "l";
}

const string literProduct::getMeasureUnit() {
    return measureUnit;
}
