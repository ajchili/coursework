// Gregory-Leibniz Series
const calculatePiGL = iterations => {
  if (iterations < 0) throw new Error("A positive integer must be provided!");
  let pi = 1;
  for (let i = 1; i < iterations; i++) {
    if (i % 2) pi -= 1 / (i * 2 + 1);
    else pi += 1 / (i * 2 + 1);
  }
  return pi * 4;
};

// Nilakantha Series
const calculatePiN = iterations => {
  if (iterations < 0) throw new Error("A positive integer must be provided!");
  let pi = 3;
  for (let i = 1; i < iterations; i++) {
    let number = i * 2 + 1;
    if (i % 2) pi += 4 / ((number - 1) * number * (number + 1));
    else pi -= 4 / ((number - 1) * number * (number + 1));
  }
  return pi;
};

let iterations = 1000;
console.log(calculatePiGL(iterations), calculatePiN(iterations));
