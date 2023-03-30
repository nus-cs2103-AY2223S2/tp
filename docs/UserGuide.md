---
layout: page
title: User Guide

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TrAcker.jar` from [here](https://github.com/AY2223S2-CS2103-F11-1/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your TrAcker.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TrAcker.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   ![Ui](images/Ui2.png)
   ![Ui](images/Ui3.png)
   ![Ui](images/Ui4.png)

--------------------------------------------------------------------------------------------------------------------
User Guide

TrAcker is a **desktop app for CS2040 Teaching Assistants to centralise the CS2040 tasks involving them.
It is optimised for use via a Command Line Interface (CLI)** while still having benefits of a Graphical User Interface (GUI).
If you can type fast, TrAcker will aid the task management for CS2040 TAs. Commands are similar to vim / terminal commands since
CS2040 TAs are familiar with it

**Features**
--------------------------------------------------------------------
--------------------------------------------------------------------
*Event CRUD Features*

### Adding a tutorial: `add tutorial`

Adds a tutorial to the TA’s schedule. Tutorial is assumed to be 1 hour long (adhering to CS2040 Tutorial timing) and no modification of duration is allowed

- Name need not be unique
- Cannot be clashes in the time with any other events
- Only .pdf file attachments allowed
- FULL file path to a VALID pdf can be given
- dd/MM/yyyy all in numbers
- Tutorial name cannot have consultation or lab in it
- Tutorial name cannot be just Tutorial alone
- date and attachment is optional. Date will assume the current time if not specified

Format: `touch Tutorial/[name] -date [dd/MM/yyyy HH:mm] -file [FULL_FILE_PATH_TO_PDF]`

```
date format: dd/MM/yyyy HH:mm
```

Examples:

* `touch Tutorial/makeUpTutorial`
* `touch Tutorial/examReview -date 01/01/2023 16:00`
* `touch Tutorial/examReview -date 01/01/2023 16:00 -file /Users/JohnDoe/Desktop/Introduction.pdf`

### Adding a lab: `add lab`

Adds a lab to the TA’s schedule. Lab is assumed to be 1 hour long (adhering to CS2040 Lab timing) and no modification of duration is allowed

- Name need not be unique
- Cannot be clashes in the time with any other events
- Only .pdf file attachments allowed
- FULL file path to a VALID pdf can be given
- dd/MM/yyyy all in numbers
- Lab name cannot have tutorial or consultation in it
- Lab name cannot be just Lab alone
- date and attachment is optional. Date will assume the current time if not specified

Format: `vim Lab/[name] -date [dd/MM/yyyy HH:mm] -file [FULL_FILE_PATH_TO_PDF]`

```
date format: dd/MM/yyyy HH:mm
```

Examples:

* `vim Lab/pancakeSort`
* `vim Lab/KosarajuAlgorithm -date 01/01/2023 16:00`
* `vim Lab/StronglyConnected -date 01/01/2023 16:00 -file /Users/JohnDoe/Desktop/StronglyConnectedComponents.pdf`

### Adding a consultation: `add consultation`

Adds a consultation to the TA’s schedule. Consultation is assumed to be 1 hour long (adhering to CS2040 Consultation timing) and no modification of duration is allowed

- Name need not be unique
- Cannot be clashes in the time with any other events
- dd/MM/yyyy all in numbers
- Consultation name cannot have tutorial or lab in it
- Consultation name cannot be just Consultation alone
- date is optional. Date will assume the current time if not specified
- No attachments allowed

Format: `mkdir Consultation/[name] -date [dd/MM/yyyy HH:mm]`

```
date format: dd/MM/yyyy HH:mm
```

Examples:

* `mkdir Consultation/reviewConnectedComponents`
* `mkdir Consultation/reviewDijsktra -date 01/01/2023 16:00`

### Adding a recurring event: `add recur`

Adds a recurring event to the TA’s schedule. Recurring event is assumed to be 1 hour long (adhering to CS2040 timing) and no modification of duration is allowed

- Name need not be unique
- Cannot be clashes in the time with any other events
- Maximum number of occurrences of the event is 10
- Event is assumed to be conducted weekly only (Adhering to CS2040 Timetables)

Format: `schedule Recur/[event_type]/[event_name] -n number_of_times_to_repeat`

Examples:

* `schedule Recur/Tutorial/weeklyWrapUp -n 3`
* `schedule Recur/Lab/weeklyVisuAlgoQuiz -n 8`

### Edit an event: `editEvent event`

Edits an event current in the TA’s schedule.

- Name need not be unique
- Cannot be clashes in the time with any other events
- Only .pdf valid files can be added
- FULL file path to the pdf file MUST be given
- index starts from 1
- Consultation does not allow attachments as mentioned in add consultation section

Format: `editEvent [index] [EVENT_TYPE]/[NEW_EVENT_NAME] -date [NEW_DATE] -file [NEW_VALID_PDF_FILE_PATH]`

Examples:

* `editEvent 1 Tutorial/BellmanFord -date 10/10/2023 10:00 -file /Users/JohnDoe/Desktop/CS2040/BellmanFord.pdf`
* `editEvent 2 Lab/VisuAlgo`
* `editEvent 1 Consultation/ConsultWithEmily -date 10/10/2023 16:00`

### Delete events: `delete events`

Deletes valid indexed events from TA's schedule.

- Index starts from 1
- Valid index must be provided
- If range is provided, it is inclusive
- If range is provided, all values in the inclusive range must be valid
- If range is provided, start index cannot be longer greater end index

Format: `delete [EVENT_TYPE]/index`

Examples:

* `delete Tutorial/1`
* `delete Lab/1-5`

### Open file in events: `Open File`

Opens a file in either Tutorial or Lab if the TA has added one.

- Index starts from 1
- Valid index for event must be provided
- Consultation events have no attachments
- Only .pdf files will be parsed and opened

Format: `openFile [EVENT_TYPE]/index`

Examples:

* `openFile Tutorial/1`
* `openFile Lab/5`

--------------------------------------------------------------------
*Student-Event CRUD Features*

### Adding a student: `add student`

Adds a student to the student list.

- Duplicate nus email address is not allowed since each student has a unique nus email
- Duplicate telegram handle or phone number is not allowed since each student has a
  unique telegram handle or unique phone number
- Duplicate names are allowed

Format: `add student n/NAME [telegram/PHONE_NUMBER or TELEGRAM_HANDLE] [e/NUS_EMAIL] [score/SCORE]`

```
Fields with brackets [] are optional. If they are empty, corresponding fields in the student's records will also be empty
```

Examples:

* `add student n/Dijkstra`
* `add student n/Bellman telegram/97482842 e/e1234567@u.nus.edu score/100`

### Deleting a student: `delete student`

Removes a student from the student list.

- Index starts from 1
- Valid index must be provided

Format: `rm INDEX`

Examples:

* `rm 1`

### Add student to event: `addStudent to Event`

Add a student to an event.

- The index input refers to the index of the student in the student list
- The index input is 1-based.
- Valid index must be provided.
- The specified event must exist.
- Event type input must be valid

Format: `addStudent [INDEX] [EVENT_TYPE}/[EVENT_NAME]`

Examples:

* `addStudent 1 Tutorial/tut1`
* `addStudent 4 Lab/mock_lab_session`

### Delete student from event: `deleteStudent from Event`

Deletes a student from an event.

- The index input refers to the index of the student in the specified event's student list
- The index input is 1-based.
- Valid index must be provided.
- The specified event must exist.
- Event type input must be valid

Format: `deleteStudent [INDEX] [EVENT_TYPE}/[EVENT_NAME]`

Examples:

* `deleteStudent 1 Tutorial/tut1`
* `deleteStudent 4 Lab/mock_lab_session`

--------------------------------------------------------------------
*Note Features*

### Add note for event: `Add Note`

Add notes for events that are both normal and recurring, or for students in the classes this TA is in charge of. Mainly serves to help TAs take down notes and todos from meeting and student queries from classes.

Note that there can be an unlimited amount of notes to be created for each event or student.

Each addition increases node index by one.

Format: `add-note -content hello world -type tutorial -name 2`

Examples:

* `add-note -content rmb to bring along apple pencil\n -type Tutorial -name 2`
* `add-note -content grade student labs timely\n -type Lab -name 2`
* `add-note -content solve this student's query via email\n -type Recur -name 2`


