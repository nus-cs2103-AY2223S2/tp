[![Java CI](https://github.com/AY2223S2-CS2103T-F12-2/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2223S2-CS2103T-F12-2/tp/actions/workflows/gradle.yml)

![Ui](docs/images/Ui.png)

# 🧑‍💻 CoDoc

CoDoc is a desktop app for students in SoC (School of Computing) to connect with each other for the benefit of their course of study. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CoDoc can get your contact management tasks done faster than traditional GUI apps and networking with people can be easily done.

## 🚀 Installation
To install CoDoc, follow these steps:

1. Ensure that you have downloaded the latest version of CoDoc under Releases in GitHub.
2. Copy the jar file into an empty folder.
3. Open a command window in that folder.
4. Run the command java -jar CoDoc-{version}.jar e.g., java -jar CoDoc-v1.2.jar (i.e., run the command in the same folder as the jar file).

## 🕹️ Usage
Use the available commands to manage your contacts and connect with students in SoC.

### Command summary
Action | Format, Examples
| --- | --- |
Add	| `add n/NAME y/YEAR c/COURSE e/EMAIL [g/GITHUB] [l/LINKEDIN] [m/YEARTAKEN MODCODE]... [s/SKILL]...`<br />e.g., `add n/Bob Sim y/2 c/com sci e/e0823741@nus.edu l/linkedin.com/in/bom-sim-086g93847/ m/ay2223s2 cs2103t m/ay2223s2 cs2101 s/python s/java`
View contact | `view INDEX`<br />e.g., `view 3`
View tab | `view C/M/T`<br />e.g., `view c`, `view m` or `view t`
Edit | `edit [n/NAME] [y/YEAR] [c/COURSE] [e/EMAIL] [g/GITHUB] [l/LINKEDIN] [m/MODINDEX YEARTAKEN MODCODE]... [s/SKILL]...`<br />e.g., `edit n/Bob Lim m/2 ay2223s2 cs2109s`
Find	name | `find NAME [MORENAMES]`<br />e.g., `find Bob Sam Pete`
Find module | `findm MODCODE [MOREMODCODES]`<br />e.g., `findm cs2013t cs2109s cs2107`
Find skills | `finds SKILL [MORESKILLS]`<br />e.g., `finds java python` 
List | `view` the default list of all contacts
Delete | `delete INDEX`<br />e.g., `delete 3`
Clear all contacts | `clear`
Help | `help`

- Words in `UPPERCASE` are the parameters to be supplied by the user.<br />e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Bob Sim`.
- Items in square brackets are optional.<br />e.g., `n/NAME [s/SKILL]` can be used as `n/Bob Sim s/python` or as `n/Bob Sim`.
- Items with … after them can be used multiple times including zero times.<br />e.g., `[s/SKILL]…` can be used as ` ` (i.e. 0 times), `s/java`, `s/java s/python` etc.
- To execute View tab or Edit command, a contact must be loaded up at the side, these commands target the current person loaded.
- Parameters can be in any order.<br />e.g. if the command specifies `n/NAME y/YEAR c/COURSE`, `y/YEAR c/COURSE n/NAME` is also acceptable.
- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br />e.g. if you specify `c/com eng c/bza`, only `c/com eng` will be taken.
- Extraneous parameters for commands that do not take in parameters (such as `list`, `clear`, `help` and `exit`) will be ignored.<br />e.g. if the command specifies `help 123`, it will be interpreted as `help`.
- 💾 CoDoc data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

For more detailed usage instructions, check out our [User Guide](https://ay2223s2-cs2103t-f12-2.github.io/tp/UserGuide.html).

## 🤝 Contributing
We welcome contributions to CoDoc! To contribute, follow these steps:

1. Fork the CoDoc repository to your own GitHub account.
2. Create a new branch for your changes.
3. Make your changes and submit a pull request.

For the detailed documentation of this project, check out our [Developer Guide](https://ay2223s2-cs2103t-f12-2.github.io/tp/DeveloperGuide.html).

## 🙌 Contributors
CoDoc is developed and maintained by the following team of students from the School of Computing, National University of Singapore:
- An Cheng Yang [@anchengyang](https://github.com/anchengyang)
- Darie Chan Rong Zhi [@NappySprout](https://github.com/NappySprout)
- Nam Harin [@harin0826](https://github.com/harin0826)
- Tan Wei Shwin, Linus [@linustws](https://github.com/linustws)
- Yip-Au Hew Kit, Shawn [@ShawnYip-Au](https://github.com/ShawnYip-Au)

👋 Thank you for choosing CoDoc! We hope you find it useful and enjoyable to use.

_This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org)._




