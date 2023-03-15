package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;



/**
 * Delete people identified using it's displayed index from the address book.
 */
public class DeleteCommands extends Command {
    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the people identified by the index numbers used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final ArrayList<Index> indices;

    /**
     * Instantiates a new Delete commands.
     *
     * @param indices the indices
     */
    public DeleteCommands(ArrayList<Index> indices) {
        Collections.sort(indices);
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indices.get(0).getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        StringBuilder str = new StringBuilder();
        indices.stream().map(index -> lastShownList.get(index.getZeroBased())).forEach(personToDelete -> {
            model.deletePerson(personToDelete);
            str.append(personToDelete);
            str.append(" ");
        });
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, str));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommands // instanceof handles nulls
                && sameArrayList(((DeleteCommands) other).indices)); // state check
    }

    /**
     * @param other
     * @return
     */
    public boolean sameArrayList(ArrayList<Index> other) {

        if (other.size() != this.indices.size()) {
            System.out.println("-----------------");
            return false;

        } else {
            for (int x = 0; x < this.indices.size(); x++) {
                boolean output = indices.get(x).getZeroBased() == other.get(x).getZeroBased();

                if (!output) {
                    return false;
                }
            }
            return true;
        }
    }
}
