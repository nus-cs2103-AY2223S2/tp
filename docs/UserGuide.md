---
layout: page
title: User Guide
---
<p align="center">
<img src="images/logo.png">
</p>

* Table of Contents<br>
  * [Introduction](#introduction)
  * [Quickstart](#quick-start)
  * [Features](#features)
    * [Help](#viewing-help--help)
    * [Tanks](#tanks)
      * [Adding a tank `tank add`](#adding-a-tank-add)
      * [Deleting a tank `tank delete`](#deleting-a-tank-delete-tank)
      * [Listing tanks `list tanks`](#listing-tanks-list-tanks)
    * [Fishes](#fishes)
      * [Adding a fish `fish add`](#adding-a-fish-add)
      * [Deleting a fish `fish delete`](#deleting-a-fish-delete-fish)
      * [Listing fishes `list fishes`](#listing-fishes-list-fishes)
      * [Listing fishes in a tank `list fish /tank`](#listing-fishes-in-a-tank-list-fish-tank)
    * [Tasks](#tasks)
      * [Adding a task `task add`](#adding-a-task-add)
      * [Deleting a task `task delete`](#deleting-a-task-delete-task)
      * [Listing tasks `list task`](#listing-tasks-list-task)
  * [FAQ](#faq)
  * [Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

# Introduction

Welcome to the *Fish Ahoy! User Guide*! <br>

*Fish Ahoy!* Is a **desktop app for managing your fish, fish tanks, and relevant tasks, optimized for use via a Command
Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

*Fish Ahoy!* **simplifies** the fish keeping experience by helping you keep track of your many **fishes**, **tanks** and 
**weekly tasks**, such as feeding and cleaning. 

Choose a feature from our table of contents above to find answers and get step-by-step instructions on how to make 
*Fish Ahoy!* work for you! Else, follow our [Quick Start Guide](#quick-start) below to get started.

This guide will explain how you can use *Fish Ahoy!* for your own fish keeping activities. It is designed to 
cater to all levels of fish keeping expertise, from novice to expert fish keepers. It does not assume any prior 
knowledge of fish keeping, only the simplest components of an aquarium such as a tank, fish, and basic fish keeping 
equipment.

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest FishAhoy.jar from [here](https://github.com/AY2223S2-CS2103T-T17-4/tp/releases).
3. Copy the file to the folder you want to use as the home folder for Fish Ahoy!.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar FishAhoy.jar` command to run the application.

A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it.
   Some examples:
  * `fish add n/bobby lfd/01/01/2023 s/guppy fi/0d5h tk/1`: Add a fish to your fish book.


6. Refer to the [Features](#Features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>


</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Tanks

Tanks form the basis of *Fish Ahoy!*, as most, if not all our activities revolve around them. Aquarium tanks are the 
first requirement of fish keeping. (Can't have a fish without a tank!) Similarly, *Fish Ahoy!* also requires you to add
a tank first, before allowing you to add in any fishes. These tanks directly represent your tanks in real life, so 
customize them with their own names and add in their respective fishes! <br>

Currently, there are three operations around tanks:
* [Adding a tank `tank add`](#adding-a-tank-add)
* [Deleting a tank `tank delete`](#deleting-a-tank-delete-tank)
* [Listing tanks `list tanks`](#listing-tanks-list-tanks)

### Adding a tank: `add`

Adds a tank to the app.

Format: `tank add d/<TANK_NAME>`

### Listing tanks: `list tanks`

Lists all tanks created.

Format: `list tanks`

### Deleting a tank: `delete tank`

Delete a tank entry from the app.

Format: `tank delete <TANK_INDEX>`

## Fishes

### Adding a fish: `add`

Adds a fish to the app.

Format: `add fish n/<FISH_NAME> lfd/<LAST_FED_DATE> s/<SPECIES> fi/<FEEDING_INTERVAL> tk/<TANK_INDEX> [tg/<TAG>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A fish can belong to a tank
</div>

### Deleting a fish: `delete fish`

Deletes a fish entry from the app.

Format: `fish delete <FISH_INDEX>`

### Listing fishes: `list fishes`

Lists all fishes owned.

Format: `list fishes`

### Listing fishes in a tank: `list fish /tank`

Lists all fishes in a specific tank.

Format: `list fish /tank <TANK_NAME>`

## Tasks

### Adding a task: `add`

Adds a task to the app.

Format: `task add d/<TASK_NAME>`

### Listing tasks: `list task`

Lists all tasks created.

Format: `list task`

### Deleting a task: `delete task`

Delete a task entry from the app.

Format: `task delete <TASK_INDEX>`

### Saving the data

App data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

App data are saved as a JSON file `[JAR file location]/data/fishahoy.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Fish Ahoy! will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Fish Ahoy! home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `fish add` etc.
**Clear** | `clear` **Coming soon**
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | **Coming soon**
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> **Coming soon**
**List** | `list`
**Help** | `help`

[]: #listing-tanks-list-tank
