const numeral = "XIIIIIXIV";

getNumeralCount = numeral => {
  let numerals = {};
  for (let i = 0; i < numeral.length; i++) {
    let letter = numeral[i];
    if (numerals[letter]) numerals[letter]++;
    else numerals[letter] = 1;
  }
  return numerals;
};

convertNumeralCountToNumber = numerals => {
  return (
    (numerals["I"] || 0) +
    (numerals["V"] || 0) * 5 +
    (numerals["X"] || 0) * 10 +
    (numerals["L"] || 0) * 50 +
    (numerals["C"] || 0) * 100 +
    (numerals["D"] || 0) * 500 +
    (numerals["M"] || 0) * 1000
  );
};

convertNumberToNumeral = number => {
  let numeral = "";
  while (number >= 1000) {
    number -= 1000;
    numeral += "M";
  }
  if (number >= 900) {
    number -= 900;
    numeral += "CM";
  } else if (number >= 500) {
    number -= 500;
    numeral += "D";
  } else if (number >= 400) {
    number -= 400;
    numeral += "CD";
  }
  while (number >= 100) {
    number -= 100;
    numeral += "C";
  }
  if (number >= 90) {
    number -= 90;
    numeral += "XC";
  } else if (number >= 50) {
    number -= 50;
    numeral += "L";
  } else if (number >= 40) {
    number -= 40;
    numeral += "XL";
  }
  while (number >= 10) {
    number -= 10;
    numeral += "X";
  }
  if (number >= 5) {
    number -= 5;
    numeral += "V";
  } else if (number === 4) {
    number -= 5;
    numeral += "IV";
  }
  while (number > 0) {
    number--;
    numeral += "I";
  }
  return numeral;
};

console.log(
  convertNumberToNumeral(convertNumeralCountToNumber(getNumeralCount(numeral)))
);
