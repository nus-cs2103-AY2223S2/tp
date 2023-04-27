package seedu.wife.logic.commands.tagcommands;

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
    private static final String MESSAGE_SUCCESS = "Here are your existing tags: ";

    private static final String MESSAGE_EMPTY_TAGS = "You have not created any tags! "
            + "You may do so with the createtag command.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        Object[] tags = model.getFilteredTagList().sorted().toArray();

        StringBuilder sb = new StringBuilder();

        if (tags.length == 0) {
            sb.append(MESSAGE_EMPTY_TAGS).append(System.lineSeparator());
            String emptyTags = sb.toString();
            return new CommandResult(emptyTags);
        }

        sb.append(MESSAGE_SUCCESS);
        sb.append(System.lineSeparator());
        for (Object tag : tags) {
            Tag thisTag = (Tag) tag;
            sb.append(thisTag.getTagName());
            sb.append(System.lineSeparator());
        }

        String allTags = sb.toString();

        return new CommandResult(allTags);
    }
}
