---
layout: page
title: User Guide
---
<div style="text-align:center;">
<img src="images/LogoCoverVersion.png">
</div>

AutoM8 is a desktop application for auto repair shops that combines the efficiency of a Command Line Interface (CLI) with the user-friendliness of a Graphical User Interface (GUI) making it easy for users to manage tasks such as customer records, vehicle information, services, appointments, and inventory tracking. AutoM8 is a powerful tool that helps auto repair shops save time and increase productivity.

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `autom8.jar` from [here](https://github.com/AY2223S2-CS2103-W17-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for this application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar autom8.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)
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

<div markdown="block" class="alert alert-info">

**:information_source: Notes command GUI behaviors:**<br>

* The numbers displayed beside the entity is referring to the entity's id. (e.g. 1. Alex Yeoh, Alex has the Customer id of 1)
* Using entity specific commands (e.g. commandX, where command is a specific operation such as add, edit, etc and X is a specific entity such as customer, vehicle etc) will help you navigate to the respective tabs (e.g. A successful `addvehicle` command will navigate you to the Vehicles tab)
* While clicking on individual items in the items list may cause the UI to show that it is highlighted, the current entity shown in the right panel (Details panel) will not reflect what you have selected*. Please use the respective `view` commands in order to view details of a specific entity.
* Entering incorrectly formatted commands will throw an error message with information on command usage, which explains the correct way to use and format the command for your convenience.

</div>

---

### Add

#### Adding a customer: `addcustomer`
Adds a customer to the system and assigns a unique customer ID.

Format: `addcustomer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAGS]`

Example: `addcustomer n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

#### Adding a technician: `addtechnician`
Adds a technician to the system and assigns a unique staff ID.

Format: `addtechnician n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAGS]`

Example: `addtechnician n/Robert Low p/90253789 e/roblow@gmail.com a/25 Bedok East Rd, #08-20, SG405100 t/Leader`

#### Adding a vehicle to a customer: `addvehicle`
Adds a vehicle of specified type (i.e. motorbike, car) to the system and assigns a unique vehicle ID.

Format: `addvehicle p/PLATE_NUMBER b/BRAND c/CUSTOMER_ID cl/COLOR t/TYPE`

Examples:
* `addvehicle p/SBA1234A b/Toyota c/1 cl/red t/car`
* `addvehicle p/SBG5678B b/Honda c/2 cl/blue t/motorbike`

#### Adding a vehicle part: `addpart`
Adds a vehicle part to the system.

Format: `addpart n/NAME q/QUANTITY`

Example: `addpart n/Cylinder Head q/50`

#### Adding a service to a vehicle: `addservice`
Adds a service to perform on the specified vehicle plate number.

Format: `addservice v/VEHICLE_ID d/DESCRIPTION [l/SERIVCE LENGTH (days)] [s/STATUS]`

Examples:
* `addservice v/10 s/in progress d/Customer says abc`
* `addservice v/10 t/standard l/10` \
  Note: Adding service without specifying the status of service will default to “to repair” \
  Note: Adding service without specifying the service length will default to 7 days

#### Adding a vehicle part to specific service: `addservicepart`
Adds a vehicle part to a specific service.

Service and part must be registered in the system and part must have sufficient quanity,

Format: `addservicepart s/SERVICE ID n/PART NAME q/QUANTITY`

Example: `addservicepart s/2 n/Cylinder Head q/20`

#### Assigning a technician to a specific service: `addservicetech`
Assigns an existing technician to an existing service

Format: `addservicetech s/SERVICE_ID t/TECHNICIAN_ID`

Example: `addservicetech s/1 t/3`

#### Adding a customer appointment: `addappointment`
Adds a customer appointment to the system.

Format: `addappointment c/CUSTOMER_ID d/DATE t/TIME`

Example: `addappointment c/5 d/2023-03-05 t/14:00`

#### Assigning a technician to an appointment: `addappointmenttech`
Adds an existing technician to an existing appointment.

Format: `addappointmenttech a/APPOINTMENT_ID t/TECHNICIAN_ID`

Example: `addappointmenttech a/1 t/2`

---
### List/Sort

#### Listing all vehicles/customers/parts/appointments/services/technicians: `list`
Shows all vehicles/customers/parts/appointments/services/technicians and helps navigate you to the respective tab.

We also have a global `list` command. The "list" command helps to display all entities in their respective tabs while staying on the current tab. Just keep in mind that it doesn't switch tabs for you, so you'll need to do that manually by clicking or using the specific `listX` commands available to help you navigate to the respective tabs.

Format:
* `list(vehicles/customers/parts/appointments/services/technicians)`
* `list`


Examples:
* `list`
* `listvehicles`
* `listcustomers`
* `listparts`
* `listappointments`
* `listtechnicians`
* `listservices`


#### Sorting displayed lists: `sort`
Sorts all vehicles/customers/parts/appointments list in ascending or descending direction by a specific param.

