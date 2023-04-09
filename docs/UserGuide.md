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
* [**Using this Guide**](#using-this-guide)
* [**Table of Contents**](#table-of-contents)
* [**Overview**](#overview)
  * [**What is EduMate?**](#what-is-edumate)
  * [**Understanding the Symbols**](#understanding-the-symbols)
  * [**Glossary**](#glossary)
* [**Quick Start**](#quick-start)
  * [**System Requirements**](#system-requirements)
  * [**Installation Instructions**](#installation-instructions)
  * [**Try it out! (Recommended)**](#try-it-out-recommended)
  * [**Getting Help**](#getting-help)
* [**User Interface**](#user-interface)
  * [**Breakdown of EduMate's UI**](#breakdown-of-edumates-ui)
  * [**Person List**](#person-list)
  * [**Meet Up Recommendation Panel**](#meet-up-recommendation-panel)
  * [**Profile Panel**](#profile-panel)
  * [**Scheduled Meet Up Panel**](#scheduled-meet-up-panel)
  * [**Command Response Box**](#command-response-box)
  * [**Command Box**](#command-box)
  * [**Help Button**](#help-button)
  * [**Exit Button**](#exit-button)
* [**Set Your Own Profile**](#set-your-own-profile)
* [**Commands**](#commands)
  * [**How to interpret the command format**](#how-to-interpret-the-command-format)
  * [**Attributes**](#attributes)
    * [**Basic Attributes**](#basic-attributes)
      * [**Basic Attributes Table**](#basic-attributes-table)
    * [**Other Attributes**](#other-attributes)
      * [**Group**](#group)
      * [**Module**](#module)
      * [**Module with Lessons**](#module-with-lessons)
  * [**Basic Commands**](#basic-commands)
    * [**Add a contact `add`**](#add-a-contact-add)
    * [**View a contact's profile `view`**](#view-a-contacts-profile-view)
    * [**Edit a contact's details `edit`**](#edit-a-contacts-details-edit)
    * [**Delete a contact `delete`**](#delete-a-contact-delete)
    * [**Add a label to a contact `tag`**](#add-a-label-to-a-contact-tag)
    * [**Remove a label from a contact `untag`**](#remove-a-label-from-a-contact-untag)
    * [**Listing all your contacts `list`**](#listing-all-your-contacts-list)
* [**Advanced Commands**](#advanced-commands)
  * [**Search Commands**](#search-commands)
    * [**Filter contacts by keywords `find`**](#filter-contacts-by-keywords-find)
    * [**Arrange contacts based on criteria `sort`**](#arrange-contacts-based-on-criteria-sort)
  * [**Storage Commands**](#storage-commands)
    * [**Save a copy of EduMate `save`**](#save-a-copy-of-edumate-save)
    * [**Load a copy of EduMate `load`**](#load-a-copy-of-edumate-load)
  * [**Meet Commands**](#meet-commands)
    * [**Suggest places to meet with your contacts `meet`, `study`, `eat`**](#suggest-places-to-meet-with-your-contacts-meet-study-eat)
  * [**Schedule Commands**](#schedule-commands)
    * [**Organise a meet up with your contacts `organise`**](#organise-a-meet-up-organise)
    * [**Unorganise a meet up with your contacts `unorganise`**](#unorganise-a-meet-up-unorganise)
* [**Other Commands**](#other-commands)
  * [**Sample some contacts `sample`**](#sample-some-contacts-sample)
  * [**Clear EduMate `clear`**](#clear-the-application-clear)
  * [**Exit the application `exit`**](#exit-the-application-exit)
* [**FAQ**](#faq)
* [**Summary**](#summary)
  * [**Prefixes**](#prefixes)
  * [**Command Summary**](#command-summary)
* [**Troubleshooting**](#troubleshooting)
  * [**How to check your Java version**](#how-to-check-your-java-version)

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
* **OS**: Operating System
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Mass Rapid Transit (MRT)**: Singapore's high-speed rail system.
* **Co-Curricular Activities (CCA)**: Another name for extra-curricular activities.
* **NUS**: National University of Singapore

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick start

### System Requirements

Here is everything you need to install and set up EduMate. For the best possible experience, we recommend that you run the application on the following supported operating systems:

* Windows
* macOS (Both Intel and M1/M2 Chips)
* Linux

Do also ensure that you have Java `11` or above installed in your Computer. If you don’t already have Java `11` or above on your system, head over to [Oracle’s Java download page](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html). 

For users who are using MacBooks with M1 or M2 chips, you might want to head over to [Azul Open JDK download page](https://www.azul.com/downloads/?version=java-11-lts&os=macos&package=jdk#zulu) to download Java `11`.

To check whether your Java version is compatible with EduMate, please refer to [How To Check Your Java Version](#how-to-check-your-java-version).

### Installation Instructions

**Step 1.** Download the latest `edumate.jar` from [here](https://github.com/AY2223S2-CS2103T-W14-2/tp/releases).

**Step 2.** Copy the file to the folder you want to use as the _home folder_ for your EduMate.

**Step 3.** Open a command terminal, change directory into the folder where you put the jar file in via the command `cd`, and use the `java -jar edumate.jar` command to run the application.<br>


After a few seconds, a GUI resembling the one shown below will appear. Please take note that the application already includes some sample data.<br>
   ![Ui](images/Ui.png)
<center>Diagram 1: The GUI upon start up.</center>

<br>

<div markdown="span" class="alert alert-danger">

:warning: **Warning!**<br>
`EduMate` will create a `data` folder which contains the data saved by `EduMate`. It is essential that you do not modify the contents
of the `data` folder so as not to corrupt your data and result in an empty application by default.

</div>

### Try it out! (Recommended)

<div markdown="span" class="alert alert-success">

:bulb: **Important:** <br>
Ensure you have met the system requirements and installed EduMate properly on a computer. If you have not,
please see [System Requirements](#system-requirements) and [Installation Instructions](#installation-instructions)
before continuing.
<br>
</div>

Before we dive into exploring the various features of EduMate, please choose whether you prefer a guided or unguided experience.

1. For a guided experience, please enter `sample 100` in the command box located at the top left of the application. This will generate 100 sample contacts in your EduMate, which you can use to familiarize yourself with the application's functionalities.
2. If you would like to start with a fresh EduMate, simply type `clear` in the same [Command Box](#command-box) located at the bottom of the application.

<div markdown="span" class="alert alert-danger">

:warning: **Important `clear` or `sample` warnings** <br>

Performing either action will remove any contacts you currently have on your EduMate. 

To save your progress, have a look at our [Save Command](#save-a-copy-of-edumate-save).

</div>

As you navigate through the application, we encourage you to experiment with different [commands](#commands) to further enhance your understanding.

### Getting help

If you require assistance while using EduMate, you may find the solution in the [Commands](#commands) section of this User Guide. Alternatively, you can type in the `help` command to access a quick reference guide.

If you need more assistance, do not hesitate to head over to our
[Github](https://github.com/AY2223S2-CS2103T-W14-2/tp) page and file an issue with a tag *customer-assistance-required*.
A member of our core team will attend to you as soon as possible.

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## User Interface

Our UI components have been carefully crafted to fulfil specific functions, ensuring that your experience using our platform is as seamless and user-friendly as possible.

We have chosen a light purple and white color scheme for the application, as these cool colors promote a sense of calm and clarity, making it easier for you to focus on your work.

![Ui](images/UG_ui_pictures/user_interface.png)
<center>Diagram 2: <code>EduMate</code>'s UI</center>

### **Breakdown of EduMate's UI**

`EduMate` consists of the following main UI components
1. [Person List](#person-list)
2. [Meet Up Recommendation Panel](#meet-up-recommendation-panel)
3. [Profile Panel](#profile-panel)
4. [Scheduled Meet Up Panel](#scheduled-meet-up-panel)
5. [Command Response Box](#command-response-box)
6. [Command Box](#command-box)
7. [Help Button](#help-button)
8. [Exit Button](#exit-button)

The following diagram shows the layout of components numbered exactly as above.

<img src="images/UG_ui_pictures/ui_labelled.svg" style="width:80%;margin:0 10%">

<center>Diagram 3: Labelled layout of <code>EduMate</code>'s UI components.</center>

### Person List

You may locate the Person List at the center-left of `EduMate`. This is where you will see all your saved contacts (or sample data if you are trying out for the first time).

When viewing your contacts in EduMate, you will notice an index assigned to each contact. This index is located directly to the _left_ of the contact's display name, and serves as a unique identifier for each contact in your list.

<img src="images/UG_ui_pictures/PersonList.svg" style="width:40%;margin:1% 1% 1% 30%">

<center>Diagram 4: Person List with some contacts displayed.</center>
<br>
<div markdown="block" class="alert alert-info">

:information_source: **Appearance of Module Tags**<br>
Not all modules that are tagged to the contact are shown on the Person List. If a contact shares the same module as you, the module tag corresponding to that module will appear for that contact on the Person List.
<br>

<img src="images/UG_ui_pictures/PersonListCommonMods.svg" style="width:80%;margin:0 10%">

<center>Diagram 5: Person List (in red) with common modules boxed in green.</center>
</div>

### Meet Up Recommendation Panel

The Meet Up Recommendation Panel (located at the top right of `EduMate`) suggests recommendations of optimal timings and locations
where you can meet your friends.

<img src="images/UG_ui_pictures/MeetUpRecommendationUnlabelled.svg" style="width:80%;margin:1% 1% 1% 10%">

<center>Diagram 6: Recommended timings and locations for meetups displayed</center>
<br>
<div markdown="block" class="alert alert-info">

:information_source: **Components of Meet Up Recommendation Panel**<br>
The panel, as shown below, contains all the recommended timings and locations for your meetups.
<br>

<img src="images/UG_ui_pictures/MeetUpRecommendationPanel.svg" style="width:80%;margin:0 10%">

<center>Diagram 7: Components of the Meet Up Recommendation Panel</center>
</div>

### Profile Panel

The Profile Panel allows you to display your own or your contact's information.

The Profile Panel will display basic information such as:
1. Name
2. Phone number
3. Email address
4. Telegram Handle
5. Nearest MRT station to home
6. Modules taken in current NUS Semester
7. Groups

<img src="images/UG_ui_pictures/Profile.svg" style="width:40%;margin:1% 1% 1% 30%">

<center>Diagram 8: Sample user profile displayed on Profile Panel.</center>

### Scheduled Meet Up Panel

The Scheduled Meet Up Panel (located directly to the right of [Profile Panel](#profile-panel)) will show your confirmed meetings with your contacts.

<img src="images/UG_ui_pictures/ScheduledMeetUpPanel.svg" style="width:40%;margin:1% 1% 1% 30%">

<center>Diagram 9: Sample scheduled meet ups displayed.</center>
<br>
<div markdown="block" class="alert alert-info">

:information_source: **Components of Scheduled Meet Up Panel**<br>
The panel contains all the information of every scheduled meet up with your contacts as shown below.
<br>

<img src="images/UG_ui_pictures/ScheduledMeetUpPanelLabelled.png" style="width:40%;margin:1% 1% 1% 30%">

<center>Diagram 10: Components of the Scheduled Meet Up Panel</center>
</div>

### Command Response Box

The Command Response box is where you will receive feedback for any command you execute. It will provide feedback on command execution status (e.g success, invalid format).

The Command Response box is located directly below the [Profile Panel](#profile-panel) and above the [Command Box](#command-box).

<br>

<img src="images/UG_ui_pictures/command_feedback.png" style="width:80%;margin:0 10%">

<center>Diagram 11: Successful command execution feedback shown in red</center>

### Command Box

The Command Box is where you can input the [Commands](#commands) when using `EduMate`.

The Command Box is located at the bottom of the application. You may refer to the [User Interface](#user-interface) section to locate it.

<img src="images/UG_ui_pictures/command_input_box.png" style="width:80%;margin:0 10%">

<center>Diagram 12: Command input box in <code>EduMate</code> boxed in red</center>

<br>

<div markdown="span" class="alert alert-success">

:bulb: **Pro Tip:** <br>
Did you know that the **UP** and **DOWN** arrow keys can be used to quickly access previously typed commands in the Command Box?
You can now **save time and increase your productivity!**<br>
<br>

<code>EduMate</code> saves up to 100 of your most recent commands to a file for your convenience when you relaunch the application as well!
That way, you can reuse commands from your previous session with ease!

</div>

### Help Button

Should you need help with command syntax whilst using `EduMate`, you may click the Help Button located at the bottom left of `EduMate` ([see
 Breakdown of `EduMate`'s UI](#breakdown-of-edumates-ui)).

Clicking the Help Button is an alternative to the `help` command.

Both commands will result in a Help Window appearing which shows the various command syntax used by  `EduMate`.

<img src="images/UG_ui_pictures/HelpWindow.svg" style="width:40%;margin:1% 1% 1% 30%">

<center>Diagram 13: Help Window of <code>EduMate</code></center>

### Exit Button

Clicking the Exit Button will allow users to quit `EduMate`.

[Return to the top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Set Your Own Profile

Once you have familiarized yourself with EduMate, you are now ready to begin customizing the application to suit your preferences and needs!

**Step 1.** Run `java -jar edumate.jar`. The application window will open and show you where you have left off
(if you have tried out `sample` from earlier) <br>

**Step 2.** On the Command Box, execute `edit n/[YOUR NAME] p/[YOUR CONTACT NUMBER] t/@[YOUR TELEGRAM HANDLE] 
s/[NEAREST MRT STATION TO YOUR HOUSE] e/[YOUR EMAIL]`
<br>

<div markdown="block" class="alert alert-info">

:information_source: To know more about what `n/`, `e/` etc. symbolises, you may want to check out [Prefixes](#prefixes).

</div>
<div markdown="span" class="alert alert-success">

:bulb: **Tip:** <br>
You do not have to enter all your information at once! You can omit some information
and **its corresponding prefix** and enter them in the future!
<br>


Moreover, EduMate will replace the missing fields with placeholders which you
can then edit over later!

</div>

**Step 3.** To add the modules that you are studying as such :
`tag m/[MODULE_CODE] DAY START_TIME END_TIME`
<br>
<div markdown="span" class="alert alert-success">

:bulb: **Tip:** <br>
E.g `tag m/CS2103T MON 8 10 m/CS2101 WED 14 15`

</div>

**Step 4.** To add groups that you currently belong to as such: 
`tag g/[GROUP_NAME]`
<div markdown="span" class="alert alert-success">

:bulb: **Tip:** <br>
E.g `tag g/SoC g/Sailing`

</div>


**Step 5.** You are done! Enjoy! <br>


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
  e.g. in `view n/NAME`, you can fill in the `NAME` argument like `view n/Amirul Thani`.

* We use `z/` to denote any prefix.<br>
  e.g. `z/FIELD` could mean `n/FIELD` or `s/FIELD`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [g/GROUP]` can be used as `n/Amirul Thani g/Friend` or as `n/Amirul Thani`.

* Items with `...`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MODULE]...​` can be used as ` ` (i.e. 0 times), `m/`, `m/CS2108 m/CS2101` etc.

* Unless otherwise specified, arguments can be in any order.<br>
  e.g. if the command specifies `n/NAME s/STATION`, `s/STATION n/NAME` is also acceptable.

* If an argument is expected only once in the command, but you specified it multiple times, only the **last** occurrence of the argument will be taken.<br>
  e.g. `p/86544145 p/81353055` will be interpreted as `p/81353055`.

* Unnecessary arguments will be ignored.<br>
  e.g. `help 123` will be interpreted as `help`.

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Invalid Command Formats**<br>

* Indices must be positive numbers.<br>
  e.g. `delete one` is not an accepted command. (Not a number)<br>
  e.g. `delete -1` is also not accepted. (Not positive)


* [Prefixes](#prefixes) must be preceded by a space.<br>
  e.g. `sort m/an/Tan` is not an accepted command for prefixes `m/` and `n/`.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **General format of commands:** <br>

<img src="images/general_command_format.png" style="width:80%;margin:0 10%">

* **Bolded** portions of the command format implies that inputs are optional.

</div>

[Return to the top](#table-of-contents)

## Attributes

Contacts in EduMate have two types of attributes: single-valued and multi-valued.

Single-valued attributes for each contact can only have one value. Examples of single-valued attributes include a contact's name, phone number, email address, Telegram handle, and home station.

Multi-valued attributes, on the other hand, can have multiple values associated with a single contact. Examples of multi-valued attributes include the groups and modules that a contact belongs to.

### Basic Attributes

Below, you can find the basic attributes we use to make up a person's profile in `EduMate`.

#### **Basic Attributes Table**

| Attribute       | Description                                                    | Format           | Rules                                                                                                                                                                                                 | Example             |
|-----------------|----------------------------------------------------------------|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|
| Name            | The name of the person.                                        | `n/NAME`         | - Only contain alphanumeric characters and spaces. <br/> - **Must also be unique**.                                                                                                                   | `n/Wen Li`          |
| Phone Number    | The phone number of the person.                                | `p/PHONE_NUMBER` | - Should only contain numbers. <br/>- At least 3 digits long. Maximum of 15 digits allowed.                                                                                                           | `p/89229358`        | 
| Email Address   | The email address of the person.                               | `e/EMAIL`        | Should be of the form `local@domain`, where:<br/>- `local` consists of only alphanumeric and the special characters `+`, `_`, `.`, `-`.<br/>-`domain` consists of an `@` symbol followed by website.  | `e/wenli@gmail.com` |
| Home Station    | The name of the **closest MRT station** to the person's home.  | `s/STATION`      | Name of a valid MRT station.                                                                                                                                                                          | `s/Boon Lay`        |
| Telegram Handle | The Telegram Handle of the person.                             | `t/TELEGRAM`     | - Should start with `@` symbol <br/>- At least 5 characters long<br/>- Only combinations of letters, numbers and underscores are allowed after the `@` symbol                                         | `t/@wenli`          |

### Other Attributes

Below are some special attributes that complement the [Basic Attributes](#basic-attributes) of a person's profile.

#### **Group**

**Description:** The group that you and the person belong to.<br>

**Pattern:** `g/GROUP`<br>

**Rules:** `GROUP` should only contain alphanumeric characters.<br>

**Example:** `g/Groupmate`

#### **Module**

**Description:** The name of the module the person is taking.<br>

**Pattern:** `m/MODULE`<br>

**Rules:** `MODULE` should be the name of a valid NUS module.<br>

**Example:** `m/CS2107`

<div markdown="block" class="alert alert-info">

:information_source: Important!<br>
* EduMate's [Person List](#person-list) will only show common modules that you are enrolled in with the contact. Do not be alarmed that some modules you tagged did not
appear in the Person List.
* You may still view the label when you [view the contact's profile](#view-a-contacts-profile-view).
* When you tag (untag) a module common to you to (from) a contact, it will appear (disappear) on the person's contact card in the Person List automatically.

</div>

#### **Module with Lessons**

**Description:** The name of the module the person is taking, and a lesson associated with the module.<br>

**Pattern:** `m/MODULE DAY START_TIME END_TIME`<br>

**Rules:** `MODULE` should be the name of a valid NUS module. `DAY` should be a valid day of the week. `START_TIME` and `END_TIME` should be valid hour in a day.<br>

**Example:** `m/CS1234 mon 12 13`

## Basic Commands

These commands will help you to perform basic functions on EduMate. These are functions that will be expected to be present in a typical address book.

### Add a contact `add`

You can easily add a new contact to EduMate using the `add` command. This allows you to quickly store their information for future reference, such as their name, phone number, email address, Telegram handle, and the closest MRT station to their home. 

Once all the basic information has been added, you can then assign them to groups or modules using the `tag` command.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `add n/NAME [z/FIELD]...​`: Adds a contact with the given details. Notice that `NAME` is a compulsory field.

</div>

##### Scenario:

You want to create a new contact for a friend named Weixiang whose information does not exist in `EduMate` yet. 
You run the `add` command with his details following the above format.

Command entered: `add n/Weixiang p/89229358 s/Boon Lay e/heavenlywang@gmail.com t/@wangkong g/Groupmate m/CS2101 m/ES2660` 

What you should see:
1. Weixiang's contact is added towards the end of the Person List.
2. Weixiang's profile appears on the Profile Panel as such (below).
3. The Command Response Box shows that Weixiang is successfully added into `EduMate`.

<img src="images/ug_cmds_pics/addcommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 14: A new contact: Weixiang is added to <code>EduMate</code>.</center>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Example 1 : All fields are present**<br>
* `add n/Zen Li p/89229358 s/Boon Lay e/zenli@gmail.com t/@zenli g/Groupmate m/CS2101 m/CS2107`: Adds a contact with the following details:
    * Name: `Zen Li`
    * Phone number: `89229358`
    * Station: `Boon Lay`
    * Email: `zenli@gmail.com`
    * Telegram handle: `@zenli`
    * Groups: `Groupmate`
    * Modules: `CS2101`, `CS2107`
  
:bulb: **Example 2 : Some fields are present**<br>
* `add n/Zen Qing`: Adds a contact with the following details. Note the default values for each attribute:
    * Name: `Zen Qing`
    * Phone number: `00000000`
    * Station: `Kent Ridge`
    * Email: `zenqing@gmail.com`
    * Telegram handle: `@zenqing00000`
    * Groups: Empty
    * Modules: Empty

</div>

<div markdown="span" class="alert alert-info">

:information_source: **Important!**<br>
As you may have already see above, when adding a contact with some fields present, EduMate will give placeholders for the respective
missing information. <br>

<br>
Notice that for `Telegram handle`, there are 5 '0's appended at the back of the name (E.g Zen Qing results in `@zenqing0000`).
This is done to conform to Telegram's username restrictions of being at least 5 characters long for short names like Bob, Sam etc.

</div>

### View a contact's profile `view`

You can use the `view` command to retrieve and view either your own information or a contact's information. This will display the relevant details on the [Profile Panel](#profile-panel).

<div markdown="block" class="alert alert-info">

:information_source: **You can use this command if**:
* you want to view a contact's full details on the [Profile Panel](#profile-panel).
* you are unsure what index to use for a particular contact

</div>

The `view` command retrieves and display the either your profile or the contact's profile on the [Profile Panel](#profile-panel).

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `view`: Views your profile.
* `view INDEX`: Views the contact at index `INDEX`.
* `view n/FULL_NAME`: Views the contact with contact's full name `FULL_NAME`.

</div>

##### Scenario (Continuation from previous scenario):

As Weixiang is a new friend, and you are not familiar with him yet. You want to chat with him on Telegram but
cannot remember his Telegram handle. You decided to use the `view` command to view his profile.

Command entered: `view n/Weixiang` or `view 101` (if you know Weixiang's contact index)

What you should see:<br>

<img src="images/ug_cmds_pics/View Command.svg" style="width:80%;margin:0 10%">

<center>Diagram 15: Weixiang's profile displayed after execution of the <code>view</code> command</center>

<br>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `view` : Displays your profile.
* `view 5`: Displays the profile of the fifth contact in EduMate.
* `view n/Wen Li`: Displays Wen Li's profile.

</div>

### Edit a contact's details `edit`

With EduMate's `edit` command, you can easily update the profile of a contact. You have the flexibility to modify as many fields as required, ensuring that your contact's information is always up-to-date.

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>

* `edit INDEX [z/FIELD]...​`: Edits the fields for the contact at index `INDEX`.
* `edit [z/FIELD]...​`: Edits your user profile.

</div>

##### Scenario (Continuation from previous scenario):

Suppose now after talking to Weixiang, you realised that he has just moved to Little India. You then decide to edit the `Station` attribute of his contact from 
`Boon Lay` to `Little India`. You also remembered that from the `view` command, Weixiang's index in `EduMate` is 101.

Command entered: `edit 101 s/Little India`

What you should see:<br>

<img src="images/ug_cmds_pics/EditCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 16: MRT station nearest to Weixiang's home updated to Little India after execution of the <code>edit</code>command.</center>

<br>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>

* `edit 3 n/Zen Qing`: Changes the name of the third contact to `Zen Qing`.
* `edit 6 p/89229358 t/@zenqing`: Changes the phone number and telegram handle of the sixth contact.
* `edit s/Bedok`: Updates your address to `Bedok`.
</div>

### Delete a contact `delete`

If you no longer need a contact in EduMate, you can remove them using the `delete` command.

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `delete INDEX`: Deletes the contact at index `INDEX`.

</div>

##### Scenario :

Suppose you do not need Astrid's contact anymore and you want to delete her contact from `EduMate`.
From the `view n/Astrid Holland` command, her `EduMate` index is 8.

Command entered: `delete 8`

What you should see:<br>

<img src="images/ug_cmds_pics/DeleteCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 17: Astrid's contact deleted after execution of the <code>delete</code>command.</center>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Example**<br>
* `delete 5`: Removes the fifth contact from EduMate.

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Deleting your own profile**<br>

You cannot delete your own profile.

</div>

### Add a label to a contact `tag`

You can tag your existing contacts with groups and modules using this command. You can also assign lessons to the contacts to indicate their availability during specific time periods. This will enable EduMate to suggest more suitable meet up times when using the `meet` command.

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `tag m/MODULE_TAG`
* `tag m/MODULE_TAG DAY START END`
* `tag INDEX m/MODULE_TAG`
* `tag INDEX m/MODULE_TAG DAY START END`
* `tag g/GROUP`
* `tag INDEX g/GROUP`

</div>

##### Scenario:

Suppose you now have a senior, Amirul, taking MA2101. You are interested and might want to contact Amirul for some advice.
You then `tag` Amirul's contact in `EduMate` with the following Monday 4pm - 6pm lesson slot and its corresponding module code.

Amirul has index 6 in `EduMate`.

Command entered: `tag 6 m/MA2101 Monday 16 18`

What you should see:<br>

<img src="images/ug_cmds_pics/TagCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 18: Amirul successfully tagged with MA2101 with a lesson from 4pm - 6pm on a Monday</center>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Module Tag Examples**<br>

* `tag 1 m/CS1234`: Adds CS1234 tag to the first contact from EduMate.
* `tag m/CS1234` : Adds CS1234 tag to your own profile.
* `tag 2 m/CS2103T MONDAY 8 10` : Adds CS2103T tag (if not present yet) to your second contact with a Monday lesson from 8am - 10am.
* `tag m/CS2103T MONDAY 8 10` : Adds CS2103T tag (if not present yet) to your profile with a Monday lesson from 8am - 10am.

:bulb: **Group Tag Examples**<br>

* `tag 1 g/Cycling` : Adds Cycling tag to the first contact from EduMate.
* `tag g/Project` : Adds Project tag to your own profile.

</div>

### Remove a label from a contact `untag`

With EduMate's `untag` command, you can remove groups or modules from your existing contacts. Additionally, you can unassign lessons from a contact to indicate their availability during that specific period of time. This will help EduMate to suggest more appropriate meet up times when using the meet command.

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `untag m/MODULE_TAG`
* `untag m/MODULE_TAG DAY START END`
* `untag INDEX m/MODULE_TAG`
* `untag INDEX m/MODULE_TAG DAY START END`
* `untag g/GROUP`
* `untag INDEX g/GROUP`

</div>

##### Scenario (Continuation from previous scenario):

You are now informed by Amirul that MA2101 is too hard and he has dropped the module. You then proceed to 
`untag` that module from Amirul's contact.

Command entered: `untag 6 m/MA2101`

What you should see:<br>

<img src="images/ug_cmds_pics/UntagCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 19: Amirul after removing the MA2101 Module Tag.</center>

<br>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Module Tag Examples**<br>

* `untag 1 m/CS1234`: Removes CS1234 tag from the first contact from EduMate.
* `untag m/CS1234` : Removes CS1234 tag from your own profile.
* `untag 2 m/CS2103T MONDAY 8 10` : Removes CS2103T tag (if it exists) from your second contact with a Monday lesson from 8am - 10am.
* `untag m/CS2103T MONDAY 8 10` : Removes CS2103T tag (if it exists) from your profile with a Monday lesson from 8am - 10am.

:bulb: **Group Tag Examples**<br>

* `untag 1 g/Cycling` : Removes Cycling tag from the first contact from EduMate.
* `untag g/Project` : Removes Project tag from your own profile.

</div>

### Listing all your contacts `list`

With the `list` command, you can get EduMate to display all your contacts in the [Person List](#person-list)

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `list`

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tips:** `list` is especially useful when<br>

- You need to view all contacts again after [`find`](#filter-contacts-by-keywords-find) and [`sort`](#arrange-contacts-based-on-criteria-sort).
- Find out the index of the contact you want to view on the [Person List](#person-list).

</div>


## Advanced Commands

Now that you have become familiar with the basics of EduMate, it's time to explore its more advanced features. For a more guided experience, you can run the `sample 100` command to navigate through this section.

### Search Commands

The search commands in EduMate help you to efficiently navigate and manage your contacts. Instead of manually searching through a long list of contacts, these commands allow you to customise how EduMate displays them, making the process much more efficient.

#### Filter contacts by keywords `find`

EduMate's `find` command helps you quickly locate specific contacts by searching for keywords within their fields. This tool allows you to generate a list of contacts that meet certain criteria and manage your contacts more efficiently. 

To use the `find` command, simply type `find` followed by the relevant keywords or criteria. 

The command can also be used with multiple prefixes to refine your search. The results returned will contain at least one keyword from each specified field. Use the `find` command to save time and easily locate the information you need.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `find z/KEYWORD [MORE_KEYWORDS]... z/KEYWORD [MORE_KEYWORDS]...`

</div>

##### Scenario:

Say you want to filter out contacts who are in the same CCA as you to study for the upcoming finals for CS2100.

Command entered: `find g/CCA m/CS2100`

What you should see:<br>

<img src="images/ug_cmds_pics/FindCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 20: Only contacts tagged with CCA and CS2100 are shown.</center>

<br>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>

* `find m/CS2103T CS2109S`: Returns all persons with modules CS2103T or CS2109S
* `find n/Edward Richards`: Returns all persons with names Edward or Richards
* `find p/9093`: Returns all persons with phone numbers that contain 9093
* `find n/Edward m/CS2103T` : Returns all persons with name Edward and module CS2103T
* `find n/Edward Richards m/CS2103T CS2109S` : Returns all persons named Edward or Richards with module CS2103T or CS2109S.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Tips on usage**<br>
* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the field specified by the prefix is searched. e.g. n/ means only the name field is searched
* Words matching the first part of the string will be matched e.g. Han will match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

</div>

<div markdown="block" class="alert alert-danger">

**:warning: Filtered list**<br>

* The `find` command returns a filtered list, so any contacts present in the filtered list will be unaffected by commands that have the capability to modify a contact's information.<br>
  e.g. If the filtered list does not contain any person with contact index 2, any operation relating to contact index 2 will throw an error.
* If you want to run commands related to persons not in the filtered list, just run the `list` command to obtain the full list of your contacts.

</div>


#### Arrange contacts based on criteria `sort`

With EduMate's `sort` command, you have the power to organise your contacts in a way that's most helpful for you. Whether you want to find out who shares the most modules with you or who's likely to become a close friend this semester, the `sort` command can help you out. 

By simply typing `sort` followed by the relevant criteria, you can sort your contacts by name, the number of shared modules, or any other specified field. This feature can help you identify potential study or social groups within your contacts, making it easier to connect and collaborate with others.


<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `sort`: Sorts the contacts by their indices.
* `sort z/a`: Sorts the contacts by their `z` attribute **in ascending order**
* `sort z/d`: Sorts the contacts by their `z` attribute **in descending order**
* `sort z/`: Sorts the contacts by their `z` attribute based on our **default ordering**
* `sort z1/ z2/`: Sorts the contacts by their `z1` attribute, and breaks ties using their `z2` attribute
* `sort [z/]...​`: Sorts the contacts by multiple attributes

</div>

<br>

<div markdown="block" class="alert alert-info">

:bulb: **Accepted versions for sort order**: <br>
In short, in the command formats `z/a` and `z/d`, we only care about the **first letter** immediately after the slash. It can be either in upper case or lower case and can have anything behind it. For example, `z/ascending` and `z/Disco` would both work. <br><br>

However, if the first letter is not either `a`, `d`, `D` or `A`, then we use the **default ordering**.

</div>
<br>

We will now teach you how to effectively use the `sort` command.

<div markdown="block" class="alert alert-info">

:information_source: **How the attributes are sorted**:

* The `NAME`, `EMAIL`, `STATION`, `PHONE` and `TELEGRAM` attributes are sorted by alphabetical order, and is default in **ascending** order.
* For `GROUP`, we sort the contacts based on the number of groups the contact belongs to. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 groups (TA, NS), and Ben has one group (TA), Alex will be placed before Ben in the default ordering.
* For `MODULE`, we sort the contacts based on the number of **common modules** they share with you. By default, we sort in **descending order**.<br>
  e.g. if Alex has 2 modules (CS1101S, CS1231S), Ben has one module (CS1101S), and you have 2 modules (CS1231S, MA2001), then Alex has 1 module in common with you (CS1231S) and Ben has 0 modules in common with you. As such, Alex will be placed before Ben in the default ordering.

</div>

`sort` is one of the most complicated commands to get your head around. Perhaps a scenario can help you understand better!

##### Scenario (Continuation from previous scenario):

Suppose you want to arrange contacts such from those that take the most number of common modules as you to the least. 

If there is a tie in the number of common modules shared with you, you decide to just rank the name lexicographically.

Command entered: `sort m/d n/a`

What you should see:<br>

<img src="images/ug_cmds_pics/SortCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 21: Contacts ranked according to sorting criteria specified in the Command Box</center>

<br>

##### Extra Explanation:

The command `sort m/d n/a` would mean that `EduMate` would first sort contacts by decreasing number of common modules with you, and tie-break by its lexicographical ordering.

Hence, Alex Quinn is ranked higher than Amirul as Alex's name is lexicographically smaller than Amirul even though they share the same common modules as you.

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `sort n/d`: Sorts by name in descending order
* `sort m/a`: Sorts by the number of modules they have in common with you (in ascending order)
* `sort t/`: Sorts by Telegram handle in its default ordering (ascending)

</div>


### Storage Commands

The storage commands allow you to save and load your EduMate data into a file. This means you can easily backup your data and access it from different devices or share it with others.

<div markdown="block" class="alert alert-info">

:information_source: **You can use these commands if**:
* You want to create backups of your EduMate
* You want to explore the functions of EduMate without fear of losing your data
* You want to maintain multiple versions of EduMate

</div>

#### Save a copy of EduMate `save`

Using the `save` command in EduMate, you can easily save your data into a file with a name of your choice. This allows you to back up your data and access it later or share it with others.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `save FILENAME`: Saves the EduMate into a file at `FILENAME.json`.

</div>


<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `save backup`: Saves the EduMate in the `backup.json` file.

</div>

#### Load a copy of EduMate `load`

Using the `load` command in EduMate, you can easily load a previously saved data file into the application. This allows you to access your data from different devices or recover it in case of data loss.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `load FILENAME`: Loads the EduMate from a file at `FILENAME.json`.

</div>

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `load backup`: Loads the EduMate from the `backup.json` file.

</div>

### Meet Commands

#### Suggest places to meet with your contacts `meet`, `study`, `eat`

The `meet` command in EduMate is used to suggest suitable meet-up locations and times for users and their contacts, based on various criteria such as availability and proximity. It is useful for finding a mutually convenient time and place to meet up with friends or study partners. EduMate also offers more specialized commands such as `eat` for suggesting places to eat and `study` for suggesting places to study.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `meet INDEX_1 INDEX_2 ...`
* `study INDEX_1 INDEX_2 ...`
* `eat INDEX_1 INDEX_2 ...`

</div>

##### Scenario (Continuation from previous scenario):

After sorting your contacts, you decided to ask Tae Seong, Alex, Amirul and Kevin from your CCA to study for CS2100. 

Problem is, you do not know when all of them are free! Luckily, you have `EduMate` to find out for you!

You can execute the `study` variant of the `meet` command
whilst referring to their index on the Person List.

From here, `EduMate` will recommend the most optimal places and the best possible time to meet and study for the final exam.

Command entered: `study 35 29 10 23`

What you should see:<br>

<img src="images/ug_cmds_pics/MeetCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 22: Recommended study spots and the common available days and timings displayed</center>

<br>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `meet 12 32 8 4` : Recommends some meeting times and locations for a potential meetup with friends with the following indices `12, 32, 4, 8`.
* `eat 5 2 8 1 3` : Recommends some meeting times and eating spots for friends with the following indices `5, 2, 8, 1, 3`
* `study 6 2` : Recommends some meeting times and study spots for friends with the following indices `6 and 2`.

</div>

### Schedule Commands

The `organise` command in EduMate is used to add meet ups with selected contacts, whether they are customised or recommended by the meet commands. The feature is useful for keeping track of your upcoming meetups in an organised and efficient way. 

In addition, EduMate offers the `unorganise` command, which helps you manage and clean up scheduled meetups by removing them - making it easy to stay on top of your schedule.

Any organised meet ups will be displayed at `EduMate`'s [Scheduled Meet Up Panel](#scheduled-meet-up-panel).

<div markdown="span" class="alert alert-danger">

:warning:**Common Mistake**<br>

The American spellings, `organize` and `unorganize` are not accepted by `EduMate`.

</div>


#### Organise a meet up `organise`

<div markdown="block" class="alert alert-info">

:information_source: **Formats** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `organise INDEX`
* `organise INDEX_1 INDEX_2 ... d/DAY T/START_TIME END_TIME l/LOCATION`

</div>

##### Scenario (Continuation from previous scenario):

You have decided that Queenstown Public Library is the most conducive library and have confirmed with your friends 
the meeting time recommended by `EduMate`. 

The recommendation given by `EduMate` for your preferred meet up is 2.

Command entered: `organise 2`

What you should see:<br>

<img src="images/ug_cmds_pics/OrganiseCommand.svg" style="width:80%;margin:0 10%">
<center>Diagram 23: A new meet up: Thursday at Queenstown Public Library from 8pm - 11pm is added to <code>EduMate</code>'s Scheduled Meet Up Panel.</center>

##### More Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Example 1 : Adding a meet up from the recommendation list**<br>
* `organise 1`: Adds the recommendation with index 1 on the [Meet Up Recommendation Panel](#meet-up-recommendation-panel) to the [Scheduled Meet Up Panel](#scheduled-meet-up-panel).

:bulb: **Example 2 : Adding a customised meet up**<br>
* `organise 1 3 d/TUESDAY T/10 11 l/Starbucks`: Adds a customised meet up with the following details to the [Scheduled Meet Up Panel](#scheduled-meet-up-panel):
  * Participants: `contacts with index 1 and 3`
  * Day: `TUESDAY`
  * Start time: `10AM`
  * End time: `11AM`
  * Location: `Starbucks`

</div>

#### Unorganise a meet up `unorganise`

If you wish to remove a scheduled meetup from your [Scheduled Meet Up Panel](#scheduled-meet-up-panel), simply use the `unorganise` command.

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `unorganise INDEX`

</div>


##### Scenario (Continuation from previous scenario):

Suppose Tae Seong cannot attend the meet up due to contracting Covid-19, and you have to cancel the scheduled meet up.

The scheduled meet up has the index 1 on the [Scheduled Meet Up Panel](#scheduled-meet-up-panel).

Command entered: `unorganise 1`

What you should see: <br>

<img src="images/ug_cmds_pics/UnorganiseCommand.svg" style="width:80%;margin:0 10%">

<center>Diagram 24: The Thursday study session at Queenstown Public Library from 8pm - 11pm is deleted from <code>EduMate</code>'s Scheduled Meet Up Panel.</center>


### Other Commands

#### Sample some contacts `sample`

<div markdown="block" class="alert alert-info">

:information_source: **Format** (See [Interpreting Command Formats](#how-to-interpret-the-command-format)):<br>
* `sample NUMBER_OF_CONTACTS`

</div>

This command was built to help you and other new users get acquainted with `EduMate` functions. 

`sample` helps to populate `EduMate` with fake profiles, so you can experiment with the commands without affecting your real contacts.


<div markdown="span" class="alert alert-info">

:information_source: **Limit of <code>sample</code>**<br>

You can only sample at most 100 contacts into `EduMate`.

</div>


<div markdown="span" class="alert alert-danger">

:warning: **Warning**<br>

Do not execute <code>sample</code> after you have started using <code>EduMate</code> properly yourself. Doing so will clear all your contacts!

</div>

##### Examples of Usage:

<div markdown="block" class="alert alert-success">

:bulb: **Examples**<br>
* `sample 25`: Randomly sample 25 contacts from within `EduMate` and populate them into the application.

</div>

#### Clear the application `clear`

If you want to delete all your contacts from `EduMate`, simply type `clear`.

<div markdown="span" class="alert alert-danger">

:warning: **Warning**<br>

After the <code>clear</code> command is executed, the data that has been deleted will not be recoverable.

</div>


#### Exit the application `exit`

If you want to end the application, simply type `exit`, or click on the `Exit` button located on the left of the interface.

---

## FAQ

**Q**: Can I add multiple module tags to a user

**A**: Yes, you can add multiple tags to a single user by using the "Module Tagging" feature multiple times.


**Q**: Can I edit my own profile?

**A**: Yes you may! Refer to [Editing a Contact's Details](#edit-a-contacts-details-edit)

**Q**: How is `EduMate` better than Microsoft Outlook or uWave?

**A**: We have features that Microsoft Outlook or uWave don't have but we think should be integrated into these platforms, like the [meet up recommenders](#meet-commands)
and several search functions like [sort](#arrange-contacts-based-on-criteria-sort) and [find](#filter-contacts-by-keywords-find). It is also open-source so student developers can 
suggest new features, and it is easily extensible!

**Q**: What is the difference between `tag` and `edit` feature when it comes to tagging groups and modules?

**A**: When you `edit` a profile with the `m/` prefix, the existing modules of the person gets entirely deleted and the new module is added. `tag` however, just adds the module for you without modifying other existing tags. The same explanation could be applied to `untag` as well.



## Summary

### Prefixes

| Prefix | Representation  |
|--------|-----------------|
| `n/`   | Name            |
| `s/`   | Station         | 
| `p/`   | Telegram Handle | 
| `e/`   | Email           | 
| `g/`   | Group           | 
| `m/`   | Module          | 
| `d/`   | Day             |
| `l/`   | Location        |
| `T/`   | Time Period     |
| ` `    | Empty           | 

### Command Summary

| Action                   | Format (See [Interpreting Command Formats](#how-to-interpret-the-command-format))      | Examples                                                       |
|--------------------------|----------------------------------------------------------------------------------------|----------------------------------------------------------------|
| **Add a contact**        | `add n/NAME p/PHONE...`                                                                | `add n/Wen Li...`                                              |
| **View a profile**       | `view`, `view INDEX`, `view n/NAME`                                                    | `view 5`, `view n/Wen Li`                                      |
| **Edit a contact**       | `edit INDEX [z/FIELD]...​`, `edit [z/FIELD]...​`                                       | `edit 4 n/Wen Qing`, `edit s/NUS t/@wenqing`                   |
| **Delete a contact**     | `delete INDEX`                                                                         | `delete 3`                                                     |
| **Tag a contact**        | `tag INDEX m/MODULE...`, `tag m/MODULE...`, `tag INDEX g/GROUP`, `tag g/GROUP`         | `tag m/CS1234`, `tag m/CS2345 mon 12 1`, `tag 1 g/Friend`      |
| **Untag a contact**      | `untag INDEX m/MODULE...`, `untag m/MODULE...`, `untag INDEX g/GROUP`, `untag g/GROUP` | `untag m/CS1234`, `untag m/CS2345 mon 12 1`, `untag 1 g/Friend` |
| **Filter contacts**      | `find z/FIELD... z/FIELD...`                                                           | `find n/Tan`, `find m/CS1231`, `find n/Tan m/CS1231`           |
| **Sort contacts**        | `sort [z/a]...​`, `sort [z/d]...​`, `sort [z/]...​`                                    | `sort`, `sort n/a`, `sort m/ p/d`                              |
| **Save a copy**          | `save FILE_NAME`                                                                       | `save backup`                                                  |
| **Load a copy**          | `load FILE_NAME`                                                                       | `load backup`                                                  |
| **Suggest meet ups**     | `meet [INDEX]...​`, `eat [INDEX]...​`, `study [INDEX]...​`                             | `meet 1 6 4`, `eat 10 4 7`                                     |
| **Organise a meet up**   | `organise INDEX`, `organise [INDEX]... d/DAY T/START_TIME END_TIME l/LOCATION`         | `organise 1`, `organise 1 2 3 d/MONDAY T/10 12 l/NUS`          |
| **Unorganise a meet up** | `unorganise INDEX`                                                                     | `unorganise 1`                                                 |


## Troubleshooting

### How to check your Java version

#### For **Windows** Users
**Step 1.** Open up Command Prompt.

**Step 2.** Run the command `java -version`.

**Step 3.** Check the version number from the output.

#### For **Mac** and **Linux** Users
**Step 1.** Open up Terminal.

**Step 2.** Run the command `java -version`.

**Step 3.** Check the version number from the output.

