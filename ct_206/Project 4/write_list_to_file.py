# Opens file list.txt and allows write permissions
# Returns file object
def open_file():
  f = open('list.txt', 'w+', encoding='utf-8')
  return f

# Writes provided list object to list.txt
# File object and list of data are the only parameters
def write_to_file(f, list):
  for item in list:
    f.write(str(item) + '\n')

# Closes file i/o
# File object is the only provided parameter
def close_file(f):
  f.close()


list = ['a', 'b', 'c', 1, 2, 3, 'x4y5z6']
f = open_file()
write_to_file(f, list)
close_file(f)