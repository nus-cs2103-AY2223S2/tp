package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Creates a group in the address book.
 */
public class GroupCreateCommand extends GroupCommand {
    public static final String SUB_COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD
            + ": Create a group in the address book. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP NAME\n"
            + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    // TODO: Add constructor with Group and incorporate Group model
    private final String groupToAdd;

    /**
     * Creates a GroupCreateCommand to create a Group in the address
     */
    public GroupCreateCommand(String group) {
        requireNonNull(group);
        groupToAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // TODO: Check duplicate group

        // TODO: Add Group
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCreateCommand // instanceof handles nulls
                && groupToAdd.equals(((GroupCreateCommand) other).groupToAdd));
    }
}
