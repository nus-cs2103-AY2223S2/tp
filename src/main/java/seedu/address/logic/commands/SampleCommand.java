package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.util.SampleDataUtil;

/**
 * Command for generating sample data in EduMate.
 * It is used mainly for manual testing.
 */
public class SampleCommand extends Command {

    public static final String COMMAND_WORD = "sample";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Populate the EduMate with "
            + "sample data of the given size.\n"
            + "Parameters: SIZE (non-negative integer)\n"
            + "Example: " + COMMAND_WORD + " 25";

    private final int size;

    public SampleCommand(int size) {
        this.size = size;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.setEduMate(SampleDataUtil.getSampleEduMate(size));
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SampleCommand // instanceof handles nulls
                && size == ((SampleCommand) other).size); // state check
    }
}

