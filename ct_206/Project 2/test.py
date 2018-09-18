import unittest
from project2 import *

class Tests(unittest.TestCase):

    def test_reverse(self):
        strings = ['abc', '123', 'abc123']
        reversed_string = ['abc123', '123', 'abc']
        self.assertEqual(reverse_array(strings), reversed_string)


if __name__ == '__main__':
    unittest.main()