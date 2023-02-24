[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/se-edu/addressbook-level3/actions)

# The Intern's Ship

The Intern’s Ship (TinS) is a desktop app for managing internships application, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TinS can help you manage and keep track of your internship applications faster than traditional GUI apps.

### Features (v1.2)
* Adding an internship application: add
* Listing all the company and position of the application : list
* Locating internship by name: view
* Deleting an internship : delete
* Saving the data

### Adding an internship : `add`

Adds an internship and its details to TinS

Format: `add POSITION`
```
add software engineer
INPUT COMPANY_NAME: COMPANY_NAME
INPUT APPLICATION STATUS: APPLICATION_STATUS
INPUT CONTACT DETAILS: [CONTACT_DETAILS]
```

* `POSITION`: Name of Internship Position
* `COMPANY NAME` : Name of hiring company
* `APPLICATION_STATUS` : Status of Application (`ACCEPTED`, `APPLIED`, `PENDING`, `REJECTED`)
* `CONTACT DETAILS` : Contact details of hiring manager (optional)
* After keying in the  add command, the user will be prompted with these fields:
  * `COMPANY_NAME`
  * `APPLICATION_STATUS`
  * `CONTACT_DETAILS (optional)`

Example:
```
add software engineer
INPUT COMPANY_NAME: Google
INPUT APPLICATION STATUS: applied
INPUT CONTACT DETAILS: BobTheManager@gmail.com
```
