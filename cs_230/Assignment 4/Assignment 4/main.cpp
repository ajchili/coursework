#include "BoundSortedType.h"
#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    BoundSortedType a = BoundSortedType();
    a.addItem(*new RandomItem("Testing", 2));
    a.addItem(*new RandomItem("Testing", 3));
    a.addItem(*new RandomItem("Testing", 4));
    cout << a.length << endl;
    return 0;
}
