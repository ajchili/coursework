const binarySearch = (set, number) => {
  if (set.length > 1) {
    let center = Math.floor(set.length / 2);
    let numberAtCenter = set[center];
    if (numberAtCenter < number) {
      return binarySearch(set.slice(center), number);
    } else if (numberAtCenter > number) {
      return binarySearch(set.slice(0, center), number);
    } else if (numberAtCenter === number) return center;
  } else return -1;
}

console.log(binarySearch([1, 4, 5, 7, 20, 100, 349], 8));