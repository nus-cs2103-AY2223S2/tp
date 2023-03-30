package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.internship.Internship;

/**
 * Copies the internship information identified using it's displayed index from InternBuddy.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the details of the internship identified by the index"
            + " number used in the displayed internship list.\n"
            + "Fields: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COPY_INTERNSHIP_SUCCESS = "Copied Internship: %1$s";

    private final Index targetIndex;

    public CopyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        //Checks for a valid index
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_OUT_OF_RANGE_INTERNSHIP_DISPLAYED_INDEX);
        }

        //Gets the internship to copy
        Internship internshipToCopy = lastShownList.get(targetIndex.getZeroBased());

        //Functionality of the copy internship command
        model.copyInternship(internshipToCopy);

        return new CommandResult(String.format(MESSAGE_COPY_INTERNSHIP_SUCCESS, internshipToCopy));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && targetIndex.equals(((CopyCommand) other).targetIndex)); // state check
    }
}
