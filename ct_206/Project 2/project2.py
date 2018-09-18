def reverse_array(input_array):
    # Get the length of the provided array.
    length = len(input_array)

    # Initialize an array to the same length of the provided array.
    output_array = [None]*length

    # Remove one from the calculated length of the input array.
    # This allows the calculations below to not have an index
    # out of range error.
    length -= 1

    # Iterates through the input array, assigning the values of that
    # array to the reversed/opposite index of the ouput array.
    for (i, element) in enumerate(input_array):
        output_array[length - i] = element

    # Returns output array.
    return output_array


def sum_array(input_array):
    # Initialize a variable to hold the sum of the values within the
    # provided array.
    sum = 0

    # Iterates through the input array, adding each value within the
    # provided array to the sum variable, calculating the sum.
    for x in input_array:
        sum += x

    # Returns the sum of all values within the provided array.
    return sum