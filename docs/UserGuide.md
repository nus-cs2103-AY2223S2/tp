---
layout: page
title: User Guide
---

## INTRODUCTION

Tutee managing system (TMS) is a **desktop application designed for private tutors managing students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

# TABLE OF CONTENT

[FEATURES](#features)
- [TABLE OF CONTENT](#table-of-content)
- [FEATURES ](#features-)
  - [Add student ](#add-student-)
  - [Delete student ](#delete-student-)
  - [List students ](#list-students-)
  - [Local save ](#local-save-)
  - [Local load ](#local-load-)
  - [Exit program ](#exit-program-)
  - [Mark/Unmark attendance](#markunmark-attendance)
  - [Query Command](#query-command)

# FEATURES <a name="features"></a>

## Add student <a name="add"></a>

Adds a student to the managing system.

Format: ```add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SUBJECT sch/SCHEDULE```
Subject supported: {`Math`, `Physics`, `English`}
Schedule supported: {`monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`}

Examples:

* ```add n/John Doe p/98765432 e/johnd@example.com a/block 224 s/Math sch/monday```

## Delete student <a name="delete"></a>

Removes a student from the managing system.

Format: ```delete INDEX```

Examples:

* ```delete 1```


## List students <a name="list"></a>

Lists students in the managing system.

Format: ```list```

Examples:

* ```list```

## Local save <a name="save"></a>

Saves the current state of the program on the hard disk upon exit.

Done automatically.

## Local load <a name="load"></a>

Loads the saved state of the program (if there is any) on the hard disk.

Creates an empty file if there is none.

Done automatically.

## Exit program <a name="exit"></a>

Exit the program.

Format: ```exit```

## Mark/Unmark attendance
Use `mark` to indicate that the tutee was present on the given dates, `unmark` to indicate that
they were absent. If a date is not specified, the current date is used.\
If the tutee was already absent or present, the command will have no effect.

Format: `mark/unmark <index> [date...]`

## Query Command
Use this command to check the tutee's attendance. If no date is given, all of the dates that tutee was present on.\
Otherwise, the command will return if the tutee was present on the given date.

Format: `query <index> [date]`