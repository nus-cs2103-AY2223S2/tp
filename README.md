[![CI Status](https://github.com/AY2223S2-CS2103-F10-3/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2223S2-CS2103-F10-3/tp/actions/workflows/gradle.yml)



# MODCheck
MODCheck is a contact management app that enables you to manage all your contacts easily that works on Windows, MacOS and Linux!
Our app is catered towards fast typist and many features are catered for students that need better management of their contacts.

## Main GUI
<p align="center">
<img src="https://raw.githubusercontent.com/AY2223S2-CS2103-F10-3/tp/master/docs/images/Ui.png" align="center" height=auto width="600">
</p>
<p align="center">
<em>Main UI</em>
</p>

## Key Features


### 🔍 Filter contacts by their tags quickly

MODCheck can quickly filter all your contacts based on the tag provided

### 💾 Instant saving when changes are made

MODCheck can save all your work immediately on the fly

### ✅ List all contacts quickly without lag

MODCheck can display all your contacts in one go!

## Usage

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
* :warning: Unrecognised fields such as `b/` or `c/` will not be picked up as fields, and will be treated as input.

</div>

## Guides

[User Guide](https://ay2223s2-cs2103-f10-3.github.io/tp/UserGuide.html)

[Developer Guide](https://ay2223s2-cs2103-f10-3.github.io/tp/DeveloperGuide.html)

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer. `load` the contents of the previous data file into your new ModCheck.

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                      |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL d/DESCRIPTION [t/TAG]…​ [m/MODULE_TAG]…​ ` <br> e.g., `add n/Benedict Tan d/Great Friend e/BenedictTan@gmail.com p/98070707 t/Friend m/CS2103 m/CS3230 `                                                                                           |
| **View**   | `view INDEX`<br> e.g., `view 2`                                                                                                                                                                                                                                                       |
| **Clear**  | `clear`                                                                                                                                                                                                                                                                               |
| **Delete** | `delete INDEX` or `delete INDEXES` or `delete NAME` <br> e.g., `delete 3` or `delete 1,2,3` or `delete James`                                                                                                                                                                         |
| **Edit**   | `edit {INDEX or NAME} [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` or `edit James e/jameslee@example.com`                                                                                                          |                                                                                                                                                                                                                       |
| **List**   | `list`                                                                                                                                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                                                                                                                                |
| **Filter** | `filter n/NAME` <br> `filter p/PHONE_NUMBER`<br> `filter e/EMAIL_ADDRESS` <br> `filter d/DESCRIPTION` <br> `filter t/TAG` <br> e.g. `filter n/Alex` <br> e.g. `filter p/91031282` <br> e.g. `filter e/royb@example.com` <br> e.g. `filter d/helpful` <br> e.g. `filter t/family` <br> |
| **Undo**   | `undo`                                                                                                                                                                                                                                                                                |
| **Redo**   | `redo`                                                                                                                                                                                                                                                                                |
| **Load**   | `load` OR `load <path>`                                                                                                                                                                                                                                                               |
| **Export** | `export INDEX`<br> e.g., `export 2`<br/>                                                                                                                                                                                                                                              |
| **Light**  | `light`                                                                                                                                                                                                                                                                               |
| **Dark**   | `dark`                                                                                                                                                                                                                                                                                |

---
