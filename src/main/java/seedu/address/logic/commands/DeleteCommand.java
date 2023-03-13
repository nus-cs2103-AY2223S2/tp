package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using the displayed name from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    private static String name;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their name in the displayed person list.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " John Doe";

    private final NameContainsKeywordsPredicate predicate;


    /**
     *  Constructor for DeleteCommand
     * @param predicate name after the deleteCommand
     * @param name person name
     */
    public DeleteCommand(NameContainsKeywordsPredicate predicate, String name) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> personsToDelete = model.getFilteredPersonList();

        if (personsToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
        } else if (personsToDelete.size() == 1) {
            if (personsToDelete.get(0).getName().toString().equals(name.trim())) {
                Person deletedPerson = personsToDelete.get(0);
                model.deletePerson(deletedPerson);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, name));
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
            }
        } else {
            int index = 0;
            for (int i = 0; i < personsToDelete.size(); i++) {
                if (personsToDelete.get(i).getName().toString().equals(name)) {
                    index = i;
                    break;
                }
            }

            if (personsToDelete.get(index).getName().toString().equals(name)) {
                Person deletedPerson = personsToDelete.get(index);
                model.deletePerson(deletedPerson);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, name));
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
            }
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && predicate.equals(((DeleteCommand) other).predicate)); // state check
    }
}
