package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.patient.PatientInWardPredicate;


/**
 * Returns all the people that are tag to a particular ward
 */
public class ListWardPatientsCommand extends Command {
    public static final String COMMAND_WORD = "lswardpat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all patients in ward __\n"
            + "Example: " + COMMAND_WORD + " Block2WardA\n"
            + "Alice Peter John";

    private final String wardToBeChecked;

    public ListWardPatientsCommand(String ward) {
        wardToBeChecked = ward;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new PatientInWardPredicate(wardToBeChecked));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

}
