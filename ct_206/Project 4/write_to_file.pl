# Open new file to write
open(f1, ">input.txt") or die "Could not open file!";

print "Please type what you would like to be output to input.txt. To exit the program, type exit.\n";
my $line = '';
while ($line ne 'exit') {
  $line = <STDIN>;
  chomp $line;
  print f1 $line;
  print f1 "\n";
}

close(f1);