### Delete note for event: `delete note`

Delete notes for events that are both normal and recurring, or for students in the classes this TA is in charge of.

Format: `delete note [type] [name or index] [note-index]`

Examples:

* `rm-note -type Tutorial -name 2 -index 3`
* `rm-note -type Lab -name 2 -index 1`
* `rm-note -type Recur -name 2 -index 0`


### Edit note for event: `edit note`

Update notes with the new note for events that are both normal and recurring, or for students in the classes this TA is in charge of.

Note that when a particular note index does not exist, it does nothing.

Format:



* `edit-note [type] [name or index] [note-index] [newcontent]`

Examples:


* `edit-note -content rmb to bring along apple pencil\n -type Tutorial -name 2 -index 3`
* `edit-note -content grade student labs timely\n -type Lab -name 2 -index 1`
* `edit-note -content solve this student's query via email\n -type Recur -name 2 -index 0`

--------------------------------------------------------------------
*Help Features*

### Help for events: `help`

Returns a list of instructions on what the event encompasses and also what parameters and input format is required to successfully create said event.

This should help new TAs understand the syntax better and also reduces the need to memorise the syntax or refer to any external documentations.

Format: `help [type]`

Examples:
* `help`
* `help lab`
* `help tutorial`
* `help consultation`

--------------------------------------------------------------------
*Sort / Filter features*

