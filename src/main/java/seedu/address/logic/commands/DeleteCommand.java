package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.ContactIndex.USER_CONTACT_INDEX;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_CANNOT_DELETE_OWN_PROFILE = "Cannot delete your own profile!";

    private final ContactIndex contactIndex;

    public DeleteCommand(ContactIndex contactIndex) {
        this.contactIndex = contactIndex;
    }

    @Override
    public ViewCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (contactIndex.equals(USER_CONTACT_INDEX)) {
            throw new CommandException(MESSAGE_CANNOT_DELETE_OWN_PROFILE);
        }

        IndexHandler indexHandler = new IndexHandler(model);
        Optional<Person> targetPerson = indexHandler.getPersonByIndex(contactIndex);

        if (targetPerson.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = targetPerson.get();
        model.deletePerson(personToDelete);
        model.updateObservableMeetUpList(Model.COMPARATOR_CONTACT_INDEX_MEETUP);
        return new ViewCommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), model.getUser());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && contactIndex.equals(((DeleteCommand) other).contactIndex)); // state check
    }
}
