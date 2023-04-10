# Parsing

## Overview

At its core, Wingman is a modal editor for managing planes, pilots, crew,
flights, and locations. To achieve this modal approach,
Wingman adopts a 2-level parsing strategy. The top level is managed inside a
class called `WingmanParser`. Inside this class, it does two types of parsing:

1. Top-level command parsing: parses the commands that do not follow any
   logical grouping. For example, since Wingman is a `modal` editor, the
   change of mode does not belong to any logical grouping, therefore it
   should be parsed at the top level.
2. Grouped command parsing: delegates the parsing of the commands that
   belong to a certain logical grouping to the `CommandGroup` class of that
   logical grouping.

The second level of parsing happens inside the `CommandGroup` class. This
class does a parsing of the commands similar to the 1. above. Because the
features are similar, a base class `FactoryParser` is created to reduce code
duplication.

In designing the parsing logic, composition is preferred over inheritance,
as inheritance makes the code less readable, increases the coupling between
different classes, and makes it harder to test.

## Parsing Logic

### Step 1: Tokenization

The first step in the parsing process is to "tokenize" the input string into
a list of tokens. Here, since our parsing logic is simple, we do the
tokenization simply by splitting the input string by white spaces.

We use a `Deque` structure to store the tokens. The benefit of this is that
we can easily pop the first token from the list, and this would take $O(1)$
time as compared to $O(n)$ time for a `List`.

The tokenization process is done in the `WingmanParser` class, inside the
private method `tokenize`.

### Step 2: Delegating to the `CommandGroup` class

If we ignore the top-level commands, the next step in the parsing process is
to delegate the parsing logic to the `CommandGroup` class. This is done
based on the current `OperationMode` of the application. The `OperationMode`
is an enum that represents the current mode of the application. It has five
different values:

- `PILOT`: the application is used to manage pilots.
- `PLANE`: the application is used to manage planes.
- `CREW`: the application is used to manage crew.
- `FLIGHT`: the application is used to manage flights.
- `LOCATION`: the application is used to manage locations.

If the current `OperationMode` is `PILOT`, then the tokens would be passed
down to the `CommandGroup` class of the `PILOT` mode.

### Step 3: Delegating to the `CommandFactory` class

Our next step is to delegate the parsing logic to the `CommandFactory`
objects. A `CommandFactory` object is a class that is responsible for
creating new `Command` objects. They are responsible for parsing the tokens
that are needed for the creation of the `Command` object, and then create
the respective `Command` object.

The logic of this delegation process is done in the super class, in a method
called `parseFactory`. This method takes in a `Deque` of tokens, peeks at
the top of the `Deque` as the command word, and matches it with the
`getCommandWord()` method of the `CommandFactory` objects. If there is a
match, it would pass the `Deque` of tokens and the `getPrefixes()` method of
the `CommandFactory` object to the `from` factory constructor of the
`CommandParam` class.

### Step 4: Extracting the values of prefixes

This step is done in the `CommandParam` class. More specifically, it is done
statically in the `from` factory constructor. It will take an optional set
of tokens and a deque of prefixes, and return a `CommandParam` object that
contains the unnamed and named parameters.

The unnamed parameters are extracted by reading from the start of the `Deque`
of tokens until either the end of the `Deque` is reached, or a token that
matches a prefix is found. The named parameters are extracted by reading from
the start of the `Deque` of tokens until either the end of the `Deque` is
reached, or a token that matches another prefix is found. **Currently,
duplicated prefixes are not allowed.**

### Step 5: Creating the `Command` object

This step is done in the `CommandFactory` class. More specifically, it is
done in the `create` method. It takes in a `CommandParam` object, and then
it will create the respective `Command` object.

## Implementing a new command

To implement a new command, we need to do the following:

1. Create a new `Command` class that extends the `Command` class.
2. Create a new `CommandFactory` class that extends the `CommandFactory`
   class with a generic type of the command class that we created in step 1.
3. Add the `CommandFactory` object to the `CommandGroup` class of the
   respective mode, which can be found in the `WingmanParser` class.