### Sort students in recurring events: `Sort Students`

Sorts the students in recurring events available in the order specified by the TA. The sorting method can either be alphabetical, participation level or urgency level. With each sorting method, the TA can also choose top down or bottom up order as well. If the TA wishes to sort all students under his supervision (regardless of type), he can specify type to be “all”.

The sorted list should be a secondary list and does not replace the existing, non-sorted one. Additional features to replace the existing one may be added in the future if deemed useful.

Format: `sort-student [group] [metric] [sorting order]`
For the [group], it can be only lab, tutorial, consultation, or all.
For the [metric], it can be only name, address, email, performance or remark.

Examples:

* `sort-student lab name reverse`
* `sort-student tutorial performance nonreverse`
* `sort-student consultation email nonreverse`
* `sort-student all remark nonreverse`


### Filter students in recurring events: `Filter Students`

Filters the students in recurring events depending on the metric specified by the TA. The metric can either be by a cut-off participation level (out of 100%) or minimum urgency level (out of 100). If the TA wishes to filter all students under his supervision (regardless of type), he can specify type to be “all”.

The filtered list should be a secondary list and does not replace the existing, non-sorted one. Additional features to replace the existing one may be added in the future if deemed useful.

Format: `filter [type] [metric] [threshold]`
For the [group], it can be only lab, tutorial, consultation, or all.
For the [metric], it can be only performance or urgency.
The [threshold] value must be an integer between 0 to 100 (inclusive).

Examples:

* `filter lab performance 60 `
* `filter tutorial performance 50`
* `filter consultation urgency 20`
* `filter all urgency 90`

--------------------------------------------------------------------
*Exit Application*
### Exit from application: `Exit application`

Exits from TrAcker

Format: `:wq`

Examples:

* `:wq`
--------------------------------------------------------------------------------------------------------------------

## Command summary

