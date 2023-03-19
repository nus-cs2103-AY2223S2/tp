package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.exceptions.TagStorageFullException;

/**
 * Create a new pre-defined tag.
 */
public class CreateTagCommand extends Command {
    public static final String COMMAND_WORD = "createtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Create a new pre-defined tag\n"
            + "Example: " + COMMAND_WORD + " n/vegetable";
    public static final String MESSAGE_TAG_CREATE_SUCCESS = "Tag successfully created: %s";
    public static final String MESSAGE_DUPLICATE_TAG = "The tag you are trying to create has been created before.";
    private Tag toCreate;

    /**
     * Constructor to create a new CreateTagCommand object.
     */
    public CreateTagCommand(Tag tag) {
        requireNonNull(tag);
        this.toCreate = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        try {
            model.createTag(toCreate);
        } catch (TagStorageFullException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_TAG_CREATE_SUCCESS, toCreate.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTagCommand // instanceof handles nulls
                && toCreate.equals(((CreateTagCommand) other).toCreate)); // state check
    }
}
