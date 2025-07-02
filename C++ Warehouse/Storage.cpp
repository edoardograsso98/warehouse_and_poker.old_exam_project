#include <iostream>
#include <algorithm>
#include "abstractProduct.h"
#include "literProduct.h"
#include "kilosProduct.h"
#include "piecesProduct.h"
#include <map>
#include <vector>

#include "Storage.h"

using namespace std;

Storage::Storage(){}

Storage::Storage(map<string,double>nameQuantity,vector<abstractProduct*>productObjects)
{
    literProduct* Passata = new literProduct("PASSATA",1.50,70);
    literProduct* Latte = new literProduct("LATTE",1.00,50);
    piecesProduct* Biro = new piecesProduct("BIRO",1.30,100);
    kilosProduct* Burro = new kilosProduct("BURRO",2.70,40);
    kilosProduct* Pollo = new kilosProduct("POLLO",11.30,12);
    piecesProduct* Sedie = new piecesProduct("SEDIE",16.90,30);
    kilosProduct* Alici = new kilosProduct("ALICI", 13.20,80);

    this->addElement(Biro);
    this->addElement(Burro);
    this->addElement(Passata);
    this->addElement(Latte);
    this->addElement(Pollo);
    this->addElement(Sedie);
    this->addElement(Alici);

    sort(productObjects.begin(),productObjects.end(),[](abstractProduct* a,abstractProduct* b){
        return (*a).getName()< (*b).getName();
    });
}

void Storage::printStorage(){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        cout << iter->first << "\t";
        (*(*iterVector)).printStock();
        cout <<"\t";
        (*(*iterVector)).printPrice();
        cout << endl;
        iter++;
        iterVector++;
    }
}

void Storage::addElement(abstractProduct* newProduct){
    nameQuantity.insert(pair<string,double>(newProduct->getName(),newProduct->getQuantity()));
    productObjects.push_back(newProduct);
    sort(productObjects.begin(),productObjects.end(),[](abstractProduct* a,abstractProduct* b){
        return (*a).getName()< (*b).getName();
    });
}

void Storage::setElement(abstractProduct* setThisProduct){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == setThisProduct->getName()){
            iter->second = setThisProduct->getQuantity();
            (*iterVector)->setQuantity(setThisProduct->getQuantity());
        }
    iter++;
    iterVector++;
    }
}


void Storage::addQuantity(string productName, double addingQuantity){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == productName){
            iter->second += addingQuantity;
            (*iterVector)->addUnits(addingQuantity);
            }
        iter++;
        iterVector++;
    }
}

void Storage::removeQuantity(string productName, double removingQuantity){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == productName){
            iter->second -= removingQuantity;
            (*iterVector)->removeUnits(removingQuantity);
  /*          if(iter->second == 0){
                nameQuantity.erase(iter);
                productObjects.erase(iterVector);
            }*/
        }
        iter++;
        iterVector++;
    }
}

bool Storage::productInStock(string productName){
    map<string,double>::iterator iter;
    iter = nameQuantity.begin();
    while(iter!=nameQuantity.end()){
        if(productName == iter->first)
            return true;
        iter++;
    }
    return false;
}

string Storage::productOrdering(string productName, double &productQuantity, double &totalPrice){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == productName){
            if(iter->second < productQuantity){
                cout << "Sono disponibili solo " << iter->second << " unità del prodotto richiesto in magazzino." << endl;
                cout << "Procedere comunque?\n1 - Sì\n2 - No\n";
                int customerChoice = 0;
                while(customerChoice!=1 && customerChoice!=2){
                    cin >> customerChoice;
                }
                if(customerChoice == 2)
                    break;
                productQuantity = iter->second;
            }
            totalPrice += (*(*iterVector)).getPrice() * productQuantity;
            iter->second -= productQuantity;
            (*(*iterVector)).removeUnits(productQuantity);
            return (*(*iterVector)).getMeasureUnit();
        }
        iter++;
        iterVector++;
    }
    return "nothing";
}

abstractProduct* Storage::getProduct(string productName){
    map<string,double>::iterator iter;
    vector<abstractProduct*>::iterator iterVector;
    iter = nameQuantity.begin();
    iterVector = productObjects.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == productName)
            return (*iterVector);
        iter++;
        iterVector++;
    }
}

bool Storage::Contains(string productName){
    map<string,double>::iterator iter;
    iter = nameQuantity.begin();
    while(iter!=nameQuantity.end()){
        if(iter->first == productName)
            return true;
        iter++;
    }
    return false;
}

void Storage::restoreInitialStorage(){
    literProduct* Passata = new literProduct("PASSATA",1.50,70);
    literProduct* Latte = new literProduct("LATTE",1.00,50);
    piecesProduct* Biro = new piecesProduct("BIRO",1.30,100);
    kilosProduct* Burro = new kilosProduct("BURRO",2.70,40);
    kilosProduct* Pollo = new kilosProduct("POLLO",11.30,12);
    piecesProduct* Sedie = new piecesProduct("SEDIE",16.90,30);
    kilosProduct* Alici = new kilosProduct("ALICI", 13.20,80);

    this->setElement(Biro);
    this->setElement(Burro);
    this->setElement(Passata);
    this->setElement(Latte);
    this->setElement(Pollo);
    this->setElement(Sedie);
    this->setElement(Alici);

    sort(productObjects.begin(),productObjects.end(),[](abstractProduct* a,abstractProduct* b){
        return (*a).getName()< (*b).getName();
    });
}

void Storage::restoreStorage(Storage Carrello){
    map<string,double>::iterator iter;
    iter = Carrello.nameQuantity.begin();
    while(iter != Carrello.nameQuantity.end()){
        this->addQuantity(iter->first,iter->second);
        iter++;
    }
}

double Storage::howMuch(string productName){
    map<string,double>::iterator iter;
    iter = nameQuantity.begin();
    while(iter != nameQuantity.end()){
        if(iter->first == productName)
            return iter->second;
        iter++;
    }
    return 0;
}