<table>
   <tr>
      <td>Action
      </td>
      <td>Format
      </td>
      <td>Examples
      </td>
   </tr>
   <tr>
      <td><strong>Add Tutorial</strong>
      </td>
      <td><code>touch Tutorial/[name] -date [dd/MM/yyyy HH:mm] -file [FULL_FILE_PATH_TO_PDF]</code>
      </td>
      <td>
         <ul>
            <li><code>touch Tutorial/makeUpTutorial</code>
            <li><code>touch Tutorial/examReview -date 01/01/2023 16:00</code>
            <li><code>touch Tutorial/examReview -date 01/01/2023 16:00 -file /Users/JohnDoe/Desktop/Introduction.pdf</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Lab</strong>
      </td>
      <td><code>vim Lab/[name] -date [dd/MM/yyyy HH:mm] -file [FULL_FILE_PATH_TO_PDF]</code>
      </td>
      <td>
         <ul>
            <li><code>vim Lab/pancakeSort</code>
            <li><code>vim Lab/KosarajuAlgorithm -date 01/01/2023 16:00</code>
            <li><code>vim Lab/StronglyConnected -date 01/01/2023 16:00 -file /Users/JohnDoe/Desktop/StronglyConnectedComponents.pdf</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Consultation</strong>
      </td>
      <td><code>mkdir Consultation/[name] -date [dd/MM/yyyy HH:mm]</code>
      </td>
      <td>
         <ul>
            <li><code>mkdir Consultation/reviewConnectedComponents</code>
            <li><code>mkdir Consultation/reviewDijsktra -date 01/01/2023 16:00</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Recurring Event</strong>
      </td>
      <td><code>schedule Recur/[event_type]/[event_name] -n number_of_times_to_repeat</code>
      </td>
      <td>
         <ul>
            <li><code>schedule Recur/Tutorial/weeklyWrapUp -n 3</code>
            <li><code>schedule Recur/Lab/weeklyVisuAlgoQuiz -n 8</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Edit event</strong>
      </td>
      <td><code>editEvent [index] [EVENT_TYPE]/[NEW_EVENT_NAME] -date [NEW_DATE] -file [NEW_VALID_PDF_FILE_PATH]</code>
      </td>
      <td>
         <ul>
            <li><code>editEvent 2 Lab/VisuAlgo</code>
            <li><code>editEvent 1 Consultation/ConsultWithEmily -date 10/10/2023 16:00</code>
            <li><code>editEvent 1 Tutorial/BellmanFord -date 10/10/2023 10:00 -file /Users/JohnDoe/Desktop/CS2040/BellmanFord.pdf</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Delete Events</strong>
      </td>
      <td><code>delete [EVENT_TYPE]/index</code>
      </td>
      <td>
         <ul>
            <li><code>delete Tutorial/1</code>
            <li><code>delete Lab/1-5</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Open File in Events</strong>
      </td>
      <td><code>openFile [EVENT_TYPE]/index</code>
      </td>
      <td>
         <ul>
            <li><code>openFile Tutorial/1</code>
            <li><code>openFile Lab/5</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Student</strong>
      </td>
      <td>
         <code>add student n/NAME telegram/PHONE_NUMBER or TELEGRAM_HANDLE] [e/NUS_EMAIL] [score/SCORE]</code>
         </li>
      </td>
      <td>
         <ul>
            <li><code>add student n/Dijkstra</code>
            <li><code>add student n/Bellman telegram/97482842 e/e1234567@u.nus.edu score/100</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Remove Student</strong>
      </td>
      <td>
         <code>rm INDEX</code>
         </li>
      </td>
      <td>
         <ul>
            <li><code>rm 1</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Student To Event</strong>
      </td>
      <td>
         <code>addStudent [INDEX] [EVENT_TYPE}/[EVENT_NAME]</code>
         </li>
      </td>
      <td>
         <ul>
            <li><code>addStudent 1 Tutorial/tut1</code>
            <li><code>addStudent 4 Lab/mock_lab_session</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Delete Student From Event</strong>
      </td>
      <td><code>deleteStudent [INDEX] [EVENT_TYPE}/[EVENT_NAME]</code>
      </td>
      <td>
         <ul>
            <li><code>deleteStudent 1 Tutorial/tut1</code>
            <li><code>deleteStudent 4 Lab/mock_lab_session</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add Note To Event</strong>
      </td>
      <td><code>addNote note -content hello world -type tutorial -name 2</code>
      </td>
      <td>
         <ul>
            <li><code>addNote -content rmb to bring along apple pencil\n -type Tutorial -name 2</code>
            <li><code>addNote -content grade student labs timely\n -type Lab -name 2</code></li>
            <li><code>addNote -content solve this student's query via email\n -type Recur -name 2</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Delete Note From Event</strong>
      </td>
      <td><code>deleteNote [type] [name or index] [note-index]</code>
      </td>
      <td>
         <ul>
            <li><code>deleteNote -type Tutorial -name 2 -index 3</code>
            <li><code>deleteNote -type Lab -name 2 -index 1</code></li>
            <li><code>deleteNote -type Recur -name 2 -index 0</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Edit Note In Event</strong>
      </td>
      <td><code>editNote [type] [name or index] [note-index] [newcontent]</code>
      </td>
      <td>
         <ul>
            <li><code>editNote -content rmb to bring along apple pencil\n -type Tutorial -name 2 -index 3</code>
            <li><code>editNote -content grade student labs timely\n -type Lab -name 2 -index 1</code></li>
            <li><code>editNote -content solve this student's query via email\n -type Recur -name 2 -index 0</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Help</strong>
      </td>
      <td><code>help [type]</code>
      </td>
      <td>
         <ul>
            <li><code>help</code>
            <li><code>help tutorial</code>
            <li><code>help lab</code>
            <li><code>help consultation</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Sort Students</strong>
      </td>
      <td><code>sort-student [group] [metric] [sorting order]</code>
      </td>
      <td>
         <ul>
            <li><code>sort-student lab name reverse</code>
            <li><code>sort-student tutorial performance nonreverse</code>
            <li><code>sort-student consultation email nonreverse</code>
            <li><code>sort-student all remark nonreverse</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Filter Students</strong>
      </td>
      <td><code>filter [type] [metric] [threshold]</code>
      </td>
      <td>
         <ul>
            <li><code>filter lab performance 60</code>
            <li><code>filter tutorial performance 50</code>
            <li><code>filter consultation urgency 20</code>
            <li><code>filter all urgency 90</code>
            </li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Exit application</strong>
      </td>
      <td><code>:wq</code>
      </td>
      <td>
         <ul>
            <li><code>:wq</code>
            </li>
         </ul>
      </td>
   </tr>
