package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.exceptions.TagNotFoundException;
import seedu.wife.model.tag.exceptions.TagStorageEmptyException;

/**
 * Delete a pre-defined tag.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deltag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a pre-defined tag\n"
            + "Example: " + COMMAND_WORD + " n/vegetable";
    public static final String TAG_DELETE_SUCCESS_MESSAGE = "Tag successfully deleted: %s";
    public static final String TAG_DELETE_UNSUCCESS_MESSAGE = "The tag you are trying to delete does not exist.";
    private Tag toDelete;

    /**
     * Constructor to create a new DeleteTagCommand object.
     */
    public DeleteTagCommand(Tag tag) {
        requireNonNull(tag);
        this.toDelete = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toDelete)) {
            throw new CommandException(TAG_DELETE_UNSUCCESS_MESSAGE);
        }

        try {
            model.deleteTag(toDelete);
        } catch (TagNotFoundException e) {
            throw new CommandException(e.getMessage());
        } catch (TagStorageEmptyException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(TAG_DELETE_SUCCESS_MESSAGE, toDelete.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && toDelete.equals(((DeleteTagCommand) other).toDelete)); // state check
    }
}
