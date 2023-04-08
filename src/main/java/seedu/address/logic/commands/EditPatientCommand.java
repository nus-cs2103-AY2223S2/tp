package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing patient in the address book.
 */
public class EditPatientCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "edit-ptn";
    public static final String SHORTHAND_COMMAND_WORD = "ep";


    private static final String MESSAGE_USAGE = COMMAND_WORD + " (short form: " + SHORTHAND_COMMAND_WORD + ")"
            + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patients list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_REMARK + "REMARK "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_HEIGHT + "1.63 ";


    private static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    private static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditPatientCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return MESSAGE_EDIT_PATIENT_SUCCESS;
    }

    public static String getMessageDuplicate() {
        return MESSAGE_DUPLICATE_PATIENT;
    }

    public static String getMessageFailure() {
        return MESSAGE_NOT_EDITED;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        changeAssignmentInDoctors(patientToEdit, editedPatient);
        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient),
                editedPatient);
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Height updatedHeight = editPatientDescriptor.getHeight().orElse(patientToEdit.getHeight());
        Weight updatedWeight = editPatientDescriptor.getWeight().orElse(patientToEdit.getWeight());
        Diagnosis updatedDiagnosis = editPatientDescriptor.getDiagnosis().orElse(patientToEdit.getDiagnosis());
        Status updatedStatus = editPatientDescriptor.getStatus().orElse(patientToEdit.getStatus());
        Remark updatedRemark = editPatientDescriptor.getRemark().orElse(patientToEdit.getRemark());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());
        Set<Doctor> updatedDoctors = editPatientDescriptor.getDoctors().orElse(patientToEdit.getDoctors());

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedHeight, updatedWeight,
                updatedDiagnosis, updatedStatus, updatedRemark, updatedTags, updatedDoctors);
    }

    /**
     * Assigns all doctors of {@code patientToEdit} to {@code editedPatient}.
     *
     * @param patientToEdit a patient to edit
     * @param editedPatient a new edited patient
     */
    private static void changeAssignmentInDoctors(Patient patientToEdit,
                                                  Patient editedPatient) {
        assert patientToEdit != null;
        assert editedPatient != null;

        for (Doctor doctor : patientToEdit.getDoctors()) {
            doctor.removePatientIfAssigned(patientToEdit);
            doctor.assignPatient(editedPatient);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        // state check
        EditPatientCommand e = (EditPatientCommand) other;
        return index.equals(e.index)
                && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Height height;
        private Weight weight;
        private Diagnosis diagnosis;
        private Status status;
        private Remark remark;
        private Set<Tag> tags;
        private Set<Doctor> doctors;

        public EditPatientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setHeight(toCopy.height);
            setWeight(toCopy.weight);
            setDiagnosis(toCopy.diagnosis);
            setStatus(toCopy.status);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
            setDoctors(toCopy.doctors);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, height, weight, diagnosis, status, remark, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }
        public void setDiagnosis(Diagnosis diagnosis) {
            this.diagnosis = diagnosis;
        }
        public Optional<Diagnosis> getDiagnosis() {
            return Optional.ofNullable(diagnosis);
        }
        public void setStatus(Status status) {
            this.status = status;
        }
        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }
        public void setRemark(Remark remark) {
            this.remark = remark;
        }
        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
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
        /**
         * Sets {@code doctors} to this object's {@code doctors}.
         * A defensive copy of {@code doctors} is used internally.
         */
        public void setDoctors(Set<Doctor> doctors) {
            this.doctors = (doctors != null) ? new HashSet<>(doctors) : null;
        }

        /**
         * Returns an unmodifiable doctor set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code doctors} is null.
         */
        public Optional<Set<Doctor>> getDoctors() {
            return (doctors != null) ? Optional.of(Collections.unmodifiableSet(doctors)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            // state check
            EditPatientDescriptor e = (EditPatientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getHeight().equals(e.getHeight())
                    && getWeight().equals(e.getWeight())
                    && getDiagnosis().equals(e.getDiagnosis())
                    && getStatus().equals(e.getStatus())
                    && getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags())
                    && getDoctors().equals(e.getDoctors());
        }
    }
}
