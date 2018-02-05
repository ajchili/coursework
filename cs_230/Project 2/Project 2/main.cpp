#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Person.h"

using namespace std;

int numberOfPeople = 0;

Person createNewUser(string user) {
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
                    input = "";
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
    numberOfPeople++;
    return Person(sex, name, phoneNumber, noi, interests, input);
}

void readFile() {
    vector<Person> people;
    ifstream infile("Clients.mf");
    string line;
    while(getline(infile, line)) {
        people.push_back(createNewUser(line));
    }
    people.at(0).unMatch(people, "Jennifer");
}

int main() {
    readFile();
    return 0;
}
