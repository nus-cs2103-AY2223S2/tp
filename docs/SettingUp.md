---
layout: page
title: Setting up and getting started

---

## Setting Up, Getting Started

### Cloning the repo

First, fork [the repo](https://github.com/AY2223S2-CS2103T-W14-1/tp), and clone
the fork into your local machine.

If you plan to use Intellij IDEA:

1. **Configure the JDK**: Follow the guide [_[se-edu/guides] IDEA: Configuring
   the JDK_](https://se-education.org/guides/tutorials/intellijJdk.html) to
   ensure Intellij is configured to use **JDK 11**.
1. **Import the project as a Gradle project**: Follow the guide
   [_[se-edu/guides] IDEA: Importing a Gradle
   project_](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)
   to import the project into IDEA.<br> :information_source: Note: Importing a Gradle
   project is slightly different from importing a normal Java project.

If you plan to use other development environments, please seek out the
appropriate guides on setting up JDK 11 and Gradle.

Now, assuming you have successfully set up the project, let us verify that the
code works as intended. In a terminal, navigate to the directory where you
cloned the repo and execute the following command: `./gradlew test`. This will
run all of Mycelium's automated tests. If you don't see any error messages
printed, then you're all set.

<div markdown="span" class="alert alert-success">
:bulb: Did `./gradlew test` not work for you? Depending on your operating
system and shell, there might be different ways to run the Gradle wrapper file.
We advise you to 1) check that the file has execute permissions, and/or 2)
check [Gradle's
website](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper)
for more information. Note that this may apply in other places in this guide
where Gradle commands are used.
</div>

### Before writing code

1. **Configure the coding style**

   If using IDEA, follow the guide [_[se-edu/guides] IDEA: Configuring the code
   style_](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to
   set up IDEA's coding style to match ours.

1. **Set up CI**

   This project comes with a GitHub Actions config files (in
   `.github/workflows` folder). When GitHub detects those files, it will run
   the CI for your project automatically at each push to the `master` branch or
   to any PR. No set up required.

1. **Learn the design**

   When you are ready to start coding, we recommend that you get some sense of
   the overall design by reading about [Mycelium’s
   architecture](#architecture).
