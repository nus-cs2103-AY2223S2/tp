package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;

/**
 * Deletes doctor from the address book
 */
public class DeleteDoctorCommand extends Command implements CommandInterface {
    public static final String COMMAND_WORD = "del-doc";
    public static final String SHORTHAND_COMMAND_WORD = "dd";

    private static final String MESSAGE_USAGE = COMMAND_WORD + " (short form: " + SHORTHAND_COMMAND_WORD + ")"
            + ": Deletes the doctor identified by the index number used in the displayed doctor list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    private static final String MESSAGE_DELETE_DOCTOR_SUCCESS = "Deleted Doctor: %1$s";

    private Index targetIndex;

    public DeleteDoctorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return MESSAGE_DELETE_DOCTOR_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDoctor(doctorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDoctorCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDoctorCommand) other).targetIndex)); // state check
    }
}
