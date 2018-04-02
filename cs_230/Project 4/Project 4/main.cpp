#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    srand((unsigned)time(0));
    int diceCount = 0;
    int diceSize = 0;
    
    cout << "How many dice would you like to roll? ";
    cin >> diceCount;
    
    while (diceCount <= 0) {
        cout << "How many dice would you like to roll? (Must be at least 1) ";
        cin >> diceCount;
    }
    
    cout << "How many sides are on each dice? ";
    cin >> diceSize;
    
    while (diceSize <= 1) {
        cout << "How many sides are on each dice? (Must be at least 2) ";
        cin >> diceSize;
    }
    
    for (int i = 0; i < 10; i++) {
        cout << "Set " << i << ":" << endl;
        for (int j = 0; j < diceCount; j++) {
            int die = j + 1;
            int side = (rand() % diceSize) + 1;
            cout << "For die " << die << ", it landed on " << side << endl;
        }
    }
    
    return 0;
}
