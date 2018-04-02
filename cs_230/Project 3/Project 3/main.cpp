#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    double avg = 0.0;
    int count = 0;
    double input;
    
    cin >> input;
    
    while (input != -1) {
        int newCount = count + 1;
        avg = ((avg * count) + input) / (newCount + 1);
        count = newCount;
        cin >> input;
    }
    
    cout << "The average of the " << count << " numbeers is: " << avg << endl;
    
    return 0;
}
