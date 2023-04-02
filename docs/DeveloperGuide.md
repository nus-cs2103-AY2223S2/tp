# Developer Guide

* [Acknowledgements](#acknowledgements)
* [Design](#design)
* [Implementation](#implementation)
    * [Add Feature](#add-feature)
    * [Delete Feature](#delete-feature)
    * [Add Image Feature](#add-image-feature)
    * [Delete Image Feature](#delete-image-feature)
    * [Import Contacts Feature](#import-contacts-feature)
    * [Find Feature](#find-feature)
    
* [Appendix: Requirements](#appendix-requirements)
    * [Product Scope](#product-scope)
    * [User Stories](#user-stories)
    * [Use Cases](#use-cases)
    * [Glossary](#glossary)

## Acknowledgements

* This project was based on the AddressBook3 application which can be found 
  [here](https://github.com/nus-cs2103-AY2223S2/tp)
  
---

## Design

### Architecture

{ToDo: Add architecture}

---

## Implementation

### Add Feature

#### Add Implementation

The Add feature is facilitated by the classes `AddCommand`,
`AddCommandParser` and `ParserUtil`.
The `AddCommandParser` first parses through the user command to obtain
the necessary inputs through using `ParserUtil#parseIndex`. Following which an
instance of a `AddCommand` containing the details encapsulated in Person class is returned.
`AddCommand#execute` is then called, which sets the image of the contact
at the desired index to a default image, and deletes the existing person through
`DeleteCommand`.

Given below is an example usage scenario for how the add mechanism behaves.

Step 1: User starts up the application and sees their list of contacts. User must then enter
[name] [status] [phone] [email] [address] as they are required by the system if not it will cause
an invalid exception.

Step 2: The user can add optional fields, that is the tags. Each of the tags is colour-coded based on its type.

> **Note**: If the user inputs a contact that exists in the contact list, or if the user misses out a compulsory
> input, an error will be thrown.

Step 3: If the instruction was valid, `AddCommand#execute` is called to wrap the details into person and set the
person to the next available index.

Step 4: User can use edit index to select the chosen person card and `EditCommand#execute` is then called to
change the contents inside and update the program directory.

The following sequence diagram shows how the `add` operation works.

![AddSequenceDiagram](images/AddSequenceDiagram.png)

> **Note**: The lifeline of the `AddCommandParser` and `AddCommand`
> should end at the destroy marker (X) but due to the limitations of PlantUML, the
> lifeline reaches the end of the diagram.

The following activity diagram summarizes what happens when a user executes a
add command:

![AddActivityDiagram](images/AddActivityDiagram.png)

#### Design Considerations:

- **Alternative 1 (current choice):** Improve on tags by adding more colours to the
  program directory.
    - Pros:
        - Ensures the labels are organised neatly in different colour
    - Cons:
        - Extra complexity in requiring to map the additional tag operations

- **Alternative 2:** Make status field compulsory from program directory.
    - Pros:
        - Easier to implement and user can search based on year and course
    - Cons:
        - Application will not detect the input content from the users which could contain
          non-related strings.

### Delete Feature

#### Delete Implementation

{Todo: Add delete implementation}

#### Design Considerations

{Todo: Add design considerations for delete}

### Add Image Feature

#### Add Image Implementation

The add-image mechanism is facilitated by `AddImageCommand`, `AddImageCommandParser` and `ImageUtil` class.

- `AddImageCommand` extends `Command`
- `AddImageCommandParser` extends `Parser`

The `AddImageCommandParser` parses the user input into index of contact and path to an image.
It returns a `AddImageCommand` with the 2 information retrieved.
`AddImageCommand#execute` copies the image provided by the user via a path and replaces the current image with the new
one.
It also saves the file name of the new image to the `model`.

Given below is an example usage scenario and how the add-image mechanism behaves at each step.

Step 1. When user wants to add an image to a contact, they use the `add-image` command.

Step 2. The `LogicManager` receives the command text from the user input and gives it to `AddressBookParser`
. `AddressBookParser` calls `AddImageCommandParser` to parse the user input.

Step 3. The `AddImageCommandParser` retrieves the contact index as well as the image path and creates
a `AddImageCommand`

Step 4. `AddImageCommand#execute` is called. The method calls `ImageUtil#importImage` to copy the image into the "
profile_pictures/" directory.
Once that is successful, `AddImageCommand#execute` proceeds to call `ImageUtil#deleteImage` to remove the current image.
Finally `AddImageCommand#execute` updates the model provided in the arguments.

> **Note**: If the path given is invalid or if the file at the given path is not a png/jpeg image, the command will not
> be completed.

The following sequence diagram shows how the add-image operation works:<br>
![AddImageSequenceDiagram](images/AddImageSequenceDiagram.png)
> **Note**: The lifeline of the `AddImageCommandParser` and `AddImageCommand`
> should end at the destroy marker (X) but due to the limitations of PlantUML, the
> lifeline reaches the end of the diagram.

The following activity diagram summarizes what happens when a user executes add-image command: <br>
![AddImageActivityDiagram](images/AddImageActivityDiagram.png)

#### Design considerations:

- Alternative 1 (current choice): Copy the image into application-created directory
    - Pros:
        - A single location to store/check for images
        - Naming scheme determined by application
    - Cons:
        - Copying is a file I/O which have many caveats
- Alternative 2: Save the path provided by User
    - Pros:
        - Does not require any file I/O
        - Easy to save as only the Path as a string
    - Cons:
        - Path is easily invalidated (e.g. user moves/deletes/renames the image)

### Delete Image Feature

#### Delete Image Implementation

The delete-image feature is facilitated by the classes `DeleteImageCommand`,
`DeleteImageCommandParser`, `ImageUtil`, and `ParserUtil`.
The `DeleteImageCommandParser` first parses through the user command to obtain
the desired index through using `ParserUtil#parseIndex`. Following which an
instance of a `DeleteImageCommand` containing the desired index is returned.
`DeleteImageCommand#execute` is then called, which sets the image of the contact
at the desired index to a default image, and deletes the existing image through
`ImageUtil#deleteImage`.

Given below is an example usage scenario for how the delete-image mechanism behaves.

Step 1: User starts up the application and sees their list of contacts. Some of
which have already had an image added.

Step 2: The user decides that the image given to the contact at index 4 is not
suitable, and wants to delete it. The user inputs `delete-image 4`.
`DeleteImageCommandParser#parse` is then called to parse this input for the
desired index.

> **Note**: If the user inputs an index of a contact which currently does not have
> an image, or if the user inputs an invalid index, an error will be returned to
> the user

Step 3: If the instruction was valid, `Model#deleteImage` is called to set the
image of the contact to the default image.

Step 4: `ImageUtil#deleteImage` is then called to delete the existing image
from the program directory.

The following sequence diagram shows how the delete-image operation works.

![DeleteImageSequenceDiagram](images/DeleteImageSequenceDiagram.png)

> **Note**: The lifeline of the `DeleteImageCommandParser` and `DeleteImageCommand`
> should end at the destroy marker (X) but due to the limitations of PlantUML, the
> lifeline reaches the end of the diagram.

The following activity diagram summarizes what happens when a user executes a
delete-image command:

![DeleteImageActivityDiagram](images/DeleteImageActivityDiagram.png)

#### Design Considerations:

- **Alternative 1 (current choice):** Delete the existing image file from program
  directory.
    - Pros:
        - Ensures application does not consume excess storage
    - Cons:
        - Extra complexity in requiring file i/o operations

- **Alternative 2:** Disregard deleting the image file from program directory.
    - Pros:
        - Easier to implement
    - Cons:
        - Application will take up increasingly more unnecessary storage during
          its lifetime of usage

### Import Contacts Feature

#### Import Contacts Implementation

The import feature is facilitated by the classes `ImportCommand`, `ImportCommandParser`,
`SocContacts` and `ChsContacts`. The `ImportCommandParser` first parses through the user
command to obtain the desired faculty to be imported. An instance of
a `ImportCommand` containing the desired faculty from either `SocContacts` or
`ChsContacts` is then returned. `ImportCommand#execute` is then called,
which calls `Model#addPerson` to add the unique contacts into BookFace.

Given below is an example usage scenario for how the import mechanism behaves.

Step 1: User starts up the application and sees their list of contacts.

Step 2: User decides to import contacts from faculty SOC and input
`import soc`. `ImportCommandParser#parse` is then called to parse
this input for the desired faculty.

> **Note**: If the user inputs a faculty that does not exist,
> an error will be returned to the user

Step 3: If the faculty was valid, `ImportCommand#execute` is called
with the valid faculty that was parsed

Step 4: `Model#addPerson` is called for however
many non-duplicate contacts are to be added.

The following sequence diagram shows how the import command works.

![ImportSequenceDiagram](images/ImportSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes an
import command:

![ImportActivityDiagram](images/ImportActivityDiagram.png)

#### Design Considerations:

- **Alternative 1 (current choice):** ImportCommand#execute takes in a string to
  determine which faculty of in-built contacts to import
    - Pros:
        - User input is easily parsed (as it is just a string), faster to read and
          execute the command
    - Cons:
        - Possibly bad OOP design, ImportCommand#execute should take in a Faculty object

- **Alternative 2:** ImportCommand#execute takes in a Faculty object instead of a string
  to determine which faculty of in-built contacts to import
    - Pros:
        - Better OOP design then simply taking in a string
    - Cons:
        - Input will take longer to parse as the string input has to be parsed into
          a faculty object to be used as input to ImportCommand#execute
          
### Find Feature

#### Find Implementation

The Find feature allows users to filter out relevant contacts from their contact list.
This feature is facilitated by the `FindCommand` class and the `FindCommandParser` class,
as well as the various predicate classes listed below.

* `AddressContainsKeywordPredicate`
* `EmailContainsKeywordPredicate`
* `NameContainsKeywordPredicate`
* `PhoneContainsKeywordPredicate`
* `StatusContainsKeywordPrediate`
* `TagContainsKeywordPredicate`

When the user inputs a `find` command, the `FindCommandParser` parses through the
input to obtain the prefixes and keywords that the user has entered. For each
prefix-keyword pair, an instance of its associated predicate class is created
and all the instances are combined with `Predicate#and`. This final predicate
object is then passed to `Model#findOrListContents` to display the filted list to
the user.

An example usage scenario is provided below to illustrate the mechanism of the
`find` command.

Step 1: User starts up the application and sees a list of all their contacts.

Step 2: User inputs `find t/cs2103` to find the list of his contacts that takes
CS2103.

Step 3: `FindCommandParser` parses through the user input and creates an instance
of `TagContainsKeywordPredicate` containing the keyword "cs2103". An instance of
`FindCommand` with this predicate is then created and returned.

Step 4: `FindCommand#execute` is called, which calls `Model#findOrListContents`
to display the final filtered list to the user.

The sequence diagram below illustrates this process.

![FindSequenceDiagram](images/FindSequenceDiagram.png)

#### Design Considerations:

**Aspect: Combining the predicates from multiple prefixes**

- **Alternative 1 (current choice):** Using `Predicate#and`
- **Alternative 2:** Using `Predicate#or`

**Rationale:** Decision was made to use a logical `AND` instead of `OR` when
combining multiple predicates as we believe this was the more intuitive approach.
When a user is looking for a specific group of people on his contacts, they would
expect the application to return a list of contacts that matches **all** of his
given input requirements, instead of a list of contacts that contains one or more
of the keywords that was entered. Hence, the logical `AND` was decided to be more
appropriate, and `Predicate#and` was used.

---

## Appendix: Requirements

### Product Scope

**Target user profile**:

Students from the National University of Singapore (NUS) who need to keep track of their contacts from different
classes/ccas/clubs etc.

**Value proposition**:

NUS Students often take many classes and meet different people. This application helps them to organise their
contacts list for an easier way to set up proper communication channels. This makes it easier for students to form
connections with their peers during their time in University.

### User stories

1. As a student, I can find all relevant university contacts/POCs for various purposes
2. As a student, I can delete the image for a person on my contact list
3. As a forgetful student, I can see who a person on my contact list looks like
4. As a user, I can add a contact so that I can keep track of my contacts.
5. As a user, I can view all contacts so that I can have an accessible list of contacts.
6. As a user, I can delete a contact so that I can remove unneeded and/or incorrect contacts.
7. As a student, I can to find other students in my classes that have been added as a contact so that I can ask them for
   help

### Use cases

System: BookFace

```
Use case: UC1 - Add Contact

Actor: User

MSS:
1. User chooses to add a contact.
2. User enters contact's details.
3. New user's contact's details are added to BookFace.
Use case ends.

Extensions:

2a. BookFace detects incomplete/invalid details entered.
BookFace displays an example of valid details.
System repeats Step 1-2 until valid details are entered.
```

```
Use case: UC2 - Delete Contact

Actor: User

MSS:
1. User chooses to delete a contact.
2. User enters contact's index.
3. Contact is deleted from BookFace.
Use case ends.

Extensions:

2a. BookFace detects incomplete/invalid index entered.
BookFace displays an example of a valid index.
System repeats Step 1-2 until valid information is entered.
```

```
Use case: UC3 - Edit Contact

Actor: User

MSS:
1. User chooses to edit a contact.
2. User enters contact's new details.
3. Contact is edited in BookFace.
Use case ends.

Extensions:

2a. BookFace detects incomplete/invalid details entered.
BookFace displays an example of valid details.
System repeats Step 1-2 until valid details are entered.
```

```
Use case: UC4 - Find Contact

Actor: User

MSS:
1. User chooses to find a contact.
2. User enters contact's name.
3. BookFace displays the matching contacts.
   Use case ends.

Extensions:

2a. BookFace detects no name entered.
BookFace displays an example of a valid name.
System repeats Step 1-2 until a name is entered.
```

```
Use case: UC5 - List Contacts

Actor: User

MSS:
1. User chooses to view all contacts.
2. BookFace displays all the user's contacts.
   Use case ends.
```

```
Use case: UC6 - Get Help on Commands

Actor: User

MSS:
1. User chooses to view instructions on how to use BookFace.
2. BookFace displays a url to its User Guide.
   Use case ends.
```

```
Use case: UC7 - Add Image for a Contact
Actor: User
MSS:
1. User chooses to add an image for a contact.
2. User enters contact index and image file path.
3. Image is added for contact.
   Use case ends.

Extensions:

2a. BookFace detects invalid/incomplete filepath or index.
BookFace displays an example of a valid filepath and index.
System repeats Step 1-2 until valid information is entered.
```

```
Use case: UC8 - Delete Image for a Contact

Actor: User

MSS:
1. User chooses to delete an image for a contact.
2. User enters contact index.
3. Image is deleted for contact.
   Use case ends.

Extensions:

2a. BookFace detects incomplete/invalid index entered.
BookFace displays an example of a valid index.
System repeats Step 1-2 until valid information is entered.

2b. BookFace detects that the contact does not have an image added.
Use case ends.
```

```
Use case: UC9 - Import Contacts from a Faculty

Actor: User

MSS:
1. User chooses to import contacts for a faculty.
2. User enters faculty name.
3. New contacts' details are added to BookFace.
   Use case ends.

Extensions:

2a. BookFace detects incomplete/invalid details entered.
BookFace displays an example of valid details.
System repeats Step 1-2 until valid details are entered.
```

### Glossary

* NUS: The National University of Singapore
* Student: A user who belongs to a faculty in NUS and attends one or more classes with other students
* Tutor: A user who tutors students in one or more classes
* Faculty: a group of university departments concerned with a major division of knowledge
* Contact: A person of interest, either student or tutor, who's details have been recorded by the user

