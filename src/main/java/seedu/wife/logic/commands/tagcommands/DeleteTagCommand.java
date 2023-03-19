package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;

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
        List<Tag> lastShownList = List.copyOf(model.getTagList());
        String deletedTagSuccessMessage = TAG_DELETE_SUCCESS_MESSAGE;

        for (Tag tag : lastShownList) {
            if (model.hasTag(this.toDelete)) {
                model.deleteTag(this.toDelete);
                deletedTagSuccessMessage += "\n" + this.toDelete;
            }
        }

        return new CommandResult(deletedTagSuccessMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && toDelete.equals(((DeleteTagCommand) other).toDelete)); // state check
    }
}
