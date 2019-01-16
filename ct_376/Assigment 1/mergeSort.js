let arr = [];
const arrSize = 10;

// Fill array with random numbers
for (let i = 0; i < arrSize; i++) {
	arr.push(Math.round(Math.random() * 100));
}

/**
 * Sorts provided array with the merge sort algorithm.
 * Does not occur in place.
 *
 * @arr {Array}     Array to be sorted
 * @returns {Array} Sorted array
 */
const mergeSort = arr => {
	// Return if array lenth is one, array no longer needs to be split
	if (arr.length === 1) return arr;
	// Get middle of array
	const middle = Math.floor(arr.length / 2);
	// Get first half of array
	let a = mergeSort(arr.slice(0, middle));
	// Get second half of array
	let b = mergeSort(arr.slice(middle));
	// Create new array
	let newArr = [];
	// Sort a and b in newArr obeject, ending when either a and/or b is empty
	while (a.length && b.length) {
		// Add first element of b to newArr and remove it from b
		if (a[0] > b[0]) newArr.push(b.shift());
		// Add first element of a to newArr and remove it from a
		else newArr.push(a.shift());
	}
	// Return sorted array, adding either all leftover elements from a and b to
	// the end of newArr. Should there be any leftover elements in either a or
	// b, they will already be sorted by previous recursive executions, so
	// they can just be added directly to the end of newArr.
	return newArr.concat(a, b);
};

console.log(mergeSort(arr));