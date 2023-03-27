---
layout: page
title: User Guide
---

#### Welcome to CoDoc, a desktop app that allows students in SoC to connect with each other for the benefit of their course of study. CoDoc is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoDoc can get your contact management tasks done faster than traditional GUI apps and networking with people can be easily done.

<img src="images/Ui.png"/>
--------------------------------------------------------------------------------------------------------------------

# Table of Contents
- [Quick Start](#quick-start)
  - [Download and Installation](#download-and-installation)
  - [Navigation](#navigation)
  - [Tutorial](#tutorial)
    - [Adding a person](#1-adding-a-person)
    - [Viewing a person's modules](#2-viewing-a-persons-modules)
    - [Editing a person's modules](#3-editing-a-persons-modules)
    - [Finding a person by their modules](#4-finding-a-person-by-their-modules)
- [Commands](#commands)
  - [Viewing help: `help`](#help)
  - [Adding a person: `add`](#adding-a-person--add)
  - [Viewing a person/Changing tabs: `view`](#viewing-a-personchanging-tabs--view)
  - [Editing a person: `edit`](#editing-a-person--edit)
  - [Finding a person: `find`](#finding-a-person--find)
  - [Lising all persons/Resetting filters: `list`](#listing-all-personsresetting-filters--list)
  - [Deleting a person: `delete`](#deleting-a-person--delete)
  - [Clearing all entries: `clear`](#clearing-all-entries--clear)
  - [Exiting the program: `exit`](#exiting-the-program--exit)
- [Data Management](#data-management)
- [FAQ](#faq)
- [Upcoming Features](#upcoming-features)
- [Command Summary](#command-summary)
- [Additional Resources](#additional-resources)
  - [How To Check Java Version](#how-to-check-java-version)
  - [How to Open CoDoc](#how-to-open-codoc)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

### Download and Installation

Before you can use CoDoc, you need to download it. To do this, follow these steps:

1. Ensure you have `Java 11` or above installed in your Computer. -> [How To Check Java Version](#how-to-check-java-version)
   * If not, you can download it from [here](https://www.oracle.com/java/technologies/downloads/#java11)
2. Download the latest version of `codoc.jar` [here](https://github.com/AY2223S2-CS2103T-F12-2/tp/releases/tag/v1.3.trial).
3. Copy the file to the folder you want to use as the _home folder_ for CoDoc.<br>
   For example,
   1. Create a new folder in your Desktop—this folder will then be the _home folder_.
   2. Place `codoc.jar` into the newly created folder.
4. Open a command terminal -> [How to Open CoDoc](#how-to-open-codoc)
   1. `cd` into the folder you put the jar file in.
   2. `java -jar codoc.jar` to run the application.<br>
   3. A window similar to the one below should appear in a few seconds.

      <img src="images/Ui-minimised.png"/>
      <br>

[Scroll back to *Tables of Contents*](#table-of-contents)

### Navigation

Before we begin, let's familiarize ourselves with the different parts of the CoDoc interface.
Maximise the screen to have a clearer view.
This way you can see more content as well.

<img src="images/navigation.png"/>

- **Menu Bar:** if you ever feel stuck, you can always click on the `Help` button here or enter `help` in the command box. 
- **Command Box:** this is where you can type in commands.
- **Result Display:** shows the result of a command that you have executed.
- **Person List Panel:** here are your contacts in CoDoc.
- **Course List Panel:** you can refer to this list when adding a person.
- **Info Panel:** displays information about a selected person, such as their contact details, modules, and skills, which are presented as tabs.

### Tutorial

Now that you know how to navigate CoDoc, let's start by adding a person to our contact list! To do this, follow these steps:

#### 1. Adding a person

Let's start by adding a person named Bob, a year 2 student taking Computer Science with the email e0823741@nus.edu. 

Enter the command `add n/Bob y/2 c/6 e/e0823741@nus.edu` into the command box. This will add a new person named Bob to our contact list. 

**Note:** You must specify minimally, the person's _name_, _email_, _year_ and _course_ when adding a person.

As you can see, we first specify the `add` command followed by the _name prefix_ `n/` and the _name_ that we want to add i.e. `Bob`. Same goes for the _year_, _course_ and _email_. 

**Note:** The person's _course_ corresponds to its index in the course list panel.

Here we used `c/6`, because if you look at the course list panel on the left, Computer Science refers to index `6`. 

If you want to, you can add other details of the person such as his/her _GitHub username_, _LinkedIn profile URL_, _modules_ and _skills_.

Related docs: [Adding a person: `add`](#adding-a-person--add)

If you followed the steps above correctly, you would see this screen:

<img src="images/tutorial/adding-bob.png"/>

**Note:** Your profile pictures and the profile pictures shown here will not be exactly the same as they are randomised.

#### 2. Viewing a person's modules

As shown above, the right info panel automatically updated to show Bob's contact after we added him. 

Now let's take a look at his _modules_: enter `view m` in the command box. As you can see, his module list is currently empty.

<img src="images/tutorial/viewing-bobs-modules.png"/>

You can enter `view s` as well if you want to see Bob's _skills_. You can also view another person by specifying the person's index in the list i.e. `view 2` would load Nicole Lee in the info panel.

**Related docs:** [Viewing a person/Changing tabs: `view`](#viewing-a-personchanging-tabs--view)

#### 3. Editing a person's modules

To update Bob's module list to have let's say CS2101 and CS2103T, first make sure that Bob is shown in the right info panel, by entering `view 8`. 

Then enter `edit m/ay2223s2 cs2101 m/ay2223s2 cs2103t` in the command box. 

**Note:** Editing only works on the person shown in the info panel. If the person you want to edit is not shown in the info panel, you have to use the `view` command first to view that person, then edit as you wish. Also, _modules_ must follow a certain format to be successfully added/edited.

**Related docs:** [Viewing a person/Changing tabs: `view`](#viewing-a-personchanging-tabs--view), [Editing a person: `edit`](#editing-a-person--edit)

Bob's updated module list (use `view m` to see) would look like this after you entered the `edit` command:

<img src="images/tutorial/editing-bobs-modules.png"/>

You might be thinking why not we just add Bob's _modules_ in the first place when adding him? You can do that! But for this tutorial, we have separated it to show how you can use the `edit` command. 

You can also edit other details of the person such as his/her _name_, _year_, _GitHub username_, etc., not just their _modules_.

**Related docs:** [Adding a person: `add`](#adding-a-person--add), [Editing a person: `edit`](#editing-a-person--edit)

#### 4. Finding a person by their modules

Let's try to find contacts that are taking/have taken CS2103T. First, let's reset all existing filters if they have been applied. We can do this by entering `list`. This shows us all the contacts in CoDoc. 

Then enter `find m/cs2103t`. This would show the contacts in the person list panel that have CS2103T in their module list:

<img src="images/tutorial/finding-cs2103t.png"/>

Here, if you followed the previous steps correctly, Bob would be shown as well.

Our `find` command is pretty powerful; it works for other attributes, like _year_, _skills_, etc. and you can find by multiple attributes. To learn more about it, you can take a look at [Finding a person: `find`](#finding-a-person--find)

**Related docs:** [Finding a person: `find`](#finding-a-person--find), [Listing all persons/Resetting filters: `list`](#listing-all-personsresetting-filters--list)

Congrats! You have completed the tutorial 🎉 

The pre-existing contacts are just some examples that you can play around with, but if you want to start with a fresh contact list, enter `clear` to clear all contacts. Also, if you need more info on the usage of each command, you can refer to the relevant sections below. 

[Scroll back to *Tables of Contents*](#table-of-contents)

## Commands

### Viewing help : `help`

`help`<br>
> <img src="images/UiHelpMenu.png"/>
> * Opens help menu <br>
> * Equivalent to clicking Help > Help F1 via the GUI <br>

### Adding a person: `add`

`add n/NAME e/EMAIL y/YEAR c/COURSE_INDEX [OPTIONAL/PARAMETER]...`<br>
>
> **Compulsory parameters:**
> - n/NAME (eg. n/John Doe)
> - e/EMAIL (eg. e/johndoe123@email.com)
> - y/YEAR (eg. ay/3, ay/prof, ay/alum)
> - c/COURSE_INDEX (eg. c/1)
>
> **Optional parameters:**
> - g/GITHUB (eg.g/johnny)
> - l/LINKED (eg. l/linkedin.com/in/john-doe)
> - m/MODULE YEAR (eg. m/AY2223S1 CS2103T, m/AY2122S2 CS2101)
> - s/SKILL (eg. s/C++, s/Rust)
>
> 💡 **Tip:** A person can have any number of [OPTIONAL/PARAMETER] (including 0)
>
> **Examples:**
> - `add` n/Betsy Crowe e/betsycrowe@example.com y/prof c/1 g/betsy123 l/linkedin.com/in/betsy-123
> - `add` n/John Doe e/johnd@example.com y/2 c/3
    > ![Add Example](images/UiAddContact.jpg)

### Viewing a person/Changing tabs: `view`
`view <PARAMETERS>`<br>

### Editing a person: `edit`
`edit [OPTIONAL/PARAMETER]...`<br>
> - Edits the person displayed in the view panel
> - At least one of the optional fields must be provided.
> - Existing values will be updated to the input values.
> - When editing skills, the existing skills of the person will be removed i.e adding of skills is not cumulative.
> - You can remove all the person’s skills by typing s/ without specifying any skills after it
>
> **Examples:**
> - edit g/91234567 e/johndoe@example.com will edit the github username and email address of the person to be 91234567 and johndoe@example.com respectively.
> - edit n/Betsy Crower s/ Edits the name of the person to be Betsy Crower and clears all existing skills.

### Finding a person: `find`
`find [OPTIONAL/PARAMETER]...`<br>
>> find KEYWORD [MORE_KEYWORDS]
> * The search is case-insensitive. e.g `hans` will match `Hans`
> * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
> * Only the name is searched.
> * Only full words will be matched e.g. `Han` will not match `Hans`
> * Persons matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
>
> **Examples:**
> - `find John` returns `john` and `John Doe`
> - `find alex david` returns `Alex Yeoh`, `David Li`<br>
    ![result for 'find alex david'](images/findAlexDavidResult.jpg)

### Listing all persons/Resetting filters: `list`
`list`<br>
> * Shows all persons stored.<br>

### Deleting a person: `delete`
`delete <INDEX>`<br>
> * Deletes the person at the specified INDEX.<br>
> * INDEX refers to an index currently shown in the displayed person list.<br>
> * INDEX must be a positive integer 1, 2, 3, ...<br>
> * E.g. `delete 1` will delete the current person at index 1 as shown in the list panel.<br>

### Clearing all entries: `clear`
`clear`<br>
> * :warning: Clears **all** entries from CoDoc.<br>


### Exiting the program: `exit`
`exit`<br>
> * Exits the program. <br>
> * Equivalent to clicking the close button via the GUI. <br>

[Scroll back to *Tables of Contents*](#table-of-contents)

## Data Management
**Q**: How do I save my person list?<br>
**A**: CoDoc automatically saves your person list after every successful `add`, `edit` and `clear`.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install CoDoc in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoDoc home folder.

**Q**: Where is the data file stored?<br>
**A**: CoDoc data are saved as a JSON file at ***[JAR file location]*****/data/codoc.json**. Advanced users are welcome to update data directly by editing that data file.

:warning: If your changes to the data file makes its format invalid, CoDoc will discard all data and start with an empty data file at the next run.

[Scroll back to *Tables of Contents*](#table-of-contents)

## FAQ

[Scroll back to *Tables of Contents*](#table-of-contents)

## Upcoming Features

### Automated bulk edits `[coming in v2.0]`
_Details coming soon ..._

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

<br>

[Scroll back to *Tables of Contents*](#table-of-contents)


## Command Summary 

| Actions                          | Description                                                                                                                                 |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Add a person                     | add n/Bob Sim y/2 c/1 e/e0823741@nus.edu g/bobabob l/linkedin.com/in/bom-sim-086g93847 m/ay2223s2 CS2103T m/AY2223S2 cs2101 s/python s/java |
| Clear all persons                | clear                                                                                                                                       |
| Delete person at index 3         | delete 3                                                                                                                                    |
| Edit contact in the right panel  | edit n/David m+/AY2223S2 CS2109S s-/python                                                                                                  |
| Find by attributes               | find n/David c/2 m/CS2109S s/java                                                                                                           |
| List the full list of contacts   | list                                                                                                                                        |
| View contact                     | view 3                                                                                                                                      |
| View tab                         | view c, view m, view s                                                                                                                      |
| View user guide and command list | help                                                                                                                                        |

[Scroll back to *Tables of Contents*](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Additional Resources
### How To Check Java Version
[Scroll back to *Downloading CoDoc*](#downloading-codoc)
### How to Open CoDoc
[Scroll back to *Downloading CoDoc*](#downloading-codoc)

--------------------------------------------------------------------------------------------------------------------
[Scroll back to *Tables of Contents*](#table-of-contents)
