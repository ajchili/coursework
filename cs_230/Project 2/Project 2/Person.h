#ifndef Person_h
#define Person_h
#include <iostream>
#include <string>
#include <fstream>

using namespace std;

struct Person {
    char sex;
    string name;
    string phoneNumber;
    int numOfInterests;
    vector<string> interests;
    string match;
  public:
    Person(char s, string n, string p, int noi, string i, string m) {
        sex = s;
        name = n;
        phoneNumber = p;
        match = m;
        if (noi > 10) {
            numOfInterests = 10;
        } else if (noi < 0) {
            numOfInterests = 0;
        } else {
            numOfInterests = noi;
        }
        
        string interest = "";
        for (int n = 0; n < i.length(); n++) {
            if (i[n] == ',') {
                interests.push_back(interest);
                interest = "";
            } else {
                interest += i[n];
            }
        }
        interests.push_back(interest);
    }
    void unMatch(vector<Person>, string);
    void printMatch();
    void printFree();
};

void Person::unMatch(vector<Person> people, string n) {
    match = "";
    for (int i = 0; i < people.size(); i++) {
        Person* person = &people[i];
        if (n == person->name) {
            match = "";
            person->match = "";
            return;
        }
    }
}

void Person::printMatch() {
    cout << match << endl;
}

void Person::printFree() {
    if (match.length() == 1) {
        cout << "Free" << endl;
    } else {
        cout << "Taken" << endl;
    }
}

#endif
