---
layout: page
title: User Guide
---
#### Welcome to CoDoc, a desktop app for students in NUS to connect with each other for the benefit of their course of study.
CoDoc is **optimized for** use via your **Command Line Interface (CLI)** while still having the **benefits of** a **Graphical User Interface (GUI)**. 
If you can type fast, CoDoc can get your **contact management** tasks **done faster** than traditional GUI apps.

CoDoc is more than just a traditional contact management app. Save useful information such as **modules taken** or
**skills possessed** by a person to greatly improve your **networking experience**.

**Spend less time** _keeping track of your contacts_ or _searching through chats_, and **more time** making _meaningful
connections_ and _accomplishing your goals_!

<img src="images/Ui.png"/>

<div markdown="span" class="alert alert-success">
:bulb: <b>Tip:</b> For first-time users, we heavily recommend going through the [Quick Start](#quick-start) to familiarize yourself with CoDoc.
</div>

---
<div style="page-break-after: always;"></div>

# Table of Contents
- [Navigating the User Guide](#navigating-the-user-guide)
- [Glossary](#glossary)
- [Quick Start](#quick-start)
  - [Download and Installation](#download-and-installation)
  - [Navigating CoDoc](#navigating-codoc)
  - [CoDoc Tutorial: Your first steps](#codoc-tutorial-your-first-steps)
    - [Adding a person](#1-adding-a-person)
    - [Viewing a person's modules](#2-viewing-a-person)
    - [Editing a person's modules](#3-editing-a-persons-details)
    - [Finding a person by their modules](#4-finding-a-person)
- [Notes about the command format](#notes-about-the-command-format)
- [Commands](#commands)
  - [Viewing help : `help`](#1-viewing-help--help)
  - [Adding a person : `add`](#2-adding-a-person--add)
  - [Viewing a person/Changing tabs : `view`](#3-viewing-a-personchanging-tabs--view)
  - [Editing a person : `edit`](#4-editing-a-person--edit)
  - [Finding a person : `find`](#5-finding-a-person--find)
  - [Listing all persons/Resetting filters : `list`](#6-listing-all-personsresetting-filters--list)
  - [Deleting a person : `delete`](#7-deleting-a-person--delete)
  - [Clearing all entries : `clear`](#8-clearing-all-entries--clear)
  - [Exiting the program : `exit`](#9-exiting-the-program--exit)
- [Data Management](#data-management)
- [FAQ](#faq)
- [Upcoming Features](#upcoming-features)
- [Command Summary](#command-summary)
- [Additional Resources](#additional-resources)
  - [How to Open CoDoc For Mac](#how-to-open-codoc-for-mac)
  - [How to Open CoDoc For Windows](#how-to-open-codoc-for-windows)

<div style="page-break-after: always;"></div>

## Navigating the User Guide
Before you start using CoDoc, you are recommended to read the
[Glossary](#glossary), [Quick Start](#quick-start) and the [Notes about the command format](#notes-about-the-command-format) sections, which will provide the necessary information to allow you to familiarise yourself with the structure of this
User Guide and help you navigate it with ease.

If you are first time users of CoDoc, we highly recommend that you read through the **entire user guide** in order to become 
**familiar** with CoDoc and to gain the most out of your experience.

We have provided a quick tutorial on how to [download and install](#download-and-installation) CoDoc, 
how to make sense of the [interface](#navigating-codoc), as well as how to [properly use each command](#codoc-tutorial-your-first-steps). 

<div class="span" class="alert alert-info">
:information_source: <b>Note:</b> It is important to note that the command summary is meant for experienced users who happen to forget the commands. <b>All first time users</b> are recommended to read through the <b>entire user guide</b>.
</div>

If you are proficient with the command types and are looking for a quick summary of our commands, you may jump to our [Command Summary](#command-summary) that contains relevant examples.

If you are looking for a detailed explanation of each individual command, you may jump to the [Commands Section](#commands).
Each command is complete with:
1. What the command does
2. The format of the command
3. Examples of the command
4. Picture of the command in action
5. Any additional tips or warnings

If you have questions regarding storing of information, you may proceed to our [Data Management Section](#data-management).

Should you have more questions, you may proceed to our [FAQ](#faq). We hope it will be sufficient to clarify your doubts.

Whenever you are in doubt, forget the command formats for any feature, need help troubleshooting, 
or you simply want to maximise your use of this application, do remember to revisit this User Guide.
Thank you for choosing CoDoc, and we hope you have a pleasant experience :+1:

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

## Glossary
<table>
  <tr>
    <th><strong>Terminology</strong></th>
    <th><strong>Meaning / Purpose</strong></th>
  </tr>
  <tr>
    <td><strong>Command Line Interface (CLI)</strong></td>
    <td>
      A way for users to interact with a piece of software via text in the form of commands where users enter a specific command, press "Enter" and wait for a response.
    </td>
  </tr>
  <tr>
    <td><strong>Graphical User Interface (GUI)</strong></td>
    <td>
      A way for users to interact with a piece of software via visual elements instead of entering commands
    </td>
  </tr>
  <tr>
    <td><strong>Index</strong></td>
    <td>
      An identifier that corresponds to an option. For CoDoc, index are used when selecting courses and contacts—where the index must be a positive integer 1, 2, 3, ...—or navigation of tabs—where the index can be c, m, or s.
    </td>
  </tr>
  <tr>
    <td><strong>Prefix</strong></td>
    <td>
      An identifier that indicates which field our input is meant for. It is denoted by a letter or symbol followed by a slash <code>/</code>. E.g. <code>n/</code> indicates name, <code>c/</code> indicates course, <code>m+/</code> indicates addition of a module, <code>s-/</code> indicates deletion of a skill.
    </td>
  </tr>
  <tr>
    <td><strong>Parameter</strong></td>
    <td>
      An input that you as the user, will give to the command. These can be in the form of numbers or text.
      Some commands take in a parameter after the prefix while some don't require a prefix at all.
    </td>
  </tr>
  <tr>
    <td><div markdown="span" class="alert alert-info"><strong>:information_source: Note</strong></div></td>
    <td>
      To inform you of relevant information that might be useful to take note of as a user.
    </td>
  </tr>
  <tr>
    <td><div markdown="span" class="alert alert-success"><strong>:bulb: Tip</strong></div></td>
    <td>
      To provide you with relevant suggestions on how to use the commands as well as address potential confusions when using
      these commands.
    </td>
  </tr>
  <tr>
    <td><div markdown="span" class="alert alert-danger"><strong>:exclamation: Caution</strong></div></td>
    <td>
      To inform you of some unintended or unexpected consequences that may occur when you use the commands.
    </td>
  </tr>
</table>

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>


## Quick Start

First time using CoDoc? Welcome aboard! Follow the steps below to familiarize yourself with CoDoc.


### Download and Installation

Before you can use CoDoc, you need to download it. To do this, follow these steps:

1. Ensure you have `Java 11` or above installed in your Computer.
   * If not, you can download it from [here](https://www.oracle.com/java/technologies/downloads/#java11)
   
   <br>
2. Download the latest version of `codoc.jar` [here](https://github.com/AY2223S2-CS2103T-F12-2/tp/releases/), where you will be redirected
to our GitHub Releases page. Find the latest release and download the latest version.

    <br>

    ![image](images/user-guide/download_codoc.PNG)

    <br>

3. Copy the file to the folder you want to use as the _home folder_ for CoDoc.<br>
   For example,
   1. Create a new folder where you want CoDoc to be (Desktop, My Documents...anywhere you wish)—this folder will then be the _home folder_.
   2. Place `codoc.jar` into the newly created folder.

    <div style="page-break-after: always;"></div>
   
4. Launch CoDoc.

   * For users familiar with command terminal:
     1. `cd` into the folder you put the jar file in (_home folder_).
     2. `java -jar codoc.jar` to launch the application.
     3. CoDoc should launch in a few seconds.
   
   * For Windows users who prefer clicking in folder to launch:
     1. Create a .bat file in the folder you put the jar file in (_home folder_).
     2. Write `java -jar codoc.jar` into the .bat file.
     3. Click on the .bat file and CoDoc should launch in a few seconds.
   
   For a more detailed guide with examples, refer to [How to Open CoDoc For Windows](#how-to-open-codoc-for-windows) or [How to Open CoDoc For Mac](#how-to-open-codoc-for-mac)

If you have managed to complete all steps, congratulations! You should be seeing CoDoc application 
launched in its factory state as shown below.

![Ui-minimised](images/Ui-minimised.png)
        <br>

[Scroll back to *Table of Contents*](#table-of-contents)

---


### Navigating CoDoc

If it's your first time using this program, the starting screen could seem a little overwhelming. Not to worry, let's familiarize ourselves with the different parts of the CoDoc interface together. We recommend **maximizing** the program window to have a clearer view. This way you can see more content as well.

Below is the breakdown of the CoDoc screen:

![navigation](images/navigation.png)

- **Menu Bar:** if you ever feel stuck, you can always click on the `Help` button here or enter `help` in the command box. 
- **Command Box:** this is where you can type in commands.
- **Result Display:** shows the result of a command that you have executed.
- **Person List Panel:** here are your contacts in CoDoc.
- **Course List Panel:** you can refer to this list when adding a person.
- **Info Panel:** displays information about a selected person, such as their contact details, modules, and skills, which are presented as tabs.

[Scroll back to *Table of Contents*](#table-of-contents)

---

### CoDoc Tutorial: Your first steps

Now that you know how to navigate CoDoc, we recommend you try the following steps to familiarize with 
available features of CoDoc.

<div style="page-break-after: always;"></div>

#### 1. Adding a person

You probably notice a list of contacts already into the database. How can I add one on my own?

Let's say you got to know Bob in school and want to add him to CoDoc. You find out that **Bob**, is a **year 2** student,
taking **Computer Science** and his email address is **e0823741@u.nus.edu**. Here is how you can use CoDoc's
[Add command](#2-adding-a-person--add) to do so:

In the command box, enter the command `add n/Bob y/2 c/6 e/e0823741@u.nus.edu` and press the `enter` key.
This will add a new person named Bob to our contact list.

![adding-bob](images/UG-tutorial/adding-bob.png)

After executing the command, we see that Bob has been added below the list.

> <b>Understanding the Command:</b> As you can see, we first specified the <code>add</code> command, followed by the <em>prefix</em> <code>n/</code> and the <em>name</em> that we want to add i.e. <code>Bob</code>. The same goes for the <em>year</em>, <em>course</em> and <em>email</em>. This <code>n/</code> and <code>Bob</code> pair, is the <em>prefix</em> and <em>parameter</em> pair that occurs frequently in other commands too. Refer to [Glossary](#glossary) for more info.

<div class="span" class="alert alert-danger">  
:exclamation: <b>Caution:</b> You must specify minimally, the person's <i>name</i>, <i>email</i>, <i>year</i> and <i>course</i> when adding a person.
</div>

You may have noticed we entered `c/6` and wonder why the parameter for _course_ was `6` instead of `Computer Science`.

To save time typing out the full course and also standardize formatting, we have coded the command so that you just
have to type its _index_ as the parameter, which you can refer to the [**Course List Panel**](#navigating-codoc) to identify. 

If you want to, you can add additional details of the person such as _GitHub username_, _LinkedIn profile URL_, _modules_ and _skills_.

Related: [Adding a person: `add`](#2-adding-a-person--add)

<div markdown="span" class="alert alert-info">
:information_source: <b>Note:</b> Profile pictures shown here might not be identical to yours as they are randomised.
</div>

<br>

#### 2. Viewing a person

Now that you have added Bob into CoDoc, you can view his details by using the [View command](#2-viewing-a-person).
This will load Bob's details into the [**Info Panel**](#navigating-codoc).

To do so, let's first identify Bob's _index_. From the [**Person List Panel**](#navigating-codoc), we can tell that Bob is
numbered `8` in this example. This is his _index_. Now enter `view 8` into the command box. His _contact_ details will then be shown in the **Info Panel**.

![viewing-bob](images/UG-tutorial/viewing-bob.png)

You may try loading another person's details by using different _index_ (e.g. `view 2`). 

<div class="span" class="alert alert-success">
:bulb: <b>Tip:</b> CoDoc also supports GUI, so you can just click the person in the list to view his details.
</div>

Remember that we said CoDoc offers more than just a traditional contact management app? So far, you have only viewed
the _detailed contacts_ tab, showing basic contact details.

Now let's try viewing other tabs in **Info Panel**. 
To do so, enter `view m` in the command box. This will show Bob's **modules taken** under _detailed modules_ tab.

![viewing-bobs-modules](images/UG-tutorial/viewing-bobs-modules.png)

Note that you do not see anything of value, and that is because we have not added any modules Bob has taken so far
(don't worry, we will show how you can add onto this list below).

You can enter `view s` as well to view Bob's _skills_ to see a similar empty list, or enter `view c` to go back to Bob's
contact information.

<div class="span" class="alert alert-success">
:bulb: <b>Tips:</b> CoDoc also supports GUI, so you can click on the name of different tabs to switch between tabs.
</div>

**Related:** [Viewing a person/Changing tabs: `view`](#2-viewing-a-person)

<br>
<div style="page-break-after: always;"></div>


#### 3. Editing a person's details

To use CoDoc to its fullest potential, you can add Bob's **modules taken** or **skills possessed** to the database.

Let's say you met Bob while taking _CS2101_ and _CS2103T_ modules. To update this information in the database, you can use
CoDoc's [Edit command](#4-editing-a-person--edit).

First make sure that Bob is shown in the right panel using the View command. In this example, we can enter `view 8` to
do so.

>**Why is this step necessary?:** Editing only works on the person shown in the info panel. Make sure you are viewing
> the correct person with the [View command](#2-viewing-a-person) (or with a click) before you enter
> the [Edit command](#4-editing-a-person--edit).

After you see Bob in the **Info Panel**, enter `edit m/ay2223s2 cs2101 m/ay2223s2 cs2103t` in the command box. 

![editing-bobs-modules](images/UG-tutorial/editing-bobs-modules.png)

You can now see that Bob's list of **modules taken** has been updated to contain modules _CS2101_ and _CS2103T_
(enter `view m` if you're not on the _modules_ tab).

<div class="span" class="alert alert-success">
:bulb: <b>Tip:</b> You do not have to be on <em>modules</em> tab to edit it as long as you are viewing the correct person (i.e. you can be viewing Bob's contacts as you add modules). However, switching to <em>modules</em> tab will definitely make it easy for you to edit <b>modules taken</b>!
</div>

If you want to save the hassle of editing _modules_ only after adding a person, you can include them in the initial
[Add command](#2-adding-a-person--add) to do so.

As the name suggests, [Edit command](#4-editing-a-person--edit) is capable of editing other details of the person such as
_name_, _year_, _GitHub username_, etc. Use this to fix your mistakes or update your database as you progress your studies!

**Related:** [Adding a person: `add`](#2-adding-a-person--add),
[Viewing a person: `view`](#2-viewing-a-person),
[Editing a person: `edit`](#4-editing-a-person--edit)

<br>

#### 4. Finding a person

You have been using CoDoc for months and have expanded the database greatly. You realize that finding a person in the
contacts is getting increasingly difficult and wishes there is an easier way to do so.

That is exactly what CoDoc's [Find command](#5-finding-a-person--find) is for! Use this to filter people in the contacts.

Let's try to find people that are taking/have taken CS2103T. To do so, we have to first clear existing filters if they
have been applied. We can do this by entering `list` into the command box. This shows us all existing contacts in CoDoc. 

Then enter `find m/cs2103t`. This will make **Person List Panel** show only the people who have CS2103T in their module
list. On successful execution, you will see something like below.

![finding-cs2103t](images/UG-tutorial/finding-cs2103t.png)

If you tried the examples for previous commands, Bob would be shown as well.

Our `find` command is pretty powerful; it works for other attributes, like _year_, _skills_, etc. and you can find by
multiple attributes. To learn more, look at [Finding a person: `find`](#5-finding-a-person--find).

**Related docs:** [Finding a person: `find`](#5-finding-a-person--find), [Listing all persons/Resetting filters: `list`](#6-listing-all-personsresetting-filters--list)

### **Congrats! You have completed the tutorial 🎉** 

The pre-existing contacts are just examples for you to mess with. When you are ready, enter `clear` in the command box to clear all contacts and start off with a fresh database.

<div class="span" class="alert alert-danger"> 
:exclamation: <b>Caution:</b> Be careful when using the <code>clear</code> command, it will not ask twice before wiping your database!
</div>

If you need more info on the usage of each command, refer to the relevant sections below. 

[Scroll back to *Table of Contents*](#table-of-contents)

<br>

---

## Notes about the command format

* Words in `UPPER_CASE` are the parameters to be **supplied by you**. \
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are **optional**. \
  e.g. `n/NAME [s/SKILL]` can be used as `n/John Doe s/Python` or as `n/John Doe`.
* Items with `…`​ after them can be used **multiple times including zero times**. \
  e.g. `[m/MODULE]…​` can be used as ` ` (i.e. 0 times), `m/AY2223S2 CS2103T`, `m/AY2122S1 CS1101S m/AY2223S2 CS2103T` etc.
* Parameters can be **in any order**. \
  e.g. if the command specifies `n/NAME e/EMAIL`, `e/EMAIL n/NAME` is also acceptable.
* If a parameter is expected only once in the command, but you specified it multiple times, **only the last occurrence of the parameter will be taken**. \
  e.g. if you specify `g/johnny g/john`, only `g/john` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored. \
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

[Scroll back to top](#table-of-contents)

---
<div style="page-break-after: always;"></div>

## Commands

Here is a list of available commands and its details.

### 1. Viewing help : `help`

Forgotten the commands? Open the help menu!

`help`
<div class="span" class="alert alert-info">
:information_source: <b>Note:</b> Equivalent to clicking Help > Help F1 via the GUI.
</div>

[Scroll back to *Table of Contents*](#table-of-contents)

### 2. Adding a person : `add`

Met a new friend? Add their contact into your CoDoc application! 

There are **compulsory** parameters that must be specified to create the new contact.
Once the person has been created, the result display will show a confirmation message and the person can be seen at the bottom of the person list panel.

`add n/NAME e/EMAIL y/YEAR c/COURSE_INDEX [OPTIONAL/PARAMETER]...`<br>

**Compulsory parameters:**
- n/NAME (eg. `n/John Doe`)
  - names are case-sensitive and allow duplicates
- e/EMAIL (eg. `e/johndoe123@email.com`)
  - emails are case-**in**sensitive and **does not allow duplicates**
- y/YEAR (eg. `y/3`)
- c/COURSE_INDEX (eg. `c/1`)

<div style="page-break-after: always;"></div>

**Optional parameters:**
- g/GITHUB (eg.`g/johnny`)
  - GitHub's usernames are case-sensitive and allow duplicates
- l/LINKEDIN (eg. `l/linkedin.com/in/john-doe`)
  - LinkedIn profile URLs are case-sensitive and allow duplicates
- m/MODULE YEAR (eg. `m/AY2223S1 CS2103T`, `m/AY2122S2 CS2101`)
  - modules are case-**in**sensitive and allow duplicate module codes but **does not allow duplicate module year**
- s/SKILL (eg. `s/C++`, `s/Rust`)
  - skills are case-sensitive and **does not allow duplicates**

<div markdown="span" class="alert alert-success">
:bulb: <b>Tip:</b> A person can have any number of [OPTIONAL/PARAMETER] (including 0)
</div>

**Examples:**
- `add n/John Doe e/johnd@example.com y/2 c/3`
- `add n/Betsy Crowe e/betsycrowe@example.com y/4 c/1 g/betsy123 l/linkedin.com/in/betsy-123`

[Scroll back to *Table of Contents*](#table-of-contents)

### 3. Viewing a person/Changing tabs : `view`

Want to look a contact with greater detail? Use view to see more information on the right panel!  

If an `integer` is specified, the person displayed on the right will be the person in the person list that has the **same index** as the integer given.

If `c` is specified, the tab on the right will switch to the **contact information**.

If `m` is specified, the tab on the right will switch to the **modules taken**.

If `s` is specified, the tab on the right will switch to the **skills** that the person has.

`view <PARAMETERS>`<br>

 
**Acceptable parameters:**
- Any index number within the length of the person list (e.g. `view 2`)
- c (e.g. `view c`)
- m (e.g. `view m`)
- s (e.g. `view s`)

<div class="span" class="alert alert-success">
:bulb: <b>Tip:</b> If the right panel is showing a person's module, changing the person by typing <code>view 3</code> for example, will result in the right panel displaying the module tab of the person at index 3. The same applies for skills.
</div>

**Examples:**
- `view 2`
   ![View Example](images/UiViewIndex.jpg)
- `view m`
   ![View Example](images/UiViewModule.jpg)

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### 4. Editing a person : `edit`

One of your contacts is outdated information? Overwrite them with the edit command!

At least one of the optional fields must be provided.

- You can remove a person's GitHub by typing `g/` without specifying any GitHub username after it
- You can remove a person's LinkedIn by typing `l/` without specifying any LinkedIn URL after it
- You can remove all the person’s modules by typing `m/` without specifying any modules after it
- You can remove all the person’s skills by typing `s/` without specifying any skills after it

<div class="span" class="alert alert-danger">
:exclamation: <b>Caution:</b> Existing values will be <b><u>overwritten</u></b> by the input values of <code>m/</code> or <code>s/</code>. Use <code>m+/</code> or <code>s+/</code> to <b><u>append</u></b> information or <code>m-/</code> or <code>s-/</code>  to <b><u>delete</u></b> information instead.
</div>

`edit [OPTIONAL/PARAMETER]...`<br>

**Acceptable Parameters**
- n/NAME (eg. `edit n/John Doe`)
  - names are case-sensitive and allow duplicates
- e/EMAIL (eg. `edit e/johndoe123@email.com`)
  - emails are case-insensitive and does not allow duplicates
- y/YEAR (eg. `edit y/3`)
- c/COURSE_INDEX (eg. `edit c/1`)
- g/GITHUB (eg. `edit g/johnny`)
  - GitHub usernames are case-sensitive and allow duplicates
- l/LINKEDIN (eg. `edit l/linkedin.com/in/john-doe`)
  - LinkedIn profile URLs are case-sensitive and allow duplicates
- m/MODULE YEAR (eg. `edit m/AY2223S1 CS2103T`)
  - m+/MODULE YEAR (eg. `edit m+/AY2223S1 CS2103T m+/AY2021S2 CS1010E`)
  - m-/MODULE YEAR (eg. `edit m-/AY2223S1 CS2103T m-/AY2021S2 CS1010E`)
  - modules are case-insensitive and allow duplicate module codes but does not allow duplicate module year
- s/SKILL (eg. `edit s/C++`)
  - s+/SKILL (eg. `edit s+/C++ s+/python`)
  - s-/SKILL (eg. `edit s-/C++ s-/python`)
  - skills are case-sensitive and does not allow duplicates

<div class="span" class="alert alert-success">
:bulb: <b>Tip:</b> If <code>m/</code> or <code>s/</code> is present in the edit command, this will take precedence over <code>s+/ s-/ m+/ m-/</code>.
</div>

**Examples:**
- `edit g/johndoeee e/johndoeee@example.com`: edit the _GitHub username_ and _email address_ of the person to be `johndoeee` and `johndoeeee@example.com` respectively.
- `edit m-/ay2223s2 cs3230`: displays an error message as the module does not exist.
- `edit s/java s+/python`: resets the skill list to contain only java—ignoring `s+/python`.
![View Example](images/UiEditSkills_crop.png)

[Scroll back to *Table of Contents*](#table-of-contents)

### 5. Finding a person : `find`
Want to find people with certain qualities? Use find command to find people that contain the given constraints! <br>
- `find` supports continuous search by allowing constraints to stack, (refer to example below to find out more)
- All constraints applied are shown in *Result Display*
- To remove all constraints, use `list`.
- At least one of the optional fields must be provided
- All constraints are case-**in**sensitive

<div style="page-break-after: always;"></div>

`find [OPTIONAL/PARAMETER]...`

**Acceptable Parameters used as Constraints**
* n/NAME
  * e.g. `find n/Jo`: load all person whose names contain `Jo` including `John` and `Joleen`
* y/YEAR
  * e.g. `find y/2`: load all person in `year 2`
* c/COURSE
  * e.g. `find c/Computer`: load all person enrolled in a course with `Computer` in the name such as `Computer Engineering` and `Computer Science` <br> <br>
  * <div class="span" class="alert alert-info">:information_source: <b>Note:</b> This is different from <code>add</code> and <code>edit</code> which uses c/COURSE_INDEX. To find out why, refer to our <a href="#faq">faq</a>.</div>
* m/ACADEMIC_YEAR MODULES
  * e.g. `find m/AY2223S1 CS2103`: load all person with `AY2223S1 CS2103` in their module list including `AY2223S1 CS2103T` and `AY2223S1 CS2103R` <br> <br>
  * <div class="span" class="alert alert-success">:bulb: <b>Tip:</b> You can omit ACADEMIC_YEAR as a constraint. E.g. <code>find m/CS2103</code> will load all person with <code>CS2103</code>,<code>CS2103T</code> and <code>CS2103R</code> regardless of ACADEMIC_YEAR in their module list</div>
  * <div class="span" class="alert alert-success">:bulb: <b>Tip:</b> You can input as many MODULES and ACADEMIC_YEAR MODULES as you want separated by a space. E.g. <code>find m/cs1101 cs1231 AY2223S1 cs2040 AY2223S2 cs2090 cs3230</code> will load all persons that have taken <br>
    > <code>CS1101</code>, <code>CS1231</code> regardless of academic year <br>
    > <code>CS2040</code> in <code>AY2223S1</code> <br>
    > <code>CS2090</code>, <code>CS3230</code> in <code>AY2223S2</code> </div>
* s/SKILLS
  * e.g. `find s/java` will load all persons that contains `java` in their skill list such as `java` and `javascript` <br> <br>
  * <div class="span" class="alert alert-success">:bulb: <b>Tip:</b> Similar to m/ACADEMIC_YEAR MODULES you can add as many skills as you want separated by a space.</div>

<div style="page-break-after: always;"></div>


**Example:**
- Start by executing `find y/2` to find all person from year 2 <br> <br> ![UiFind_02](images/UiFind_02_2.png) <br> <br>
- Next, execute `find m/cs1101 s/java c` to further narrow down the filtered list thereby **stacking the constraints** <br> <br> ![UiFind_03](images/UiFind_03_02.png) <br> <br>
- Finally, use `list` to get back the unfiltered list

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### 6. Listing all persons/Resetting filters : `list`

Want to see all your contacts? Display everyone stored in Codoc with the list command!<br>

`list`<br>

<div class="span" class="alert alert-success">
:bulb: <b>Tip:</b> Use this command to revert back to the original list after performing the <code>find</code> command to shorten the list displayed.
</div>

[Scroll back to *Table of Contents*](#table-of-contents)


### 7. Deleting a person : `delete`

Want to remove a contact? Delete it at the specified INDEX with the delete command! 

`delete <INDEX>`<br>

**Example:** `delete 1` will delete the current person at index 1<br>

<div markdown="span" class="alert alert-info">
:information_source: <b>Note:</b> INDEX refers to an index currently shown in the displayed person list. You may check out our [Glossary](#glossary) for more information.
</div>

[Scroll back to *Table of Contents*](#table-of-contents)

### 8. Clearing all entries : `clear`

Want a fresh start? Use the clear command!

`clear`

<div class="span" class="alert alert-danger">
:exclamation: <b>Caution:</b> Clears <b>all</b> entries from CoDoc.
</div>

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### 9. Exiting the program : `exit`

Want to quit? Use exit command to exit the program!

`exit`
<div class="span" class="alert alert-info">
:information_source: <b>Note:</b> Equivalent to clicking the close button via the GUI.
</div>

[Scroll back to *Table of Contents*](#table-of-contents)

---

## Data Management
**Q: How do I save my person list?**

**A:** CoDoc automatically saves your person list after every successful `add`, `edit` and `clear`.

<br>

**Q: How do I transfer my data to another Computer?**

**A:** Install CoDoc on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CoDoc home folder.

<br>

**Q: Where is the data file stored?**

**A:** CoDoc data are saved as a JSON file at ***[JAR file location]*****/data/codoc.json**. Advanced users are welcome to update data directly by editing that data file.

<div class="span" class="alert alert-danger">  
:exclamation: <b>Caution:</b> If your changes to the data file make its format invalid, CoDoc will discard all data and start with an empty data file at the next run.
</div>

<br>

[Scroll back to *Table of Contents*](#table-of-contents)

---

<div style="page-break-after: always;"></div>


## FAQ

**Q: I have added a person with wrong details. How do I change it?**

**A:** Use CoDoc's [Edit command](#4-editing-a-person--edit) to update the details. If you have also forgotten to add certain
details, you may use the same command to add them (e.g. `edit m+/AY2223S2 CS2101` to add module _CS2101_).

<br>

**Q: My edit command is not working/changes are not shown.**

**A:** Check that you have entered the command in correct format and the command result shown in the **Result Display** is not
showing any errors.

<br>

**Q: My edit command is editing the wrong person.**

**A:** Edit command works on the current person you are viewing. Change the viewed person with the [View command](#3-viewing-a-personchanging-tabs--view)
and view the correct person you want to edit before entering the command.

<br>

**Q: Why is it `find c/COURSE` and not `c/COURSE_INDEX` like `add` and `edit`?**

**A:** This is done as it speeds up the adding and editing process since there is no need for you to manually keep track of how courses are inputted—CS vs Computer Science.
Also, this avoids clashing acronyms such as CS which could be interpreted as Chinese Studies too.

<br>

**Q: My find command is not showing the correct list of people.**

**A:** This could be because there is an existing list of filters before you entered the [Find command](#5-finding-a-person--find).
Try clearing all filters with [List command](#6-listing-all-personsresetting-filters--list) then re-enter the Find command.

<br>

**Q: I cannot find the course I want to add in the Course List Panel.**

**A:** We are currently working to add as many courses as possible. Meanwhile, you would have to assign any other courses to the person and remember it.

[Scroll back to *Table of Contents*](#table-of-contents)

---
<div style="page-break-after: always;"></div>

## Upcoming Features

### Automated bulk edits `[coming in v2.0]`
_Details coming soon ..._

### Archiving data files `[coming in v2.0]`
_Details coming soon ..._

### Support for staff `[coming in v2.0]`
_Details coming soon ..._

### Automated year updates `[coming in v2.0]`
_Details coming soon ..._

<br>

[Scroll back to *Table of Contents*](#table-of-contents)

---

<div style="page-break-after: always;"></div>

## Command Summary 

| Actions                          | Example                                                                                                                                     |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Add a person                     | add n/Bob Sim y/2 c/1 e/e0823741@nus.edu g/bobabob l/linkedin.com/in/bom-sim-086g93847 m/ay2223s2 CS2103T m/AY2223S2 cs2101 s/python s/java |
| View contact                     | view 3                                                                                                                                      |
| View tab                         | view c, view m, view s                                                                                                                      |
| Edit contact in the right panel  | edit n/David m+/AY2223S2 CS2109S s-/python                                                                                                  |
| Find by attributes               | find n/David c/Computer Science m/CS2109S s/java                                                                                            |
| List the full list of contacts   | list                                                                                                                                        |
| Delete person at index 3         | delete 3                                                                                                                                    |
| Clear all persons                | clear                                                                                                                                       |
| View user guide and command list | help                                                                                                                                        |
| Exits the application            | exit                                                                                                                                        |

[Scroll back to *Table of Contents*](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Additional Resources
### How to Open CoDoc for Mac

1. Create your *home folder* and place the jar file into it. In this example, <br>
   * we create a new folder called *CoDoc* 
   * and place our `codoc.jar` into it
   ![mac_open_01](images/user-guide/mac_open_01.png)
   <div class="span" class="alert alert-info">
   :information_source: <b>Note:</b> The folder CoDoc is now our <em>home folder</em>.
   </div>
2. Create an empty text file and **leave it open for now.**
   * <kbd>CMD</kbd> + <kbd>Space</kbd> > type *textEdit.app* >  <kbd>Enter</kbd> (to open textEdit)
   * <kbd>CMD</kbd> + <kbd>SHIFT</kbd> + <kbd>T</kbd> (to toggle textEdit to plain text)
   * Your window should look like the image below
   ![mac_plain_text](images/user-guide/mac_plain_text_small.png)
   <div style="page-break-after: always;"></div>
3. Right-click on your home folder and navigate to `New Terminal at Folder`. This will open a terminal window.
   ![mac_open_home_holder](images/user-guide/mac_open_home_folder.png)
4. Then, 
    * type `pwd` > <kbd>Enter</kbd>
    * copy the output which is yourHomeFolderPath
    * **leave this window open too**
   ![mac_pwd](images/user-guide/mac_pwd.png)
   <div style="page-break-after: always;"></div>
5. Next,
   * paste `cd yourHomeFolderPath` as well as `java -jar codoc.jar` 
   * and save it as a `.command` file
   ![mac_command_file_save](images/user-guide/mac_command_file_save.png)
6. Place the newly created `.command` file into your *home folder*.
   ![mac_command_file_result](images/user-guide/mac_command_file_result.png)
    <div style="page-break-after: always;"></div>

7. Finally, on your opened terminal from step 4, type `chmod 777 start.command` > <kbd>Enter</kbd>.

   <div class="span" class="alert alert-info">
   :information_source: <b>Note:</b> Replace <code>start</code> with whatever you name your <code>.command</code> file from step 5.
   </div>
   ![mac_command_file_chmod](images/user-guide/mac_command_file_chmod.png)
   
8. You can now double-click on your `.command` file to open CoDoc.

   <div class="span" class="alert alert-success">
   :bulb: <b>Tip:</b> You can now place the <code>.command</code> file anywhere.
   </div>
   <div class="span" class="alert alert-danger">  
   :exclamation: <b>Caution:</b> If you decide to change the location of <em>home folder</em>, repeat from step 2.
   </div>

<br>

[Scroll back to *Downloading CoDoc*](#download-and-installation)

---
<div style="page-break-after: always;"></div>

### How to Open CoDoc for Windows
Batch file method for Windows users:

1. Right-click the empty space in the _home folder_ and create new _text file_.

    ![open_new_file](images/user-guide/open_new_file.png)

2. Open the _text file_ with any text editor (notepad is fine) and write in `java -jar codoc.jar`. Save and close the file.

    ![open_save_text](images/user-guide/open_save_text_tiny.png)
    <div style="page-break-after: always;"></div>

3. Rename the file to `start.bat`. Make sure that file extension gets changed too, if you do not know how to see the file 
extension, see [here](https://www.howtogeek.com/205086/beginner-how-to-make-windows-show-file-extensions/).
    
    ![open_rename](images/user-guide/open_rename.png)

    <div class="span" class="alert alert-info">
    :information_source: <b>Note:</b> You may see a warning message below. This is because you are converting a text file to a batch file. If so, in the prompt, press "Yes" to continue renaming the file.
    </div>

4. If done correctly, you should now see a file with an icon similar to below. Double-click the renamed batch file `start.bat` to launch CoDoc.

    ![open_done](images/user-guide/open_done.PNG)

<br>

[Scroll back to *Downloading CoDoc*](#download-and-installation)

--------------------------------------------------------------------------------------------------------------------
[Scroll back to *Table of Contents*](#table-of-contents)
