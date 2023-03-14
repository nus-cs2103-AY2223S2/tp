package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

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
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialty;
import seedu.address.model.person.Yoe;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing doctor in the address book.
 */
public class EditDoctorCommand extends Command {

    public static final String COMMAND_WORD = "edit-doc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the doctor identified "
            + "by the index number used in the displayed doctors list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_SPECIALTY + "SPECIALTY "
            + PREFIX_YOE + "YEARS OF EXPERIENCE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_SPECIALTY + "Family Medicine ";


    public static final String MESSAGE_EDIT_DOCTOR_SUCCESS = "Edited Doctor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This doctor already exists in the address book.";

    private final Index index;
    private final EditDoctorDescriptor editDoctorDescriptor;

    /**
     * @param index of the doctor in the filtered doctor list to edit
     * @param editDoctorDescriptor details to edit the doctor with
     */
    public EditDoctorCommand(Index index, EditDoctorDescriptor editDoctorDescriptor) {
        requireNonNull(index);
        requireNonNull(editDoctorDescriptor);

        this.index = index;
        this.editDoctorDescriptor = new EditDoctorDescriptor(editDoctorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Doctor editedDoctor = createEditedDoctor(doctorToEdit, editDoctorDescriptor);

        if (!doctorToEdit.isSameDoctor(editedDoctor) && model.hasDoctor(editedDoctor)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor));
    }

    /**
     * Creates and returns a {@code Doctor} with the details of {@code doctorToEdit}
     * edited with {@code editDoctorDescriptor}.
     */
    private static Doctor createEditedDoctor(Doctor doctorToEdit, EditDoctorDescriptor editDoctorDescriptor) {
        assert doctorToEdit != null;

        Name updatedName = editDoctorDescriptor.getName().orElse(doctorToEdit.getName());
        Phone updatedPhone = editDoctorDescriptor.getPhone().orElse(doctorToEdit.getPhone());
        Email updatedEmail = editDoctorDescriptor.getEmail().orElse(doctorToEdit.getEmail());
        Specialty updatedSpecialty = editDoctorDescriptor.getSpecialty().orElse(doctorToEdit.getSpecialty());
        Yoe updatedYoe = editDoctorDescriptor.getYoe().orElse(doctorToEdit.getYoe());
        Set<Tag> updatedTags = editDoctorDescriptor.getTags().orElse(doctorToEdit.getTags());

        return new Doctor(updatedName, updatedPhone, updatedEmail, updatedSpecialty, updatedYoe, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDoctorCommand)) {
            return false;
        }

        // state check
        EditDoctorCommand e = (EditDoctorCommand) other;
        return index.equals(e.index)
                && editDoctorDescriptor.equals(e.editDoctorDescriptor);
    }

    /**
     * Stores the details to edit the doctor with. Each non-empty field value will replace the
     * corresponding field value of the doctor.
     */
    public static class EditDoctorDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Specialty specialty;
        private Yoe yoe;
        private Set<Tag> tags;

        public EditDoctorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDoctorDescriptor(EditDoctorDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setSpecialty(toCopy.specialty);
            setYoe(toCopy.yoe);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, specialty, yoe, tags);
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

        public void setSpecialty(Specialty specialty) {
            this.specialty = specialty;
        }

        public Optional<Specialty> getSpecialty() {
            return Optional.ofNullable(specialty);
        }

        public void setYoe(Yoe yoe) {
            this.yoe = yoe;
        }

        public Optional<Yoe> getYoe() {
            return Optional.ofNullable(yoe);
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
            if (!(other instanceof EditDoctorDescriptor)) {
                return false;
            }

            // state check
            EditDoctorDescriptor e = (EditDoctorDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getSpecialty().equals(e.getSpecialty())
                    && getYoe().equals(e.getYoe())
                    && getTags().equals(e.getTags());
        }
    }
}
