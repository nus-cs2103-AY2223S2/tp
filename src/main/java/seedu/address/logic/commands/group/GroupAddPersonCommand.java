package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

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
            + PREFIX_GROUP + "CS2103 ";

    public static final String MESSAGE_SUCCESS = "New person added to Group %1$s: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    public static final String MESSAGE_MISSING_PERSON = "No such person in the address book";

    private final Person toAdd;

    private final Group targetGroup;

    /**
     * Creates an AddCommand to add the specified {@code Name}
     */
    public GroupAddPersonCommand(Person person, Group group) {
        requireNonNull(person);
        toAdd = person;
        targetGroup = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_MISSING_PERSON);
        }

        if (!model.hasGroup(targetGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP)
        }

        model.addPersonInGroup(toAdd, targetGroup);
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
