package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;

/**
 * Adds a person to a group in the address book.
 */
public class GroupAddPersonCommand extends GroupCommand {

    public static final String SUB_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
            + ": Adds a person to an existing group from the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD
            + PREFIX_NAME + "John Doe "
            + PREFIX_GROUP_NAME + "CS2103 ";

    public static final String MESSAGE_SUCCESS = "New person added to Group %1$s: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the group";

    public static final String MESSAGE_MISSING_PERSON = "No such person in the address book";

    private final Name toAdd;

    private final String targetGroup;

    /**
     * Creates an AddCommand to add the specified {@code Name}
     */
    public GroupAddPersonCommand(Name person, String groupName) {
        requireNonNull(person);
        toAdd = person;
        targetGroup = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // TODO: Check if person exists in list

        // TODO: Check if group already has person toAdd

        // TODO: Add person to group list
        // model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetGroup, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupAddPersonCommand // instanceof handles nulls
                && toAdd.equals(((GroupAddPersonCommand) other).toAdd)
                && targetGroup.equals(((GroupAddPersonCommand) other).targetGroup));
    }
}
