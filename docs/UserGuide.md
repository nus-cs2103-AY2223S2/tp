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
can dive right into the management of your airline.

Users can switch effortlessly between different modes by typing in the following command:
```
mode XYZ
```
where XYZ can be any of the 5 mode names specified above.

<br>

### Shared Commands

The commands in this section are available across all 5 modes.

### 1. Adding a resource
```
add /prefix_A value_A /prefix_B value_B
```
This commands adds an entity of the current resource mode to Wingman's database. For example,
if the user is currently in the `plane` mode, then this command will add a new
`plane` to the database. It shall be noted, however, that the attributes that are specified in different modes are different. 

Here are some examples of how the command works in each mode:

#### Crew mode: `add /name Bob /rank 1`
Attributes:
- `/name`: the name of the crew.
- `/rank`: the rank of the crew.

#### Flight mode: `add /code SQ324`
Attributes:
- `/code` : the code for the flight.

#### Location mode: `add /name Singapore`
Attributes:
- `/name`: name of the location.

#### Pilot mode: `add /name Bob /rank 1 /age 32 /gender 0 /fh 20100`
Attributes:
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
Attributes:
- `/model`: model of the plane.
- `/age`: age of the plane.

<br>

### `delete {index}`

This command will delete the corresponding model from the database. For
example, if the user wishes to delete the first plane, then he will need to
make sure that the software is in the `plane` mode, and then he will need to
type the command:

```
delete 1
```

Then the plane would be removed from the fleet.

## Mode-specific Commands

> This part is beyond the scope of v1.2, and we will update this once we build
> features related to this part.

## Application Commands

### `exit`

This will exit the program.

## Command Summary

| **Action** | **Format**                               | **Examples**    |
|------------|------------------------------------------|-----------------|
| Add        | `add [{parameter_type} {parameter_val}]` | `add /name Bob` |
| Delete     | `delete {index}`                         | `delete 1`      |
| Exit       | `exit`                                   | `exit`          |


## FAQ

Ask us questions so that we can have a FAQ.

## Other information