Format: `sort(vehicles/customers/parts/appointments/services) by/ENTITY_PARAMS [r/]`
ENTITY_PARAMS are dependent on which entity* and adding `r/` means to reverse the sort direction.\
To see params are available for each entity for sorting, enter `sort(vehicles/customers/parts/appointments/services)` and it will show you the command usage with the params you can sort that entity by.

Examples:
* `sortcustomers by/id r/`
* `sortvehicles by/brand`
* `sortappointments by/date r/`
* `sortappointments by/date status`

---
### View

#### Viewing specific vehicles/customers/services/appointments/technicians: `view`

View a specific vehicle/customer/part/appointment/service detail. Id can be found by using the find or list command.

Format: `view(vehicle/customer/appointment/service/technician) ID`

Examples:
* `viewvehicle 12`
* `viewcustomer 2`
* `viewappointment 56`
* `viewservice 77`

---
### Find

#### Finding specific vehicles/customers/services/appointments/technicians: `find`

Finds all entities whose attributes match the specified keywords (case-insensitive) or the given date, to filter and displays them in the relevant tab lists.

Format: `find KEYWORD [MORE SPACE-SEPERATED KEYWORDS]`

Examples:
* `find alex alex@gmail.com`
* `find toyota`
* `find completed Oil`

---
### Edit

#### Editing a vehicle/customer/appointment : `edit`

Updates the specified (Vehicle/Customer/Appointment) information

Format: `edit(vehicle/customer/appointment/service/technician) i/ID [?/PARAM] …​`

* Edits the specified object at the specified `ID`. The id refers to the index number shown in the displayed list from the list or find command. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `editcustomer i/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with id 1 to be `91234567` and `johndoe@example.com` respectively.
*  `editvehicle i/2 p/SBA9876G` Edits the plate number of the vehicle with id 2 to be `SBA9876G`.

---
### Delete

#### Deleting a vehicle/customer/appointment/service/technician : `delete`

Deletes the specified vehicle/customer/part/appointment/service from the system and all its related records.
* Deletes the vehicle/customer/part/appointment/service at the specified `ID`.
* The id refers to the index number shown in the displayed list from the list or find command.
* The id **must be a positive integer** 1, 2, 3, …​

Format: `delete(vehicle/customer/appointment/service/technician) ID`

Example: `deletecustomer 12` deletes the customer with id 12 and all their related records in the AutoM8 system.

#### Deleting a part : `delete`

Deletes the specified part from the system and all its related records.
* Deletes the part with the specified `NAME`.

Format: `deletepart NAME`

Example: `deletepart Cylinder Head` deletes the part 'Cylinder Head' and all their related records in the AutoM8 system.

#### Unassign part from service: `removeservicepart`

Removes part from service and transfers it back into global part List.

Format: `removeservicepart s/SERVICE_ID n/PART_NAME q/PART_QTY`

Example: `removeservicepart s/2 n/Wheels q/2`

#### Unassign technician from service: `removeservicetech`

Removes an existing technician from an existing service.

Format: `removeservicetech t/TECHNICIAN_ID s/SERVICE_ID`

Example: `removeservicetech t/1 s/2`

#### Unassign technician from appointment: `removeappointmenttech`

Removes an existing technician from an existing appointment.

Format: `removeappointment t/TECHNCIAN_ID a/APPOINTMENT_ID`

Example: `removeappointmenttech t/1 a/2`

---
### Actions

<div markdown="block" class="alert alert-warning">
*Do note that the Undo and Redo commands are designed to work specifically with commands that alter or change data. See note below to find out what commands can be undo/redo.*
</div>
<div markdown="block" class="alert alert-info">
**Commands that you can undo/redo:**<br>
* `add`
* `delete`
* `edit`
* `undo`/`redo` i.e. you can `undo` a `redo`, and you can `redo` an `undo`
</div>

#### Undo a previously executed command: `undo`

Undoes certain previously typed command

_See above for a list of commands that `undo` applies to_

Format: `undo`

Example flow:
1. `add customer n/john p/91238765 e/john@gmail.com a/kent ridge street, block 123, #01-01`
2. `undo`

`undo` will reverse the add command, causing the system to delete previously added customer.

#### Redo a previously executed command: `redo`

Restores most recent command that was undone using undo. If you use a command that is not undo or redo, while in the midst of undoing, there will no longer be commands to redo.
Format: `redo`

Examples:

- `deletecustomer 3`
  `undo`
  `redo`

After deleting a customer at Index 3 and using `undo` to reverse the deletion of the customer, using `redo` will restore the customer back into the list.

---
### Others


#### Get number of appointments : `totalappointment`

Finds the number of appointments on the specified date.

Format: `totalAppointment d/DATE`

Example: `totalappointment d/2023-02-03`

* The `DATE` must follow a YYYY-MM-DD format. The range of allowable years is 0001 to 9999.

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

#### Saving the data

AutoM8 data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

