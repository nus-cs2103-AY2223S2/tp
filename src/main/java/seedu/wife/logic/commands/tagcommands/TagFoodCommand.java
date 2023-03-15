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
 * Tag food in WIFE with a specified tag.
 */
public class TagFoodCommand extends Command {
    private Tag tag;
    private Index targetIndex;

    /**
     * Constructor to create a Tag command.
     * @param tag Tag you wish to add to the food item.
     * @param index Index of the food item in the list you wish to tag.
     */
    public TagFoodCommand(Tag tag, Index index) {
        requireNonNull(tag);
        requireNonNull(index);
        this.tag = tag;
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Food> lastShownList = model.getFilteredFoodList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToTag = lastShownList.get(targetIndex.getZeroBased());
        Set<Tag> currFoodTags = foodToTag.getTags();
        currFoodTags.add(this.tag);
        return new CommandResult(String.format(Messages.MESSAGE_SUCCESSFUL_FOOD_TAG, foodToTag, tag));
    }
}
