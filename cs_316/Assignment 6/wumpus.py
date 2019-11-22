import os
import sys
sys.path.append("/Users/kirinpatel/github/coursework/cs_316/aima-python")
from logic import *

wumpus_kb = PropKB()
"""
-------------------------------------------------------------------------
|       |   S   |   W?  |   P?  |   B   |       |       |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|       |   W?  |   S   |   P?  |   B   |       |       |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|   B   |       |       |   B   |       |       |       |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|   P?  |   B   |       |       |       |       |   B   |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|   B   |   B   |       |       | start |   B   |   P?  |   B   |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|   B   |   P?  |   B   |       |       |       |   B   |       |       |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|       |   B   |       |       |       |       |       |       |   B   |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|       |       |       |       |   B   |       |       |   B   |   P?  |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|       |       |       |   B   |   P?  |   B   |       |   P?  |   ?   |
-------------------------------------------------------------------------
"""
S11, S12, S13, S14, S15, S16, S17, S18, S19, S21, S22, S23, S24, S25, S26, S27, S28, S29, S31, S32, S33, S34, S35, S36, S37, S38, S39, S41, S42, S43, S44, S45, S46, S47, S48, S49, S51, S52, S53, S54, S55, S56, S57, S58, S59, S61, S62, S13, S64, S65, S66, S67, S68, S69, S71, S72, S73, S74, S75, S76, S77, S78, S79, S81, S82, S83, S84, S85, S86, S87, S88, S89, S91, S92, S93, S94, S95, S96, S97, S98, S99, W13, W22, P14, P24, P41, P57, P62, P89, P95, P98, B15, B25, B31, B34, B42, B47, B51, B52, B56, B58, B61, B63, B67, B72, B79, B85, B88, B94, B96 = expr(
    "S11, S12, S13, S14, S15, S16, S17, S18, S19, S21, S22, S23, S24, S25, S26, S27, S28, S29, S31, S32, S33, S34, S35, S36, S37, S38, S39, S41, S42, S43, S44, S45, S46, S47, S48, S49, S51, S52, S53, S54, S55, S56, S57, S58, S59, S61, S62, S13, S64, S65, S66, S67, S68, S69, S71, S72, S73, S74, S75, S76, S77, S78, S79, S81, S82, S83, S84, S85, S86, S87, S88, S89, S91, S92, S93, S94, S95, S96, S97, S98, S99, W13, W22, P14, P24, P41, P57, P62, P89, P95, P98, B15, B25, B31, B34, B42, B47, B51, B52, B56, B58, B61, B63, B67, B72, B79, B85, B88, B94, B96")
