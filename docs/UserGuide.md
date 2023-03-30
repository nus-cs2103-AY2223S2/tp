---
layout: page
title: User Guide
---
# EduMate User Guide

#### Are you a _busy NUS student_ who wants to efficiently manage your **academic** and **social** life?
We acknowledge that for brilliant individuals like yourself, the time you spend in university presents an opportunity to connect with others and establish long-lasting friendships. We have built this [personalised desktop app](#what-is-edumate) to help **YOU** do just that!

## Using this Guide
If you are new to this user guide, we strongly recommend you to read the [Overview](#overview) section. Otherwise,

* If you want to run EduMate for the first time, check out our [Quick Start](#quick-start) guide.
* If you want to learn to use EduMate, check out our [Commands](#commands) section for a detailed guide.
* If you want a more hands-on learning experience with EduMate, check out our [Try-it-out](#try-it-out-recommended) section.
* If you want to contribute to this project, check out our [Developer Guide](https://ay2223s2-cs2103t-w14-2.github.io/tp/DeveloperGuide.html).

For **experienced users**, you may refer to the [Command Summary](#command-summary) for a summarised table of all the commands available. 
## Table of Contents
1. [**Using this Guide**](#using-this-guide)
2. [**Table of Contents**](#table-of-contents)
3. [**Overview**](#overview)
    1. [**What is EduMate?**](#what-is-edumate)
    2. [**Understanding the Colours**](#understanding-the-symbols)
    3. [**Glossary**](#glossary)
4. [**Quick Start**](#quick-start)
    1. [**System Requirements**](#system-requirements)
    2. [**Installation Instructions**](#installation-instructions)
    3. [**Try it out! (Recommended)**](#try-it-out---recommended-)
    4. [**Getting Help**](#getting-help)
5. [**User Interface**](#user-interface)
    1. [**Person List**](#person-list)
    2. [**Profile Panel**](#profile-panel)
    3. [**Command Box**](#command-box)
    4. [**Command Response**](#command-response)
6. [**Set Your Own Profile**](#set-your-own-profile)
7. [**Commands**](#commands)
    1. [**How to interpret the command format**](#how-to-interpret-the-command-format)
    2. [**Arguments**](#arguments)
    3. [**Basic Commands**](#basic-commands)
        1. [**Add a contact `add`**](#add-a-contact-add)
        2. [**View a contact's profile `view`**](#view-a-contacts-profile-view)
        3. [**Edit a contact's details `edit`**](#edit-a-contacts-details-edit)
        4. [**Delete a contact `delete`**](#delete-a-contact-delete)
        5. [**Add a label to a contact `tag`**](#add-a-label-to-a-contact-tag)
        6. [**Remove a label from a contact `untag`**](#remove-a-label-from-a-contact-untag)
    4. [**Advanced Commands**](#advanced-commands)
        1. [**Search Commands**](#search-commands)
            1. [**Filter contacts by keywords `find`**](#filter-contacts-by-keywords-find)
            2. [**Arrange contacts based on criteria `sort`**](#arrange-contacts-based-on-criteria-sort)
        2. [**Storage Commands**](#storage-commands)
            1. [**Save a copy of EduMate `save`**](#save-a-copy-of-edumate-save)
            2. [**Load a copy of EduMate `load`**](#load-a-copy-of-edumate-load)
        3. [**Meet Commands**](#meet-commands)
    5. [**Other Commands**](#other-commands)
        1. [**Exit the application**](#exit-the-application)
8. [**FAQ**](#faq)
9. [**Summary**](#summary)
    1. [**Prefixes**](#prefixes)
    2. [**Command Summary**](#command-summary)
10. [**Troubleshooting**](#troubleshooting)
     1. [**How to check your Java version**](#how-to-check-your-java-version)

--------------------------------------------------------------------------------------------------------------------

## Overview

The following subsections explain the overarching functionality of EduMate, as well as the various terminology we will use in this guide.

### What is EduMate?

EduMate is a desktop app designed for NUS students to manage their academic and social lives. It works as an address book
but has other interesting features as well. It works mostly by commands and can behave like a
Command Line Interface (CLI).

### Understanding the Symbols

Here is a breakdown of the different symbols and colours we will use throughout this guide.

<div markdown="span" class="alert alert-info">

:information_source: Useful information for you to know.

</div>

<div markdown="span" class="alert alert-success">

:bulb: Tips and tricks to enhancing your experience in using EduMate.

</div>

<div markdown="span" class="alert alert-danger">

:warning: Warnings for you to heed so that EduMate works as intended.

</div>

### Glossary

* **Command Line Interface (CLI)**: A text-based user interface (UI) used to run programs, manage computer files and interact with the computer
* **Graphical User Interface (GUI)**: A form of user interface that allows users to interact with electronic devices through graphical icons
* **Mainstream OS**: Windows, Linux, Unix, OS-X

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

### System Requirements

Here is everything you need to install and set up EduMate. For the best possible experience, we recommend that you run the application on the following supported operating systems:

* Windows
* macOS (Both Intel and M1/M2 Chips)
* Linux

Do also ensure that you have Java `11` or above installed in your Computer. If you don’t already have Java `11` or above on your system, head over to [Oracle’s Java download page](https://www.oracle.com/java/technologies/downloads/). To check whether your Java version is compatible with EduMate, please refer to [this section]().

### Installation Instructions

1. Download the latest `eduMate.jar` from [here](https://github.com/AY2223S2-CS2103T-W14-2/tp/releases).

2. Copy the file to the folder you want to use as the _home folder_ for your EduMate.

3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eduMate.jar` command to run the application.<br>
   After a few seconds, a GUI resembling the one shown below will appear. Please take note that the application already includes some sample data.<br>
   ![Ui](images/Ui.png)
   <br>

### Try it out! (Recommended)

<div markdown="span" class="alert alert-primary">

:bulb: **Important:** <br>
Ensure you have met the system requirements and installed EduMate properly on a computer. If you have not,
please see [System Requirements](#system-requirements) and [Installation Instructions](#installation-instructions)
before continuing.
<br>
</div>

Before we dive into exploring the various features of EduMate, please choose whether you prefer a guided or unguided experience.

1. For a guided experience, please enter `sample 100` in the command box located at the top left of the application. This will generate 100 sample contacts in your EduMate, which you can use to familiarize yourself with the application's functionalities.
2. If you would like to start with a fresh EduMate, simply type `clear` in the same command box.

As you navigate through the application, we encourage you to experiment with different [commands](#commands) to further enhance your understanding.

### Getting help

If you require assistance while using EduMate, you may find the solution in the [Commands](#commands) section of this User Guide. Alternatively, you can type in the `help` command to access a quick reference guide.

If you need more assistance, do not hesitate to head over to our
[Github](https://github.com/AY2223S2-CS2103T-W14-2/tp) page and file an issue with a tag *customer-assistance-required*.
A member of our core team will attend to you as soon as possible.

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## User Interface

{to be filled in}

### Person List

{to be filled in}

### Profile Panel

{to be filled in}

### Command Box

{to be filled in}

### Command Response

{to be filled in}

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Set Your Own Profile

Once you have familiarized yourself with EduMate, you are now ready to begin customizing the application to suit your preferences and needs!

**Step 1.** Run `java -jar eduMate.jar`. The application window will open and show you where you have left off
(if you have tried out `sample` from earlier) <br>

**Step 2.** On the Command Box, execute `edit n/[YOUR NAME] p/[YOUR CONTACT NUMBER] t/@[YOUR TELEGRAM HANDLE] 
a/[NEAREST MRT STATION TO YOUR HOUSE] e/[YOUR EMAIL]`
<br>
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** 
You do not have to enter all your information at once! You can omit some of the information
and **its corresponding prefix** and enter them in the future!
<br>


Moreover, eduMate will replace the missing fields with placeholders which you
can then edit over later!

</div>

**Step 3.** To add the modules that you are studying as such :
`tag m/[MODULE_CODE] DAY START_TIME END_TIME`
<br>
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** <br>
E.g `tag m/CS2103T MON 8 10 m/CS2101 WED 14 15`

</div>

**Step 4.** To add groups that you currently belong to as such: `tag g/[GROUP_NAME]`  
<br>
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** <br>
E.g `tag g/SoC g/Sailing` <br>
</div>


**Step 5.** You are done! **ENJOY!** <br>


[Return to the top](#table-of-contents)

------------

## Commands

EduMate is a command-driven application, which means that its various functionalities can be accessed by executing specific commands.

### How to interpret the command format

<div markdown="block" class="alert alert-info">

**:information_source: Basic Command Format**<br>

* The first word is the type of command that you are running.<br>
  e.g. for the command `delete 5`, we are running the `delete` command.

* We use [Prefixes](#prefixes) like `p/` and `g/` to label our arguments for the command. Additionally, we use numbers to specify the index of contacts in our list.<br>
  e.g. `edit 2 n/Steven Tan` runs the `edit` command with an index of 2 and an `n/` argument of Steven Tan.

* Words in `UPPER_CASE` are arguments you are meant to fill in.<br>
  e.g. in `view n/NAME`, you can fill in the `NAME` argument like `view n/Tan Kah Kee`.

* We use `z/` to denote any prefix.<br>
  e.g. `z/FIELD` could mean `n/FIELD` or `a/FIELD`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [g/GROUP]` can be used as `n/Tan Kah Kee g/Friend` or as `n/Tan Kah Kee`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULE]…​` can be used as ` ` (i.e. 0 times), `m/`, `m/CS2108 m/CS2101` etc.

* Unless otherwise specified, arguments can be in any order.<br>
  e.g. if the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable.

* If an argument is expected only once in the command, but you specified it multiple times, only the **last** occurrence of the argument will be taken.<br>
  e.g. `p/86544145 p/81353055` will be interpreted as `p/81353055`.

* Unnecessary arguments will be ignored.<br>
  e.g. `help 123` will be interpreted as `help`.

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Invalid Command Formats**<br>

* Indices must be positive numbers.<br>
  e.g. `delete one` is not an accepted command.

* [Prefixes]() must be preceded by a space.<br>
  e.g. `sort m/an/Tan` is not an accepted command for prefixes `m/` and `n/`.

</div>

[Return to the top](#table-of-contents)

## Attributes

Contacts in EduMate have two types of attributes: single-valued and multi-valued.

Single-valued attributes for each contact can only have one value. Examples of single-valued attributes include a contact's name, phone number, email address, Telegram handle, and home address.

Multi-valued attributes, on the other hand, can have multiple values associated with a single contact. Examples of multi-valued attributes include the groups and modules that a contact belongs to.

### Name

Description: The name of the person.<br>
Pattern: `n/NAME`<br>
Rules: `NAME` should only contain alphanumeric characters and spaces. It **must also be unique**.<br>
Example: `n/Wen Li`

### Phone Number

Description: The phone number of the person.<br>
Pattern: `p/PHONE_NUMBER`<br>
Rules: `PHONE_NUMBER` should only contain numbers, and be at least 3 digits long.<br>
Example: `p/89229358`

### Email Address

Description: The email address of the person.<br>
Pattern: `e/EMAIL`<br>
Rules: `EMAIL` should be of the form `local@domain`, where
* `local` consists of only alphanumeric and the special characters `+`, `_`, `.`, `-`.
* `domain` {can someone fill up}<br>
  Example: `e/wenli@gmail.com`

### Home Address

Description: The name of the **closest MRT station** to their home.<br>
Pattern: `a/ADDRESS`<br>
Rules: `ADDRESS` should be the name of a valid MRT station.<br>
Example: `a/Boon Lay`

### Telegram Handle

Description: The telegram handle of the person.<br>
Pattern: `t/TELEGRAM`<br>
Rules: `TELEGRAM` should start with an `@` symbol.<br>
Example: `t/@wenli`

### Group

Description: The group that you and the person belong to.<br>
Pattern: `g/GROUP`<br>
Rules: `GROUP` should only contain alphanumeric characters.<br>
Example: `g/Groupmate`

### Module

Description: The name of the module the person is taking.<br>
Pattern: `m/MODULE`<br>
Rules: `MODULE` should be the name of a valid NUS module.<br>
Example: `m/CS2107`

### Module with Lessons

Description: The name of the module the person is taking, and a lesson associate with the module.<br>
Pattern: `m/MODULE DAY START_TIME END_TIME`<br>
Rules: `MODULE` should be the name of a valid NUS module. `DAY` should be a valid day of the week. `START_TIME` and `END_TIME` should be valid hour in a day.<br>
Example: `m/CS1234 mon 12 13`

## Basic Commands

### Add a contact `add`

You can easily add a new contact to EduMate using the `add` command. This allows you to quickly store their information for future reference, such as their name, phone number, email address, Telegram handle, and home address. Once added, you can also assign them to groups or modules using the `tag` command.

Format (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `add n/NAME [z/FIELD]…​`: Adds a contact with the given details. Notice that `NAME` is a compulsory field.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `add n/Wen Qing`: Adds a contact with the following details. Note the default values for each attribute:
  * Name: `Wen Qing`
  * Phone number: `00000000`
  * Address: `NUS`
  * Email: `wenqing@gmail.com`
  * Telegram handle: `@wenqing`
  * Groups:
  * Modules:
* `add n/Wen Li p/89229358 a/Boon Lay e/wenli@gmail.com t/@wenli g/Groupmate m/CS2101 m/CS2107`: Adds a contact with the following details:
    * Name: `Wen Li`
    * Phone number: `89229358`
    * Address: `Boon Lay`
    * Email: `wenli@gmail.com`
    * Telegram handle: `@wenli`
    * Groups: `Groupmate`
    * Modules: `CS2101`, `CS2107`

### View a contact's profile `view`

You can use the `view` command to retrieve and view either your own information or a contact's information. This will display the relevant details on the [Profile Panel](#profile-panel).

<div markdown="block" class="alert alert-info">

:information_source: You can use this command if:
* you want to view a contact's full details on the [Profile Panel](#profile-panel).
* you are unsure what index to use for a particular contact

</div>

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `view`: Views your profile.
* `view INDEX`: Views the contact at index `INDEX`.
* `view n/NAME`: Views the contact with name `NAME`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `view 5`: Views the profile of the fifth contact in EduMate.
* `view n/Wen Li`: Views Wen Li's profile.

### Edit a contact's details `edit`

With EduMate's `edit` command, you can easily update the profile of a contact. You have the flexibility to modify as many fields as required, ensuring that your contact's information is always up-to-date.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `edit INDEX [z/FIELD]…​`: Edits the fields for the contact at index `INDEX`.
* `edit [z/FIELD]…​`: Edits your user profile.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `edit 3 n/Wen Qing`: Changes the name of the third contact to `Wen Qing`.
* `edit 6 p/89229358 t/@wenqing`: Changes the phone number and telegram handle of the sixth contact.
* `edit a/Bedok`: Updates your address to `Bedok`.

### Delete a contact `delete`

If you no longer need a contact in EduMate, you can remove them using the `delete` command.

Format (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `delete INDEX`: Deletes the contact at index `INDEX`.

What you should see:<br>
{GUI}<br>
{Explanation}

Example:
* `delete 5`: Removes the fifth contact from EduMate.

### Add a label to a contact `tag`

With Edumate's `tag` command, you can tag your existing contacts with groups and modules using this command. You can also assign lessons to the contacts to indicate their availability during specific time periods. This will enable EduMate to suggest more suitable meet up times when using the `meet` command.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `tag CONTACT_INDEX m/MODULE_TAG`
* `tag m/MODULE_TAG`
* `tag CONTACT_INDEX m/MODULE_TAG DAY START END`
* `tag m/MOUDLE_TAG DAY START END`
* `tag CONTACT_INDEX g/GROUP`
* `tag g/GROUP`

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `tag 1 m/CS1234`: Adds CS1234 to the first contact from EduMate.


### Remove a label from a contact `untag`

With EduMate's `untag` command, you can remove groups or modules from your existing contacts. Additionally, you can unassign lessons from a contact to indicate their availability during that specific period of time. This will help EduMate to suggest more appropriate meet up times when using the meet command.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `untag CONTACT_INDEX m/MODULE_TAG`
* `untag m/MODULE_TAG`
* `untag CONTACT_INDEX m/MODULE_TAG DAY START END`
* `untag m/MOUDLE_TAG DAY START END`
* `untag CONTACT_INDEX g/GROUP`
* `untag g/GROUP`

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `utag 1 m/CS1234`: Removes CS1234 to the first contact from EduMate.



## Advanced Commands

Now that you have become familiar with the basics of EduMate, it's time to explore its more advanced features. For a more guided experience, you can run the `sample 100` command to navigate through this section.

### Search Commands

The search commands in EduMate help you to efficiently navigate and manage your contacts. Instead of manually searching through a long list of contacts, these commands allow you to customise how EduMate displays them, making the process much more efficient.

#### Filter contacts by keywords `find`

EduMate's `find` command allows you to quickly locate specific contacts by searching for keywords within their fields. This can be useful when you need to find someone with a specific tag or shared module, or when you want to obtain specific information about a particular contact. Additionally, you can use the `find` command to generate a list of contacts that meet certain criteria, which can help you manage your contacts more efficiently. To use the `find` command, simply type `find` followed by the relevant keywords or criteria you want to search for.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `find z/KEYWORD [MORE_KEYWORDS]…​`

What you should see:<br>
{GUI}<br>
{Explanation}

<div markdown="block" class="alert alert-success">

:bulb: Tips on usage<br>
* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the field specified by the prefix is searched. e.g. n/ means only the name field is searched
* Words matching the first part of the string will be matched e.g. Han will match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

</div>

Examples:
* `find m/CS2103T CS2109S`: Returns all persons with modules CS2103T or CS2109S
* `find n/Edward Richards`: Returns all persons with names Edward or Richards
* `find p/9093`: Returns all persons with phone numbers starting with 9093

#### Arrange contacts based on criteria `sort`

With EduMate's `sort` command, you have the power to organize your contacts in a way that's most helpful for you. Whether you want to find out who shares the most modules with you or who's likely to become a close friend this semester, the `sort` command can help you out. By simply typing `sort` followed by the relevant criteria, you can sort your contacts by name, the number of shared modules, or any other specified field. This feature can help you identify potential study or social groups within your contacts, making it easier to connect and collaborate with others.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `sort`: Sorts the contacts by their indices.
* `sort z/a`: Sorts the contacts by their `z` attribute **in ascending order**
* `sort z/d`: Sorts the contacts by their `z` attribute **in descending order**
* `sort z/`: Sorts the contacts by their `z` attribute based on our **default ordering**
* `sort z1/ z2/`: Sorts the contacts by their `z1` attribute, and breaks ties using their `z2` attribute
* `sort [z/]…​`: Sorts the contacts by multiple attributes

<div markdown="block" class="alert alert-info">

:information_source: How we sort the attributes:

* The `NAME`, `EMAIL`, `ADDRESS`, `PHONE` and `TELEGRAM` attributes are sorted by alphabetical order, and is default in **ascending** order.
* For `GROUP`, we sort the contacts based on the number of groups the contact belongs to. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 groups (TA, NS), and Ben has one group (TA), Alex will be placed before Ben in the default ordering.
* For `MODULE`, we sort the contacts based on the number of **common modules** they share with you. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 modules (CS1101S, CS1231S), Ben has one module (CS1101S), and you have 2 modules (CS1231S, MA2001), then Alex has 1 module in common with you (CS1231S) and Ben has 0 modules in common with you. As such, Alex will be placed before Ben in the default ordering.

</div>

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `sort n/d`: Sorts by name in descending order
* `sort m/a`: Sorts by the number of modules they have in common with you (in ascending order)
* `sort t/`: Sorts by Telegram handle in its default ordering (ascending)

### Storage Commands

The storage commands allow you to save and load your EduMate data into a file. This means you can easily backup your data and access it from different devices or share it with others.

<div markdown="block" class="alert alert-info">

:information_source: You can use these commands if:
* You want to create backups of your EduMate
* You want to explore the functions of EduMate without fear of losing your data
* You want to maintain multiple versions of EduMate

</div>

#### Save a copy of EduMate `save`

Using the `save` command in EduMate, you can easily save your data into a file with a name of your choice. This allows you to back up your data and access it later or share it with others.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `save FILENAME`: Saves the EduMate into a file at `FILENAME.json`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `save backup`: Saves the EduMate in the `backup.json` file.

#### Load a copy of EduMate `load`

Using the `load` command in EduMate, you can easily load a previously saved data file into the application. This allows you to access your data from different devices or recover it in case of data loss.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `load FILENAME`: Loads the EduMate from a file at `FILENAME.json`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:
* `load backup`: Loads the EduMate from the `backup.json` file.

### Meet Commands

The `meet` command in EduMate is used to suggest suitable meet-up locations and times for users and their contacts, based on various criteria such as availability and proximity. It is useful for finding a mutually convenient time and place to meet up with friends or study partners. EduMate also offers more specialized commands such as `eat` for suggesting places to eat and `study` for suggesting places to study.

Formats (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):
* `meet 12 32 8 4` : Recommends a meeting time and location for a potential meet up with friends with the following indices `12, 32, 4, 8`.
* `eat 5 2 8 1 3` : Recommends a meeting time and eating spot for friends with the following indices `5, 2, 8, 1, 3`
* `study 6 2` : Recommends a meeting time and study spot for friends with the following indices `6 and 2`.

What you should see:<br>
{GUI}<br>
{Explanation}

Examples:

## Other Commands

### Exit the application

If you want to end the application, simply type `exit`, or click on the `Exit` button located on the left of the interface.

## FAQ

**Q**: Can I add multiple module tags to a user
**A**: Yes, you can add multiple tags to a single user by using the "Module Tagging" feature multiple times.

**Q**: Can I edit my own profile?
**A**: Yes you may! Refer to [Editing a Contact's Details](#edit-a-contacts-details-edit)

**Q**: How is `EduMate` better than Microsoft Outlook or uWave?
**A**: We have features that Microsoft Outlook or uWave don't have but we think should be integrated into these platforms, like the [meet up recommenders](#meet-commands)
and several search functions like [sort](#arrange-contacts-based-on-criteria-sort) and [find](#filter-contacts-by-keywords-find). It is also open-source so student developers can 
suggest new features and it is easily extensible!


## Summary

### Prefixes

| Prefix | Representation  |
|--------|-----------------|
| n/     | Name            |
| a/     | Address         | 
| p/     | Telegram Handle | 
| e/     | Email           | 
| g/     | Group           | 
| m/     | Module          | 
| "   "  | Empty           | 

### Command Summary

| Action               | Format (See [Interpreting Command Formats](#how-to-interpret-the-command-format))      | Examples                                                    |
|----------------------|----------------------------------------------------------------------------------------|-------------------------------------------------------------|
| **Add a contact**    | `add n/NAME p/PHONE...`                                                                | `add n/Wen Li...`                                           |
| **View a profile**   | `view`, `view INDEX`, `view n/NAME`                                                    | `view 5`, `view n/Wen Li`                                   |
| **Edit a contact**   | `edit INDEX [z/FIELD]…​`, `edit [z/FIELD]…​`                                           | `edit 4 n/Wen Qing`, `edit a/NUS t/@wenqing`                |
| **Delete a contact** | `delete INDEX`                                                                         | `delete 3`                                                  |
| **Tag a contact**    | `tag INDEX m/MODULE...`, `tag m/MODULE...`, `tag INDEX g/GROUP`, `tag g/GROUP`         | `tag m/CS1234`, `tag m/CS2345 mon 12 1`, `tag 1 g/Friend`    |
| **Untag a contact**  | `untag INDEX m/MODULE...`, `untag m/MODULE...`, `untag INDEX g/GROUP`, `untag g/GROUP` | `untag m/CS1234`, `untag m/CS2345 mon 12 1`, `tag 1 g/Friend` |
| **Filter contacts**  | `find z/FIELD`                                                                         | `find n/Tan`, `find m/CS1231`                               |
| **Sort contacts**    | `sort [z/a]…​`, `sort [z/d]…​`, `sort [z/]…​`                                          | `sort`, `sort n/a`, `sort m/ p/d`                           |
| **Save a copy**      | `save FILE_NAME`                                                                       | `save backup`                                               |
| **Load a copy**      | `load FILE_NAME`                                                                       | `load backup`                                               |
| **Suggest meet ups**  | `meet [INDEX]…​`, `eat [INDEX]…​`, `study [INDEX]…​`                                   | `meet 1 6 4`, `eat 10 4 7`                                  |


## Troubleshooting

### How to check your Java version

#### For **Windows** Users
**Step 1** Open up Command Prompt

**Step 2** Run the command `java -version`

**Step 3** Check the version number from the output.

#### For **Mac** and **Linux** Users
**Step 1** Open up Terminal.

**Step 2** Run the command `java -version`

**Step 3** Check the version number from the output.