</table>

-----------------------------------------------------------------
Old UG commands (to remove)
-----------------------------------------------------------------

Sort student: `sort student`

Sort students by attributes such as their attendance rate for labs or tutorials (later by their exam grades)

Format: `sort student [type] [sorting order]`

This method is designed for ease of grading students based on their attendance to tutorials and labs.

Examples:
* `sort students labs reverse`
* `sort students tutorials nonreverse`

### Sort students in recurring events: `sort-student`

Sorts the students in recurring events available in the order specified by the TA. The sorting method can either be alphabetical, participation level or urgency level. With each sorting method, the TA can also choose top down or bottom up order as well. If the TA wishes to sort all students under his supervision (regardless of type), he can specify type to be “all”.

The sorted list should be a secondary list and does not replace the existing, non-sorted one. Additional features to replace the existing one may be added in the future if deemed useful.

Format: `sort-student [group] [metric] [sorting order]`
For the [group], it can be only lab, tutorial, consultation, or all.
For the [metric], it can be only name, address, email, performance or remark.

Examples:

* `sort-student lab name reverse`
* `sort-student tutorial performance nonreverse`
* `sort-student consultation email nonreverse`
* `sort-student all remark nonreverse`


### Filter students in recurring events: `filter`

Filters the students in recurring events depending on the metric specified by the TA. The metric can either be by a cut-off participation level (out of 100%) or minimum urgency level (out of 100). If the TA wishes to filter all students under his supervision (regardless of type), he can specify type to be “all”.

The filtered list should be a secondary list and does not replace the existing, non-sorted one. Additional features to replace the existing one may be added in the future if deemed useful.

Format: `filter [type] [metric] [threshold]`
For the [group], it can be only lab, tutorial, consultation, or all.
For the [metric], it can be only performance or urgency.
The [threshold] value must be an integer between 0 to 100 (inclusive).

Examples:

* `filter lab performance 60 `
* `filter tutorial performance 50`
* `filter consultation urgency 20`
* `filter all urgency 90`


### Alert students in recurring events: `alert student`

Provides a list of students who are suddenly performing poorly based on a certain metric, such as a sudden drop in participation level or a sudden increase in urgency level.

The filtered list should be a secondary list and does not replace the existing, non-sorted one. As of now, this can be subjective and we will increase ways to customise this so that the TA can specify what he defines as a “sudden change”.

Format: `alert student [type] [metric] `

Examples:

* `alert student lab urgency `
* `alert student tutorial participation`
* `alert student consultation urgency`
* `alert student all participation`


```
nr stands for non-recurring
```