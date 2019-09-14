# Create empty list to hold file contents
stuff = []

with open("stuff.txt", "r") as f:
	for line in f.readlines():
		# Add contents of file, line by line to list
		stuff.append(line)

# Remove first and last element from list
stuff = stuff[1:-1]
# Reverse list
stuff.reverse()

# Save list in original file
with open("stuff.txt", "w") as f:
	f.write("".join(stuff))
