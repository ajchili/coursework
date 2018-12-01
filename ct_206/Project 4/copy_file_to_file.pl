# Open file to read
open(f1, "<input.txt") or die "Could not open file!";

# Open new file to write
open(f2, ">output.txt") or die "Could not open file!";

# Copy data from one file to another.
while(<f1>) {
  print f2 $_;
}

close(f1);
close(f2);
