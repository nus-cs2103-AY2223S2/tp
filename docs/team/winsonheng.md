# Winson Heng's Project Portfolio Page

### Project: Elister Level 3

E-Lister is a desktop address book application used for streamlining contact management in insurance companies. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `export` command.
  * What it does: Exports the data into a csv file at a location selected by the user.
  * Justification: This feature provides a convenient way for insurance agents to mass extract contact information such as email and phone number.
  * Highlights: This feature required the implementation of new helper classes to convert Person objects into csv-friendly text outputs.
  * Additional info: There are two optional keywords that can be appended to this command, namely `all` and `shown`.
    * `all` exports all contacts on the app
    * `shown` exports only contacts which are displayed, which is useful when the agent wants to target a specific group of potential customers

* **New Feature**: Added the `import` command.
  * What it does: Imports the data from a csv file selected by the user.
  * Justification: This feature provides allows insurance agents to directly work on an existing dataset rather than adding contacts one by one.
  * Highlights: This feature required the implementation of new helper classes to parse csv inputs into instances of Person.
  * Additional info: There are two optional keywords that can be appended to this command, namely `combine` and `reset`.
    * `combine` merges the imported dataset with the existing contacts to preserve information while ensuring duplicates are ignored
    * `reset` removes all existing data and allows the user to start fresh from his/her dataset

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=winsonheng&breakdown=true)

* **Project management**:
  * To be added soon

* **Enhancements to existing features**:
  * To be added soon

* **Documentation**:
  * User Guide:
    * To be added soon
  * Developer Guide:
    * To be added soon

* **Community**:
  * To be added soon

* **Tools**:
  * To be added soon
