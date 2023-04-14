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
 * The type Upload file command.
 */
public class UploadFileCommand extends Command {
    public static final String COMMAND_WORD = "upload";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Upload file for the people identified by the index numbers used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_UPLOAD_SUCCESS = "Upload Person: %1$s";

    private final Index targetIndex;

    public UploadFileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpload = lastShownList.get(targetIndex.getZeroBased());
        FilesManager filesManager = new FilesManager(personToUpload);
        try {
            filesManager.addFile();
        } catch (RuntimeException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILE_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_UPLOAD_SUCCESS, personToUpload));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UploadFileCommand // instanceof handles nulls
                && targetIndex.equals(((UploadFileCommand) other).targetIndex)); // state check
    }
}
