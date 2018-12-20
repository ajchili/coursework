from enum import Enum
import random


class Mood(Enum):
  HAPPY = 'happy'
  SAD = 'sad'
  MAD = 'mad'


class Character:
  def __init__(self, name, color, mood, size):
    self.name = name
    self.color = color
    self.mood = mood
    self.size = max([0, min([300, size])])
    self.health = 100
    self.quotes = []

  def set_name(self, name):
    self.name = name
  
  def set_color(self, color):
    self.color = color

  def change_mood(self):
    self.mood = Mood(random.randint(1, 3))

  def set_size(self, size):
    self.size = max([0, min([300, size])])

  def eat(self):
    health = self.health + 5
    self.health = max([0, min([100, health])])

  def add_quote(self, quote):
    self.quotes.append(quote)

  def talk(self):
    print(self.quotes[random.randint(0, len(self.quotes) - 1)])

character1 = Character("Zane", "red", Mood.HAPPY, 200)
print("Hi, I am %s. I am %s with %i health and %i size." % (character1.name, character1.mood.value, character1.health, character1.size))
character2 = Character("Zane", "black", Mood.SAD, 100)
print("Hi, I am %s. I am %s with %i health and %i size." % (character2.name, character2.mood.value, character2.health, character2.size))

character1.add_quote("Testing quote")
character1.add_quote("Hello, World!")
character1.add_quote("123abc")
character1.add_quote("words. words. words")
character2.add_quote("Hello, World!")
character2.add_quote("words. words. words")
character2.add_quote("Testing quote")
character2.add_quote("123abc")
print(character1.quotes)
print(character2.quotes)

character1.talk()
character2.talk()
character1.talk()
character2.talk()