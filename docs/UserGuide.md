---
layout: page
title: User Guide
---

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Introduction**

MyLib is a desktop application originally built to serve as a single platform for organising and tracking all the online webnovels and comics that you may be reading. However, it is more than capable of doing so for any other reading material you might be interested in, such as blogs, articles, research papers and basically anything you can read!

With MyLib, you have the ability to easily organise all your reading materials in a **single platform**, in a **highly personalized** way via a custom set of tags or labels that you can define on your own.

MyLib is **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This means that most of MyLib's features are meant to be accessed through typed commands rather than mouse clicks.

If you are a fast typer who is seeking a one-stop platform to organise and track your readings (online or physical), then MyLib is what you need! Even if you are not a fast typer, the commands are simple enough such that typing them out will not be much slower than using a GUI, if at all!

--------------------------------------------------------------------------------------------------------------------
## **About User Guide**
### Objectives of the User Guide

This User Guide provides an easy to understand and comprehensive documentation, so you can easily start using MyLib. It covers how to download the application, launch the application and the various features in MyLib that will make it easy for you to organise and track all your reading materials.
<div style="page-break-after: always;"></div>

### How to use the User Guide

The user guide contains certain visuals to aid in conveying information more effectively

:information_source: **Info** - Useful supplementary information

:bulb: **Tip** - Suggestions on how to enhance your experience

:exclamation: **Warning**  - Warning  of a potentially dangerous action that you should be aware of



