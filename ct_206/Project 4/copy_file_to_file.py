# Opens file from provided name and allows provided permissions
#
# file_name    Name of file
# permissions File permissions (r, w+)
# Returns file object
def open_file(file_name, permissions):
  f = open(file_name, permissions, encoding='utf-8')
  return f

# Writes provided data to provided file
#
# f    File object
# list List of data objects to be written to file
def write_to_file(f, list):
  for item in list:
    f.write(str(item))

# Converts provided file object to tuple, then returns tuple
#
# f File object
# Returns tuple of data within file
def read_from_file(f):
  data = tuple(f)
  return data

# Closes file i/o
# File object is the only provided parameter
def close_file(f):
  f.close()

f1 = open_file('input.txt', 'r')
f2 = open_file('output.txt', 'w+')
list = read_from_file(f1)
write_to_file(f2, list)
close_file(f1)
close_file(f2)