---
layout: page
title: User Guide
---

PowerCards (PCs) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PCs can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `powercards.jar` from [here]().

3. Copy the file to the folder you want to use as the _home folder_ for your PCs.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar powercards.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add q\QUESTION`, `QUESTION` is a parameter which can be used as `add q\What is chemical symbol for Oxygen?`.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d\science d\chemsitry`, only `d\chemsitry` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------

## Main Mode

Main mode will be started by default when the program is launched. 
Main mode refers to when no deck is selected and the user wishes to manage their list of decks.

### Viewing help : `help`

Shows a message explaning how to access the help page.

[//]: # (![help message]&#40;images/helpMessage.png&#41;)

Format: `help`

### Adding a new Deck : `addDeck`

Adds a new deck.

Format: `addDeck DECK_NAME`

Examples:
* `addDeck Science`

### Editing a Deck : `editDeck`

Edits the name of a deck.

Format: `editDeck INDEX d\ DECK_NAME`

### Deleting a Deck : `deleteDeck`

Deletes an existing Deck in the masterDeck.

Format: `deleteDeck INDEX`

* Deletes the card at the specified `INDEX`. The index refers to the index number shown in the displayed card list. The index **must be a positive integer** 1, 2, 3, …​

### Selecting a Deck : `selectDeck`

Selects a deck from the list of decks. 

Format: `selectDeck INDEX`

Examples:
* `selectDeck 2`

##Main - After selecting a Deck
With a deck selected, you can see all the cards in the deck on the right panel! 
Now you can `addCard`, `deleteCard`, `editCard` or `findCards` in the deck.
You will not be able to make any deck-related changes (e.g. `addDeck`, `deleteDeck`) until you unselect the current deck.

### Unselecting a Deck : `unselectDeck`

Unselects the currently selected deck.

Format: `unselectDeck`

### Adding a Card: `addCard`

Adds a card to the **selected** deck.

Format: `addCard q\QUESTION a\ANSWER [t\TAG]`

<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Tagging is optional and should be of value Easy, Medium, or Hard.
</div> -->

Examples:
* `add q\What is chemical symbol for Oxygen? a\O`
* `add q\What is gravity? a\A force of attraction between objects due to their mass t\Easy`

### Deleting a Card : `deleteCard`

Deletes an existing card from the **selected** Deck. 

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that this is irreversible!
</div>

Format: `deleteCard INDEX`

* Deletes the card at the specified `INDEX`. The card's index can be found in the displayed card list.

Example:
- `deleteCard 2`

### Editing a Card : `editCard`

Edits an existing card in the **selected** Deck.

Format: `editCard INDEX [q\QUESTION] [a\ANSWER] [t\TAG]`

* Edits the card at the specified `INDEX`. The card's index can be found in the displayed card list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `editCard 1 q\What is chemical symbol for Carbon? a\C` Edits the question and answer of the 1st card to be `What is chemical symbol for Carbon?` and `C` respectively.

### Finding Cards by Keywords in Question : `findCards`

Show only cards in the **selected** Deck with questions containing any of the given keyword(s).
Note that the find function does not support partial words.

Format: `findCards KEYWORD...`
- You can include multiple KEYWORDS - as long as a card's question contains at least one keyword, the card will be found
- At least one KEYWORD must be given.

### Showing all Cards : `showCards`

Show all the cards in the **selected** Deck.

Format: `showCards`

### Clearing the data : `clear`

Clears existing decks and cards.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that this is irreversible!
</div>

Format: `clear`

--------------------------------------------------------------------------------------------------------------------

## Review Mode

Once ready, you can enter the Review Mode to test yourself on the cards of a deck. You can also specify the difficulties of the cards of the deck you want to test - e.g. you just want test medium and hard cards only.

In the review mode, you will see:
- On the left panel - updated statistics of the current review (current deck, current card number, number of cards tagged each difficulty) and a navigation guide of the keys.
- On the right panel - the card that is currently under review, which you can flip to reveal the answer and then tag with a given difficulty.

To review a card, you can attempt the question on the card (in your mind or on a paper if you prefer!) before flipping it. 

Flipping a card reveals the answer - based on how close your guess was to the answer or how confident you were when attempting, you can tag the card with a difficulty of easy, medium or hard.

Your goal would be to eventually have all cards in a deck be tagged as easy!

### Setting the Limit of Cards per Review: `setLimit`

Sets an upper limit of the number of cards per review. 
The review deck will be truncated to the card limit in future reviews.

You can set the limit back to 'none' to view all cards in the deck for future reviews.

Format: `setLimit LIMIT_NUM` or `setLimit none`
- LIMIT_NUM is a non-zero positive integer

Examples:
* `setLimit 30`
* `setLimit none`

### Start a Review: `review`
From the Main Mode, run this command to enter the Review Mode!

Format: `review INDEX [-e] [-m] [-h]`

* Reviews the cards from the deck with the specified INDEX. The deck's index can be found in the displayed deck list.
  - `-e` include this flag to test cards tagged as "easy"
  - `-m` include this flag to test cards tagged as "medium"
  - `-h` include this flag to test cards tagged as "hard"
  - Omit any flags to test all cards in the deck

Examples:
* `review 5 -e -h`
* `review 2`

### Ending the Review: `endReview`

Ends the review and returns to the main mode. You can use this when you reach the end of the review deck or at any point during the review.

Format: `endReview`

### Flipping the Card: `p`

Flips the card to reveal the answer.

Format: `p`

### Tagging the Card as Easy: `l`

Tags the current card as easy. This replaces any previous tags.

Format: `l`

### Tagging the Card as Medium: `;`

Tags the current card as medium. This replaces any previous tags.

Format: `;`

### Tagging the Card as Hard: `'`

Tags the current card as hard. This replaces any previous tags.

Format: `'`

### Next Card: `]`

Displays the next card.

Format: `]`

### Previous Card: `[`

Displays the previous card.

Format: `[`

### Exiting the program : `exit`

At any point, run this command to exit the programme.

Format: `exit`
--------------------------------------------------------------------------------------------------------------------

### Saving the data

PCs data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PCs data are saved as a JSON file `[JAR file location]/data/masterdeck.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PCs will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PCs home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

### Main Mode - before selecting a Deck

| Action         | Format, Examples                                             |
|----------------|--------------------------------------------------------------|
| Select Deck    | `selectDeck INDEX` <br /> e.g., `selectDeck 2`               |
| Add Deck       | `addDeck DECK_NAME` <br /> e.g., `addDeck Science`           |
| Edit Deck      | `editDeck INDEX DECK_NAME` <br /> e.g., `editDeck 3 Physics` |
| Delete Deck    | `deleteDeck INDEX`                                           |
| Find Decks     | `findDecks KEYWORDS...`                                      |
| Show All Decks | `showDecks`                                                  |
| Start Review   | `review INDEX`                                               |
| Set Limit      | `setLimit LIMIT_NUM` <br /> e.g., `setLimit 30`              |
| Clear          | `clear`                                                      |
| Help           | `help`                                                       |
| Exit           | `exit`                                                       |

### Main Mode - after selecting a Deck

| Action         | Format, Examples                                                                                                                                 |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| Unselect Deck  | `unselectDeck`                                                                                                                                   |
| Add Card       | `addCard q\QUESTION a\ANSWER [t\TAG]` <br /> e.g., `addCard q\What is gravity? a\A force of attraction between objects due to their mass t\Easy` |
| Edit Card      | `editCard INDEX [q\QUESTION] [a\ANSWER] [t\TAG]` <br /> e.g., `editCard 1 q\What is chemical symbol for Caarbon? a\C t\Hard`                     |
| Delete Card    | `deleteCard INDEX`                                                                                                                               |
| Find Cards     | `findCards KEYWORDS...`                                                                                                                          |
| Show All Cards | `showCards`                                                                                                                                      |
| Start Review   | `review INDEX`                                                                                                                                   |
| Set Limit      | `setLimit LIMIT_NUM` <br /> e.g., `setLimit 30`                                                                                                  |
| Clear          | `clear`                                                                                                                                          |
| Help           | `help`                                                                                                                                           |
| Exit           | `exit`                                                                                                                                           |

### Review mode

| Action        | Format, Examples |
|---------------|------------------|
| End Review    | `endReview`      |
| Flip          | `p`              |
| Previous Card | `[`              |
| Next Card     | `]`              |
| Tag Easy      | `l`              |
| Tag Medium    | `;`              |
| Tag Hard      | `'`              |
| Help          | `help`           |
| Exit          | `exit`           |
