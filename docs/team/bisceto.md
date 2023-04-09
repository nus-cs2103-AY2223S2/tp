---
layout: page
title: Teo Yu Xiang Wesley's Project Portfolio Page
---

### Project: Well Informed Fridge Environment (WIFE)
Enhance life with WIFE! Our product Well Informed Fridge Environment &lt;WIFE/&gt; helps users to manage their 
items in the fridge. With this, one never has to worry about optimizing storage and organisation of food items in their
refrigerator!

Given below are my contributions to the project.

* **New Feature**: Added the ability to increase/decrease the quantity of a food item.

    - What it does: It allows the user to increase or decrease the quantity of a food item by a specific quantity. If no
quantity is specified, it will increase/decrease the quantity of the specified food item by 1.

    - Justification: This feature provides a shorthand command for users to change the quantity of a food item.
Originally, if a user wishes to change the quantity, they would use the edit command to update the new quantity.
However in reality, this may not be convenient as users will have to calculate the new end quantity of a food item.
In addition, most of the time they only wish to change the quantity by 1. This command eases convenience for users by simply
stating the quantity they wish to increase/decrease by.

    - Difficulty: The nature of this command is similar to the edit command, with the difference being that this command only considers the 
`Quantity` of a `Food`, thus its implementation was relatively similar as well. However, additional checks and test cases were written e.g.
ensuring that the value to decrease by was not greater than the current `Quantity`.
<br/>


* **Enhancements to existing features**: Finding a food item by its name.
    - What it does: Originally, the find feature implemented by AB3 needed the name of a person to match the keyword exactly.
      Now, the name of the food item just needs to contain the keyword for it to appear in the filtered list.

    - Justification: The need for the name of the food item to exactly match the keyword inputted by the user is too restrictive.
      The user may not remember the exact name or spelling of the food item that they are looking for, thus editing the feature for
      the name to simply contain the keyword is more flexible and user-friendly, enhancing the user experience.

<br/>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jnjy&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed documentation formatting and content structure for WIFE, including the User Guide and Developer Guide.
    * Reviewed all PRs that involved editing of UG and DG e.g. diagrams and feature implementations.

<br/>

* **Documentation**:
    * User Guide:
        * Added documentation for the features `inc` and `dec` [[PR#175]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/175)
        * Added table of contents and command summary [[PR#186]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/186)
        * Fixed UG issues from PE-D [[PR#280]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/280)
        * 
        * Finalised formatting issues and structure of UG

      * Developer Guide:
        * Added documentation for non-functional requirements (NFR)
        * Added use case when user wants to view help
        * Added documentation for user stories
        * Added implementation details and diagrams for features `inc` and `dec` [[PR#171]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/171)
        * Added documentation for manual testing [[PR#297]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/297)
        * Finalised formatting issues and structure of DG

<br/>

-   **Testing**:

    -   Added tests for `IncreaseCommand` and `IncreaseCommandParser` [[PR#160]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/160) [[PR#290]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/290)
    -   Added tests for `DecreaseCommand` and `DecreaseCommandParser` [[PR#290]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/290)
    -   Edited tests for `Find` [[PR#278]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/278)

<br/>

* **Community**:
    * PRs reviewed: [[PR#38]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/38) [[PR#43]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/43)
[[PR#56]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/56) [[PR#61]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/61)
[[PR#62]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/62) [[PR#63]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/63)
[[PR#64]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/64) [[PR#160]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/160)
[[PR#161]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/161) [[PR#163]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/163)
[[PR#164]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/164) [[PR#166]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/166)
[[PR#168]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/168) [[PR#171]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/171)
[[PR#172]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/172) [[PR#174]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/174)
[[PR#180]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/180) [[PR#188]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/188) 
[[PR#279]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/279) [[PR#282]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/282) 
[[PR#283]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/283) [[PR#284]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/284)
[[PR#285]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/285) [[PR#286]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/286)
[[PR#292]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/292) [[PR#294]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/294)
[[PR#295]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/295) [[PR#296]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/296)
[[PR#298]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/298) [[PR#301]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/301)
[[PR#302]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/302) [[PR#303]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/303) 
[[PR#304]](https://github.com/AY2223S2-CS2103T-T11-1/tp/pull/304)

