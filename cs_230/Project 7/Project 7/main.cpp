#include <iostream>
#include <string>

using namespace std;

float calculateExpression(string expression) {
    float value = 0.0;
    
    for (int i = 0; i < expression.length(); i++) {
        cout << expression.at(i) << endl;
    }
    
    return value;
}

int main(int argc, const char * argv[]) {
    string expression, parsedExpression = "";
    cout << "Please enter a mathmatical expression: ";
    getline(cin, expression);
    
    for (int i = 0; i < expression.length(); i++) {
        if (expression.at(i) != ' ') {
            parsedExpression += expression.at(i);
        }
    }
    
    cout << calculateExpression(parsedExpression) << endl;
    
    return 0;
}
