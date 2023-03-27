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

## HotKeys

HotKeys are keyboard short cuts supported by Mycelium to enable faster navigation and editing of commands with the keyboard. This allows for Mycelium to be fully keyboard driven. The following are the supported hotkeys:

### (F1) Help

This shortcut allows you to quickly access the help page.

### (CTRL+Q) Quit

This shortcut allows you to quickly quit Mycelium.

### (CTRL+W) Start of Line

This shortcut allows you to quickly navigate to the start of the line in the command box.

### (CTRL+E) End of Line

This shortcut allows you to quickly navigate to the end of the line in the command box.

### (CTRL+D) Clear line

This shortcut allows you to quickly clear the current line in the command box. The command also clears the command log found below the command box.

### (CTRL+L) Switch Tabs

This shortcut allows you to quickly switch between the projects and clients tabs. 

### (CTRL+J) Select next

This shortcut allows you to quickly select the next item on the currently selected list in the command box. 

If you are on the projects tab, then the next item in the projects list will be selected. If there was no project originally selected, then the first project in the list will be selected.

If you are on the clients tab, then the next item in the client list will be selected. If there was no client originally selected, then the first client in the list will be selected.

### (CTRL+K) Select previous

This shortcut allows you to quickly select the previous item on the currently selected list in the command box.

If you are on the projects tab, then the previous item in the projects list will be selected. If there was no project originally selected, then the last project in the list will be selected.

If you are on the clients tab, then the previous item in the client list will be selected. If there was no client originally selected, then the last client in the list will be selected.

### (CTRL+F) Search

This shortcut allows you toggle the command box between **search mode** and **command mode** (default).

In **command mode**, the input in the command box is used to execute the command. 

In **search mode**, the command box is highlighted light blue and the input is used to *interactively* search for the closest matching project or client by name. *Interactively* would mean that the search results are updated in the projects and client list as you type.

If you have a project or client selected, pressing ***Enter*** in **search mode** switches back to **command mode** and appends the name or email of the selected project or client respectively to the command box. This is useful if you want to quickly reference a project or client in your command.


## Command summary

Action | Format
--------|------------------
**Create new client** | c -cn client_name -e email -y year_of_birth -src source -mn mobile_number <br> e.g., `c -cn 'Alice Baker' -e alice_baker@bakers.com -y 2000`
**Delete an existing client** | dc -e email <br> e.g., `dc -e alice_baker@bakers.com`
**Create a project** | p -pn project_name -e client_email -s status -src source -d description -ad accepted_date -dd deadline_date <br> e.g., `p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075`
**Delete a project** | dp -pn project_name <br> `e.g., dp -pn Mycelium`
