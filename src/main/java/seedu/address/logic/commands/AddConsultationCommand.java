package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;

/**
 * Adds a consultation to the events that the teaching assistant would like to schedule.
 */
public class AddConsultationCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation to the address book. "
            + "Parameters: "
            + PREFIX_CONSULTATION + "CONSULTATION_NAME "
            + "Restrictions: Not allowed to add consultation and student with the same command! \n"
            + "Example: " + COMMAND_WORD + " Consultation/consultEmily -date 10/10/2024 10:00";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in the address book";

    private final Consultation toAdd;

    /**
     * Creates an AddConsultation to add the specified {@code Consultation}.
     *
     * @param consultation the consultation to be added.
     */
    public AddConsultationCommand(Consultation consultation) {
        requireNonNull(consultation);
        toAdd = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        model.addConsultation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, false, false, false, true));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddConsultationCommand // instanceof handles nulls
                && toAdd.equals(((AddConsultationCommand) other).toAdd));
    }
}
