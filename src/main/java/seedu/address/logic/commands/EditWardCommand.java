package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ward.Capacity;
import seedu.address.model.ward.Ward;
import seedu.address.model.ward.WardName;

/**
 * Edits the details of an existing patient in the address book.
 */
public class EditWardCommand extends Command {

    public static final String COMMAND_WORD = "editward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the ward name or capacity "
        + "of the ward identified by the index number used in the displayed ward list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_WARD + "WARD " + PREFIX_CAPACITY + "CAPACITY]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_WARD + "A1" + PREFIX_CAPACITY + "35";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Ward: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_WARD = "This ward already exists in MedInfo.";
    public static final String MESSAGE_EDITED_WARD_INSUFFICIENT_CAPACITY = "The given capacity is insufficient for this ward.";

    private final Index index;
    private final EditWardDescriptor editWardDescriptor;

    /**
     * @param index              of the ward in the filtered ward list to
     *                           edit
     * @param editWardDescriptor details to edit the ward with
     */
    public EditWardCommand(Index index, EditWardDescriptor editWardDescriptor) {
        requireNonNull(index);
        requireNonNull(editWardDescriptor);

        this.index = index;
        this.editWardDescriptor = new EditWardDescriptor(editWardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ward> lastShownList = model.getFilteredWardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_WARD_DISPLAYED_INDEX);
        }

        Ward wardToEdit = lastShownList.get(index.getZeroBased());
        Ward editedWard = createEditedWard(wardToEdit, editWardDescriptor);

        if (!wardToEdit.isSameWard(editedWard) && model.hasWard(editedWard)) {
            throw new CommandException(MESSAGE_DUPLICATE_WARD);
        }

        if (!editedWard.canSupport(wardToEdit.getOccupancy())) {
            throw new CommandException(MESSAGE_EDITED_WARD_INSUFFICIENT_CAPACITY);
        }

        model.setWard(wardToEdit, editedWard);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedWard));
    }

    /**
     * Creates and returns a {@code Patient} with the details of
     * {@code wardToEdit}
     * edited with {@code editWardDescriptor}.
     */
    private static Ward createEditedWard(Ward wardToEdit, EditWardDescriptor editWardDescriptor) {
        assert wardToEdit != null;

        WardName updatedName = editWardDescriptor.getWard().orElse(wardToEdit.getName());

        Capacity updatedCapacity = editWardDescriptor.getCapacity()
                .orElse(wardToEdit.getCapacity());

        return new Ward(updatedName, updatedCapacity);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditWardCommand)) {
            return false;
        }

        // state check
        EditWardCommand e = (EditWardCommand) other;
        return index.equals(e.index)
                && editWardDescriptor.equals(e.editWardDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will
     * replace the
     * corresponding field value of the patient.
     */
    public static class EditWardDescriptor {
        private WardName ward;
        private Capacity capacity;

        public EditWardDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditWardDescriptor(EditWardDescriptor toCopy) {
            setWard(toCopy.ward);
            setCapacity(toCopy.capacity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(ward, capacity);
        }

        public void setWard(WardName ward) {
            this.ward = ward;
        }

        public void setCapacity(Capacity capacity) {
            this.capacity = capacity;
        }

        public Optional<WardName> getWard() {
            return Optional.ofNullable(ward);
        }

        public Optional<Capacity> getCapacity() {
            return Optional.ofNullable(capacity);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditWardDescriptor)) {
                return false;
            }

            // state check
            EditWardDescriptor e = (EditWardDescriptor) other;

            return getWard().equals(e.getWard())
                    && getCapacity().equals(e.getCapacity());
        }
    }
}