AutoM8 data are saved as a JSON file `[JAR file location]/data/autom8.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AutoM8 will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: It appears that the index numbers in the list are not in a sequential order, such as 1, 2, 3, and so on, is there something wrong with my AutoM8 app? <br>
**A**: AutoM8 is actually functioning properly. The numbers you see listed next to the entities are not their index numbers, but rather their unique IDs. The reason why it might appear inconsistent is because our IdGenerator automatically generates IDs based on the highest ID value that has been assigned previously, and then continues sequentially from there. So, the ID numbers might not necessarily be in a perfectly sequential order, but they are still unique and assigned correctly.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AutoM8 home folder.

**Q**: I edited the data file and now AutoM8 throws a fatal error and stops working.<br>
**A**: It seems that your edit has made some conflicts with the way different parts of information are related to each other. This is probably because AutoM8 has some entities that are closely linked together. When you make edits, it's important to be cautious of this and make sure that your changes match up with AutoM8's entity references. You have two options: you can either delete the entire JSON file to reset everything and start fresh, or you can double-check your edits to make sure they are consistent with the way AutoM8 works.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                                                | Format, Examples                                                                                                                                                                |
|-----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Customer**                                                      | `addcustomer n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `addcustomer n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`     |
| **Add Technician**                                                    | `addtechnician n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `addtechnician n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Add Vehicle**                                                       | `addvehicle p/PLATE_NUMBER b/BRAND c/CUSTOMER_ID t/TYPE` <br> e.g., `addvehicle p/SBA1234A b/Toyota c/1 cl/red t/car`                                                           |
| **Add Vehicle Part**                                                  | `addpart n/NAME q/Quantity` <br> e.g., `addpart n/Cylinder Head q/50`                                                                                                           |
| **Add Service**                                                       | `addservice v/VEHICLE_ID d/DESCRIPTION [s/STATUS] [l/SERVICE_LENGTH (days)]`<br> e.g., `addservice v/10 d/standard s/in progress l/5`                                           |
| **Add Part to Service**                                               | `addservicepart s/SERVICE ID n/PART NAME q/QUANTITY` <br> e.g., `addservicepart s/2 n/Cylinder Head q/20`                                                                       |
| **Add Technician to Service**                                         | `addservicetech s/SERVICE_ID t/TECHNICIAN_ID` <br> e.g., `addservicetech s/1 t/3`                                                                                               |
| **Add Customer Appointment**                                          | `addappointment c/CUSTOMER_ID d/DATE t/TIME`<br> e.g.,`addappointment c/5 d/2023-03-05 t/14:00`                                                                                 |
| **Add Technician to Appointment**                                     | `addappointmenttech a/APPOINTMENT_ID t/TECHNICIAN_ID` <br> e.g., `addappointmenttech a/1 t/2`                                                                                   |
| **List all Vehicles/Customers/Parts/Appointments**                    | `list(vehicles/customers/parts/appointments)`<br> e.g., `listcustomers`, `list`                                                                                                 |
| **Sort Displayed List**                                               | `sort by/ENTITY_PARAM d/DIRECTION`<br> e.g., `listvehicles` then `sort by/brand d/asc`                                                                                          |
| **View specific Vehicle/Customer/Service/Appointment/Technician**     | `view(vehicle/customer/service/appointment/technician) ID`<br> e.g., `viewappointment 56`                                                                                       |
| **Find Vehicle/Customer/Service/Appointment/Technician**              | `find KEYWORD [MORE SPACE SEPERATED KEYWORDS]`<br> e.g., `find toyota car`                                                                                                      |
| **Edit a specific Vehicle/Customer/Service/Appointment/Technician**   | `edit(vehicle/customer/service/appointment/technician) i/ID [?/PARAM] …​`<br> e.g., `editcustomer i/1 p/91234567 e/johndoe@example.com`                                         |
| **Delete a specific Vehicle/Customer/Service/Appointment/Technician** | `delete(vehicle/customer/part/appointment) ID`<br> e.g., `deletecustomer 12`                                                                                                    |
| **Unassign Technician from Service/Appointment**                      | `remove(service/appointment)tech t/TECHCICIAN_ID (a//s/)APPOINTMENT/SERVICE_ID`<br> e.g., `removeservicetech s/1 t/2`                                                           | ` 
| **Remove Part from Service**                                          | `removeservicepart s/SERVICE_ID n/PART_NAME q/PART_QUANTITY`<br> e.g., `removeservicepart s/1 n/wheels q/2`                                                                     |
| **Get total number appointment on a specified date**                  | `totalAppointment d/DATE` <br> e.g., `totalappointment d/2023-02-03`                                                                                                            |
| **Undo**                                                              | `undo`                                                                                                                                                                          |
| **Redo**                                                              | `redo`                                                                                                                                                                          |
| **Exit**                                                              | `exit`                                                                                                                                                                          |
| **Help**                                                              | `help`                                                                                                                                                                          |
