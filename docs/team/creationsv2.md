---
layout: page 
title: Jonathon Low Eng Kiat's Project Portfolio Page
---

### Project: HospiSearch

HospiSearch is a desktop app for healthcare administrators to manage hospital/clinic patients' particulars and optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

## Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added `nric` field in patient records. [#45](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/45)
    * What it does: Allows users to store `nric` of patients and uniquely identify them my `nric`.
    * Justification: This feature allows for easy unqiue identification of a patient.
  Previously uniqueness was checked by the `name` field, which is not ideal as two patients may have the same name.
    * Highlights: To prevent incorrect formats, the `nric` field is only able to take in `nric`s with valid
                  formats.


* **New Feature**: Added ability to filter by `tag` fields.
  [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86)
    * What it does: Allows users to be filtered by their current `tag`s.
    * Justification: This feature allows for easy filtering of patients by their conditions, represented by `tag`s.


* **New Feature**: Added `doctor` field in patient records.
[#121](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/121)
    * What it does: Allows users to be tagged to an attending `doctor`.
    * Justification: This feature allows for easy identification of patients by current attending `doctor`.
    * Highlights: Attending `doctor` is now shown beside each patient for quick relational view.


* **New Feature**: Added ability to filter by attending `doctor` field.
[#123](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/123)
    * What it does: Allows users to be filtered by their current attending `doctor`.
    * Justification: This feature allows for easy filtering of patients by current attending `doctor`.


* **Code contributed**:
[RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=Creationsv2&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Project Management**:
  * Organised and coordinated team meetings.
  * Published Releases `v1.2b`, `v1.3.trial`, `v1.3.1` and `v1.4`.
  * Set up and maintained team Organisation and Repo.
  * Used GitHub issue tracker.
  * Reviewed and approved PRs for merging.


* **Enhancements to existing features**:
  * Changed usage of `tag` classes to reflect patient conditions. [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86)
  * Wrote test cases for `nric` classes. [#45](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/45), [#71](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/71)
  * Wrote test cases for `doctor` classes. [#121](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/121)
  * Wrote test cases for `tag` and its related classes. [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86)


* **Documentation**:
  * User Guide:
    * Added documentation for changed usage of `tag` field and how to filter it. [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86)
    * Added documentation for the feature of the attending `doctor` field. [#121](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/121)
    * General formatting aesthetic changes/fixes. [#56](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/56), [#126](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/126)
    , [#127](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/127), [#128](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/128), [#142](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/142/files)
  * Developer Guide:
    * Added implementation details for the `nric` class. [#83](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/83)
    * Added implementation details for using `tag` as patient conditions. [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86/files)
    * Added local testing details for `add` command. [#110](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/110)
    * General formatting aesthetic changes/fixes. [#20](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/20), [#97](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/97)
    , [#126](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/126), [#214](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/214)


* **Documentation**:
  * PRs reviewed (with non-trivial comments): [#42](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/42), [#76](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/76), [#125](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/125)
  , [#131](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/131), [#134](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/134),
  * Created a total of [27](https://github.com/AY2223S2-CS2103T-T11-4/tp/issues?q=is%3Aissue+author%3ACreationsv2+) issues for GitHub issue tracker.
  * Reported a total of [12](https://github.com/Creationsv2/ped/issues) bugs and issues for PE-D.

