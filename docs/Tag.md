### \[Proposed\] Tag feature

#### Proposed Implementation

The proposed Tag mechanism is facilitated by `ELister`. It extends `ELister` with an Tag , stored internally as an `Tag` and  Additionally, it implements the following operations:

* `ELister#Tag()` — Adds a Tag to the person based on the index in the list.

These operations are exposed in the `Model` interface as `Model#addTag()`

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedELister` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

Step 2. The user executes `Tag 5 good` command to Tag the 5th person in the address book with a new Tag `good`. The `Tag` command calls `Model#addTag()`, causing the modified state of the address book after the `Tag 5 good` command executes to be saved in the `eListerStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.