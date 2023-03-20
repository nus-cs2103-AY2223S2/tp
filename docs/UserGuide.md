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

1. Download the latest `powercards.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your PCs.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar powercards.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add q/QUESTION`, `QUESTION` is a parameter which can be used as `add q/What is chemical symbol for Oxygen?`.

* Items in square brackets are optional.<br>
  e.g `q/QUESTION [t/TAG]` can be used as `q/What is chemical symbol for Oxygen? t/chemistry` or as `q/What is chemical symbol for Oxygen?`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/chemistry`, `t/chemistry t/science` etc.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------

## Main Mode

Main mode will be started by default when the program is launched.

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a new Deck : `addDeck`

Adds a new masterDeck.

Format: `addDeck DECKNAME`

Examples:
* `addDeck Science`

### Editing a Deck : `editDeck`

Edits the name of a masterDeck.

Format: `editDeck d\DECKNAME`

### Listing all Decks : `list`

_**No Decks should not be selected to use this command.**_

Shows a list of all Decks.

Format: `list`

### Selecting a Deck : `selectDeck`

Selects a masterDeck. 

Format: `selectDeck INDEX`

Examples:
* `selectDeck 2`

### Unselecting a Deck : `unselectDeck`

Unselects the current masterDeck.

Format: `unselectDeck`

Examples:
* `unselectDeck`


### Adding a Powercard: `add`

_**User must select a masterDeck to use this command.**_

Adds a Powercard to the **selected** Deck.

Format: `add q\QUESTION a\ANSWER t\TAG`

<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A card can have any number of tags (including 0)
</div> -->

Examples:
* `add q\What is chemical symbol for Oxygen? a\O`
* `add q\What is gravity? a\A force of attraction between objects due to their mass t\Easy`

### Listing all Powercards : `list`

_**User must select a masterDeck to use this command.**_

Shows a list of all Powercard in the **selected** Deck.

Format: `list`

### Editing a Powercard : `edit`

_**User must select a masterDeck to use this command.**_

Edits an existing Powercard in a Deck. 

Format: `edit INDEX q\QUESTION a\ANSWER`

* Edits the card at the specified `INDEX`. The index refers to the index number shown in the displayed card list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 q\What is chemical symbol for Caarbon? a\C` Edits the question and answer of the 1st Powercard to be `What is chemical symbol for Oxygen?` and `C` respectively.

### Reviewing a masterDeck: `review`

_**User must select a masterDeck to use this command.**_

Begins reviewing the selected masterDeck.

Format: `review INDEX`

Examples:
* `review 2`

### Setting the number of cards per review: `setNumCardsPerReview`

Sets the number of cards per review to a non-zero positive integer. After setting, only the entered number of cards will appear in future reviews.
Setting it to none sets back to view all cards in future reviews.

Format: `setNumCardsPerReview INDEX` or `setNumCardsPerReview none`

Examples:
* `setNumCardsPerReview 30`
* `setNumCardsPerReview none`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Clearing the data : `clear`

Clears existing decks and cards. 

Format: `clear`

--------------------------------------------------------------------------------------------------------------------

## Review Mode

Review mode is started when `review INDEX` has been entered on the main mode.

### Flipping the Powercard: `flip`

Flips the Powercard to check the answer of it.

Format: `[`

### Marking the Powercard as correct: `correct`

Marks the current Powercard as correct.

Format: `'`

### Marking the Powercard as wrong: `wrong`

Marks the current PowerCard as wrong.

Format: `;`


--------------------------------------------------------------------------------------------------------------------

### Saving the data

PCs data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PCs data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

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

## Command summary

Action | Format, Examples
--------|------------------

