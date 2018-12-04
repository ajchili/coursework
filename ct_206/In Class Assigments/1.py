import math
import matplotlib.pyplot as plt
import numpy as np
import random
	

plt.figure(figsize=(250,250))
fig,ax=plt.subplots()
	
rolls = []

while (raw_input("Would you like to generate 100 points of data (y/n): ") is not "n"):
  for i in range(100):
    rolls.append(math.floor(random.uniform(1, 7)))

avg = []
for i, x in enumerate(rolls):
  if i is 0:
    avg.append(x)
  else:
    avg.append(((avg[i - 1] * len(avg)) + x) / (len(avg) + 1))

plt.plot(avg)
plt.plot(np.full((1, len(avg)), 3.5)[0], 'r')
plt.xlabel('Number of Rolls')
plt.ylabel('Side of Dice')
plt.title('Average Side of Dice')
plt.show()

# Currently the histogram is providing issues, it does not seem to work as expected
# hist = plt.hist(rolls, density=1, bins=6)
# hist.axis([1, 2, 3, 4, 5, 6])
# hist.ylabel('Number of times dice landed on side')
# hist.show()