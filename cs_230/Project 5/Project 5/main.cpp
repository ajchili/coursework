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

vector<string> getPermutation(string word) {
    vector<string> words;
    
    if (word.length() > 1) {
        for (int i = 0; i < word.length(); i++) {
            string characters = "";
            for (int j = 0; j < word.length(); j++) {
                if (j != i) {
                    characters += word.at(j);
                }
            }
            vector<string> permutations = getPermutation(characters);
            for (int j = 0; j < permutations.size(); j++) {
                words.push_back(word.substr(i, 1) + permutations.at(j));
            }
        }
    } else if (word.length() == 1) {
        words.push_back(word);
    }
    
    return words;
}

int main(int argc, const char * argv[]) {
    string word;
    cout << "Please enter a word: ";
    cin >> word;
    
    int numOfPermutations = getNumberOfPermutations(word.length());
    cout << "Expected Number of Permutations: " << numOfPermutations << endl;
    vector<string> words = getPermutation(word);
    
    for (int i = 0; i < words.size(); i++) {
        cout << (i + 1) << ": " << words.at(i) << endl;
    }
    
    return 0;
}
