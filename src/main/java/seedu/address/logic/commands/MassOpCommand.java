package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Changes the remark of an existing person in the e-lister.
 */
public class MassOpCommand extends Command {

    //CHECKSTYLE.OFF: VisibilityModifier
    public static List<String> commandWords = new ArrayList<String>(Arrays.asList("mass", "m", "Mass"));
    //CHECKSTYLE.ON: VisibilityModifier

    public static final String MESSAGE_USAGE = commandWords
            + ":Tag or delete Tags people who are filtered by the filter command in the person list.\n"
            + "Filter command must be used first. \n"
            + "Parameters:  \n"
            + "Example: " + commandWords + "tag/delete <TagName>";

    public static final String MESSAGE_SUCCESS = "Tags added: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "Person does not exist.";

    private Tag toAddOrDelete;
    private boolean isDelete;

    /**
     * Creates a MassOpCommand object to perform mass operations
     *
     * @param toAddOrDelete The tag to add
     * @param isDelete Whether the command is deletion
     */
    public MassOpCommand(Tag toAddOrDelete, boolean isDelete) {
        this.toAddOrDelete = toAddOrDelete;
        this.isDelete = isDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int end = lastShownList.size() - 1;
        // lastShownList gets updated every iteration
        for (int i = end; i >= 0; i--) {
            Person personToTag = lastShownList.get(i);
            if (isDelete) {
                model.deleteTag(personToTag, toAddOrDelete);
            } else {
                model.addTag(personToTag, toAddOrDelete);
            }
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()),
                true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MassOpCommand // instanceof handles nulls
                && toAddOrDelete.equals(((MassOpCommand) other).toAddOrDelete))
                && (isDelete == ((MassOpCommand) other).isDelete);
    }

}
