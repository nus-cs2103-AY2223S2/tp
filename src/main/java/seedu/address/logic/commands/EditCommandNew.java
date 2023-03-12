package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_KEYDATE;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_STATUS;
import static seedu.address.model.ModelNew.PREDICATE_SHOW_ALL_OPENINGS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelNew;
import seedu.address.model.opening.Company;
import seedu.address.model.opening.Date;
import seedu.address.model.opening.Email;
import seedu.address.model.opening.Opening;
import seedu.address.model.opening.Position;
import seedu.address.model.opening.Remark;
import seedu.address.model.opening.Status;

/**
 * Edits the details of an existing opening in the address book.
 */
public class EditCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the opening identified "
            + "by the index number used in the displayed opening list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_KEYDATE + "DATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPANY + "Microsoft "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_OPENING_SUCCESS = "Edited Opening: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_OPENING = "This opening already exists in the address book.";

    private final Index index;
    private final EditOpeningDescriptor editOpeningDescriptor;

    /**
     * @param index of the opening in the filtered opening list to edit
     * @param editOpeningDescriptor details to edit the opening with
     */
    public EditCommandNew(Index index, EditOpeningDescriptor editOpeningDescriptor) {
        requireNonNull(index);
        requireNonNull(editOpeningDescriptor);

        this.index = index;
        this.editOpeningDescriptor = new EditOpeningDescriptor(editOpeningDescriptor);
    }

    @Override
    public CommandResultNew execute(ModelNew model) throws CommandException {
        requireNonNull(model);
        List<Opening> lastShownList = model.getFilteredOpeningList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
        }

        Opening openingToEdit = lastShownList.get(index.getZeroBased());
        Opening editedOpening = createEditedOpening(openingToEdit, editOpeningDescriptor);

        if (!openingToEdit.isSameOpening(editedOpening) && model.hasOpening(editedOpening)) {
            throw new CommandException(MESSAGE_DUPLICATE_OPENING);
        }

        model.setOpening(openingToEdit, editedOpening);
        model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        return new CommandResultNew(String.format(MESSAGE_EDIT_OPENING_SUCCESS, editedOpening));
    }

    /**
     * Creates and returns a {@code Opening} with the details of {@code openingToEdit}
     * edited with {@code editOpeningDescriptor}.
     */
    private static Opening createEditedOpening(Opening openingToEdit, EditOpeningDescriptor editOpeningDescriptor) {
        assert openingToEdit != null;

        Position updatedPosition = editOpeningDescriptor.getPosition().orElse(openingToEdit.getPosition());
        Company updatedCompany = editOpeningDescriptor.getCompany().orElse(openingToEdit.getCompany());
        Email updatedEmail = editOpeningDescriptor.getEmail().orElse(openingToEdit.getEmail());
        Status updatedStatus = editOpeningDescriptor.getStatus().orElse(openingToEdit.getStatus());
        Remark updatedRemark = editOpeningDescriptor.getRemark().orElse(openingToEdit.getRemark());
        Set<Date> updatedDates = editOpeningDescriptor.getDates().orElse(openingToEdit.getDates());

        return new Opening(updatedPosition, updatedCompany, updatedEmail, updatedStatus, updatedRemark, updatedDates);
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
        EditCommandNew e = (EditCommandNew) other;
        return index.equals(e.index)
                && editOpeningDescriptor.equals(e.editOpeningDescriptor);
    }

    /**
     * Stores the details to edit the opening with. Each non-empty field value will replace the
     * corresponding field value of the opening.
     */
    public static class EditOpeningDescriptor {
        private Position position;
        private Company company;
        private Email email;
        private Status status;
        private Remark remark;
        private Set<Date> keydates;

        public EditOpeningDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code keydates} is used internally.
         */
        public EditOpeningDescriptor(EditOpeningDescriptor toCopy) {
            setPosition(toCopy.position);
            setCompany(toCopy.company);
            setEmail(toCopy.email);
            setStatus(toCopy.status);
            setRemark(toCopy.remark);
            setDates(toCopy.keydates);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(position, company, email, status, keydates);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
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
         * Sets {@code keydates} to this object's {@code keydates}.
         * A defensive copy of {@code keydates} is used internally.
         */
        public void setDates(Set<Date> keydates) {
            this.keydates = (keydates != null) ? new HashSet<>(keydates) : null;
        }

        /**
         * Returns an unmodifiable date set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code keydates} is null.
         */
        public Optional<Set<Date>> getDates() {
            return (keydates != null) ? Optional.of(Collections.unmodifiableSet(keydates)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOpeningDescriptor)) {
                return false;
            }

            // state check
            EditOpeningDescriptor e = (EditOpeningDescriptor) other;

            return getPosition().equals(e.getPosition())
                    && getCompany().equals(e.getCompany())
                    && getEmail().equals(e.getEmail())
                    && getStatus().equals(e.getStatus())
                    && getDates().equals(e.getDates());
        }
    }
}
