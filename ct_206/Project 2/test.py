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

if __name__ == '__main__':
    unittest.main()