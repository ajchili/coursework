/**
 * Returns string at Fibbonacci sequence at provided index. The
 * string is determined by the following algorithm, "for any two
 * strings of digits, A and B, we define F(A, B) to be the
 * sequence (A, B, AB, BAB, ABBAB, ...) in which each term is
 * the concatenation of the previous two." More information about
 * this can be found on project euler.
 *
 * {@link https://projecteuler.net/problem=230}
 * @param {Number}   n - Index of Fibbonacci sequence to obtain
 * @returns {String}     String at index @n within Fibbonacci sequence
 */
fibonacciWords = n => {
  const fibonacci = ["A", "B"];
  if (n > 0 && n < 2) return fibonacci[n - 1];
  else if (n > 0) {
    for (let i = 2; i < n; i++) {
      fibonacci.push(fibonacci[i - 2].concat(fibonacci[i - 1]));
    }
    return fibonacci[n - 1];
  } else throw new Error("Invalid number range provided.");
};

for (let i = 1; i < 10; i++) {
  console.log(i, fibonacciWords(i));
}
