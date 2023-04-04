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

### Notable Features (v1.3)
  * Finding a particular internship application in TinS using keywords: `find`
  * Finding all clashing events: `clash`
  
  
<div style="page-break-after: always;"></div>

## About the User Guide

### Objective of User Guide
The Objective of this User Guide is to provide you with useful instructions on how to navigate the TinS application.
To ensure a smooth on-boarding process for you to the TinS application, this User Guide will include detailed
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
    
#### Buttons

Being optimized for use via typing, you can key in your desired commands to TinS using the keyboard.
Then, press the <button>Enter</button> to confirm your command and instruct TinS to execute them.

### How to use the User Guide
* If you **have not installed TinS** or **are new to TinS**, [Quick Start](#quick-start) will guide you through the process of
installing TinS and provide you with a brief introduction to TinS. 
* If you are **a more advanced user of TinS**, the [Features](#features) section contains detailed information of what features
TinS has to offer. Embedded in this section, there are also tips on how you can maximize your efficiency while using TinS. 
* If you are **encountering an issue** with TinS, the [Frequently Asked Question](#frequently-asked-question) section might 
be helpful for you.

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
    * `list`
    * `add`
    * `delete`

6. Refer to the [Command Summary](#command_summary) below for your desired TinS Commands. More details on each specific
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
  TinS are written in capitalised letters.

An Internship can also store zero or more than one **Event**. 

**Events**
In TinS, an **Event** can be used to represent a **Deadline** or an **Interview** associated to an Internship. Within
an Event, you would be able to store the following fields:
* `EVENT_NAME`: The Name of the Event.
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

**Important Information for Use of Prefixes**
*

#### Notes about the Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION`, `POSITION` is a parameter which can be
  used as `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java`.

* Items in square brackets are optional.<br>
  e.g `[t/TAG]` means that the user input is optional.



<div style="page-break-after: always;"></div>

## Features

### Feature List

1. Opening Home Page: [`home`](#opens-the-home-page-:-home)
2. Opening Statistics Page: [`stats`](#stats_function)
3. Opening Calendar Page: [`calendar`](#calendar_function)
4. Adding an Internship Application: [`add`](#add_function)
6. Listing all Internship Applications in TinS: [`list`](#list_function)
7. Finding a Particular Internship Application in TinS: [`find`](#find_function)
8. Editing Details of an Internship Application: [`edit`](#edit_function)
9. Viewing Details of a Particular Internship Application: [`select`](#select_function)
10. Deleting an Internship Application: [`delete`](#delete_function)
11. Adding an Event to an Internship Application: [`event add`](#eventadd_function)
12. Deleting an Event from an Internship Application: [`event delete`](#eventdelete_function)
13. Finding a Particular Event: [`event find`](#eventfind_function)
14. Finding all Clashing Events: [`clash`](#clash_function)
15. Clearing all Internship Applications in TinS: [`clear`](#clear_function)
16. Getting help: [`help`](#help_function)
17. Exiting the program: [`exit`](#exit_function)
18. [Saving data in TinS](#save_function)

<div style="page-break-after: always;"></div>

### Opens the Home Page : `home`

Returns user to the home page. 

> Note: Command can also be used to refresh home page.

* After keying in the `home` command, TinS displays the home page.
* Home page shows the today's events and useful commands.

Example: `home`

![home_page](images/home_page.png)

<div style="page-break-after: always;"></div>

### Opens the Statistics Page : `stats`

Displays the user's statistics.

> Note: Command can also be used to refresh stats page.

* After keying in the `stats` command, TinS displays users statistics on the stats page.
* Summary shows pie chart of current internships' statuses.

Example: `stats`

![stats_page](images/stats_page.jpeg)

<div style="page-break-after: always;"></div>

### Opens the Calendar Page : `calendar`

Displays the user's events on a Calendar.

> Note: Command can also be used to refresh calendar page.

* After keying in the `calendar` command, TinS displays user's events on the calendar page.

Example: `calendar`

![calendar_page](images/calendar_page.png)

<div style="page-break-after: always;"></div>

### Adding an Internship Application : `add`

Adds an internship and its details to TinS.

Format: `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION [t/TAG] ...`

* `POSITION`: Name of Internship Position
* `COMPANY_NAME` : Name of Hiring Company

> Note: `POSITION` and `COMPANY_NAME` have to be unique. <br>
> If there is an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab` already saved in TinS:
> * Adding an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab` is not valid
> * Adding an Internship with `POSITION` as `Software Engineer 1` and `COMPANY_NAME` as `Grab` is valid
> * Adding an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab 1` is valid

* `APPLICATION_STATUS` : Status of Application
    * `APPLICATION_STATUS` should be an Integer value from 0 to 3. Here are the statuses for the corresponding integer
      values:
        * `0` : Interested
        * `1` : Applied
        * `2` : Offered
        * `3` : Rejected
* `DESCRIPTION` : Additional details about the Internship (E.g. Contact Details, Link to Webpage, Requirements of
  Internship)
* `TAG` : Customised Tag
    * This is optional.
    * An Internship can have more than one tag.
    * Example of commonly used Tags: `Important`, `Priority`

<div style="page-break-after: always;"></div>

Example: `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java t/Important t/Priority`

![Ui](images/ug-add.png)

<div style="page-break-after: always;"></div>

### Listing all Internships Applications : `list`

List all the internships in Internship List panel of TinS.

* After keying in the `list` command, TinS displays **all** the Internships stored in TinS in the Internship List panel.
* Only the `POSITION`, `COMPANY_NAME`, `APPLICATION_STATUS` and `TAG` are display in the
[Internship List Panel](#about_gui) for each Internship.

Example: `list` 

![internship_list_panel](images/internship_list_panel.png)

<div style="page-break-after: always;"></div>

### Finding Internships: `find`

Finds an Internships by their fields (`POSITION`, `COMPANY_NAME`, `APPLICATION_STATUS`, `TAGS`).
Format: `find p/[POSITION] c/[COMPANY_NAME] s/[APPLICATION_STATUS] t/[TAG] t/[TAG]`

* Choose the fields you would like to filter the Internship Catalogue by. One or more fields can be used to filter
  the Internship Catalogue.
* For each chosen field, enter a keyword you would like to find corresponding Internship Applications for.
* After keying in the `find` command, TinS will filter the internship catalogue based on your chosen fields and
  provided keywords.
* To view all Internship Applications in TinS again, simply enter the `list` command.

Example: Default TinS has 6 internship listings. `find c/GovTech` would return the list of internships with company name
GovTech.

![find_internship](images/ug-find.png)

<div style="page-break-after: always;"></div>

### Viewing Details of a Particular Internship Application : `select`

View details of the internship application selected by ID.

Format: `select ID`

* `ID`: The number of the selected internship in the [Internship List Panel](#about_GUI)
* After keying in the `select` command, TinS will return the all details of the selected internship in the 
  [Internship Display Panel](#about_GUI).

Example: `select 2`

![select_internship](images/ug-select.png)

<div style="page-break-after: always;"></div>

### Editing Details of Internship Application : `edit`

Edit details of the internship application selected by ID.

Format: `edit ID p/[POSITION] c/[COMPANY_NAME] s/[APPLICATION_STATUS] d/[DESCRIPTION] t/[TAG] ...`

* `ID`: Identification number of the Internship.
* `[POSITION]`: Name of Internship Position (optional).
* `[COMPANY_NAME]` : Name of hiring company (optional).
* `[APPLICATION_STATUS]` : Status of Application (`ACCEPTED`, `APPLIED`, `PENDING`, `REJECTED`) (optional).
* `[DESCRIPTION]`: Additional details of the internship application (optional).
* `[TAG]`: Labels for the internship application (optional).
* After keying in the `edit` command, along with the selected fields the user wishes to edit, TinS will update the
  corresponding fields of the internship application accordingly.

Example: <br>
After keying in the `edit` command followed by an existing `ID` and the fields `APPLICATION_STATUS` and `DESCRIPTION`, 
TinS will update the `APPLICATION_STATUS` and `DESCRIPTION` fields of the internship application with the specified 
`ID`, then display the newly updated internship application. This command `edit 1 s/1 d/Learn C++` will return the
following results.

![view_internship](images/ug-edit.png)

<div style="page-break-after: always;"></div>

### Deleting an Internship Application : `delete`

Deletes an internship along with its details by its ID.

Format: `delete ID`
* Deletes the person at the specified `ID` in the [Internship List Panel](#about_GUI).

Example: `delete 3` deletes the 3rd internship listed in the [Internship List Panel](#about_GUI). In this example, the
internship application with `POSITION` as Data Analytics and `COMPANY_NAME` as Google is removed.

![delete_internship](images/ug-delete.png)

<div style="page-break-after: always;"></div>

### Adding an Event to an Internship Application : `event add`

Adds an Event along with its details to the selected internship.

> Note: Before adding an event, you have to select an internship using `select ID`.

Format: `event add na/EVENT_NAME st/[START_DATETIME] en/END_DATETIME de/DESCRIPTION`

* `EVENT_NAME`: Name of the Event
* `START_DATETIME`: Starting time of an event (optional) (in the format: DD/MM/YYYY HHMM)
* `END_DATETIME`: Ending time of an event (in the format: DD/MM/YYYY HHMM)
* `DESCRIPTION`: Description of the Event (e.g. Venue, Things to take note of)

Examples:
1. If you would like to add an Internship Application Submission Deadline:
  `event add na/Application Submission Deadline en/14/04/2023 2359 de/Via Talent Connect`
2. If you would like to add an Interview:
  `event add na/Technical Interview st/10/05/2023 1500 en/10/05/2023 1700 de/On Zoom`

![eventadd_internship](images/ug-eventadd.png)

<div style="page-break-after: always;"></div>

### Deleting an Event from an Internship Application : `event delete`

Deletes selected Event along with its details from the selected internship.

> Note: Before deleting an event, you have to select an internship using `select ID`

Format: `event delete ID`
* `ID`: The ID of the selected event in the list

Examples: `event delete 1` entered after `select 1` deletes the first event in the first internship.

<div style="page-break-after: always;"></div>

### Finding a particular Event : `event find`

Finds events based on certain filters.

Format: `event add na/[EVENT_NAME] st/[START_DATETIME] en/[END_DATETIME]`

Example: `event add na/Technical Interview st/20/03/2023 1400` returns a list of events with the name Technical
Interview and starting time 20 March 2023, 2pm.

<div style="page-break-after: always;"></div>

### Finding all Clashing Events : `clash`

Displays all events with clashing timings in the [Internship Display Panel](#about_gui).

Format: `clash`

Example:

![clash_function](images/ug-clash.png)

<div style="page-break-after: always;"></div>

### Clearing all Internship Applications in TinS : `deleteall`

Deletes all existing Internship Applications in TinS.

> Caution: This command is irreversible. Only use it when you would like to clear <b>all</b> internship applications in
> your Internship Catalogue.

Format: `deleteall`

### Getting Help : `help`

Provides you with the link to The Intern's Ship - User Guide.

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
<td><b><a href="#add_function">Add</a></b></td>
<td><code>add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION [t/TAG] ...</code><br>
E.g. <code>add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python t/Important t/Priority</code></td>
</tr>

<tr>
<td><b><a href="#list_function">List</a></b></td>
<td><code>list</code></td>
</tr>

<tr>
<td><b><a href="#edit_function">Edit</a></b></td>
<td><code>edit ID [p/POSITION] [c/COMPANY_NAME] [s/APPLICATION_STATUS] [d/DESCRIPTION] [t/TAG] ...</code><br>
E.g. <code>edit 1 p/Data Analyst</code> edits the <code>POSITION</code> of first internship in the Internship List panel
to Data Analyst</td>
</tr>

<tr>
<td><b><a href="#delete_function">Delete</a></b></td>
<td><code>delete ID</code><br>
E.g. <code>delete 1</code> deletes the first internship in the Internship List panel</td>
</tr>

<tr>
<td><b><a href="select_function">Select</a></b></td>
<td><code>select ID</code><br>
E.g. <code>select 1</code> selects the first internship in the Internship List panel</td>
</tr>

<tr>
<td><b><a href="#eventadd_function">Add Event</a></b></td>
<td><code>event add na/EVENT_NAME st/[START_DATETIME] en/END_DATETIME de/DESCRIPTION</code><br>
E.g. <code>event add na/Technical Interview st/10/09/2023 1500 en/10/09/2023 1700 de/On Zoom</code></td>
</tr>

<tr>
<td><b><a href="#eventdelete_function">Delete Event</a></b></td>
<td><code>event delete ID</code><br>
E.g. <code>event delete 1</code></td>
</tr>

<tr>
<td><b><a href="#clash_function">Clash</a></b></td>
<td><code>clash</code></td>
</tr>

<tr>
<td><b><a href="#clear_function">Clear</a></b></td>
<td><code>clear</code></td>
</tr>

<tr>
<td><b><a href="#help_function">Help</a></b></td>
<td><code>help</code></td>
</tr>

<tr>
<td><b><a href="#exit_function">Exit</a></b></td>
<td><code>exit</code></td>
</tr>

</table>
