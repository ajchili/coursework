#include <iostream>
#include <string>

using namespace std;

int main(int argc, const char * argv[]) {
    string input, reversedInput;
    cout << "Please enter a word: ";
    cin >> input;
    
    for (int i = input.length() - 1; i >= 0; i--) {
        reversedInput += input.at(i);
    }
    
    cout << "The word is ";
    if (input.compare(reversedInput)) {
        cout << "not ";
    }
    cout << "a palindrome." << endl;
    
    return 0;
}
