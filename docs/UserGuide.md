---
layout: page
title: User Guide
---

# GoodMatch

## **Overview**

GoodMatch (GM) is a **desktop app for managing applicants and job listings, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI), specifically catering to HR managers in charge of tracking job listings across many platforms. If you can type fast, GM can get your applicant and job listing management tasks done faster than traditional GUI apps.

---

### Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help--help)
  - [Viewing all job listings: `view`](#viewing-all-job-listings--view)
  - [Adding a job listing: `add`](#adding-a-job-listing--add)
  - [Editing a job listing: `edit`](#editing-a-job-listing--edit)
  - [Deleting a job listing: `delete`](#deleting-a-job-listing--delete)
  - [Adding an applicant to listing: `add_app`](#adding-an-applicant-to-listing--addapp)
  - [Editing an applicant of a listing: `edit_app`](#editing-an-applicant-of-a-listing--editapp)
  - [Deleting an applicant from a listing: `del_app`](#deleting-an-applicant-from-a-listing--delapp)
  - [Adding a platform: `add_plat`](#adding-a-platform-add_plat)
  - [Deleting a platform: `del_plat`](#delete-a-platform-from-a-listing-del_plat)
  - [Locating job listings by title: `find`](#locating-job-listings-by-title--find)
  - [Sorting job listings: `sort`](#sorting-job-listings--sort)
  - [Filtering job listings `[coming in v2.0]`](#filtering-job-listings-coming-soon)
  - [Tagging a job listing `[coming in v2.0]`](#tagging-a-job-listing-coming-soon)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

### Purpose of this guide

Welcome to the user guide for GoodMatch. This guide will help you understand how to use GoodMatch to streamline your recruitment process. We aim to help you find the best candidates for your company. 

### How to use this guide

To make the most of this guide, start by reading it from beginning to end. We recommend that you familiarize yourself with the basic concepts before moving on to the advanced topics.

Use the interactive [table of contents](#table-of-contents) to navigate through the document quickly. Simply click on the bullet points to be taken to the relevant subsection. Follow the step-by-step instructions, screenshots, and examples to get the most out of the guide.

### Legends

ℹ️ **Notes**: You can find additional information about the command or feature here.

❗ **Caution**: Be careful not to make this deadly mistake.

---

## **Quick Start**

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `goodmatch.jar` from [here](https://github.com/AY2223S2-CS2103T-W14-3/tp/releases/tag/v1.2).
3. Copy the file to the folder you want to use as the _home folder_ for your GoodMatch.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar goodmatch.jar` command to run the application.

A GUI similar to the one below should appear in a few seconds.

![Mock Up](./images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

Some example commands you can try:

- `view`: Lists all listings.
- `add t/Chicken Rice Uncle d/Cooks tasty chicken rice a/Tom`: Adds a listing called `Chicken Rice Uncle` into the Listing Book.
- `delete 1`: Deletes the 1st listing shown in the current list.
- `exit`: Exits the app.

6. Refer to the [Features](#features) below for details of each command.

###### _< Back to [Table of Contents](#table-of-contents) >_

---

## **Features**
Here is a list of GM features, click on them to jump to the section!

1. [`help`](#viewing-help--help)
2. [`list`](#listing-all-job-listings--list)
3. [`add`](#adding-a-job-listing--add)
4. [`edit`](#editing-a-job-listing--edit)
5. [`delete`](#deleting-a-job-listing--delete)
6. [`add_app`](#adding-an-applicant-to-listing--addapp)
7. [`edit_app`](#editing-an-applicant-of-a-listing--editapp)
8. [`del_app`](#deleting-an-applicant-from-a-listing--delapp)
9. [`find`](#locating-job-listings-by-title--find)
10. [`sort`](#sorting-job-listings--sort)
11. [Saving data](#saving-the-data)
12. [Editing data file](#editing-the-data-file)


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be **supplied by the user**. E.g. in `add t/TITLE`, `TITLE` is a parameter which can be used as `add t/Chicken Rice Uncle`.


- Items in square brackets are **optional**. E.g. `t/TITLE [a/APPLICANT]...` can be used as `t/Chicken Rice Uncle a/John Tan` or as `t/Chicken Rice Uncle`.


- Items with `…` after them can be used **multiple times** including **zero times**. E.g. `[a/APPLICANT]…` can be used as `` (i.e. 0 times), `a/applicant1`, `a/applicant2 a/application3` etc.


- Parameters can be in **any order**. E.g. if the command specifies `t/TITLE d/DESCRIPTION`, `d/DESCRIPTION t/TITLE` is also acceptable.


- If a parameter is expected only once in the command, but you specified it multiple times, only the **last occurrence** of the parameter will be taken. E.g. if you specify `t/Chicken Rice Uncle t/Chicken Rice Auntie`, only `t/Chicken Rice Auntie` will be taken.


- Extraneous parameters for commands that do not take in parameters (such as `help`, `view` and `exit`) will be **ignored**. E.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

---

### Viewing help: `help`

Shows a message explaining how to access the help page.

![Help Message](./images/helpMessage.png)

**Format:** `help`

###### _< Back to [Table of Contents](#table-of-contents) >_

### Viewing all job listings: `view`

Lists out all the jobs that are currently in the listing book.

**Format:** `view`

**Expected Output:**

- You should see all the listings that are currently in your listing book, as well as the confirmation message:

```ignorelang
Listed all listings
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Adding a job listing: `add`

Adds a listing to the listing book.

**Format:** `add t/TITLE d/DESCRIPTION [a/APPLICANT]...`

**Notes:**

- A listing can have any number of applicants (including 0)

**Examples:**

- `add t/Chicken Rice Uncle d/Cooks tasty chicken rice a/Nicholas a/Tom a/Adele`
- `add t/NodeFlair SWE Intern d/Fullstack Experience`

**Expected Output:**
You should see a confirmation message showing the job title, description and applicants (if any). An example is shown below:

```ignorelang
Added new listing:
Job Title: Chicken Rice Uncle
Job Description: Cooks tasty chicken rice
Applicants: Nicholas, Tom, Adele
```

**Possible Error:**

If the above format is not followed, an error message will be displayed.

```ignorelang
Invalid Command Format!
add: Adds a listing to the listing book.
Parameters: t/TITLE d/DESCRIPTION [a/APPLICANT]... [p/PLATFORMS]...
Example: add t/Cool job title d/Informative job description a/John a/Sam
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Editing a job listing: `edit`

Edits the details of a job listing.

**Format:** `edit INDEX [t/TITLE] [d/DESCRIPTION] [a/APPLICANTS]... [p/PLATFORMS]...`

**Notes:**

- Ensure that `INDEX` is valid (i.e. it is non-negative and not greater than the number of tasks) or an error will occur!
- Only the details included in the command will be edited. E.g. if the command entered is:

  `edit 1 t/TITLE d/DESCRIPTION`

  then only the title and the description of listing 1 will be edited (the applicants will remain unchanged)

**Examples:**

- `edit 1 t/Noodle seller d/Cooks tasty noodles a/Johnson`
- `edit 2 t/Videographer d/Films videos`

**Expected Output:**

You will see a confirmation message showing the edited job title, description and applicants (if any).
An example is shown below:

```ignorelang
Edited listing:
Job Title: Noodle seller
Job Description: Cooks tasty noodles
Applicants: Johnson
```

**Possible Error:**

A valid index must be provided, and at least one field of the listing must be edited. Otherwise, one of the error
messages shown below will be displayed.

```ignorelang
Invalid command format!
edit: Edits a listing identified by the index used in the displayed listing book.
      Existing values will be overwritten by the input values.
Parameters: INDEX (must be a positive integer) [t/TITLE] [d/DESCRIPTION] [a/APPLICANT]...
Example: edit 1 t/Cool job title a/John a/Sam
```

```ignorelang
The listing index provided exceeded the number of listings shown!
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Deleting a job listing: `delete`

Deletes a job listing from the listing book.

**Format:** `delete INDEX`

**Notes:**

- Ensure that `INDEX` is valid (ie. it is non-negative and not greater than the number of tasks) or an error will occur!

**Examples:**

- `delete 1`

**Expected Output:**

A confirmation message will show, along with the details of the listing. An example message is shown below:

```ignorelang
Deleted listing:
Job Title: Chicken farmer
Job Description: farms chickens
Applicants: Tommy, Connor
```

**Possible Error:**
If an index is invalid or absent, an error message such as the one below will be displayed.

```ignorelang
Invalid command format!
delete: Deletes the listing identified by the index used in the displayed listing book.
Parameters: INDEX (must be a positive integer)
Example: delete 1
```

```ignorelang
The listing index provided exceeded the number of listings shown!
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---
### Adding an applicant to listing: `add_app`

Adds an applicant to a specified listing.

**Format:** `add_app INDEX a/APPLICANT`

**Notes:**

- A listing can have any number of applicants
- Command will only take the latest applicant given

**Examples:**

- `add_app 1 a/Tom`

**Expected Output:**
You should see a confirmation message showing the job title, description and applicants (if any). An example is shown below:

```ignorelang
Applicant Tom added to the listing Computer Science Intern
```


**Possible Error:**

If an index is invalid or absent, an error message such as the one below will be displayed.

```ignorelang
Invalid command format! 
add_app: Adds an applicant to a listing in the listing book.
Parameters: INDEX (must be a positive integer) a/APPLICANT
Example: add_app 1 a/John Doe
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Editing an applicant of a listing: `edit_app`

Edits the name of an applicant in a specified listing.

**Format:** `edit INDEX id/APPLICANT a/APPLICANT`

**Notes:**

- Ensure that `INDEX` is valid (i.e. it is non-negative and not greater than the number of tasks) or an error will occur!
- Applicant given with the id/ flag will be edited to the applicant given with the a/ flag.
- If the listing has applicants of the same name, the unique id has to be specified with the id/ flag

**Examples:**

- `edit_app 1 id/Tom a/Johnson`
- `edit_app 2 id/Tom#1334 a/Johnson`

**Expected Output:**

You will see a confirmation message showing the edited job title, description and applicants (if any).
An example is shown below:

```ignorelang
Applicant: Tom#1334 has been edited to Johnson in Computer Science Intern
```

**Possible Error:**

A valid index must be provided, and same name applicants must be specified with their unique id. Otherwise, one of the error
messages shown below will be displayed.

```ignorelang
Invalid command format! 
edit_app: Edits an applicant's details for a listing. Ordered by the index number of the listing used in the displayed listing book.
      Existing values will be overwritten by the input values.
Parameters: INDEX (must be a positive integer) id/OLD_APPLICANT a/NEW_APPLICANT
Example: edit_app 1 id/John a/Sam
```

```ignorelang
The listing index provided exceeded the number of listings shown!
```

```ignorelang
There are multiple applicants with the name Tom in Computer Science Intern, 
specify the 4-digit unique identifier after the name.
Example: edit_app 1 id/John Doe#2103 a/Sam
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Deleting an applicant from a listing: `del_app`

Deletes an applicant from a specified listing.

**Format:** `del_app INDEX id/APPLICANT`

**Notes:**

- Ensure that `INDEX` is valid (ie. it is non-negative and not greater than the number of tasks) or an error will occur!
- If the listing has applicants of the same name, the unique id has to be specified with the id/ flag.
- Ensure that the applicant to delete exists.

**Examples:**

- `del_app 1 id/Tom`
- `del_app 2 id/Tom#1334`

**Expected Output:**

A confirmation message will show, along with the details of the listing. An example message is shown below:

```ignorelang
Applicant: Tom has been deleted from Computer Science Intern!
```

**Possible Error:**

If an index is invalid or the command does not follow the format, an error message such as the ones below will be displayed.

```ignorelang
Invalid command format! 
del_app: Deletes an applicant from a listing identified by the index number used in the displayed listing book.
Parameters: INDEX (must be a positive integer) id/APPLICANT
Example: del_app 1 id/John Doe
*If there are duplicated names, specify the id by adding the 4-digit unique identifier after the name.
Example: del_app 1 id/John Doe#2103

```

```ignorelang
The listing index provided exceeded the number of listings shown!
```

If the applicant does not exist in the listing, an error message such as the one below will be displayed.
```ignorelang
Applicant Tom cannot be found in Computer Science Intern.
```

If the unique id is not specified when needed, an error message such as the one below will be displayed.
```ignorelang
There are multiple applicants with the name Tom in Computer Science Intern, 
specify the 4-digit unique identifier after the name.
Example: del_app 1 id/John Doe#2103
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Adding a platform: `add_plat`

Adds a new platform to the specified job listing.

**Format:** `add_plat INDEX p/PLATFORM`

**Notes:**

- Ensure that INDEX is valid (ie. it is non-negative and not greater than the number of tasks) or an error will occur!
- Ensure that the platform added does not already exist in the job listing.
- The platform name must contain only alphanumeric characters and cannot be blank.
- The command only adds one platform at a time. Should there be more than one platform per command then only the latest platform will be taken into consideration. If the lastest platform provided is invalid, an error will occur even if the rest of the provided platforms are valid.

**Examples:**
- `add_plat 1 p/linkedin`
- `add_plat 3 p/indeed`
- `add_plat 2 p/glints p/jobstreet` (only "jobstreet" will be added)

**Expected Output:**
A confirmation message showing the platform as well as the title of the listing that it is added to will be shown. An example of the message is shown below:
```ignorelang
Platform linkedin added to listing Computer Science Intern
```

**Possible Errors:**

If the index provided exceeds the number of listings shown, an error message will show:
```ignorelang
The listing index provided exceeded the number of listings shown!
```

If the platform provided is a duplicate and already exists in the listing, the following error message will show:
```ignorelang
This listing already contains the provided platform.
```

If the platform name provided is invalid, the following error message will show:
```ignorelang
Names should only contain alphanumeric characters and spaces, and it should not be blank.
```

If there are any other deviations from the expected format of the command, there will be an error message shown along with the correct usage of the command:
```ignorelang
Invalid command format! 
add_plat: Adds a platform to a listing in the listing book.
Parameters: INDEX (must be a positive integer) p/PLATFORM
Example: add_plat 1 p/LinkedIn
```


###### _< Back to [Table of Contents](#table-of-contents) >_

--- 
### Delete a platform from a listing: `del_plat`

Deletes a platform from a specified job listing.

**Format:** `del_plat INDEX p/PLATFORM`

**Notes:** 

- Ensure that INDEX is valid (ie. it is non-negative and not greater than the number of tasks) or an error will occur!
- Ensure that the platform provided exists in the specified listing.
- If more than one platform is provided per command, only the latest platform will be taken into consideration. (An error will occur if the last provided platform is invalid, even if the rest are valid).

**Examples:**

- `del_plat 1 p/linkedin`
- `del_plat 2 p/linkedin p/glints` - Only "glints" will be deleted

**Expected Output:**
A confirmation message showing the deleted platform and the listing it was deleted from will be displayed, such as the one shown below:
```ignorelang
Platform: glints has been deleted from Computer Science Intern!
```

**Possible Errors:**

If the index provided exceeds the number of listings shown, an error message will show:
```ignorelang
The listing index provided exceeded the number of listings shown!
```

If the platform provided does not exist in the specified listing, an error message such as the one below will show:
```ignorelang
Platform linkedin cannot be found in Computer Science Intern.
```

If there are other deviations from the expected command format, an error message will be displayed along with the expected usage of the command:
```ignorelang
Invalid command format! 
del_plat: Deletes a platform from a listing identified by the index number used in the displayed listing book.
Parameters: INDEX (must be a positive integer) p/PLATFORM
Example: del_plat 1 p/LinkedIn
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---


### Locating job listings by title: `find`

Finds job listings whose titles contain any of the given keywords.

**Format:** `find KEYWORD [MORE_KEYWORDS]`

**Notes:**

- The search is case-insensitive. eg. `software` will match `Software`
- The order of the keywords does not matter. e.g. `Software Developer` will match `Developer Software`
- Only the title is searched.
- Only full words will be matched e.g. `Engineer` will not match `Engineering`
- Job listings matching at least one keyword will be returned (i.e. `OR` search). e.g. `Software Developer` will return `Software Engineer`, `Microservices Developer`

**Examples:**

- `find Software` returns `software` and `Software Developer`
- `find Software Engineer` returns `Software Developer` and `Chemical Engineer`

**Expected Output**:
A confirmation message will display. The message indicates the number of listings which match the given keyword(s).
An example is shown below.

```ignorelang
1 listing(s) shown!
```

If no listings match the keyword(s), the following message will show:

```ignorelang
0 listing(s) shown!
```

**Possible Error**
If no keyword is provided, an error message will display:

```ignorelang
Invalid command format!
find: Finds all listings whose titles contain any of the specified keywords (case-insensitive).
      Displays them as a list with index numbers.
Parameters: KEYWORD [MORE_KEYWORDS]...
Example: find chicken rice
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Sorting job listings: `sort`

Sort job listings by the field specified by the user and display the sorted list of job listings.

**Format:** `sort f/[FIELD]`

- The possible fields are: `title` , `description` , `applicants`

**Notes:**

- The sorting logic will always be applied until it is overridden by a `sort f/none` command.
- The `title` field sorts the listings in alphabetical order of their title fields.
- The `description` field sorts the listings in alphabetical order of their listings.
- The `applicants` field sorts the listings in increasing number of applicants.
- The `none` field resets the sorter and stop sorting the listings.
- Note that if more than one field is provided, the last field will be taken into consideration.

**Examples:**

- `sort f/title`
- `sort f/description`

**Expected Output:**
A confirmation message will show:

```ignorelang
Listings have been sorted.
```

**Possible Error:**
If the `field` is not present, an error message will show:

```ignorelang
Invalid command format!
sort: Sorts the listing displayed according to the specified field.
Parameters: f/FIELD_COMPARED(none, title, description, applicants)
Example: sort f/applicants
```

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Saving the data

GoodMatch data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Editing the data file

GoodMatch data are saved as a JSON file `[JAR file location]/data/listingbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, GoodMatch will discard all data and start with an empty data file at the next run.

</div>

###### _< Back to [Table of Contents](#table-of-contents) >_

---

### Filtering job listings [coming soon!]

Sort job listings by the field specified by the user and display the sorted list of job listings.

**Format:** `filter attribute/[POSSIBLE_FIELDS] by/[SOME_VALUE]`

**Notes:**

- The possible fields are: `expiry date` , `num_of_applicants` , `...`
- Fields have to be numerical
- Metrics can be more than or less than or equal

**Examples:**

- `filter attribute/num_of_applicants by/>= 5`

###### _< Back to [Table of Contents](#table-of-contents) >_

### Tagging a job listing [coming soon]

Add tags to a job listing for easy reference.

###### _< Back to [Table of Contents](#table-of-contents) >_

---

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous GoodMatch home folder.

###### _< Back to [Table of Contents](#table-of-contents) >_

---

## **Command summary**

| Action     | Format, Examples                                                                                                                                   |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **add**    | `add t/TITLE d/DESCRIPTION [a/APPLICANTS]` <br> e.g., `add t/Chicken Rice Uncle d/Cooks tasty chicken rice a/Nicholas a/Tom a/Adele` |
| **delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                  |
| **edit**   | `edit [t/TITLE] d/DESCRIPTION [a/APPLICANTS]`<br> e.g.,`edit 2 t/Noodle Seller d/Makes tasty noodles`                                |
| **find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Chicken Noodle`                                                                       |
| **view**   | `view`                                                                                                                               |
| **help**   | `help`                                                                                                                               |

###### _< Back to [Table of Contents](#table-of-contents) >_
