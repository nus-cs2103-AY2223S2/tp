---
layout: page
title: User Guide
---

Group: AY2223S2-CS2103T-T15-4

# User Guide

## Introduction

CLIpboard is a desktop app that helps educators (like you!), especially those that tutor multiple classes, by managing their students’ particulars<strong> in an organised manner.</strong>

CLIpboard is optimized **for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It can get your student management tasks done faster than traditional GUI apps. CLIpboard is optimised for keyboard users, so if you can type fast, CLIpboard can work even faster.

<img src="https://lh4.googleusercontent.com/nJEHEfBEaTqtMlaihfUQVGUEwWmxZkjWSyY9CkknQ9WE6q9VnwlT2YbG4CP6rguZjN0b0ZslxZPH6TivrbM6S6S0fDq5WEuxOciYJILifALzS5mRS8RGjOlQLcjBp-DqYfVGSmw8_cU4WfKFlA-J1Hg" width="634" height="346" />
<br><br>

## <br>Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest clipboard.jar from [here](https://github.com/se-edu/addressbook-level3/releases).
3. Copy the file to the folder you want to use as the *home folder* for your CLIpboard.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar clipboard.jar command to run the application.&nbsp;

&emsp;&emsp;e.g. your clipboard.jar is stored in user/app/task/,&nbsp; you run cd user/app/task/
<br>&emsp;&emsp;A GUI similar to the above should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. typing <strong>help </strong>and pressing Enter will open the help window.
    <br>Some example commands you can try:
    1. list : Lists all students.
    2. add n/John Doe p/12345678 sid/a0123456z [e/e1234567@u.nus.edu](mailto:e/e1234567@u.nus.edu) a/blk 123 nus: Adds a student named John Doe with the particulars to the CLIpboard.
    3. delete 3 : Deletes the 3rd student shown in the current list.
    4. exit : Exits the app.
6. Refer to the [<ins>Commands</ins>](https://docs.google.com/document/d/129glYXctEtL77of9dMmzea-TjVfZh727fVPrv_e9AyI/edit#bookmark=id.1r9lnvft19co) list below for a detailed description&nbsp; of of each command.

## Commands

List of commands:

- Adding
- Deleting
- Listing students
- Show
- Exit

### Adding a student: add

&emsp;&emsp;Adds a student to the address book.

&emsp;&emsp;Usage:

```
add n/<name> p/<phone_number> sid/<student_number> e/<email> a/<address>
```

### Deleting a student: delete&nbsp;

&emsp;&emsp;Deletes a student from the address book.

&emsp;&emsp;Usage:

```
delete <list_index>
```

### Listing all students: list&nbsp;

&emsp;&emsp;Lists all students in the address book.

&emsp;&emsp;Usage:

```
    list
```

### View a student's information: Show

&emsp;&emsp;View personal information for a particular student

&emsp;&emsp;Usage:&nbsp;

```
show <list_index>
```

### Exit: exit

&emsp;&emsp;Exits the program.

&emsp;&emsp;Usage:&nbsp;

```
  exit
```
