---
layout: page
title: User Guide
---
## Table of Contents
* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

## **The Intern's Ship - User Guide**
<p align="center">
    <img src="images/ship.png" alt="logo" width="90"><br>
</p>

Designed with **internship-seeking university students** in mind, **The Intern’s Ship (TinS)** aims to make
managing your internship applications fuss-free. Optimized for 


Designed with internship-seeking <b>university students</b> in mind, <b>The Intern’s Ship (TinS)</b> aims to make
managing your internship applications fuss-free. Optimized for use via a Command Line Interface (CLI), while still having
the benefits of a Graphical User Interface (GUI), this <b>desktop app</b> is perfect for fast typers. With a
simple command, TinS can help you manage, coordinate and keep track of your internship applications conveniently and
neatly.<br>

### Notable Features (v1.3)
  * Finding a particular internship application in TinS using keywords: `find`
  * Finding all clashing events: `clash`


<div style="page-break-after: always;"></div>

## About the User Guide
The Objective of this User Guide is to provide you with useful instructions on how to navigate the TinS application.
To ensure a smooth on-boarding process for you to the TinS application, this User Guide will include detailed
step-by-step instructions on how to install the TinS application into your device, as well as how to use the various 
features of TinS. If you are a frequent user of TinS, this User Guide will also include useful tips on how you can use
TinS in a more efficient way.

## How the User Guide is Formatted
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

<div style="page-break-after: always;"></div>

<h2 id="about_gui">About TinS Application's Interface</h2>

![gui](images/gui.png)

1. **Command Box**: This is where you would input your commands.
2. **Program Response**: This box displays the program's feedback to your inputted commands.<br>
   Types of Program Response Messages:
   * Success Message: indicating that TinS has successfully executed your command
   * Error Message: indicating the reason why TinS was not about to execute your command and tips on what you can do to
   correct the error
3. **Internship List Panel**: This panel displays your internships listings stored in TinS (including Position,
   Company, Status and Tags).
4. **Internship Display Panel**: This panel displays relevant details pertaining to specific internship.
5. **Location Bar**: This location bar tells you where your TinS data file is currently stored on your computer.

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tins.jar` from [here](https://github.com/AY2223S2-CS2103T-W11-2/tp/releases/tag/v1.3).

3. Copy the file to the folder you want to use as the _home folder_ for your TinS.

4. Double-click on the file `tins.jar` to launch TinS. 
   A TinS Application Interface similar to the one below should appear in a few seconds.
   ![Ui](images/Ui-starting.png)

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

## Features (v1.3)

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION`, `POSITION` is a parameter which can be
  used as `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java`.

* Items in square brackets are optional.<br>
  e.g `[TAG]` means that the user input is optional.


### Feature List

1. Opening Home Page: [`home`](#home_function)
2. Opening Statistics Page: [`stats`](#stats_function)
3. Opening Calendar Page: [`calendar`](#calendar_function)
4. Adding an Internship Application: [`add`](#add_function)
5. Listing all Internship Applications in TinS: [`list`](#list_function)
6. Finding a Particular Internship Application in TinS: [`find`](#find_function)
7. Editing Details of an Internship Application: [`edit`](#edit_function)
8. Viewing Details of a Particular Internship Application: [`select`](#select_function)
9. Deleting an Internship Application: [`delete`](#delete_function)
10. Adding an Event to an Internship Application: [`event add`](#eventadd_function)
11. Deleting an Event from an Internship Application: [`event delete`](#eventdelete_function)
12. Finding a Particular Event: [`event find`](#eventfind_function)
13. Finding all Clashing Events: [`clash`](#clash_function)
14. Clearing all Internship Applications in TinS: [`clear`](#clear_function)
15. Getting help: [`help`](#help_function)
16. Exiting the program: [`exit`](#exit_function)
17. [Saving data in TinS](#save_function)

<div style="page-break-after: always;"></div>

### Opens the Home Page : `home`</h3>

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

<h3 id="eventdelete_function">Deleting an Event from an Internship Application : `event delete`</h3>

Deletes selected Event along with its details from the selected internship.

> Note: Before deleting an event, you have to select an internship using `select ID`

Format: `event delete ID`
* `ID`: The ID of the selected event in the list

Examples: `event delete 1` entered after `select 1` deletes the first event in the first internship.

<div style="page-break-after: always;"></div>

<h3 id="eventfind_function">Finding a particular Event : `find event`</h3>

Finds events based on certain filters.

Format: `event add na/[EVENT_NAME] st/[START_DATETIME] en/[END_DATETIME]`

Example: `event add na/Technical Interview st/20/03/2023 1400` returns a list of events with the name Technical
Interview and starting time 20 March 2023, 2pm.

<div style="page-break-after: always;"></div>

<h3 id="clash_function">Finding all Clashing Events : `clash`</h3>

Displays all events with clashing timings in the [Internship Display Panel](#about_gui).

Format: `clash`

Example:

![clash_function](images/ug-clash.png)

<div style="page-break-after: always;"></div>

<h3 id="clear_function">Clearing all Internship Applications in TinS : `clear`</h3>

Deletes all existing Internship Applications in TinS.

> Caution: This command is irreversible. Only use it when you would like to clear <b>all</b> internship applications in
> your Internship Catalogue.

Format: `clear`

<h3 id="help_function">Getting Help : `help`</h3>

Provides you with the link to [The Intern's Ship - User Guide](#intro).

Format: `help`

<h3 id="exit_function">Exiting the program : `exit`</h3>

Exits and closes the TinS application.

Format: `exit`

<h3 id="save_function">Saving the data</h3>

The Intern Ship’s data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

<h2 id="command_summary">Command summary</h2>

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
