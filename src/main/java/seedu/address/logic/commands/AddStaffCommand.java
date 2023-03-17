package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNSUPPORTED_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Staff;

/**
 * Manages adding of Staff
 */
public class AddStaffCommand extends AddCommand {
    public static final String COMMAND_WORD = "addstaff";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the shop. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "temp intern "
            + PREFIX_TAG + "leaving 2nd march";
    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This staff already registered";


    /**
     * Constructs command that adds staff to the model
     *
     * @param staff Staff to be added
     */
    public AddStaffCommand(Staff staff) {
        super(staff);
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
//        requireNonNull(model);
//        Staff toAdd = (Staff) this.toAdd;
//        if (model.hasStaff(toAdd.getId())) {
//            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
//        }
//
//        model.addStaff(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        throw new CommandException(MESSAGE_UNSUPPORTED_COMMAND);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd));
    }
}
