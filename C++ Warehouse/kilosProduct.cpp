#include <iostream>
#include <string>
#include <map>
#include "kilosProduct.h"

using namespace std;

kilosProduct::kilosProduct(string productName, double productPrice, double initialQuantity) {
    setName(productName);
    setPrice(productPrice);
    setQuantity(initialQuantity);
}

void kilosProduct::printPrice() {
    cout << getPrice() << "$/kg";
}

void kilosProduct::printStock() {
    cout << getQuantity() << "kg";
}

const string kilosProduct::getMeasureUnit() {
    return measureUnit;
}
