# Opens file from provided name and allows read permissions
# Returns file object for provided file name parameter
def open_file(file_name):
  f = open(file_name, 'r', encoding='utf-8')
  return f

# Converts provided file object to tuple, then prints each element in turple
# File object is the only provided parameter
def read_from_file(f, lines_to_skip):
  data = tuple(f)
  for i, row in enumerate(data):
    if i >= int(lines_to_skip):
      print(row.replace('\n', ''))

# Closes file i/o
# File object is the only provided parameter
def close_file(f):
  f.close()


file_name = input('Please enter a file name to read from: ')
lines_to_skip = input('Please enter the number of lines to skip before showing output: ')
f = open_file(file_name)
read_from_file(f, lines_to_skip)
close_file(f)