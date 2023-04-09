package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.patientist.model.Model;

/**
 * Lists all wards in the patientist book to the user.
 */
public class ListWardsCommand extends Command {
    public static final String COMMAND_WORD = "lsward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all wards";

    public static final String MESSAGE_WARDS_LISTED_OVERVIEW = "%1$d wards listed!";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(
                String.format(MESSAGE_WARDS_LISTED_OVERVIEW, model.getPatientist().getWardList().size()),
                -1, 1, false, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return other instanceof ListPatientsCommand; // instanceof handles nulls
    }
}
