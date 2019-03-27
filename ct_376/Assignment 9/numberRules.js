const isNumberIncreasing = number => {
  if (number < 10) return false;
  let previousNumber = 10,
    currentNumber;
  while (number > 10) {
    currentNumber = number % 10;
    number = Math.floor(number / 10);
    if (currentNumber > previousNumber) return false;
    previousNumber = currentNumber;
  }
  return currentNumber <= previousNumber;
};

const isNumberDecreasing = number => {
  if (number < 10) return false;
  let previousNumber = -1,
    currentNumber;
  while (number > 10) {
    currentNumber = number % 10;
    number = Math.floor(number / 10);
    if (currentNumber < previousNumber) return false;
    previousNumber = currentNumber;
  }
  return currentNumber >= previousNumber;
};

const doesNumberBounce = number => {
  if (number <= 100) return false;
  let previousNumber = -1,
    currentNumber;
  let flipped = (inverse = false);
  while (number > 10) {
    previousNumber = currentNumber || number % 10;
    if (!currentNumber) {
      number = Math.floor(number / 10);
      flipped = previousNumber < number % 10;
    }
    currentNumber = number % 10;
    number = Math.floor(number / 10);
    if (previousNumber === currentNumber) return false;
    else if (inverse) {
      if (flipped && previousNumber < currentNumber) return false;
      else if (!flipped && previousNumber > currentNumber) return false;
    } else {
      if (flipped && previousNumber > currentNumber) return false;
      else if (!flipped && previousNumber < currentNumber) return false;
    }
    inverse = !inverse;
  }
  if (!inverse) {
    if (flipped && previousNumber < currentNumber) return false;
    else if (!flipped && previousNumber > currentNumber) return false;
  } else {
    if (flipped && previousNumber > currentNumber) return false;
    else if (!flipped && previousNumber < currentNumber) return false;
  }
  return true;
};

console.log(doesNumberBounce(51515), doesNumberBounce(921142));
