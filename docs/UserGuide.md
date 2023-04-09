---
layout: page
title: User Guide
---

<b style="font-size: 20px">_LoyaltyLift_</b> is a desktop application designed for small business owners to manage their customers and orders efficiently, allowing you to **improve customer relations** and **increase customer loyalty**.
It is optimized for users who are fast typists while still having the benefits of a visual interface.

With LoyaltyLift, you can easily keep track of your customers' preferences, purchase history, and contact information, enabling you to provide personalized service that will keep them coming back. 
Our application makes it easy to manage orders and provide rewards, helping you grow your business while delivering an exceptional customer experience. 

This user guide provides in-depth documentation on LoyaltyLift installation process, command features and common questions that you might have. 
In addition, the quick start guide provides an end-to-end setup process to get you started.

Are you excited yet? Let's get started!

----------------------------------------------------------------------------------------------------------------------

## Table of contents
{: .no_toc}

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. 
You can check what version of Java you have, from [here](https://www.java.com/en/download/help/version_manual.html).

1. Download the latest `loyaltylift.jar` from [here](https://github.com/AY2223S2-CS2103T-T09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for LoyaltyLift.

1. Open a command terminal by searching for terminal and launching it. Then, `cd` into the folder you put the jar file in, and use the `java -jar loyaltylift.jar` command to run the application.<br>
   You can learn more about the `cd` command [here](https://www.ibm.com/docs/en/aix/7.1?topic=directories-changing-another-directory-cd-command). <br> 
   An application similar to the one below should appear in a few seconds. Note how the app contains some sample data. You can click on one of the customers in the left hand panel to view more information about them. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `listc` : Lists all customers.

   * `addc n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a customer named `John Doe` to LoyaltyLift.

   * `deletec 3` : Deletes the 3rd customer shown in the current list.

   * `clear` : Deletes all customers and orders.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Getting to know LoyaltyLift

This section is designed to help new users get started with using LoyaltyLift.
In this section, you will learn how to perform essential tasks such as adding customers, creating orders, and setting up rewards. 
By the end of these tutorials, you will have a solid understanding of the fundamental features of LoyaltyLift and how to use them effectively to improve your business operations.

So, let's dive in and get started!


### Navigating around LoyaltyLift

Before starting your journey with LoyaltyLift, let's take a quick look at the layout of LoyaltyLift.

![Layout of LoyaltyLift](images/loyaltyLiftLayout.png)

It comprises 3 panels, the Information Panel, Command Panel and Table Panel. 
The table panel displays either the **customers** or **orders**. 
This can be configured by selecting the corresponding tab in the Table Tabs area.

The components are summarised below.

|         Component | Purpose                                                           |
|------------------:|:------------------------------------------------------------------|
|     Command Panel | Input area to submit your command to LoyaltyLift                  |
| Information Panel | Displays details of a specific customer or order                  |
|       Table Panel | Displays list of customers or orders                              |
|        Table Tabs | Switch between displaying customers and orders in the Table Panel |

#### Using the CLI
{: .no_toc}

The Command Panel is where you type your commands and submit them.
LoyaltyLift then processes your request and performs them if it is successful.

If LoyaltyLift understands your request, it will perform it and display feedback that it has succeeded, as seen below.

![CLI Success](images/cliSuccess.png)

Otherwise, the input font will turn red instead as seen below. 
The feedback will assist you in correcting the command as much as possible. 

![CLI Failure](images/cliFailure.png)


Now that you are familiar with the layout of LoyaltyLift, you are ready to jump right into using the application! 

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Add your first customer and order

In this tutorial, you will learn how to add your first customer _Lyndon Edwards_ and his order of 2 of his favourite _Banana Cake_.
Finally, you will learn to remove a customer and observe that **all orders belonging to a customer will be removed as well**.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you have not done so, use the `clear` command to remove the sample data!
</div>

#### Your first customer, Lyndon Edwards
{: .no_toc}

Adding your first customer is effortless on LoyaltyLift using [`addc`](#adding-a-customer--addc). 
Enter the following command to insert a new customer _Lyndon Edwards_ along with some basic contact information.

    addc n/Lyndon Edwards p/93015612 e/lyndon@example.com a/Loyalty Street 103, block 122, #01-20

The application should already have your customer displayed by this step.
Nonetheless, you can always type the following commands to list all customers and view the first customer in LoyaltyLift, which corresponds to _Lyndon Edwards_.

    listc
    viewc 1

![result after adding customer](images/addingFirstCustomerResult.png)

#### Your first order, 2 of Banana Cakes
{: .no_toc}

Now that _Lyndon Edwards_ is in LoyaltyLift, we can add an order for him.
This can be done with the [`addo`](#adding-an-order--addo) command like the following.

    addo 1 n/Banana Cake q/2 

Likewise, the application should already have the order opened for your convenience. 
To do this manually, you can list all your orders and view the first order's information by performing the following commands.  

    listo
    viewo 1

![result after adding customer and order](images/addingFirstCustomerOrderResult.png)

#### Clear application data again
{: .no_toc}

Before ending this tutorial, let us clear our application data by removing both _Lyndon Edwards_ and his _Banana Cake_ order.

While the [`clear`](#clearing-all-entries--clear) command achieves this easily, we can also simply remove _Lyndon Edwards_ from LoyaltyLift with the [`deletec`](#deleting-a-customer--deletec) command.

    deletec 1

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that in addition to removing `Lyndon Edwards` from the application, all of his orders are also removed!
</div>

Hence, you will notice that the order list is now empty, and our application is back to a clean slate.

    listo

![result after deleting lyndon edwards](images/addingFirstCustomerOrderClearResult.png)

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Following your order to completion

By following this tutorial, you'll learn how to update your order's status in LoyaltyLift, which will help you to better understand and manage the process of orders in your own business.

#### The order's status
{: .no_toc}

In LoyaltyLift, an order can have the following status.
* Pending
* Paid
* Shipped
* Completed
* Cancelled

<div markdown="block" class="alert alert-info">
LoyaltyLift assumes that your order will follow a process similar to the order of status stated above (excluding 'cancelled').
</div>

Before we start, head over to [_Add your first customer and order_](#add-your-first-customer-and-order) to include _Lyndon Edwards_ and the _Banana Cake_ order.
However, do not clear the application data as we will be using the order for this tutorial.

#### Advancing an order's status
{: .no_toc}

Let's take a look at our current list of orders, which should only consist of the order of _Banana Cake_.

    listo

Notice that the order's status is now 'Pending'.
To advance an order's status, the [`advo`]() command will come in handy, which takes the order index as its only parameter.

    advo 1

![result after advancing order status](images/advanceOrderResult.png)

Now, the status of the order should be 'Paid'. Doing this multiple times will eventually update the order's status to 'Completed'.
Further attempts to advance the order status will return an error!

#### Reverting an order's status
{: .no_toc}

To revert an order's status, the [`revo`]() command is similar to `advo` but has the inverse effect. 

    revo 1

Now, the status of the order should be 'Pending'. 
As expected, trying to revert the status when it is already 'Pending', will be met with an error!

#### Cancelling an order
{: .no_toc}

An order can be cancelled at any stage of the order. This can be performed with the [`cancelo`]() command.

    cancelo 1

![result after cancelling order](images/cancelOrderResult.png)

Looking at the order status, it is now reflected as 'Cancelled'. Once an order is cancelled, its status can no longer be advanced.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you accidentally cancel an order, you can still revert its status!
</div>

### Rewarding your customers

This tutorial will teach you the rewards system in LoyaltyLift and how you can apply it to boost your business' customer loyalty.

#### The Tier System
{: .no_toc}

In LoyaltyLift, there are three tiers with their associated point threshold.
* Bronze - 1000 points
* Silver - 5000 points
* Gold - 10000 points

<div markdown="block" class="alert alert-info">
LoyaltyLift assumes that your tier system will follow a similar style of Bronze, Silver and Gold.

The point threshold above means that if a customer has cumulative points equal to or greater than that threshold, he will be part of that tier.
For instance, a customer with 5500 cumulative points will be in the Silver tier as he is above the Silver tier point threshold
but below the Gold tier point threshold. 

</div>

Before we start, head over to [_Add your first customer and order_](#add-your-first-customer-and-order) to include _Lyndon Edwards_ and the _Banana Cake_ order.
However, do not clear the application data as we will be using the customer for this tutorial.

Rewarding your customers points is an effortless process on LoyaltyLift using 
[`setpoints`](#setting-reward-points-for-a-customer--setpoints) or 
[`addpoints`](#addingsubtracting-points-for-a-customer--addpoints).

In this scenario, we would like to add points to _Lyndon Edwards_ for his _Banana Cake_ order!
Here, we assume that _Lyndon Edwards_ is at your first index.

Enter the following command to add points to _Lyndon Edwards_ to reward him with some points.

    addpoints 1 pt/800

![result after adding points](images/addingPointsResult.png)

You should be able to see that _Lyndon Edwards_ has _800 points_.

Now that _Lyndon Edwards_ has some points, let's claim a reward on his behalf for being a loyal customer.
In this scenario, let's say that it costs _500 points_ for a water bottle. 
Let's use _500 points_ from _Lyndon Edwards_ to reward him with a water bottle!
You can then easily track his new points by again, 
using the [`addpoints`](#addingsubtracting-points-for-a-customer--addpoints) command!

Enter the following command to subtract points from _Lyndon Edwards_ after claiming a reward for him.

    addpoints 1 pt/-500

![result after subtracting points](images/subtractingPointsResult.png)

You should see that _Lyndon Edwards_ has _300 points_ remaining. However, do note that he still has 800 cumulative points,
as cumulative points are not subtracted away while claiming rewards.

Lastly, perhaps you feel that this was an error! _Lyndon Edwards_ should actually have _8000 points_ for being such a loyal customer.
You can overwrite his previous points, as well as his cumulative points, using the [`setpoints`](#setting-reward-points-for-a-customer--setpoints) command!

    setpoints 1 pt/8000

![result after setting points](images/setPointsResult.png)

You should see that _Lyndon Edwards_ now has _8000 points_ and _8000 cumulative points_.
Also, you should see that he is now part of the Silver tier as he has above 5000 cumulative points!

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The difference between `setpoints` and `addpoints` is that setting points will also set cumulative points to the same amount, 
if you wish to keep the current cumulative points, use the `addpoints` command instead.
</div>

You should now be able to reward any of your customers with our point system!

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Extra remarks

While these tutorials covers a simplified situation of adding a customer and order,
you may find yourself in need of more complex control/tasks, and LoyaltyLift is ready for it!

Here are some recommended features to explore after this tutorial.

* Editing a customer or order's information with [`editc`](#editing-a-customer--editc) or [`edito`](#editing-an-order--edito)
* Assigning a customer as an individual or enterprise with [`addc`](#adding-a-customer--addc) or [`editc`](#editing-a-customer--editc)
* Set or adding rewards points for the customer with [`setpoints`](#setting-reward-points-for-a-customer--setpoints) or [`addpoints`](#adding-points-for-a-customer--removing-points-from-a-customer--addpoints)

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* You should supply parameters to words in `UPPER_CASE`.<br>
  e.g. `addc n/NAME ...`, `NAME` is a parameter which can be used as `addc n/John Doe ...`.

* Items in square brackets are optional.<br>
  e.g. `...n/NAME [q/QUANTITY]` can be used as `...n/Cake q/2` or as `...n/Cake`.

* Items in curly brackets are an indication to use one of the given options, separated by the character `|`.
  e.g. `ct/{ind|ent}` is a parameter that should be used as `ct/ind` or `ct/ent` exactly.

* You can enter parameters in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is specified multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Some commands require you to specify an `INDEX`:**<br>

* The index **must be a positive integer** 1, 2, 3, …​ 

* The index refers to the index number of a customer or order as shown in the **Table Panel**.
  * `CINDEX` refers to a customer's index
  * `OINDEX` refers to an order's index

</div>

### Customer

#### Adding a customer : `addc`

Adds a customer to the current list.

**Format**

```
addc n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [ct/{ind|ent}]
```

| Prefix  | Parameter     | Optional | Description                              |
|---------|---------------|:--------:|------------------------------------------|
| `n/`    | Name          |          | Alphanumeric characters and spaces       |
| `p/`    | Phone         |          | A sequence of numbers, at least 3 digits |
| `e/`    | Email         |          | Of the format `local-part@domain`        |
| `a/`    | Address       |          | Any value                                |
| `ct/`   | Customer Type | ✅       | `ind` - Individual<br>`ent` - Enterprise<br>Customers are 'Individuals' by default |


<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `addc ct/ind n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`<br>
  Add an individual customer named "John Doe"

* `addc ct/ent n/The Potato Farm e/thepotatofarm@example.com a/South street, block 983, #02-01 p/1234567`<br>
  Add an enterprise customer named "The Potato Farm"

* `addc n/Mary Jane p/93130151 e/maryjane@example.com a/Mary Lamb Street, block 23, #01-12`<br>
  Add an individual customer named "Mary Jane"

</div>

#### Listing customers : `listc`

Shows a list of customers in the **Table Panel**.

**Format**

```
listc [s/{name|points}] [f/{marked|ind|ent}]
```

* Lists customers with the specified sort and filter option.

| Prefix  | Parameter | Optional | Description                              |
|---------|-----------|:--------:|------------------------------------------|
| `s/`    | Sort      | ✅       | `name` - Sort by name<br>`points` - Sort by points<br>By default, customers are sorted by name |
| `f/`    | Filter    | ✅       | `marked` - Show only marked customers<br>`ind` - Show only individuals<br>`ent` - Show only enterprises<br>By default, all customers are shown |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc`<br>
  Lists all customers sorted by name

* `listc s/points`<br>
  Lists all customers sorted by points

* `listc s/points f/marked`<br>
  Lists marked customers sorted by points

</div>

#### Locating customers by name : `findc`

Finds customers whose names contain any of the given keywords.

**Format**

```
findc KEYWORD [MORE_KEYWORDS]
```

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Customers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `findc John`<br>
  Returns `john` and `John Doe`

* `findc alex david`<br>
  Returns `Alex Yeoh` and `David Li`<br>
  ![result for 'findc alex david'](images/findAlexDavidResult.png)

</div>

#### View a customer : `viewc`

Displays a customer's information in the **Information Panel**.

**Format**

```
viewc CINDEX
```

* Displays the customer's information at the specified `CINDEX`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can click on the customer in the table directly for the same effect!
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` and `viewc 2`<br>
  Displays the second customer in the **Information Panel**<br>
  ![result for 'viewc 2'](images/viewcBerniceYuResult.png)

</div>

#### Editing a customer : `editc`

Edits an existing customer in LoyaltyLift.

**Format**

```
editc CINDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [ct/{ind|env}]
```

* Edits the customer at the specified `CINDEX`. 
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

| Prefix  | Parameter     | Optional | Description                              |
|---------|---------------|:--------:|------------------------------------------|
| `n/`    | Name          | ✅       | Alphanumeric characters and spaces       |
| `p/`    | Phone         | ✅       | A sequence of numbers, at least 3 digits |
| `e/`    | Email         | ✅       | Of the format `local-part@domain`        |
| `a/`    | Address       | ✅       | Any value                                |
| `ct/`   | Customer Type | ✅       | `ind` - Individual<br>`ent` - Enterprise |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `editc 1 p/91234567 e/johndoe@example.com`<br>
  Edits the phone number and email address of the 1st customer to be `91234567` and `johndoe@example.com` respectively.

* `editc 2 ct/ind n/Betsy Crower`<br>
  Edits the name of the 2nd customer to be `Betsy Crower`.

</div>

#### Deleting a customer : `deletec`

Delete a customer from the list.

**Format**

```
deletec CINDEX
```

* Deletes the customer at the specified `CINDEX`.

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `deletec 2`<br>
  Deletes the 2nd customer in LoyaltyLift.

* `findc Betsy` followed by `deletec 1`<br>
  Deletes the 1st customer in the results of the `findc` command.

</div>

[//]: # (@@author JavonTeo)

#### Marking a customer : `markc`

Bookmarks a customer from the list of customers.

Do you have a customer you want to prioritise or stand out from other customers in the list? Use this command.

**Format**

```
markc CINDEX
```

* Bookmarks the customer at the specified `CINDEX`.​

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `markc 2`<br>
  Bookmarks the 2nd customer in LoyaltyLift.
  ![result for 'markc 2'](images/markcResult.png)

* `findc Betsy` followed by `markc 1`<br>
  Bookmarks the 1st customer in the results of the `findc` command.

</div>

#### Unmarking a customer : `unmarkc`

Un-bookmarks a customer from the list of customers.

This command allows you to remove any bookmark.

**Format**

```
unmarkc CINDEX
```

* Un-bookmarks the customer at the specified `CINDEX`.

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `unmarkc 2`<br>
  Un-bookmarks the 2nd customer in LoyaltyLift.
  ![result for 'unmarkc 2'](images/unmarkcResult.png)

* `findc Betsy` followed by `unmarkc 1`<br>
  Un-bookmarks the 1st customer in the results of the `findc` command.

</div>

[//]: # (@@author CloudHill)

#### Setting a customer's note : `setnotec`

Sets a customer's note.

Note that information about your customer down with the `setnotec` command!

**Format**

```
setnotec CINDEX nt/NOTE
```

* Sets `NOTE` as the note of the customer at the specified `CINDEX`.
* Customers, by default, have a blank note.

| Prefix  | Parameter     | Optional | Description |
|---------|---------------|:--------:|-------------|
| `nt/`   | Note          |          | Any value   |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
This command overwrites existing notes.
You can remove a customer's note by setting an empty note.
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `setnotec 2 nt/Very friendly!`<br>
  Sets the 2nd customer's note as "Very friendly!".

* `listc` followed by `setnotec 2 nt/`<br>
  Removes the 2nd customer's note.

* `findc Betsy` followed by `setnotec 1 nt/Vegetarian`<br>
  Sets the 1st customer's note as "Vegetarian" in the results of the `findc` command.

</div>

#### Appending a customer's note : `appendnotec`

Adds more text to a customer's note.

**Format**

```
appendnotec CINDEX nt/NOTE
```

* Adds `NOTE` to any existing note of the customer at the specified `CINDEX`.

| Prefix  | Parameter     | Optional | Description |
|---------|---------------|:--------:|-------------|
| `nt/`   | Note          |          | Any value   |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `appendnotec 2 nt/Very friendly!`<br>
  Adds "Very friendly!" to the 2nd customer's existing note.

* `findc Betsy` followed by `appendnotec 1 nt/Vegetarian`<br>
  Adds "Vegetarian" to the note of the 1st customer in the results of the `findc` command.

</div>

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

[//]: # (@@author Dawson)

### Rewards

#### Setting reward points for a customer : `setpoints`

Sets a customer's reward points.

**Format**

```
setpoints CINDEX pt/POINTS
```

* Sets the points of the customer at the specified `CINDEX` to `POINTS`.
* Customers, by default, have 0 points.

| Prefix  | Parameter     | Optional | Description                     |
|---------|---------------|:--------:|---------------------------------|
| `pt/`   | Points        |          | A positive integer up to 999999 |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Setting points will also set cumulative points to the same amount, if you wish to keep the current cumulative points, use the `addpoints` command instead.
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `setpoints 2 pt/100`<br>
  Sets the 2nd customer's points as 100.
  ![result for 'setpoints 2'](images/setpointsExampleResult.png)

* `findc Betsy` followed by `setpoints 1 pt/300`<br>
  Sets the 1st customer points as 300 in the results of the `findc` command.

</div>

#### Adding/Subtracting points for a customer : `addpoints`

Edits a customer's reward points by adding or subtracting from it.

**Format**

```
addpoints CINDEX pt/POINTS
```

* Adds or subtracts the points of the customer at the specified `CINDEX` by `POINTS`.
* If the points subtracted is greater than what the customer has, the command will not be executed.
* If the points added will result in points or cumulative points being greater than 999999, 
the command will not be executed.

| Prefix  | Parameter     | Optional | Description                                                                   |
|---------|---------------|:--------:|-------------------------------------------------------------------------------|
| `pt/`   | Points        |          | - A positive integer to add points<br>- A negative integer to subtract points |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Addition will also result in an addition of cumulative points, while subtraction will not affect cumulative points.
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listc` followed by `addpoints 2 pt/100`<br>
  Adds 100 reward points to the 2nd customer.

* `findc Betsy` followed by `addpoints 1 pt/-300`<br>
  Deducts 300 reward points from the 1st customer in the results of the `findc` command.

</div>

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

[//]: # (@@author jednghk)

### Orders

#### Adding an order : `addo`

Adds an order for a customer to the current order list.

**Format**

```
addo CINDEX n/NAME [q/QUANTITY] [a/ADDRESS]
```

* Adds an order for the customer at the specified `CINDEX`.

| Prefix  | Parameter    | Optional | Description                                     |
|---------|--------------|:--------:|-------------------------------------------------|
| `n/`    | Product Name |          | Alphanumeric characters and spaces              |
| `q/`    | Quantity     | ✅       | A positive integer up to 999<br>Defaults to 1   |
| `a/`    | Address      | ✅       | Any value<br>Defaults to the customer's address |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `addo 1 n/Banana Cake 1 q/2 a/Changi Airport`<br>
  Adds the order, 2 x Banana cakes to the list, tags it to customer at index 1, and status is "pending", address is "Changi Airport".

* `addo 4 n/Strawberry ice cream`<br>
  Adds the order 1 x Strawberry ice cream, tags it to customer 4, and sets status to "pending", address is the customer's address.

</div>

#### Listing all orders : `listo`

Shows a list of orders in the **Table Panel**.

**Format**

```
listo [s/{created|name|status}] [f/STATUS]
```

* List orders with the specified sort and filter option.

| Prefix  | Parameter | Optional | Description                                                                                                                             |
|---------|-----------|:--------:|-----------------------------------------------------------------------------------------------------------------------------------------|
| `s/`    | Sort      | ✅       | `created` - Sort by created date<br>`name` - Sort by name<br>`status` - Sort by status<br>By default, orders are sorted by created date |
| `f/`    | Filter    | ✅       | Show only orders with the specified status<br>By default, all orders are shown                                                          |

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Currently, you are unable to add the same order name and quantity for the same customer twice on the same day!
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listo`<br>
  Lists all orders sorted by created date.

* `listo s/status`<br>
  Lists all orders sorted by status.

* `listo s/name f/pending`<br>
  Lists all pending orders sorted by name.

</div>

[//]: # (@@author CloudHill)

#### Locating orders by name : `findo`

Find orders whose names contain any of the given keywords.

**Format**

```
findo KEYWORD [MORE_KEYWORDS]
```

* The search is case-insensitive. e.g. `chocolate` will match `Chocolate`
* The order of the keywords does not matter. e.g. `chocolate cake` will match ` cake chocolate`.
* Only the order name is searched.
* Only full words will be matched e.g. `chocolate` will not match `chocolatey`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `chocolate cake` will return `chocolate muffin`, `crepe cake`

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `findo chocolate`<br>
  Returns `chocolate cake` and `chocolate muffin`

* `findo banana muffin`<br>
  Returns `banana cake`, `chocolate muffin`

</div>

[//]: # (@@author Junyi00)

#### View an order : `viewo`

Displays an order's information in the **Information Panel**.

**Format**

```
viewo OINDEX
```

* Displays the order's information at the specified `OINDEX`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can click on the order in the table directly for the same effect!
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listo` and `viewo 2`<br>
  Displays the second order in the **Information Panel**
  ![result for 'viewo 2'](images/viewoBananaCakeResult.png)

</div>

[//]: # (@@author jednghk)

#### Editing an order : `edito`

Edits an existing order in LoyaltyLift.

**Format**

```
edito OINDEX [n/NAME] [q/QUANTITY] [a/ADDRESS]
```

* Edits the order at the specified `OINDEX`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

| Prefix  | Parameter    | Optional | Description                                     |
|---------|--------------|:--------:|-------------------------------------------------|
| `n/`    | Product Name | ✅       | Alphanumeric characters and spaces              |
| `q/`    | Quantity     | ✅       | A positive integer up to 999<br>Defaults to 1   |
| `a/`    | Address      | ✅       | Any value<br>Defaults to the customer's address |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `edito 1 q/100 a/Gardens by the Bay`<br>
  Edits the quantity and address of the 1st order to `100` and `Gardens by the Bay` respectively.

* `edito 2 n/Brownies q/10`<br>
  Edits the name and quantity of the 2nd order to be `Brownies` and `10` respectively.

</div>

#### Advancing an order status: `advo`

Advances an order's status.

**Format**

```
advo OINDEX
```

* Advances the order at the specified `OINDEX`.
* An order's status will be advanced in the following sequence: "Pending", "Paid", "Shipped", "Completed".


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the order is completed, the status cannot be advanced further
</div>


<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `advo 1`<br>
  Advances the status of the 1st order

</div>

#### Reverting an order status: `revo`

Reverts an order's status to its previous status. 

**Format**

```
revo OINDEX
```

* Edits the order at the specified `OINDEX`.
* An order's status will be reverted in the following sequence: "Completed", "Shipped", "Paid", "Pending".

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**<br>

* If the order is "Pending", the status cannot be reverted further.

* If the order is "Cancelled", `revo` will revert the order to its previous status prior to cancellation.

</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `revo 1`<br>
  Reverts the status of the 1st order.

</div>

#### Cancel an order: `cancelo`

Changes an order's status to "Cancelled"

**Format**

```
cancelo OINDEX
```

* Cancels the order at the specified `OINDEX`.

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `cancelo 1`<br>
  Changes the status of the 1st order to "Cancelled".

</div>

#### Deleting an order : `deleteo`

Deletes the specified order from LoyaltyLift.

**Format**

```
deleteo OINDEX
```

* Deletes the order at the specified `OINDEX`.

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listo` followed by `deleteo 2`<br>
  Deletes the 2nd order on the displayed order list.

* `findo chocolate` followed by `deleteo 1`<br>
  Deletes the 1st order in the results of the `findo` command.

</div>

[//]: # (@@author CloudHill)

#### Setting an order's note : `setnoteo`

Sets an order's note.

**Format**

```
setnoteo OINDEX nt/NOTE
```

* Sets `NOTE` as the note of the order at the specified `OINDEX`.
* Orders, by default, have a blank note.

| Prefix  | Parameter     | Optional | Description |
|---------|---------------|:--------:|-------------|
| `nt/`   | Note          |          | Any value   |

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can remove an order's note by setting an empty note.
</div>

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listo` followed by `setnoteo 2 nt/Keep cool`<br>
  Sets the 2nd order's note as "Keep cool".

* `listc` followed by `setnoteo 2 nt/`<br>
  Removes the 2nd order's note.

* `findo Brownies` followed by `setnoteo 1 nt/Vegan-friendly`<br>
  Sets the 1st order's note as "Vegan-friendly" in the results of the `findo` command.

</div>

#### Appending an order's note : `appendnoteo`

Adds more text to an order's note.

**Format**

```
appendnoteo OINDEX nt/NOTE
```

* Adds `NOTE` to any existing note of the customer at the specified `OINDEX`.

| Prefix  | Parameter     | Optional | Description |
|---------|---------------|:--------:|-------------|
| `nt/`   | Note          |          | Any value   |

<div markdown="block" class="alert alert-secondary">

**:keyboard: Examples:**<br>

* `listo` followed by `appendnoteo 2 nt/Keep cool`<br>
  Adds "Keep cool" to the 2nd order's existing note.

* `findo Brownies` followed by `appendnoteo 1 nt/Vegan-friendly`<br>
  Adds "Vegan-friendly" to the note of the 1st order in the results of the `findo` command.

</div>

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

[//]: # (@@author)

### Miscellaneous

#### Clearing all entries : `clear`

Clears all customers and orders from LoyaltyLift.

**Format**

```
clear
```

#### Exiting the program : `exit`

Exits the program.

**Format**

```
exit
```

#### Viewing help : `help`

Shows a message explaining how to access the help page.

**Format**

```
help
```

![help message](images/helpMessage.png)

### Saving the data

Customer and order data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: My customer's name includes special characters such as 'Jôse'; how can I add this customer into LoyaltyLift? <br />
**A**: Currently, LoyaltyLift cannot store a customer’s name with special characters. However, one workaround is to add the customer by replacing the special character with a close equivalent, such as 'Jose', and use the `setnotec` command to type his actual name such as `senotec 1 nt/Jôse`.

**Q**: Why are orders deleted or missing after deleting a customer with `deletec`? <br />
**A**: Orders are closely tied to a customer in LoyaltyLift. Once a customer has been removed, all the customer's orders needs to be cleared. If this is not ideal, we recommend to avoid deleting any customers.

**Q**: Can my orders have a different address than the customer's address? <br />
**A**: Yes! When you create an order using `addo`, you can specify the new address with the parameter `a/`. If this is not specified, the customer's address is used instead for your convenience.

**Q**: How can I store specific requests from my customer's for an order? <br />
**A**: Using the `setnoteo` command, you can add information such as special customer requests for that particular order! Here is an example  when your customer requests for lesser sugar: `setnoteo 1 nt/less sugar`.

**Q**: Are the reward tiers of my customers based on the cumulated points or the current points they have? <br />
**A**: Currently, LoyaltyLift determines the tiers of your customers by the **cumulated points**. However, if you feel that a customer no longer deserves the tier, you can use `setpoints` to replace both his current and cumulated points so that it would be below the threshold of the tier!

**Q**: How do I view a customer's order history in LoyaltyLift? <br />
**A**: The order history of a customer is available when you view the customer's information in the information panel. To do so, you can first use `findc` command to locate the customer, then `viewc` command to display the customer in the information panel. The order history is found under the **History** section.

[🠕 Back To Top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Command summary

### Customer

|                       Action | Format, Examples                                                                                                                                                                             |
|-----------------------------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|             **Add Customer** | <code>addc [ct/{ind&#124;ent}] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `addc n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague</code> |
|            **List Customer** | `listc` <br>                                                                                                                                                                                 |
|            **Find Customer** | `findc KEYWORD [MORE_KEYWORDS]` <br> e.g., `findc Hans Bo`                                                                                                                                   |
|            **View Customer** | `viewc CINDEX` <br> e.g. `viewc 2`                                                                                                                                                           |
|            **Edit Customer** | <code>editc CINDEX [ct/{ind&#124;env}] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br> e.g. `editc 1 p/91234567 e/johndoe@example.com</code>                                           |
|          **Delete Customer** | `deletec CINDEX`<br> e.g., `deletec 3`                                                                                                                                                       |
|            **Mark Customer** | `markc CINDEX` <br> e.g., `markc 1`                                                                                                                                                          |
|          **Unmark Customer** | `unmarkc CINDEX` <br> e.g., `unmarkc 1`                                                                                                                                                      |                                                                                                                                      |
|    **Set Note for Customer** | `setnotec CINDEX nt/NOTE` <br> e.g., `setnotec 2 nt/Very friendly!`                                                                                                                          |
| **Append Note for Customer** | `appendnotec CINDEX nt/NOTE` <br> e.g., `appendnotec 2 nt/Very friendly!`                                                                                                                    |

### Rewards

|         Action | Format, Examples                                                                        |
|---------------:|-----------------------------------------------------------------------------------------|
| **Set Points** | `setpoints CINDEX pt/POINTS` <br> e.g., `setpoints 2 pt/100`                            |
| **Add Points** | `addpoints CINDEX pt/[+/-]POINTS` <br> e.g., `addpoints 2 pt/100`, `addpoints 1 pt/-50` |


### Order

|                    Action | Format, Examples                                                                      |
|--------------------------:|---------------------------------------------------------------------------------------|
|             **Add Order** | `addo CINDEX n/NAME [q/QUANTITY] [a/ADDRESS]` <br> e.g., `addo 1 n/Banana Cake 1 q/2` |
|            **List Order** | `listo` <br>                                                                          |
|            **Find Order** | `findo KEYWORD [MORE_KEYWORDS]` <br> e.g., `findo banana muffin`                      |
|            **View Order** | `viewo OINDEX` <br> e.g. `viewo 2`                                                    |
|            **Edit Order** | `edito OINDEX [n/NAME] [q/QUANTITY] [a/ADDRESS]` <br> e.g., `edito 2 n/Brownies q/10` |
|         **Advance Order** | `advo OINDEX`<br> e.g., `advo 1`                                                      |
|          **Revert Order** | `revo OINDEX`<br> e.g., `revo 2`                                                      |
|          **Cancel Order** | `cancelo OINDEX`<br> e.g., `cancelo 3`                                                |
|          **Delete Order** | `deleteo OINDEX`<br> e.g., `deletec 3`                                                |                                                                                                                                     |
|    **Set Note for Order** | `setnoteo OINDEX nt/NOTE` <br> e.g., `setnoteo 2 nt/Very friendly!`                   |
| **Append Note for Order** | `appendnoteo OINDEX nt/NOTE` <br> e.g., `appendnoteo 2 nt/Very friendly!`             |

### Miscellaneous

|    Action | Format, Examples |
|----------:|------------------|
| **Clear** | `clear`          |
|  **Exit** | `exit`           |
|  **Help** | `help`           |

[🠕 Back To Top](#table-of-contents)
