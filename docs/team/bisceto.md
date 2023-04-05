---
layout: page
title: Teo Yu Xiang Wesley's Project Portfolio Page
---

### Project: Well Informed Fridge Environment (WIFE)
Enhance life with WIFE! Our product Well Informed Fridge Environment &lt;WIFE/&gt; helps users to manage their 
items in the fridge. With this, one never have to worry about optimizing storage and organization of food items in their
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
        * Added documentation for the features `inc` and `dec` (Pull Request [[PR#175]])

      * Developer Guide:
        * Added documentation for non-functional requirements (NFR)
        * Added use case when user wants to view help
        * Added documentation for user stories

<br/>

-   **Testing**:

    -   Added test for `IncreaseCommand` (Pull Request [[PR#160]]) 
    -   Added test for `DecreaseCommand` (Pull Request [[PR#177]]) 
    -   Edited test for `Find` (Pull Request [[PR#278]])

<br/>

* **Community**:
    * PRs reviewed: [[#PR38]] [[#PR43]] [[#PR56]] [[#PR61]] [[#PR62]] [[#PR63]] [[#PR64]] [[#PR160]] [[#PR161]] [[#PR163]]
      [[#PR164]] [[#PR166]] [[#PR168]] [[#PR171]] [[#PR172]] [[#PR174]] [[#PR180]] [[#PR188]] [[#PR279]] [[#PR282]] [[#PR283]]
      [[#PR284]] [[#PR285]] [[#PR38]] [[#PR286]] 
