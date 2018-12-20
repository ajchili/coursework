import math
import numpy as np
import random


# Calculates length, mean, standard deviation,
# min, max, 25th, 50th, and 75th percentile of
# specified numbers array
def run_calculations(numbers):
  # Length
  print("Count:", len(numbers))
  # Mean
  print("Mean:", sum(numbers) / len(numbers))
  # Standard Deviation
  length = len(numbers)
  mean = sum(numbers) / length
  summation = 0
  for value in numbers:
    summation += (value - mean)**2
  standardDeviation = math.sqrt(summation / length)
  print("Standard Deviation:", standardDeviation)
  # Min
  print("Min:", min(numbers))
  # Max
  print("Max:", max(numbers))
  # 25th Percentile
  print("25th Percentile", np.percentile(numbers, 25))
  # 50th Percentile
  print("50th Percentile", np.percentile(numbers, 50))
  # 75th Percentile
  print("75th Percentile", np.percentile(numbers, 75))


numbers = []
for i in range(30, random.randint(100, 1000)):
  numbers.append(random.randint(0, 100))
run_calculations(numbers)