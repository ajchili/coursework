memo = [0, 1, 1]

def fib(index):
    pos = len(memo) - 1
    while pos < index:
        memo.append(memo[pos] + memo[pos - 1])
        pos += 1

if __name__ == "__main__":
    while True:
        response = input("Would you like to get a number in the fibonacci sequence? (Y/n) ")
        if response.lower() == "n":
            break
        
        index = input("What index of the fibonacci sequence would you like to know? ")
        index = int(index)
        if index < 0:
            print("The provided number must be greater than or equal to 0!")
            continue
        elif index > 2:
            fib(index)

        print("The number at index {} is {}.".format(index, memo[index]))
