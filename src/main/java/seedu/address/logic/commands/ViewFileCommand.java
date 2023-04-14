package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.files.FilesManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * The type View file command.
 */
public class ViewFileCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": view file for the people identified by the index numbers used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) index (must be the exact name)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 1";

    public static final String MESSAGE_VIEW_SUCCESS = "View Person: %1$s";

    private final Index targetIndex;
    private final int number;

    /**
     * @param targetIndex
     * @param number
     */
    public ViewFileCommand(Index targetIndex, int number) {
        this.targetIndex = targetIndex;
        this.number = number;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        FilesManager filesManager = new FilesManager(personToView);

        if (number > filesManager.getFileNames().size() || number <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILE_INDEX);
        }
        filesManager.readNthFile(number);
        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, personToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewFileCommand // instanceof handles nulls
                && targetIndex.equals(((ViewFileCommand) other).targetIndex)
                && number == ((ViewFileCommand) other).number); // state check
    }
}
