# Pawan Kishor Patil

### Hi there👋 <br><br>
I took part in developing Pied Piper, a task management and performace tracking app that helps current and future university student
leaders take charge of their group projects. <br>
I was responsible analyzing user requirements, and designing the user interface. I also collaborated with the development team to implement key features, such as assigning tasks, storing tasks, etc. In addition, I conducted user testing and collected feedback to refine the app's functionality and improve the user experience. It allows students to more easily manage their group project, and keep track of their teammates' progress while being able to rate their performances upon completion of their assigned tasks.


<center><img src="../images/pawanpatil19.png" alt= “Pawan”   height="400"></center>

Given below are my contributions to Pied Piper:

* **New Feature**: Added the ability to assign tasks to a person/member.
  * What it does: allows the user to assign or reassign a task to a person.
  * Justification: This feature improves the product significantly as it helps the user to keep track of the tasks assigned to each person, which is a key feature of the app. It is further used by other features such as the ability to rate the performance of a person. The user can also reassign a task to a different person if the task is not completed by the person assigned to it.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required consideration of different scenarios where the user might delete the person, or make modifications to different instances of the the member. 
  * Pull requests: [\#48](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/48),[\#180](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/180)

<br>

* **New Feature**: Added the ability to mark/unmark a task as completed and rate the performance of a person.
  * What it does: allows the user to mark a test as complete and give a score to the task in the range of 1 to 5 depending on the performace of the person assigned to it. The user can also unmark a task as incomplete if necessary.
  * Justification: This feature improves the product significantly as it helps the user to keep track of the performance of each person, which is a key feature of the app. It is further used by other features such as the ability to comment on the performance of a person, or reviewing the average score of a person in review function.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required consideration of different scenarios where user wanted to mark a task as complete, but the task was not assigned to any person. It also required consideration of different scenarios where the user might delete the person, or wanted to modify the score of a task. 
  * Credits: *{Idea for marking tasks was inspired from iP}*
  * Pull requests: [\#74](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/74)

  <br>

* **New Feature**: Added the ability to store tasks in a local file.
  * What it does: It creates a local json file based on the user's input and its structure is based on the TaskBook, TaskStorage and related classes. It also allows the user to read the tasks from the local file. Any changes made to the tasks are also reflected in the local file.
  * Justification: This feature is an essential part of the app as it allows the user to store the tasks in a local file and load them when the app is launched.
   * Credits: *{TaskBook storage was inspired by AddressBook storage techniques}* 
  * Pull requests:[\#85](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/85)

  <br>

* **Updated Feature**: Updated the deletion workflow of a person.
  * What it does: It allows the user to delete a person from the address book and also mark all the tasks given to that person as not assigned.
  * Justification: This feature is crucial in ensuring a proper workflow of the app. It ensures that the user does not have to manually unassign the tasks assigned to the person being deleted. It also ensures that the other tasks assigned to different members are not affected.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required consideration of different scenarios where the task was completed and the user wanted to delete the person who completed it.
  * Pull requests: [\#181](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/181)

<br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=PawanPatil19&tabRepo=AY2223S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Documentation**:
  * Developer Guide:
    * Added implementation details of the `todo` feature aka feature to add tasks.[\#173](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/173)

* **Community**:
  * PRs reviewed: [\#91](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/91), [\#95](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/95), [\#73](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/73), [\#49](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/49), [\#56](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/56), [\#100](https://github.com/AY2223S2-CS2103T-W15-3/tp/pull/100)
