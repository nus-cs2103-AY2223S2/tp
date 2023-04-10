# Winson Heng's Project Portfolio Page

### Project: E-lister

E-Lister is a desktop address book application used for streamlining contact management in insurance companies. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Feature I**: Added the `export` command.
  * What it does: Exports the data into a csv file at a location selected by the user.
  * Justification: This feature provides a convenient way for insurance agents to mass extract contact information such as email and phone number.
  * Highlights: This feature required the implementation of new helper classes to convert `Person` objects into csv-friendly text outputs.
  * Additional info: There are two optional keywords that can be appended to this command, namely `all` and `shown`.
    * `all` exports all contacts on the app
    * `shown` exports only contacts which are displayed, which is useful when the agent wants to target a specific group of potential customers

* **Feature II**: Added the `import` command.
  * What it does: Imports the data from a csv file selected by the user.
  * Justification: This feature provides allows insurance agents to directly work on an existing dataset rather than adding contacts one by one.
  * Highlights: This feature required the implementation of new helper classes to parse csv inputs into instances of `Person`.
  * Additional info: There are two optional keywords that can be appended to this command, namely `combine` and `reset`.
    * `combine` merges the imported dataset with the existing contacts to preserve information while ensuring duplicates are ignored
    * `reset` removes all existing data and allows the user to start fresh from his/her dataset

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=winsonheng&breakdown=true)

* **Project management**:
  * Ensured the harmonisation of different features in solving the needs of our target users
  * Adviced and assisted feature design and debugging
  * Managed milestone progress and ensured tasks were completed on time

* **Enhancements to existing features**:
  * Ideation and design of UI enhancements, including the tags and history panes
  * Refactored multiple classes and comments to match the E-Lister name

* **Documentation**:
  * User Guide:
    * Added documentation for own features
    * Addition of various screenshots with labelled pointers to aid user navigation
  * Developer Guide:
    * Added implementation for `import` and `export` features
    * Added information on CSV-format conversion
    * Added ExportSequenceDiagram.puml to illustrate the export command process
    * Updated UML diagrams such as StorageClassDiagram.puml
