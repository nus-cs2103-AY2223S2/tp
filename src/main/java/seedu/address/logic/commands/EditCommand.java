package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.JobDescription;
import seedu.address.model.listing.JobTitle;
import seedu.address.model.listing.Listing;

/**
 * Edits a listing in the listing book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a listing in the listing book. "
            + "by the index number used in the displayed listing book. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_APPLICANT + "APPLICANT]...\n"
            + "[" + PREFIX_APPLICANT + "APPLICANT (optional)]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Cool job title "
            + PREFIX_APPLICANT + "John "
            + PREFIX_APPLICANT + "Sam";

    public static final String MESSAGE_EDIT_LISTING_SUCCESS = "Edited Listing: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LISTING = "This listing already exists in the listing book";

    private final Index index;
    private final EditListingDescriptor editListingDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Listing}
     */
    public EditCommand(Index index, EditListingDescriptor editListingDescriptor) {
        requireNonNull(index);
        requireNonNull(editListingDescriptor);

        this.index = index;
        this.editListingDescriptor = new EditListingDescriptor(editListingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getFilteredListingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
        }

        Listing listingToEdit = lastShownList.get(index.getZeroBased());
        Listing editedListing = createEditedListing(listingToEdit, editListingDescriptor);

        if (!listingToEdit.isSameListing(editedListing) && model.hasListing(editedListing)) {
            throw new CommandException(MESSAGE_DUPLICATE_LISTING);
        }

        model.setListing(listingToEdit, editedListing);
        model.updateFilteredListingList(Model.PREDICATE_SHOW_ALL_LISTINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_LISTING_SUCCESS, editedListing));
    }

    /**
     * Creates and returns a {@code Listing} with the details of {@code listingToEdit}
     * edited with {@code editListingDescriptor}.
     */
    private static Listing createEditedListing(Listing listingToEdit, EditListingDescriptor editListingDescriptor) {
        assert listingToEdit != null;

        JobTitle updatedJobTitle = editListingDescriptor.getJobTitle().orElse(listingToEdit.getTitle());
        JobDescription updatedJobDescription = editListingDescriptor.getJobDescription().orElse(
                listingToEdit.getDescription());
        ArrayList<Applicant> updatedApplicants = editListingDescriptor.getApplicants().orElse(
                listingToEdit.getApplicants());

        return new Listing(updatedJobTitle, updatedJobDescription, updatedApplicants);
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
                && editListingDescriptor.equals(e.editListingDescriptor);
    }

    /**
     * Stores the details to edit the listing with. Each non-empty field value will replace the
     * corresponding field value of the listing.
     */
    public static class EditListingDescriptor {
        private JobTitle jobTitle;
        private JobDescription jobDescription;
        private ArrayList<Applicant> applicants;

        public EditListingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditListingDescriptor(EditListingDescriptor listingToCopy) {
            setJobTitle(listingToCopy.jobTitle);
            setJobDescription(listingToCopy.jobDescription);
            setApplicants(listingToCopy.applicants);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobTitle, jobDescription, applicants);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setJobDescription(JobDescription jobDescription) {
            this.jobDescription = jobDescription;
        }

        public Optional<JobDescription> getJobDescription() {
            return Optional.ofNullable(jobDescription);
        }

        /**
         * Sets {@code applicants} to this object's {@code applicants}.
         * A defensive copy of {@code applicants} is used internally.
         */
        public void setApplicants(ArrayList<Applicant> applicants) {
            this.applicants = (applicants != null) ? new ArrayList<>(applicants) : null;
        }

        /**
         * Returns an unmodifiable applicants array list, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code applicants} is null.
         */
        public Optional<ArrayList<Applicant>> getApplicants() {
            return (applicants != null) ? Optional.of(
                    new ArrayList<>(Collections.unmodifiableList(applicants))) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditListingDescriptor)) {
                return false;
            }

            // state check
            EditListingDescriptor e = (EditListingDescriptor) other;

            return getJobTitle().equals(e.getJobTitle())
                    && getJobDescription().equals(e.getJobDescription())
                    && getApplicants().equals(e.getApplicants());
        }
    }
}
