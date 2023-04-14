package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.library.commons.core.Messages;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import seedu.library.model.tag.Tag;

/**
 * Deletes a tag identified by its name from the tag list.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "dtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag identified by the name used in displayed tag list.\n"
            + "Parameters: Tag name\n"
            + "Example: " + COMMAND_WORD + " MaleProtagonist";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %1$s";
    public static final String PREVENT_USED_TAG_DELETION = "Cannot delete tags that are currently used by bookmarks";

    private final Tag tag;

    public DeleteTagCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.tagInUse(tag)) {
            throw new CommandException(PREVENT_USED_TAG_DELETION);
        }

        if (!model.hasTag(tag)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_NAME);
        }

        model.deleteTag(tag);
        model.updateSelectedIndex(-1);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tag), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && tag.equals(((DeleteTagCommand) other).tag)); // state check
    }
}
