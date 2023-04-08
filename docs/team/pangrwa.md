---
layout: page
title: Rui Wei's Project Portfolio Page
---

### Project: Advis.io

Advis.io (AIO) is a desktop app for managing clients information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit policies to a client.
  * What it does: Given the updated policy information, the user can edit a policy to the client selected.
  * Justification: This feature improves the product significantly because a user can now edit policies to a client to meet the needs of the client demands. Given that there are different financial cycles, it's always good to remain relevant by updating policies to the best policies available in the market.
  * Highlights: This enhancement was not particularly challenging because `Client` already has a policy list in it. I only created the necessary `Command` classes to modify the policies. This enhancement was inspired by `EditCommand` format. 
* **New Feature**: Added the ability to delete policies to a client.
  * What it does: The user can now delete the policy chosen that is attached to the client selected.
  * Justification: This feature improves the product significantly because a user can now delete policies that are not relevant to the client anymore, so that entire tracking process is neater.
  * Highlights: This enhancement was not challenging because `Client` already has a policy list in it, all I have to do is just create the necessary `Command` classes to modify the policies in-place. This enhancement was inspired by `EditCommand` format.
  * Highlights: This enhancement was not particularly challenging because `Client` already has a policy list in it. I only created the necessary `Command` classes to delete the policies. This enhancement was inspired by `DeleteCommand` format.
* **New Feature**: Added the ability to add appointment to a client.
  * What it does: Given appointment details, the user can now add an appointment with his or her client.
  * Justification: This features improves the product significantly because a user can now add appointments to meet his or her clients.
  * Highlights: The enhancement was rather challenging because I had to modify the `Client` constructor to take in an `Appointment` class, this meant that I had to refactor the entire codebase. Also, since `Appointment` could be empty, I had to find a way to modify `Appointment` such that it can represent an empty appointment.
* **New Feature**: Added the ability to remove an appointment to a client.
  * What it does: Once the user meets up with the client, he or she can remove the appointment that was set for.
  * Justification: Past appointments should be removed from the application to make it neater.
  * Highlights: The enhancment was not particularly challenging as was inspired by `DeleteCommand`.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=pangrwa&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3.trial`, `v1.3.1` and `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated `Client` to be able to receive `Appointments`.
  * Modified `JsonAdaptedClient` to take in `Policy` and `Appointment` by creating `JsonAdaptedPolicy` and `JsonAdaptedAppointment` respectively.
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * `How To Get Started`: Added screenshots for non-technical users who aren't familiar with terminal. 
    * Refactored most `AddressBook` to `Clientele` to suit the context of the product, however `addressbook.json` remains the same but we have explained he term in the glossary
    * Added documentation for the features `editPolicy`, `deletePolicy`, `addApt` and `deleteApt`.
    * Handle the FAQ section of the UG.
  * Developer Guide:
    * Updated most of the `UML` diagrams in `Model` and `Storage` to suit the context of our product. 
    * Added implementation details of the `deletePolicy`, `editPolicy`, `addApt` and `deletApt` feature.
    * Added sequence diagrams for `deletePolicy` and `addApt` to guide the reader.
    * Adjusted most of the user stories and glossary

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
