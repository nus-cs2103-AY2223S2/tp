package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;

/**
 * Lists all persons in the address book to the user.
 */
public class ListByTagCommand extends Command {

    public static final String COMMAND_WORD = "listbytag";

    public static final String MESSAGE_SUCCESS = "Listed all food with the following tags:";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows all food with the specified tag.\n"
            + "Example: " + "\n"
            + COMMAND_WORD + "n/Dairy" + "\n"
            + COMMAND_WORD + " n/Dairy n/Healthy";

    private final Set<Tag> targetTags;

    /**
     * Constructor to create a new ListByTag object.
     */
    public ListByTagCommand(Tag targetTag) {
        this.targetTags = new HashSet<Tag>();
        this.targetTags.add(targetTag);
    }

    /**
     * Constructor to create a new ListByTag object.
     */
    public ListByTagCommand(Set<Tag> targetTag) {
        this.targetTags = targetTag;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredFoodList(
            food -> food.getTags().stream().anyMatch(tag -> this.targetTags.contains(tag))
        );

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.targetTags));
    }
}
