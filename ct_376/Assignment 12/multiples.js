/**
  * Gets all multiples of specified numbers in range then returns their
  * sum.
  *
  * @param {Array<number>} inputs - array of numbers that should be used
  *                        when checking for multiples
  * @param {number} to - number that specifies range in which multiples
  *                 should be checked for (i.e. 10 would be from 1 to 10)
  * @returns {number} sum of all numbers that are factors of provided
  *                   inputs array within range specified by to
  */
const multipleSums = (inputs, to) => {
	if (!inputs.length) throw new Error("No inputs provided!");
	else if (to <= 0) throw new Error("Second parameter must be greater than 0!");
	let multiples = [];
	for (let i = 1; i <= to; i++) {
		if (inputs.filter(input => i % input === 0).length) {
			multiples.push(i);
		}
	}
	return sum(multiples);
};

/**
  * Sums all numbers in provided array.
  *
  * @param {Array<number>} array - array of numbers
  * @returns {number} sum of numbers in provided array
const sum = array => {
	let sum = 0;
	array.forEach(e => sum += e);
	return sum;
};

console.log(multipleSums([3, 5], 1000));
