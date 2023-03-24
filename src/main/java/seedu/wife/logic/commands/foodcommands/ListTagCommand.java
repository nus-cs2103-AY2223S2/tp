package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;

/**
 * List all pre-defined tags.
 */
public class ListTagCommand extends Command {
    public static final String COMMAND_WORD = "listtag";
    private static final String MESSAGE_SUCCESS = "Listed all tags.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        Object[] tags = model.getFilteredTagList().toArray();

        StringBuilder sb = new StringBuilder();
        sb.append("Here are your existing tags: ");
        for (Object tag : tags) {
            Tag thisTag = (Tag) tag;
            sb.append(thisTag.getTagName());
            sb.append(System.lineSeparator());
        }

        String allTags = sb.toString();

        return new CommandResult(allTags);
    }
}
