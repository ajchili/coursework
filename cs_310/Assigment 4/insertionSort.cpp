#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int SIZE = 100;

void fillArray(int array[]);
void insertionSort(int array[]);

int main()
{
  srand(time(NULL));
  int array[SIZE];
  fillArray(array);
  for (int i = 0; i < SIZE; i++) {
    printf("%i ", array[i]);
  }
  printf("\n\n");
  insertionSort(array);
  for (int i = 0; i < SIZE; i++) {
    printf("%i ", array[i]);
  }
  return 0;
}

void fillArray(int array[]) {
  for (int i = 0; i < SIZE; i++) {
    array[i] = rand() % SIZE + 1;
  }
}

void insertionSort(int array[]) {
  for (int i = 1; i < SIZE; i++) {
    int x = array[i];
    int j = i - 1;
    while (j >= 0 && array[j] > x) {
      array[j + 1] = array[j];
      j--;
    }
    array[j + 1] = x;
  }
}