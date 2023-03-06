package seedu.address.logic.commands.crew;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.IdentifiableManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.crew.Crew;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCrewCommand implements Command {

    public static final String COMMAND_WORD = "delete crew";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the crew identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteCrewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Crew> lastShownList = model.getFilteredCrewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Crew crewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCrew(crewToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, crewToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCrewCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCrewCommand) other).targetIndex)); // state check
    }
}
