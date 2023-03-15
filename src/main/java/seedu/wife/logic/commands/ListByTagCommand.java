package seedu.wife.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.model.Model.PREDICATE_SHOW_ALL_FOODS;

import seedu.wife.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListByTagCommand extends Command {

    public static final String COMMAND_WORD = "listbytag";

    public static final String MESSAGE_SUCCESS = "Listed all food with %1$s tag";

    public final Tag targetTag;
    
    public void ListByTagCommand(Tag targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Food> lastShownList = model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.targetTag));
    }
}
