# Create a hash with 5 values
my %hash = (
  "a" => 1,
  "b" => 2,
  "c" => 3,
  "d" => 4,
  "e" => 5
);

print "@{[%hash]}\n";

# Add a new key/value pair to the dictionary
$hash{"f"} = 6;

print "@{[%hash]}\n";

#  Change on of the values through reassignment
$hash{"a"} = 0;

print "@{[%hash]}\n";

# Access at least 3 values using the keys
print "Accessing via key: $hash{\"a\"} $hash{\"b\"} $hash{\"c\"}\n";

# Access at least 3 keys using the values
my %rhash = reverse %hash;
print "Accessing via value: $rhash{0} $rhash{2} $rhash{3}\n";

# Delete an element
delete $hash{"a"};

print "@{[%hash]}\n";

# Test for key inclusion for 3 elements
if (exists $hash{"b"} && exists $hash{"c"} && exists $hash{"d"}) {
  print "True\n";
} else {
  print "False\n";
}

# Loop through the dictionary, printing out
# the key and value on the same line separated
# by a space.
while ((my $key, $value) = each %hash) {
  print "$key, $value\n";
}