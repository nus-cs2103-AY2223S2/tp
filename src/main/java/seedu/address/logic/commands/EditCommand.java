package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.*;


/**
 * Edits the details of an existing patient in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the status and/or ward of the "
            + "patient identified by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STATUS + "STATUS " + PREFIX_WARD + "WARD]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS + "GREEN " + PREFIX_WARD + "A1";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in MedInfo.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index                of the patient in the filtered patient list to
     *                             edit
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
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToEdit, editedPatient);
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

        Name updatedName = editPatientDescriptor.getName().orElse((Name) patientToEdit.getName());
        Nric updatedNric = editPatientDescriptor.getNric().orElse((Nric) patientToEdit.getNric());
        Status updatedStatus = editPatientDescriptor.getStatus().orElse((Status) patientToEdit.getStatus());
        Ward updatedWard = editPatientDescriptor.getWard().orElse((Ward) patientToEdit.getWard());

        return new Patient(updatedNric, updatedName, updatedStatus, updatedWard);
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
        private Nric nric;
        private Status status;
        private Ward ward;

        public EditPatientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setNric(toCopy.nric);
            setStatus(toCopy.status);
            setWard(toCopy.ward);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, nric, status, ward);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public void setWard(Ward ward) {
            this.ward = ward;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public Optional<Ward> getWard() {
            return Optional.ofNullable(ward);
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
                    && getNric().equals(e.getNric())
                    && getStatus().equals(e.getStatus())
                    && getWard().equals(e.getWard());
        }
    }
}
