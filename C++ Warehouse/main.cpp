#include <iostream>
#include <string>
#include <map>
#include <vector>
#include <algorithm>
#include <limits>
#include "abstractProduct.h"
#include "literProduct.h"
#include "kilosProduct.h"
#include "piecesProduct.h"
#include "productFactory.h"
#include "Storage.h"

using namespace std;

void convertToUpperCase(string &phrase){
    for_each(phrase.begin(),phrase.end(),[](char & c){
        c = ::toupper(c);
    });
}

void inStreamInteger(int& inputInteger){
    cin >> inputInteger;
    if(cin.fail()){
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(),'\n');
        cout << "You must enter a NUMBER: " << endl;
    }
}

void inStreamDouble(double& inputDouble){
    cin >> inputDouble;
    if(cin.fail()){
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(),'\n');
        cout << "You must enter a NUMBER: " << endl;
    }
}

int main(){

    int keepGoing = 1;

    map<string,double> nameQuantity;

    vector<abstractProduct*> productObjects;

    Storage* Magazzino = new Storage(nameQuantity,productObjects);

    while(keepGoing == 1){
        cout << "Benvenuto al programma Magazzino" << endl;
        cout << "Selezione un'opzione:\n1 - Menù utente\n2 - Menù magazziniere\n3 - Esci dal programma\n";

        int userChoice = 0;
        while(userChoice!=1 && userChoice!=2 && userChoice!=3){
            inStreamInteger(userChoice);
        }
        if(userChoice == 3)
            return 0;
        while(userChoice == 1){
            Storage* Carrello = new Storage();
            productFactory customerFactory;
            cout << "1 - Fai il log-out\n2 - Consulta il catalogo\n";
            int customerAction = 0;
            while(customerAction != 1 && customerAction != 2)
                inStreamInteger(customerAction);
            if(customerAction == 2){
                Magazzino->printStorage();
                customerAction = 0;
                cout << "1 - Fai il log-out\n2 - Effettua un ordine\n";
                while(customerAction!=1 && customerAction!=2)
                    inStreamInteger(customerAction);
                if(customerAction == 1)
                    break;
            }
            else
                break;
            double totalPrice = 0;
            while(customerAction == 2){
                cout << "Inserire il nome del prodotto" << endl;
                string orderedProduct;
                cin >> orderedProduct;
                convertToUpperCase(orderedProduct);
                if(Magazzino->productInStock(orderedProduct)){
                    cout << "Inserire la quantità desiderata" << endl;
                    double orderedQuantity = -1;
                    while(orderedQuantity <= 0)
                        inStreamDouble(orderedQuantity);
                    if(Magazzino->getProduct(orderedProduct)->getMeasureUnit() == "")
                        orderedQuantity = (int)orderedQuantity;
                    string boughtType = Magazzino->productOrdering(orderedProduct,orderedQuantity,totalPrice);
                    abstractProduct* boughtProduct = nullptr;
                    if(Carrello->Contains(orderedProduct))
                        Carrello->addQuantity(orderedProduct,orderedQuantity);
                    else{
                        if(boughtType == "l")
                            boughtProduct = customerFactory.automaticProduct("liter",orderedProduct,(Magazzino->getProduct(orderedProduct))->getPrice(),orderedQuantity);
                        else if(boughtType == "kg")
                            boughtProduct = customerFactory.automaticProduct("kilos",orderedProduct,(Magazzino->getProduct(orderedProduct))->getPrice(),orderedQuantity);
                        else if(boughtType == "")
                            boughtProduct = customerFactory.automaticProduct("pieces",orderedProduct,(Magazzino->getProduct(orderedProduct))->getPrice(),orderedQuantity);
                        else{
                            cout << "L'operazione non è andata a buon fine: " << endl;
                            cout << "1 - Fai il log-out\n2 - Prosegui\n";
                            customerAction = 0;
                            while(customerAction!= 1 && customerAction!= 2)
                                inStreamInteger(customerAction);
                            if(customerAction == 1){
                                userChoice = 1;
                                delete Carrello;
                                break;
                            }
                        }
                        if(boughtType!="nothing")
                            Carrello->addElement(boughtProduct);
                    }
                }
                else{
                    cout << "Prodotto non esistente" << endl;
                }
                cout << "\nIl carrello è:\n";
                Carrello->printStorage();
                cout << "\nIl magazzino è:\n";
                Magazzino->printStorage();
                cout << "\nIl prezzo totale ammonta a:\n";
                cout << totalPrice << "$\n";
                cout << "\n1 - Fai il log-out\n2 - Inserisci un altro prodotto\n3 - Vai al pagamento\n4 - Rimuovi un prodotto dal carrello\n";
                customerAction = 0;
                while(customerAction != 1 && customerAction != 2 && customerAction != 3 && customerAction != 4)
                    inStreamInteger(customerAction);
                if(customerAction == 1){
                    userChoice = 0;
                    Magazzino->restoreStorage(*Carrello);
                    delete Carrello;
                }
                else if(customerAction == 3){
                    cout << "TRANSAZIONE ESEGUITA" << endl;
                    cout << "Permere <Enter> per tornare al menù iniziale" << endl;
                    cin.get();
                    cin.get();
                    userChoice = 0;
                    delete Carrello;
                }
                while(customerAction == 4){
                    cout << "Inserire il nome del prodotto da rimuovere" << endl;
                    string removeThisProduct;
                    cin >> removeThisProduct;
                    convertToUpperCase(removeThisProduct);
                    if(!Carrello->Contains(removeThisProduct)){
                        cout << "Il prodotto inserito non è presente nel carrello" << endl;
                        customerAction = 0;
                        cout << "1 - Annulla l'operazione\n2 - Inserisci un altro prodotto" << endl;
                        while(customerAction!=1 && customerAction!=2)
                            inStreamInteger(customerAction);
                        if(customerAction == 1)
                            break;
                        else
                            customerAction = 4;
                    }
                    else{
                        cout << "Che quantità vuole rimuovere?\n";
                        double removeThisQuantity = -1;
                        while (Carrello->howMuch(removeThisProduct) < removeThisQuantity || removeThisQuantity < 0)
                            inStreamDouble(removeThisQuantity);
                         if(Carrello->getProduct(removeThisProduct)->getMeasureUnit() == "")
                            removeThisQuantity = (int)removeThisQuantity;
                    Carrello->removeQuantity(removeThisProduct,removeThisQuantity);
                    Magazzino->addQuantity(removeThisProduct,removeThisQuantity);
                    totalPrice -= (Magazzino->getProduct(removeThisProduct)->getPrice())*removeThisQuantity;
                    cout << "Il carrello è:" << endl;
                    Carrello->printStorage();
                    cout << "Il magazzino è:" << endl;
                    Magazzino->printStorage();
                    cout << "Il prezzo totale ammonta a:\n " << totalPrice << "$" << endl;
                    cout << "\n1 - Fai il log-out\n2 - Inserisci un prodotto\n3 - Vai al pagamento\n4 - Rimuovi un altro prodotto\n";
                    customerAction = 0;
                    while(customerAction != 1 && customerAction != 2 && customerAction != 3 && customerAction != 4)
                        inStreamInteger(customerAction);
                    if(customerAction == 1){
                        userChoice = 0;
                        Magazzino->restoreStorage(*Carrello);
                        delete Carrello;
                    }
                    else if(customerAction == 3){
                        cout << "TRANSAZIONE ESEGUITA" << endl;
                        cout << "Permere <Enter> per tornare al menù iniziale" << endl;
                        cin.get();
                        cin.get();
                        userChoice = 0;
                        delete Carrello;
                    }
                    }
                }
            }
        }


        while(userChoice == 2){
            productFactory storageFactory;
            cout << "1 - Fai il log-out\n2 - Consulta il catalogo" << endl;
            int workerAction = 0;
            while(workerAction!=1 && workerAction!=2)
                inStreamInteger(workerAction);
            if(workerAction==1)
                break;
            else if(workerAction==2){
                Magazzino->printStorage();
                workerAction = 0;
                cout << "1 - Fai il log-out\n2 - Aggiungi un prodotto" << endl;
                while(workerAction!=1 && workerAction!=2)
                    inStreamInteger(workerAction);
                if(workerAction == 1)
                    break;
                else{
                    string newProductName;
                    string newProductType;
                    double addingProductQuantity=-1;
                    double newProductPrice=-1;
                    cout << "Inserisci il nome del prodotto" << endl;
                        cin >> newProductName;
                    convertToUpperCase(newProductName);

                    if(Magazzino->productInStock(newProductName)){

                        cout << "Il prodotto è già nel magazzino. Inserire la quantità da aggiungere" << endl;
                        while(addingProductQuantity<=0)
                            inStreamDouble(addingProductQuantity);
                        if(Magazzino->getProduct(newProductName)->getMeasureUnit() == "")
                            addingProductQuantity = (int)addingProductQuantity;
                        Magazzino->addQuantity(newProductName,addingProductQuantity);
                        cout << "Prodotto aggiunto correttamente" << endl;
                    }
                    else{
                        cout << "Inserisci il tipo del prodotto che vorresti aggiungere (liter,kilos or pieces)" << endl;
                        while(newProductType!="liter" && newProductType!="kilos" && newProductType!="pieces")
                            cin >> newProductType;
                        cout << "Inserisci il prezzo" << endl;
                        while(newProductPrice<=0)
                            inStreamDouble(newProductPrice);
                        cout << "Inserisci la quantità" << endl;
                        while(addingProductQuantity<=0)
                            inStreamDouble(addingProductQuantity);
                        if(newProductType == "pieces")
                            addingProductQuantity = (int)addingProductQuantity;
                        Magazzino->addElement(storageFactory.automaticProduct(newProductType,newProductName,newProductPrice,addingProductQuantity));
                        cout << "Prodotto aggiunto correttamente" << endl;
                    }
                }
            }
        }
    }
    return 0;
}
