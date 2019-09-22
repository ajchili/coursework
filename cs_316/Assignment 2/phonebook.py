class Phonebook():

    def __init__(self, size=5):
        self.book = {}
        self.size = size

    def store_item(self, name, number):
        if len(self.book.items()) >= self.size:
            print("Unable to add \"{}\" to phonebook, maximum size reached!".format(name))
        else:
            self.book.update({name: number})

    def search(self, name):
        try:
            print(self.book[name])
        except KeyError:
            print("Unable to find \"{}\" in the phonebook!".format(name))


phonebook = Phonebook(2)
phonebook.store_item("Test User", "123-456-7890")
phonebook.search("Test User")
phonebook.search("Unknown User")
phonebook.store_item("Bob Bobberson", "555-555-1212")
phonebook.store_item("Last Person", "098-765-4321")
phonebook.search("Bob Bobberson")