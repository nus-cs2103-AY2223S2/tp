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

### Exit from application: `Exit application`

Exits from TrAcker

Format: `:wq`

Examples:

* `:wq`

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

### Add student to event: `addStudent`

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

### Delete student from event: `deleteStudent`

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

### Add note for event: `add-note`

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


Find specific non-recurring event: `find event`

Find a specific task on a particular date and (optional) time

Format: `/ event [date] [startTime] [endTime]`

Examples:



Navigate to students in recurring event: `show event students`

Show specific task’s students based on index from list of **all **non-recurring tasks or based on name.

Once entered, use the students command features to add, delete, edit students.

Format:

* `:cd nr [name] or [index] students`

Examples:

* `:cd nr 1 students`
* `:cd nr consultation students`



Navigate to students in recurring event: `show recur students`

Show specific recurring task students based on index from list of **all **recurring tasks or based on name.

Once entered, use the students command features to add, delete, edit students.

Format:

* `:cd [name] or [index] students`

Examples:

* `:cd 1 students`
* `:cd weeklyTutorials students`


### Sort recurring events: `sort recur`

Sorts the recurring events available in the order specified by the TA. The sorting method can either be alphabetical, date or duration. With each sorting method, the TA can also choose top down or bottom up order as well.

The sorted list should be a secondary list and does not replace the existing, non-sorted one. Additional features to replace the existing one may be added in the future if deemed useful.

Format: `sort [type] [sorting method] [sorting order]`

Examples:

* `sort lab alphabetical reverse`
* `sort tutorial duration nonreverse`
* `sort consultation date nonreverse`


### Replace existing list with sorted list: `replace sort recur`

Similar to the sorting command, except with a "replace" keyword in front. This function overwrites the recurring event list with a sorted one with the sorting parameters specified.

There should be a prompt to ask if the TA wants to proceed with overwriting as the process is irreversible.

Format: `replace sort [type] [sorting method] [sorting order]`

Examples:



* `replace sort lab alphabetical reverse`
* `replace sort tutorial duration nonreverse`
* `replace sort consultation date nonreverse`



### Help tutorial for recurring events: `help recur`

Returns a list of instructions on what the event encompasses and also what parameters and input format is required to successfully create said event.

This should help new TAs understand the syntax better and also reduces the need to memorise the syntax or refer to any external documentations.

Format: `help [type]`

Examples:



* `help lab`
* `help tutorial`
* `help consultation`


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


### Adding a student: `add student`

Adds a student to the student list.

Format: `add student n/NAME [p/PHONE_NUMBER] [e/EMAIL] [t/TUTORIAL_GROUP] [l/LAB_GROUP]`


```
Fields with brackets [] are optional. If they are empty, corresponding fields in the student's records will also be empty
```


Examples:



* `add student n/Dijkstra`
* `add student n/Tony Hoare p/97482842 t/T03 l/B09`


### List all students: `list student`

Lists all students of the TA user. Can be filtered according to events.

Format: `ls student [task]`

Examples:



* `ls student`
* `ls student Tutorials`
* `ls student Labs`

Deleting student: `delete student`

Delete students based on index from list of students corresponding to an event

Format:



1. `:cd EVENT students`
2. `:delete student INDEX`

Examples:



* `example 1`
    * `:cd weeklyTutorials students`
    * `:delete student 5`
* `example 2`
    * `:cd weeklyLabs students`
    * `:delete student 1`

Edit student: `edit student`

Edit student details from list of all students

Format: `edit INDEX n/NAME [p/PHONE_NUMBER] [e/EMAIL] [t/TUTORIAL_GROUP] [l/LAB_GROUP]`

Edits the student at the specified INDEX within the student list.

Existing values will be updated to the input values.

Examples:



* `edit 1 n/Dijkstra`
* `edit 2 n/Tony Hoare p/97482842 t/T03 l/B09`



* `/ event 2023-04-01`
* `/ event 2023-03-12 8:00 10:00`

Deleting non-recurring event: `delete event`

Delete tasks based on index from list of **all **non-recurring tasks

Format: `:delete event [start],[end]d`

Examples:



* `:delete event 5d`
* `:delete event 5,10d`
* `:delete event %d`
* `:delete event .,10d`
* `:delete event 1d,$`

Edit non-recurring event: `edit event`

Edit tasks based on index from list of **all **non-recurring tasks or unique name

Format:



* `:/%s/nr/[name] or [index]/[new task details]/g`
* `:/%s/nr/[name] or [index]/[new task details]/gc`


```
nr stands for non-recurring
```


Examples:



* `:/%s/nr/make-up-Tutorial/Extra-tutorial Tutorials 2023-04-14 14:00 1/g`
* `:/%s/nr/1/Consultation Consultations 2023-04-14 16:00 1/g`


