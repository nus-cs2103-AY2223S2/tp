---
layout: page
title: dsja612's Project Portfolio Page
---

### Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on the entire stack, both frontend and backend. I was mainly in charge of refactoring and working on the new UI.


## Contributions

* **New Feature**: Added the ability to store a `JobDescription` field. [[PR#56]]
  * What it does: TechTrack users can now store job descriptions for each role.
  * Justification: The addition of the JobDescription field provides TechTrack users with an essential field for managing and tracking jobs.
    A job description is essential as it explains the tasks, duties, function and responsibilities of a role.
  * Highlights: TechTrack users can now add their own job descriptions. As job descriptions have many forms, we decided
    not to place restrictions on this field, other than it being non-empty. Also added more tests for this field.


* **Refactoring**: Refactored the `Address` field to the `Company` field. [[PR#82]]
  * What it does: TechTrack users can now store the `company` for each role.
  * Justification: After discussion, we felt that the `Address` field would not be useful for TechTrack. We also plan to
  include a `Company` field for all roles. Hence, it would be easier to refactor `Address` to represent a company instead.
  * Highlights: Refactored AB3 code and tests. Updated sample data to reflect examples of companies.


* **New Feature & Refactoring**: Add a new `View` command to display more details of a role in a nicer format. [[PR#114]], [[PR#127]], [[PR#147]]
  * What it does: Only important details are displayed in the `RoleCard`. The user can view more length/less significant details
  using the `view {index}` command, which replaces the `ResultDisplay` of the UI with a `RoleDisplay`.
  * Justification: Displaying all 10 fields of a `Role` would not look aesthetically pleasing, especially with longer fields
  like `JobDescription`. We decided to refactor the UI to support multiple displays. As of now, we only need 2 types
  of displays from output of commands: one for `String`, another for `Role`. The type of display can be easily made extendible in the future, if needed.
  * Highlights:
  
    | Old UI                                                              | New UI                                    |  
    |------------------------------------------------------------------|----------------------------------------------------------------|
    | <img src="https://nus-cs2103-ay2223s2.github.io/tp/images/Ui.png" width=800> | <img src="../images/UICommandImages/ViewCommand0.png" width=800> |
     

## Enhancements

* Enhanced help window to show list of commands for all messages [[PR#127]]
* Add message on startup to let the user know if TechTrack fails to load a file [[PR#226]]
* Improved support of unnecessarily long fields for the UI [[PR#217]]
* Change legacy logging details [[PR#217]]

## Bug Fixes
* Fixed a bug where TechTrack crashes if a `Deadline` field in `TechTrack.json` is past the current date [[PR#99]]
* Fixed a bug where loading sample data on a fresh installation of TechTrack would cause the program to crash [[PR#72]]

* **Documentation** [Coming soon]:
  * User Guide:
    * Constructed outline of UG for team members to edit easily, and fixed bugs [[PR#121]], [[PR#227]]
  * Developer Guide (TO BE ADDED):
    * ...

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dsja612&breakdown=true)

