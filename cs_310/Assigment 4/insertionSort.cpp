#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int SIZE = 100;

void printArray(int array[]);
void fillArray(int array[]);
void insertionSort(int array[]);

int main()
{
  srand(time(NULL));
  int array[SIZE];
  fillArray(array);
  printArray(array);
  insertionSort(array);
  printArray(array);
  return 0;
}

void printArray(int array[])
{
  printf("\n");
  for (int i = 0; i < SIZE; i++)
  {
    printf("%i ", array[i]);
  }
  printf("\n");
}

void fillArray(int array[])
{
  for (int i = 0; i < SIZE; i++)
  {
    array[i] = rand() % SIZE + 1;
  }
}

/*
 * This form of insertion sort performs the comparison from
 * the beginning of the array. This is done by first shifting
 * all values from 0 -> i by one then setting the value at index
 * 0 to the original value at index i. Once this is done, the
 * array is iterated through again, at each index of the iteration
 * the value at the current index is compared to the next value.
 * If the current value is greater than the next, they are swapped.
 * Otherwise, the nested iteration is stopped and the process
 * repeats itself until completion.
 */
void insertionSort(int array[])
{
  for (int i = 1; i < SIZE; i++)
  {
    int x = array[i];
    int j = i - 1;
    while (j >= 0)
    {
      array[j + 1] = array[j];
      j--;
    }
    array[0] = x;
    j = 0;
    while (j < i && array[j] > array[j + 1])
    {
      x = array[j];
      array[j] = array[j + 1];
      array[j + 1] = x;
      j++;
    }
  }
}