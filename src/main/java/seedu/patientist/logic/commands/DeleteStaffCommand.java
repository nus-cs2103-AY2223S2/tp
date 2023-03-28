package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;

import java.util.List;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.staff.DummyStaff;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;


/**
 * Deletes a staff member using their unique id number.
 */
public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "delstf";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the staff identified by their id number.\n"
            + "Parameters: id/ID_NUMBER\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "A123456789B";

    public static final String MESSAGE_DELETE_STAFF_ID_SUCCESS = "Deleted Staff with id: %1$s";

    public static final String MESSAGE_STAFF_ID_NOT_FOUND = "Staff with id: %1$s not found";

    private final IdNumber idNumber;

    public DeleteStaffCommand(IdNumber idNumber) {
        requireNonNull(idNumber);
        this.idNumber = idNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String> wardNames = model.getWardNames();
        Staff staffToMatch = new DummyStaff(idNumber);

        for (String wardName : wardNames) {
            Ward ward = model.getWard(wardName);
            if (ward.containsStaff(staffToMatch)) {
                ward.deleteStaffById(idNumber, staffToMatch);
                return new CommandResult(String.format(MESSAGE_DELETE_STAFF_ID_SUCCESS, idNumber));
            }
        }
        throw new CommandException(String.format(MESSAGE_STAFF_ID_NOT_FOUND, idNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same instance
                || (other instanceof DeleteStaffCommand)
                && this.idNumber == ((DeleteStaffCommand) other).idNumber;
    }
}