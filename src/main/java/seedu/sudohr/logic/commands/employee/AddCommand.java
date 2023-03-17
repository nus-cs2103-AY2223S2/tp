package seedu.sudohr.logic.commands.employee;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Employee;

/**
 * Adds an employee to SudoHR.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an employee to SudoHR. "
            + "Parameters: "
            + PREFIX_ID + "EMPLOYEE_ID "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "777 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New employee added: %1$s";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in SudoHR";
    public static final String MESSAGE_DUPLICATE_EMAIL = "There already exists someone with this email!";
    public static final String MESSAGE_DUPLICATE_PHONE = "There already exists someone with this phone number!";

    private final Employee toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Employee}
     */
    public AddCommand(Employee employee) {
        requireNonNull(employee);
        toAdd = employee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEmployee(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        if (model.hasClashingPhoneNumber(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }

        if (model.hasClashingEmail(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }

        model.addEmployee(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
