package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * Command to untag a food item.
 */
public class UntagFoodCommand extends Command {
    public static final String COMMAND_WORD = "untag";
    public static final String MESSAGE_USAGE = "Remove a tag from a food item in your list.\n"
            + "Format:\n"
            + "untag <index> n/<tag name>\n"
            + "example: untag 1 n/Vege";
    private Tag tag;
    private Index index;

    /**
     * Constructor to create a new UntagFoodCommand object.
     */
    public UntagFoodCommand(String tagName, Index index) {
        requireNonNull(tagName);
        requireNonNull(index);
        this.tag = new Tag(tagName);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Food foodToUntag = UntagFoodCommand.getFoodToUntag(model, tag, index);
        Set<Tag> foodTags = foodToUntag.getCurrentTags();

        if (!foodTags.contains(tag)) {
            throw new CommandException(String.format(Messages.MESSAGE_TAG_NOT_FOUND, tag.getTagName()));
        }

        foodTags.remove(tag);
        Food editedFood = foodToUntag.createNewFoodWithNewTags(foodToUntag, foodTags);
        model.setFood(foodToUntag, editedFood);

        return new CommandResult(String.format(Messages.MESSAGE_SUCCESSFUL_FOOD_UNTAG,
                 tag.getTagName(), editedFood.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntagFoodCommand// instanceof handles nulls
                && tag.equals(((UntagFoodCommand) other).tag))
                && index.equals(((UntagFoodCommand) other).index); // state check
    }

    //@@author rmj1405-reused
    //Adapted from https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/src/main/java/seedu
    // /foodrem/logic/commands/tagcommands/TagCommandUtil.java
    // with minor modifications
    public static Food getFoodToUntag(Model model, Tag tag, Index index) throws CommandException {
        requireNonNull(model);
        requireNonNull(tag);
        requireNonNull(index);

        if (!model.hasTag(tag)) {
            throw new CommandException(String.format(Messages.MESSAGE_TAG_NOT_FOUND, tag.getTagName()));
        }

        List<Food> foodList = model.getFilteredFoodList();

        if (index.getZeroBased() >= foodList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        return foodList.get(index.getZeroBased());
    }
    //@@author
}
