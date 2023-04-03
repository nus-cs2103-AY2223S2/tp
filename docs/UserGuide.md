---
layout: page
title: HMHero User Guide
---

## Table of Contents


1. [About HMHero](#1-about-hmhero)
2. [Features](#2-features)
   * [Applicant Management System](#21-applicant-management-system)
   * [Hiring Process Analytics](#22-hiring-process-analytics)
3. [How to use this User Guide](#3-how-to-use-this-user-guide)
   * [Icons and Hyperlinks](#31-icons-and-hyperlinks)
   * [Purpose of User Guide](#32-purpose-of-user-guide)
4. [Installation](#4-installation)
5. [Quick Start](#5-quick-start)
   * [User Interface](#51-user-interface)
   * [Key Definitions](#52-key-definitions)
     * [Applicant](#521-applicant)
     * [Notes](#522-notes)
     * [Flags](#523-flags)
     * [Placeholders](#524-placeholders)
   * [Command Format](#53-command-format)
   * [Trying your First Command](#54-trying-your-first-command)
6. [Commands](#6-commands)
   * [Applicant Commands](#61-applicant-commands)
     * [Create a new applicant `add`](#611-create-a-new-applicant-add)
     * [Search for an applicant `find`](#612-search-for-an-applicant-find)
     * [List all applicants `list`](#613-list-all-applicants-list)
     * [Delete an applicant `delete`](#614-delete-an-applicant-delete)
     * [Advance an applicant `advance`](#615-advance-an-applicant-advance)
     * [Reject an applicant `reject`](#616-reject-an-applicant-reject)
     * [List all interview dates of applicants `interview`](#617-list-all-interview-dates-of-applicants-interview)
     * [Edit an existing applicant `edit`](#618-edit-an-existing-applicant-edit)
     * [Remind upcoming interviews `remind`](#619-remind-upcoming-interviews-remind)
   * [Statistic Commands](#62-statistics-commands)
   * [General Commands](#63-general-commands)
     * [Receive help during usage `help`](#631-receive-help-during-usage-help)
     * [Exit HMHero `exit`](#632-exit-hmhero-exit)
7. [Command summary](#7-command-summary)
   * [Applicant Commands](#71-applicant-commands)
   * [Statistics Commands](#72-statistics-commands)
   * [General Commands](#73-general-commands)
8. [Troubleshooting](#8-troubleshooting)
9. [FAQ](#9-faq)
10. [Acknowledgements](#10-acknowledgements)
11. [Glossary](#11-glossary)

---

# 1. About HMHero

Have you ever found it difficult to manage the constant influx of applicants during each application cycle?
Have you ever found it strenuous filtering through countless applicants to find those that fit specific job requirements?
HMHero is here to help!

## Overview of key features

There are two core features that HMHero provides:

1. Applicant Management System
2. Hiring Process Analytics



### Applicant Management System

HMHero empowers you to manage applicants during hiring process.<br>
You can:
1. Easily add, edit, delete, and view your applicants.
2. Quickly advance applicants through application statuses or reject them.
3. Filter shortlisted applicants and sort them by interview date.
4. Filter all applicants based on their skill-sets.
5. View all applicants that have interviews coming up in three days.



### Hiring Process Analytics

HMHero provides analysis on your hiring process. <br>
You can:
1. Find out how many applicants are accepted out of total applicants.
2. Find out the average time taken to process each applicant.


[Back to Table of Contents](#table-of-contents)

---

## 3. How to use this User Guide

### 3.1 Icons and Hyperlinks

You may come across colored boxes containing text that will aid you in better understanding how to use each feature.

<div markdown="span" class="alert alert-info" role="alert">:information_source: <strong>Info:</strong> <br>
Highlight and display information you should pay attention to. </div>


<div markdown="span" class="alert alert-success" role="alert">:bulb: <strong>Tip:</strong> <br>
Highlight tips which you might find useful. </div>


<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong> <br>
Highlight dangers and things to look out for. </div>


Other than the icons mentioned above, you maye also come across <a href> Phrases coloured in blue </a> are hyperlinks
that will bring you to another part of this documentation that is relevant to the phrase.

[Back to Table of Contents](#table-of-contents)

---

### 3.2 Purpose of User Guide

If you are a **new user**, head over to the [Installation](#4-installation) section to download the application.


<div markdown="span" class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
We highly recommend that you read through this User Guide in **sequential order** to get a thorough understanding
of how to use HMHero.
</div>


Next, you can find the necessary information to get started in the [Quick Start](#5-quick-start) section!

If you are an **experienced user**, you may head over to the [Command Summary](#7-command-summary) to get a quick overview
of all the commands available in HMHero!

If you are stuck, please refer to the section on [Troubleshooting](#8-troubleshooting) or [FAQ](#9-faq).

You can also refer to the [Glossary](#11-glossary) for definitions of commonly used terminologies in HMHero.

<div markdown="span" class="alert alert-success" role="alert">:bulb: <strong>Tip:</strong> <br>
To aid in your navigation, we have included the a <a href="#table-of-contents">Back to Table of Contents<a/> link in every
sub-section in this User Guide so that it is easy for you to jump across different sections!
</div>


[Back to Table of Contents](#table-of-contents)

---

## 4. Installation

1. Ensure you have downloaded `Java 11` or above installed in your computer. If you have not, you may download it [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).
2. Download the latest `HMHero` from [here](https://github.com/AY2223S2-CS2103T-W14-4/tp/releases).
3. Copy the file to an empty folder. This folder is where all the information needed for the HMHero application
will reside in.
4. Double-click on the jar file to launch HMHero.
5. A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.

<img src="images/ui.png" alt="UI image" width="500" height="400">


<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong> <br>
Ensure that the file is added to an empty folder as additional data and configuration files will be created when HMHero
is launched for the first time in your computer!
</div>

<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong> <br>
You will notice that a folder called "data" would have been created in the same folder as <code>hmhero.jar</code>.
Do not edit this file because doing so might result in a corruption of your data, causing the application to fail!
</div>

The following section on [Quick Start](#5-quick-start) is a tutorial on how to use HMHero
after you have launched it for the first time. Feel free to skip to the next section on [Commands](#6-commands) if you
are already familiar with the application!

[Back to Table of Contents](#table-of-contents)

---

## 5. Quick Start
This section covers everything you should know about HMHero, as well as a tutorial on 
[trying your first command](#54-trying-your-first-command). The special note is the 
[Key Definitions](#52-key-definitions) and [Command Format](#53-command-format) sections, 
which covers essential knowledge to using HMHero's features.


### 5.1 User Interface
When you launch HMHero, HMHero appears on your screen as a Graphical User Interface(GUI). Let's explore the
layout of the different components of HMHero.

HMHero's GUI consists of a single main window consisting of 3 main sections.
1. [Command Input Box](#command-input-box)
2. [Command Output Box](#command-output-box)
3. [Applicant List Box](#applicant-list-box)

The following picture of the main window shows the three components, numbered accordingly:
![components.png](images%2Fcomponents.png)

Besides the main window, HMHero also has the Help Window. It is not part of the main GUI and is only
shown after a [Help Command](#631-receive-help-during-usage-help) is run.

The Help Window looks like the following:

![help_window.png](images%2Fhelp_window.png)

[Back to Table of Contents](#table-of-contents)

---

### 5.2 Key Definitions

#### 5.2.1 Applicant

An applicant in HMHero represents an individual that applied for a job at your company. HMHero tracks various
attributes of an applicant. Compulsory attributes would include their name, phone number, address and email.

The following are the attributes stored for each `Applicant`
* Name
* Phone Number
* Address
* Email
* Application Date
* Interview Date
* Notes

Applicants are unique by name and phone number and are Case Sensitive.
This means you cannot add two or more applicants of the same name and phone number.



[Back to Table of Contents](#table-of-contents)

---

#### 5.2.2 Notes

A note in HMHero serves as a means of labelling the skills of an applicant. These tags
are unique and case-sensitive.

We can tag multiple applicants with the same note and each applicant can have multiple notes. These notes
are optional.

Feel free to make use of notes as you fit to highlight skill-sets of an applicant. Examples of such notes
can include:
* Highlight applicant's:
  * Technical proficiency, e.g. `Python`, `Java`, `Flask`
  * Soft Skills, e.g. `Project Management`, `Event Planning`

HMHero's Notes are unique by name and are case-sensitive. This means you cannot add two or more notes of
the same name.

[Back to Table of Contents](#table-of-contents)

---

#### 5.2.3 Flags
Flags are delimiters that enable HMHero to distinguish different parameters without ambiguity.

You could put in the corresponding [Placeholder](#524-placeholders) immediately after each flag.

<div markdown="span" class="alert alert-success" role="alert">:bulb: <strong>Tip:</strong> <br>
You may find the following image taken from the subsequent <a href="#53-command-format"> Command Format </a> section helpful:
<img src="./images/CommandExample.png">
</div>

Placeholders in this User Guide refers to the UPPER_CASE words that can be replaced by valid user input supplied.
These placeholders follow immediately after a [Flag](#523-flags).

| Action   | Corresponding Placeholder |
|----------|---------------------------|
| n/       | `NAME`                    |
| p/       | `PHONE`                   |
| e/       | `EMAIL`                   |
| a/       | `ADDRESS`                 |
| note/    | `NOTE`                    |
| d/       | `INTERVIEW DATETIME`      |
| applied/ | `APPLICATION DATETIME`    |

Please refer to the subsequent [Command Format](#53-command-format) section to see how [Flags](#523-flags)
and [Placeholders](#524-placeholders) are used together.

[Back to Table of Contents](#table-of-contents)

---

#### 5.2.4 Placeholders
Placeholders in this User Guide refers to the UPPER_CASE words that can be replaced by valid user input supplied.
These placeholders follow immediately after a [Flag](#523-flags).

Please refer to the subsequent [Command Format](#53-command-format) 
section to see how Flags and Placeholders are used together.

| Placeholder  | Corresponding Flag | Description                                                                                  |
|--------------|--------------------|----------------------------------------------------------------------------------------------|
| INDEX        | (Not Applicable)   | The INDEX of an item is the number to the left of the applicant's name in the Item List Box. |
| NAME         | n/                 | The NAME is the name we use to identify an Applicant.                                        |
| PHONE        | p/                 | The PHONE is the text we use to represent the phone number of the Applicant.                 |
| EMAIL        | e/                 | The EMAIL is the text we use to represent the email of the Applicant.                        |
| NOTE         | note/              | The NOTE is the term we use to identify a skill of an Applicant.                             |
| DATETIME     | d/                 | The DATETIME is the datetime indicating the interview datetime of the Applicant.             |

[Back to Table of Contents](#table-of-contents)

---

### 5.3 Command Format
You will encounter HMHero commands throughout this User Guide.
Before you delve into the different commands in Commands, let’s learn what a command consists of.

Here is an example:
![CommandExample.png](images%2FCommandExample.png)
A command consists of:
Command Word: Tells HMHero what action you wish to execute. These actions are covered in [Commands](#6-commands).<br>
[Flags](#523-flags): Distinguishes between inputs. A flag is usually followed by a placeholder.<br>
[Placeholders](#524-placeholders): Represents data that you wish to input. Replace this with valid data.<br>
For example, `NAME` in `n/NAME` can be replaced with `n/John`.

[Back to Table of Contents](#table-of-contents)

---


### 5.4 Trying your First Command

To let you become more familiar with HMHero, let's practise executing some commands.

To start off, let's try out the `add` command! This command lets you add an [applicant](#521-applicant) to HMHero.

One of the available commands in HMHero is the command to create a new applicant.

**Format:** `add n/NAME p/PHONE e/EMAIL a/ADDRESS [note/NOTE]`
<br>
<br>

**What does the format mean?**
* `add` tells HMHero that this is the command to create a new applicant
* [Flags](#523-flags) such as `n/` and `p/` are delimiters that enable HMHero to distinguish different
parameters supplied by you without ambiguity
* [Placeholders](#524-placeholders) such as `NAME` and `PHONE` shows you what you should place in each portion of the 
command

Notice that there is a pair of square brackets `[]` surrounding some parameters like `note/NOTE`
in the format. This indicates that the parameter is **optional**. Each of these placeholders in the parameters 
have a default value based on the commands. These are documented in the [Commands](#6-commands) section for each command.


<div markdown="span" class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
The <a href="#524-placeholders">Placeholder</a> section covers the restrictions for respective placeholders. For example, 
the format of PHONE, you must have at least three integers and cannot use characters. 
</div>

**Let's try an example!**

Suppose you just add Thomas, 91918153, thomas@gmail.com, living at 6 Sims Drive (s)543230, and you do not
feel the need to record a note for this applicant.

`NAME`: Thomas

`PHONE`: 91918153

`EMAIL`: thomas@gmail.com

`ADDRESS`: 6 Sims Drive (s)543230

The command you would like to enter into the command box would be:

```
add n/Thomas p/91918153 e/thomas@gmail.com a/6 Sims Drive (s)543230
```
<br>

Alternatively, executing these would do the same thing:

* `add n/Thomas e/thomas@gmail.com p/91918153 a/6 Sims Drive (s)543230`

  This is because the order of the flags does not matter.
<br> <br>

* `add n/Thomas p/91918153 e/thomas@gmail.com n/Sally p/97833468 a/6 Sims Drive (s)543230`

    In this case, the name "Thomas" will be overridden by "Sally", and the phone "91918153" 
will be overridden by "97833468".
<br> <br>

However, note that the following executions are invalid:

* `add n/Thomasp/91918153e/thomas@gmail.coma/6 Sims Drive (s)543230`

  There must be spacings between the placeholders and flags.

* `add n/ThomaŚ p/91918153 e/thomas@gmail.com a/6 Sims Drivè (s)543230`

  The restrictions of placeholders are not followed.

* `add`

  There is insufficient information provided; you must minimally provide a name.

Find out more about restrictions in the sections [Flags](#523-flags), [Placeholders](#524-placeholders) 
and [Commands](#6-commands).

---


Let's try out another command -- the `list` command! `list` lets you see the list of the applicants.

<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong> <br>
The format for different commands are not always identical. For example, executing the `add` command and the `list` 
command will have different formats! 
</div>

For example, after adding an applicant, you decided to see the list of applicant.

**Format:** `list`

The command you would like to enter into the [Command Input Box](#51-user-interface) would be:

```
list
```

You should now have a better understanding of how commands are formatted and used. All commands are consolidated
in the [Command Summary](#7-command-summary).

Here is a checklist you can use before running a command:

* [ ] I know the restrictions of the command
* [ ] I know what parameters are supplied to the command
* [ ] I know the flags for each parameter to be supplied
* [ ] I know the restrictions of each parameter
* [ ] I know the effects of not specifying each optional flag.

[Back to Table of Contents](#table-of-contents)

---

## 6. Commands

This section shares with you on how to use each command in detail.

Before continuing, ensure you have read the section on [Flags](#523-flags) and [Placeholders](#524-placeholders).

What you should expect to find:

* A description of the command
* The format of the command
* The expected behaviour of the command
* A few valid and invalid examples of the command
* Important points to note


<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
<li> For each command, "Format" indicates the syntax of the command.</li>
    <li>Square brackets indicates an optional parameter.</li>
    <li>In most commands, if the same parameter is repeated and only one is required, we take the last value provided.
</li> 
</ul>
</div>

[Back to Table of Contents](#table-of-contents)




### 6.1 Applicant Commands

#### 6.1.1 Create a new applicant `add`

**Format**: `add n/NAME p/PHONE e/EMAIL a/ADDRESS [note/NOTE]`

> Creates a new applicant with the provided information

**Info**
* All fields apart from `NOTE` are compulsory.
* All `NAME` and `PHONE` must be unique.
* `NAME` is case-sensitive. (e.g. "Thomas" is different from "thomas").
* `PHONE` does not require you to include the country code. Only include the numbers.
* The value of `NOTE` will be `-` if it is not provided.


<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li> If two or more values of the same parameter are provided, only the last value for that parameter will be taken.
</li> 
</ul> </div>


**Example:**

**Assumption:**

HMHero does not already contain an applicant with the name "Adam" and phone number 91918153.

![add_command.png](images%2Fadd_command.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.2 Search for an applicant `find`

**Format**: `find n/[KEY] p/[KEY] note/[KEY]`

> Finds all applicants in HMHero using name, phone, note or all

**Info**
* The notation `[KEY]...` means that we take in name or phone or note or all.
  In this case, at least one `KEY` is required.
* The `n/[KEY]` and `note/[KEY]` are case-insensitive. (e.g. "apples" will match "Apples").
* The result will be applicants where each of the `KEY` are present in the `NAME` or `PHONE` or `NOTE`.
  (e.g. "Thomas" will only match "Thomas", "91918153" will only match "91918153", "java" will only match "java")


<div class="alert alert-success" role="alert">:bulb: <strong>Tip:</strong> <br>
<ul>
    <li> You can use the <a href="#613-list-all-applicants-list">List Command</a> in the next section to display all applicants again!</li> 
</ul> </div>

<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li> The <code>find</code> command only finds applicants which has a name, phone or both 
that fully matches the specified search of the full name, phone or both!</li>
    <li> This means that if the <code>NAME</code> <code>Thomas Lee</code> and <code>Thomas Tan</code> is in HMHero, 
executing <code>Thomas Lee</code> will only find <code>Thomas Lee</code>. </li>
    <li> This also means that if the <code>PHONE</code> <code>91918153</code> and <code>9191</code> is in HMHero, 
executing <code>91918153</code> will only find <code>PHONE</code> <code>91918153</code>. </li>
    <li> If you try to find applicants using both <code>NAME</code> and <code>PHONE</code>,
it will work the same as finding individually! 
</li>
</ul> </div>

**Example:**

![find_command.png](images%2Ffind_command.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.3. List all applicants `list`

**Format**: `list`

> List all applicants in HMHero

**Info**
* This command is useful to view all applicants again after using the [Find Command](#612-search-for-an-applicant-find).


**Example:**

![list_command.png](images%2Flist_command.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.4. Delete an applicant `delete`

**Format**: `delete n/NAME p/PHONE`

> Deletes an applicant in HMHero using name and phone

**Info**
* All fields are compulsory.


<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li> The <code>delete</code> command only deletes <code>Applicant</code> which has a name and phone 
that fully matches the specified search.</li> 
</ul> </div>


**Example:**

Applicant list before deleting the applicant Bernice Yu with phone number 99272758:

![delete_command_before.png](images%2Fdelete_command_before.png)

After:

![delete_command_after.png](images%2Fdelete_command_after.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.5. Advance an applicant `advance`

**Format**: `advance n/NAME p/PHONE [d/INTERVIEW DATETIME]`

> Advances an applicant in HMHero using name, phone and interview datetime

<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li>The <code>advance</code> command only advances <code>Applicant</code> which has a name and phone that
fully matches the specified search.</li> 
    <li> The <code>INTERVIEW DATETIME</code> is required to advance <code>Applicant</code> from <code>status</code>
<code>APPLIED</code> to <code>status</code> <code>SHORTLISTED</code>. 
</li> 
    <li> However, <code>INTERVIEW DATETIME</code> is not required to advance <code>Applicant</code> from <code>status
</code> <code>SHORTLISTED</code> to <code>status</code> <code>ACCEPTED</code>. </li>
    <li> The format for <code>INTERVIEW DATETIME</code> should follow: "dd-mm-yyyy HH:MM".
        <ul>
            <li> “dd”: Day of the month. For example, “10” would represent the 10th day of the month. </li>
            <li> “mm”: Month of the year, ranging from 1 to 12 for January to December respectively.
For example, “05” would represent May. </li>
            <li> “yyyy”: A 4-digit year. For example, “2023” would represent the year 2023. </li>
            <li> "HH": Hour of the day, ranging from 0-23 in 24-hour clock format. 
            For example, "15" would represent 15th hour of the day. </li>
            <li> "MM": Minute of the day, ranging from 0-59. For example, "50" would represent the 50th minute of the 
hour.</li>
        </ul> 
    </li> 
</ul> </div>

<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong>
<code>INTERVIEW DATETIME</code> cannot be earlier than current time when you advance an applicant! </div>



**Example:**

Advancing an applicant with the status `APPLIED` requires an interview date and time.

![advance_command_applied.png](images%2Fadvance_command_applied.png)


Advancing an applicant with the status `SHORTLISTED` does not require an interview date and time.

![advance_command_shortlisted.png](images%2Fadvance_command_shortlisted.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.6. Reject an applicant `reject`

**Format**: `reject n/NAME p/PHONE`

> Rejects an applicant in HMHero using name and phone

**Info**
* All fields are compulsory.


<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li> The <code>reject</code> command only rejects <code>Applicant</code> which has a name and phone that 
fully matches the specified search.</li> 
    <li> The <code>NAME</code> and <code>PHONE</code> is required to reject <code>Applicant</code> from <code>status</code> 
<code>APPLIED</code>, <code>SHORTLISTED</code> and <code>ACCEPTED</code> to <code>status</code> <code>REJECTED</code>.
    </li> 
</ul> </div>


**Example:**

![reject_command.png](images%2Freject_command.png)

<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong>
Applicants with the status <code>REJECTED</code> cannot be rejected!
</div>

[Back to Table of Contents](#table-of-contents)

---

#### 6.1.7. List all interview dates of applicants `interview`

**Format**: `interview`

> List all interview dates of shortlisted applicants in HMHero in chronological order

**Info**
* This command is useful to view all the applicants' interview dates again after
  using the [Advance Command](#615-advance-an-applicant-advance).


**Example:**

![interview_command.png](images%2Finterview_command.png)

[Back to Table of Contents](#table-of-contents)

---


#### 6.1.8. Edit an existing applicant `edit`

**Format**: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/INTERVIEW DATETIME] [note/NOTE]`

> Edits an existing applicant with the provided information

**Info**
* If two or more values of the same parameter are provided, only the last value for that parameter will be taken.
* However, if two or more values of `NOTE` are provided, both parameter will be taken in.

<div class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
<ul>
    <li> All fields apart from <code>INDEX</code> are optional. However, you need to include at least one optional 
parameter. </li>
    <li> The format for <code>INTERVIEW DATETIME</code> should follow: "dd-mm-yyyy HH:MM".
        <ul> 
            <li> “dd”: Day of the month. For example, “10” would represent the 10th day of the month. </li>
            <li> “mm”: Month of the year, ranging from 1 to 12 for January to December respectively.
For example, “05” would represent May. </li>
            <li> “yyyy”: A 4-digit year. For example, “2023” would represent the year 2023. </li>
            <li> "HH": Hour of the day, ranging from 0-23 in 24-hour clock format. 
For example, "15" would represent 15th hour of the day. </li>
            <li> "MM": Minute of the day, ranging from 0-59. For example, 
"50" would represent the 50th minute of the hour. </li> 
        </ul>
    </li>
    <li> You are allow to change <code>INTERVIEW DATETIME</code> to a time before the current time using edit command. 
</li>
</ul> </div>


**Example:**

Applicant list before editing:
![edit_command_before.png](images%2Fedit_command_before.png)

After editing Bernice Yu's phone and notes:
![edit_command_after.png](images%2Fedit_command_after.png)


[Back to Table of Contents](#table-of-contents)

---


#### 6.1.9. Remind upcoming interviews `remind`

**Format:** `remind`

> Lists all applicants with interviews within the next three days.

**Example:**

![remind_command.png](images%2Fremind_command.png)

[Back to Table of Contents](#table-of-contents)

---

### 6.2. Statistics Commands

#### 6.4.1. Summary Statistics `summary`

**Format:** `summary`

> Shows a statistical summary of the company's hiring processes.

**Example:**

![summary_command.png](images%2Fsummary_command.png)


[Back to Table of Contents](#table-of-contents)

---

### 6.3. General Commands

#### 6.3.1. Receive help during usage `help`

**Format**: `help`

> Displays help window for HMHero which contains link to User Guide.

**Example:**

![help_command.png](images%2Fhelp_command.png)


[Back to Table of Contents](#table-of-contents)

---

#### 6.3.2. Exit HMHero `exit`

**Format**: `exit`

> Exits HMHero

<div markdown="span" class="alert alert-danger" role="alert">:exclamation: <strong>Caution:</strong>
This command is the only guaranteed way for the data file to be saved when you exit the application.
To prevent losing data, always exit the application using this command instead of any other way.
</div>

**Example:**

**Command Input Box:**

Possible inputs:
```
exit
```

**Expected Outcomes:**

* All HMHero application windows will close
* Your data is saved locally in your computer.


<div markdown="span" class="alert alert-info" role="alert">:information_source: <strong>Note:</strong> <br>
If your data cannot be saved successfully, HMHero will not close in order to prevent data loss.
</div>

[Back to Table of Contents](#table-of-contents)

---


## 7. Command Summary

### 7.1. Applicant Commands

| Action                                 | Format                                                                                                                                                               | Example                                                                     |
|----------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Add a new applicant                    | `add n/NAME p/PHONE e/EMAIL a/ADDRESS [note/NOTE]`                                                                                                                   | `add n/Tom p/98763213 e/asd@gmail.com a/6 Sims Drive (s)532123 note/Python` |
| Search for an applicant                | `find [n/NAME] [p/PHONE]` <br> (Minimally one of `n/NAME` or `p/PHONE` must be provided)                                                                             | `find n/Thomas p/98764321`                                                  |
| List all applicants                    | `list`                                                                                                                                                               | `list`                                                                      |
| Delete an applicant                    | `delete n/NAME p/PHONE`                                                                                                                                              | `delete n/Thomas p/98765432`                                                |
| Advance an applicant                   | `advance n/NAME p/PHONE [d/INTERVIEW DATETIME]` <br> <br> **Note:** You need to provide `INTERVIEW DATETIME` to advance applicant's `status` `APPLIED` to `ACCEPTED` | `advance n/Thomas p/98765432 d/20-03-2024 12:12`                            |
| Reject an applicant                    | `reject n/NAME p/PHONE`                                                                                                                                              | `reject n/Thomas p/98765432`                                                |
| View the interview dates of applicants | `interview`                                                                                                                                                          | `interview`                                                                 |
| Edit the information of an applicant   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/INTERVIEW DATE TIME] [note/NOTE]`                                                                            | `edit 1 n/Marry p/98763245`                                                 |
| Remind an applicant's interview date   | `remind`                                                                                                                                                             | `remind`                                                                    |
| View summary statistics                | `summary`                                                                                                                                                            | `remind`                                                                    |

[Back to Table of Contents](#table-of-contents)

---

### 7.2. Statistics Commands

| Action                                  | Format    | Example   |
|-----------------------------------------|-----------|-----------|
| Displays statistics collected by HMHero | `summary` | `summary` | 

[Back to Table of Contents](#table-of-contents)

---

### 7.3. General Commands

| Action                                                | Format | Example |
|-------------------------------------------------------|--------|---------|
| Shows a help dialog with a list of available commands | `help` | `help`  |
| Exits HMHero                                          | `exit` | `exit`  |

[Back to Table of Contents](#table-of-contents)

---

## 8. Troubleshooting

**Problem:**

The JAR file not launching even after double-clicking the file.

**Solution:**

1. Open your terminal
* Windows:
    * The default key combination to launch your terminal is <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>P</kbd>
* Mac:
    * Use <kbd>Cmd</kbd>+<kbd>Space</kbd> to open Spotlight Search
    * Search for "terminal" and click it to launch. 
2. Navigate to the location where "hmhero.jar" is stored within your terminal using `cd`. For example, `cd Downloads/`


3. On your terminal, run `java -jar hmhero.jar`

**Problem:**

The JAR file not launching in Windows Subsystem for Linux (WSL).

<div markdown="span" class="alert alert-info" role="alert">:information_source: **Note:** 
WSL does not support GUI applications by default. 
</div>

**Solution:**

1. Our recommendation is to run HMHero application on Windows and not on WSL.

**Problem:**

Unable to exit/save HMHero to data file

**Solution:**

1. This error is due to `hmhero.jar` being started in a protected folder. (Examples of write-protected folders include`C:\WINDOWS\System32` in windows and the `/etc` dir in linux) <br> Please move the `hmhero.jar` file into another folder in your computer and start HMHero application from that folder.
   

[Back to Table of Contents](#table-of-contents)

---

## 9. FAQ

### Q: If I do not have Java 11, how do I install it on my computer? <br>
**A:** You can navigate to this site [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) and download Java 11 according to your system’s specifications.

### Q: Do I need an internet connection to run HMHero? <br>
**A:** No, HMHero can boot up and run all functionalities without an internet connection.

### Q: Can I use HMHero on my mobile device?<br>
**A:** Unfortunately, HMHero is only designed to run on your desktop/laptop such that you can use the command line interface.

### Q: How do I transfer my data to another computer?<br>
**A:** Install HMHero on the other computer and overwrite the empty data file with the data file created by HMHero in your current computer.

[Back to Table of Contents](#table-of-contents)

---
## 10. Acknowledgements


HMHero is a brownfield software engineering project based off [AddressBook Level-3](https://nus-cs2103-ay2223s2.github.io/tp/), taken under the [CS2103T Software Engineering](https://nus-cs2103-ay2223s2.github.io/website/admin/index.html) held by School of Computing at National University of Singapore.

Java dependencies:
* [JavaFX](https://openjfx.io/) for Graphical User Interface
* [JUnit5](https://github.com/junit-team/junit5) for testing
* [Jackson](https://github.com/FasterXML/jackson) for JSON-related operations

Documentation dependencies:
* [PlantUML](https://plantuml.com/) for creating UML diagrams
* [Jekyll](https://jekyllrb.com/)  for rendering the website

[Back to Table of Contents](#table-of-contents)

---

## 11. Glossary

### Quick Reference
- [ADDRESS (Placeholder)](#address-placeholder)
- [Address](#address)
- [Admonitions](#admonitions)
- [Applicant](#applicant)
- [Applicant List Box](#applicant-list-box)
- [Application Cycle](#application-cycle)
- [COMMAND_WORD (Placeholder)](#commandword-placeholder)
- [Command](#command)
- [Command Input Box](#command-input-box)
- [Command Line (CLI)](#command-line)
- [Command Output Box](#command-output-box)
- [Placeholder](#email-placeholder)
- [Email](#email)
- [Flags](#flags)
- [Graphical User Interface(GUI)](#graphical-user-interface)
- [Help Window](#help-window)
- [Hiring Manager](#hiring-manager)
- [Input](#input)
- [INTERVIEW_DATE (Placeholder)](#interviewdate-placeholder)
- [Interview Date](#interview-date)
- [KEYWORD (Placeholder)](#keyword-placeholder)
- [NAME (Placeholder)](#name-placeholder)
- [Name](#name)
- [NOTES (Placeholder)](#notes-placeholder)
- [Notes](#notes)
- [Operating System (OS)](#operating-system)
- [Output](#output)
- [Parameter](#parameter)
- [PHONE_NUMBER (Placeholder)](#phonenumber-placeholder)
- [Phone Number](#phone-number)
- [Placeholder](#placeholder)
- [STATUS (Placeholder)](#status-placeholder)
- [Status](#status)
- [Syntax](#syntax)
- [URL](#url)

---


### A
> #### ADDRESS Placeholder
> The ADDRESS is a text representing the address of the applicants.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Address
> The address of the applicant provided by the user.
> #### Admonitions
> Admonition boxes (or simply “admonitions”) are coloured boxes containing highlighted pieces of text.
> <br>For details on various type of admonitions used in HMHero, refer to the [Admonitions Boxes](#31-admonition-boxes) section of the User Guide
> #### Applicant
> The person who apply to the company for the job.
> #### Applicant List Box
> The region located at the bottom left of the HMHero's main window.<br>
> To view more information, refer to the [User Interface](#51-user-interface) section of the User Guide
> #### Application Cycle
> All applicants added are at the Applied status by default. From there, hiring managers can advance their application status to Shortlisted, then to Accepted. Applicants can be rejected at any stage.
![Application Cycle](images/application_stage.png)


### C
> #### COMMAND_WORD Placeholder
> The COMMAND_WORD is a text indicating a command word of a command
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Command
> A feature or function that HMHero can perform.
> #### Command Input Box
> The region located at the top left of the HMHero's main window.<br>
> To view more information, refer to the [User Interface](#51-user-interface) section of the User Guide
> #### Command Line 
> The typing interface that you use to interact with HMHero. It is represented as the box where you type in commands.
> #### Command Output Box
> The region located at the right half of the HMHero's main window. <br>
> To view more information, refer to the [User Interface](#51-user-interface) section of the User Guide

### E
> #### EMAIL Placeholder
> The EMAIL is a text representing the email address of the applicants.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Email
> The email address of the applicant provided by the user.

### F
> #### Flags
> A marker to identify the type of input by the user. For instance, in `n/NAME`, `n/` is the flag.

### G
> #### Graphical User Interface
> A Graphical User Interface is a graphics-based interface that uses icons, menus and a mouse (to click on the icon or pull down the menus) to manage interaction with the system. In HMHero, this presents as the window that appears when launching it.

### H
> #### Help Window
> A pop-up window containing help information, shown only after calling a <kbd>help</kbd> command.<br>
> To view more information, refer to the [User Interface](#51-user-interface) section of the User Guide
> #### Hiring Manager
> A person responsible for overseeing the recruitment process within an organization, from identifying staffing needs to making hiring decisions.

### I
> #### Input
> The text that a user would enter into HMHero
> #### INTERVIEW_DATE Placeholder
> The INTERVIEW_DATE is the date indicating when the applicant is having his/her interview.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Interview Date
> Date where applicants are going to have their interviews. Interview date is in the format "day-month-year hour:minute", represented in the "DD-MM-YYYY HH:MM" format.

### K
> #### KEYWORD Placeholder
> The KEYWORD is the text we use search for an item. It can be `NAME` or `PHONE_NUMBER` of the applicant.


### N
> #### NAME Placeholder
> The NAME is a text representing the name of the applicants.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Name
> The name of the applicant provided by the user.
> #### NOTES Placeholder
> The NOTES are some texts that use to represent the skill set of the applicants.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Notes
> The skill set the applicant possesses provided by the user.

### O
> #### Operating System
> Is a software program that manages computer hardware and software resources, and provides common services for computer programs to run and interact with the computer hardware.
> #### Output
> The result after calling a Command. Results are displayed in the GUI.

### P
> #### Parameter
> Values that are passed while calling the Commands. For instance, when calling `find n/NAME`, parameter here is `n/NAME`.
> #### PHONE_NUMBER Placeholder
> The PHONE_NUMBER is an integer representing the phone number of the applicant.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Phone Number
> The phone number of the applicant provided by the user.
> #### Placeholder
> Placeholders in HMHero refers to the UPPER_CASE words that appear after the flags in commands that is provided by the user. For instance, `n/NAME`, `NAME` is a placeholder

### S
> #### STATUS Placeholder
> The STATUS is a text that represent the current state of the applicant.
> <br>To view more information (limitations, examples, etc.), refer to the [Placeholder](#524-placeholders) table.
> #### Status
> Status has four stages, which are APPLIED, SHORTLISTED, ACCEPTED and REJECTED. Status can only be modified by using <kbd>advance</kbd> or <kbd>reject</kbd> command.
> #### Syntax
> The structure of statements/inputs users type into the Command Line.

### U
> #### URL
> A hyperlink to a website.

[Back to Table of Contents](#table-of-contents)

---

