let words = {
  ones: [
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
  ],
  tens: [
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen"
  ],
  otherTens: [
    "twenty",
    "thirty",
    "fourty",
    "fifty",
    "sixty",
    "seventy",
    "eighty",
    "ninety"
  ],
  hundred: "hundred",
  thousand: "thousand"
};

const numberToWord = number => {
  let word = "";
  if (number >= 1000) {
    let thousands = 0;
    while (number >= 1000) {
      number -= 1000;
      thousands++;
    }
    word += `${words.ones[thousands]} ${words.thousand}`;
  }
  if (number >= 100) {
    let hundreds = 0;
    while (number >= 100) {
      number -= 100;
      hundreds++;
    }
    word += `${word.length ? " and " : ""}${words.ones[hundreds]} ${
      words.hundred
    }`;
  }
  if (number >= 20) {
    let otherTens = 0;
    let ones = 0;
    while (number % 10 !== 0) {
      number--;
      ones++;
    }
    while (number > 20) {
      number -= 10;
      otherTens++;
    }
    ones = ones > 0 ? `-${words.ones[ones]}` : "";
    word += `${word.length ? " and " : ""}${words.otherTens[otherTens]}${ones}`;
  } else if (number < 20 && number >= 10) {
    word += `${word.length ? " and " : ""}${words.tens[number % 10]}`;
    number = 0;
  } else if (number >= 1) {
    let ones = 0;
    while (number >= 1) {
      number--;
      ones++;
    }
    word += `${word.length ? " and " : ""}${words.ones[ones]}`;
  }
  return word;
};

const countLetters = words => {
  let count = 0;
  words.forEach(word => {
    let compressedWord = word.replace(/[\s-]/g, "");
    count += compressedWord.length;
  });
  return count;
};

let numbers = [];
for (let i = 1; i <= 1000; i++) {
  numbers.push(numberToWord(i));
}
console.log(countLetters(numbers));
