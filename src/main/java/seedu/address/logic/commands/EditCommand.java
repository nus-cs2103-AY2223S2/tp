package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

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
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Location;
import seedu.address.model.application.Note;
import seedu.address.model.application.ProgrammingLanguage;
import seedu.address.model.application.Qualification;
import seedu.address.model.application.Rating;
import seedu.address.model.application.Reflection;
import seedu.address.model.application.Review;
import seedu.address.model.application.Salary;
import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;

/**
 * Edits the details of an existing internship in the tracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the internship identified "
            + "by the index number used in the displayed internship list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME] "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_SALARY + "SALARY]"
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION]... "
            + "[" + PREFIX_PROGRAMMING_LANGUAGE + "PROGRAMMING_LANGUAGE]... "
            + "[" + PREFIX_REVIEW + "REVIEW]... "
            + "[" + PREFIX_NOTE + "NOTE]... "
            + "[" + PREFIX_REFLECTION + "REFLECTION]... "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SALARY + "2000 SGD "
            + PREFIX_NOTE + "JC senior working there";

    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the tracker.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
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
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToEdit = lastShownList.get(index.getZeroBased());
        InternshipApplication editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameApplication(editedInternship) && model.hasApplication(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setApplication(internshipToEdit, editedInternship);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code personToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static InternshipApplication createEditedInternship(InternshipApplication internshipToEdit,
                                                                    EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        CompanyName updatedCompanyName = editInternshipDescriptor.getCompanyName()
                                                                    .orElse(internshipToEdit.getCompanyName());
        JobTitle updatedJobTitle = editInternshipDescriptor.getJobTitle().orElse(internshipToEdit.getJobTitle());
        Location updatedLocation = editInternshipDescriptor.getLocation().orElse(internshipToEdit.getLocation());
        Salary updatedSalary = editInternshipDescriptor.getSalary().orElse(internshipToEdit.getSalary());
        Rating updatedRating = editInternshipDescriptor.getRating().orElse(internshipToEdit.getRating());
        Set<Qualification> updatedQualifications = editInternshipDescriptor.getQualifications()
                                                                        .orElse(internshipToEdit.getQualifications());
        Set<ProgrammingLanguage> updatedProgrammingLanguages = editInternshipDescriptor.getProgrammingLanguages()
                                                                    .orElse(internshipToEdit.getProgrammingLanguages());
        Set<Review> updatedReviews = editInternshipDescriptor.getReviews().orElse(internshipToEdit.getReviews());
        Set<Note> updatedNotes = editInternshipDescriptor.getNotes().orElse(internshipToEdit.getNotes());
        Set<Reflection> updatedReflections = editInternshipDescriptor.getReflections()
                                                            .orElse(internshipToEdit.getReflections());
        Contact contact = internshipToEdit.getContact();
        InternshipStatus status = internshipToEdit.getStatus();
        InterviewDate interviewDate = internshipToEdit.getInterviewDate();
        Documents documents = internshipToEdit.getDocuments();
        boolean isArchived = internshipToEdit.isArchived();

        return new InternshipApplication(updatedCompanyName, updatedJobTitle, updatedReviews,
                        updatedProgrammingLanguages, updatedQualifications, updatedLocation, updatedSalary,
                        updatedNotes, updatedRating, updatedReflections, contact, status, isArchived, interviewDate,
                                                                                                            documents);
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
        private CompanyName companyName;
        private JobTitle jobTitle;
        private Location location;
        private Salary salary;
        private Rating rating;
        private Set<Qualification> qualifications;
        private Set<ProgrammingLanguage> programmingLanguages;
        private Set<Review> reviews;
        private Set<Note> notes;
        private Set<Reflection> reflections;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setJobTitle(toCopy.jobTitle);
            setLocation(toCopy.location);
            setSalary(toCopy.salary);
            setRating(toCopy.rating);
            setQualifications(toCopy.qualifications);
            setProgrammingLanguages(toCopy.programmingLanguages);
            setReviews(toCopy.reviews);
            setNotes(toCopy.notes);
            setReflections(toCopy.reflections);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, jobTitle, location, salary, rating, qualifications,
                                                                    programmingLanguages, reviews, notes, reflections);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        /**
         * Sets {@code qualifications} to this object's {@code qualifications}.
         * A defensive copy of {@code qualifications} is used internally.
         */
        public void setQualifications(Set<Qualification> qualifications) {
            this.qualifications = (qualifications != null) ? new HashSet<>(qualifications) : null;
        }

        /**
         * Returns an unmodifiable qualification set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code qualifications} is null.
         */
        public Optional<Set<Qualification>> getQualifications() {
            return (qualifications != null) ? Optional.of(Collections.unmodifiableSet(qualifications))
                                                                                        : Optional.empty();
        }

        /**
         * Sets {@code programmingLanguages} to this object's {@code programmingLanguages}.
         * A defensive copy of {@code programmingLanguages} is used internally.
         */
        public void setProgrammingLanguages(Set<ProgrammingLanguage> programmingLanguages) {
            this.programmingLanguages = (programmingLanguages != null) ? new HashSet<>(programmingLanguages) : null;
        }

        /**
         * Returns an unmodifiable programmingLanguage set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code programmingLanguages} is null.
         */
        public Optional<Set<ProgrammingLanguage>> getProgrammingLanguages() {
            return (programmingLanguages != null) ? Optional.of(Collections.unmodifiableSet(programmingLanguages))
                                                                                                    : Optional.empty();
        }

        /**
         * Sets {@code reviews} to this object's {@code reviews}.
         * A defensive copy of {@code reviews} is used internally.
         */
        public void setReviews(Set<Review> reviews) {
            this.reviews = (reviews != null) ? new HashSet<>(reviews) : null;
        }

        /**
         * Returns an unmodifiable review set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code reviews} is null.
         */
        public Optional<Set<Review>> getReviews() {
            return (reviews != null) ? Optional.of(Collections.unmodifiableSet(reviews)) : Optional.empty();
        }

        /**
         * Sets {@code notes} to this object's {@code notes}.
         * A defensive copy of {@code notes} is used internally.
         */
        public void setNotes(Set<Note> notes) {
            this.notes = (notes != null) ? new HashSet<>(notes) : null;
        }

        /**
         * Returns an unmodifiable note set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code notes} is null.
         */
        public Optional<Set<Note>> getNotes() {
            return (notes != null) ? Optional.of(Collections.unmodifiableSet(notes)) : Optional.empty();
        }

        /**
         * Sets {@code reflections} to this object's {@code reflections}.
         * A defensive copy of {@code reflections} is used internally.
         */
        public void setReflections(Set<Reflection> reflections) {
            this.reflections = (reflections != null) ? new HashSet<>(reflections) : null;
        }

        /**
         * Returns an unmodifiable reflection set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code reflections} is null.
         */
        public Optional<Set<Reflection>> getReflections() {
            return (reflections != null) ? Optional.of(Collections.unmodifiableSet(reflections)) : Optional.empty();
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

            return getCompanyName().equals(e.getCompanyName())
                    && getJobTitle().equals(e.getJobTitle())
                    && getLocation().equals(e.getLocation())
                    && getSalary().equals(e.getSalary())
                    && getRating().equals(e.getRating())
                    && getQualifications().equals(e.getQualifications())
                    && getProgrammingLanguages().equals(e.getProgrammingLanguages())
                    && getReviews().equals(e.getReviews())
                    && getNotes().equals(e.getNotes())
                    && getReflections().equals(e.getReflections());
        }
    }
}
