package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * Adds a hospital staff member to patientist.
 */
public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "addstf";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff member to patientist. "
                                                            + "Parameters: "
                                                            + PREFIX_NAME + "NAME "
                                                            + PREFIX_ID + "ID "
                                                            + PREFIX_WARD + "WARD "
                                                            + PREFIX_PHONE + "PHONE "
                                                            + PREFIX_EMAIL + "EMAIL "
                                                            + PREFIX_ADDRESS + "ADDRESS "
                                                            + "[" + PREFIX_TAG + "TAG]...\n"
                                                            + "Example: " + COMMAND_WORD + " "
                                                            + PREFIX_NAME + "Mary Jane "
                                                            + PREFIX_ID + "A17625H "
                                                            + PREFIX_WARD + "Block B Ward 2 "
                                                            + PREFIX_PHONE + "96421234 "
                                                            + PREFIX_EMAIL + "mj@example.com "
                                                            + PREFIX_ADDRESS + "789 Hospice St, #06-16 "
                                                            + PREFIX_TAG + "12B";

    public static final String MESSAGE_SUCCESS = "New staff member added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This staff member already exists in the patientist book";

    public static final String MESSAGE_WARD_NOT_FOUND = "Ward not found: %1$s";

    private final String wardToAdd;
    private final Staff toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Staff}
     *
     * @param staff The staff member to be created.
     */
    public AddStaffCommand(String ward, Staff staff) {
        requireNonNull(staff, ward);
        wardToAdd = ward;
        toAdd = staff;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!model.hasWard(new Ward(wardToAdd))) {
            throw new CommandException(String.format(MESSAGE_WARD_NOT_FOUND, wardToAdd));
        }

        model.addStaff(toAdd, model.getWard(wardToAdd));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                    && toAdd.equals(((AddStaffCommand) other).toAdd)
                    && wardToAdd.equals(((AddStaffCommand) other).wardToAdd));
    }
}
