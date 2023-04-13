---
layout: page
title: Hui Xuan's Project Portfolio Page
---

## Project: sprINT

sprINT is an internship-tracking application that was created to assist students in their internship hunt. Students 
often face a great administrative burden in keeping track of the high volume of job or internship applications, which 
include details like interview dates. Therefore, sprINT aims to help these students by 
tracking and recording their application details, such as the company, company contact information and status.

sprINT is written in Java, with the GUI built using JavaFX. 

Given below are my contributions to the project.

### **Code contributed** 
* See [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=huixuant&breakdown=true)

### **New feature** 
**Added the ability to add, edit and delete tasks** (PR [#115](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/115))
  * **What it does**: allows the user to add a task to an existing application, as well as edit or delete an existing task.
  * **Justification**: This feature improves the product significantly because the user can now keep track of other 
    requirements or milestones that are related to the application, like interviews and online assessments, but could
    not be captured previously. 
  * **Highlights**: This enhancement involved adding new classes to represent `Task` objects and its fields, as well as for
    task-related commands. It required changes in existing classes and test cases related to `Applications`, since 
    `Task` was implemented as an additional field within `Application`.

### **Project management**
  * Authored **31** issues on the Github repo

### **Enhancements to existing features**

**Updated GUI** (PR [#166](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/166) and
  [#68](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/68))
* Enhanced the previous design of AB3 to match our product mock-up

**Updated delete feature** (PR [#72](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/72))
* Updated the original `DeleteCommand` in AB3 (now `DeleteApplicationCommand`) to match sprINT's user 
  story and requirements 

**Refactored Model component** (PR [#46](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/46))
* Added new classes to represent `Application` objects in a new package and updated existing classes in Model to 
  match sprINT's requirements  

### **Documentation**
**User Guide** (PR [#227](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/227) and [#47](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/47))
* Created the User Interface section to explain the functionality of each component in sprINT's UI 
* Updated Glossary section with definitions for technical terms and sprINT-related terminology like 'application' 
  and 'status'
* Updated documentation for the delete feature to match sprINT's requirements 

**Developer Guide** (PR [#119](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/119))
* Added implementation details of the `AddTaskCommand`
* Updated Glossary section with definitions for sprINT-related terminology  
* Changed all references to AB3 and its classes (including links and UML diagrams) to sprINT 

### **Contributions to team-based tasks**
* Set up and configured Github organisation and team repository

