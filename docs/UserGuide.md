---
layout: page
title: User Guide
---

LE TRACKER is a gamified tracking application that allows fast typist to easily log their lecture progress, search for lecture by mod code/ keywords/ topics for a stress-free learning environment. Unlike todo list applications, LE TRACKER is tailored to the needs of students; it provides additional information specific to lecture media such as watch progress and topics.

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Quick Start](#quick-start)
  - [Nav](#nav)
  - [List](#list)
  - [Add](#add)
  - [Edit](#edit)
  - [Mark/Unmark](#markunmark)
  - [Delete](#delete)
  - [Tag](#tag)
  - [Find](#find)
  - [Clear](#clear)
- [Command Syntax](#command-syntax)
- [Argument Validity](#argument-validity)
- [Navigation](#navigation)
- [Features](#features)
  - [Navigate to the Root Context](#navigate-to-the-root-context)
  - [Navigate Relatively](#navigate-relatively)
  - [Navigate Directly](#navigate-directly)
  - [Navigate Backwards](#navigate-backwards)
  - [List Modules or Lectures or Videos](#list-modules-or-lectures-or-videos)
  - [List Modules](#list-modules)
  - [List Lectures of Modules](#list-lectures-of-modules)
  - [List Videos of Lectures](#list-videos-of-lectures)
  - [Add a Module](#add-a-module)
  - [Add a Lecture](#add-a-lecture)
  - [Add a Video](#add-a-video)
  - [Edit a Module](#edit-a-module)
  - [Edit a Lecture](#edit-a-lecture)
  - [Edit a Video](#edit-a-video)
  - [Mark or Unmark Video](#mark-or-unmark-video)
  - [Delete Module](#delete-module)
  - [Delete Lecture](#delete-lecture)
  - [Delete Video](#delete-video)
  - [Tag a module](#tag-a-module)
  - [Tag a lecture](#tag-a-lecture)
  - [Tag a video](#tag-a-video)
  - [Untag a module](#untag-a-module)
  - [Untag a lecture](#untag-a-lecture)
  - [Untag a video](#untag-a-video)
  - [Find Modules or Lectures or Videos](#find-modules-or-lectures-or-videos)
  - [Find Modules or Lectures or Videos By Tag](#find-modules-or-lectures-or-videos-by-tag)
  - [Find Lectures in a Module](#find-lectures-in-a-module)
  - [Find Lectures in a Module By Tag](#find-lectures-in-a-module-by-tag)
  - [Find Videos in a Lecture](#find-videos-in-a-lecture)
  - [Find Videos in a Lecture By Tag](#find-videos-in-a-lecture-by-tag)
  - [Clear](#clear-1)
  - [Note](#note)
  - [Warning](#warning)
  - [FAQ](#faq)

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Type the command in the command box. Use the :arrow_up: and :arrow_down: arrow to toggle through previous commands that you have inputted.

3. Press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

### Nav

- `nav`: Navigates to the top-most context (root)
- `nav {module_code / lecture_name}`: Navigates relative to the current context to a module or lecture context
- `nav /mod {module_code / lecture_name} [/lec {lecture_name}]`: Navigates directly to the specified module or lecture context
- `navb`: Navigates to the parent context of the current context

### List

- `list`: Lists all modules/lectures/videos based on context
- `list /r`: Lists all modules from any context
- `list [/mod {module_code}]`: Lists all the lectures in a specified module
- `list [/lec {lecture_name}]`: Lists all the videos in a navigated module and specified lecture (:exclamation: only works if you are in `module` context)
- `list [/mod {module_code} /lec {lecture_name}]`: Lists all the videos in a specified module and lecture

### Add

- `add {module_code} [/name {module_name}] [/tags {tag_1}, [{tag_2}, ...]]`: Adds a module to Le Tracker
- `add {lecture_name} [/mod {module_code}] [/tags {tag_1}, [{tag_2}, ...]]`: Adds a lecture to a module
- `add {video_name} [/mod {module_code}] [/lec {lecture_name}] [/timestamp {timestamp}] [/watch] [/tags {tag_1}, [{tag_2}, ...]]`: Adds a video to a lecture

### Edit

- `edit {module_code} [/code {updated_code}] [/name {updated_name}] [/tags {tag_1}, [{tag_2}, ...]]`: Edits the details of a module in Le Tracker
- `edit {lecture_name} [/mod {module_code}] [/name {updated_name}] [/tags {tag_1}, [{tag_2}, ...]]`: Edits the details of a lecture
- `edit {video_name} [/mod {module_code}] [/lec {lecture_name}] [/name {updated_name}] [/timestamp {updated_timestamp}] [/watch] [/unwatch] [/tags {tag_1}, [{tag_2}, ...]]`: Edits the details of a video

### Mark/Unmark

- `mark {video_name_1}[, {video_name_2}[, {video_name_3}[, ...]]] /mod {module_code} /lec {lecture_name}`: Marks video(s) as watched
- `unmark {video_name_1}[, {video_name_2}[, {video_name_3}[, ...]]] /mod {module_code} /lec {lecture_index}`: Unmarks video(s) as unwatched

### Delete

- `delete {module_code_1}[, {module_code_2}[, {module_code_3}[, ...]]]`: Deletes module(s) from Le Tracker
- `delete {lecture_name_1}[, {lecture_name_2}[, {lecture_name_3}[, ...]]] [/mod {module_code}]`: Deletes the specified lecture(s) from the specified module
- `delete {video_name_1}[, {video_name_2}[, {video_name_3}[, ...]]] [/mod {module_code}] [/lec {lecture_name}]`: Deletes the specified video(s) from the specified lecture from the specified module

### Tag

- `tag {module_code} /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`: Tags a module from Le Tracker
- `tag {lecture_name} [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`: Tags a lecture from
  a module
- `tag {video_name} [/lec {lecture_name}] [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`:
  Tags a video from a lecture
- `untag {module_code} /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`: Removes specified tags from a module
  from Le Tracker
- `untag {lecture_name} [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`: Removes the
  specified tags from a lecture
- `untag {video_name} [/lec {lecture_name}] [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`:
  Removes the specified tags of a video

### Find

- `find {keywords}`: Find all modules/lectures/videos based on context whose code/name (whichever applicable) starts with any of the keyword(s)
- `find {keywords} [/byTag]`: Find all modules/lectures/videos based on context whose tag list contains any tag that starts with any of the keyword(s)
- `find {keywords} [/mod {module_code}]`: Find all lectures in a specified module whose name starts with any of the keyword(s)
- `find {keywords} [/byTag /mod {module_code}]`: Find all lectures in a specifed module whose tag list contains any tag that starts with any of the keyword(s)
- `find {keywords} [/lec {lecture_name}]`: Find all videos in a specified lecture in navigated module whose name starts with any of the keyword(s) (:exclamation: only works if you are in `module` context)
- `find {keywords} [/byTag /lec {lecture_name}]`: Find all videos in a specified lecture in a navigated module whose tag list contains any tag that starts with any of the keyword(s) (:exclamation: only works if you are in `module` context)
- `find {keywords} [/mod {module_code} /lec {lecture_name}]`: Find all videos in a specified lecture in specified module whose name starts with any of the keyword(s)
- `find {keywords} [/byTag /mod {module_code} /lec {lecture_name}]`: Find all videos in a specified lecture in a specifed module whose tag list contains any tag that starts with any of the keyword(s)

### Clear

- `clear`: Clears all information from Le Tracker

Refer to the [Features](#features) below for details of each command.

---

## Command Syntax

**:information_source: The following are rules applicable to all commands:**

1. Words encapsulated in `{}` are the parameters to be supplied by the user.\
  e.g. in `add {module_code}`, `{module_code}` is a parameter which can be used as `add CS2040`.

2. Items in square brackets are optional.\
  e.g. `add {module_code} [/name {module_name}]` can be used as `add CS2040 /name Data Structures and Algorithms` or as `add CS2040`.

3. Named parameters can be specified in any order as long as it is after all unnamed parameters (if any).\
  e.g. `edit {module_code} /code {updated_code} /name {updated_name}` can be used as `edit CS2040 /code CS2040S /name DSAG` or as `edit CS2040 /name DSAG /code CS2040S`.

4. If a named parameter is expected only once in the command but the user specified it multiple times, only the last occurrence of the parameter will be taken.\
  e.g. `add {module_code} [/name {module_name}]` if used as `add CS2040 /name Data Structures and Algorithms /name DSAG`, `DSAG` will be used as the value of the `/name` parameter.

5. Extraneous parameters will be ignored.\
  e.g. `add {module_code} /name {module_name}` if used as `add CS2040 /name DSAG /foo bar`, the `/foo` parameter is ignored.

6. Arguments must be specified in the format `/{argument_name} {value}`, if the argument takes a value, or `/{argument_name}`, if the argument takes no value, and there must be a whitespace before `/{argument_name}`.

---

## Argument Validity
- **Module Code**\
  Module codes should begin with uppercase alphabet characters, followed by numeric characters, optionally followed by more uppercase alphabet characters.
- **Module Name**\
  Module names should only contain alphanumeric characters and spaces, and it can be blank.
- **Lecture Name**\
  Lecture names should only contain alphanumeric characters and spaces, and it should not be blank.
- **Video Name**\
  Video names should only contain alphanumeric characters and spaces, and it should not be blank.
- **Tag**\
  Tags should only contain alphanumeric characters, and it should not be blank.
- **Timestamp**\
  Timestamp should be of the format `HH:mm:ss` where `HH` is the number of hours, `mm` is the number of minutes, and `ss` is number of seconds, each integer being exactly 2 digits long.

---

## Navigation

---

## Features

### Navigate to the Root Context

> Sets the current context to the root context

Format: `nav`

### Navigate Relatively

> Navigates relative to the current context to a module or lecture context

Format: `nav {module_code / lecture_name}`

- `module_code` has to belong to an existing module that is a child of the current context
- `lecture_name` has to belong to an existing lecture that is a child of the current context

### Navigate Directly

> Navigates directly to the specified module or lecture context

Format: `nav /mod {module_code / lecture_name} [/lec {lecture_name}]`

- `module_code` has to belong to an existing module
- `lecture_name` has to belong to an existing lecture

### Navigate Backwards

> Navigates to the parent context of the current context

Format: `navb`

### List Modules or Lectures or Videos

> Root context: modules, Module context: lectures, Lecture context: videos

Format: `list`

### List Modules

> Lists all modules

Format: `list /r`

### List Lectures of Modules

> Lists all lectures belonging to a specified module code

Format: `list [/mod {module_code}]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)

Examples:

- `list /mod CS2040S` lists lectures belonging to CS2040S

### List Videos of Lectures

> Lists all videos belonging to a specified lecture code of a navigated/specified module code

Format:\
In module context: `list [/lec {lecture_name}]`\
In any context: `list [/mod {module_code} /lec {lecture_name}]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the module context (if any)
- `lecture_name` must belong to a lecture that exist within the module specified in `module_code`
- `lecture_name` if not specified, defaults to the name of the lecture in the current context (if any)

Examples:

- In module context of module `CS2040S`: `list /lec Week 1`
- In any context: `list /mod CS2040 /lec Week 1`

_\* Both commands lists videos that belongs to lecture `Week 1` in module `CS2040S`_

### Add a Module

> `add {module_code} [/name {name}] [/tags {tag_1}[, {tag_2}[, ...]]]`

Add a module to Le Tracker.

- <span style="color:#e46c0a">`module_code`</span> : The code of the module
  - Must be unique among the module code of the modules in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`module_name`</span> : The name of the module
  - Must be a valid module name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`tag_1, tag_2, ...`</span> : The tags to apply to the module
  - All tags must be valid (refer to [Argument Validity](#argument-validity) for more information)
  - Repeated tags (if any) will be ignored

Examples:

- `add CS2040 /name Data Structures and Algorithms /tags Heavy, Math, Analysis`

### Add a Lecture

> `add {lecture_name} /mod {module_code} [/tags {tag_1}[, {tag_2}[, ...]]]`

Add a lecture to a module.

- <span style="color:#e46c0a">`lecture_name`</span> : The name of the lecture
  - Must be unique among the names of the lectures belonging to the module specified in `module_code`
  - Uniqueness is case sensitive
  - Must be a valid lecture name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`module_code`</span> : The code of the module to add the lecture to
  - Must belong to an existing module in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
  - Might be automatically specified by the navigation system (refer to [Navigation](#navigation) for more information)
- <span style="color:#e46c0a">`tag_1, tag_2, ...`</span> : The tags to apply to the lecture
  - All tags must be valid (refer to [Argument Validity](#argument-validity) for more information)
  - Repeated tags (if any) will be ignored

Examples:

- `add Week 1 /mod CS2040S /tags Intro, Important`

### Add a Video

> `add {video_name} /mod {module_code} /lec {lecture_name} [/timestamp {timestamp}] [/watch] [/tags {tag_1}[, {tag_2}[, ...]]]`

Add a video to a lecture.

- <span style="color:#e46c0a">`/watch`</span> : If specified, the video will be marked as watched
  - An argument that takes no value
- <span style="color:#e46c0a">`video_name`</span> : The name of the video
  - Must be unique among the names of the videos belonging to the lecture specified in `lecture_name`
  - Uniqueness is case sensitive
  - Must be a valid video name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`module_code`</span> : The code of the module containing the lecture to add the video to
  - Must belong to an existing module in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
  - Might be automatically specified by the navigation system (refer to [Navigation](#navigation) for more information)
- <span style="color:#e46c0a">`lecture_name`</span> : The name of the lecture to add the video to
  - Must belong to an existing lecture in the module specified in `module_code`
  - Must be a valid lecture name (refer to [Argument Validity](#argument-validity) for more information)
  - Might be automatically specified by the navigation system (refer to [Navigation](#navigation) for more information)
- <span style="color:#e46c0a">`timestamp`</span> : The timestamp of the video where the user last stopped watching at
  - Must be a valid timestamp (refer to [Argument Validity](#argument-validity) for more information)
  - Defaults to `00:00:00` if not specified
- <span style="color:#e46c0a">`tag_1, tag_2, ...`</span> : The tags to apply to the video
  - All tags must be valid (refer to [Argument Validity](#argument-validity) for more information)
  - Repeated tags (if any) will be ignored

Examples:

- `add Video 1 /mod CS2040S /lec Week 1 /watch /tags Intro, Short`

### Edit a Module

> `edit {module_code} [/code {updated_code}] [/name {updated_name}] [/tags {tag_1}[, {tag_2}[, ...]]]`

Edit the details of a module.

- <span style="color:#e46c0a">`module_code`</span> : The code of the module to be edited
  - Must belong to an existing module in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`updated_code`</span> : The updated module code
  - Must be unique among the module code of the modules in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`updated_name`</span> : The updated module name
  - Must be a valid module name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`tag_1, tag_2, ...`</span> : The tags that will replace the current tags applied to the module
  - All tags must be valid (refer to [Argument Validity](#argument-validity) for more information)
  - Repeated tags (if any) will be ignored

Examples:

- `edit CS2040 /code CS2040S /name Data Structures and Algorithms /tags Heavy, Math, Analysis`

### Edit a Lecture

> `edit {lecture_name} /mod {module_code} [/name {updated_name}] [/tags {tag_1}[, {tag_2}[, ...]]]`

Edit the details of a lecture.

- <span style="color:#e46c0a">`lecture_name`</span> : The name of the lecture to be edited
  - Must belong to a lecture that exist within the module specified in `module_code`
  - Must be a valid lecture name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`module_code`</span> : The code of the module that contains the lecture to be edited
  - Must belong to an existing module in Le Tracker
  - Must be a valid module code (refer to [Argument Validity](#argument-validity) for more information)
  - Might be automatically specified by the navigation system (refer to [Navigation](#navigation) for more information)
- <span style="color:#e46c0a">`updated_name`</span> : The updated lecture name
  - Must be unique among the names of the lectures belonging to the module specified in `module_code`
  - Must be a valid lecture name (refer to [Argument Validity](#argument-validity) for more information)
- <span style="color:#e46c0a">`tag_1, tag_2, ...`</span> : The tags that will replace the current tags applied to the lecture
  - All tags must be valid (refer to [Argument Validity](#argument-validity) for more information)
  - Repeated tags (if any) will be ignored

Examples:

- `edit Week 1 /mod CS2040S /name Week 01 Introduction /tags Intro, Important`

### Edit a Video

> Edits the details of a video

Format: `edit {video_name} [/mod {module_code}] [/lec {lecture_name}] [/name {updated_name}] [/timestamp {updated_timestamp}] [/watch] [/unwatch] [/tags {tag_1}, [{tag_2}, ...]]`

- `/watch` if specified, then `/unwatch` cannot be specified
- `/unwatch` if specified, then `/watch` cannot be specified
- `video_name` must belong to a video that exist within the lecture specified in `lecture_name`
- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)
- `lecture_name` must belong to a lecture that exist within the module specified in `module_code`
- `lecture_name` if not specified, defaults to the name of the lecture in the current context (if any)
- `updated_name` must be a valid video name
- `updated_name` must be unique among the names of the videos belonging to the lecture specified in `lecture_name`
- `updated_timestamp` must be a valid timestamp
- `tag_1`, `tag_2`, ... must be valid tags
- `tag_1`, `tag_2`, ... if it contains repeated tags, the repeats will be ignored

Examples:

- `edit Video 1 /mod CS2040S /lec Week 1 /name Video 01 Grade Breakdown /watch /tags Intro, Short`

### Mark or Unmark Video

> Marks/Unmarks video(s) as watched/unwatched in a lecture of its specified module.

Format:

- `mark {video_name} /mod {module_name} /lec {lecture_index}`
- `unmark {video_name} /mod {module_name} /lec {lecture_index}`

Parameters:

- `mark` marks `{video_name}` as watched
- `unmark` marks `{video_name}` as unwatched
- `{video_name}` can be names of multiple videos, separated by commas (",")
- if `{video_name}` contains repeated names, the repeats will be ignored

Note: Calling mark or unmark would only prompt an error for already marked or unmarked videos if calling on a single video, not when calling on multiple videos in one command

- `video_name_1`, `video_name_2`, `video_name_3`, ...: Multiple videos can be specified to be marked/unmarked by specifying multiple video names, separating them by commas(",")
- Video Names must be of valid format
- If any video specified does not exist or has already been marked or unmarked (accordingly to the command called), nothing changes within the model

- `mark Vid 1 /mod CS2040 /lec Week 1`
- `mark Vid 1, Vid 2 /mod CS2040 /lec Week 1`
- `unmark Vid 2 /mod CS2040 /lec Week 1`
- `unmark Vid 1, Vid 2 /mod CS2040 /lec Week 1`

### Delete Module

> Deletes the specified module(s) and all its embodied content from the application

Format: `delete {module_code_1}[, {module_code_2}[, {module_code_3}[, ...]]]`

- `module_code_1`, `module_code_2`, `module_code_3`, ...: Multiple modules can be specified to be deleted by specifying multiple module codes, separating them by commas(",")
- Module codes must be of valid format
- If any module specified does not exist, nothing changes within the model

Examples:

- `delete CS2040`
- `delete CS2040, ST2334`

### Delete Lecture

> Deletes the specified lecture(s) and all its embodied content from the same specified module

Format: `delete {lecture_name_1}[, {lecture_name_2}[, {lecture_name_3}[, ...]]] [/mod {module_code}]`

- `lecture_name_1`, `lecture_name_2`, `lecture_name_3`, ...: Multiple lectures within the same module can be specified to be deleted by specifying their lecture names, separating them by commas(",")
- `module_code` must be of valid format and have a module of `module_code` exist in Le Tracker
- Lecture names must be of valid format
- If any lecture specified does not exist within specified module, nothing changes within the model

Examples:

- `delete lecture 1 /mod CS2040` deletes `lecture 1` lecture found in module `CS2040`
- `delete lecture 1, lecture 2 /mod ST2334` deletes `lecture 1` and `lecture 2` lectures found in module `ST2334`

### Delete Video

> Deletes the specified video(s) and all its embodied content from the same specified lecture of the specified module

Format: `delete {video_name_1}[, {video_name_2}[, {video_name_3}[, ...]]] /mod {module_code} /lec {lecture_name}`

- `video_name_1`, `video_name_2`, `video_name_3`, ...: Multiple videos within the same lecture of a module can be specified to be deleted by specifying their video names, separating them by commas(",")
- `module_code` must be of valid format and have a module of `module_code` exist in Le Tracker
- `lecture_name` must be of valid format and have a lecture of `lecture_name` exist in module of `module_code`
- Video names must be of valid format
- If any video specified does not exist within the specified lecture of the specified module, nothing changes within the model

Examples:

- `delete video 3 /mod CS2040 /lec lecture 1` deletes `video 3` from lecture `lecture 1` of module `CS2040`
- `delete video 1, video 3 /mod CS2040 /lec lecture 1` deletes `video 1` and `video 3` from lecture `lecture 1` of module `CS2040`

### Tag a module

> Tag a specified module from the current list of modules in Le Tracker with descriptions

Format: `tag {module_code} /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `tag_1, tag_2, ...` must be of correct format

Example:

- `tag EG2310 /tags fun, hard` tags the module `EG2310` with the tags `fun` and `hard`

### Tag a lecture

> Tag a specified lecture with descriptions

Format: `tag {lecture_name} [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)
- `lecture_name` must belong to an existing lecture in the specified module
- `tag_1, tag_2, ...` must be of correct format

Examples:

- `tag Lecture_1 /mod CS2040 /tags Yay` tags the lecture `Lecture_1` in the module `CS2040` with the tag
  `Yay`

### Tag a video

> Tag a specified video with descriptions

Format: `tag {video_name} [/lec {lecture_name}] [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)
- `lecture_name` must belong to an existing lecture in the specified module
- `lecture_name` if not specified, defaults to the lecture name of the module in the current context (if any)
- `video_name` must belong to an existing video in the specified lecture
- `tag_1, tag_2, ...` must be of correct format

Examples:

- `tag Video_1 /lec Lecture_1 /mod CS2040 /tags Yay` tags the video `Video_1` in the lecture `Lecture_1` of
  the module `CS2040` with the tag `Yay`

### Untag a module

> Remove specified tags from a specified module in the current list of modules in Le Tracker

Format: `untag {module_code} /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `tag_1, tag_2, ...` must belong to existing tags of the specified module

Example:

- `untag EG2310 /tags fun, hard` removes the tags `fun` and `hard` from the module `EG2310`

### Untag a lecture

> Untag a specified lecture from a specified module with a description

Format: `untag {lecture_name} [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)
- `lecture_name` must belong to an existing lecture in the specified module
- `tag_1, tag_2, ...` must belong to existing tags of the specified lecture

Examples:

- `untag Lecture_1 /mod CS2040 /tags Yay` removes the tag `Yay` from the lecture `Lecture_1` in the module `CS2040`

### Untag a video

> Remove specified tags from video

Format: `untag {video_name} [/lec {lecture_name}] [/mod {module_code}] /tags {tag_1}[, {tag_2}[, {tag_3}, ...]]]`

- `module_code` must belong to an existing module
- `module_code` if not specified, defaults to the module code of the module in the current context (if any)
- `lecture_name` must belong to an existing lecture in the specified module
- `lecture_name` if not specified, defaults to the lecture name of the module in the current context (if any)
- `video_name` must belong to an existing video in the specified lecture
- `tag_1, tag_2, ...` must belong to existing tags of the specified video

Examples:

- `untag Video_1 /lec Lecture_1 /mod CS2040 /tags Yay` removes the tag `Yay` in the video `Video_1` of the
  lecture `Lecture_1` that belongs to the module `CS2040`

### Find Modules or Lectures or Videos

> Find all modules/lectures/videos based on context whose code/name (whichever applicable) starts with any of the keyword(s)

Format: `find {keywords}`

Examples:

- In root level, `find CS2040S` searches for module `CS2040S` from the module list.
- In module level within `CS2040S`, `find week 1, week 2` searches for lectures `week 1` or `week 2` from the lecture list of module `CS2040S`.
- In lecture level within `week2` of `CS2040S`, `find vid1, vid2` searches for videos `vid1` or `vid2` from the video list of lecture `week2` of module `CS2040S`.

### Find Modules or Lectures or Videos By Tag

> Find all modules/lectures/videos based on context whose tag list contains any tag that starts with any of the keyword(s)

Format: `find {keywords} [/byTag]`

Assumption:\
Module `CS2040S` has tags `["heavy", 'math']`\
Lecture `Week 1` of `CS2040S` has tags `["Arrays", "Sorting"]`\
Video `Vid 1` of `Week 1` of `CS2040S` has tags `["content"]`

Examples:

- In root level, `find heavy /byTag` will show module `CS2040S` from the module list.
- In module level within `CS2040S`, `find array /byTag` will show lecture `Week 1` from the lecture list of module `CS2040S`.
- In lecture level within `Week 1` of `CS2040S`, `find cont /byTag` will show video `Vid 1` from the video list of lecture `Week 1` of module `CS2040S`.

### Find Lectures in a Module

> Find all lectures in a specified module whose name starts with any of the keyword(s)

Format: `find {keywords} [/mod {module_code}]`

- `module_code` must belong to an existing module

Examples:

- `find week 1, week 2 /mod CS2040S` searches for lectures `Week 1` or `Week 2` from the lecture list of module `CS2040S`.

### Find Lectures in a Module By Tag

> Find all lectures in a specifed module whose tag list contains any tag that starts with any of the keyword(s)

Format: `find {keywords} [/byTag /mod {module_code}]`

- `module_code` must belong to an existing module

Assumption:\
Module `CS2040S` has lecture `Week 1` which has tags `["array", 'sorting']`

Examples:

- `find intro, array /byTag /mod CS2040S` will show lecture `Week 1` from the lecture list of module `CS2040S`.

### Find Videos in a Lecture

> Find all videos in a specified lecture in a navigated/specified module whose name starts with any of the keyword(s)

Format:\
In module context: `find {keywords} [/lec {lecture_name}]`\
In any context: `find {keywords} [/mod {module_code} /lec {lecture_name}]`

- `module_code` must belong to an existing module
- `lecture_name` must belong to a lecture that exist within the module specified in `module_code`

Examples:

- In module context of module `CS2040S`: `find vid1, vid2 /lec Week 2`
- In any context: `find vid1, vid2 /mod CS2040S /lec Week 2`

_\* Both commands searches for videos `vid1` or `vid2` from the video list of lecture `Week 2` of module `CS2040S`_

### Find Videos in a Lecture By Tag

> Find all videos in a specified lecture in a navigated/specified module whose tag list contains any tag that starts with any of the keyword(s)

Format:\
In module context: `find {keywords} [/byTag /lec {lecture_name}]`\
In any context: `find {keywords} [/byTag /mod {module_code} /lec {lecture_name}]`

- `module_code` must belong to an existing module
- `lecture_name` must belong to a lecture that exist within the module specified in `module_code`

Assumption:\
Module `CS2040S` has lecture `Week 2` which has video `Vid 1` which has tags `["content"]`

Examples:

- In module context of module `CS2040S`: `find content /byTag /lec Week 2`
- In any context: `find content /byTag /mod CS2040S /lec Week 2`

_\* Both commands will show video `Vid 1` from the video list of lecture `Week 2` of module `CS2040S`_

### Clear

> Clears all information (modules, lectures, videos, tags) from Le Tracker

Format:

- `clear`

- any following term entered after `clear` is ignored
- calling `clear` will result in an empty Tracker

---

### Note

- Saving the data\
  Le Tracker data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

- Editing the data file\
  Le Tracker data are saved as a JSON file `[JAR file location]/data/letracker.json`. Advanced users are welcome to update data directly by editing that data file.

---

### Warning

:warning: If your changes to the data file makes its format invalid, Le Tracker will discard all data and start with an empty data file at the next run.

---

### FAQ

**Q**: How do I transfer my data to another Computer?\
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Le Tracker home folder.

---

<!-- TODO: Update this after user guide is finalised -->
<!-- ## Command summary

| Action            | Format, Examples                                                                                                                                      |
| ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add a Module**  | `add-module /code {module_code} [/name {module_name}]` / e.g., `add-module /code CS2040 /name Data Structures & Algorithms`                           |
| **Add a Lecture** | `add-lecture /module {module_code}` / e.g., `add-lecture /module CS2040`                                                                              |
| **Add a Video**   | `add-video /module {module_name} /lecture {lecture_index} /video {video_name}` / e.g., `add-video /module CS2040 /lecture 1 /video lecture-01-part-1` |
| **Tag a Lecture** | `tag /module {module_code} /lecture {lecture_id} /description {tag_description}` / e.g, `tag /module CS2040 /lecture 1 /description Boohoo`           |
| **Delete a Tag**  | `untag /module {module_code} /lecture {lecture_id} /tag {tag_id}` / e.g,  `untag /module CS2040 /lecture 1 /tag 1`                                    | --> |
