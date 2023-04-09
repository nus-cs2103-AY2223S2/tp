# Yulong's Project Portfolio Page

## Project: ExecutivePro
### Overview:
ExecutivePro (EP) is a desktop app for Human Resource managers to manage their employee information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EP can get your employee management tasks done faster than traditional GUI apps.

Here are my contributions to the project.

**Code Contributed:**
[RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=gyulong1&breakdown=true)

* **Enhancements implemented**:
- Updated implementation of `edit` command to use new EmployeeId class
- Refactored `Person` class to `Employee` class to tailor the code base to our product. Also changed all instances of person into employee.
- Refactored `AddressBook` class to `ExecutiveProDb` class to tailor the code base to our product.
- Added 4 new fields for `Employee` class, `payroll`, `leaveCounter`, `dateOfBirth`, `dateOfJoining`. Updated test cases.
- Updated implementation of `AddCommand`, `EditCommand` and ui components to view the new fields. Updated test cases.
- Created new classes `Payroll`, `LeaveCounter` for an employee. Created test cases.
- Added new command `leave` to help employees take leave. Created test cases.
- Created new function in `model` to select employee based on EmployeeId instead of Index.

* **Contributions to the UG**:
- Added instructions for `leave` command along with examples.
- Updated the format for `edit` and `add` to include the new fields for employees.

* **Contributions to the DG**:
- Updated the explanation of the _Implementation_ of `edit` and `leave` command and also the UML sequence diagram.
- Added NFR use cases.

* **Contributions to team-based tasks**:
- Played active role in the brainstorming sessions to come up with new features.
- Helped to triage the bugs received from the PE dry run.
- Helped fix bugs received after the PE dry run.
- Fix test cases and improve code coverage

* **Review/mentoring contributions**:
- Pull Requests reviewed and merged:
  [#231](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/231)
  [#226](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/226)
  [#154](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/154)
  [#153](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/153)
  [#159](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/159)
  [#162](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/162)
  [#166](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/166)
  [#107](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/107)
  [#100](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/100)
  [#90](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/90)
  [#82](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/82)
  [#81](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/81)
  [#28](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/28)

**Contributions beyond the project team :**
