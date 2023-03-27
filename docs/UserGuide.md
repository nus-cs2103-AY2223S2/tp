---
layout: page
title: User Guide
---

Mycelium is a desktop application aimed at helping **freelance web developers manage clients and projects** from multiple online sources. All interactions with Mycelium are through text commands, allowing for efficient manipulation of data while benefiting from the ease of viewing offered by the Graphical User Interface.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Main View - Projects and Clients

Mycelium has two main tabs. One tab lists all existing projects, and the other lists existing clients.

### Projects Tab

The Projects tab lists all the projects you've created. Each project block contains the project’s

- Name
- Status, which would be either **not started, in progress, or done**
- Client
- Source, e.g. Fiverr
- Description
- Accepted date
- Deadline

### Clients Tab

The Clients tab lists all the clients you've created. Each client block contains the client’s

- Name or username
- Year of birth
- Email
- Phone number
- List of associated platforms


--------------------------------------------------------------------------------------------------------------------
## Command Layout

Commands in Mycelium take the general form of `command_name [arguments]`. Arguments may be compulsory or optional. For optional arguments, Mycelium uses sensible defaults in their place.

All arguments take the structure of `-arg arg_data`, and multiple arguments can be specified consecutively. Note that if `arg_data` consists of multiple white-space separated tokens, then it must be surrounded by single quotation marks.

As an example, the command below creates a new project with the name *Mycelium Desktop*, for client *Spiderman*, and sourced from *fiverr.com*.

```bash
p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com
```

### A note on dates

For arguments which expect a date, Mycelium only accepts input of the format dd/MM/yyyy.
For example, "14/03/2023" is okay, but "14/3/2023", or "14-03-2023" are not okay.

--------------------------------------------------------------------------------------------------------------------

## Managing Clients

### Creating a client contact: `c`

Creates a new client contact.

**Compulsory Arguments**

- **`-cn client_name`**
    - The client’s name.
- **`-e email`**
    - The client’s email. Must be unique in Mycelium.

**Optional Arguments**

- **`-y year_of_birth`**
    - The client’s year of birth.
    - **Default**: *null*
- **`-src source`**
    - The client’s digital service platform, e.g. Fiverr.
    - **Default:** *null*
- **`-mn mobile_number`**
    - The client’s mobile number.
    - **Default:** *null*

**Examples**

The following command creates a new client with name *Alice Baker*, whose email is *alice_baker@bakers.com*, born in the year *2000*.

```bash
c -cn 'Alice Baker' -e alice_baker@bakers.com -y 2000
```

### Deleting a client contact: `dc`

Deletes an *existing* client contact.

**Compulsory Arguments**

- **`-e email`**
    - Email of the client to delete.

**Examples**

The command `dc -e alice_baker@bakers.com`  deletes the contact with the corresponding email.

--------------------------------------------------------------------------------------------------------------------

## Managing Projects

### Creating a project: `p`

Creates a new project.

**Compulsory Arguments**

* `-pn project_name`
    * The project’s name, which must be *unique*. Case sensitive.
* `-e client_email`
    * The email of the client who submitted this project. Note that this client
      does not need to exist in Mycelium.

**Optional Arguments**

- `-s status`
    - Here `status` should be set, verbatim, to one of `not_started`,
      `in_progress`, or `done`.
    - **Default:** `not_started`
- `-src source`
    - The platform the project is sourced from, such as Fiverr. This can be any
      arbitrary non-empty string.
    - **Default:** *null*
- `-d description`
    - A short description of the project.
    - **Default:** *null*
- `-ad accepted_date`
    - The date that the project was accepted.
    - **Default**: the current date
- `-dd deadline_date`
    - The deadline of the project.
    - **Default:** *null*

**Examples**

The following command creates a new project whose name is *Mycelium Desktop*,
submitted from the client *spiderman@gmail.com* from *fiverr.com*, with a
deadline on *30 February, 2075*.

```
p -pn Mycelium Desktop -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075
```

If the project is added successfully, you should see the following message in the output box:

```
New project added: Mycelium Desktop from client spiderman@gmail.com
```

**Notes**

* The client specified by the `-e` argument does not need to exist in Mycelium.
  You can add them later if you wish.
* If you attempt to create a project with a name which already exists in
  Mycelium, an error will be displayed to block the operation.

### Deleting a project: `dp`

Deletes an existing project.

**Compulsory Arguments**

- `-pn project_name`
    - Name of the project to delete. Case sensitive.

**Examples**

The following command deletes a project with name *Mycelium Desktop*.

```
dp -pn Mycelium Desktop
```

If successful, you should see the following message in the output box. (In this
example, the project's client is *spiderman@gmail.com*).

```
Deleted Project: Mycelium Desktop from client spiderman@gmail.com
```

**Notes**

* If you attempt to delete a project which does not exist in Mycelium, an error
  will be displayed and no changes will be made to your data.
* Deletion is irreversible!

### Updating a project: `up`

Performs partial updates an existing project.

**Compulsory Arguments**

* `-pn project_name`
    * Name of the project to update. Case sensitive.

**Optional Arguments**

* `-pn2 new_project_name`
    * A new project name.
    * **Default:** *null*
* `-e client_email`
    * A new client email.
    * **Default:** *null*
* `-s status`
    * A new project status. Should be set, verbatim, to one of `not_started`,
      `in_progress`, or `done`.
    * **Default:** *null*
* `-src source`
    * A new source for the project. Can be any arbitrary non-empty string.
    * **Default:** *null*
* `-d description`
    * A new description for the project.
    * **Default:** *null*
* `-ad accepted_date`
    * A new accepted-on date for the project.
    * **Default:** *null*
* `-dd deadline_date`
    * A new deadline for the project.
    * **Default:** *null*

Each of these arguments, if specified, will be used to (paritially) update the
target project.

<div markdown="span" class="alert alert-info">
:information_source: Notice that the arguments here are similar to that of
creating a project. You may refer to the section above on [creating a
project](#creating-a-project:-p) for further details on what each argument
means.
</div>

**Examples**

Suppose we have a project named *Mycelium Desktop*, and wish to update

1. its name to *Mycelium Mobile*; and
1. its status to `in_progress`.

The following command will do the trick.

```
up -pn Mycelium Desktop -pn2 Mycelium Mobile -s in_progress
```

**TODO: add output**

**Notes**

* It is not possible to "unset" an optional field. For example, Mycelium allows
  projects to have deadlines. Suppose that you have project, *X*, which
  currently has a deadline. Then it is not possible to use the `up` command to
  unset the deadline.
* The target project should already exist in Mycelium. Otherwise, an error will
  be displayed and no changes are made to the data.
* If the project's name is updated, then it must be a unique name. Suppose we
  currently have the projects *foo* and *bar*. An attempt to update *foo*'s
  name to *bar* will result in an error, and the operation will be blocked.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Create new client** | c -cn client_name -e email -y year_of_birth -src source -mn mobile_number <br> e.g., `c -cn 'Alice Baker' -e alice_baker@bakers.com -y 2000`
**Delete an existing client** | dc -e email <br> e.g., `dc -e alice_baker@bakers.com`
**Create a project** | p -pn project_name -e client_email -s status -src source -d description -ad accepted_date -dd deadline_date <br> e.g., `p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075`
**Delete a project** | dp -pn project_name <br> `e.g., dp -pn Mycelium`
