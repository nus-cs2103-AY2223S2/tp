# Wingman User Guide


Wingman is a **modal** manager for managing crew, flights, locations, pilots, and planes.
It seeks to provide a highly efficient way of resource management for airline managers.

## Table of Contents
- **[How to use this guide?](#how-to-use-this-guide)**
- **[Getting Started](#getting-started)**
- **[Features](#features)**
- **[Command summary](#command-summary)**
- **[FAQ](#faq)**
- **[Other information](#other-information)**

-------------------------------------------------------------------------------

## How to use this guide?

This user guide provides in-depth documentation on the multiple features that enable Wingman
to assist you in managing your resources.
We have also included a handy [command summary](#command-summary) where you can view all the commands at a glance.
Refer to the next section to see how you can get started in just a few steps!

## Getting Started

You can get started with Wingman in just 3 simple steps:
1. Click [here](https://github.com/AY2223S2-CS2103T-W11-1/tp/releases/tag/v1.3) to download the Wingman.jar file
2. Open the terminal or command prompt (for Windows users) application in your device
3. Run the following command: `java -jar Wingman.jar` in your terminal

You should now be in the Wingman application. 
If that is not the case, head over to our [FAQ](#faq) section to troubleshoot

<br>

## Features

### Modal Editing

Wingman offers 5 different resource modes through which you can manage your resources:
- `crew` mode: to manage the crews that form your airline workforce
- `flight` mode: to manage the flights that your airline operates
- `location` mode: to manage the locations in which your airline operates
- `pilot` mode: to manage the pilots that form your airline workforce
- `plane` mode: to manage the planes that your airline operates

The different modes offer similar and intuitive commands, with optimisations to cater to the subject
that the mode is managing. This means you do not have to worry about memorising complex commands and instead
can dive right into the management of your airline. As a result of Wingman's modal design, you will be
able to complete a variety of management tasks through singular commands. These commands are detailed
in the subsequent [sections](#shared-commands).

To switch between different modes simply enter the following command:
```
mode XYZ
```
where XYZ can be any of the 5 mode names specified above.

<img src="images/ModeCrewLanding.jpg" width="2032" alt="Switching to mode crew">

As shown in the image above, upon successfully switching to a mode, Wingman will
display the current mode's name in the status bar in the bottom left corner of the window.
The window also displays 2 lists, the left one displaying the entities belonging to the current resource mode
and the right one displaying all the flights that these entities can be linked to.

<br>

### Shared Commands

The commands in this section are available across ALL 5 modes.

### 1. Adding a resource

Use this command when you wish to add a new resource entity (e.g. a new plane that has been added to your fleet)
to Wingman, for you to manage the resource. 
```
add /prefix_A value_A /prefix_B value_B
```
This commands adds an entity of the current resource mode to Wingman's database. For example,
if you are currently in the `plane` mode, then this command will add a new
`plane` to the database. It shall be noted, however, that the parameters that are specified in different modes are different. 

Here are some examples of how the command works in each mode:

#### Crew mode: `add /name Bob /rank 1`
Parameters:
- `/name`: the name of the crew.
- `/rank`: the rank of the crew.

#### Flight mode: `add /code SQ324`
Parameter:
- `/code` : the code for the flight.

#### Location mode: `add /name Singapore`
Parameter:
- `/name`: name of the location.

#### Pilot mode: `add /name Bob /rank 1 /age 32 /gender 0 /fh 20100`
Parameters:
- `/name`: the name of the pilot.
- `/rank`: the rank of the pilot. Possible values:
  - `1`: Training Captain,
  - `2`: Captain,
  - `3`: Senior,
  - `4`: First Officer,
  - `5`: Second Officer,
  - `6`: Cadet.
- `/age`: the age of the captain.
- `/gender`: the gender of the pilot. Possible values:
  - `0`: male
  - `1`: female
  - `2`: other
- `/fh`: the flight hours of the pilot.

#### Plane mode: `add /model A380 /age 12`
Parameters:
- `/model`: model of the plane.
- `/age`: age of the plane.

If the command is valid, upon pressing enter, your application window will be updated as shown below.
The response box describes the addition that was made and the new entity should be displayed in the left list.

<img src="images/AddSuccessPage.jpg" width="2032" alt="Successful plane addition page">

<br>


### 2. Deleting a resource

Use this command when you wish to remove a resource entity (e.g. a pilot that has retired)
from Wingman, so as to keep your database of resources up to date.
```
delete index_number
```
This commands deletes an entity of the current resource mode from Wingman's database. For example,
if you are currently in the `plane` mode, then this command will delete the specified
`plane` from the database.

This command has no variations across modes:

#### All modes: `delete 3`
Parameter:
- `index_number`: the index number of the resource entity you wish to delete. Note that the indexing starts from 0.

You can obtain the index number of an entity from its position in the displayed list.
For instance, Flight Attendant Mary will have an index number of 1 in the image below.
(The index number will be displayed in future updates)

<img src="images/ModeCrewLanding.jpg" width="2032" alt="Mode crew page">

<br>



## Mode-specific Commands

The commands in this section are only available in some modes.

### 1. Linking a resource to a location

Use this command when you wish to link a resource entity to a location 
(e.g. a pilot that has just landed in Dubai can have his location set as Dubai, so you are able to identify
the correct pilots when filtering the pilots by location).
```
linklocation /resource_prefix resource_index /loc location_index
```
This command is ONLY available in the following modes: `crew`, `flight`, `pilot` and `plane`.

This commands links an entity of the current resource mode to a specified location entity in Wingman's database.
For example, if you are currently in the `plane` mode, then this command will link a `plane` 
to a specified location entity in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Here are some examples of how the command works in each mode:

#### Crew mode: `linklocation /crew 0 /loc 1`
Parameters:
- `/crew`: the index of the crew to be linked.
- `/loc`: the index of the location to which the crew is to be linked.

#### Flight mode: `linklocation /flight 0 /loc 1`
Parameter:
- `/flight`: the index of the flight to be linked.
- `/from`: the index of the location to which the flight is to be linked as departing from.
- `/to`: the index of the location to which the flight is to be linked as arriving at.

#### Pilot mode: `linklocation /pilot 0 /loc 1`
Parameters:
- `/pilot`: the index of the pilot to be linked.
- `/loc`: the index of the location to which the pilot is to be linked.

#### Plane mode: `linklocation /pl 0 /loc 1`
Parameters:
- `/pl`: the index of the plane to be linked.
- `/loc`: the index of the location to which the plane is to be linked.

If the command is valid, Wingman will return a response describing the link that has been made, as shown below:

<img src="images/LinkLocationSuccess.jpg" width="2032" alt="Successful link of plane to location">

<br>

### 2. Unlinking a resource from a location
Use this command when you wish to unlink a resource entity from a location
(e.g. when a plane has departed from Milan, you can unlink it from Milan,
so you get the correct information when filtering planes by location).
```
unlinklocation /resource_prefix resource_index /loc location_index
```
This command is ONLY available in the following modes: `crew`, `flight`, `pilot` and `plane`.

This commands unlinks an entity of the current resource mode to a specified location entity in Wingman's database.
For example, if you are currently in the `plane` mode, then this command will unlink a `plane`
from the specified location entity in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Here are some examples of how the command works in each mode:

#### Crew mode: `unlinklocation /crew 0 /loc 1`
Parameters:
- `/crew`: the index of the crew to be unlinked.
- `/loc`: the index of the location from which the crew is to be unlinked.

#### Flight mode: `unlinklocation /flight 0 /loc 1`
Parameter:
- `/flight`: the index of the flight to be unlinked.
- `/from`: the index of the departure location from which the flight is to be unlinked.
- `/to`: the index of the arrival location from which the flight is to be unlinked.

#### Pilot mode: `unlinklocation /pilot 0 /loc 1`
Parameters:
- `/pilot`: the index of the pilot to be unlinked.
- `/loc`: the index of the location from which the pilot is to be unlinked.

#### Plane mode: `unlinklocation /pl 0 /loc 1`
Parameters:
- `/pl`: the index of the plane to be unlinked.
- `/loc`: the index of the location from which the plane is to be unlinked.

<br>

### 3. Linking a resource to a flight

Use this command when you wish to link a resource entity to a flight (e.g. when assigning pilots to flights,
you can use this command to link each pilot to a flight).
```
link /resource_prefix resource_index /fl flight_index
```
This command is ONLY available in the following modes: `crew`, `pilot` and `plane`. 
(Note that locations are linked to flights through the `flight` mode,
using the `linklocation` command described [above](#1-linking-a-resource-to-a-location))

This commands links an entity of the current resource mode to a specified flight in Wingman's database. For example,
if you are currently in the `plane` mode, then this command will link a `plane`
to a specified flight in the database. It shall be noted, however, that the parameters that are specified in different modes are different.

Here are some examples of how the command works in each mode:

#### Crew mode: `link /csd 0 /sfa 1 /fa 2 /tr 4 /fl 2`
Parameters:
- `/csd`: the index of the crew to be linked as cabin service director for this flight.
- `/sfa`: the index of the crew to be linked as senior flight attendant for this flight.
- `/fa`: the index of the crew to be linked as flight attendant for this flight.
- `/tr`: the index of the crew to be linked as trainee for this flight.
- `/fl`: the flight to which the specified crew is to be linked.

Note: In each command, you only need to fill up **at least** 1 crew related parameter.

#### Pilot mode: `link /pf 0 /pm 1 /f1 2`
Parameters:
- `/pf`: the index of the flying pilot to be linked to the flight.
- `/pm`: the index of the monitoring pilot to be linked to the flight.
- `/fl`: the flight to which the specified pilots are to be linked.

Note: In each command, you only need to fill up **at least** 1 pilot related parameter.

#### Plane mode: `link /pu 0 /fl 1`
Parameters:
- `/pu`: the index of the plane to be linked as being used for the flight.
- `/fl`: the flight to which the specified plane is to be linked.

<br>

### 4. Unlinking a resource from a flight

Use this command when you wish to unlink a resource entity from a flight (e.g. when a flight has been cancelled,
you can use this command to unlink the crew members from the flight).
```
unlink /resource_prefix resource_index /fl flight_index
```
This command is ONLY available in the following modes: `crew`, `pilot` and `plane`.
(Note that locations are unlinked from flights through the `flight` mode,
using the `unlinklocation` command described [above](#2-unlinking-a-resource-from-a-location))

This commands unlinks an entity of the current resource mode from a specified flight in Wingman's database. For example,
if you are currently in the `plane` mode, then this command will unlink a `plane`
from a specified flight in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Here are some examples of how the command works in each mode:

#### Crew mode: `unlink /csd 0 /sfa 1 /fa 2 /tr 4 /fl 2`
Parameters:
- `/csd`: the index of the crew to be unlinked as cabin service director for this flight.
- `/sfa`: the index of the crew to be unlinked as senior flight attendant for this flight.
- `/fa`: the index of the crew to be unlinked as flight attendant for this flight.
- `/tr`: the index of the crew to be unlinked as trainee for this flight.
- `/fl`: the flight from which the specified crew is to be unlinked.

Note: In each command, you only need to fill up **at least** 1 crew related parameter.

#### Pilot mode: `unlink /pf 0 /pm 1 /f1 2`
Parameters:
- `/pf`: the index of the flying pilot to be unlinked from the flight.
- `/pm`: the index of the monitoring pilot to be unlinked from the flight.
- `/fl`: the flight from which the specified pilots are to be linked.

Note: In each command, you only need to fill up **at least** 1 pilot related parameter.

#### Plane mode: `unlink /pu 0 /fl 1`
Parameters:
- `/pu`: the index of the plane to be unlinked as being used for the flight.
- `/fl`: the flight from which the specified plane is to be linked.

If the command is valid, Wingman will return a response describing how the specified link has been removed.
Wingman will also update the lists in your window, to remove the specified link.

<img src="images/UnlinkSuccessful.jpg" width="2032" alt="Successful link of plane to location">


<br>

## Application Commands

### 1. Exiting from Wingman
```
exit
```
This will close the application window and exit the program. 
All your changes to your resources will be saved for you to get back to when you reopen the application.

## Command Summary

| **Action**      | **Format**                                                           | **Examples**                       |
|-----------------|----------------------------------------------------------------------|------------------------------------|
| Add             | `add /prefix_A value_A /prefix_B value_B`                            | `add /name Bob /rank 2`            |
| Delete          | `delete resource_index`                                              | `delete 1`                         |
| Link location   | `linklocation /resource_prefix resource_index /loc location_index`   | `linklocation /crew 0 /loc 1`      |
| Unlink location | `unlinklocation /resource_prefix resource_index /loc location_index` | `unlinklocation /flight 0 /loc 1`  |
| Link flight     | `link /resource_prefix resource_index /fl flight_index`              | `link /pf 0 /pm 1 /f1 2`           |
| Unlink flight   | `unlink /resource_prefix resource_index /fl flight_index`            | `unlink /pu 0 /fl 1`               |
| Exit            | `exit`                                                               | `exit`                             |


## FAQ

### 1. Why is Wingman not opening when I run the `java -jar` command?
- You might be running the command in the wrong directory.
Navigate to the directory where you downloaded the application file and run the same command again. 

## Other information

