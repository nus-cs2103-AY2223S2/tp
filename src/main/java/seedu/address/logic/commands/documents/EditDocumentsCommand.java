package seedu.address.logic.commands.documents;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
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
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;

/**
 * Edits links to the resume and/or cover letter of an application identified using it's displayed index
 * from the list of internship applications.
 */
public class EditDocumentsCommand extends Command {

    public static final String COMMAND_WORD = "edit_docs";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits links to the resume and/or cover letter of the specified application from the "
            + "list of internships applied.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RESUME + "RESUME] "
            + "[" + PREFIX_COVER_LETTER + "COVER_LETTER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RESUME + "https://docs.google.com/document/d/EXAMPLE_RESUME/edit "
            + PREFIX_COVER_LETTER + "https://docs.google.com/document/d/EXAMPLE_COVER_LETTER/edit";

    public static final String MESSAGE_DOCUMENTS_NOT_FOUND =
            "No resume and/or cover letter added";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_EDIT_DOCUMENTS_SUCCESS =
            "Resume and/or cover letter updated for application: %1$s";

    private final Index targetIndex;


    private final EditDocumentsDescriptor editDocumentsDescriptor;

    /**
     * @param targetIndex of the internship application to edit documents
     * @param editDocumentsDescriptor details to edit the documents with
     */
    public EditDocumentsCommand(Index targetIndex, EditDocumentsDescriptor editDocumentsDescriptor) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        this.editDocumentsDescriptor = editDocumentsDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipApplication> lastShownList = model.getSortedFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipApplication internshipToEditDocuments = lastShownList.get(targetIndex.getZeroBased());
        if (internshipToEditDocuments.getDocuments() == null) {
            throw new CommandException(MESSAGE_DOCUMENTS_NOT_FOUND);
        }
        InternshipApplication internshipWithUpdatedDocuments =
                createInternshipWithUpdatedDocuments(internshipToEditDocuments, editDocumentsDescriptor);

        model.setApplication(internshipToEditDocuments, internshipWithUpdatedDocuments);
        return new CommandResult(String.format(MESSAGE_EDIT_DOCUMENTS_SUCCESS, internshipToEditDocuments));
    }

    /**
     * Creates and returns a {@code InternshipApplication} with the details of {@code internshipToEditDocuments}
     * edited with {@code editDocumentsDescriptor}.
     */
    private static InternshipApplication createInternshipWithUpdatedDocuments(
            InternshipApplication internshipToEditDocuments,
            EditDocumentsDescriptor editDocumentsDescriptor) {

        assert internshipToEditDocuments != null;

        CompanyName companyName = internshipToEditDocuments.getCompanyName();
        JobTitle jobTitle = internshipToEditDocuments.getJobTitle();
        Set<Review> reviews = internshipToEditDocuments.getReviews();
        Set<ProgrammingLanguage> programmingLanguages = internshipToEditDocuments.getProgrammingLanguages();
        Set<Qualification> qualifications = internshipToEditDocuments.getQualifications();
        Location location = internshipToEditDocuments.getLocation();
        Salary salary = internshipToEditDocuments.getSalary();
        Set<Note> notes = internshipToEditDocuments.getNotes();
        Rating rating = internshipToEditDocuments.getRating();
        Set<Reflection> reflections = internshipToEditDocuments.getReflections();
        Contact contact = internshipToEditDocuments.getContact();
        InternshipStatus status = internshipToEditDocuments.getStatus();
        InterviewDate interviewDate = internshipToEditDocuments.getInterviewDate();
        boolean isArchived = internshipToEditDocuments.isArchived();

        ResumeLink resumeLink = editDocumentsDescriptor.getResumeLink()
                .orElse(internshipToEditDocuments.getDocuments().getResumeLink());
        CoverLetterLink coverLetterLink = editDocumentsDescriptor.getCoverLetterLink()
                .orElse(internshipToEditDocuments.getDocuments().getCoverLetterLink());

        Documents newDocuments = new Documents(resumeLink, coverLetterLink);

        return new InternshipApplication(companyName, jobTitle, reviews, programmingLanguages, qualifications, location,
                salary, notes, rating, reflections, contact, status, isArchived, interviewDate, newDocuments);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditDocumentsCommand // instanceof handles nulls
                && targetIndex.equals(((EditDocumentsCommand) other).targetIndex)
                && editDocumentsDescriptor.equals((
                        (EditDocumentsCommand) other).editDocumentsDescriptor)); // state check
    }

    /**
     * Stores the details to edit the documents with. Each non-empty field value will replace the
     * corresponding field value of the documents.
     */
    public static class EditDocumentsDescriptor {
        private ResumeLink resumeLink;
        private CoverLetterLink coverLetterLink;

        public EditDocumentsDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditDocumentsDescriptor(EditDocumentsCommand.EditDocumentsDescriptor toCopy) {
            setResumeLink(toCopy.resumeLink);
            setCoverLetterLink(toCopy.coverLetterLink);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(resumeLink, coverLetterLink);
        }

        public void setResumeLink(ResumeLink resumeLink) {
            this.resumeLink = resumeLink;
        }

        public Optional<ResumeLink> getResumeLink() {
            return Optional.ofNullable(resumeLink);
        }

        public void setCoverLetterLink(CoverLetterLink coverLetterLink) {
            this.coverLetterLink = coverLetterLink;
        }

        public Optional<CoverLetterLink> getCoverLetterLink() {
            return Optional.ofNullable(coverLetterLink);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDocumentsCommand.EditDocumentsDescriptor)) {
                return false;
            }

            // state check
            EditDocumentsCommand.EditDocumentsDescriptor e = (EditDocumentsCommand.EditDocumentsDescriptor) other;

            return getResumeLink().equals(e.getResumeLink())
                    && getCoverLetterLink().equals(e.getCoverLetterLink());
        }
    }
}
