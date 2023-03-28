package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.id.AppointmentId;
import seedu.address.model.patient.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used as their ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TIMESLOT + "TIMESLOT] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TIMESLOT + "01022023 09:00,01022023 11:00"
            + PREFIX_DESCRIPTION + "irregular checkup ";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final AppointmentId appointmentId;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param appointmentId             of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(AppointmentId appointmentId, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(appointmentId);
        requireNonNull(editAppointmentDescriptor);

        this.appointmentId = appointmentId;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();
        List<Appointment> matchingAppointments =
                lastShownList.stream().filter(appt -> appt.getAppointmentId().equals(appointmentId)).collect(
                        Collectors.toList());

        if (matchingAppointments.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_NAME);
        }

        Appointment appointmentToEdit = matchingAppointments.get(0);
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        AppointmentId updatedAppointmentId =
                editAppointmentDescriptor.getAppointmentId().orElse(appointmentToEdit.getAppointmentId());
        Timeslot updatedTimeslot = editAppointmentDescriptor.getTimeslot().orElse(appointmentToEdit.getTimeslot());
        Description updatedDescription =
                editAppointmentDescriptor.getDescription().orElse(appointmentToEdit.getDescription());
        Name updatedName = editAppointmentDescriptor.getPatientName().orElse(appointmentToEdit.getPatientName());
        Set<Tag> updatedTags = editAppointmentDescriptor.getTags().orElse(appointmentToEdit.getTags());

        return new Appointment(updatedAppointmentId, updatedName, updatedTimeslot, updatedDescription,
                updatedTags);
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
        return appointmentId.equals(e.appointmentId)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private AppointmentId appointmentId;
        private Timeslot timeslot;
        private Description description;
        private Name patientName;
        private Set<Tag> tags;

        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setAppointmentId(toCopy.appointmentId);
            setTimeslot(toCopy.timeslot);
            setDescription(toCopy.description);
            setPatientName(toCopy.patientName);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(timeslot, description, patientName, tags);
        }

        public void setAppointmentId(AppointmentId appointmentId) {
            this.appointmentId = appointmentId;
        }

        public Optional<AppointmentId> getAppointmentId() {
            return Optional.ofNullable(appointmentId);
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

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
                    && getTags().equals(e.getTags());
        }
    }
}
