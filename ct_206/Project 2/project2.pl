sub reverseArray {
    # Initializes a new array.
    my @outputArray = ();

    # Iterates through the elements of the array. Adding them in
    # reverse order to the newly created array.
    for (my $i = 0; $i < @inputArray; $i += 1) {
        @outputArray[$i] = @inputArray[@inputArray - $i - 1];
    }

    # Returns the reversed array.
    return @outputArray;
}

sub sumArray {
    # Initializes a new variable to store the sum.
    my $sum = 0;

    # Iterates through the elements of the array. Adding their
    # values to the sum variable.
    for (my $i = 0; $i < @inputArray; $i += 1) {
        $sum += @inputArray[$i];
    }

    # Returns the sum of the elements of the array.
    return $sum;
}

# Tests
@inputArray = (1, 2, 3);
@a = reverseArray(@inputArray);
print @a[0] == @inputArray[2];
print "\n";
print @a[1] == @inputArray[1];
print "\n";
print @a[2] == @inputArray[0];
print "\n\n";
print sumArray(@inputArray);
print "\n";