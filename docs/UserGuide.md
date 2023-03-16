# User Guide

GoodMatch (GM) is a **desktop app for managing applicants and job listings, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, GM can get your applicant and job listing management tasks done faster than traditional GUI apps

# Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `goodmatch.jar` from [here](https://github.com/se-edu/addressbook-level3/releases). //change the link for this
3. Copy the file to the folder you want to use as the *home folder* for your GoodMatch.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar goodmatch.jar` command to run the application. 


A GUI similar to the one below should appear in a few seconds. 
    
    ![Screenshot 2023-03-15 at 1.02.55 AM.png](User%20Guide%2023950683b3cc4950a36706df6ae00bf4/Screenshot_2023-03-15_at_1.02.55_AM.png)
    
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window. 

Some example commands you can try:
    - add something here
6. Refer to the [Features](https://www.notion.so/User-Guide-23950683b3cc4950a36706df6ae00bf4) below for details of each command.

# Features

<aside>
ℹ️ ************************Notes about the command format:************************

- Words in `UPPER_CASE` are the parameters to be supplied by the user.e.g. in `add t/TITLE`, `TITLE` is a parameter which can be used as `add t/Chicken Rice Uncle`.
- Items in square brackets are optional.e.g `t/TITLE [a/APPLICANT]...` can be used as `t/Chicken Rice Uncle a/John Tan` or as `t/Chicken Rice Uncle`.
- Items with `…` after them can be used multiple times including zero times.e.g. `[a/APPLICANT]…` can be used as  `` (i.e. 0 times), `a/applicant1`, `a/applicant2 a/application3` etc.
- Parameters can be in any order.e.g. if the command specifies `t/TITLE d/DESCRIPTION`, `d/DESCRIPTION t/TITLE` is also acceptable.
- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.e.g. if you specify `t/Chicken Rice Uncle t/Chicken Rice Auntie`, only `t/Chicken Rice Uncle` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</aside>

## Viewing help: `help`

Shows a message explaining how to access the help page.

![Untitled](User%20Guide%2023950683b3cc4950a36706df6ae00bf4/helpMessage.png)

****************Format:**************** `help`

## Listing all job listings: `list`

Lists out all the jobs that are currently in the listing book.

**Format:** `list` 

## Adding a job listing: `add`

Adds a listing to the listing book.

**Format:** `add t/TITLE d/DESCRIPTION [a/APPLICANT]...`

**Tips:**

- A listing can have any number of applicants (including 0)

**Examples:**

- `add t/Chicken Rice Uncle d/Cooks tasty chicken rice a/Nicholas a/Tom a/Adele`
- `add t/NodeFlair SWE Intern d/Fullstack Experience`

## Editing a job listing: `edit`

Edits the details of a job listing.

**Format:** `edit NUMBER t/TITLE d/DESCRIPTION [a/APPLICANTS...]`

********Tips:********

- Ensure that `NUMBER` is valid (ie. its is non-negative and not greater than the number of tasks) or an error will occur!
- Only the details included in the command will be edited. eg. if the command entered is:
    
     `edit 1 t/Chicken Rice Uncle d/Cooks tasty chicken rice`
    
    then only the title and the description of task 1 will be edited (the applicants will remain unchanged)
    

**************Examples:**************

- `edit 1 t/Noodle seller d/Cooks tasty noodles a/Johnson`
- `edit 2 t/Videographer d/Films videos`

## Deleting a job listing: `delete`

Deletes a job listing from the listing book.

**************Format:************** `delete NUMBER`

********Tips:********

- Ensure that `NUMBER` is valid (ie. its is non-negative and not greater than the number of tasks) or an error will occur!

******************Examples:******************

- `delete 1`

## Locating job listings by title: `find`

Finds job listings whose titles contain any of the given keywords.

**Format:** `find KEYWORD [MORE_KEYWORDS]`

**********Tips:**********

- The search is case-insensitive. e.g `software` will match `Software`
- The order of the keywords does not matter. e.g. `Software Developer` will match `Developer Software`
- Only the title is searched.
- Only full words will be matched e.g. `Engineer` will not match `Engineering`
- Job listings matching at least one keyword will be returned (i.e. `OR` search). e.g. `Software Developer` will return `Software Engineer`, `Microservices Developer`

**Examples:**

- `find Software` returns `software` and `Software Developer`
- `find Software Engineer` returns `Software Developer`, `Chemical Engineer`

## Sorting job listings: `sort`

Sort job listings by the field specified by the user and display the sorted list of job listings.

**Format:** `sort by/[POSSIBLE_FIELDS]`

- The possible fields are: `expiry date` , `title` , `num_of_applicants` , `description` , `...`
- String fields are sorted in lexicographical order
- Date fields are sorted by date and time
- Number fields are sorted by value

**Examples:**

- `sort by/num_of_applicants`

### 

## Filtering job listings: `filter`

Sort job listings by the field specified by the user and display the sorted list of job listings.

**Format:** `filter attribute/[POSSIBLE_FIELDS] by/[SOME_VALUE]`

**********Tips:**********

- The possible fields are: `expiry date`  , `num_of_applicants`  , `...`
- Fields have to be numerical
- Metrics can be more than or less than or equal

**Examples:**

- `filter attribute/num_of_applicants by/>= 5`

## Tagging a job listing [coming soon]

Adds tags to 

| Recruiter | add tag indicating relevant hiring department to the job listing |  |
| --- | --- | --- |
| Recruiter | view the tag attached to the job listing |  |
| Recruiter | change the tag attached to the job listing |  |

## Add applicant information [coming soon]

Type **`new`**

| As a recruiter who receives thousands of applications each day. | store all information about my applicants | I don't lose track of any applicants. |
| --- | --- | --- |

## Viewing applicants for each job listing [coming soon]

| Recruiter | view applicants for each job listing | I know who has applied for each job |
| --- | --- | --- |

## Adding applicants to each job listing [coming soon]

| Recruiter | add applicants to job listing |  |
| --- | --- | --- |
| Recruiter | prevent applicants from applying to the same job more than once | the list of applicants is not cluttered |

## Viewing applicant details [coming soon]

| Recruiter | view applicant details | I can make sure it’s updated |
| --- | --- | --- |

## Removing applicants from each job listing [coming soon]

| Recruiter | remove disqualified applicant | the database stays clean |
| --- | --- | --- |

## Updating applicant details and status [coming soon]

| Recruiter | update applicant status | it’s easy for me to track the progress of each applicant |
| --- | --- | --- |
| Recruiter | update applicant details | each applicant’s info is updated |

## Viewing all applicants [coming soon]

| Recruiter | view all applicants |  |
| --- | --- | --- |

### By name

### By number of applicants

### By tag

| A recruiter from a particular department | filter the job listings according to the tag |  |
| --- | --- | --- |

## Exiting the program [coming soon]

## Saving the data [coming soon]

| Recruiter | come back to the programme and continue from where I left off | I won't lose my progress |
| --- | --- | --- |

## Importing the data [coming soon]

---

# FAQ

---

# Command summary