package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.patient.Name;
import seedu.address.ui.CalendarCard;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit_appt";
    public static final String COMMAND_ALIAS = "ea";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
        + "by the index number used in the displayed appointment list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TIMESLOT + "TIMESLOT] "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
        + "[" + PREFIX_DOCTOR + "DOCTOR]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "John Doe "
        + PREFIX_TIMESLOT + "01022023 09:00,01022023 11:00 "
        + PREFIX_DESCRIPTION + "Regular checkup "
        + PREFIX_DOCTOR + "Xiao Lu";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
        "This appointment already exists in the appointment list.";
    public static final String MESSAGE_OVERLAPPING_TIMESLOT = "That timeslot is occupied.";
    public static final String MESSAGE_PATIENT_DOES_NOT_EXIST =
        "The specified patient does not exist in the patient list.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        System.out.println(editAppointmentDescriptor);

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        } else if (editAppointmentDescriptor.getPatientName().isPresent()
            && !model.hasPatientName(editAppointmentDescriptor.getPatientName().get())) {
            throw new CommandException(MESSAGE_PATIENT_DOES_NOT_EXIST);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        } else if (model.hasOverlap(editedAppointment)) {
            throw new CommandException(MESSAGE_OVERLAPPING_TIMESLOT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        CalendarCard.addAppointmentsToCalendar(model.getAppointmentList());
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Timeslot updatedTimeslot = editAppointmentDescriptor.getTimeslot().orElse(appointmentToEdit.getTimeslot());
        Description updatedDescription =
            editAppointmentDescriptor.getDescription().orElse(appointmentToEdit.getDescription());
        Name updatedName = editAppointmentDescriptor.getPatientName().orElse(appointmentToEdit.getPatientName());
        Doctor updatedDoctor = editAppointmentDescriptor.getDoctor().orElse(appointmentToEdit.getDoctor());

        return new Appointment(updatedName, updatedTimeslot, updatedDescription, updatedDoctor);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
            && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Timeslot timeslot;
        private Description description;
        private Name patientName;
        private Doctor doctor;

        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setTimeslot(toCopy.timeslot);
            setDescription(toCopy.description);
            setPatientName(toCopy.patientName);
            setDoctor(toCopy.doctor);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(timeslot, description, patientName, doctor);
        }

        public void setTimeslot(Timeslot timeslot) {
            this.timeslot = timeslot;
        }

        public Optional<Timeslot> getTimeslot() {
            return Optional.ofNullable(timeslot);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setPatientName(Name patientName) {
            this.patientName = patientName;
        }

        public Optional<Name> getPatientName() {
            return Optional.ofNullable(patientName);
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public Optional<Doctor> getDoctor() {
            return Optional.ofNullable(doctor);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getPatientName().equals(e.getPatientName())
                && getTimeslot().equals(e.getTimeslot())
                && getDescription().equals(e.getDescription())
                && getDoctor().equals(e.getDoctor());
        }

        @Override
        public String toString() {
            return "EditAppointmentDescriptor{"
                + "timeslot=" + timeslot
                + ", description=" + description
                + ", patientName=" + patientName
                + ", doctor=" + doctor
                + '}';
        }
    }
}
