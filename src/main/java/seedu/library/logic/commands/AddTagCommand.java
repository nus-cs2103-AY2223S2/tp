package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import seedu.library.model.tag.Tag;

/**
 * Adds a bookmark to the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to list of tags. "
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Literature Reading "
            + PREFIX_TAG + "Holiday reading list";

    public static final String MESSAGE_SUCCESS = "New tags added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAGS = "This tag already exists in the tag list";

    private final Set<Tag> toAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Set<Tag> tagList) {
        requireNonNull(tagList);
        toAdd = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAGS);
        }

        model.addTags(toAdd);
        model.updateSelectedIndex(-1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && toAdd.equals(((AddTagCommand) other).toAdd));
    }
}