```
nr stands for non-recurring
```
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
   <tr>
      <td><strong>Add Student</strong>
      </td>
      <td>
         <ol>
            <li><code>add student n/NAME [telegram/PHONE_NUMBER or TELEGRAM_HANDLE] [e/NUS_EMAIL] [score/SCORE]</code>
            </li>
         </ol>
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
         <ul>
            <li><code>rm INDEX</code>
            </li>
         </ul>
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
         <ul>
            <li><code>addStudent [INDEX] [EVENT_TYPE}/[EVENT_NAME]</code>
            </li>
         </ul>
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
      <td><strong>Find recur</strong>
      </td>
      <td><code>/ recur [timeframe] [startTime] [endTime]</code>
      </td>
      <td>
         <ul>
            <li><code>/ recur Wednesday</code>
            <li><code>/ recur Wednesday 8:00 10:00</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Find non-recurring event</strong>
      </td>
      <td><code>/ event [date] [startTime] [endTime]</code>
      </td>
      <td>
         <ul>
            <li><code>/ event 2023-04-01</code>
            <li><code>/ event 2023-03-12 8:00 10:00</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Show recur</strong>
      </td>
      <td>
         <ul>
            <li><code>:cd [name] or [index] calendar</code>
            <li><code>:cd [name] or [index] students</code></li>
         </ul>
      </td>
      <td>
         <ul>
            <li><code>:cd 1</code>
            <li><code>:cd weeklyTutorials</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Show non-recurring event</strong>
      </td>
      <td>
         <ul>
            <li><code>cd nr [name] or [index] calendar</code></li>
         </ul>
      </td>
      <td>
         <ul>
            <li><code>:cd nr 1</code>
            <li><code>:cd nr Make-up-Tutorial</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Sort student </strong>
      </td>
      <td><code>sort student [type] [sorting order]</code>
      </td>
      <td>
         <ul>
            <li><code>sort students labs reverse</code>
            <li><code>sort students tutorials nonreverse</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Move recur</strong>
      </td>
      <td><code>:mv [name] or [index]</code>
      </td>
      <td>
         <ul>
            <li><code>:mv 1</code>
            <li><code>:mv weeklyTutorials</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Stop recur</strong>
      </td>
      <td><code>stop [name] or [index]  </code>
      </td>
      <td>
         <ul>
            <li><code>:stop 1</code>
            <li><code>:stop weeklyTutorials</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Sort recur</strong>
      </td>
      <td><code>sort [type] [sorting method]</code>
      </td>
      <td>
         <ul>
            <li><code>sort labs alphabetical reverse</code>
            <li><code>sort consultation date nonreverse</code>
            <li><code>sort tutorial duration nonreverse</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Replace sort recur</strong>
      </td>
      <td><code>replace sort [type] [sorting method]</code>
      </td>
      <td>
         <ul>
            <li><code>replace sort labs alphabetical reverse</code>
            <li><code>replace sort consultation date nonreverse</code>
            <li><code>replace sort tutorial duration nonreverse</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Help recur</strong>
      </td>
      <td><code>help [type]</code>
      </td>
      <td>
         <ul>
            <li><code>help labs</code>
            <li><code>help tutorial</code>
            <li><code>help consultation</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Sort student</strong>
      </td>
      <td><code>sort student [type] [sorting method] [sorting order]</code>
      </td>
      <td>
         <ul>
            <li><code>sort student lab alphabetical reverse</code>
            <li><code>sort student tutorial participation nonreverse</code>
            <li><code>sort student consultation urgency nonreverse</code>
            <li><code>sort student all urgency nonreverse</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Filter student</strong>
      </td>
      <td><code>filter student [type] [metric] [threshold]</code>
      </td>
      <td>
         <ul>
            <li><code>filter student lab urgency 60 </code>
            <li><code>filter student tutorial participation 50</code>
            <li><code>filter student consultation urgency 20</code>
            <li><code>filter student all participation 90</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Alert student</strong>
      </td>
      <td><code>alert student [type] [metric]</code>
      </td>
      <td>
         <ul>
            <li><code>alert student lab urgency </code>
            <li><code>alert student tutorial participation</code>
            <li><code>alert student consultation urgency</code>
            <li><code>alert student all participation</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Add note</strong>
      </td>
      <td><code>touch note [type] [name or index] [content]</code>
      </td>
      <td>
         <ul>
            <li><code>touch note recur weeklyTutorials "rmb to bring along apple pencil\n"</code>
            <li><code>touch note recur 2 "grade student labs timely\n"</code>
            <li><code>touch note student Eldon "solve this student's query via email\n"</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Edit note</strong>
      </td>
      <td><code>edit note [type] [name or index] [note-index] [newcontent]</code>
      </td>
      <td>
         <ul>
            <li><code>edit note recur weeklyTutorials 0 "no new tasks lol\n"</code>
            <li><code>edit note recur 2 0 "bring my lab cheat-sheet\n"</code>
            <li><code>edit note student Eldon 1 ""</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>Delete note</strong>
      </td>
      <td><code>delete note [type] [name or index] [note-index]</code>
      </td>
      <td>
         <ul>
            <li><code>delete note recur weeklyTutorials 3</code>
            <li><code>delete note recur 2 0</code>
            <li><code>delete note student Eldon 0</code></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><strong>List note</strong>
      </td>
      <td><code>ls note [type] [name or index]</code>
      </td>
      <td>
         <ul>
            <li><code>ls note recur weeklyTutorials</code></li>
         </ul>
      </td>
   </tr>
</table>



Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
