import pickle

data = {'a': 'testing', 'b': 'dictionary', 'c': 'to', 'd': 'pickle'}
pickle.dump(data, open('pickle.p', 'wb'))