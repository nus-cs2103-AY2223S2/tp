---
layout: page
title: Yip-Au Hew Kit, Shawn's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about (To be added) kLoC.

Given below are my contributions to the project.

* **New Feature**: store/retrieve new attributes(modules and skills) into/from a json file. 
    * What it does: allow users to keep track of all modules and skills associated to a person in a non-volatile way.
    * Justification: we design CoDoc with collaboration in mind and the ability to store these two attributes will help users find the right people to collaborate with.
    * Highlights: had to study how the codebase handles storing and revival of data and then emulate it for the two new fields while also making a stub as the modules and skills component were implemented in parallel.
* **New Feature**: Find skills and modules
    * What it does: Find all skills and modules of all person in the contact list
    * Justification: Mansually scrolling is too slow, need a faster way to find
    * Highlights: The decison between logicial and and or along with deadling with all possible inputs escpially for moduls where we give users the option to inclde the acadmeuc year along with how many modules that preceeds it and how many sets of this are allowed. as such multiple test case had to be implemeented to make sure that it works as expected.
* **New Feature**: Stackable predicates for find command
    * What is does: allows users to continue to search from the current list instead of a new list everytime
    * Justification: allows user to do searching 1 field at a time without the need to type in everything they want to find. Also, let them decide how to continue to search based on the results
    * Highlights: as this affects all find prefixs, had to search for a place to implement this functuoanlity that is independent from each of the find command as it would mean we will have to change it. In the end, I found that a class where all predicates are stored and simiply reduce them to a singular predicate
* **New Feature**: Image support for jar file
    * What is does: supports image when compress to jar file
    * Justification: image ups the visuals and the way jar file heirechy is different from our ddeveloplement.
    * Highlights: Had to learn how to use relative links instead of absolute

* **Code contributed**: insert link?

* **Project management**:
    * Merged PRs by teammates
    * Provided feedback on PRS
    * Assigned issues to teammates and myself
    * Reminded teammates about the deliverables every week

* **Enhancements to existing features**:
    * update parser to check for stuff
    * minor UI alignements
    * bug fixes

* **Documentation**:
    * User Guide:
        * Created tutorial section
        * Created addtional resources section
        * Help to doument find command
        * Tidy up and synchronose the UG to make it more readable
        * Fix bug reportede
    * Developer Guide:
        * Created sequence diagram for how data is save and retreive
        * Re-organise the UG to make it neater

