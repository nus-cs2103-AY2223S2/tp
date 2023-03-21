### Implementation Details

The implementation of the `add` command involves creating a new `Student` object and storing it in `AddressBook`.

Given below is a class diagram on the `Student` class and the classes related to its attributes: <br>
![student_diagram](/docs/images/StudentClassDiagram.png)

The `Student` object is composed of attributes:

* `Name`: The name of the student.
* `Phone`: The phone number of the student.
* `Email`: The email address of the student.
* `Address`: The address of the student.
* `Education`: The education level of the student.
* `Subject`: The subjects the tutor is teaching the student.
* `Remark`: Remarks/notes the tutor has about the student.

### Proposed Implementation
The `add` command has the following fields:
* Prefix `\n` followed by the name of the student.
* Prefix `\p` followed by the phone number of the student.
* Prefix `\e` followed by the student's email.
* Prefix `\a` followed by the student's address.
* Prefix `\edu` followed by the student's education level.
* Prefix `\s` followed by the subject name.
* Prefix `\r` followed by the remarks/notes on the student.

Here is a sequence diagram showing the interactions between components when `add n/Alice edu/Primary 6` is run.: <br>

![add_sequence](/docs/images/AddSequenceDiagram.png)

### Feature details
1. The app will validate the parameters supplied by the user with pre-determined formats for each attribute.
2. If an input fails the validation check, an error message is provided which details the error and prompts the user for a corrected input.
3. If the input passes the validation check, a new `Student` entry is created and stored in the `AddressBook`.

### General Design Considerations 

The implementation of the attributes of a `Student` is very similar to that of a `Person` in the original AB3 codebase. </br>

Some additions made were the `Education`, `Subject` and `Remark` attributes. </br>
1. `Education` is implemented similar to the other attributes like `Address`, but is modified to fit the logic that a student can only have one education level.
2. `Subject` is implemented in a similar way to `Tags` in AB3 but has been modified to accomodate subject names that are more than one word long as in real life.
3. Every attribute except`Name` has been made **OPTIONAL** to accomodate circumstances where some student's details are unknown at the time of entry.
   * We utilised the [java.util.Optional<T>](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html "java.util.Optional<T>") class to encapsulate the optional logic of the attributes.

When adding a student entry, these were the alternatives considered.
* **Alternative 1 (current choice):** Only `Name` has to be specified to create a `Student` entry, making the other attributes `Optional<>`.
  * Pros:
    * Improves user convenience by allowing them to add a `Student` entry even with limited knowledge about their details.
  * Cons:
    * A lot of modification for empty/*null* inputs have to be accounted for when saving the data and testing.
* **Alternative 2:** All parameters have to be filled in
  * Pros: 
    * Easier to implement as there is lesser room for errors when dealing with empty/*null* inputs
  * Cons:
    * `add` becomes a lengthy command to execute as unnecessary additional time is needed to enter dummy values to meet the input requirements.
    * Reduces user convenience as "useful" entries that can be made are limited to students whose details are all known.
