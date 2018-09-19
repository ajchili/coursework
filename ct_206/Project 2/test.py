import unittest
from project2 import *

class Tests(unittest.TestCase):

    def test_reverse(self):
        strings = ['abc', '123', 'abc123']
        reversed_strings = ['abc123', '123', 'abc']
        self.assertEqual(reverse_array(strings), reversed_strings)

    def test_sum_array(self):
        a = [1, 2, 3]
        b = [5, -7]
        self.assertEqual(sum_array([]), 0)
        self.assertEqual(sum_array(a), 6)
        self.assertEqual(sum_array(b), -2)

    def test_arrays_to_dictionary(self):
        keys = ['a', 'b', 'c']
        values = [1, 2, 3]
        dictionary = {'a': 1, 'b': 2, 'c': 3}
        self.assertEqual(arrays_to_dictionary(keys, values), dictionary)
        self.assertEqual(arrays_to_dictionary(keys, values[:-1]), False)
        self.assertEqual(arrays_to_dictionary(keys[:-1], values), False)
        self.assertEqual(arrays_to_dictionary(keys, []), False)
        self.assertEqual(arrays_to_dictionary([], values), False)

if __name__ == '__main__':
    unittest.main()