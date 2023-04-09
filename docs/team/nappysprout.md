---
layout: page
title: Darie Chan Rong Zhi's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20.8k LoC.

Given below are my contributions to the project.

* **New Feature**: Module
    * What it does: Provide Module field for Person in Codoc
    * Justification: Codoc is an address book for Students and Module is an important aspect of student
    * Highlights: The regex for Module was a little complicated and different errors need to show up depending which part of module is wrong during input

* **New Feature**: Course
  * What it does: Provide Course field for Person in Codoc
  * Justification: Codoc is an address book for Students and Course is an important aspect of student

* **New Feature**: Year
  * What it does: Provide Year field for Person in Codoc
  * Justification: Codoc is an address book for Students and Year is an important aspect of student

* **Code contributed**: I implemented the basic structure of Module, Course and Year into Person class. 
I had to change files from many directories just to implement it such as making sure storage class expects that field, 
parser knowing the particular prefix for the field and also initialising and error catching when initialising the attributes. 
After implementing these attributes, my teammates were then able to put it into the UI and implement more complex logic like the find command on the fields I added.

* **Project management**: I was able to merge PRs of my teammates and even spot errors which might be bad for the project before they are merged into master branch.
I was able to set up the organisation, create the repository and gave my teammates access as well. 

* **Enhancements to existing features**:
    * Extended Person class to include more attributes
    * Modified Storage class to account for new attributes
    * Modify add command to include the new attributes
    * Modify find command to include the new attributes

* **Documentation**:
    * User Guide:
        * Fix github issues pertaining to confusing layout in UserGuide
    * Developer Guide:
        * Explanation of Module Class
        * Tidied up table of content links and navigation and ensure their proper function

* **Tools**:
    * JavaFX
    * Jackson
    * JUnit5
