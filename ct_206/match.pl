$_ = "aabababa";
if ($_ =~ m/^a(ab)*a$/) {
  print "Matches.\n";
} else {
  print "Does not match.\n";
}