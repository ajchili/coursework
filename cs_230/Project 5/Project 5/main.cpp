#include <iostream>
#include <string>
#include <vector>

using namespace std;

int getNumberOfPermutations(int length) {
    if (length == 0) {
        return 1;
    } else {
        return length * getNumberOfPermutations(length - 1);
    }
}

vector<string> getPermutation(string word, int index) {
    vector<string> words;
    
    char character = word.at(index);
    for (int i = 0; i < word.length(); i++) {
        
    }
    
    return words;
}

int main(int argc, const char * argv[]) {
    string word;
    cout << "Please enter a word: ";
    cin >> word;
    
    vector<string> words = getPermutation(word, 0);
    
    for (int i = 0; i < words.size(); i++) {
        cout << i << ": " << words.at(i) << endl;
    }
    
    return 0;
}
