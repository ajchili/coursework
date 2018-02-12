#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Person.h"

using namespace std;

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
                    noi = user[i - 1] - 48;
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
    return Person(sex, name, phoneNumber, noi, interests, input);
}

vector<Person> match(vector<Person> people) {
    for (int i = 0; i < people.size(); i++) {
        Person personA = people.at(i);
        
        if (personA.match.length() == 1) {
            for (int j = 0; j < people.size(); j++) {
                if (j != i) {
                    Person personB = people.at(j);
                    
                    if (personA.sex != personB.sex && personB.match.length() == 1) {
                        int matchCount = 0;
                        
                         for (int k = 0; k < personA.interests.size(); k++) {
                            for (int l = 0; l < personB.interests.size(); l++) {
                                if (personA.interests.at(k) == personB.interests.at(l)) {
                                    matchCount++;
                                    break;
                                }
                            }
                        }
                        
                        if (matchCount >= 3) {
                            people.at(i).match = personB.name;
                            people.at(j).match = personA.name;
                        }
                    }
                }
            }
        }
    }
    
    return people;
}

void readFile() {
    vector<Person> people;
    ifstream infile("Clients.mf");
    string line;
    while(getline(infile, line)) {
        people.push_back(createNewUser(line));
    }
    people = match(people);
}

int main() {
    readFile();
    return 0;
}
