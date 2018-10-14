# Create dictionary with 5 values
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

# Test for the inclusion of 3 elements
if dictionary["b"] and dictionary["c"] and dictionary["d"]:
  print("True")
else:
  print("False")

# Printing all keys and values in the dictionary
print("Looping through values:")
for key, value in dictionary.items():
  print(key, ":", value)