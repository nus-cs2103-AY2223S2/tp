# USER GUIDE

## INTRODUCTION

Tutee managing system (TMS) is a **desktop application designed for private tutors managing students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

# TABLE OF CONTENT

[[TOC]]

# FEATURES

## Add student

Adds a student to the managing system.

Format: ```add student n/NAME p/PHONE_NUMBER e/EMAIL s/SUBJECT c/CLASS sch/SCHEDULE```

Examples:

* ```add student n/John Doe p/98765432 e/johnd@example.com s/math c/AdvanceJCMath sch/Monday 2pm - 5pm``` 

## Delete student

Removes a student from the managing system.

Format: ```delete INDEX```

Examples:

* ```delete 1```


## List students

Lists students in the managing system.

Format: ```list```

Examples:

* ```list```

## local save

Saves the current state of the program on the hard disk upon exit.

Done automatically.

## local load

Loads the saved state of the program (if there is any) on the hard disk.

Creates an empty file if there is none.

Done automatically.

## exit program

Exit the program.

Format: ```exit```

