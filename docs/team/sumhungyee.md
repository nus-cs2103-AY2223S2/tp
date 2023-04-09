---
layout: page
title: Sum Hung Yee's Project Portfolio Page
---

### Project: Dengue Hotspot Tracker

Dengue Hotspot Tracker is a desktop app for managing dengue cases. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9.5 kLoC.
Given below are my technical contributions to the project:
Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=sumhungyee)
Summary of Contributions:

- Refactoring:
   - Refactored phone numbers from AB3. Postal Codes are now part of a person's data.
   - `Postal` codes may be entered as a string of 6 digits, or a character 's' or 'S' followed by a string of 6 digits.
   - Refactored addresses from AB3. Dates are now part of a person's data as `Date`, and dates must be valid. Date strings are validated through the parsing of strings into LocalDate objects.

- New Feature: Added the ability to undo/redo previous commands that affect stored data.
  - What it does: allows the user to undo all previous commands one at a time, or by any number of steps specified by the user, up to a maximum number of 10.
    Preceding undo commands can be reversed by using the redo command. If the user specifies an undo/redo of n steps, where n is greater than the maximum possible number of undo/redo actions allowed eg m, m steps will be undone/redone.
  - Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  - Highlights: This enhancement involves the use of slightly complex data structures, such as deques and stacks.
  - To implement an undo/redo feature, an implementation of a specialised stack is created. This stack can contain up to a maximum number of 10 items, which represents a maximum of 10 undo/redo steps.
    This stack supports pop operations, and popped items will be stored in a separate location in an undo. In a redo, all popped items in the separate location will be pushed back in. When a user performs an undo and performs another action, the temporary location is cleared.
    This implementation makes minimal changes to existing commands, and is compatible with/can be extended to future commands, as undo/redo and the temporary memory do not depend on executed commands, but only on the saved states of the DengueHotspotTracker.
  - To account for undo/redo allowing positive-integer arguments which determine the number of steps to undo/redo, an `UndoCommandParser` and `RedoCommandParser`, as well as a consolidated abstract class `UndoRedoCommandParser` were implemented.
    Through these parsers, the user can undo/redo multiple steps. If no undo/redo operations can be performed, the user will be notified. If undo/redo operations can be performed, but the number of possible undo/redo operations is
    less than the number of undo/redo operations possible, the undo/redo command will execute undo/redo operations for the maximum time possible.

- New Feature: Accept dates in differing formats.
  - What it does: allows the user to add dates in different/multiple formats.
  - Justification: The user may not always want to enter dates in a specific format. This feature allows the user some flexibility in entering the required arguments.
  - Highlights: The `DengueHotspotTracker` takes in a user's date input and attempts to determine the format. Then, the Tracker parses the format.
    For example "2000-01-01", "2000 January 19" are both acceptable dates. This strategy involves the use of `java.util.regex.Pattern` to determine the format of the date string
    and `DateTimeFormatterBuilder` to create the format, before converting the original date string based on this generated format.
  - A `DateFormat` class also has to be created to represent a date format, eg. "yyyy MMM dd".

- Enhancements to existing features:
    - Wrote tests for the undo/redo feature (Pull Request #138, #131). Some tests are on the underlying data structure of the temporary memory storage used for undo/redo operations.
      Subsequent tests are on the memory storage itself and involve the generation of random `DengueHotspotTracker` instances, each with a different list of randomly generated persons.
    - To allow for these tests, the `DengueHotspotTracker` and `ReadOnlyDengueHotspotTracker` classes had to be modified to generate a deep copy of themselves. A deep copy will represent an
      instance of the data to be saved. `PersonBuilder` was also modified to generate random persons.
    - Created new interface for `Date` and `Age`, named `ContinuousData`, and `generateRange()` such that the Range class will not be misused. (#172, #174)
      For example, `Range<Integer>` cannot be created, as the constructor of Range is private, and the `.of` method takes in subclasses of `ContinuousData`.
    - Edit name inputs to remove excess whitespaces, and invalidate numerics. For example "22a" is not a valid name. Furthermore, "Michael          Scott   " is read in as "Michael Scott".
    - Edit FXML files in Main Window. Changed icon and title. (Pull Request #37).

- Project management:
  - Assigned and created issues for possible future implementations.
  - Recorded Demo video for v1.3.
- **Documentation:**
  - User Guide
    - Added documentation for undo/redo.
    - Edited documentation for `Postal`, `Date` and `Name`.
  - Developer Guide
    - Added UML diagrams for creating `Date` objects.
- **Community:**
  - PRs reviewed (with non-trivial review comments):
    - [#173](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/173)
    - [#157](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/157)
    - [#140](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/140)
    - [#104](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/104)
    - [#156](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/156)
    - [#159](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/159)
