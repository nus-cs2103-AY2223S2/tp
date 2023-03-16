# User Guide

GoodMatch (GM) is a  **desktop app for managing applicants and job listings, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, GM can get your applicant and job listing management tasks done faster than traditional GUI apps

# Quick Start

1. Ensure you have Java  `11`  or above installed in your Computer.
2. Download the latest  `goodmatch.jar`  from  [here](https://github.com/se-edu/addressbook-level3/releases). //change the link for this
3. Copy the file to the folder you want to use as the  *home folder*  for your GoodMatch.
4. Open a command terminal,  `cd`  into the folder you put the jar file in, and use the  `java -jar goodmatch.jar`  command to run the application.

A GUI similar to the one below should appear in a few seconds.

![Mock Up](./images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing  **`help`**  and pressing Enter will open the help window.

Some example commands you can try:
* add something here
6. Refer to the  [Features](https://www.notion.so/User-Guide-23950683b3cc4950a36706df6ae00bf4)  below for details of each command.

# Features

💡 **Notes about the command format:**

* Words in  `UPPER_CASE`  are the parameters to be supplied by the user.e.g. in  `add n/NAME`,  `NAME`  is a parameter which can be used as  `add n/John Doe`.
* Items in square brackets are optional.e.g  `n/NAME [t/TAG]`  can be used as  `n/John Doe t/friend`  or as  `n/John Doe`.
* Items with  `…` after them can be used multiple times including zero times.e.g.  `[t/TAG]…`  can be used as    ``  (i.e. 0 times),  `t/friend`,  `t/friend t/family`  etc.
* Parameters can be in any order.e.g. if the command specifies  `n/NAME p/PHONE_NUMBER`,  `p/PHONE_NUMBER n/NAME`  is also acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.e.g. if you specify  `p/12341234 p/56785678`, only  `p/56785678`  will be taken.
* Extraneous parameters for commands that do not take in parameters (such as  `help`,  `list`,  `exit`  and  `clear`) will be ignored. e.g. if the command specifies  `help 123`, it will be interpreted as  `help`.

## Viewing help [coming soon]

Type **`help`**  and press Enter. This will open the help window to show all the commands available.

## Post a new job listing [coming soon]

Type **`new`**

| Recruiter | post a new job posting | I can start attracting applicants. |
| --------- | ---------------------- | ---------------------------------- |

## Viewing job listing information [coming soon]

| Recruiter | list out information regarding a job offer | I can confirm the information |
| --------- | ------------------------------------------ | ----------------------------- |

## Editing a job listing [coming soon]

| Recruiter | update job listings | it reflects the most updated information |
| --------- | ------------------- | ---------------------------------------- |

## Tagging a job listing [coming soon]

| Recruiter | add tag indicating relevant hiring department to the job listing |     |
| --------- | ---------------------------------------------------------------- | --- |
| Recruiter | view the tag attached to the job listing                         |     |
| Recruiter | change the tag attached to the job listing                       |     |

## Deleting a job listing [coming soon]

| Recruiter | delete outdated job listing                             | the information is accurate                  |
| --------- | ------------------------------------------------------- | -------------------------------------------- |
| Recruiter | auto-remove job listings from view if they are expired  | viewing job listings won't be cluttered      |
| Recruiter | auto-remove job listings from view if vacancy fulfilled | the current list of jobs are still available |

## Viewing all job listings [coming soon]

| Recruiter | view all job listings |     |
| --------- | --------------------- | --- |

## Searching job listings [coming soon]

| Recruiter | search job listings by title |     |
| --------- | ---------------------------- | --- |

## Sorting job listings [coming soon]

### By expiry date [coming soon]

| Recruiter | sort job listings by their expiry date | finding the most urgent job listing can be easy |
| --------- | -------------------------------------- | ----------------------------------------------- |

## Add applicant information [coming soon]

Type **`new`**

| As a recruiter who receives thousands of applications each day. | store all information about my applicants | I don't lose track of any applicants. |
| --------------------------------------------------------------- | ----------------------------------------- | ------------------------------------- |

## Viewing applicants for each job listing [coming soon]

| Recruiter | view applicants for each job listing | I know who has applied for each job |
| --------- | ------------------------------------ | ----------------------------------- |

## Adding applicants to each job listing [coming soon]

| Recruiter | add applicants to job listing                                   |                                         |
| --------- | --------------------------------------------------------------- | --------------------------------------- |
| Recruiter | prevent applicants from applying to the same job more than once | the list of applicants is not cluttered |

## Viewing applicant details [coming soon]

| Recruiter | view applicant details | I can make sure it’s updated |
| --------- | ---------------------- | ---------------------------- |

## Removing applicants from each job listing [coming soon]

| Recruiter | remove disqualified applicant | the database stays clean |
| --------- | ----------------------------- | ------------------------ |

## Updating applicant details and status [coming soon]

| Recruiter | update applicant status  | it’s easy for me to track the progress of each applicant |
| --------- | ------------------------ | -------------------------------------------------------- |
| Recruiter | update applicant details | each applicant’s info is updated                         |

## Viewing all applicants [coming soon]

| Recruiter | view all applicants |     |
| --------- | ------------------- | --- |

### By name [coming soon]

### By number of applicants [coming soon]

### By tag [coming soon]

| A recruiter from a particular department | filter the job listings according to the tag |     |
| ---------------------------------------- | -------------------------------------------- | --- |

## Exiting the program [coming soon]

## Saving the data [coming soon]

| Recruiter | come back to the programme and continue from where I left off | I won't lose my progress |
| --------- | ------------------------------------------------------------- | ------------------------ |

## Importing the data [coming soon]

---

# FAQ

---

# Command summary
