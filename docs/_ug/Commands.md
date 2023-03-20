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