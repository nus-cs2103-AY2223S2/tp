package seedu.wife.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.core.Messages.MESSAGE_DUPLICATE_TAG;
import static seedu.wife.commons.core.Messages.MESSAGE_TAG_CREATE_SUCCESS;

import java.util.HashSet;
import java.util.Set;

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
            + "Example: "
            + COMMAND_WORD + " n/vegetable\n"
            + COMMAND_WORD + " n/vegetable n/dairy n/grains\n";

    private Set<Tag> toCreate;

    /**
     * Constructor to create a new CreateTagCommand object.
     */
    public CreateTagCommand(Tag tags) {
        requireNonNull(tags);
        this.toCreate = new HashSet<Tag>();
        this.toCreate.add(tags);
    }

    /**
     * Constructor to create a new CreateTagCommand object.
     * @param tags
     */
    public CreateTagCommand(Set<Tag> tags) {
        requireNonNull(tags);
        this.toCreate = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String messageString = MESSAGE_TAG_CREATE_SUCCESS;

        for (Tag tag: this.toCreate) {
            if (model.hasTag(tag)) {
                continue;
            }

            try {
                model.createTag(tag);
                messageString += "\n" + tag.getTagName();
            } catch (TagStorageFullException e) {
                throw new CommandException(e.getMessage());
            }
        }

        if (messageString.equals(MESSAGE_TAG_CREATE_SUCCESS)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        return new CommandResult(messageString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTagCommand // instanceof handles nulls
                && toCreate.equals(((CreateTagCommand) other).toCreate)); // state check
    }
}
