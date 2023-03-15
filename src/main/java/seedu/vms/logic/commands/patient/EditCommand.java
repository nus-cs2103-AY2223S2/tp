package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;
import static seedu.vms.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.util.CollectionUtil;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.GroupName;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * Edits the details of an existing patient in the bloodType book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_GROUP = "patient";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + DELIMITER + PREFIX_NAME + " NAME] "
            + "[" + DELIMITER + PREFIX_PHONE + " PHONE] "
            + "[" + DELIMITER + PREFIX_DOB + " Date of Birth] "
            + "[" + DELIMITER + PREFIX_BLOODTYPE + " BLOODTYPE] "
            + "[" + DELIMITER + PREFIX_ALLERGY + " ALLERGY]...\n"
            + "[" + DELIMITER + PREFIX_VACCINATION + " VACCINE]...\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1 "
            + DELIMITER + PREFIX_PHONE + " 91234567 "
            + DELIMITER + PREFIX_DOB + " 2000-02-18";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the patient manager.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index                 of the patient in the filtered patient list to
     *                              edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Integer, IdData<Patient>> lastShownList = model.getFilteredPatientList();

        if (!lastShownList.containsKey(index.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased()).getValue();
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        model.setPatient(index.getZeroBased(), editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of
     * {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Dob updatedDob = editPatientDescriptor.getDob().orElse(patientToEdit.getDob());
        BloodType updatedBloodType = editPatientDescriptor.getBloodType().orElse(patientToEdit.getBloodType());
        Set<GroupName> updatedAllergies = editPatientDescriptor.getAllergies().orElse(patientToEdit.getAllergy());
        Set<GroupName> updatedVaccines = editPatientDescriptor.getVaccines().orElse(patientToEdit.getVaccine());

        return new Patient(updatedName, updatedPhone, updatedDob, updatedBloodType, updatedAllergies, updatedVaccines);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will
     * replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Dob dob;
        private BloodType bloodType;
        private Set<GroupName> allergies;
        private Set<GroupName> vaccines;

        public EditPatientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code allergies} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setDob(toCopy.dob);
            setBloodType(toCopy.bloodType);
            setAllergies(toCopy.allergies);
            setVaccines(toCopy.vaccines);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, dob, bloodType, allergies, vaccines);
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

        public void setDob(Dob dob) {
            this.dob = dob;
        }

        public Optional<Dob> getDob() {
            return Optional.ofNullable(dob);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        /**
         * Sets {@code allergies} to this object's {@code allergies}.
         * A defensive copy of {@code allergies} is used internally.
         */
        public void setAllergies(Set<GroupName> allergies) {
            this.allergies = (allergies != null) ? new HashSet<>(allergies) : null;
        }

        /**
         * Returns an unmodifiable allergy set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code allergies} is null.
         */
        public Optional<Set<GroupName>> getAllergies() {
            return (allergies != null) ? Optional.of(Collections.unmodifiableSet(allergies)) : Optional.empty();
        }

        /**
         * Sets {@code vaccines} to this object's {@code vaccines}.
         * A defensive copy of {@code vaccines} is used internally.
         */
        public void setVaccines(Set<GroupName> vaccines) {
            this.vaccines = (vaccines != null) ? new HashSet<>(vaccines) : null;
        }

        /**
         * Returns an unmodifiable vaccine set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code vaccines} is null.
         */
        public Optional<Set<GroupName>> getVaccines() {
            return (vaccines != null) ? Optional.of(Collections.unmodifiableSet(vaccines)) : Optional.empty();
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
                    && getDob().equals(e.getDob())
                    && getBloodType().equals(e.getBloodType())
                    && getAllergies().equals(e.getAllergies())
                    && getVaccines().equals(e.getVaccines());
        }
    }
}
