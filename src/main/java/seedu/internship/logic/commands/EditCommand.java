package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.commons.util.CollectionUtil;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Description;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * Edits the details of an existing internship in the internship catalogue.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a positive integer) "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS + "1 "
            + PREFIX_DESCRIPTION + "Need to learn Golang";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_INTERNSHIP_UNCHANGED = "Internship is unchanged";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists "
            + "in the internship catalogue.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * Creates Edit Command.
     *
     * @param index of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);

        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        // if another internship other than internshipToEdit has same Position and Company as editedInternship
        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        // if internshipToEdit is not changed.
        if (internshipToEdit.equals(editedInternship)) {
            throw new CommandException(MESSAGE_INTERNSHIP_UNCHANGED);
        }

        model.updateFilteredEventList(new EventByInternship(internshipToEdit));
        List<Event> oldEvents = model.getFilteredEventList().stream().collect(Collectors.toList());
        for (Event oldEvent : oldEvents) {
            Event newEvent = oldEvent.getCopyOf();
            newEvent.setInternship(editedInternship);
            model.setEvent(oldEvent, newEvent);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        model.updateSelectedInternship(editedInternship);

        model.updateFilteredEventList(new EventByInternship(model.getSelectedInternship()));
        ObservableList<Event> events = model.getFilteredEventList();
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship),
                ResultType.SHOW_INFO, editedInternship, events);
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(
            Internship internshipToEdit, EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        Position updatedPosition = editInternshipDescriptor.getPosition().orElse(internshipToEdit.getPosition());
        Company updatedCompany = editInternshipDescriptor.getCompany().orElse(internshipToEdit.getCompany());
        Status updatedStatus = editInternshipDescriptor.getStatus().orElse(internshipToEdit.getStatus());
        Description updatedDescription = editInternshipDescriptor.getDescription()
                .orElse(internshipToEdit.getDescription());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(internshipToEdit.getTags());

        return new Internship(updatedPosition, updatedCompany, updatedStatus, updatedDescription, updatedTags);
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
                && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }

    /**
     * Stores the details to edit the internship with. Each non-empty field value will replace the
     * corresponding field value of the internship.
     */
    public static class EditInternshipDescriptor {
        private Position position;
        private Company company;
        private Status status;
        private Description description;
        private Set<Tag> tags;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setPosition(toCopy.position);
            setCompany(toCopy.company);
            setStatus(toCopy.status);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(position, company, status, description, tags);
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

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
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
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getPosition().equals(e.getPosition())
                    && getCompany().equals(e.getCompany())
                    && getStatus().equals(e.getStatus())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
