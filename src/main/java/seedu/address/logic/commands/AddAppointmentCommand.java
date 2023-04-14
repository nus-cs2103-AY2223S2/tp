package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalCondition;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds an appointment to a person.
 */
// notice that add appointment is essentially edit a person!
public class AddAppointmentCommand extends MakeAppointmentCommand {

    public static final String COMMAND_WORD = "makeApp";
    public static final String MESSAGE_SUCCESS = "Appointment successfully made";
    public static final String APPOINTMENT_CLASH = "This appointment has clash with existing arranged appointments";

    private final Index index;
    private Appointment appointmentToAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Index index, Appointment appointmentToAdd) {
        requireNonNull(index);
        requireNonNull(appointmentToAdd);
        this.index = index;
        this.appointmentToAdd = appointmentToAdd;
    }

    // should check for possible clashes with other appointments
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, appointmentToAdd);

        if (model.hasClash(editedPerson, index)) {
            throw new CommandException(APPOINTMENT_CLASH);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private static Person createEditedPerson(Person personToEdit, Appointment appointmentToAdd) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Age updatedAge = personToEdit.getAge();
        LocalDateTime updatedTime = personToEdit.getTime();
        MedicalCondition updatedMedicalCondition = personToEdit.getMedicalCondition();
        Nric nric = personToEdit.getNric();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedAge,
                updatedTags, updatedTime, updatedMedicalCondition, appointmentToAdd, nric);
    }
}
