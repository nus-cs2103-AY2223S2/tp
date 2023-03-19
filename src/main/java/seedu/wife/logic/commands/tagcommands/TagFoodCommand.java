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
 * Command to tag food.
 */
public class TagFoodCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    private Tag tag;
    private Index index;

    /**
     * Constructor to create a new TagFoodCommand object.
     */
    public TagFoodCommand(String tagName, Index index) {
        requireNonNull(tagName);
        requireNonNull(index);
        this.tag = new Tag(tagName);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Food foodToTag = TagFoodCommand.getFoodToTag(model, this.tag, this.index);
        Set<Tag> foodTags = foodToTag.getTags();

        if (foodTags.contains(this.tag)) {
            throw new CommandException(String.format(Messages.MESSAGE_DOUBLE_TAG,
                    foodToTag.toString(), tag.getTagName()));
        }

        foodTags.add(tag);
        Food editedFood = foodToTag.createNewFoodWithNewTags(foodTags);
        model.setFood(foodToTag, editedFood);

        return new CommandResult(String.format(Messages.MESSAGE_SUCCESSFUL_FOOD_TAG,
                editedFood.toString(), tag.getTagName()));
    }

    // Tag utils
    public static Food getFoodToTag(Model model, Tag tag, Index index) throws CommandException {
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

}
