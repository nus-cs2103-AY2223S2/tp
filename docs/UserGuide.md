---
layout: page
title: User Guide
---

## Table of Contents
* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

<h1 align="center">
    <b>The Intern's Ship - User Guide</b>
</h1>
<p align="center">
    <img src="images/ship.png" alt="logo" width="90"><br>
</p>

## Introduction
Designed with **internship-seeking university students** in mind, **The Intern’s Ship (TinS)** aims to make
managing your internship applications fuss-free. Despite being optimised for use via the keyboard using
a Command Line Interface (CLI), you will still be able to enjoy the benefits of having a visual display
through our Graphical User Interface (GUI).

With TinS, you can conveniently and efficiently manage, coordinate and keep track of your internship applications
all in one place.

**Notable Features**
  * Finding a particular internship application in TinS using keywords: `find`
  * Calendar View: `calendar`
  * Finding all clashing events: `clash`


<div style="page-break-after: always;"></div>

## About the User Guide

### Objective of User Guide
The Objective of this User Guide is to provide you with useful instructions on how to navigate the TinS application.
To ensure a smooth on-boarding process to The Intern's Ship, this User Guide will include detailed
step-by-step instructions on how to install the TinS application into your device, as well as how to use the various
features of TinS. If you are a frequent user of TinS, this User Guide will also include useful tips on how you can use
TinS in a more efficient way.

### How the User Guide is Formatted

#### Additional Tips and Comments

**Information Box**
<div markdown="span" class="alert alert-primary">

    :information_source: **Info:** Provides useful information that supplements the main text
</div>

**Tip Box**
<div markdown="span" class="alert alert-success">

    :bulb: **Tip:**  Suggestions on how to enhance your experience
</div>

**Warning Box**
<div markdown="span" class="alert alert-danger">

    :warning: **Warning:**  Warns of a dangerous action that you should be aware of and to consider
    carefully before committing
</div>

#### Syntax Highlighting

Commands, fields, prefixes and file paths are highlighted in the User Guide.
Example: `list`, `POSTIION`, `data/internshipcatalogue.json`