### Getting Started
Head on over to the [Quick start](#quick-start) section to get started with MyLib!

If you are an experienced user, you can refer to [Command Summary](#command-summary) for a quick overview of MyLib's commands.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `myLib.jar` from [here](https://github.com/AY2223S2-CS2103T-T13-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Library.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar myLib.jar` command to run the application.

5. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

    <img width="560" height="400"  src="images/Ui.png">

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all Bookmarks.

   * `add n/The Odyssey a/Homer p/1 1 1 g/Fantasy r/4 u/http://classics.mit.edu/Homer/odyssey.html t/Literature` : Adds a bookmark for the book `The Odessey` to the Library.

   * `delete 3` : Deletes the 3rd Bookmark shown in the current list.

   * `clear` : Deletes all Bookmarks.

   * `goto 1` : Opens the url of 1st Bookmark shown in current list.

   * `exit` : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Graphical User Interface**<br>

   <img width="680" src="images/annotated-UI.png">

### Purposes of each GUI component

|     Component      |                          Purpose                          |
|:------------------:|:---------------------------------------------------------:|
|    Command Box     |                 To accept user commands.                  |
|     Result Box     | To display the result of the commands that user executed. |
| BookmarkList Panel |         To display the current list of bookmarks          |
|     View Panel     |      To display the details of a specified bookmark       |

### Bookmark UI details

<img width="680" src="images/bookmarkCardUI.png">

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/TITLE`, `TITLE` or name of `TITLE` is a parameter which can be used as `add n/The Odessey`.

* Items in square brackets are optional.<br>
  e.g `n/TITLE [t/TAG]` can be used as `n/The Odessey t/School` or as `n/The Odessey`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/School`, `t/School t/Literature` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/TITLE a/AUTHOR`, `a/AUTHOR n/TITLE` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `a/John Butcher a/Jim Butcher`, only `a/Jim Butcher` will be taken.

* Extra parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `genre`, `tags` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* List of prefixes used in commands :
  * `n/` - TITLE
  * `a/` - AUTHOR
  * `p/` - PROGRESS
  * `g/` - GENRE
  * `u/` - URL
  * `t/` - TAGS

</div>
<div style="page-break-after: always;"></div>

### Adding a tag: `addtag`

Adds a tag to the list of tags.

Format: `addtag [t/TAG]…`

Examples:
* `addtag t/Novel t/MaleProtagonist`
* `addtag t/FemaleProtagonist`

### Deleting a tag: `dtag`

Deletes a tag from the tag list.

Format: `dtag TAGNAME`

Example:
* `dtag MaleProtagonist`

### Listing all tags: tags
Lists all tags in the tag list.

Format: `tags`

### Listing available Genres: `genre`
Shows list of all valid Genres.

Format: `genre`
<div style="page-break-after: always;"></div>

### Adding a bookmark: `add`

Adds a bookmark to the library.

Format: `add n/TITLE g/GENRE [a/AUTHOR] [p/PROGRESS] [r/RATING] [u/URL] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A bookmark can have any number of tags (including 0)
</div>

Examples:
* `add n/Hobbit a/J. R. R. Tolkien p/1 ~ 256 r/4 g/Fantasy`
* `add n/The Odyssey a/Homer p/1 1 23 g/Action r/5 t/Literature`

### Deleting a bookmark : `delete`

Deletes the specified bookmark from the library.

Format: `delete INDEX`

* Deletes the bookmark at the specified `INDEX`.
* The index refers to the index number shown in the displayed bookmark list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd bookmark in the library.
* `find n/Chainsaw Man` followed by `delete 1` deletes the 1st bookmark in the results of the `find` command.

### Editing a bookmark : `edit`

Edits an existing bookmark in the library.

Format: `edit INDEX [n/TITLE] [a/AUTHOR] [p/PROGRESS] [g/GENRE] [r/RATING] [u/URL] [t/TAG]…​`

* Edits the bookmark at the specified `INDEX`. The index refers to the index number shown in the displayed bookmark list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* The genre and tags provided must be in the list of existing genre and tags respectively.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the bookmark will be removed i.e adding of tags is not cumulative.
* You can remove all the bookmark’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 n/Hobbit a/J. R. R. Tolkien` Edits the title and author of the 1st bookmark to be `Hobbit` and `J. R. R. Tolkien` respectively.
*  `edit 2 n/The Odyssey t/` Edits the name of the 2nd bookmark to be `The Odyssey` and clears all existing tags.

### Sorting bookmarks : `sort`

Sorts the list of bookmarks by ratings in either ascending or descending order.

Format : sort [ORDER]
* ORDER can only be either `asc` or `desc`
* ORDER is case-sensitive. e.g. `sort ASC` does not work.

Examples:
* sort asc
* sort desc
<div style="page-break-after: always;"></div>

### Locating bookmarks by specific fields: `find`

Find bookmarks whose specified fields contain the given keywords.

`find` helps you to find bookmarks whose specified fields contain the given keywords. You can use this when you
want to filter out certain bookmarks from your large list of bookmarks. `find` allows you to search for bookmarks
using the title, author, genre and/or tags of a bookmark.

:bulb: **Tip** - You can use the `list` command to get back the bookmarks that you have filtered out after `find`.

Format: `find [n/TITLE] [a/AUTHOR] [g/GENRE] [t/TAG]…​`

* At least one of the optional fields must be provided.
* The search for name and author is case-insensitive. e.g. `rankers` will match `Rankers`
* The search for genre and tag is case-sensitive. e.g. `fantasy` will not match `Fantasy`
* The genre and tags provided must be in the list of existing genre and tags respectively.
* The order of the keywords matter. e.g. `Guide Rankers` will not match `Rankers Guide`
* Only the fields of the specified prefixes are searched.
* Only full words will be matched e.g. `Ranker` will not match `Ranker's`.
* The search for tags will return any bookmark that has a tag that matches the given tag.
<div style="page-break-after: always;"></div>

Examples:
* `find n/ranker's g/Fantasy` returns `Ranker's Guide to an Ordinary Life` that has the genre `Fantasy`
*  `find a/Yoo Heonhwa` returns every bookmark whose author of the book is Yoo Heonhwa<br>
* `find n/Chainsaw Man` returns `chainsaw man` and `Chainsaw Man`
  ![find_reply](images/find-chainsaw%20man.png)

### Listing all bookmarks/ Resetting filters : `list`

Shows a list of all bookmarks in the library.


:bulb: **Tip** - Use this command to get back the original list of bookmarks after using `find` command to filter out bookmarks.

Format: `list`


### Viewing a bookmark's details : `view`

Displays details of Bookmark in the right side panel.

Format: `view INDEX`

* gets bookmark at the specified `INDEX` and displays its details.
* The index refers to the index number shown in the displayed bookmark list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` displays the 2nd bookmark in the library.
* `find n/Chainsaw Man` followed by `goto 1` displays 1st bookmark in the results of the `find` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Our Application also supports this feature in gui format, simply click onto the bookmark you want and watch the magic happen.
</div>

### Going to a url : `goto`

Opens up specified bookmark's url in default browser

Format: `goto INDEX`

* Opens the url of bookmark at the specified `INDEX`.
* The index refers to the index number shown in the displayed bookmark list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `goto 2` opens up the url of  2nd bookmark in the library.
* `find n/Chainsaw Man` followed by `goto 1` opens url of the 1st bookmark in the results of the `find` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Our Application also supports this feature in gui format, clicking on the url in the right panel will open it on your default browser .
</div>
<div style="page-break-after: always;"></div>

### Clearing all entries : `clear`

Clears all bookmark entries from the MyLib. You will still keep your list of genres and tags after using the `clear` command.

Format: `clear`

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MyLib data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
<div style="page-break-after: always;"></div>

### Editing the data file

MyLib data are saved as a JSON file `[JAR file location]/data/library.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MyLib will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MyLib home folder.

**Q**: Do I need an internet connection to run MyLib?<br>
**A**: No, MyLib can boot up and run all functionalities without an internet connection.

**Q**: Can I use MyLib on my mobile device?<br>
**A**: Unfortunately, MyLib is only designed to run on your desktop/laptop such that you can use the command line interface.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples
--------|------------------
**Add a bookmark** | `add n/TITLE g/GENRE [a/AUTHOR] [p/PROGRESS] [r/RATING] [u/URL] [t/TAG]…​` <br> e.g., `add n/The Odyssey 2 a/Homer p/1 ~ 32 g/Others t/Literature`
**Clear bookmarks** | `clear`
**Delete a bookmark** | `delete INDEX`<br> e.g., `delete 3`
**Edit a bookmark** | `edit INDEX [n/TITLE] [a/AUTHOR] [p/PROGRESS] [g/GENRE] [r/RATING] [u/URL] [t/TAG]…​`<br> e.g.,`edit 1 n/Hobbit a/J. R. R. Tolkien`
**Find bookmarks** | `find [n/TITLE] [a/AUTHOR] [g/GENRE] [t/TAG]…​`<br> e.g., `find n/ Chainsaw Man`
**GoTo url of bookmark** | `goto INDEX`<br> e.g., `goto 3`
**List all bookmarks** | `list`
**Sort bookmark by ratings** | `sort [ORDER]` <br> e.g., `sort asc`, `sort desc`
**Help** | `help`
**List all tags** | `tags`
**Add a tag** | `addtag [t/TAG]…` <br> e.g., `addtag t/Novel`
**Delete a tag** | `dtag TAGNAME` <br> e.g., `dtag MaleProtagonist`
**List all genres** | `genre`

