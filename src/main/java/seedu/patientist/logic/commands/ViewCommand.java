package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.Person;

/**
 * Shows details of persons in patientist whose names contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows details the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewed Person: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return new CommandResult(
                String.format(MESSAGE_VIEW_PERSON_SUCCESS, lastShownList.get(targetIndex.getZeroBased())),
                targetIndex.getZeroBased(), false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
