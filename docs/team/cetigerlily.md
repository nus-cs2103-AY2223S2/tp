---
layout: page
title: Celeste Cheah's Project Portfolio Page
---

### Project: Wingman

Wingman is an application designed to help airline managers efficiently manage the allocation of their resources.
Given below are my contributions to the project.

* **Code Contribution**:
    * My code contribution to the Wingman project can be found at this [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cetigerlily&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).


1. Handled general implementation for Plane ([#60](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/60), [#77](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/77))
    * Responsible for implementing the Plane model in Wingman along with all the basic functions related. This includes:
      adding and deleting a Plane, linking or unlinking a Plane to or from a Flight, and handling the storage and the 
      parsing of a Plane.

    * **What it does:** Allows for a Plane to be properly managed in the Wingman app.
    * **Justification:** It includes all the necessary commands which a manager would need to manage the planes in their
      fleet eg. they can add a new plane, and delete planes which are out of service. 


2. Added an `isAvailable` attribute for Crew, Pilot, and Plane ([#100](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/100))
    * **What it does:** Allows for Crew, Pilot, and Plane to be marked as available or unavailble, depending on whether 
      they are linked to a Flight. This feature works with the `linkflight` command ie. when any of the resources are linked
      to a flight, they automatically become unavailable.
    * **Justification:** This feature is essential for airline managers as their scheduling depends on the
      availability of their resources. With the`check` command, airline manager's no longer have to look through the list of
      resources - thereby, making it easier for them to check a resource's availability.


3. Implemented the `check` command for Crew, Pilot, and Plane ([#100](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/100))
    * **What it does:** Check the availability of a Crew, Pilot, or Plane.
    * **Justification:** With the`check` command, airline manager's no longer have to look through the list of resources
      , making it easier for them to check a specific resource's availability.

**Note:** This feature was later removed, unfortunately, as we were not able to update our `linkflight` logic to
accommodate for the problems which arose with the `check` command. 


4. Added better error messaging across all commands ([#140](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/140))
    * Added better error messages across all commands in Wingman and created new errors to be thrown when 
     needed (eg. a non-numeric value is entered although a numeric value is needed, an error message would be thrown).
   * **What it does:** Commands return more appropriate/helpful error messages when exceptions are thrown.
   * **Justification:** Better error messages tell users what exactly is wrong with the input they've given, which helps
     them. Additionally, it allows for Wingman to run smoother as Wingman knows how to deal with errors which are expected
     to be encountered.


5. Edited prefixes used in all commands ([#130](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/130))
    * Edited the previously used prefixes for commands, such that they would be streamlined across all commands.
    * **Justification:** Makes Wingman more consistent and hence, easier for users to learn and use.


* **Project Management**:
  * Recorded meeting minutes during weekly group meetings 
  * Managed releases `v1.3` - `v1.3.1` on GitHub


* **Community**:
    * PRs reviewed (with non-trivial review comments): (examples: [#125](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/125), [#133](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/133), [#145](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/145))

[//]: # (    * Reported bugs and suggestions for other teams in the class &#40;examples: [1]&#40;&#41;, [2]&#40;&#41;, [3]&#40;&#41;&#41;)


* **Enhancements to Existing Features**:
TBC

* **Documentation**:
    * User Guide:
        * Added input/output examples for commands and a complete prefix summary table ([#135](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/135))
    * Developer Guide:
        * Added user stories and use cases related to Plane ([#28](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/28))
        * Added documentation for `delete` feature, focusing on the logic layer, and created a sequence diagram of the 
          deletion process ([#111](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/111))