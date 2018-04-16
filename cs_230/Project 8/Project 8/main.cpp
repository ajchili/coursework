#include <iostream>

using namespace std;

long getPosition(int position) {
    long previousValue = 0;
    long value = 1;
    
    for (int i = 1; i < position; i++) {
        long tempValue = value;
        value += previousValue;
        previousValue = tempValue;
    }
    
    return value;
}

int main(int argc, const char * argv[]) {
    int position;
    cout << "Please enter the position of the fibonacci sequence you want to see: ";
    cin >> position;
    cout << "At position " << position << " the value is: " << getPosition(position) << endl;
    
    return 0;
}
