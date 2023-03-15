---
layout: page
title: Chin Jun An's Project Portfolio Page
---

### Project: Duke Delivery

Duke Driver - a delivery tasking and planning application for delivery personnel. Duke Driver provides bulk management and personalised job listing for drivers. The user can interact with it using both CLI and GUI. The GUI is created with JavaFX. It is written in Java and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added notification function.
    * What is does: Shows pop up notifications when app starts up, or when certain events/deadline has passed
    * Justification: This feature is implemented to alert the user of any events from the app. It is used in conjunction with the reminder and scheduling function of the app
    * Highlights: It was challenging to implement this function as JavaFx did not have any native notification feature. Hence, I had to find an external library that did so. I decided to used ControlFX, an open source project for JavaFX that serves to provide high quality UI controls on top of the existing JavaFX distribution. Through abstraction, this feature can be used by other portions of the applications (ie. reminders, scheduling, etc). Hence, the implementation extended the functionalities and benefits of the application.
    * Credits: ControlFx library, Genuine Coder Youtube Channel on implementing the ControlFX notification feature.

* **New Feature**: Added reminder function.
    * What is does: Allows users to add reminders into the address book. This function is used in conjunction with the notification function, to fire a pop up notification when an upcoming deadline/schedule is approaching
    * Justification: This feature helps users with busy schedules to remind them of certain task they might have forgotten to do in the upcoming future. Essentialy, it reduces the load of the shoulders of the users in remembering upcoming task/schedules.
    * Highlights: This feature required an addition to the data structure of the address book (an additional list to record reminders). This led to additional classes that involved managing the storage and reading from local memory and hard disk. Fortunately, not much have to be done as it was similar to existing codes. Further improvement to this feature involves running process threads to fire notifications while the app is running in the background.
    * Credits: Existing codes from AB3 which involved the models and storage functionality of the app.
  

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * to be added soon

* **Enhancements to existing features**:
  * to be added soon

* **Documentation**:
  * User Guide:
    * Added documentation for the features add_reminder, delete_reminder, list_reminder (PR #92)
  * Developer Guide:
    * Added implementation details of the notification function. (PR #92)
    * Added implementation details of the reminder function. (PR #92)

* **Community**:
  * PRs reviewed (with non-trival review comments): #84
  * 
