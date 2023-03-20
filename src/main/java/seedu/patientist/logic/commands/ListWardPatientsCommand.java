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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose wards matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Keywords must be alphanumeric.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Block1WardA Block2WardC";

    private final PatientInWardPredicate predicate;

    public ListWardPatientsCommand(PatientInWardPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof ListWardPatientsCommand // instanceof handles nulls
                   && predicate.equals(((ListWardPatientsCommand) other).predicate));
    }
}
