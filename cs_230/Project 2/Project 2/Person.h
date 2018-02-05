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
    string interests[0];
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
        string *interests = new string[numOfInterests];
        
        string interest = "";
        int iteration = 0;
        for (int n = 0; n < i.length(); n++) {
            if (iteration > numOfInterests) {
                return;
            }
            
            if (i[n] == ',') {
                interests[iteration] = interest;
                iteration++;
                interest = "";
            } else {
                interest += i[n];
            }
        }
        interests[iteration] = interest;
    }
    void unMatch(vector<Person>, string);
    void printMatch();
    void printFree();
};

void Person::unMatch(vector<Person> people, string n) {
    match = "";
    for (int i = 0; i < people.size(); i++) {
        Person* person = &people[i];
        if (n = person->name) {
            match = "";
        }
    }
}

void Person::printMatch() {
    cout << match << endl;
}

void Person::printFree() {
    cout << match;
    if (match.length() == 0) {
        cout << "Free" << endl;
    } else {
        cout << "Taken" << endl;
    }
}

#endif
