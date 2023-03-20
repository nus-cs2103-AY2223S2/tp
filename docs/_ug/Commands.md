## Trying your first command

To let you become more familiar with HMHero, let's practise executing some commands.

To start off, let's try out the `add` command! This command lets you add an [applicant](#) to HMHero.

One of the available commands in HMHero is the command to create a new applicant.

**Format:** `add n/NAME p/PHONE e/EMAIL a/ADDRESS [note/NOTE]`

**What does the format mean?** 

* `add` tells HMHero that this is the command to create a new applicant
* [flag](#) such as `n/` and `p/` are [[ delimiter:delimiters ]] that enable HMHero to distinguish different 
[[ parameter:parameters ]] supplied by you without ambiguity
* [Placeholders](#) such as `NAME` and `PHONE` shows you what you should place in each portion of the command

Notice that there is a pair of square brackets `[]` surrounding some [[ parameter:parameters ]] like `note/NOTE`
in the format. This indicates that the parameter is **optional**. Each of these [[ placeholder:placeholders]] in the
[[ parameter:parameters ]] have a default value based on the commands. These are documented in the [Commands](#commands)
section for each command.

```
🚨NOTE
The [Placeholder](#placeholders) section covers the restrictions for respective placeholders. For example, 
the date format of PHONE, certain characters you cannot use and the limit and precision of numbers.
```

**Let's try an example!**

Suppose you just add Thomas, 91918153, thomas@gmail.com, living at 6 Sims Drive (s)543230 and you do not 
feel the need to record a note for this applicant.

`NAME`: Thomas

`PHONE`: 91918153

`EMAIL`: thomas@gmail.com

`ADDRESS`: 6 Sims Drive (s)543230

The command you would like to enter into the command box would be:

`add n/Thomas p/91918153 e/thomas@gmail.com a/6 Sims Drive (s)543230`

Alternatively, executing these would do the same thing:

* `add n/Thomas e/thomas@gmail.com p/91918153 a/6 Sims Drive (s)543230`

  This is because the order of the flags does not matter.

* `add n/Thomas p/91918153 e/thomas@gmail.com n/Sally p/97833468 a/6 Sims Drive (s)543230`

  {{ site.data.constraints.lastValueOfDuplicates }} In this case, the name "Thomas" will be overridden by "Sally", 
and the phone "91918153" will be overridden by "97833468".

However, note that the following executions are invalid:

* `addn/Thomasp/91918153e/thomas@gmail.coma/6 Sims Drive (s)543230`

  There must be between the placeholders and flags.

* `add n/ThomaŚ p/91918153 e/thomas@gmail.com a/6 Sims Drivè (s)543230`

  The restrictions of placeholders are not followed.

* `add`

  There is insufficient information provided; you must minimally provide a name.

Find out more about restrictions in the sections [Flags](#flags), [Placeholders](#placeholders) and [Commands](#commands).

---

Let's try out another command -- the `list` command! `list` lets you see the list of the applicants.

```
⚠️Warning
The format for different commands are not always identical. For example, executing the `add` command and the `list` 
command will have different formats!
```

For example, after adding an applicant, you decided to see the list of applicant.

**Format:** `list`

The command you would like to enter into the [Command Input Box](#layout) would be:

`list`

You should now have a better understanding of how commands are formatted and used. All commands are consolidated 
in the [Command Summary](#command-summary).

Here is a checklist you can use before running a [[ command ]]:

* [ ] I know the restrictions of the command
* [ ] I know what [[ parameter:parameters ]] are supplied to the command
* [ ] I know the [[ flag:flags ]] for each parameter to be supplied
* [ ] I know the restrictions of each parameter
* [ ] I know the effects of not specifying each optional flag.

## Commands

This section shares with you on how to use each [[ command ]] in detail.

Before continuing, ensure you have read the section on [Flags](#flags) and [Placeholders](#placeholders).

What you should expect to find:

* A description of the command
* The format of the command
* The expected behaviour of the command
* A few valid and invalid examples of the command
* Important points to note


```
🚨Note
* For each command, "Format" indicates the syntax of the command.
* Square brackets indicates an optional [[ parameter ]].
* In most commands, if the same parameter is repeated and only one is required, we take the last value provided.
```

### Applicant Commands

#### Create a new applicant: `add`

**Format**: `add n/NAME p/PHONE e/EMAIL a/ADDRESS [note/NOTE]`

> Creates a new applicant with the provided information

**Info**
* All fields apart from `NOTE` are compulsory.
* `PHONE` does not require you to include the country code. Only include the numbers.
* THe value of `NOTE` will be `-` if it is not provided.



🚨Note
* If two or more values of the same parameter are provided, only the last value for that parameter will be taken.


**Example:**

**Assumption:**
 
HMHero does not already contain an applicant with the name "Thomas".

**Command Input Box:**

`add n/Thomas p/91918153 e/thomas@gmail.com a/6 Sims Drive (s)543230`

**Command Output Box:**

`New person added: Thomas; Phone: 91918153; Email: thomas@gmail.com; Address: 6 Sims Drive (s)543230; Status: APPLIED`

---


#### Search for an applicant: `find`

**Format**: `find n/[KEY] p/[KEY] ...`

> Finds all applicants in HMHero using name, phone or both

**Info**
* The notation `[KEY]...` means that we take in name or phone or both. 
In this case, at least one `KEY` is required.
* The `KEY` are case-insensitive. (e.g. "apples" will match "Apples").
* The result will be applicants where each of the `KEY` are present in the `NAME` or `PHONE`.
(e.g. "Thomas" will match "Thomas Lee", "Thomas", "Thomas Tan" and "Thomas Lim", 
"91918153" will only match "91918153")


Tip
* You can use the [List Command](#list-all-applicants--list) in the next section to display all applicants again!


🚨Note
* The `find` command only finds `Applicants` which has a name, phone or both that fully matches the specified search 
of part of the name, phone or both!
* This means that if the `NAME` `Thomas Lee` and `Thomas Tan` is in HMHero, 
executing `Thomas` will find these two `Applicants`.
* This also means that if the `PHONE` `91918153` and `9191` is in HMHero, executing `91918153` will only find
  `PHONE` `91918153`.
* If you try to find an `NAME` `Thomas` by executing the command `Thomas Lee`, it will still work!
* However, if you try to find an `PHONE` `91918153` by executing the command `919181532`, it will not work!
* If you try to find `Applicants` using both `NAME` and `PHONE`, it will work the same as finding individually!

**Example:**

**Assumption:**

HMHero contains the following applicants:

1. Thomas Tan, 91918153
1. Thomas, 98765432
1. Marry, 98765432
1. Thoma, 98231234

**Command Input Box:**

`find n/Thomas p/98765432`

**Command Output Box:**

`2 persons listed`

1. `Thomas, REJECTED, 98765432, thomas@gmail.com, 6 Sims Drive (s)543230`
2. `Marry, APPLIED, 98765432, marry@gmail.com, 5 Sims Drive (s)542333`

---


#### List all applicants: `list`

**Format**: `list`

> List all applicants in HMHero

**Info**
* This command is useful to view all applicants again after using the [Find Command](#search-for-an-applicant--find).


**Example:**

**Assumption:**

HMHero contains the following applicants, each with their own attributes:

1. Thomas Tan, 91918153
1. Thomas, 98765432
1. Marry, 98765432
1. Thoma, 98231234

**Command Input Box:**

`list`

**Command Output Box:**

- `Listed all applicants`
- `Total Applicants: 4`
- `Applied: 1`
- `Shortlisted: 1`
- `Accepted: 1`
- `Rejected: 1`

1. `Thomas, REJECTED, 98765432, thomas@gmail.com, 6 Sims Drive (s)543230`
2. `Marry, APPLIED, 98765432, marry@gmail.com, 5 Sims Drive (s)542333`
3. `Thomas Tan, ACCEPTED, 91918153, thomastan@gmail.com, 4 Sims Drive (s)543231`
4. `Thoma, SHORTLISTED, 98231234, thoma@gmail.com, 7 Sims Drive (s)543521`

---


#### Delete an applicant: `delete`

**Format**: `delete n/NAME p/PHONE`

> Deletes an applicant in HMHero using name and phone

**Info**
* All fields are compulsory.


🚨Note
* The `delete` command only deletes `Applicant` which has a name and phone that fully matches the specified search.


**Example:**

**Assumption:**

HMHero contains the following applicants, each with their own attributes:

1. Thomas Tan, 91918153
1. Thomas, 91918153
1. Marry, 98765432
1. Thoma, 98231234

**Command Input Box:**

`delete n/Thomas p/91918153`

**Command Output Box:**

`Deleted Person: Thomas; Phone: 91918153; Email: thomas@gmail.com; Address: 6 Sims Drive (s)543230; Status: REJECTED`

---


#### Advance an applicant: `advance`

**Format**: `advance n/NAME p/PHONE d/[INTERVIEW DATETIME]

> Advances an applicant in HMHero using name, phone and interview datetime

**Info**
* All fields except [INTERVIEW DATETIME] are compulsory.
* [INTERVIEW DATETIME] is compulsory when the initial `status` is `APPLIED`.


🚨Note
* The `advance` command only advances `Applicant` which has a name and phone that fully matches the specified search.
* The `INTERVIEW DATETIME` is required to advance `Applicant` from `status` `APPLIED` to `status` `SHORTLISTED`.
* However, `INTERVIEW DATETIME` is not required to advance `Applicant` from `status` `SHORTLISTED` 
to `status` `ACCEPTED`.


**Example:**

**Assumption:**

HMHero contains the following applicants, each with their own attributes:

1. Thomas Tan, 91918153, SHORTLISTED
1. Thomas, 91818153, APPLIED

**Command Input Box:**

1. `advance n/Thomas p/91918153 d/20-03-2023 12:12`

1. `advance n/Thomas Tan p/91818153`

**Command Output Box:**

1. `Advanced Applicant: Thomas`

`1. Thomas, SHORTLISTED 20-03-2023 12:12, 91918153, thomas@gmail.com, 6 Sims Drive (s)543230`

2. `Advanced Applicant: Thomas Tan`

`1. Thomas, ACCEPTED, 91918153, thomas@gmail.com, 6 Sims Drive (s)543230`

---


#### Reject an applicant: `reject`

**Format**: `reject n/NAME p/PHONE 

> Rejects an applicant in HMHero using name and phone

**Info**
* All fields are compulsory.


🚨Note
* The `reject` command only rejects `Applicant` which has a name and phone that fully matches the specified search.
* The `NAME` and `PHONE `is required to reject `Applicant` from `status` `APPLIED`, `SHORTLISTED` and `ACCEPTED`
to `status` `REJECTED`.


**Example:**

**Assumption:**

HMHero contains the following applicants, each with their own attributes:

1. Thomas Tan, 91918153, SHORTLISTED
1. Thomas, 91818153, APPLIED

**Command Input Box:**

`reject n/Thomas p/91918153 d/20-03-2023 12:12`

**Command Output Box:**

`Rejected Applicant: Thomas`

`1. Thomas, REJECTED 20-03-2023 12:12, 91918153, thomas@gmail.com, 6 Sims Drive (s)543230`

---

#### List all interview dates of applicants: `interview`

**Format**: `interview`

> List all interview dates of shortlisted applicants in HMHero in chronological order

**Info**
* This command is useful to view all the applicants' interview dates again after 
using the [Advance Command](#advance-an-applicant--advance).


**Example:**

**Assumption:**

HMHero contains the following applicants, each with their own attributes:

1. Thomas Tan, SHORTLISTED 19-03-2023 12:12, 91918153, 
1. Thomas, SHORTLISTED 18-03-2023 12:12, 98765432
1. Marry, SHORTLISTED 21-03-2023 12:12, 98765432

**Command Input Box:**

`interview`

**Command Output Box:**

- `Listed all shortlisted applicants`

1. `Thomas, SHORTLISTED 18-03-2023 12:12, 98765432, thomas@gmail.com, 6 Sims Drive (s)543230`
2. `Thomas Tan, SHORTLISTED 19-03-2023 12:12, 91918153, thomastan@gmail.com, 4 Sims Drive (s)543231`
3. `Marry, SHORTLISTED 21-03-2023 12:12, 98765432, marry@gmail.com, 5 Sims Drive (s)542333`

---







edit
### Statistics Command

{% include_relative _ug/commands/StatisticsCommands.md %}

### General Commands

{% include_relative _ug/commands/GeneralCommands.md %}


The first word of every command allows HMHero to distinguish different commands.
## Features

| Note                                                                                                                                                                                                                               |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Words in UPPER_CASE are the parameters to be supplied by the user.<br>e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.                                                                           |
| Items in square brackets are optional.<br>e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.                                                                                                           |
| Items with `…​` after them can be used multiple times including zero times.<br>e.g. `[t/TAG]…​` can be used as ` `, `t/friend`, `t/friend t/family` etc.                                                                           |
| Parameters can be in any order.<br>e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.                                                                                              |
| If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken. |
| Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>e.g. if the command specifies `help 123`, it will be interpreted as `help`.                     |

---
// Additional features: can list out the tag(skillset) of the applicants.
### **Command**

### 1. Viewing help: `help`

- Format: `help`

### 2. Adding a person: `add`

- Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [note/NOTES]`

- Examples: `add n/Jack Dill p/91234567 e/jackdill@example.com a/John Street, block 123 #01-01`

### 3. List applicant: `list`

- Format: `list`

### 4. Delete applicants: `delete`

- Format: `delete n/NAME p/PHONE_NUMBER`

- Examples:
    - `delete n/Jack Dill p/PHONE_NUMBER`

### 5. Advancing an applicant's status: `advance`

- Applied -> Shortlisted
- Shortlisted -> Accepted

Shortlists a candidate

- Format: `advance n/NAME p/PHONE_NUMBER`

- Examples:
    - `advance n/Jack Dill p/91234567`

### 6. Rejecting an applicant: `reject`

- Shortlisted -> Rejected
- Applied -> Rejected

Rejects a candidate or interviewee

- Format: `reject n/NAME p/PHONE_NUMBER`

- Examples:
    - `reject n/Jack Dill p/91234567`

### 7. Find an applicant: `find`

Find applicants using keywords (name OR phone number OR both)

- Format:
    - Format 1: `find NAME or PHONE NUMBER` (only need to provide either one)
    - Format 2: `find n/NAME p/PHONE NUMBER` (must provide both)
- Example:
    - Example 1: `find Jack Dill`, `find 91234567`
    - Example 2: `find n/Jack Dill p/91234567`

---