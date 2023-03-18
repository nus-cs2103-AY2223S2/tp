package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class ParentDeleteCommand extends ParentCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "student " + COMMAND_WORD
            + ": Deletes the parent identified by their phone number.\n"
            + "Parameters: Phone Number (must be a positive integer)\n"
            + "Example: parent delete " + PREFIX_PHONEPARENT + "91234567";

    public static final String MESSAGE_DELETE_PARENT_SUCCESS = "Deleted Parent: %1$s";

    private final Phone phoneNumber;

    /**
     * Creates a ParentDeleteCommand to delete the specified {@code Parent}
     */
    public ParentDeleteCommand(Phone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Parent> parents = model.getFilteredParentList();
        for (Parent parent : parents) {
            if (parent.getPhone().equals(phoneNumber)) {
                model.deleteParent(parent);
                return new CommandResult(String.format(MESSAGE_DELETE_PARENT_SUCCESS, parent));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_PARENT_DISPLAYED_NUMBER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentDeleteCommand // instanceof handles nulls
                && phoneNumber.equals(((ParentDeleteCommand) other).phoneNumber)); // state check
    }
}
