import re


string = "If you do much work on computers, eventually you find that there's some task you'd like to automate. For example, you may wish to perform a search-and-replace over a large number of text files, or rename and rearrange a bunch of photo files in a complicated way. Perhaps you'd like to write a small custom database, or a specialized GUI application, or a simple game.If you're a professional software developer, you may have to work with several C/C++/Java libraries but find the usual write/compile/test/re-compile cycle is too slow. Perhaps you're writing a test suite for such a library and find writing the testing code a tedious task. Or maybe you've written a program that could use an extension language, and you don't want to design and implement a whole new language for your application.You could write a Unix shell script or Windows batch files for some of these tasks, but shell scripts are best at moving around files and changing text data, not well-suited for GUI applications or games. You could write a C/C++/Java program, but it can take a lot of development time to get even a first-draft program. Python is simpler to use, available on Windows, Mac OS X, and Unix operating systems, and will help you get the job done more quickly.Python is simple to use, but it is a real programming language, offering much more structure and support for large programs than shell scripts or batch files can offer. On the other hand, Python also offers much more error checking than C, and, being a very-high-level language, it has high-level data types built in, such as flexible arrays and dictionaries. Because of its more general data types Python is applicable to a much larger problem domain than Awk or even Perl, yet many things are at least as easy in Python as in those languages.Python allows you to split your program into modules that can be reused in other Python programs. It comes with a large collection of standard modules that you can use as the basis of your programs - or as examples to start learning to program in Python. Some of these modules provide things like file I/O, system calls, sockets, and even interfaces to graphical user interface toolkits like Tk.Python is an interpreted language, which can save you considerable time during program development because no compilation and linking is necessary. The interpreter can be used interactively, which makes it easy to experiment with features of the language, to write throw-away programs, or to test functions during bottom-up program development. It is also a handy desk calculator."

# Sorts provided array lexically
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


# Returns all words as a list from provided string
def words(string):
  # removes all punctuation from string variable
  noPunctuation = re.sub(r'[^\w\s]', ' ', string)
  # splits noPunctuation by any spaces and lower cases all
  words = [word.lower() for word in noPunctuation.split(' ')]
  # removes all blank strings
  filteredWords = filter(lambda word: len(re.sub(r'[ ]', '', word)) > 0, words)

  return filteredWords

words = sortArray(words(string))
print(words)