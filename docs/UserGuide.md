---
layout: page
title: User Guide
---

Wingman is a **modal** manager for managing airplanes, pilots, crews, flights,
and locations. It seeks to provide a highly efficient way of resource
management for airline managers.

* Table of Contents
  {:toc}

-------------------------------------------------------------------------------

## Modal Editing

In Wingman, there are 5 different modes underwhich the app is operating:

- `location` mode: the mode in which an airline manager manages the
  locations that the company is operating at.
- `plane` mode: the mode in which an airline manager manages the planes
  that the company is operating.
- `flight` mode: the mode in which the airline manager manages the flights
  that the company will carry out.
- `pilot` mode: the mode in which the airline manager manages its pilots.
- `crew` mode: the mode in which the airline manager manages its crews.

Different modes offer similar APIs, with differences catered to the subject
that the mode is managing. Users can switch to different modes by typing the
corresponding mode name in the very beginning. For example:

```
plane
```

would switch the user to the plane mode.

## Shared Commands

### `add [{param_type} {param_val}]`

This commands adds an entity of the current mode to the database. For example,
if the user is currently in the `plane` mode, then this command will add a new
`plane` to the database. It shall be noted, however, that the params passed in
in different modes are different.

#### Add a `location`

To add a `location` to the locations on which the airline is currently
operating, the user will need to ensure that he is in the `location` mode.

Params:

- `/name`: the name of the location.

Example:

```
add /name SIN
add /name ZRH
add /name KUL
add /name PVG
add /name NKG
```

#### Add a `plane`

To add a `plane` to the fleet, the user will need to ensure that he is in the
`plane` mode.

Params:

- `/model`: the model of the plane.
- `/mdate`: the date that this plane was manufactured. This should be of the
  format `YYYY-MM-DD`, i.e. `2023-01-01`.

Examples:

```
add /model B737 /mdate 2023-01-01
add /model A380 /mdate 2012-07-21
```

#### Add a `flight`

To add a `flight` to the fleet, the user will need to ensure that the software
is in the `flight` mode.

Params:

- `/num`: the flight number of the flight.

Examples:

```
add /num SQ830
add /num LX200
add /num NH802
```

#### Add a `pilot`

To add a `pilot` to the fleet, the user will need to ensure that the software
is in the `pilot` mode.

Params:

- `/name`: the name of the pilot.

Optional Params:

- `/rank`: the rank of the pilot. Possible values:
    - `1`: Training Captain,
    - `2`: Captain,
    - `3`: Senior,
    - `4`: First Officer,
    - `5`: Second Officer,
    - `6`: Cadet.
- `/age`: the age of the captain.
- `/gender`: the gender of the pilot. Possible values:
    - `male`
    - `female`
    - `other`
- `/fh`: the flight hours of the pilot.

Examples:

```
add /name Len Beong /rank 1
add /name Hartin Menz /rank 2 /age 39 /gender male /fh 5000
```

#### Add a `crew`

TO add a `crew` to the fleet, the user will need to ensure that the software is
in the `crew` mode.

Params:

- `/name`: the name of the crew

Optional Params:

- `/age`: the age of the crew
- `/gender`: the gender of the crew. Possible values:
    - `male`
    - `female`
    - `other`

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

## FAQ

Ask us questions so that we can have a FAQ.

## Command Summary

| **Action** | **Format**                               | **Examples**    |
|------------|------------------------------------------|-----------------|
| Add        | `add [{parameter_type} {parameter_val}]` | `add /name Bob` |
| Delete     | `delete {index}`                         | `delete 1`      |
| Exit       | `exit`                                   | `exit`          |

