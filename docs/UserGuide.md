---
layout: page
title: User Guide
---

AutoM8 is a **desktop app for an auto repair shop, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AutoM8 can get your auto repair shop management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `autom8.jar` from [here](https://github.com/AY2223S2-CS2103-W17-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for this application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar autom8.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
    [UI image to be added]
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add appointment c/CUSTOMER_ID d/DATE t/TIME`, `CUSTOMER_ID, DATE and Time` are parameters which can be used as `add appointment c/2 d/02/02/2023 t/2pm`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

---

### Add

### Adding a customer: `add customer`
Adds a customer to the system and assigns a unique customer ID.

Format: `addcustomer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAGS]`

Example: `addcustomer n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Adding a technician: 'add technician'
Adds a technician to the system and assigns a unique staff ID.

Format: `addtechnician n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAGS]`

Example: `addtechnician n/Robert Low p/90253789 e/roblow@gmail.com a/25 Bedok East Rd, #08-20, SG405100 t/Leader`

### Adding a vehicle: `add vehicle`
Adds a vehicle of specified type (i.e. motorbike, car) to the system and assigns a unique vehicle ID.

Format: `addvehicle p/PLATE_NUMBER b/BRAND c/CUSTOMER_ID t/TYPE`

Example: `addvehicle p/SBA1234A b/Toyota c/1 t/car`

### Adding a vehicle part: `add part`
Adds a vehicle part to the system.

Format: `addpart n/NAME q/Quantity`

Example: `addpart n/Cylinder Head q/50`

### Adding a service to a vehicle: `add service`
Adds a service to perform on the specified vehicle plate number.

Format: `addservice v/VEHICLE_ID [l/SERIVCE LENGTH (days)] [s/STATUS] [d/DESCRIPTION]`

Examples:
* `addservice v/10 s/in progress d/Customer says abc`
* `addservice v/10 t/standard l/10` \
Note: Adding service without specifying the type of service will default to “to repair” \
Note: Adding service without specifying the service length will default to 7 days

### Adding a customer appointment: `add appointment`
Adds a customer appointment to the system.

Format: `addappointment c/CUSTOMER_ID d/DATE t/TIME`

Example: `addappointment c/5 d/05/03/2023 t/5pm`

---
### List/Sort

### Listing all vehicles/customers/parts/appointments: `list`
Shows all vehicles/customers/parts/appointments.

Format: `list (vehicles/customers/parts/appointments)`

Examples:
* `listvehicles`
* `listcustomers`
* `listparts`
* `listappointments`

Shows a list of all persons in the address book.

### Sorting displayed list: `sort`
Sorts all vehicles/customers/parts/appointments list in ascending or descending direction by a specific param. \
*Note: Command is context-sensitive (i.e. can only be used after list command)*

Format: `sort by/BRAND d/DIRECTION`

Example: `sort by/brand d/asc`

---
### Find

### Finding specific vehicles/customers/parts/appointments: `find`

Finds vehicles/customers/parts/appointments that contain any of the given keywords. This does not return details regarding the searched term, only returns information useful for other commands such as view, delete etc.
* Vehicle - can find by brand, plate number and customer id
* Customer - can find by name and customer id
* Part - can find by name
* Appointment - can find by customer id and date

Format: `find (vehicle/customer/part/appointment) KEYWORD`

Examples:
* `findvehicle toyota`
* `findcustomer John`
* `findpart Cylinder Head`
* `findappointment 05/03/2023`
---
### View

### Viewing specific vehicle/customer/appointment details: `view`

View a specific vehicle/customer/part/appointment detail. Id can be found by using the find or list command.

Format: `view(vehicle/customer/appointment) ID`

Examples:
* `viewvehicle 12`
* `viewcustomer 2`
* `viewappointment 56`

### Viewing specific vehicle/customer/part/appointment details: `view`

View a specific part detail. Part name can be found by using the find or list command.

Format: `viewpart name`

Examples:
* `viewpart Cylinder Head`

---
### Edit

