#include <iostream>
#include <string>
#include <map>
#include <algorithm>
#include "abstractProduct.h"

using namespace std;

string abstractProduct::getName() {
    return name;
}
double abstractProduct::getPrice() {
    return price;
}
double abstractProduct::getQuantity() {
return quantity;
}

double abstractProduct::getOrderPrice(double quantity) {
    return price * quantity;
}

    //Setter
void abstractProduct::setName(string newName) {
    name = newName;
}
void abstractProduct::setPrice(double newPrice) {
    price = newPrice;
}
void abstractProduct::setQuantity(double newQuantity) {
    quantity = newQuantity;
}

void abstractProduct::removeUnits(double numberOfUnits) {
    quantity -= numberOfUnits;
}
void abstractProduct::addUnits(double newUnits) {
    quantity += newUnits;
}

bool abstractProduct::isAvailable(double numberRequested) {
    if(quantity - numberRequested >= 0)
        return true;
    return false;
}

