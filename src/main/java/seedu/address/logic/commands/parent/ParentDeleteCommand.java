package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;

/**
 * Deletes a parent identified using its name and phone number displayed on PowerConnect's parent list.
 */
public class ParentDeleteCommand extends ParentCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the parent identified by their name and phone number used.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONEPARENT + "PHONE\n"
            + "Example: parent " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567";

    public static final String MESSAGE_DELETE_PARENT_SUCCESS = "Deleted Parent: %1$s Phone Number: %2$s";

    private final Phone phoneNumber;
    private final Name parentName;

    /**
     * Creates a ParentDeleteCommand to delete the specified {@code Parent}
     */
    public ParentDeleteCommand(Name parentName, Phone phoneNumber) {
        requireNonNull(parentName);
        requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.parentName = parentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasParent(parentName, phoneNumber)) {
            throw new CommandException(Messages.MESSAGE_PARENT_NOT_FOUND);
        }

        Parent parentToDelete = model.getParent(parentName, phoneNumber);

        if (parentToDelete.hasStudents()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARENT_DELETE);
        }

        model.deleteParent(parentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PARENT_SUCCESS, parentToDelete.getName(),
                parentToDelete.getPhone()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentDeleteCommand // instanceof handles nulls
                && phoneNumber.equals(((ParentDeleteCommand) other).phoneNumber)); // state check
    }
}
