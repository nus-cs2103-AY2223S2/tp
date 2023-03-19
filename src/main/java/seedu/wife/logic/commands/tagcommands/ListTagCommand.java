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
    private static final String MESSAGE_SUCCESS = "Listed all tags.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        Object[] tags = model.getFilteredTagList().toArray();

        // for testing purpose: to list all the tags
        for (Object tag : tags) {
            System.out.println((Tag) tag);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
