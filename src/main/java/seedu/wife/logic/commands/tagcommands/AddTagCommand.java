package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.exceptions.TagStorageFullException;

/**
 * Add a new pre-defined tag.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a new pre-defined tag\n"
            + "Example: " + COMMAND_WORD + " vegetable";
    public static final String TAG_ADD_SUCCESS_MESSAGE = "Tag successfully added: %s";
    private Tag toAdd;

    /**
     * Constructor to create a new AddTagCommand object.
     */
    public AddTagCommand(Tag tag) {
        requireNonNull(tag);
        this.toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException("The tag you try to add is already in the tag list.");
        }

        try {
            model.addTag(toAdd);
        } catch (TagStorageFullException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(TAG_ADD_SUCCESS_MESSAGE, toAdd.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && toAdd.equals(((AddTagCommand) other).toAdd)); // state check
    }
}
