package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.User;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.util.SampleDataUtil;

/**
 * Command for generating sample data in EduMate.
 * It is used mainly for manual testing.
 */
public class SampleCommand extends Command {

    public static final String COMMAND_WORD = "sample";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Populate the EduMate with "
            + "sample data of the given size between 1 and 100.\n"
            + "Parameters: SIZE (non-negative integer less than 101)\n"
            + "Example: " + COMMAND_WORD + " 25";

    private final int size;

    public SampleCommand(int size) {
        this.size = size;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        User user = model.getUser();
        model.setEduMate(SampleDataUtil.getSampleEduMate(size));
        model.setUser(user);

        Set<ModuleTag> userModuleTags = user.getImmutableModuleTags();

        model.getObservablePersonList().forEach(person ->
                person.setCommonModules(userModuleTags));

        model.updateObservablePersonList();
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getObservablePersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SampleCommand // instanceof handles nulls
                && size == ((SampleCommand) other).size); // state check
    }
}

