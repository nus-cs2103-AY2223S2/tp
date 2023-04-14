package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.library.model.Model;

/**
 * Lists all tags in the tag list to the user.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "tags";

    public static final String MESSAGE_SUCCESS = "Listed all tags: ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String test = model.tagListToString();
        model.updateSelectedIndex(-1);
        return new CommandResult(MESSAGE_SUCCESS + test);
    }
}

