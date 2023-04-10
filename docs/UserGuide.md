---
layout: page
title: User Guide
---
> A Desktop App for managing TTRPGs

<br />
<div align="center">
  <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp">
    <img alt="Banner" src="images/Reroll_Banner.png" alt="Logo" width="1000">
  </a>
<h3 align="center">Manage your TTRPGs easily.</h3>
  <p align="center">
    A simple yet powerful way to manage your next playthrough
    <br />
    <a href="https://nus-cs2103-ay2223s2.github.io/tp/"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="#tasks">Features</a>
    ·
    <a href="#summary">Commands</a>
    ·
    <a href="#FAQ">FAQ</a>
    ·
    <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp/blob/master/docs/AboutUs.md">Meet the Team</a>
    <br />
    🎉 Version 1.2.1 is out. Check out the latest release
    <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp/releases/">here</a>.
    <br />
    <br />
    <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp/releases/">
    <img src="https://img.shields.io/github/v/release/AY2223S2-CS2103T-T15-1/tp" alt="Logo">
    </a>
    <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp/releases/">
    <img src="https://img.shields.io/github/watchers/AY2223S2-CS2103T-T15-1/tp?style=social" alt="Logo">
    </a>
    <a href="https://github.com/AY2223S2-CS2103T-T15-1/tp/releases/">
    <img src="https://img.shields.io/github/stars/AY2223S2-CS2103T-T15-1/tp?style=social" alt="Logo">
    </a>
  </p>
</div>


