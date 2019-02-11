/**
 * Determines whether provided number is a palindrome.
 * 
 * @param {Number} number Number to be checked
 * @returns {Boolean}     Whether provided number is a palindrome
 */
const isPalindrome = number => {
  let copy = number;
  let reversedNumber = 0;
  while (copy > 0) {
    let x = copy % 10;
    copy = Math.floor(copy / 10);
    reversedNumber *= 10;
    reversedNumber += x;
  }
  return number === reversedNumber;
};

/**
 * Iterates through all three digit whole numbers to find
 * the largest palindrome made from the product of two three
 * digit whole numbers.
 */
const checkNumbers = () => {
  let returned = {l: 0};
  for (let i = 100; i <= 999; i++) {
    for (let j = i; j <= 999; j++) {
      const number = i * j;
      if (number > returned.l && isPalindrome(number)) {
        returned = {i, j, l: number};
      }
    }
  }
  return returned;
};

console.log(checkNumbers());