package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Creates a group in the address book.
 */
public class GroupCreateCommand extends GroupCommand {
    public static final String SUB_COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = SUB_COMMAND_WORD
            + " : Create a group in the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NAME\n"
            + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " "
            + PREFIX_GROUP + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    private final Group toAdd;

    /**
     * Creates a GroupCreateCommand to create a Group in the address book
     */
    public GroupCreateCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCreateCommand // instanceof handles nulls
                && toAdd.equals(((GroupCreateCommand) other).toAdd));
    }
}
