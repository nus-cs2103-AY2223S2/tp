---
layout: page
title: Teo Yu Xiang Wesley's Project Portfolio Page
---

### Project: Well Informed Fridge Environment (WIFE)
Enhance life with WIFE! Our product Well Informed Fridge Environment &lt;WIFE/&gt; helps users to manage their 
items in the fridge. With this, one never has to worry about optimizing storage and organization of food items in their
refrigerator!

Given below are my contributions to the project.

* **New Feature**: Added the ability to increase/decrease the quantity of a food item.

    - What it does: It allows the user to increase or decrease the quantity of a food item by a specific quantity. If no
quantity is specified, it will increase/decrease the quantity of the specified food item by 1.

    -   Justification: This feature provides a shorthand command for users to change the quantity of a food item.
Originally, if a user wishes to change the quantity, they would use the edit command to update the new quantity.
However in reality, this may not be convenient as users will have to calculate the new end quantity of a food item.
In addition, most of the time they only wish to change the quantity by 1. This command eases convenience for users by simply
stating the quantity they wish to increase/decrease by.

<br/>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jnjy&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed documentation formatting and content structure for WIFE, including the User Guide and Developer Guide. 

<br/>

* **Enhancements to existing features**: Finding a food item by its name.
    - What it does: Originally, the find feature implemented by AB3 needed the name of a person to match the keyword exactly.
      Now, the name of the food item just needs to contain the keyword for it to appear in the filtered list.

    - Justification: The need for the name of the food item to exactly match the keyword inputted by the user is too restrictive.
      The user may not remember the exact name or spelling of the food item that they are looking for, thus editing the feature for
      the name to simply contain the keyword is more flexible and user-friendly, enhancing the user experience.

<br/>

* **Documentation**: *to be edited*
    * User Guide:
        * Added documentation for the features `inc` and `dec` [[PR#175]]
        * Added table of contents and command summary [[PR#186]]
        * Fixed UG issues from PE-D [[PR#280]]

      * Developer Guide:
        * Added documentation for non-functional requirements (NFR)
        * Added use case when user wants to view help
        * Added documentation for user stories
        * Added implementation details and diagrams for features `inc` and `dec` [[PR#171]]

<br/>

-   **Testing**:

    -   Added tests for `IncreaseCommand` and `IncreaseCommandParser` [[PR#160]] [[PR#290]]
    -   Added tests for `DecreaseCommand` and `DecreaseCommandParser` [[PR#290]]  
    -   Edited tests for `Find` [[PR#278]]

<br/>

* **Community**:
    * PRs reviewed: [[PR#38]] [[PR#43]] [[PR#56]] [[PR#61]] [[PR#62]] [[PR#63]] [[PR#64]] [[PR#160]] [[PR#161]] [[PR#163]]
      [[PR#164]] [[PR#166]] [[PR#168]] [[PR#171]] [[PR#172]] [[PR#174]] [[PR#180]] [[PR#188]] [[PR#279]] [[PR#282]] [[PR#283]]
      [[PR#284]] [[PR#285]] [[PR#38]] [[PR#286]] [[PR#292]]
