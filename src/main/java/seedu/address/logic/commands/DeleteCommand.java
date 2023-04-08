package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using the displayed name from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their name in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " ic/S9876543J";
    private final Nric nric;


    /**
     * Constructor for DeleteCommand
     *
     * @param nric person nric
     */
    public DeleteCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personsToDelete = model.getFilteredPersonList();

        if (personsToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        } else {
            int index = 0;
            for (int i = 0; i < personsToDelete.size(); i++) {
                if (personsToDelete.get(i).getNric().equals(nric)) {
                    index = i;
                    break;
                }
            }
            if (personsToDelete.get(index).getNric().equals(this.nric)) {
                Person deletedPerson = personsToDelete.get(index);

                if (deletedPerson.isPatient()) {
                    Patient deletedPatient = (Patient) deletedPerson;
                    ArrayList<Appointment> patientAppmts = deletedPatient.getPatientAppointments();
                    int patientAppmtsSize = patientAppmts.size();
                    for (int i = 0; i < patientAppmtsSize; i++) {
                        Appointment a = patientAppmts.get(i);
                        model.deleteAppointment(a);
                        deletedPatient.deletePatientAppointment(i);
                        Nric drIc = a.getDrNric();
                        Doctor d = (Doctor) model.retrievePersonByNric(drIc);
                        d.deletePatientAppointment(a);
                    }
                }
                if (deletedPerson.isDoctor()) {
                    Doctor deletedDoctor = (Doctor) deletedPerson;
                    ArrayList<Appointment> drAppmts = deletedDoctor.getPatientAppointments();
                    int drAppmtsSize = drAppmts.size();
                    for (int i = 0; i < drAppmtsSize; i++) {
                        Appointment a = drAppmts.get(0); 
                        model.deleteAppointment(a);
                        deletedDoctor.deletePatientAppointment(a);
                        Nric drIc = a.getPatientNric();
                        Patient p = (Patient) model.retrievePersonByNric(drIc);
                        p.deletePatientAppointment(a);
                    }
                }

                model.deletePerson(deletedPerson);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPerson.getName()));
            } else {
                throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
            }
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && nric.equals(((DeleteCommand) other).nric)); // state check
    }
}
