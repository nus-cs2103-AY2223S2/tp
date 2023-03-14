package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class ParentDeleteCommand extends ParentCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by their index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Phone phoneNumber;
    private final Name parentName;

    /**
     * Creates a StudentDeleteCommand to delete the specified {@code Student}
     */
    public ParentDeleteCommand(Name parentName, Phone phoneNumber) {
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Parent> parents = model.getFilteredParentList();

        for (Parent parent : parents) {
            if (parent.getPhone().equals(parentName)
                    && parent.getStudentClass().equals(phoneNumber)) {
                model.deleteParent(parent);
                return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, parent));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentDeleteCommand // instanceof handles nulls
                && phoneNumber.equals(((ParentDeleteCommand) other).phoneNumber)
                && parentName.equals(((ParentDeleteCommand) other).parentName)); // state check
    }
}