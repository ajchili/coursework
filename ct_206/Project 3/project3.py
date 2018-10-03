# Project written in Python 3

# Create dictionary with 3 values
dictionary = {
  "a": 1,
  "b": 2,
  "c": 3,
  "d": 4,
  "e": 5
}
print(dictionary)

# Add new key value pair
dictionary["f"] = 6
print(dictionary)

# Change value of key a
dictionary["a"] = 0
print(dictionary)

# Print 3 values via their key
print("Accessing via key:", dictionary["a"], dictionary["b"], dictionary["c"])

# Print 3 keys via their value
print("Accessing via value:", \
      list(dictionary.keys())[list(dictionary.values()).index(0)], \
      list(dictionary.keys())[list(dictionary.values()).index(2)], \
      list(dictionary.keys())[list(dictionary.values()).index(4)])

# Delete an element from the dictionary
del dictionary["a"]
print(dictionary)

# Printing all keys and values in the dictionary
print("Looping through values:")
for key, value in dictionary.items():
  print(key, ":", value)