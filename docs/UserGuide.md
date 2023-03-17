---
layout: page
title: User Guide
---

eduMate is a **desktop app designed for NUS students to manage their academic and social lives**. It is optimized for use through a Command Line Interface (CLI) while still providing the benefits of a Graphical User Interface (GUI). If you type quickly, eduMate can complete contact and module management tasks faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `eduMate.jar` from [here](https://github.com/AY2223S2-CS2103T-W14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eduMate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   <br>
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `user` : View personal profile.

   * `add` : Adds a contact.

   * `tag` : Tag a module to a contact.

   * `find` : Find contacts by keywords.

   * `sort` : Sorts contact.

   * `view` : View contacts profile on profile window.

   <br>
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Viewing user profile: `user`

Allows users to view personal profile.
For users using GUI, the user profile will be in the top right corner of the app.
Format (for CLI only): `user`
Example of usage: `user`

Expected outcome for CLI:
```agsl
Username: John Doe
Email: E1234567@u.nus.edu
Modules: CS2103T | CS2101 | MA2104 | MA3252 | CFG1002
Phone Number: 12345678
```


Expected outcome for GUI:
![result for 'user' GUI](images/userResultGui.png)

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Adding a contact to list : `add`

Allow users to add a contact to the address book.
Format: `add n/NAME m/mod1 mod2 mod3`
Example of usage: `add n/Penny Lane m/DTK1234 HSH1000 HSS1000 MA1100 GEA1000`
Context:
There are 3 friends with the following information:
```
Name: Ben Tan
3 Common Modules: CS2103T | CS2101 | MA3252
Name: Jane Lane
2 Common Modules: CS2103T | CS2101
Name: John Street
1 Common Module: MA3252
```

Expected outcome for CLI:
```
Name: John Doe (User)
Modules reading: CS2103T | CS2101 | MA2104 | MA3252 | CFG1002
Name: Ben Tan
Modules reading: CS2103T | CS2101 | MA3252 | CS4230 | CS2105
Name: Jane Lane
Modules reading: CS2103T | CS2101, CM1102 | CS2102 | CS2108
Name: John Street
Modules reading: MA3252 | MA2104 | ST2131 | MA2101S
Name: Penny Lane
Modules reading: DTK1234 | HSH1000 | HSS1000 | MA1100 | GEA1000
```

### Deleting a contact: `delete`

Allow users to delete a contact.
Format: `delete n/NAME`
Example of usage: `delete n/John Doe`
Context:
```
Name: John Doe (User)
Modules reading: CS2103T | CS2101 | MA2104 | MA3252 | CFG1002
```

Expected outcome for CLI:
```
Name: John Doe (User)
Modules reading: CS2101 | MA2104 | MA3252 | CFG1002
```

Description of outcome:
This command removes the module stated in the command (“CS2103T”) from the list of modules that the user is reading.


### Tagging a module to contact : `tag`

Adds module tag(s) to an existing contact.

Formats:
* `tag CONTACT_INDEX m/MODULE_TAG`
* `tag m/MODULE_TAG`

Example of usage: `tag 3 m/CS2103T`
```
Name: John Doe (User)
Modules: [CS2101 , MA2104 , MA3252 , CFG1002]
```

Expected outcome for CLI:
```
Module(s) tagged to Person!
Name: John Smith
Modules: [CS2100, CS2101, CS2102, CS2103T]
Module(s) in common: [CS2101, CS2103T]
```
Description of outcome:

CS2103T is added to John Doe's list of modules. Assuming the user also takes CS2101 and CS2103T, which are represented as the modules in common.

Example of usage: tag m/CS2103T
```
Name: John Doe (user)
Modules: [CS2101, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
Module(s) tagged to Person!
Name: John Doe
Modules: [CS2101, CS2103T, MA2104, MA3252, CFG1002]
```
Description of outcome:

CS2103T is added to John Doe's, the user, list of modules.

### Untagging a module from an existing contact : `untag`

Removes a module tag from an existing contact.

Formats:
* `untag CONTACT_INDEX m/MODULE_TAG`
* `untag m/MODULE_TAG`

Example of usage: `untag 3 m/CS2103T`
```
Name: John Doe (User)
Modules: [CS2101, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
"Module(s) untagged to Person!
Name: John Smith
Modules: [CS2100, CS2101, CS2102]
Module(s) in common: [CS2101]
```
Description of outcome:

CS2103T is removed from John Doe's list of modules. Assuming the user also takes CS2101, which is represented as the modules in common.

Example of usage: untag m/CS2103T
```
Name: John Doe (user)
Modules: [CS2101, CS2103T, MA2104, MA3252, CFG1002]
```

Expected outcome for CLI:
```
"Module(s) untagged to Person!
Name: John Doe
Modules: [CS2101, MA2104, MA3252, CFG1002]
```
Description of outcome:

CS2103T is added to John Doe's, the user, list of modules.

### Locating persons by keyowrds : `find`

Finds persons whose specified fields contain any of the given keywords.
Format: `find PREFIX/KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the field specified by the prefix is searched. e.g. n/ means only the name field is searched
* Words matching the first part of the string will be matched e.g. Han will match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

Example of usage:
* `find m/CS2103T CS2109S` returns all persons with modules CS2103T or CS2109S
* `find n/John Doe` returns all persons with names John or Doe
* `find p/9093` returns all persons with phone numbers starting with 9093
Expected outcome:
```
Name: John Doe | Jane Lane | John Street
Modules reading: CS2103T | CS2101 | MA2104 | MA3252 | CFG1002

Name: Jane Doe
Modules reading: CS2103T | CS2101 | CM1102 | CS2102 | CS2109S
```
Description of outcome: Assuming that John Doe, Jane Lane, John Street has CS2103T as a module tag to their profiles, then their contacts will appear as shown.

### Sorting Contacts : `sort`

Sorts all contacts by number of common modules, with contacts with most common modules at the top.
Format: `sort` <br>
Example of usage: `sort` <br>
Context:
There are 4 friends with the following information:
```
Name: John Doe (User)
Modules reading: CS2103T | CS2101 | MA2104 | MA3252 | CFG1002
Name: Ben Tan
Modules reading: CS2103T | CS2101 | MA3252 | CS4230 | CS2105
Name: Jane Lane
Modules reading: CS2103T | CS2101 | CM1102 | CS2102 | CS2108
Name: John Street
Modules reading: MA3252 | MA2104 | ST2131 | MA2101S
Name: Penny Lane
Modules reading: DTK1234 | HSH1000 | HSS1000 | MA1100 | GEA1000
```
Expected outcome:
```
Name: Ben Tan
3 Common Modules: CS2103T | CS2101 | MA3252
Name: Jane Lane
2 Common Modules: CS2103T | CS2101
Name: John Street
1 Common Module: MA3252
```

Description of outcome: Assuming you have 4 friends, then there are 3 of them who are doing common modules as you, they will be sorted by decreasing number of similarity in the modules.
Hence, Ben Tan will be the first name on top as he has the most number of modules in common with the user, followed by Jane Lane and John Street with 2 and 1 common modules respectively. The 4th friend, Penny Lane, does not have any common modules with the user (John Doe) and hence is omitted.

### View Contacts : `view`

Users can use the view command to look up their current information, or their contact's information on the Panel to the right.
There are 3 types of usage for this command.
<br><br>
Example of usage: `view` <br>
Expected outcome: User's own profile displayed on the panel. <br>
![result for 'user profile' GUI](images/userprofile.png)
<br><br>
Example of usage: `view 5` <br>
Expected outcome: The profile of the 5th person indexed by EduMate will be displayed on the panel.
![result for '5th person profile' GUI](images/fifthPersonProfile.png)
<br><br>
Example of usage: `view n/Charles Windsor` <br>
Expected outcome: The profile of Charles Windsor will be displayed on the panel.
![result for 'Charles Windsor profile' GUI](images/charlesWindsor.png)
## FAQ

**Q**: Can I add multiple module groupTags to a user
**A**: Yes, you can add multiple groupTags to a single user by using the "Module Tagging" feature multiple times.

**Q**: Can I edit my own profile?
**A**: It will not be in v1.02, but it may be possible for future iterations.


## Command summary

| Action            | Format, Examples                                                                     |
|-------------------|--------------------------------------------------------------------------------------|
| **User**          | `user`                                                                               |
| **Add Person**    | `add n/NAME` <br> e.g., `add n/John Doe`                                             |
| **Delete Person** | `delete n/NAME`<br> e.g., `delete n/John Doe`                                        |
| **Tag Module**    | `tag n/NAME m/MODULE_TAG`<br> e.g.,`tag n/John Doe m/CS2103T`                        |
| **Untag Module**  | `untag n/NAME m/MODULE_TAG`<br> e.g., `untag n/John Doe m/CS2103T`                   |
| **Filter By Mod** | `filter m/MODULE_TAG` <br> e.g., `filter m/CS2103T`                                  |
| **Sort**          | `sort`                                                                               |
| **View**          | `view`<br/> `view <index>`<br/>`view n/NAME`<br/>e.g., `view 5` or `view n/Komyo San` |

