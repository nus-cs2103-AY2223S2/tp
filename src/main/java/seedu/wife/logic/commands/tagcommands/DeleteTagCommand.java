package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * Delete a pre-defined tag.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deltag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a pre-defined tag\n"
            + "Example: " + COMMAND_WORD + " n/vegetable";
    public static final String TAG_DELETE_SUCCESS_MESSAGE = "Tag successfully deleted:";
    public static final String TAG_DELETE_UNSUCCESS_MESSAGE = "The tag you are trying to delete does not exist.";
    private Set<Tag> toDelete;

    /**
     * Constructor to create a new DeleteTagCommand object.
     */
    public DeleteTagCommand(Tag tag) {
        requireNonNull(tag);
        HashSet<Tag> taglist = new HashSet<Tag>();
        taglist.add(tag);
        this.toDelete = taglist;
    }

    /**
     * Constructor to create a new DeleteTagCommand object.
     */
    public DeleteTagCommand(Set<Tag> tag) {
        requireNonNull(tag);
        this.toDelete = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownTagList = List.copyOf(model.getTagList());
        List<Food> lastShownFoodList = List.copyOf(model.getFoodList());
        String deletedTagSuccessMessage = TAG_DELETE_SUCCESS_MESSAGE;

        // Deletes tag from food
        for (Tag tag : toDelete) {
            for (Food food : lastShownFoodList) {
                food.removeTag(tag);
            }
        }

        // Deletes tag from WIFE
        for (Tag tag : toDelete) {
            if (model.hasTag(tag)) {
                model.deleteTag(tag);
                deletedTagSuccessMessage += "\n" + this.toDelete;
            }
        }

        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        return new CommandResult(deletedTagSuccessMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && toDelete.equals(((DeleteTagCommand) other).toDelete)); // state check
    }
}
