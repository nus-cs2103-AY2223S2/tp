---
layout: page
title: User Guide
---

_Ez-Schedule_ is a **desktop application for managing and scheduling of events, optimized for use via a Command Line
Interface** (CLI) while still providing an easy way to visualize all events through a Graphical User Interface (GUI).

<h1 id="index">Index</h1>
-------------------------
* [Quick Start](#quick-start)
* [Features](#features)
  * [Command summary](#command-summary)
    * [Add event](#add) : `add` command
    * [Repeat existing event](#recur) : `recur` command
    * [Delete event](#delete) : `delete` command
    * [List all events](#list) : `list` command
    * [Retrieve next event](#next) : `next` command
    * [Locate events by keyword](#find) : `find` command
    * [View help](#help) : `help` command
    * [Exit application](#exit) : `exit` command
  * GUI
    * Command Entry
    * Information Box
    * Upcoming Events
    * Calendar
* [Limitations](#limitations)
* [FAQ](#faq)


<h1 id="quick-start">Quick Start</h1>
-------------------------------------
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Ez-Schedule.jar` from [here](https://github.com/AY2223S2-CS2103-W17-3/tp/releases).
3. Copy the file to the folder you want to use as the *home folder* for your EzSchedule.
4. Open a command terminal, `cd` into the folder you put the jar file in
5. Use the `java -jar Ez-Schedule.jar` command to run the application.


<h1 id="features">Features</h1>
-------------------------------

<h2 id="command-summary">Command Summary</h2>

| Action |                    Command Format                    |
|:-------|:----------------------------------------------------:|
 | Add    | `add n/<event> d/<date> s/<start time> e/<end time>` |
| Delete |                   `delete <index>`                   |
| List   |                        `list`                        |
| Next   |               `next` or `next <count>`               |
| Find   |                   `find <keyword>`                   |
| Help   |                        `help`                        |
| Exit   |                        `exit`                        |

[[Back to top](#index)]


<h3 id="add">Add Event</h3>

Adds an event into the Scheduler.

Format: `add n/<event> d/<date> s/<start time> e/<end time>`

Parameters:  
`n/`: Name or description of event  
`d/`: Date of the event in `YYYY-MM-DD` format  
`s/`: Start time of the event in `HH:mm` format  
`e/`: End time of the event in `HH:mm` format

> Note:  
> Name/Description is alphanumeric only.  
> Start time should come before end time.

Example: `add n/booked tennis court /2023-02-01 /2023-02-02`

[[Back to top](#index)]


<h3 id="recur">Repeat Existing Event</h3>

[[Back to top](#index)]



<h3 id="delete">Delete Event</h3>

Deletes an event in the Scheduler.

Format: `delete <event>`

Example: `delete booked tennis court `

[[Back to top](#index)]


<h3 id="list">Listing All Events</h3>

Displays all events currently saved in the Scheduler.

Format: `list`

Example: `list`

[[Back to top](#index)]


<h3 id="next">Retrieve next event</h3>

Searches the Scheduler and returns the next upcoming event start time.

Format: `next event`

Example: `next event`<br><br>

[[Back to top](#index)]



<h3 id="find">Locate events by keyword</h3>

Finds the Scheduler for the task, returns all events containing the relevant keyword.

Format: `find <event>`

Example: `find booked tennis court`<br><br>

[[Back to top](#index)]



<h3 id="help">View Help</h3>

Provide a link to access this website.

Format: `help`

Example: `help`<br><br>

[[Back to top](#index)]


<h3 id="exit">Exit Application</h3>

Exits the application.

Format: `exit`

Example: `exit`<br><br>

[[Back to top](#index)]


<h1 id="limitations">Limitations</h1>

* Currently, we only support same-day events.
  Events which are stretched across multiple days are not supported.

[[Back to top](#index)]


<h1 id="faq">FAQ</h1>

**Q:** On a scale of 1 to 10, how awesome is this product?

**A:** 10<br><br>

[[Back to top](#index)]