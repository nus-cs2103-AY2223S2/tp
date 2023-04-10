---
layout: page
title: User Guide
---
<img src="images/FitBookBanner.png" width="1200" />

<h2><div style="color:#f9963f">About FitBook</div></h2>

FitBook is a **desktop application for managing clients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FitBook can get your client management tasks done faster than traditional GUI apps.

This User Guide provides brief documentation on how you can install the application and describes how each feature should be used. Start by looking at the [quick start](#quick-start) guide to get you started.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick start

1. Before using FitBook, make sure to install **Java** `11` or later version in your Computer. This is required to ensure proper functionality of the application. Please install the correct Java version to use FitBook.
   * To check Java version, please follow the instructions [here](https://blog.hubspot.com/website/check-java-verison).
   * If your computer does not have java installed, or if the version is older than Java 11, you may refer to the guide [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).
2. **Download** the latest `fitbook.jar` from [here](https://github.com/AY2223S2-CS2103T-T15-2/tp/releases).
3. **Copy** the file to the folder you want to use as the _home folder_ for your FitBook. You may create a new folder under any directory.
4. Open a command terminal and navigate into the folder you put the jar file in using `cd`. Then, use the `java -jar fitbook.jar` command to run the application.<br>
   A GUI similar to the one shown below should appear in a few seconds. Note how the app contains some sample data.<br>
   * For example, if your jar file is stored in a folder 'FitBook' located in the desktop directory of your computer, the commands in the command terminal will be:
     1. `cd Desktop`
     2. `cd FitBook`
     3. `java -jar fitbook.jar`

   * If you are unsure how to open the command terminal, you may refer to the guide below.
     * For [Windows](https://www.businessinsider.com/guides/tech/how-to-open-command-prompt). For [Mac](https://support.apple.com/en-sg/guide/terminal/apd5265185d-f365-44cb-8b09-71a064a42125/mac#:~:text=Click%20the%20Launchpad%20icon%20in,%2C%20then%20double%2Dclick%20Terminal).

   ![Ui](images/Ui.png)


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `listClients` : Lists all Clients.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 w/50 g/M` : Adds a contact named `John Doe` to the FitBook.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `export` : Exports all Client's details in FitBook into a csv file.

   * `exportRoutine` : Exports all Routine details in FitBook into a csv file.

   * `addRoutine r/Cardio ex/3x5 1km Jog ex/3x10 Jumping Jacks` : Adds a new routine named `Cardio` with 2 exercises `3x5 1km Jog` and `3x10 Jumping Jacks`.

   * `exit` : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## User Interface Introduction

This section provides a brief overview of the graphical user interface (GUI) components that are present in FitBook. These components are designed to provide an easy-to-use interface that enables users to track and improve their clients' fitness goals.
![User Interface](images/interface.png)

### Menu Bar
The menu bar is typically located at the top of the user interface and is primarily used for navigation purposes. It provides users with access to various features and functions within the application or dashboard, allowing them to easily navigate between different tabs. The menu bar is commonly used in user guides to help users understand how to navigate and use the different features within the application.

#### FitBook
* 'Schedule' item allows you to switch to the Schedule panel.
* 'Exit' item directly exits out of the application.

#### Exercise
* 'Exercise' menu item allows you to switch to Exercise panel.

#### Summary
* 'Summary' menu item allows you to switch to Summary panel.

#### Help
* Provides a new tab outside FitBook with the link to the User Guide.

### Result Panel
Panels are _empty_ when the data for that specific panel is _empty_.

#### Schedule
* Left panel (`Schedule` tab) displays the list of clients' basic information.
* Right panel (`Schedule` tab) displays the list of appointments sorted chronologically. (Refresh the page by changing tabs or reopening FitBook to see the appointment get removed after it passes its deadline.)


  ![Schedule Panel](images/schedule.png)

#### Exercise
* Left panel (`Exercise` tab) displays the list of clients' basic information including weight, average calories, goal, any unique routines and exercises.
* Right panel (`Exercise` tab) displays the list of routines added.


  ![Exercise Panel](images/exercise.png)

#### Summary
* Left panel (`Summary` tab) displays the list of clients' basic information including name and scheduled appointments.
* Right panel (`Summary` tab) displays all information about the selected client. (Using the view command)


  ![Summary Panel](images/summary.png)

### Result Display
When you enter a command into the Command Box of the application, the system will provide a feedback message to you through the display. This feedback message is designed to inform you of the status of the command you have entered, such as whether it was executed successfully or encountered an error. The feedback message may also include additional information related to the command, such as the results of the command's execution or the specific error encountered.
### Command Box
The Command Box is where you can type in your commands. It is found below the Feedback Box. To execute the command, simply press the "Enter" button on your keyboard. This will send the command to the system for processing.

---

<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* These rules apply to all commands including for Clients and Routines.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `export`, `exportRoutine`, `listRoutines`, `clearRoutines` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Client list can be found in the Client panel. Routine list can be found in the Routine Panel of the `Routine` tab.
</div>

### Prefixes for Client Commands

| Prefix   | Compulsory field for adding client | Multiple Input  | Prefix Meaning                                   | Characters restrictions                                      |
|----------|------------------------------------|-----------------|--------------------------------------------------|--------------------------------------------------------------|
| `n/`     | Yes                                | Only Last Taken | Name of Client                                   | Alphabets, numbers and spaces                                |
| `p/`     | Yes                                | Only Last Taken | Phone number                                     | At least 3 digits                                            |
| `e/`     | Yes                                | Only Last Taken | Email                                            | local-part@domain                                            |
| `a/`     | Yes                                | Only Last Taken | Address                                          | Any characters except blank                                  |
| `w/`     | Yes                                | Only Last Taken | Weight                                           | Positive number from 0.1 to 999.9 with 0 or 1 decimal place  |
| `g/`     | Yes                                | Only Last Taken | Gender                                           | M or F (not case sensitive)                                  |
| `c/`     | No                                 | Only Last Taken | Recommended Calories                             | At least 4 digits long                                       |
| `gl/`    | No                                 | Only Last Taken | Goal for Client                                  | Any characters except blank                                  |
| `r/`     | No                                 | All Taken       | Routines for Client (Must be in Exercise Routine | Valid routine names in exercise routine portion              |
| `t/`     | No                                 | All Taken       | Tag                                              | Alphabets and numbers                                        |
| `app/`   | No                                 | All Taken       | Appointment                                      | dd-mm-yyyy HH:mm format that is after current date and time  |
| `d/`     | NA                                 | NA              | Date used for adding weight                      | dd-mm-yyyy HH:mm format that is before current date and time |


<div style="page-break-after: always;"></div>

### Prefixes for Routine Commands

| Prefix   | Compulsory field for adding routine | Multiple Input  | Prefix Meaning | Characters restrictions       |
|----------|-------------------------------------|-----------------|----------------|-------------------------------|
| `r/`     | Yes                                 | Only Last Taken | Routine Name   | Alphabets, numbers and spaces |
| `ex/`    | No                                  | All Taken       | Exercise Name  | Alphabets, numbers and spaces |
| `exno/`  | NA                                  | NA              | Exercise index | Numeric                       |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** For `Multiple Input Column`, `Only Last Taken` means that for example `add n/John Doe n/Richard Yang...` there are 2 name prefixes, the last name prefix which is `Richard Yang` will be stored as the name of the client to be added.
While `All Taken` means for example `add ... t/friends t/VIP ...` both tags will be added to the client.  </div>

---

<div style="page-break-after: always;"></div>

### Client Commands

#### Adding a client: `add`

{% include information.html content="

Adds a new client into FitBook's client list and storage.

" %}

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS w/WEIGHT g/GENDER
[cal/RECOMMENDED_CALORIES_INTAKE] [g/GOAL] [r/ROUTINE]…​ [app/APPOINTMENT_TIME]…​ [t/TAG]…​`

* A client can have any number of `tags`, `appointments` and `exercise routines` (including 0).
* A user cannot add an exercise routine that does not exist in the exercise routine list in the `Exercise` tab to a client.
* `APPOINTMENT` needs to be later than the current time, or it will get deleted.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** `Gender` should be either M or F (not case-sensitive). </div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:** `Routine` is case sensitive. </div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:** `Weight` is specified in kilograms (Kg). </div>


Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 w/50 g/M r/Cardio` (Cardio routine has to exist in exercise routine).
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/93125841 t/John's Friend w/55 g/F`
* `add n/Betsy Crowe app/12-12-2030 12:00 cal/2300 g/F e/betsycrowe@example.com a/Newgate Prison p/93125841 t/Friend`

<div style="page-break-after: always;"></div>

#### Editing a client : `edit`

{% include information.html content="

Edits the information of an existing client in the FitBook.

" %}


Format: `edit CLIENT_INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS w/WEIGHT g/GENDER
[cal/RECOMMENDED_CALORIES_INTAKE] [g/GOAL] [r/ROUTINE]…​ [app/APPOINTMENT_TIME]…​ [t/TAG]…​`

* Edits the client at the specified `CLIENT_INDEX`. The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing `tags`,`appointments` and `routines`, the existing `tags`,`appointments` and `routines` of the client will be removed i.e. adding of `tags`,`appointments` and `routines` are not cumulative.
* You can remove all the client’s tags by typing `t/` without specifying any tags after it. (same for `appointments` with `/app` prefix and `routines` with `/r`)
* `APPOINTMENT` needs to be later than the current time, or it will get deleted.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** `Gender` should be either M or F (not case-sensitive). </div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:** `Routine` is case sensitive. </div>

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.
*  `edit 3 w/23.0` Edits the weight of the 3rd client to `23.0`.
*  `edit 4 w/25.0 g/m` Edits the weight and gender of the 4th client to `25.0` and `m`.

<div style="page-break-after: always;"></div>

#### Locating clients by fields: `find`

{% include information.html content="

Finds clients in FitBook whose details contain any of the given keywords.

" %}

Format: `find n/NAME p/PHONE e/EMAIL a/ADDRESS w/WEIGHT g/GENDER cal/CALORIE t/TAG
app/APPOINTMENT gl/GOAL…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** The search is case-insensitive. e.g `hans` will match `Hans` </div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:** Details containing the keyword will also be matched, even if both do not match exactly. e.g. `Han` will match `Hans`, `John` will match `John Li` and `John Tan`, `19` will match `1900` and `0190` </div>

Available Prefixes:
* `n` filters by Name
* `p` filters by Phone
* `e` filers by Email
* `a` filters by Address
* `t` filters by Tag
* `w` filters by Weight
* `g` filters by Gender
* `gl` filters by Goal
* `cal` filters by Calorie
* `app` filters by Appointment

Examples:
* `find n/Alex` returns every client with 'Alex' in their name.
* `find p/91234567` returns every client with phone numbers that match or contains '91234567'.
* `find n/Alex p/91234567` returns every client with 'Alex' in their name OR with phone numbers that match or contain
  '91234567'

<div style="page-break-after: always;"></div>

#### Deleting a client : `delete`

{% include information.html content="

Deletes the specified client from the FitBook.

" %}

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647

Examples:
* `list` followed by `delete 2` deletes the 2nd client in the FitBook.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

#### Viewing a client summary: `view`

{% include information.html content="

Views the client's summary which contains additional information on top of the basic information, such as the `Exercise Routines` tagged to the client.

" %}

Format: `view INDEX`

* Views the summary of the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647

Examples:
* `view 1` views the summary of the first client in the FitBook.
* `find n/Alex` followed by `view 1` views the summary of the 1st client in the results of the `view` command.

<div style="page-break-after: always;"></div>

#### Listing all clients : `listClients`

{% include information.html content="

Displays a list of all clients in the FitBook under the Client panel.

" %}

Format: `listClients`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** To list all clients in the client panel after using find.</div>

* Initiate the command with `listClients`
* All clients in FitBook has been listed once again!

#### Clearing all entries : `clear`
{% include information.html content="

Clears all client entries from the FitBook.

" %}


Format: `clear`

#### Exporting client details : `export`

{% include information.html content="

Exports the client details locally into a csv file.

" %}


Format: `export`

* The exported client data includes: <br>
    * Name of Client
    * Phone number of Client
    * Email of Client
    * Address of Client
    * Weight of Client
    * Gender of Client
* Example generated CSV file when opened using Microsoft Excel:

  ![CSV Sample File](images/CSVSample.png)

<div style="page-break-after: always;"></div>

### Client's Weight Commands

#### Adding new weight to a client: `addWeight`

{% include information.html content="

Adds a new weight to the weight history of the client in the FitBook.

" %}


Format: `addWeight INDEX w/WEIGHT d/DATE`

* Adds weight to the weight history of the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647
* The new `DATE` must be specified in `dd-MM-yyyy HH:mm` format.
* `DATE` field must be a date that has passed.
* `WEIGHT` field is specified in kilograms (Kg).
* The weight of the client displayed in the client list is updated to the latest weight inputted by the user. Use `listClients` to refresh the client list and view the updated weight of the client.

Examples:
* `addWeight 1 w/70 d/10-03-2023 18:00` adds weight 70kg to the weight history of the first client in the FitBook and dates the weight at 10 March 2023, 6pm.
* `find n/Alex` followed by `addWeight 1 w/70 d/10-03-2023 18:00` adds weight 70kg, dated 10 March 2023, 6pm to the weight history of the 1st client in the results of the `find` command.

<div style="page-break-after: always;"></div>

#### Plotting weight history graph for a client: `graph`

{% include information.html content="

Plots the weight history line graph of the client in the FitBook.

" %}


Format: `graph INDEX`

* Plots the weight history of the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647
* The weight history graph will be shown only for weights dated within one month of the current date and time.

Examples:
* `graph 1` plots the weight history of the first client in the FitBook.
* `find n/Alex` followed by `graph 1` plots the weight history of the 1st client in the results of the `find` command.

Example:
![Graph](images/GraphUG.png)

---

<div style="page-break-after: always;"></div>

### Exercise Routine Commands

* All routines and exercises must be in alphanumeric characters and spaces between characters are allowed.

#### Adding a routine: `addRoutine`

{% include information.html content="

Adds a new routine to FitBook's exercise routine list and storage.

" %}


Format: `addRoutine r/ROUTINE_NAME [ex/EXERCISE]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** A client can have any number of `Exercise` (including 0). </div>

Examples:
* `addRoutine r/Cardio`
* `addRoutine r/HIIT ex/3x10sets of Jumping ropes`

Example: (Adding Routine without any exercises)
![AddRoutine](images/AddRoutineUG.png)

<div style="page-break-after: always;"></div>

#### Listing all Routines in FitBook : `listRoutines`

{% include information.html content="

List all Routines in FitBook under Exercise tab.
Displays a list of all exercise routines in the FitBook in the Exercise tab, under the Exercise panel.

" %}


Format: `listRoutines`

* Initiate the command with 'listRoutines'
* All Routines in FitBook has been listed once again!

#### Editing a routine : `editRoutine`

{% include information.html content="

Edits the information of an existing exercise routine in the FitBook.

" %}


Format: `editRoutine ROUTINE_INDEX r/ROUTINE_NAME` or `editRoutine ROUTINE_INDEX exno/EXERCISE_INDEX ex/EXERCISE`

* Edits the routine at the specified `ROUTINE_INDEX`. The index refers to the index number shown in the displayed exercise routine list.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647
* At least one of the two types should be used. Mixture of the two types is not allowed.
* This command only allows the editing existing Routine's name or existing Exercise's name.

Examples:
* `editRoutine 1 r/Cardio` edits the routine name of the 1st routine to be `Cardio` respectively.
* `editRoutine 2 exno/1 ex/3x10sets dumbbells` edits the exercise of the 2nd routine to be `3x10sets dumbbells`.
* `editRoutine 3 r/Cardio exno/1 ex/3x10 sets dumbbells` will throw an error as it attempts to change both the routine name and the exercises in the routine.

<div style="page-break-after: always;"></div>

#### Locating routines by name: `findRoutine`

{% include information.html content="

Finds routines in FitBook whose routine name contains any of the given keywords.

" %}


Format: `findRoutine KEYWORD`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** The search is case-insensitive. e.g `cardio` will match `Cardio`. </div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:** The order of the keywords does not matter. e.g. `Fun swimming` will match `Swimming fun`. </div>

Examples:
* `findRoutine OPM`

Example:
![FindRoutine](images/FindRoutineUG.png)

<div style="page-break-after: always;"></div>

#### Deleting a routine : `deleteRoutine`

{% include information.html content="

Deletes the specified routine from the FitBook.

" %}


Format: `deleteRoutine ROUTINE_INDEX`

* Deletes the routine at the specified `ROUTINE_INDEX`.
* The index refers to the index number shown in the displayed exercise routine list in the `Exercise panel`.
* The index **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647

Examples:
* `listRoutines` followed by `deleteRoutine 2` deletes the 2nd Routine in FitBook.
* `findRoutine Cardio` followed by `deleteRoutine 1` deletes the 1st Routine in the results of the `findRoutine` command.

#### Adding an exercise : `addExercise`

{% include information.html content="

Adds an exercise to an existing routine in FitBook.

" %}


Format: `addExercise ROUTINE_INDEX ex/EXERCISE_NAME`

* Adds an exercise `EXERCISE_NAME` to the routine in FitBook at the specified `ROUTINE_INDEX`.
* The `ROUTINE_INDEX` refers to the index number shown in the displayed exercise routine list in the exercise panel.
* The `ROUTINE_INDEX` **must be a positive integer** 1, 2, 3, …​
* The index must not contain signs +1, +2, +3, …​
* The index must not be larger than `Integer.MAX_VALUE` i.e. 2147483647

Examples:
* `listRoutines` followed by `addExercise 1 ex/push ups` adds the exercise `push ups` to the first routine in the exercise routine list in FitBook.

<div style="page-break-after: always;"></div>

#### Deleting an exercise : `deleteExercise`

{% include information.html content="

Deletes the specified exercise from the specified routine in FitBook.

" %}


Format: `deleteExercise ROUTINE_INDEX EXERCISE_INDEX`

* Deletes the exercise specified by the `EXERCISE_INDEX` of the routine in FitBook specified by `ROUTINE_INDEX`.
* The `ROUTINE_INDEX` refers to the index number shown in the displayed exercise routine list in the exercise panel.
* The `EXERCISE_INDEX` refers to the index number shown in the displayed exercise routine list in the exercise panel.
* Both `ROUTINE_INDEX` and `EXERCISE_INDEX` **must be a positive integer and a valid integer according to the list displayed** 1, 2, 3, …​
* Both `ROUTINE_INDEX` and `EXERCISE_INDEX` must not contain signs +1, +2, +3, …​
* Both `ROUTINE_INDEX` and `EXERCISE_INDEX` must not be larger than `Integer.MAX_VALUE` i.e. 2147483647

Examples:
* `listRoutines` followed by `deleteExercise 1 2` deletes the exercise specified at index `2` from the Routine specified at index `1` in the Routine list in FitBook.
* `findRoutine Cardio` followed `deleteExercise 1 1` deletes the exercise specified at index `1` from the Routine specified at index `1` in the results of the `findRoutineCardio` command.

#### Clearing all routine entries : `clearRoutines`

{% include information.html content="

Clears all routine entries in FitBook under the `Exercise` Tab.

" %}


Format: `clearRoutines`

<div style="page-break-after: always;"></div>

#### Exporting routine details : `exportRoutines`

{% include information.html content="

Exports the routine details locally into a csv file.

" %}


Format: `exportRoutines`

* The exported client data includes: <br>
    * Name of routine
    * Exercises in the routine

* Example generated CSV file when opened using Microsoft Excel:

  ![CSV Sample File](images/CSVRoutineSample.png)

---

<div style="page-break-after: always;"></div>

### FitBook Miscellaneous Commands

#### Viewing help : `help`

{% include information.html content="

Shows a message explaining how to access the help page.

" %}

Format: `help`

#### Exiting the program : `exit`

{% include information.html content="

Exits the program.

" %}

Format: `exit`

---

### FitBook Storage Usage

#### Saving the data

FitBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

FitBook data are saved as a JSON file `[JAR file location]/data/fitbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FitBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FitBook home folder.

**Q**: Must I exit the FitBook app through the `exit` command?<br>
**A**: There is no need to exit the FitBook app through the `exit` command as everything is saved after each action changes to the list.

**Q**: Why can I not tag the exercise routine to the client?<br>
**A**: The exercise routine is probably not inside the list of routines or the routine name is wrong. The routine name is case-sensitive.

**Q**: When I try to edit my weight a few times, and it updates in the client panel, but the data does not show up on the graph?<br>
**A**: To view historical weight data on the line graph, more values of weight must be added using addWeight command. edit command only changes the latest weight of the client.

**Q**: Why is my FitBook empty even though I have data saved in the storage?<br>
**A**: The format of your json file storage is invalid. Hence, it will create an empty storage system for the FitBook as a measure.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

| Action                                  | Format, Examples                                                                                                                                                                                                                                                                        |
|-----------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                                 | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS w/WEIGHT g/GENDER [cal/RECOMMENDED_CALORIES_INTAKE] [g/GOAL] [r/ROUTINE_NAME]…​ [app/APPOINTMENT_TIME]…​ [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague w/55 g/M` |
| **Clear all client entries in FitBook** | `clear`                                                                                                                                                                                                                                                                                 |
| **Delete**                              | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                     |
| **Edit**                                | `edit CLIENT_INDEX n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS w/WEIGHT g/GENDER [cal/RECOMMENDED_CALORIES_INTAKE] [gl/GOAL] [r/ROUTINE_NAME]…​ [app/APPOINTMENT_TIME]…​ [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                    |
| **Find**                                | `find n/NAME p/PHONE e/EMAIL a/ADDRESS w/WEIGHT g/GENDER cal/CALORIE t/TAG app/APPOINTMENT gl/GOAL`<br> e.g., `find n/James Jake e/jamesjake@example.com`                                                                                                                               |
| **List**                                | `listClients`                                                                                                                                                                                                                                                                           |
| **Help**                                | `help`                                                                                                                                                                                                                                                                                  |
| **Export Client Details**               | `export`                                                                                                                                                                                                                                                                                |
| **Add Exercise**                        | `addExercise ROUTINE INDEX ex/EXERCISE` <br> e.g, `addExercise 1 ex/4 x 3 sprints`                                                                                                                                                                                                      |
| **Add Routine**                         | `addRoutine r/ROUTINE_NAME [ex/EXERCISE]…​` <br> e/g, `addRoutine r/Cardio ex/3x5 1km Jog ex/3x10 Jumping Jacks `                                                                                                                                                                       |
| **Edit Routine**(RoutineName)           | `editRoutine ROUTINE_INDEX r/ROUTINE_NAME` <br> e/g, `editRoutine 1 r/Cardio`                                                                                                                                                                                                           |
| **Edit Routine**(Exercise)              | `editRoutine INDEX exno/EXERCISE_INDEX ex/EXERCISE` <br> e/g, `editRoutine 1 exno/1 ex/3x10sets of Dumbbell curls`                                                                                                                                                                      |
| **Delete Exercise**                     | `deleteExercise ROUTINE INDEX EXERCISE INDEX` <br> e.g, `deleteExercise 1 2`                                                                                                                                                                                                            |
| **Delete Routine**                      | `deleteRoutine ROUTINE INDEX ` <br> e.g, `deleteRoutine 1`                                                                                                                                                                                                                              |
| **List Routine**                        | `listRoutines`                                                                                                                                                                                                                                                                          |
| **Clear Routine**                       | `clearRoutines`                                                                                                                                                                                                                                                                         |
| **Find Routine**                        | `findRoutine ROUTINE NAME` <br> e.g, `findRoutine Cardio`                                                                                                                                                                                                                               |
| **Export Routine Details**              | `exportRoutine`                                                                                                                                                                                                                                                                         |
| **Add weight**                          | `addWeight INDEX w/WEIGHT d/DATE`<br> e.g., `addWeight 1 w/70 d/10-10-2023 18:00`                                                                                                                                                                                                       |
| **Graph**                               | `graph INDEX`<br> e.g., `graph 1`                                                                                                                                                                                                                                                       |
| **View**                                | `view INDEX`<br> e.g., `view 1`                                                                                                                                                                                                                                                         |

