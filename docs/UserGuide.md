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

# Fuzzy Search

Fuzzy search allows us to find projects or clients which match *closely* to
some query, rather than *exactly*. This is useful if you are, for instance,
trying to find a project whose name you only remember partial bits of, or how
it sounds in your head.

Mycelium supports fuzzy finding for both projects and clients. For projects,
the query is matched against the project's name, while for clients, the query
is matched against the client's email. Furthermore, the search is
*interactive*. This means that the UI automatically updates as you type your
query into the command box.

Please take note of these details for fuzzy search:

* Closer matches will be placed at the top;
* Projects or clients which do not match at all will not be shown;
* Fuzzy search is *not* case-sensitive.

Note that by "do not match at all", we refer to the case where literally not a
single character matches. As long as at least one character matches, the
project or client will be listed (although possibly ranked very low).

The following two sections will walk through performing fuzzy search on
projects and clients.

## Fuzzy searching projects

Let us assume we have the following projects in Mycelium:

**TODO: insert screenshot with some sample projects**

<div markdown="span" class="alert alert-info">
:information_source: Recall that Mycelium supports fuzzy search for projects by
their *names* only!
</div>

First, press `Ctrl+F` to toggle to search mode (if you are not already in
search mode). You should see the command box turn light blue; now we can begin
searching. Suppose we wanted to search for *Clash of Clans*. With the power of
fuzzy matching, just typing *coc* is enough, as shown below.

**TODO: add screenshot**

If we queried a term which matches nothing at all, then no results will be
listed.

**TODO: add screenshot**

Once we are done, pressing `Ctrl+F` again switches us back to command mode.

## Fuzzy searching clients

This works exactly the same as as fuzzy searching projects, described above.
The only difference to note is that the query is matched against the clients'
emails, and not their names.

## Gotchas

In general, fuzzy search in Mycelium should feel familiar to most developers,
since it is similar to, for example, finding files in IDEs, or the well known
[fzf](https://github.com/junegunn/fzf) tool. However, here are a few things you
might wish to note:

* The fuzzy query is performed on clients and projects simultaneously. That is,
  when you query for something, say, "coc", this query is applied to both
  clients and projects, regardless of which tab is currently being displayed.
* **TODO: add more gotchas**

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Create new client** | c -cn client_name -e email -y year_of_birth -src source -mn mobile_number <br> e.g., `c -cn 'Alice Baker' -e alice_baker@bakers.com -y 2000`
**Delete an existing client** | dc -e email <br> e.g., `dc -e alice_baker@bakers.com`
**Create a project** | p -pn project_name -e client_email -s status -src source -d description -ad accepted_date -dd deadline_date <br> e.g., `p -pn 'Mycelium Desktop' -e spiderman@gmail.com -src fiverr.com -dd 30/02/2075`
**Delete a project** | dp -pn project_name <br> `e.g., dp -pn Mycelium`
