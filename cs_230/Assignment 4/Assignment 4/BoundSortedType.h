#ifndef BoundSortedType_h
#define BoundSortedType_h

#include <iostream>
#include <string>

using namespace std;

struct RandomItem {
    string name;
    int size;
  public:
    RandomItem(string name, int size) {
        this->name = name;
        this->size = size;
    };
    bool isEqual(RandomItem item) {
        return item.name == name && item.size == size;
    }
};

struct BoundSortedType {
    RandomItem *items[1];
    int length = 1;
public:
    BoundSortedType();
    void addItem(RandomItem item);
};

BoundSortedType::BoundSortedType() {
    
}

void BoundSortedType::addItem(RandomItem item) {
    if (items[length] == nullptr) {
        
        for (int i = 0; i < length; i++) {
            if (items[i] == nullptr) {
                items[i] = &item;
                break;
            }
        }
    } else {
        length = length * 2;
        RandomItem *newItems[this->length];
        
        for (int i = 0; i < length / 2; i++) {
            newItems[i] = items[i];
        }
        
        *items = *newItems;
    }
}

#endif
