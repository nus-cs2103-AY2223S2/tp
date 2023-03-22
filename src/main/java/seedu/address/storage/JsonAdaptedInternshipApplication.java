package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.documents.CoverLetter;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.Resume;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;

/**
 * Jackson-friendly version of {@link seedu.address.model.person.InternshipApplication}.
 */
public class JsonAdaptedInternshipApplication {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship application 's %s field is missing!";

    private final String companyName;
    private final String jobTitle;
    private final List<String> contact = new ArrayList<>();
    private final String status;
    private final String interviewDate;
    private final List<String> documents = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternshipApplication} with the given InternshipApplication details.
     */
    @JsonCreator
    public JsonAdaptedInternshipApplication(@JsonProperty("companyName") String companyName,
                                            @JsonProperty("jobTitle") String jobTitle,
                                            @JsonProperty("status") String status,
                                            @JsonProperty("interviewDate") String interviewDate,
                                            @JsonProperty("contact") List<String> contact,
                                            @JsonProperty("documents") List<String> documents) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        if (contact != null) {
            this.contact.addAll(contact);
        }
        this.status = status;
        this.interviewDate = interviewDate;
        if (documents != null) {
            this.documents.addAll(documents);
        }
    }

    /**
     * Converts a given {@code InternshipApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternshipApplication(InternshipApplication source) {
        companyName = source.getCompanyName().fullName;
        jobTitle = source.getJobTitle().fullName;
        if (source.getContact() != null) {
            contact.add(source.getContact().getPhone().value);
            contact.add(source.getContact().getEmail().value);
        }
        status = source.getStatus().name();
        if (source.getInterviewDate() != null) {
            interviewDate = source.getInterviewDate().toString();
        } else {
            interviewDate = null;
        }
        if (source.getDocuments() != null) {
            documents.add(source.getDocuments().getResume().value);
            documents.add(source.getDocuments().getCoverLetter().value);
        }
    }

    /**
     * Converts this Jackson-friendly adapted InternshipApplication object
     * into the model's {@code InternshipApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted InternshipApplication.
     */
    public InternshipApplication toModelType() throws IllegalValueException {
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipStatus.class.getSimpleName()));
        }
        if (!InternshipStatus.isValidStatus(status)) {
            throw new IllegalValueException(InternshipStatus.MESSAGE_CONSTRAINTS);
        }
        final InternshipStatus modelStatus = InternshipStatus.valueOf(status);

        if (!InterviewDate.isValidInterviewDate(interviewDate)) {
            throw new IllegalValueException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        final InterviewDate modelInterviewDate = interviewDate == null ? null : new InterviewDate(interviewDate);

        if (contact.size() == 2) {
            if (!Phone.isValidPhone(contact.get(0))) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            final Phone modelPhone = new Phone(contact.get(0));

            if (!Email.isValidEmail(contact.get(1))) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            final Email modelEmail = new Email(contact.get(1));
            final Contact modelContact = new Contact(modelPhone, modelEmail);

            if (documents.size() == 2) {
                if (!Resume.isValidResumeLink(documents.get(0))) {
                    throw new IllegalValueException(Resume.MESSAGE_CONSTRAINTS);
                }
                final Resume modelResume = new Resume(documents.get(0));

                if (!CoverLetter.isValidCoverLetterLink(documents.get(1))) {
                    throw new IllegalValueException(CoverLetter.MESSAGE_CONSTRAINTS);
                }
                final CoverLetter modelCoverLetter = new CoverLetter(documents.get(1));
                final Documents modelDocuments = new Documents(modelResume, modelCoverLetter);

                return new InternshipApplication(modelCompanyName, modelJobTitle, modelContact, modelStatus,
                        modelInterviewDate, modelDocuments);
            }
            return new InternshipApplication(modelCompanyName, modelJobTitle, modelContact, modelStatus,
                    modelInterviewDate, null);
        }

        return new InternshipApplication(modelCompanyName, modelJobTitle, null, modelStatus,
                modelInterviewDate, null);
    }
}
