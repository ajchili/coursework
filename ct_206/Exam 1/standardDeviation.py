import math

def standardDeviation(values):
    length = len(values)
    mean = sum(values) / length
    summation = 0
    for value in values:
        summation += (value - mean)**2
    standardDeviation = math.sqrt(summation / length)
    return standardDeviation