### Editing a vehicle/customer/appointment : `edit`

Updates the specified (Vehicle/Customer/Appointment) information

Format: `edit(vehicle/customer/appointment) ID [?/PARAM] …​`

* Edits the specified object at the specified `ID`. The id refers to the index number shown in the displayed list from the list or find command. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `editcustomer 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with id 1 to be `91234567` and `johndoe@example.com` respectively.
*  `editvehicle 2 p/SBA9876G` Edits the plate number of the vehicle with id 2 to be `SBA9876G`.

---
### Delete

### Deleting a vehicle/customer/appointment : `delete`

Deletes the specified vehicle/customer/part/appointment from the system and all its related records.
* Deletes the vehicle/customer/part/appointment at the specified `ID`.
* The id refers to the index number shown in the displayed list from the list or find command.
* The id **must be a positive integer** 1, 2, 3, …​

Format: `delete(vehicle/customer/appointment) ID`

Example: `deletecustomer 12` deletes the customer with id 12 and all their related records in the AutoM8 system.

### Deleting a part : `delete`

Deletes the specified part from the system and all its related records.
* Deletes the part with the specified `NAME`.

Format: `deletepart NAME`

Example: `deletepart Cylinder Head` deletes the part 'Cylinder Head' and all their related records in the AutoM8 system.


---
### Actions

### Undo a previously executed command: `undo`

Undoes the previously typed command

Format: `undo`

Example flow:
1. `add customer n/john p/91238765 e/john@gmail.com a/kent ridge street, block 123, #01-01`
2. `undo`
3. *System deletes previously added customer*

### Redo a previously executed command: `redo`

Repeats the execution of the previously typed command

Format: `redo`

Example flow:
to be updated

---
### Others

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Saving the data

AutoM8 data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AutoM8 data are saved as a JSON file `[JAR file location]/data/autom8.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AutoM8 will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

to be updated

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                                  | Format, Examples                                                                                                                                                     |
|---------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Customer**                                        | `add customer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Add Vehicle**                                         | `add vehicle p/PLATE_NUMBER b/BRAND c/CUSTOMER_ID t/TYPE` <br> e.g., `add vehicle p/SBA1234A b/Toyota c/1 t/4wd`                                                     |
| **Add Service**                                         | `add service v/VEHICLE_ID t/SERVICE_TYPE [s/STATUS] [d/DESCRIPTION]`<br> e.g., `add service v/10 t/standard s/in progress d/Customer says abc`                       |
| **Add Appointment**                                     | `add appointment c/CUSTOMER_ID d/DATE t/TIME`<br> e.g.,`add appointment c/5 d/05/03/2023 t/5pm`                                                                      |
| **List all Vehicles/Customers/Parts/Appointments**      | `list (vehicles/customers/parts/appointments)`<br> e.g., `list customers`                                                                                            |
| **Sort Displayed List**                                 | `sort by/BRAND d/DIRECTION`<br> e.g., `list vehicles` then `sort by/brand d/asc`                                                                                     |
| **Find specific Vehicle/Customer/Part/Appointment**     | `find (vehicle/customer/part/appointment) KEYWORD`<br> e.g., `find vehicle toyota`                                                                                   |
| **View specific Vehicle/Customer/Part/Appointment**     | `view (vehicle/customer/part/appointment) ID`<br> e.g., `view appointment 56`                                                                                        |
| **Edit a specific Vehicle/Customer/Part/Appointment**   | `edit (vehicle/customer/part/appointment) ID [?/PARAM] …​`<br> e.g., `edit customer 1 p/91234567 e/johndoe@example.com`                                              |
| **Delete a specific Vehicle/Customer/Part/Appointment** | `delete (vehicle/customer/part/appointment) ID`<br> e.g., `delete customer 12`                                                                                       |
| **Undo**                                                | `undo`                                                                                                                                                               |
| **Redo**                                                | `redo`                                                                                                                                                               |
| **Exit**                                                | `exit`                                                                                                                                                               |
| **Help**                                                | `help`                                                                                                                                                               |
