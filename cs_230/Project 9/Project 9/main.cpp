#include <iostream>

using namespace std;

int getPosition(long value) {
    long previousValue = 0;
    long currentValue = 1;
    int position = 2;
    
    while (currentValue < value) {
        long tempValue = currentValue;
        currentValue += previousValue;
        previousValue = tempValue;
        position++;
    }
    
    return position;
}

void getTrace(int position) {
    long previousValue = 0;
    long value = 1;
    
    for (int i = 1; i < position; i++) {
        long tempValue = value;
        value += previousValue;
        previousValue = tempValue;
    }
    
    cout << value << endl;
    while (previousValue > 0) {
        long tempValue = previousValue;
        previousValue = value - previousValue;
        value = tempValue;
        cout << value << endl;
    }
}

int main(int argc, const char * argv[]) {
    long value;
    cout << "Please enter a position in the fibonacchi sequence: ";
    cin >> value;
    getTrace(getPosition(value));
    return 0;
}
