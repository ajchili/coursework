#include <iostream>
#include <string>

int getIndexOfChar(std::string expression, char character) {
    for (int i = 0; i < expression.length(); i++) {
        if (expression.at(i) == character) {
            return i;
        }
    }
    
    return -1;
}

int getLastIndexOfChar(std::string expression, char character) {
    for (int i = expression.length() - 1; i > 0; i--) {
        if (expression.at(i) == character) {
            return i;
        }
    }
    
    return -1;
}

int getIndexOfNegative(std::string expression) {
    bool isPreviousCharMinus = false;
    
    for (int i = 0; i < expression.length(); i++) {
        if (isPreviousCharMinus && expression.at(i) != ' ') {
            return i;
        }
        
        isPreviousCharMinus = expression.at(i) == '-';
    }
    
    return -1;
}

bool contains(std::string expression, char character) {
    return getIndexOfChar(expression, character) > -1;
}

bool hasNegative(std::string expression) {
    bool isPreviousCharMinus = false;
    
    for (int i = 0; i < expression.length(); i++) {
        if (isPreviousCharMinus && expression.at(i) != ' ') {
            return true;
        }
        
        isPreviousCharMinus = expression.at(i) == '-';
    }
    
    return false;
}

int calculateExpression(std::string expression) {
    if (!contains(expression, ' ')) {
        return std::stoi(expression);
    }
    
    // std::cout << expression << std::endl;
    
    bool hasSubExpression = contains(expression, '(') || contains(expression, ')');
    bool hasMultiplicationOrDivision = contains(expression, '*') || contains(expression, '/');
    bool hasAdditionOrSubtraction = contains(expression, '+') || contains(expression, '-');
    
    if (hasSubExpression) {
        int subIndexStart = getIndexOfChar(expression, '(');
        int subIndexEnd = getLastIndexOfChar(expression, ')');
        
        std::string subExpression = expression.substr(subIndexStart + 1, subIndexEnd - 1);
        
        if (subIndexStart > 0) {
            return calculateExpression(expression.substr(0, subIndexStart) + std::to_string(calculateExpression(subExpression)) + expression.substr(subIndexEnd + 1));
        } else {
            return calculateExpression(expression.substr(0, subIndexEnd));
        }
    } if (hasMultiplicationOrDivision) {
        int index = getIndexOfChar(expression, '*');
        bool isMultiplication = true;
        int startIndex = 0;
        int endIndex = expression.length() - 1;
        
        if (index == -1) {
            index = getIndexOfChar(expression, '/');
            isMultiplication = false;
        }
        
        for (int i = index - 1; i > 0; i--) {
            if (expression.at(i) == ' ') {
                startIndex = i - 1;
                break;
            }
        }
        
        for (int i = index + 2; i < expression.length(); i++) {
            if (expression.at(i) == ' ') {
                endIndex = i;
                break;
            }
        }
        
        
        int firstNumber;
        int secondNumber;
        
        if (hasNegative(expression)) {
            int indexOfNegative = getIndexOfNegative(expression);
            
            if (indexOfNegative > startIndex) {
                firstNumber = std::stoi(expression.substr(startIndex, index));
                secondNumber = std::stoi(expression.substr(indexOfNegative - 1, endIndex));
            } else {
                firstNumber = std::stoi(expression.substr(startIndex - 1, index));
                secondNumber = std::stoi(expression.substr(index + 1, endIndex));
            }
        } else {
            firstNumber = std::stoi(expression.substr(startIndex, index));
            secondNumber = std::stoi(expression.substr(index + 1, endIndex));
        }
        
        if (isMultiplication) {
            if (endIndex != expression.length() - 1) {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber * secondNumber) + expression.substr(endIndex));
            } else {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber * secondNumber));
            }
        } else {
            if (endIndex != expression.length() - 1) {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber / secondNumber) + expression.substr(endIndex));
            } else {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber / secondNumber));
            }
        }
    } else if (hasAdditionOrSubtraction) {
        int index = getIndexOfChar(expression, '+');
        bool isAddition = true;
        int startIndex = 0;
        int endIndex = expression.length() - 1;
        
        if (index == -1) {
            index = getIndexOfChar(expression, '-');
            isAddition = false;
        }
        
        for (int i = index - 1; i > 0; i--) {
            if (expression.at(i) == ' ') {
                startIndex = i - 1;
                break;
            }
        }
        
        for (int i = index + 2; i < expression.length(); i++) {
            if (expression.at(i) == ' ') {
                endIndex = i;
                break;
            }
        }
        
        int firstNumber;
        int secondNumber;
        
        if (hasNegative(expression)) {
            int indexOfNegative = getIndexOfNegative(expression);
            
            if (indexOfNegative > startIndex) {
                firstNumber = std::stoi(expression.substr(startIndex, index));
                secondNumber = std::stoi(expression.substr(indexOfNegative - 1, endIndex));
            } else {
                firstNumber = std::stoi(expression.substr(startIndex - 1, index));
                secondNumber = std::stoi(expression.substr(index + 1, endIndex));
            }
        } else {
            firstNumber = std::stoi(expression.substr(startIndex, index));
            secondNumber = std::stoi(expression.substr(index + 1, endIndex));
        }
        
        if (isAddition) {
            if (endIndex != expression.length() - 1) {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber + secondNumber) + expression.substr(endIndex));
            } else {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber + secondNumber));
            }
        } else {
            if (endIndex != expression.length() - 1) {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber - secondNumber) + expression.substr(endIndex));
            } else {
                return calculateExpression(expression.substr(0, startIndex) + std::to_string(firstNumber - secondNumber));
            }
        }
    }
    
    return std::stoi(expression);
}

int main(int argc, const char * argv[]) {
    std::string expression;
    std::cout << "Please enter a mathmatical expression: ";
    getline(std::cin, expression);
    
    std::cout << calculateExpression(expression) << std::endl;
    
    return 0;
}
