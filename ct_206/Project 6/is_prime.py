import is_prime as prime

# Returns a boolean specifying the provided number is prime
def is_prime(num):
  # 1 is not considered prime due to its lack of exactly
  # two positive divisors
  if num is 1:
    return False

  # Iterates through each number between 2 and half of the
  # number specified.
  for i in range(2, num // 2):
    # Ensures that i at any number within the range of 2
    # and half of the number specified is not a factor of
    # the specified number.
    if not num % i:
      return False

  # Returns true if number is not one and no factors are
  # found within the for loop
  return True


print("is 1 prime?", is_prime(1), prime.is_prime(1))
print("is 2 prime?", is_prime(2), prime.is_prime(2))
print("is 25 prime?", is_prime(25), prime.is_prime(25))
print("is 100 prime?", is_prime(100), prime.is_prime(100))
print("is 13 prime?", is_prime(13), prime.is_prime(13))