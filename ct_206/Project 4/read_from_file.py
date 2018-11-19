# Opens file from provided name and allows read permissions
# Returns file object for provided file name parameter
def open_file(file_name):
  f = open(file_name, 'r', encoding='utf-8')
  return f

# Converts provided file object to tuple, then prints each element in turple
# File object is the only provided parameter
def read_from_file(f):
  data = tuple(f)
  for row in data:
    print(row.replace('\n', ''))

# Closes file i/o
# File object is the only provided parameter
def close_file(f):
  f.close()


file_name = input('Please enter a file name to read from: ')
f = open_file(file_name)
read_from_file(f)
close_file(f)