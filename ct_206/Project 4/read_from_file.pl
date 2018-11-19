# Open file to read
open(f, "<input.txt") or die "Could not open file!";

# Print file data
while(<f>) {
  print $_;
}

close(f1);
