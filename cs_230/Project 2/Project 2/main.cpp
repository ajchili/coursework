#include <iostream>
#include <fstream>
#include <string>
#include "Person.h"

using namespace std;

int numberOfPeople = 0;

Person createNewUser(string user) {
    Person person;
    char sex;
    string name, phoneNumber, interests;
    int noi;
    string input;
    int index = 0;
    for (int i = 0; i <= user.length(); i++) {
        if (user[i] == ',' && index <= 3) {
            switch(index) {
                case 0:
                    sex = user[i - 1];
                    break;
                case 1:
                    name = input;
                    input = "";
                    break;
                case 2:
                    phoneNumber = input;
                    input = "";
                    break;
                case 3:
                    noi = user[i - 1];
                    input = "";
                    break;
            }
            index++;
        } else if (user[i] == '.') {
            interests = input;
            input = "";
        } else {
            input += user[i];
        }
    }
    person.newClient(sex, name, phoneNumber, noi, interests, input);
    numberOfPeople++;
    return person;
}

void readFile() {
    Person people = *new Person[0];
    ifstream infile("Clients.mf");
    string line;
    while(getline(infile, line)) {
        createNewUser(line);
    }
}

int main() {
    readFile();
    return 0;
}
