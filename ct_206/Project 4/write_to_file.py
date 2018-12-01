# Opens file input.txt and allows write permissions
# Returns file object
def open_file():
  f = open('input.txt', 'w+', encoding='utf-8')
  return f

# Writes input from user to input.txt
# File object is the only provided parameter
def write_to_file(f):
  print('Please type what you would like to be output to input.txt.', \
    'To exit the program, type exit.')
  line = ''

  while line != 'exit':
    line = input()
    f.write(line + '\n')

# Closes file i/o
# File object is the only provided parameter
def close_file(f):
  f.close()


f = open_file()
write_to_file(f)
close_file(f)