# Farkle
The dice game Farkle

Here is a description *based on the rules from http://farkle.games/official-rules/
*Currently only a simple rule set is implemented and is reflected below. 

Farkle is a game of chance and strategy where two or more players roll dice which earn points. The goal is to get to 4,000 points first.

1. Each player starts their turn by rolling six dice.
2. After rolling, the player sets aside specific dice combinations which have a score value.
3. The player can either bank the points earned that turn to their total points and pass the dice to the next player or risk the points earned that turn and roll the ramaining dice again, hoping to earn more points.
4. If the remaining dice rolled do not have a scoring combination, then the player has “FARKLED” and points earned that turn are gone foreever.
5. (TODO) If the player has successfully with some luck and strategy used all six dice to score, then the player gets to roll all six dice again for a chance to earn more points. This “hot dice” move can be repeated over and over.
6. A user must click "Stop & score" to end a turn. Points are then determined and the opponent turn starts. 

Scoring:
- Only 1s and 5s award points indiviually. A 1 is worth 100 points and a 5 is worth 50 points.
- Three of a kind: Any three dice with the same number value is worth 100 times the number shared by all 3 dice. For example three dice with the number “4” face up is worth 400 points. 
- Three 1s: Instead of three “1”s being worth 100 points as described above, it is instead worth 1,000 points.


