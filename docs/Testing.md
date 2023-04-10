---
layout: page
title: Testing guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Running tests

There are two ways to run tests.

* **Method 1: Using IntelliJ JUnit test runner**
    * To run all tests, right-click on the `src/test/java` folder and
      choose `Run 'All Tests'`
    * To run a subset of tests, you can right-click on a test package,
      test class, or a test and choose `Run 'ABC'`
* **Method 2: Using Gradle**
    * Open a console and run the command `gradlew clean test` (
      Mac/Linux: `./gradlew clean test`)

<div markdown="span" class="alert alert-secondary">:link: **Link**: Read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html) to learn more about using Gradle.
</div>

--------------------------------------------------------------------------------------------------------------------

## Types of tests

At the moment, we have only done some extent of **Unit Testing**. The
integration tests as well as hybrid tests are not done yet.

One example of this can be seen in
[`ItemTest`](../src/test/java/wingman/model/item/ItemTest.java).

--------------------------------------------------------------------------------------------------------------------

## Testing Standards

To help with testing, we used the package [Mockito](https://site.mockito.org/),
which makes it easier to create mock objects for testing. For the most part,
we mock the irrelevant objects and only test the relevant objects.
