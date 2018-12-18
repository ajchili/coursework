def sortArray(array):
  sortedArray = []

  # Iterates through the elements of the array and compares
  # them to the sorted elements, arranging the provided array
  # in a sorted array.
  for string in array:
    added = False
    for i, _string in enumerate(sortedArray):
      # Adds word at current index if it is lexically greater
      # than or equal to the word at the current index
      if string < _string or string == _string:
        sortedArray.insert(i, string)
        added = True
        break

    # Adds color at beginning of array if its second character
    # is lexically less than all others in array
    if not added:
      sortedArray.append(string)

  return sortedArray


def sortNumArray(numArray):
  sortedArray = []

  # Iterates through the elements of the array and compares
  # them to the sorted elements, arranging the provided array
  # in a sorted array.
  for number in numArray:
    added = False
    for (i, _number) in enumerate(sortedArray):
      if number <= _number:
        # Adds number at current index if it is less than or
        # equal to the number at the current index
        sortedArray.insert(i, number)
        added = True
        break

    # Adds number to end of array if it is not less than or
    # equal to any of the numbers currently in the array
    if not added:
      sortedArray.append(number)

  return sortedArray


def sortColorsArray(array):
  sortedArray = []

  # Iterates through the elements of the array and compares
  # them to the sorted elements, arranging the provided array
  # in a sorted array.
  for color in array:
    added = False
    for (i, _color) in enumerate(sortedArray):
      # Adds color at current index if its second character is
      # lexically greater than or equal to second character of
      # word at current index
      if color[1] < _color[1] or color[1] == color[1]:
        sortedArray.insert(i, color)
        added = True
        break

    # Adds color at beginning of array if its second character
    # is lexically less than all others in array
    if not added:
      sortedArray.append(color)

  return sortedArray

# Base string
string = "jkas djw kal skdl hu"
# Split string 3 times
splices = [string[0:5], string[6:13], string[13:19]]

# Create list from string using space as delimeter
list = string.split()
print(list)

# Capitalize all words in base string
capitalizedString = ""
for word in list:
  capitalizedString += word.capitalize() + " "
print(capitalizedString)

# Replace all spaces with hyphen in base string
hypenatedString = ""
for i in range(len(string)):
  if string[i] is ' ':
    hypenatedString += '-'
  else:
    hypenatedString += string[i]
print(hypenatedString)

# Create list from hypenatedString with hypen as delimeter
list = hypenatedString.split("-")
print(list)

# Join list with new delimeter
joinedString = ";".join(list)
print(joinedString)

# Sort words
words = ['testing', 'word', 'mound', 'cabbage', 'spike']
print(sortArray(words))

# Sort numbers
numbers = [24, 598, 1, 800, 15]
print(sortNumArray(numbers))

# Sort colors
colors = ['red', 'blue', 'purple', 'green', 'orange']
print(sortColorsArray(colors))