Reroll is a **desktop app for managing tabletop RPG character, monster and item sheets, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Reroll can get your entity management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `reroll.jar` from [here](https://github.com/AY2223S2-CS2103T-T15-1/tp/releases/).

1. Copy the file to the folder you want to use as the _home folder_ for Reroll.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar reroll.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `make char John Cena` : Adds a character sheet named `John Cena` to the database.
   
   * `edit char John Cena` : Enters edit mode for a character named `John Cena`.

   * `delete char John Cena` : Deletes previously created character `John Cena`.

   * `list char` : Displays a list of all character entities.
   
   * `template orc John Cena`: Create a character sheet named `John Cena` based on 
   a pre-existing character template called `orc`.
   
   * `clear all` : Deletes all entity sheets.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Important Terms

## Entity classifications
There are 3 different entity classifications in Reroll, each with their own set of fields to track.

`Character` or `char` in short refers to characters whose actions are controlled by players instead of the game master.

`Mob` refers to hostile non-playable characters which are controlled by the game master,
meant to be defeated by player characters.

`Item` refers to equipment such as armour, and miscellaneous tools. These are mainly dropped by mobs and
can be used by the player characters.

## Entity Fields
This is a definitive list of the different fields each entity has:

| Character    | Mob              | Item   |
|--------------|------------------|--------|
| Name         | Name             | Name   | 
| Strength     | Strength         | Cost   |
| Dexterity    | Dexterity        | Weight | 
| Intelligence | Intelligence     | Tags   |
| Level        | Challenge Rating |        |
| XP           | Legendary        |        |
| Tags         | Tags             |        |
| Inventory    | Inventory        |        |

Each field accepts a certain type of value and has a number of command terms, as seen below:

| Field            | Command Term          | Value Type           |
|------------------|-----------------------|----------------------|
| Name             | name                  | Alphanumeric String  | 
| Strength         | strength, str         | `int`                |
| Dexterity        | dexterity, dex        | `int`                | 
| Intelligence     | intelligence          | `int`                |
| Level            | level                 | `int`                |
| XP               | exp, xp               | `int`                |
| Challenge Rating | challengerating, cr   | `float`              |
| Legendary        | isLegendary, legend   | true / false         |
| Tags             | tags, tag             | Alphanumeric Strings |
| Inventory        | inventory             | Items                |

These values come predefined when an entity is created, and if you want the fields to have different values, then you
can edit them later in the edit command, which will be mentioned later.

Fill in one of the field's command terms into where the command says to fill in `FIELD` to edit the corresponding field.

Here's a general breakdown of what each field means:

`Name` is the name of the entity, be it the name of a character, or the name of a mob like a gargoyle.

`Strength` is a numerical measure of the physical force that an entity can exert. This then measures actions like 
if the entity is capable of carrying heavy items, or interacting in scenarios that require lots of 
physical strength.

`Dexterity` is a numerical measure of physical prowess, mostly related to aspects like agility, reflexes and how fast 
the entity can move. Like strength, this determines the choices and outcomes of certain events in the game.

`Intelligence` is a numerical measure of magical and fantastical knowledge, often used to determine if an entity
is able to wield certain spells, use certain magic items, or decipher foreign languages.

`Level` is the numerical measure of the current stage of capability of a character. This increases by one after a 
certain number of XP points are gained, and usually characters get to increase their stats when they level up.

`XP` stands for Experience Points, which are points gained when a character defeats a mob, or completes quests. These 
help the character level up.

`Challenge Rating` is a total numerical score used to judge mob. based on a number of different stats. The higher
the rating, the more difficult it is to fight the mob.

`Legendary` is a boolean value denoting if a mob is legendary, which allows them to have special Legendary Resistances
or Legendary Actions that normal mobs do not have.

`Tags` are string values used to label entities with extra information you might want to, like if a fish is tasty.

`Inventory` is a list of items that mobs and characters have, basically denoting what items they have on hand currently 
and can use. Records the total value of every item in the inventory.


## Templates

`Template` refers to pre-determined characters that can be used to quickly generate characters
with a preferred stat distribution. Templates are pre-determined and cannot be created/edited/deleted by the user.

<h2 id="tasks"></h2>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `make CLASSIFICATION NAME`, `CLASSIFICATION` and `NAME` are parameters which can be used as `add item gilded dagger`.

* CLASSIFICATION can be the following: .<br>
  Item: `item`, Monster:`mob`, Character: `char`

* Extraneous parameters for commands that do not take in parameters (such as `help` or `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Entities in Reroll are identified by classification and name. Entities with the same name but different classification are valid.

* After creation, entities are modified via the edit mode.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Making an entity: `make`

Adds a new entity into the database.

Format: `make CLASSIFICATION NAME`

Examples:
* `make char John Cena`
* `make item Sword`

### Listing all entities of a classification : `list`

Shows a list of all entities in Reroll's database. If no classification is given, all entities are shown instead.

Format: `list CLASSIFICATION`

Examples:
* `list char` shows a list of all characters
* `list mob` shows a list of all mobs
* `list item` shows a list of all items
* `list` shows all entities


### View an entity's details: `view`

A detailed view of a single entity which shows all fields.

Examples:
* `view char John Cena` shows a detailed view of the character named John Cena.

Format: `view CLASSIFICIATION NAME`

Detailed view may be left by entering the command: `back` or `b` while in detailed view.

### Entering edit mode for an entity : `edit`

Enter into edit mode for the specified entity.

Format: `edit CLASSIFICATION NAME`

Examples:
* `edit char Mike` will enter edit mode for the character named Mike

### Editing fields in edit mode : `field`

* In edit mode, changes can be made to any of the fields with the following format: <br>
  * `FIELD NEW_VALUE`
  * e.g. `name gilded staff` or `lvl 9000`
  * Put the command word of the field you want to edit into the FIELD portion of the command.
  * If the specified field is unavailable (i.e. hp of an item), no values will be changed.
  * Shortform names can be used instead to shorten commands. Do check out <a href="#Short-form commands">Short-form commands</a> section for shortform commands!
  * Multiple tags can be added at once, separated by spaces.
  * For legendary status, any value other than `true` defaults to false.
* Inventory has a different command format for adding and removing items from the inventory:
  * `inventory add/remove NAME`
  * e.g. `inventory add fish`
* Refer to [entity fields](#entity-fields) for more details.
* Edit mode may be left by entering the command: `back` or `b` while in edit mode.

Examples:
* `str 55` changes the strength stat to 55
* `t undead zombie` assigns the tags `undead` and `zombie` to the entitiy
* `n tim` changes the name of the entity to tim

### Filter entities by tags : `filter`

Filters the currently selected entities by the given tags. Can be used multiple times to combine filtered tags.

Format: `filter TAG [MORE_TAGS]`

Examples:
* `filter undead` filters out all currently selected entities without the tag `undead`
* `filter zombie` filters out all currently selected entities without the tag `zombie`
*  `filter undead zombie` filters out all currently selected entities without the tags `undead` and `zombie`


### Locating Entity by name: `find`

Finds Entity whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

### Deleting an entity : `delete`

Deletes the specified entity from Reroll's database.

Format: `delete CLASSIFICATION NAME`

* Deletes the entity with both the specified `CLASSIFICATION` and `NAME`.

Examples:
* `delete char John Cena` will delete a `Character` with the exact name `John Cena`.

### Show templates : `show`

Shows all template names.

### Make new character from a template: `template`

Creates a new Character from a pre-determined template

Format: `template TEMPLATE_NAME NAME`

Examples:
* `template orc John Cena` will create a new `Character` named `John Cena` using the template called `orc`

### Clearing all entries : `clear all`

Clears all entries from the database.

Format: `clear all`

### Clearing selected entries : `clear selected`

Clear all entries currently shown on Reroll.

Format: `clear selected`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Reroll's database is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Reroll's data is saved as a JSON file `[JAR file location]/data/reroll.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Reroll will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
<h2 id="FAQ"></h2>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Reroll home folder.

--------------------------------------------------------------------------------------------------------------------
<h2 id="Short-form commands"></h2>

## Short-form commands

To aid expert users, commonly used commands and fields have short-form variants that can be used instead.

| Reference        | Full      | Short-form |
|------------------|-----------|------------|
| Make command     | make      | m          |
| Edit command     | edit      | e          |
| Delete command   | delete    | d          |
| List command     | list      | l          |
| Character        | char      | c          |
| Mob              | mob       | m          |
| Item             | item      | i          |
| Template command | template  | t          |
| Strength         | strength  | s          |
| Dexterity        | dexterity | d          |
| Level            | level     | lvl        |
| Name             | name      | n          |
| Tags             | tags      | t          | 
| Inventory        | inventory | inv        |

Example: `make char John Cena` -> `m c John Cena`

--------------------------------------------------------------------------------------------------------------------
<h2 id="summary"></h2>

## Command summary

| Action                        | Format, Examples                                                    |
|-------------------------------|---------------------------------------------------------------------|
| **Make**                      | `make CLASSIFICATION NAME` <br> e.g., `make char BigMcLargeHuge`    |
| **Clear All**                 | `clear all`                                                         |
| **Clear Selected**            | `clear selected`                                                    |    
| **Delete**                    | `delete CLASSIFICATION NAME`<br> e.g., `delete char BigMcLargeHuge` |
| **Enter Edit Mode**           | `edit CLASSIFICATION NAME`<br> e.g.,`edit char BigMcLargeHuge`      |
| **Edit field (in Edit Mode)** | `FIELD NEW_VALUE` <br> e.g., `name SmallMcTinyMicro`                |
| **Find**                      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Large`               |
| **Show templates**            | `show`                                                              |
| **Template**                  | `template TEMPLATE_NAME NAME` <br> e.g. `template orc John Cena`    |
| **List entities**             | `list CLASSIFICATION` <br> e.g., `list item`                        |
| **Help**                      | `help`                                                              |
| **Filter**                    | `filter TAG [MORE_TAG]` <br> e.g., `filter elite`                   |
