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

- **`-pn project_name`**
    - The project’s name, which must be *unique.*
    - If another project with the same name already exists, then the command *overrides* the existing project with the new details.
- **`-e client_email`**
    - The email of the client who submitted this project. Note that this client must have already been created in Mycelium.

**Optional Arguments**

- **`-s status`**
    - The `status` should be set to either **`not_started` , `in_progress` , or `done` .**
    - **Default:** **`not_started`**
- **`-src source`**
    - The platform the project is sourced from, such as Fiverr. This can be any arbitrary string.
    - **Default:** *null*
- **`-d description`**
    - A short description of the project.
    - **Default:** *null*
- **`-ad accepted_date`**
    - The date that the project was accepted.
    - **Default**: the current date
- **`-dd deadline_date`**
    - The due date of the project.
    - **Default:** *null*

**Examples**

The following command creates a new project whose name is *Mycelium Desktop*, submitted from the client *Spiderman* from *fiverr.com*, with a deadline on *30 February, 2075*.

```bash
p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075
```

### Deleting a project: `dp`

Deletes an *existing* project.

**Compulsory Arguments**

- `-pn project_name`
    - Name of the project to delete.

**Examples**

Running `dp -pn Mycelium` would delete the project with name *Mycelium*.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Create new client** | c -cn client_name -e email -y year_of_birth -src source -mn mobile_number <br> e.g., `c -cn 'Alice Baker' -e alice_baker@bakers.com -y 2000`
**Delete an existing client** | dc -e email <br> e.g., `dc -e alice_baker@bakers.com`
**Create a project** | p -pn project_name -e client_email -s status -src source -d description -ad accepted_date -dd deadline_date <br> e.g., `p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075`
**Delete a project** | dp -pn project_name <br> `e.g., dp -pn Mycelium`
