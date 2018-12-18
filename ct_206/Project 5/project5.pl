sub sortArray {
    # Initializes a new array.
    my @outputArray = ();

    # Iterates through the elements of the array and compares
    # them to the sorted elements, arranging the provided array
    # in a sorted array.
    foreach my $word (@words) {
      $added = 0;
      $index = 0;
      foreach my $_word (@outputArray) {
        # Adds word at current index if it is lexically greater
        # than or equal to the word at the current index
        if ($word cmp $_word < 1) {
          $added = 1;
          splice(@outputArray, $index, 0, $word);
          last;
        }
        $index++;
      }

      # Adds word to beginning of array if it is less than all
      # words currently in array
      if ($added == 0) {
        unshift @outputArray, $word;
      }
    }

    # Returns the sorted array.
    return @outputArray;
}

sub sortNumArray {
    # Initializes a new array.
    my @outputArray = ();

    # Iterates through the elements of the array and compares
    # them to the sorted elements, arranging the provided array
    # in a sorted array.
    foreach my $number (@numbers) {
      $added = 0;
      $index = 0;
      foreach my $_number (@outputArray) {
        # Adds number at current index if it is less than or
        # equal to the number at the current index
        if ($number <= $_number) {
          $added = 1;
          splice(@outputArray, $index, 0, $number);
          last;
        }
        $index++;
      }

      # Adds number to end of array if it is not less than or
      # equal to any of the numbers currently in the array
      if ($added == 0) {
        push @outputArray, $number;
      }
    }

    # Returns the sorted array.
    return @outputArray;
}

sub sortColorArray {
    # Initializes a new array.
    my @outputArray = ();

    # Iterates through the elements of the array and compares
    # them to the sorted elements, arranging the provided array
    # in a sorted array.
    foreach my $color (@colors) {
      $added = 0;
      $index = 0;
      foreach my $_color (@outputArray) {
        # Adds color at current index if its second character is
        # lexically greater than or equal to second character of
        # word at current index
        if (substr($color, 1, 1) <=> substr($_color, 1, 1) < 1) {
          $added = 1;
          splice(@outputArray, $index, 0, $color);
          last;
        }
        $index++;
      }

      # Adds color at beginning of array if its second character
      # is lexically less than all others in array
      if ($added == 0) {
        unshift @outputArray, $color;
      }
    }

    # Returns the sorted array.
    return @outputArray;
}

# Base string
$string = "jkas djw kal skdl hu";

# Split string 3 times
@newList = (
  substr($string, 0, 5),
  substr($string, 6, 7),
  substr($string, 13, 6)
);
print "@newList\n";

# Create list from base string, splitting on spaces
# Create string that capitalizes each word in base string
$capitalizedString = "";
@list = ();
foreach my $word (split(" ", $string)) {
  push @list, $word;
  $capitalizedString .= ucfirst($word .= " ");
}
print "$capitalizedString\n";
print "@list\n";

# Replaces spaces with hyphens in base string
$hyphenatedWord = $string;
$hyphenatedWord =~ s/\ /-/g;
print "$hyphenatedWord\n";

# Replaces hyphens with semi-colons (breaks string into list first)
$delimiterWord = "";
foreach my $word (split("-", $hyphenatedWord)) {
  $delimiterWord .= $word .= ";";
}
$delimiterWord =~ s/;$//g;
print "$delimiterWord\n";

# Sorts words 
@words = ("testing", "word", "mound", "cabbage", "spike");
@sortedArray = sortArray(@words);
print "@sortedArray\n";

# Sorts numbers
@numbers = (24, 598, 1, 800, 15);
@sortedNumbers = sortNumArray(@numbers);
print "@sortedNumbers\n";

# Sorts colors
@colors = ("red", "blue", "purple", "green", "orange");
@sortedColors = sortColorArray(@colors);
print "@sortedColors\n";