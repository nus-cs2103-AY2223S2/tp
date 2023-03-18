package seedu.wife.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;

/**
 * Lists all persons in the address book to the user.
 */
public class ListByTagCommand extends Command {

    public static final String COMMAND_WORD = "listbytag";

    public static final String MESSAGE_SUCCESS = "Listed all food with %1$s tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all food with the specific tag.\n"
        + "Example: " + COMMAND_WORD + "dairy";

    private final Tag targetTag;

    public ListByTagCommand(Tag targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredFoodList(food -> food.getTags().contains(this.targetTag));

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.targetTag));
    }
}
