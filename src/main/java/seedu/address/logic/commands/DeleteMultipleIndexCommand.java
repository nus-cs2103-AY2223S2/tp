package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteMultipleIndexCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted People Successfully ";

    private final ArrayList<Index> indexes;

    public DeleteMultipleIndexCommand(ArrayList<Index> indexes) {
        this.indexes = indexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        isIndexesValid(lastShownList);

        ArrayList<Person> listOfPeople = new ArrayList<Person>();
        for (Index targetIndex : this.indexes) {

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            listOfPeople.add(personToDelete);
        }
        model.deleteMultiplePersons(listOfPeople);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMultipleIndexCommand // instanceof handles nulls
                && indexes.equals(((DeleteMultipleIndexCommand) other).indexes)); // state check
    }

    /**
     * Checks if all the indexes in the list of indexes are valid.
     * @param lastShownList
     * @return validity of the list of indexes.
     * @throws CommandException
     */
    public boolean isIndexesValid(List<Person> lastShownList) throws CommandException {
        for (Index targetIndex : indexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        return true;
    }
}
