---
layout: page
title: User Guide
---

## INTRODUCTION

Tutee managing system (TMS) is a **desktop application designed for private tutors managing students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

# TABLE OF CONTENTS

[FEATURES](#features)
1. [Add student](#add)
2. [Delete student](#delete)
3. [List student](#list)
4. [Filter student](#filter)
5. [Add a lesson](#learn)
6. [Remove a lesson](#unlearn)
7. [Local save](#save)
8. [Local load](#load)
9. [Exit program](#exit)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TMS.jar` from [here](https://github.com/AY2223S2-CS2103T-W10-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Tutee Managing System.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all students.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/Math sch/monday st/09:30 et/11:30` : Adds a student named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd student shown in the current list.

   * `clear` : Deletes all students.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## FEATURES 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>
## Add student <a name="add"></a>

Adds a student to the managing system.

Format: ```add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SUBJECT sch/SCHEDULE st/START TIME et/END TIME```
Subject supported: {`Math`, `Physics`, `English`}
Schedule supported: {`monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`}

Examples:

* ```add n/John Doe p/98765432 e/johnd@example.com a/block 224 s/Math sch/monday st/09:30 et/11:30```

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

## Filter students by any fields <a name='filter'></a>

Filter and list students whose fields contain any of the given keywords.

Format: ```filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/SUBJECT] [sch/SCHEDULE] [st/START TIME] [et/END TIME] [t/TAG]```

- Filter is case-insensitive. e.g `n/yeoh` will match `Yeoh`
- Only `NAME`, `ADDRESS`, `TAG` can include more than 1 word.
- At least one of the fields must be provided.
- The order of the keywords does not matter. e.g. `n/Yeoh Alex` will match `Alex Yeoh`
- All fields can be searched and filtered.
- Only full words will be matched e.g. `n/Yeo` will not match `Yeoh`
- When only one field is specified, tutees matching at least one keyword will be returned(i.e. `OR` search). e.g. `a/blk street` will return `Blk 313`, `street 29`
- When more than one field is specified, only tutees whose keywords matching all fields provided will be returned
e.g. `filter s/math sch/monday` will only return tutees whose lessons are on `monday` AND subject `math`

Examples:
- `filter sch/tuesday` returns tutees whose lessons are on tuesday.
- `filter a/clementi s/math` returns tutees whose address are in `clementi` and being tutored `math` subject.
![img.png](images/filterExampleResult.png)

## Add a new lesson <a name="learn"></a>

Add a lesson taught to a student.

Format: ```learn [INDEX] [l/LESSON]```

Examples:

* ```learn 1 l/Rational number```
![learn.png](images/learnExample.PNG)

## Remove a lesson <a name="unlearn"></a>

Remove a lesson taught to a student.

Format: ```unlearn [INDEX] [l/LESSON]```

Examples:

* ```unlearn 1 l/Rational number```
  ![unlearn.png](images/unlearnExample.PNG)

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