# Row 1
wumpus_kb.tell(S11)
wumpus_kb.tell(~S12)
wumpus_kb.tell(~W13)
wumpus_kb.tell(~P14)
wumpus_kb.tell(~B15)
wumpus_kb.tell(S16)
wumpus_kb.tell(S17)
wumpus_kb.tell(S18)
wumpus_kb.tell(S19)
# Row 2
wumpus_kb.tell(S21)
wumpus_kb.tell(~W22)
wumpus_kb.tell(~S23)
wumpus_kb.tell(~P24)
wumpus_kb.tell(~B25)
wumpus_kb.tell(S26)
wumpus_kb.tell(S27)
wumpus_kb.tell(S28)
wumpus_kb.tell(S29)
# Row 3
wumpus_kb.tell(~B31)
wumpus_kb.tell(S32)
wumpus_kb.tell(S33)
wumpus_kb.tell(~B34)
wumpus_kb.tell(S35)
wumpus_kb.tell(S36)
wumpus_kb.tell(S37)
wumpus_kb.tell(S38)
wumpus_kb.tell(S39)
# Row 4
wumpus_kb.tell(~P41)
wumpus_kb.tell(~B42)
wumpus_kb.tell(S43)
wumpus_kb.tell(S44)
wumpus_kb.tell(S45)
wumpus_kb.tell(S46)
wumpus_kb.tell(~B47)
wumpus_kb.tell(S48)
wumpus_kb.tell(S49)
# Row 5
wumpus_kb.tell(~B51)
wumpus_kb.tell(~B52)
wumpus_kb.tell(S53)
wumpus_kb.tell(S54)
wumpus_kb.tell(S55)
wumpus_kb.tell(~B56)
wumpus_kb.tell(~P57)
wumpus_kb.tell(~B58)
wumpus_kb.tell(S59)
# Row 6
wumpus_kb.tell(~B61)
wumpus_kb.tell(~P62)
wumpus_kb.tell(~B63)
wumpus_kb.tell(S64)
wumpus_kb.tell(S65)
wumpus_kb.tell(S66)
wumpus_kb.tell(~B67)
wumpus_kb.tell(S68)
wumpus_kb.tell(S69)
# Row 7
wumpus_kb.tell(S71)
wumpus_kb.tell(~B72)
wumpus_kb.tell(S73)
wumpus_kb.tell(S74)
wumpus_kb.tell(S75)
wumpus_kb.tell(S76)
wumpus_kb.tell(S77)
wumpus_kb.tell(S78)
wumpus_kb.tell(S79)
# Row 8
wumpus_kb.tell(S81)
wumpus_kb.tell(S82)
wumpus_kb.tell(S83)
wumpus_kb.tell(S84)
wumpus_kb.tell(~B85)
wumpus_kb.tell(S86)
wumpus_kb.tell(S87)
wumpus_kb.tell(~B88)
wumpus_kb.tell(~P89)
# Row 9
wumpus_kb.tell(S91)
wumpus_kb.tell(S92)
wumpus_kb.tell(S93)
wumpus_kb.tell(~B94)
wumpus_kb.tell(~P95)
wumpus_kb.tell(~B96)
wumpus_kb.tell(S97)
wumpus_kb.tell(~P98)
wumpus_kb.tell(S99)
# Knowledgebase
wumpus_kb.tell(S12 | "<=>" | (W13 | W22))
wumpus_kb.tell(S23 | "<=>" | (W13 | W22))
wumpus_kb.tell(B15 | "<=>" | P14)
wumpus_kb.tell(B25 | "<=>" | P24)
wumpus_kb.tell(B31 | "<=>" | P41)
wumpus_kb.tell(B34 | "<=>" | P24)
wumpus_kb.tell(B42 | "<=>" | P41)
wumpus_kb.tell(B47 | "<=>" | P57)
wumpus_kb.tell(B51 | "<=>" | P41)
wumpus_kb.tell(B52 | "<=>" | P62)
wumpus_kb.tell(B56 | "<=>" | P57)
wumpus_kb.tell(B58 | "<=>" | P57)
wumpus_kb.tell(B61 | "<=>" | P62)
wumpus_kb.tell(B63 | "<=>" | P62)
wumpus_kb.tell(B67 | "<=>" | P57)
wumpus_kb.tell(B72 | "<=>" | P62)
wumpus_kb.tell(B79 | "<=>" | P89)
wumpus_kb.tell(B85 | "<=>" | P95)
wumpus_kb.tell(B88 | "<=>" | P98)
wumpus_kb.tell(B94 | "<=>" | P95)
wumpus_kb.tell(B96 | "<=>" | P95)

# Hypothesis
# Based on the map and without traversal, Wumpus will be in W13 because there
# is not a mapped stench at S21 or S32 around W22.

print(wumpus_kb.ask_if_true(W13)) # Outputs False, expected to be True
print(wumpus_kb.ask_if_true(W22)) # Outputs False

# Assertions
# - There is likely a pit at P14 since there is a breeze at B15 and since
#   wumpus is likely at W13.
# - There is likely a pit at P24 since there is a breeze at B25 and B34 but it
#   cannot be confirmed since there is a stench at S23.
# - P41 is a pit since all surrounding locations have a breeze.
# - P57 is a pit since all surrounding locations have a breeze.
# - P62 is a pit since all surrounding locations have a breeze.
# - P95 is a pit since all surrounding locations have a breeze.
# - P89 is a pit since all surrounding locations have a breeze (excluding S99).
# - There is no pit at P98 since there is no breeze at S97.
