package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

// Implementation below adapted from https://github.com/Charles1026/tp/blob/7f3cc48fb35418f1f6f6f4c1dc5e8a4a037d29d8/
// src/main/java/seedu/address/logic/commands/ViewCommand.java
/**
 * Displays detailed person card.
 */
public class DisplayCommand extends Command {
    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the personal details of the person identified "
            + "by the NRIC. "
            + "\n"
            + "Parameters: view ic/ [NRIC]\n "
            + "Example: " + COMMAND_WORD
            + "ic/S1234567Z";

    public static final String MESSAGE_SUCCESS = "Displaying person details: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "Specified NRIC is invalid ";

    private final Nric nric;

    /**
     * Creates a DisplayCommand to display the personal details of specified {@code Person}
     */
    public DisplayCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getFilteredPersonList();
        Person personToView = null;
        personToView = retrievePersonToView(model, persons, personToView);
        checkIfValidPerson(personToView);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToView));
    }

    private void checkIfValidPerson(Person personToView) throws CommandException {
        if (personToView == null) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
    }

    private Person retrievePersonToView(Model model, List<Person> persons, Person personToView) {
        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            if (p.getNric().equals(nric)) {
                model.updatePersonView(p);
                personToView = p;
                break;
            }
        }
        return personToView;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DisplayCommand)) {
            return false;
        }

        // state check
        DisplayCommand e = (DisplayCommand) other;
        return nric.equals(e.nric);
    }
}
