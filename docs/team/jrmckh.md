---
layout: page
title: Chia Jeremy's Project Portfolio Page
---

## <span style="color:lightblue">Overview</span>

<span style="color:lightblue">Project: EZ-Schedule</span>

EZ-Schedule is a desktop application designed to manage and schedule events with ease.
It features a robust Command Line Interface (CLI) that allows users to interact with it seamlessly,
and a sleek Graphical User Interface (GUI) built with JavaFX that enhances user experience.
Written in Java, this application boasts of approximately 8 kLoC
(check it out [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=w17-3&breakdown=true))
making it a reliable and efficient tool for organizing events.

## <span style="color:lightblue">Summary of Contributions</span>

* [Code contributed](#span-namecode-contributed-stylecolorlightblue-code-contributed-span)
* [New features](#span-namenew-features-stylecolorlightblue-new-features-span)
* [Enhancements to existing features](#span-stylecolorlightblue-enhancements-to-existing-features-span)
* [Documentations](#span-stylecolorlightblue-documentations-span)
* [Reviews / Mentoring](#span-stylecolorlightblue-reviews--mentoring-span)

### <span name="code-contributed" style="color:lightblue">Code contributed</span>

* Contributed to over 5 kLoC. Check it
  out [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jrmckh&breakdown=true).

### <span name="new-features" style="color:lightblue">New Features</span>

1. Calendar GUI

- What it does:
    - This feature provides users with a quick overview of all events within a month.
    - Users can navigate through different months to view all events within that period.
    - The overview displays the name of each event, which allows users to quickly identify busy or free periods.
- Justification:
    - By providing users with a clear and organized view of all scheduled events, this feature significantly enhances
      the user experience and boosts productivity.
- Highlights:
    - This enhancement affects both existing commands and commands that may be added in the future.
    - Any actions related to events, such as adding, deleting, or editing an event, will impact the events that appear
      in the calendar.
- Credits:
    - The calendar GUI is inspired from [here](https://gist.github.com/Da9el00/f4340927b8ba6941eb7562a3306e93b6).
      I have further enhanced it by incorporating more code abstractions, including separate class and FXML files for
      the calendar and its boxes.
      Additionally, it now supports highlighting of boxes when a `find` command is executed, and is compatible with
      other commands at runtime.

2. Delete command

- What it does:
    - This feature allows the user to delete an event or multiple events at a time.
- Justification:
    - The ability to delete events either in bulk or individually as needed ensures that their calendar remains accurate
      and up-to-date, while saving users valuable time.

3. Find command

- What it does:
    - This feature provides users with a search functionality that enables them to quickly find events by name, date, or
      a combination of both.
    - Once the search is executed, this feature highlights all the events that match the search criteria within the
      calendar GUI.
- Justification:
    - The ability to quickly search for events by name or date saves users valuable time and ensures that they can
      easily access the information they need.

### <span style="color:lightblue">Enhancements to existing features</span>

- Updated the GUI's components and color scheme:
  [#94](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/94),
  [#98](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/98)
- Wrote additional tests for existing features/classes:
  [#180](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/180),
  [#181](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/181),
  [#182](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/182),
  [#186](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/186),
  [#196](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/196),
  [#198](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/198),
  [#200](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/200)
- Wrote tests for new features/classes:
  [#190](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/190),
  [#194](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/194),
  [#201](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/201)

### <span style="color:lightblue">Documentations</span>

- #### User Guide:
    - Added documentation for the features `edit`, `delete`, `find`, and `GUI`
      [#103](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/103)
- #### Developer Guide:
    - to be added soon

### <span style="color:lightblue">Reviews / Mentoring</span>

[pr-reviewed-v1.1]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh+milestone%3Av1.1
[pr-reviewed-v1.2]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh+milestone%3Av1.2
[pr-reviewed-v1.3]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh+milestone%3Av1.3
[pr-reviewed-v1.3b]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh+milestone%3Av1.3b+
[pr-reviewed-v1.4]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh+milestone%3Av1.4
[pr-reviewed-total]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajrmckh

| Milestone |                   PRs reviewed                    |
|-----------|:-------------------------------------------------:|
| v1.1      |              [1] [pr-reviewed-v1.1]               |
| v1.2      |              [2] [pr-reviewed-v1.2]               |
| v1.3/b    | [9] [pr-reviewed-v1.3] + [11] [pr-reviewed-v1.3b] |                                                                               
| v1.4      |              [16] [pr-reviewed-v1.4]              |                                                                             
| Total:    |             [44] [pr-reviewed-total]              |