### How to use the User Guide
* If you **have not installed TinS** or **are new to TinS**, [Quick Start](#quick-start) will guide you through the
  process of installing TinS and provide you with a brief introduction to TinS.
* If you are **a more advanced user of TinS**, the [Features](#features) section contains detailed information of what
  features TinS has to offer. Embedded in this section is also tips on how you can maximize your efficiency while using
  TinS.

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tins.jar` from [here](https://github.com/AY2223S2-CS2103T-W11-2/tp/releases/tag/v1.3).

3. Copy the file to the folder you want to use as the _home folder_ for your TinS.

4. Double-click on the file `tins.jar` to launch TinS.
   A TinS Application Interface similar to the one below should appear in a few seconds.
   ![Ui](images/ug/home_page.png)

   <div markdown="span" class="alert alert-primary">

   :information source: **Info:** The default TinS application contains some sample data. These sample data can
   come in handy for you to try out the various commands of TinS. To remove all sample data, use the
   <code>deleteall</code> feature.
   </div>

5. Type your command into the Command Box, then press <button>Enter</button> to instruct TinS to execute your command.
   Here are some basic commands you can try:
    * `list` : List all internships in the List Panel
    * `add p/Data Analyst c/Google s/0 d/NA t/IMPORTANT t/PRIORITY` : Add an internship to TinS
    * `delete 1` : Delete the first internship in the List Panel from TinS

6. Refer to the [Command Summary](#command-summary) below for your desired TinS Commands. More details on each specific
TinS command can be found [here](#features).

<div style="page-break-after: always;"></div>

## About TinS

### About TinS Application's Interface

![gui](images/ug/gui.png)

1. **Command Box**: This is where you would input your commands.
2. **Program Response**: This box displays the program's feedback to your inputted commands.<br>
   Types of Program Response Messages:
   * Success Message: indicating that TinS has successfully executed your command
   * Error Message: indicating the reason why TinS was not about to execute your command and tips on what you can do to
   correct the error
3. **List Panel**: This panel displays your internships listings stored in TinS (including Position,
   Company, Status and Tags).
4. **Display Panel**: This panel is used to display more details. Depending on your given command, the Details could be
   any one of the following:
   * More details pertaining to a Specific Internship
   * Pages: Home, Calendar View, Statistics
   * Results of Commands, like `clash` or `event find`
5. **Location Bar**: This location bar states the location in which your TinS data file is currently stored on your
   local device

### How your Internship Application Data is Organised in TinS
![data organisation](images/ug/ug-internship-organisation.png)

**Internships**

In TinS, an internship application is stored as an **Internship**. Within an Internship, you would be able to store the
following fields:
* `POSITION`: The Name of Internship Position.
* `COMPANY` : The Name of Hiring Company.

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** Each Internship is **uniquely identifiable** by a **combination of its `POSITION` and
`COMPANY`**. `POSITION` and `COMPANY` fields are **case-insensitive**.

Example: The following internships will be identified as same internship in TinS.

* Internship with `POSITION` as `Software Engineer` and `COMPANY` as `Grab`
* Internship with `POSITION` as `Software ENGINEER` and `COMPANY` as `GRAB`

</div>

* `STATUS` : The Status of your Application. This is an **Integer value** from **0 to 3**. Here are the statuses for the
  corresponding integer values:
   * `0` : Interested
   * `1` : Applied
   * `2` : Offered
   * `3` : Rejected

* `DESCRIPTION` : Additional details about the Internship (E.g. Contact Details of Hiring Manager, Link to Internship
  Webpage, Requirements of Internship). This field can be left blank if you have nothing to include for this field.

* `TAG` : Customised Tag (E.g. `IMPORTANT`, `PRIORITY`). An Internship can have zero or more than one tag. All Tags in
  TinS are displayed in capitalised letters.
  * `TAG` must be alphanumeric (i.e. a `TAG` should not contain any blank-spaces, hyphens, colons, dashes etc)

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:** It is recommanded to ensure that your entries for `POSITION`, `COMPANY` and `TAG`to  have a maximum
    of **40 characters**. While TinS will still allow the addition of entries with more than 40 characters, without
    giving a warning, the addition of these fields could affect the visual display (i.e. truncated fields).
</div>

An Internship can also store zero or more than one **Event**.

**Events**

In TinS, an **Event** can be used to represent a **Deadline** or an **Interview** associated to an Internship. Within
an Event, you would be able to store the following fields:
* `EVENT_NAME`: The Name of the Event.

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:** It is recommanded to ensure that your entry for `EVENT_NAME` to have a maximum
    of **40 characters**. While TinS will still allow the addition of entries with more than 40 characters, without
    giving a warning, the addition of this field could affect the visual display (i.e. truncated field).
</div>

* `START_DATETIME`: The Starting Time of an Event (in the format: DD/MM/YYYY HHMM).
* `END_DATETIME`: The Ending Time of an Event (in the format: DD/MM/YYYY HHMM).
  * If the Event is a **Deadline**, the `START_DATETIME` will be the same as the `END_DATETIME`.
* `EVENT_DESCRIPTION`: Description of the Event (e.g. Venue, Things to take note of). This field can be left blank if you have
  nothing to include for this field.

### Information about Crafting Your Command
This section contains information on how to craft your command.

#### Prefixes and Fields
TinS differentiates between the various fields in your command by its preceding prefix. The Prefixes are designed to be
short, to enable you to quickly indicate which field your information is associated to. Here is the list of fields and
their associated prefixes.

<table>

<tr>
<th>Field</th>
<th>Prefix</th>
</tr>

<tr>
<td><code>POSITION</code></td>
<td><code>p/</code></td>
</tr>

<tr>
<td><code>COMPANY</code></td>
<td><code>c/</code></td>
</tr>

<tr>
<td><code>STATUS</code></td>
<td><code>s/</code></td>
</tr>

<tr>
<td><code>DESCRIPTION</code></td>
<td><code>d/</code></td>
</tr>

<tr>
<td><code>TAG</code></td>
<td><code>t/</code></td>
</tr>

<tr>
<td><code>EVENT_NAME</code></td>
<td><code>na/</code></td>
</tr>

<tr>
<td><code>START_DATETIME</code></td>
<td><code>st/</code></td>
</tr>

<tr>
<td><code>END_DATETIME</code></td>
<td><code>en/</code></td>
</tr>

<tr>
<td><code>EVENT_DESCRIPTION</code></td>
<td><code>de/</code></td>
</tr>

</table>

#### Notes about the Command Format
* Command words are case-insensitive.

  E.g. The command `LIST` is the same as the command `list`.

* Prefixes are case-sensitive.

  E.g. Entering the prefix `P/` will not be recognised by TinS as `p/`

* Words in `UPPER_CASE` are the fields to be supplied by you.

  E.g. in `add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION`, `POSITION` is a parameter which can be
  used as `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java`.

* Items in square brackets are optional.

  E.g `[t/TAG]` means in `add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION [t/TAG]` means the commands
  `add p/Software Engineer c/Grab s/0 d/` and `add p/Software Engineer c/Grab s/0 d/ t/IMPORTANT` are both
  valid.

* Items with `...` after them can be used multiple times including zero times.

  E.g. `[t/TAG]...` in `add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION [t/TAG]...` means the command
  `add p/Software Engineer c/Grab s/0 d/` and `add p/Software Engineer c/Grab s/0 d/ t/IMPORTANT t/PRIORITY` is
  valid.

* Items without `...` after them should only be used **once**.

  <div markdown="span" class="alert alert-primary">

  :information_source: **Info:** If an item without `...` after it is used more than once, TinS will identify the last
  occurrence of that item as the input for that field.

  Example: `add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION [t/TAG]...` means that the item, `p/POSITION` should only be
  used once. Hence, if `add p/Software Engineer c/Grab s/0 d/ t/IMPORTANT p/Data Analyst p/Machine Learning` is
  entered, TinS would identify the input for the field `POSITION` as `Machine Learning`.
  </div>

* Fields can be in any order.

  E.g. `add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION [t/TAG]...` is the same as
  `add c/COMPANY s/STATUS d/DESCRIPTION p/POSITION [t/TAG]...`

* If the command does not require you to input a field, any extra field/values enter after the identified
  command is ignored.

  The commands in TinS that do not require additional input of fields from user are `list`, `clash`, `home`, `calendar`,
  `stats`, `help` and `exit`.
  E.g. If `clash help` is entered, TinS will identify your command as a `clash` command, ignoring all the extra
  fields/values found after `clash`, and execute the command `clash`.


<div style="page-break-after: always;"></div>

## Features

### Opens the Home Page : `home`

Opens the Home Page in the Display Panel.

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:**  The `home` command can also be used to refresh the Home Page in the Display Panel.
</div>

* Home Page reminds you of your upcoming deadlines and interviews, occurring either today or tomorrow, and the command
  format for useful commands, by conveniently displaying them in the Display Panel.

Example: `home`

![home_page](images/ug/home_page.png)

<div style="page-break-after: always;"></div>

### Opens the Statistics Page : `stats`

Calculates statistics based on your current internship applications and displays personalized statistics in the
Display Panel.

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:**  The `stats` command can also be used to refresh the Statistic Page in the Display Panel.
</div>

* Statistics Page shows you a summary of how your internship-seeking journey has been. The pie chart provides you with a
  visual representation of the current status of your internship applications.

Example: `stats`

![stats_page](images/ug/stats_page.png)

<div style="page-break-after: always;"></div>

### Opens the Calendar Page : `calendar`

Displays a calendar view of all your events in the Display Panel.

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:**  The `calendar` command can also be used to refresh the Calendar Page in the Display Panel.
</div>

* Clicking on an event displayed in the calendar view shows you more details about the event.

<div markdown="span" class="alert alert-success">

    :bulb: **Tip:**  If there are too many events on one day, you can use the `event find` function to find events by
    their start and end timings.
</div>

Example: `calendar`

![calendar_page](images/ug/calendar_page.png)

<div style="page-break-after: always;"></div>

### Adding an Internship Application : `add`

Adds an internship and its details to TinS.

Format: `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION [t/TAG] ...`

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** In TinS, each internship stored must be unique (no duplicates). Since an internships
is uniquely identified by the combination of `POSITION` and `COMPANY` fields, you would not be able to `add` an
internship application to TinS, if there is a pre-existing internship in TinS with the same `POSITION` and `COMPANY`
(comparison is case-insensitive).

Example: If TinS contains an Internship with `POSITION` as `Software Engineer` and `COMPANY` as `Grab`, the commands
below will not be allowed.

* `add p/Software Engineer c/Grab s/0 d/`
* `add p/SOFTWARE engineer c/Grab s/3 d/`

</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** In TinS, Tags are standardised to be all in capitalised letters. If you enter
`t/important`, TinS will automatically save it as `t/IMPORTANT` for you.
</div>

Example: `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java t/Important t/Priority`

![Ui](images/ug/ug-add.png)

<div style="page-break-after: always;"></div>

### Listing all Internships Applications : `list`

List all the internships in List panel.

* After keying in the `list` command, TinS displays **all** the Internships stored in TinS in the List panel.
* Only the `POSITION`, `COMPANY_NAME`, `APPLICATION_STATUS` and `TAG` are display in the List Panel for each Internship.

Example: `list`

![internship_list_panel](images/ug/ug-list.png)

<div style="page-break-after: always;"></div>

### Finding Internships: `find`

Finds an Internships by their fields (`POSITION`, `COMPANY`, `STATUS`, `TAG`).

Format: `find [p/POSITION] [c/COMPANY] [s/STATUS] [t/TAG]...`

* Choose the fields you would like to filter the Internship Catalogue by. One or more fields can be used to find an
  internship.
* For each chosen field, enter a keyword you would like to find corresponding Internship Applications for.
* An internship is will be included in the filtered list if the internship's field contains the keyword you indicated.
* `find` is case-insensitive.
* After keying in the `find` command, TinS will find related internships based on your chosen fields and
  provided keywords, displaying them in the List Panel.
* To view all Internship in TinS again, simply enter the `list` command.

Example: TinS has 7 internship listings. `find c/tech t/important` would return the list of internships with `COMPANY`
containing the word "gov" and `TAG` as `IMPORTANT`.

![find_internship](images/ug/ug-find.png)

<div style="page-break-after: always;"></div>

### Viewing Details of a Particular Internship Application : `select`

View more details of the internship application selected by ID.

Format: `select ID`

* `ID`: The number of the selected internship in the List Panel.
* After keying in the `select` command, TinS will return the all details of the selected internship in the Display
  Panel.

Example: `select 2`

![select_internship](images/ug/ug-select.png)

<div style="page-break-after: always;"></div>

### Editing Details of Internship Application : `edit`

Edit details of the internship application selected by ID.

Format: `edit ID [p/POSITION] [c/COMPANY] [s/STATUS] [d/DESCRIPTION] [t/TAG] ...`

* `ID`: The number of the selected internship in the List Panel.
* After keying in the `edit` command, along with the selected fields the user wishes to edit, TinS will update the
  corresponding fields of the internship application accordingly.

Example:

After keying in the `edit` command followed by an existing `ID` and the fields `STATUS` and `DESCRIPTION`,
TinS will update the `STATUS` and `DESCRIPTION` fields of the internship application with the specified
`ID`, then display the newly updated internship application. This command `edit 1 s/1 d/Learn C++` will return the
following results.

![view_internship](images/ug/ug-edit.png)

<div style="page-break-after: always;"></div>

### Deleting an Internship Application : `delete`

Deletes an internship along with its details by ID.

Format: `delete ID`

* `ID`: The number of the selected internship in the List Panel.
* Deletes the internship application at the specified `ID` in the List Panel.

Example: `delete 3` deletes the 3rd internship listed in the List Panel. In this example, the
internship application with `POSITION` as `Data Analytics` and `COMPANY_NAME` as `Google` is removed.

![delete_internship](images/ug/ug-delete.png)

<div style="page-break-after: always;"></div>

### Adding an Event to an Internship Application : `event add`

Adds an Event along with its details to the selected internship.

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** Before adding an event, you have to select an internship using `select ID`.
</div>

Format: `event add na/EVENT_NAME [st/START_DATETIME] en/END_DATETIME de/EVENT_DESCRIPTION`

* `START_DATETIME` and `END_DATETIME` fields need to be in the format : DD/MM/YYYY HHMM

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** `event add` enables you to add events even if the events occurred in the past.
</div>

Examples:
1. If you would like to add an Internship Application Submission Deadline:
  `event add na/Application Submission Deadline en/14/04/2023 2359 de/Via Talent Connect`
2. If you would like to add an Interview:
  `event add na/Technical Interview st/10/05/2023 1500 en/10/05/2023 1700 de/On Zoom`

![eventadd_internship](images/ug/ug-eventadd.png)

<div style="page-break-after: always;"></div>

### Deleting an Event from an Internship Application : `event delete`

Deletes selected Event along with its details from the selected internship.

<div markdown="span" class="alert alert-primary">

:information_source: **Info:** Before deleting an event, you have to select an internship using `select ID`.
</div>

Format: `event delete ID`
* `ID`: The ID of the selected event in the list

Examples: `event delete 1` entered after `select 1` deletes the first event in the first internship.

![event delete](images/ug/ug-eventdelete.png)

<div style="page-break-after: always;"></div>

### Finding a particular Event : `event find`

Finds events based on certain filters.

Format: `event add [na/EVENT_NAME] [st/START_DATETIME] [en/END_DATETIME]`

* Choose the fields you would like to find an event by. One or more fields can be used to find an event.
* For `EVENT_NAME` field, TinS will find events with `EVENT_NAME` containing the keyword provided by you. This is
  case-insensitive.
* For `START_DATETIME` and `END_DATETIME`, enter a `DATETIME` in the format DD/MM/YYYY HHMM. TinS will find events with
  the corresponding `START_DATETIME` or `END_DATETIME`.
* The results of `event find` will be displayed in the Display Panel.

Example: `event find na/Technical Interview st/20/03/2023 1400` returns a list of events with the name Technical
Interview and starting time 20 March 2023, 2pm.

<div style="page-break-after: always;"></div>

### Finding all Clashing Events : `clash`

Displays all events with clashing timings in the Display Panel.

* Clashing Events are arranged by date.
* Clicking on any date, you will be able to see all events with clashing timing on that particular day.

Format: `clash`

Example:

![clash_function](images/ug/ug-clash.png)

<div style="page-break-after: always;"></div>

### Clearing all Internship Applications in TinS : `deleteall`

Deletes all existing Internship Applications and Events in TinS.

* When you enter the command `deleteall`, TinS will prompt you to double confirm that you would like to delete **all**
  Internship Applications and Events currently stored in TinS.
* To proceed with executing the command, enter `deleteall confirm`.

<div markdown="span" class="alert alert-success">

:bulb: **Tip:**  The `deleteall` command can come in handy if you need to delete all the dummy data initially stored in
TinS or to reset the data in TinS in preparation for your next Internship Application season.
</div>

<div markdown="span" class="alert alert-danger">

:warning: **Warning:** This command is irreversible. Only use it when you would like to clear **all** internship
applications and events in TinS.

</div>

<div markdown="span" class="alert alert-danger">

:warning: **Warning:** If you enter `deleteall confirm` directly, without first entering `deleteall`, TinS will delete
all internship application and events in TinS immediately. Hence, you are advised to avoid entering the command
`deleteall confirm` directly.

</div>

Format: `deleteall`

### Getting Help : `help`

Provides you with a link to helpful instructions on how to use TinS.

Format: `help`

### Exiting the program : `exit`

Exits and closes the TinS application.

Format: `exit`

### Saving the data

The Intern Ship’s data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

<table>

<tr>
<th>Action</th>
<th>Format</th>
</tr>

<tr>
<td><b>Home Page</b></td>
<td><code>home</code>></td>
</tr>

<tr>
<td><b>Statistics Page</b></td>
<td><code>stats</code>></td>
</tr>

<tr>
<td><b>Calendar View</b></td>
<td><code>calendar</code>></td>
</tr>

<tr>
<td><b>Add</b></td>
<td><code>add p/POSITION c/COMPANY s/STATUS d/DESCRIPTION [t/TAG]...</code><br>
E.g. <code>add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python t/Important t/Priority</code></td>
</tr>

<tr>
<td><b>List</b></td>
<td><code>list</code></td>
</tr>

<tr>
<td><b>Find</b></td>
<td><code>find [p/POSITION] [c/COMPANY] [s/STATUS] [d/DESCRIPTION] [t/TAG]...</code><br>
E.g. <code>find c/tech t/important</code> would return the list of internships with <code>COMPANY</code> containing the
word “gov” and <code>TAG</code> as <code>IMPORTANT</code></td>
</tr>

<tr>
<td><b>Select</b></td>
<td><code>select ID</code><br>
E.g. <code>select 1</code> selects the first internship in the List Panel</td>
</tr>

<tr>
<td><b>Edit</b></td>
<td><code>edit ID [p/POSITION] [c/COMPANY] [s/STATUS] [d/DESCRIPTION] [t/TAG]...</code><br>
E.g. <code>edit 1 p/Data Analyst</code> edits the <code>POSITION</code> of first internship in the List Panel
to <code>Data Analyst</code></td>
</tr>

<tr>
<td><b>Delete</b></td>
<td><code>delete ID</code><br>
E.g. <code>delete 1</code> deletes the first internship in the List Panel</td>
</tr>

<tr>
<td><b>Add Event</b></td>
<td><code>select ID</code>, then <code>event add na/EVENT_NAME [st/START_DATETIME en/END_DATETIME
de/EVENT_DESCRIPTION</code><br>
E.g. <code>select 1</code>, then <code>event add na/Technical Interview st/10/09/2023 1500 en/10/09/2023 1700
de/On Zoom</code> adds the event to the first internship in List Panel</td>
</tr>

<tr>
<td><b>Delete Event</b></td>
<td><code>select ID</code>, then <code>event delete ID</code><br>
E.g. <code>select 1</code>, then <code>event delete 1</code> deletes the first event from the first internship</td>
listed in the List Panel
</tr>

<tr>
<td><b>Find Event</b></td>
<td><<code>event add [na/EVENT_NAME] [st/START_DATETIME] [en/END_DATETIME]</code><br>
E.g. <code>event find na/Technical Interview st/20/03/2023 1400</code></td>
</tr>

<tr>
<td><b>Clash</b></td>
<td><code>clash</code></td>
</tr>

<tr>
<td><b>Delete All</b></td>
<td><code>delete</code>, followed by <code>deleteall confirm</code></td>
</tr>

<tr>
<td><b>Help</b></td>
<td><code>help</code></td>
</tr>

<tr>
<td><b>Exit</b></td>
<td><code>exit</code></td>
</tr>

</table>

## Acknowledgements
* The Intern's Ship is written in Java 11.
* The Intern's Ship uses the following libraries: [JavaFX](https://openjfx.io/),
  [Jackson](https://github.com/FasterXML/jackson), [Junit5](https://github.com/junit-team/junit5), [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX)
* The Intern's Ship is adapted from [addressbook-level3](https://github.com/se-edu/addressbook-level3)
* The Intern's Ship - User Guide references the following projects:
  [addressbook-level4](https://github.com/se-edu/addressbook-level4),
  [InternBuddy](https://github.com/AY2223S2-CS2103T-T14-3/tp/blob/master/docs/UserGuide.md) (for Markdown Formatting
  and Information, Tip and Warning Box)
