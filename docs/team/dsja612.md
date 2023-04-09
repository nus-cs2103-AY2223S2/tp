---
layout: page
title: dsja612's Project Portfolio Page
---

* Table of Contents
{:toc}

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on the entire stack, both frontend and backend. I was mainly in charge of refactoring and working on the new UI.

## Contributions

* **New Feature**: Added the ability to store a `JobDescription` field.
  * What it does: TechTrack users can now store job descriptions for each role.
  * Justification: The addition of the JobDescription field provides TechTrack users with an essential field for managing and tracking jobs.
    A job description is essential as it explains the tasks, duties, function and responsibilities of a role.
  * Highlights: TechTrack users can now add their own job descriptions. As job descriptions have many forms, we decided
    not to place restrictions on this field, other than it being non-empty. Also added more tests for this field.


* **Refactoring**: Refactored the `Address` field to the `Company` field.
  * What it does: TechTrack users can now store the `company` for each role.
  * Justification: After discussion, we felt that the `Address` field would not be useful for TechTrack's use case. We also plan to
  include a `Company` field for all roles. Hence, it would be easier to refactor `Address` to represent `Company` instead.
  * Highlights: Refactored AB3 code and tests. Updated sample data to reflect examples of companies.


* **New Feature & Refactoring**: Add a new `View` command to display more details of a role in a better format.
  * What it does: Edited the `Role Card` to only display important details. The user can view more lengthy/less significant details
  using the `view {index}` command, which replaces the `ResultDisplay` of the UI with a `RoleDisplay`.
  * Justification: Displaying all 10 fields of a `Role` would not look aesthetically pleasing, especially with longer fields
  like `JobDescription`. We decided to refactor the UI to support multiple displays. As of now, we only need 2 types
  of displays from output of commands: one for `String`, another for `Role`. The type of display can be easily made extendable 
  in the future, if needed.
  
| Old UI                                                                       | New UI                                                           |  
|------------------------------------------------------------------------------|------------------------------------------------------------------|
| <img src="https://nus-cs2103-ay2223s2.github.io/tp/images/Ui.png" width=800> | <img src="../images/UICommandImages/ViewCommand0.png" width=800> |

* **Testing**: Refactored old variables in test classes to reflect the changes in TechTrack.

## Enhancements

* Enhanced help window to show list of commands for all messages
* Add message on startup to let the user know if TechTrack fails to load a file
* Improved support of unnecessarily long fields for the UI
* Change legacy logging details

## Bug Fixes
* Fixed a bug where TechTrack crashes if a `Deadline` field in `TechTrack.json` is past the current date
* Fixed a bug where loading sample data on a fresh installation of TechTrack would cause the program to crash

* **Documentation**:
  * User Guide:
    * Constructed outline of UG for team members to edit easily, and fixed bugs
    * Proofread and checked for grammatical errors
  * Developer Guide:
    * Proofread and checked for grammatical errors
    * Added the `view command`, `UI Enhancement` section, which are features that I implemented
    * Updated manual tests for `tag`, `name`, `clear`, `help`, `list`, `exit` command.
    * Checked for bugs in UML diagrams.
    * Wrote the `Planned enhancements for Feature Flaws` section

## **Contribution to team-based tasks**:
  * Sourcing of images
    * Sourced for application icon from [PNGEgg](https://www.pngegg.com/en/png-ewtjs)
    * Sourced for contact icon for `view` command from [icons8](https://icons8.com/icon/104074/contact-us)
    * Sourced for more details icon for `view` command from [icons8](https://icons8.com/icon/set/details/color)
  * Created tags for PE-D issue triaging
  * Ensure DG and UG are properly formatted and split into coherent sections 
  * Removed all remnants of AB3 from JavaDocs of test code.
  * Added summary text to `/docs/data/techtrack.yml` to ensure summary of TechTrack is consistent among
    documents

## **Review/mentoring contributions**
* Reviewed [PRs from other members](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+reviewed-by%3Adsja612)
* Commented on [PRs from other members](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+commenter%3Adsja612)

## Links to Contributions
[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dsja612&breakdown=true)

[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=dsja612)

[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Adsja612)

[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+author%3Adsja612)
