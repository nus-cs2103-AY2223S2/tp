---
layout: page
title: Douglch's Project Portfolio Page
---

### Project: Mycelium

**Mycelium** is a desktop application aimed at helping **freelance web developers
manage clients and projects** from multiple online sources like [Upstack](https://upstackhq.com/),
[Fiverr](https://www.fiverr.com/), and [Toptal](https://www.toptal.com/). All interactions
with **Mycelium** are done through text commands or HotKeys, enabling one to efficiently
manipulate data while availing oneself to the convenience of viewing, offered by the
Graphical User Interface (GUI) created with JavaFX. The application is written in Java.

Below are the contributions I have made to the project:
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=douglch&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Douglch&tabRepo=AY2223S2-CS2103T-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**: I implemented the `Client` entity of the Mycelium project, and ensured that a client's
attributes are compatible with the Jackson JSON library for storage and retrieval. [JsonAdaptedClient](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/storage/JsonAdaptedClient.java)
* Additionally, I have designed and implemented the
client-related parser and command classes necessary for users to perform a range of client-related actions, including
editing clients: [Client](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/client/Client.java), [UpdateClientCommand](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/logic/commands/UpdateClientCommand.java), [UpdateClientCommandParser](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/logic/parser/UpdateClientCommandParser.java)
* To ensure the quality and functionality of the Client entity and its associated classes,
I have also written a comprehensive set of test cases for the `Client` entity and its related classes: [UpdateClientCommandTest](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/test/java/mycelium/mycelium/logic/commands/UpdateClientCommandTest.java), [UpdateClientCommandParserTest](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/test/java/mycelium/mycelium/logic/parser/UpdateClientCommandParserTest.java), [JsonAdaptedClientTest](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/test/java/mycelium/mycelium/storage/JsonAdaptedClientTest.java)
* Note: More on what else I've written can be found in the RepoSense link above.

* **Documentation**:
  * **Contributions to UG**: I wrote the User Guide for the `Client` entity, including the `add`, `delete`, `edit`, `find`, under the `Managing Clients` section.
  * **Contributions to DG**: I documented the sections under `Architecture`, `Model`, and `Storage`.

* **Code Reviews**:
I have provided code reviews to the following PRs (Non-exhaustive):
    * [#59](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/59)
    * [#158](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/158)
    * [#26](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/26)
    * [#23](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/23)
    * [#98](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/98)
