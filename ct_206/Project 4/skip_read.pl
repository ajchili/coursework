# Obtain file to open
print "Please enter a file to open: ";
my $fileName = <STDIN>;
chomp $fileName;

# Obtain number of lines to skip
print "Please enter the number of lines you would like to skip: ";
my $linesToSkip = <STDIN>;
chomp $linesToSkip;

# Open file to read
open(f, "<$fileName") or die "Could not open file!";

# Print file data
while(<f>) {
  if ($linesToSkip > 0) {
    $linesToSkip--;
  } else {
    print $_;
  }
}

close(f1);