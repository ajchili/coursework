#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/time.h>

#define ARRAY_SIZE 10000

int offset = 0;
int array[ARRAY_SIZE];
int totalThreads;
int threadCount;
int threadMinimums[100];
int threadMaximums[100];
int foundMinimum;
int foundMaximum;

void resetVariables(int totalThreads);
void findMinimumAndMaximumForConfiguration();
void *runner(void *params);

int main(int argc, char** argv) {
  srand(time(0));
  // Fill Array
  for (int i = 0; i < ARRAY_SIZE; i++) {
    offset += rand() % 5;
    array[i] = i + 1 + offset;
  }
  // Shuffle Array
  for (int i = 0; i < ARRAY_SIZE; i++) {
    int temp = array[i];
    int index = rand() % ARRAY_SIZE;
    array[i] = array[index];
    array[index] = temp;
  }
  // Print frist and last 10 values of the array
  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 10; j++) {
      int index = j;
      if (i == 1) {
        index = ARRAY_SIZE - 1 - j;
      }
      int value = array[index];
      printf("Index (%d): %d\n", index, value);
    }
  }
  // Find min and max in array with 2 threads.
  resetVariables(2);
  findMinimumAndMaximumForConfiguration();
  // Find min and max in array with 10 threads.
  resetVariables(10);
  findMinimumAndMaximumForConfiguration();
  // Find min and max in array with 100 threads.
  resetVariables(100);
  findMinimumAndMaximumForConfiguration();
  exit(0);
}

void resetVariables(int _totalThreads) {
  memset(threadMinimums, ARRAY_SIZE, sizeof threadMinimums);
  memset(threadMaximums, -1, sizeof threadMaximums);
  totalThreads = _totalThreads;
  threadCount = 0;
  foundMinimum = ARRAY_SIZE;
  foundMaximum = -1;
}

void findMinimumAndMaximumForConfiguration() {
  pthread_t tid;
  struct timeval start, end;
  gettimeofday(&start, NULL);
  for (int i = 0; i < totalThreads; i++) {
    pthread_create(&tid, NULL, runner, NULL);
    pthread_join(tid, NULL);
  }
  gettimeofday(&end, NULL);
  for (int i = 0; i < totalThreads; i++) {
    if (threadMinimums[i] < foundMinimum) {
      foundMinimum = threadMinimums[i];
    }
    if (threadMaximums[i] > foundMaximum) {
      foundMaximum = threadMaximums[i];
    }
  }
  printf("\nThreads: %d\nMinimum: %d\nMaximum: %d\nTime (microseconds): %ld\n\n\n", threadCount, foundMinimum, foundMaximum, end.tv_usec - start.tv_usec);
}

void *runner(void *params) {
  int threadNumber = threadCount++;
  int threadMinimum = ARRAY_SIZE;
  int threadMaximum = 0;
  int BLOCK_SIZE = ARRAY_SIZE / totalThreads;
  for (int i = threadNumber * BLOCK_SIZE; i <= (threadNumber + 1) * BLOCK_SIZE; i++) {
    if (i >= ARRAY_SIZE) {
      break;
    }
    if (array[i] < threadMinimum) {
      threadMinimum = array[i];
    }
    if (array[i] > threadMaximum) {
      threadMaximum = array[i];
    }
  }
  threadMinimums[threadNumber] = threadMinimum;
  threadMaximums[threadNumber] = threadMaximum;
}
