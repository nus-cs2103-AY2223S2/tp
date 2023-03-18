package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Changes the remark of an existing person in the e-lister.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the person identified by the index used in the displayed person list.\n"
            + "Parameters: Index(must be positive number) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "New Tag added: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "Person does not exist.";

    private final Tag tagToAdd;
    private final Index targetIndex;

    /**
     * Creates a TagCommand to add a specified tag to the person at a supplied index.
     *
     * @param index The index of the person to add the tag to
     * @param tag The tag to add
     */
    public TagCommand(Index index, Tag tag) {
        this.tagToAdd = tag;
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());
        model.addTag(personToTag, tagToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && targetIndex.equals(((TagCommand) other).targetIndex)
                && tagToAdd.equals(((TagCommand) other).tagToAdd));
    }

}
