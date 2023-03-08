package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * Edits the details of an existing patient in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This patient already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the patient in the filtered patient list to
     *                             edit
     * @param editPersonDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPerson(patientToEdit, editPersonDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPerson(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(patientToEdit, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of
     * {@code patientToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Patient createEditedPerson(Patient patientToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse((Name) patientToEdit.getName());

        return new Patient(updatedName);
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
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will
     * replace the
     * corresponding field value of the patient.
     */
    public static class EditPersonDescriptor {
        private Name name;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName());
        }
    }
}
