Wingman is a **modal** manager for managing crew, flights, locations, pilots, and planes.
It seeks to provide a highly efficient way of resource management for airline managers.

## Table of Contents
- **[How to use this guide?](#how-to-use-this-guide)**
- **[Getting Started](#getting-started)**
- **[Features](#features)**
- **[Command summary](#command-summary)**
- **[FAQ](#faq)**
- **[Other information](#other-information)**

<div style="page-break-after: always;"></div>

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


<div style="page-break-after: always;"></div>

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


<div style="page-break-after: always;"></div>

### Shared Commands

The commands in this section are available across ALL 5 modes.

#### 1. Adding a resource

Use this command when you wish to add a new resource entity (e.g. a new plane that has been added to your fleet)
to Wingman, for you to manage the resource. 
```
add /prefix_A value_A /prefix_B value_B
```
This commands adds an entity of the current resource mode to Wingman's database. For example,
if you are currently in the `plane` mode, then this command will add a new
`plane` to the database. It shall be noted, however, that the parameters that are specified in different modes are different. 

Here are some examples of how the command works in each mode:

##### Crew mode: `add /n Bob /r 1`
Parameters:
- `/n`: the name of the crew.
- `/r`: the rank of the crew.

##### Flight mode: `add /c SQ324`
Parameter:
- `/c` : the code for the flight.

##### Location mode: `add /n Singapore`
Parameter:
- `/n`: name of the location.

##### Pilot mode: `add /n Bob /r 1 /a 32 /g 0 /fh 20100`
Parameters:
- `/n`: the name of the pilot.
- `/r`: the rank of the pilot. Possible values:
  - `1`: Training Captain,
  - `2`: Captain,
  - `3`: Senior,
  - `4`: First Officer,
  - `5`: Second Officer,
  - `6`: Cadet.
- `/a`: the age of the captain.
- `/g`: the gender of the pilot. Possible values:
  - `0`: male
  - `1`: female
  - `2`: other
- `/fh`: the flight hours of the pilot.

##### Plane mode: `add /m A380 /a 12`
Parameters:
- `/m`: model of the plane.
- `/a`: age of the plane.

If the command is valid, upon pressing enter, your application window will be updated as shown below.
The response box describes the addition that was made and the new entity should be displayed in the left list.

<img src="images/AddSuccessPage.jpg" width="2032" alt="Successful plane addition page">


<div style="page-break-after: always;"></div>

#### 2. Deleting a resource

Use this command when you wish to remove a resource entity (e.g. a pilot that has retired)
from Wingman, to keep your database of resources up to date.
```
delete index_number
```
This commands deletes an entity of the current resource mode from Wingman's database. For example,
if you are currently in the `plane` mode, then this command will delete the specified
`plane` from the database.

This command has no variations across modes:

##### All modes: `delete 3`
Parameter:
- `index_number`: the index number of the resource entity you wish to delete. Note that the indexing starts from 0.

You can obtain the index number of an entity from its position in the displayed list.
For instance, Flight Attendant Mary will have an index number of 1 in the image below.
(The index number will be displayed in future updates)

<img src="images/ModeCrewLanding.jpg" width="2032" alt="Mode crew page">


<div style="page-break-after: always;"></div>


### Mode-specific Commands

The commands in this section are only available in some modes.

#### 1. Linking a resource to a location

Use this command when you wish to link a resource entity to a location 
(e.g. a pilot that has just landed in Dubai can have his location set as Dubai, so you are able to identify
the correct pilots when filtering the pilots by location).
```
linklocation /lo location_index /resource_prefix resource_index
```
This command is ONLY available in the following modes: `crew`, `flight`, `pilot` and `plane`.

This commands links an entity of the current resource mode to a specified location entity in Wingman's database.
For example, if you are currently in the `plane` mode, then this command will link a `plane` 
to a specified location entity in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Note that the indexing for `location_index` and `resource_index` starts from 0.

Here are some examples of how the command works in each mode:

##### Crew mode: `linklocation /lo 0 /cr 1`
Parameters:
- `/lo`: the index of the location to which the crew is to be linked to.
- `/cr`: the index of the crew to be linked.

##### Flight mode: `linklocation /fl 0 /from 1 /to 2`
Parameter:
- `/fl`: the index of the flight to be linked.
- `/from`: the index of the location to which the flight is to be linked as departing from.
- `/to`: the index of the location to which the flight is to be linked as arriving at.

##### Pilot mode: `linklocation /lo 0 /pi 1`
Parameters:
- `/lo`: the index of the location to which the pilot is to be linked to.
- `/pi`: the index of the pilot to be linked.

##### Plane mode: `linklocation /lo 0 /pl 1`
Parameters:
- `/lo`: the index of the location to which the plane is to be linked to.
- `/pl`: the index of the plane to be linked.

If the command is valid, Wingman will return a response describing the link that has been made, as shown below:

<img src="images/LinkLocationSuccess.jpg" width="2032" alt="Successful link of plane to location">


<div style="page-break-after: always;"></div>

#### 2. Unlinking a resource from a location
Use this command when you wish to unlink a resource entity from a location
(e.g. when a plane has departed from Milan, you can unlink it from Milan,
so you get the correct information when filtering planes by location).
```
unlinklocation /lo location_index /resource_prefix resource_index
```
This command is ONLY available in the following modes: `crew`, `flight`, `pilot` and `plane`.

This commands unlinks an entity of the current resource mode to a specified location entity in Wingman's database.
For example, if you are currently in the `plane` mode, then this command will unlink a `plane`
from the specified location entity in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Note that the indexing for `location_index` and `resource_index` starts from 0.

Here are some examples of how the command works in each mode:

##### Crew mode: `unlinklocation /lo 0 /cr 1`
Parameters:
- `/lo`: the index of the location from which the crew is to be unlinked from.
- `/cr`: the index of the crew to be unlinked.

##### Flight mode: `unlinklocation /fl 0 /from 1 /to 2`
Parameter:
- `/fl`: the index of the flight to be unlinked.
- `/from`: the index of the departure location from which the flight is to be unlinked.
- `/to`: the index of the arrival location from which the flight is to be unlinked.

##### Pilot mode: `unlinklocation /lo 0 /pi 1`
Parameters:
- `/lo`: the index of the location from which the pilot is to be unlinked from. 
- `/pi`: the index of the pilot to be unlinked.

##### Plane mode: `unlinklocation /lo 0 /pl 1`
Parameters:
- `/lo`: the index of the location from which the plane is to be unlinked from.
- `/pl`: the index of the plane to be unlinked.


<div style="page-break-after: always;"></div>

#### 3. Linking a resource to a flight

Use this command when you wish to link a resource entity to a flight (e.g. when assigning pilots to flights,
you can use this command to link each pilot to a flight).
```
linkflight /fl flight_index /resource_prefix resource_index
```
This command is ONLY available in the following modes: `crew`, `pilot` and `plane`. 
(Note that locations are linked to flights through the `flight` mode,
using the `linklocation` command described [above](#1-linking-a-resource-to-a-location))

This commands links an entity of the current resource mode to a specified flight in Wingman's database. For example,
if you are currently in the `plane` mode, then this command will link a `plane`
to a specified flight in the database. It shall be noted, however, that the parameters that are specified in different modes are different.

Note that the indexing for `flight_index` and `resource_index` starts from 0.

Here are some examples of how the command works in each mode:

##### Crew mode: `linkflight /fl 0 /csd 1 /sfa 2 /fa 3 /tr 4`
Parameters:
- `/fl`: the flight to which the specified crew is to be linked to.
- `/csd`: the index of the crew to be linked as cabin service director for this flight.
- `/sfa`: the index of the crew to be linked as senior flight attendant for this flight.
- `/fa`: the index of the crew to be linked as flight attendant for this flight.
- `/tr`: the index of the crew to be linked as trainee for this flight.

Note: In each command, you only need to fill up **at least** 1 crew related parameter.

##### Pilot mode: `linkflight /fl 0 /pf 1 /pm 2`
Parameters:
- `/fl`: the flight to which the specified pilots are to be linked to.
- `/pf`: the index of the flying pilot to be linked to the flight.
- `/pm`: the index of the monitoring pilot to be linked to the flight.

Note: In each command, you only need to fill up **at least** 1 pilot related parameter.

##### Plane mode: `linkflight /fl 0 /pl 1`
Parameters:
- `/fl`: the flight to which the specified plane is to be linked to.
- `/pl`: the index of the plane to be linked as being used for the flight.


<div style="page-break-after: always;"></div>

#### 4. Unlinking a resource from a flight

Use this command when you wish to unlink a resource entity from a flight (e.g. when a flight has been cancelled,
you can use this command to unlink the crew members from the flight).
```
unlinkflight /fl flight_index /resource_prefix resource_index 
```
This command is ONLY available in the following modes: `crew`, `pilot` and `plane`.
(Note that locations are unlinked from flights through the `flight` mode,
using the `unlinklocation` command described [above](#2-unlinking-a-resource-from-a-location))

This command unlinks an entity of the current resource mode from a specified flight in Wingman's database. For example,
if you are currently in the `plane` mode, then this command will unlink a `plane`
from a specified flight in the database. It shall be noted, however,
that the parameters that are specified in different modes are different.

Note that the indexing for `flight_index` and `resource_index` starts from 0.

Here are some examples of how the command works in each mode:

##### Crew mode: `unlinkflight /fl 0 /csd 1 /sfa 2 /fa 3 /tr 4`
Parameters:
- `/fl`: the flight from which the specified crew is to be unlinked from.
- `/csd`: the index of the crew to be unlinked as cabin service director for this flight.
- `/sfa`: the index of the crew to be unlinked as senior flight attendant for this flight.
- `/fa`: the index of the crew to be unlinked as flight attendant for this flight.
- `/tr`: the index of the crew to be unlinked as trainee for this flight.

Note: In each command, you only need to fill up **at least** 1 crew related parameter.

##### Pilot mode: `unlink /fl 0 /pf 1 /pm 2`
Parameters:
- `/fl`: the flight from which the specified pilots are to be linked from.
- `/pf`: the index of the flying pilot to be unlinked from the flight.
- `/pm`: the index of the monitoring pilot to be unlinked from the flight.

Note: In each command, you only need to fill up **at least** 1 pilot related parameter.

##### Plane mode: `unlink /fl 0 /pl 1`
Parameters:
- `/fl`: the flight from which the specified plane is to be linked from.
- `/pl`: the index of the plane to be unlinked as being used for the flight.

If the command is valid, Wingman will return a response describing how the specified link has been removed.
Wingman will also update the lists in your window, to remove the specified link.

<img src="images/UnlinkSuccessful.jpg" width="2032" alt="Successful link of plane to location">

#### 5. Checking a resource's availability

Use this command when you wish to check a resource's availability (i.e. when deciding to use a resource for flight, you 
can use this command to check whether the resource is already currently linked to another flight).
```
check /id resource_index
```
This command is ONLY available in the following modes: `crew`, `pilot` and `plane`.

This command checks whether an entity of the current resource is currently linked to a flight in Wingman's database.
For example, if you are currently in `plane` mode, then this command will check whether the specified `plane` is linked
to any flights or not and indicate its availability.

This command is the same across `crew`, `pilot`, and `plane`.

Note that the indexing for `resource_index` starts from 0.


<div style="page-break-after: always;"></div>

## Application Commands

### 1. Exiting from Wingman
```
exit
```
This will close the application window and exit the program. 
All your changes to your resources will be saved for you to get back to when you reopen the application.

## Command Summary

| **Action**       | **Format**                                                           | **Examples**                   |
|------------------|----------------------------------------------------------------------|--------------------------------|
| Add              | `add /prefix_A value_A /prefix_B value_B`                            | `add /n Bob /r 2`              |
| Delete           | `delete resource_index`                                              | `delete 1`                     |
| Link location    | `linklocation /lo location_index /resource_prefix resource_index`    | `linklocation /lo 0 /cr 1`     |
| Unlink location  | `unlinklocation /lo location_index /resource_prefix resource_index`  | `unlinklocation /lo 0 /fl 1`   |
| Link flight      | `linkflight /fl flight_index /resource_prefix resource_index `       | `linkflight /fl 0 /pf 1 /pm 2` |
| Unlink flight    | `unlinkflight /fl flight_index /resource_prefix resource_index `     | `unlinkflight /fl 0 /pu 1`     |
| Check            | `check /id resource_index`                                           | `check /id 0`                  |
| Exit             | `exit`                                                               | `exit`                         |


<div style="page-break-after: always;"></div>

## FAQ

### 1. Why is Wingman not opening when I run the `java -jar` command?
- You might be running the command in the wrong directory.
Navigate to the directory where you downloaded the application file and run the same command again. 

## Other information

