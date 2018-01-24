#include <iostream>

using namespace std;

int pennies = 0, nickles = 0, dimes = 0, quarters = 0;
const int nickleValue = 5, dimeValue = 10, quarterValue = 25;

void getCoints() {
    cout << "Please enter the total number of pennies: ";
    cin >> pennies;
    cout << "Please enter the total number of nickles: ";
    cin >> nickles;
    cout << "Please enter the total number of dimes: ";
    cin >> dimes;
    cout << "Please enter the total number of quarters: ";
    cin >> quarters;
}

void printCoinTotal() {
    float coinValue = (pennies + nickles * nickleValue + dimes * dimeValue + quarters * quarterValue) / 100.0;
    cout << "Total value of coins: $" << coinValue << endl;
}

int main()
{
    getCoints();
    printCoinTotal();
    return 0;
}
