---
layout: page
title: Jiahui's Project Portfolio Page
---

### Project: FitBook

FitBook is a desktop tracking book application used for tracking the progress and information
for the clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added *ClientAppointmentComparator* and enhanced *Appointment* class to client.
    * What it does: Allows the user to view sorted appointments of clients.
    * Justification: This feature significantly improves the product by providing users with an easy way to keep track of appointment dates and times for their clients, making it easier to manage client relationships and ensure appointments are scheduled effectively.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation was challenging as it required changes to existing commands and an in-depth analysis of design alternatives to determine the most effective and user-friendly approach. However, the end result is a highly useful feature that enhances the overall user experience of FitBook.
  
* **New Feature**: Added **view** Command.
    * What it does: Allows users to view detailed information about a selected client, including their basic information, as well as any attached exercises and routines.
    * Justification: This feature significantly improves the product by providing users with an easy way to keep track of detailed information about their clients, making it easier to manage client relationships and ensure that their fitness programs are tailored to their specific needs and goals.
    * Highlights: It required an in-depth analysis of design alternatives.

* **New Feature**: Enhanced **graph** Command and generated weight over time graph scene.
    * What it does: Allows users to view a graph of a client's weight over time.
    * Justification: This feature significantly improves the product by providing users with a visual representation of a client's weight change over time, which can be an important factor in tracking progress and adjusting fitness programs accordingly.
    * Highlights: The development of this feature required an in-depth analysis of various design alternatives in order to determine the most effective and user-friendly approach for presenting weight data over time. The implementation was also challenging, as it required changes to existing commands and careful consideration of how this new command would affect other commands and features of FitBook. However, the end result is a highly useful feature that enhances the overall user experience of FitBook.

* **Major enhancement**: Designed the **UI/UX** of FitBook
    * Function: The GUI includes the following pages:
      * Schedule page (default main page) with client list panel and schedule panel
      * Exercise page with exercise list panel and exercise panel
      * Summary page with summary list panel and summary panel
      * Help Window
      * Graph scene
      * The inner components can automatically adjust themselves according to the current width and height of the window.
    * Justification: The redesigned UI/UX of FitBook follows a relaxed style, which helps users to feel more engaged and motivated during their exercises. The responsive layout also allows the application to be easily adapted to different screen sizes, providing users with a seamless experience across different devices.
    * Highlights:  Designing and implementing the new UI/UX required a significant amount of self-learning and reference, as well as careful consideration of user feedback and testing. The end result is a highly user-friendly and engaging interface that enhances the overall experience of using FitBook.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=OliviaJHL&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=jundatan&zR=AY2223S2-CS2103T-T15-2%2Ftp%5Bmaster%5D&zACS=355.44&zS=2023-02-17&zFS=jundatan&zU=2023-04-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Documentation**:
    * User Guide:
      * Added documentation for `User Interface Introduction` [\#196](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/196)
      * Enhanced documentation for `Quick Start`, `Prefixes for Client Commands`, `Prefixes for Routine Commands`, and `FAQ`  [\#196](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/196)
      * Added documentation for the features `listClients` [\#72](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/72)
      * Did cosmetic tweaks to existing for `Command Sumamry` [\#196](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/196)
      
  * Developer Guide:
      * Added use case for  `listClients` feature [\#75](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/75)
      * Added use case for  `View selected client's summary information` feature [\#205](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/205)
      * Added manual testing for `Saving data`, `exit` [\#205](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/205)
      * Added Implementation for `Edit appointments` [\#131](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/131)
      * Added Implementation for `Past 30 days weight data graph` [\#205](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/205)
      * Update UML diagrams and descriptions for Ui component of FitBook [\#205](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/205)

* **Community**:
    * PRs reviewed (with non-trivial review comments):
    [\#77](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/77),
    [\#93](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/93),
    [\#137](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/137),
    [\#145](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/145),
    [\#147](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/147),
    [\#148](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/148),
    [\#150](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/150),
    [\#161](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/161),
    [\#163](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/163),
    [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193),
    [\#195](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/195),
    [\#202](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/202),
    [\#206](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/206),
    
    * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/OliviaJHL/ped))
