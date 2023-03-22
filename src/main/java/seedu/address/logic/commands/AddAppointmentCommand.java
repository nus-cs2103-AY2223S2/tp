package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Appointment;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

// notice that add appointment is essentially edit a person!
public class AddAppointmentCommand extends MakeAppointmentCommand {

    public static final String COMMAND_WORD = "makeApp";
    public static final String MESSAGE_SUCCESS = "Appointment successfully made";

    private Appointment toAdd;

    public AddAppointmentCommand(Appointment toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonListByName(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
