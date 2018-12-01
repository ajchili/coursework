@list = ('perl', 'b', 'c', 1, 2, 3, 'x4y5z6');

# Open new file to write
open(f1, ">list.txt") or die "Could not open file!";

# Write data from list to file
for (my $i = 0; $i < @list; $i += 1) {
  print f1 @list[$i];
  print f1 "\n";
}

close(f1);