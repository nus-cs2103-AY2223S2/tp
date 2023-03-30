package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Note;
import seedu.address.model.person.ProgrammingLanguage;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Reflection;
import seedu.address.model.person.Review;
import seedu.address.model.person.Salary;

/**
 * Jackson-friendly version of {@link seedu.address.model.person.InternshipApplication}.
 */
public class JsonAdaptedInternshipApplication {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship application 's %s field is missing!";

    private final String companyName;
    private final String jobTitle;
    private final List<String> reviews = new ArrayList<>();
    private final List<String> programmingLanguages = new ArrayList<>();
    private final List<String> qualifications = new ArrayList<>();
    private final String location;
    private final String salary;
    private final List<String> notes = new ArrayList<>();
    private final String rating;
    private final List<String> reflections = new ArrayList<>();
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
                                            @JsonProperty("review") List<String> reviews,
                                            @JsonProperty("programmingLanguage") List<String> programmingLanguages,
                                            @JsonProperty("qualification") List<String> qualifications,
                                            @JsonProperty("location") String location,
                                            @JsonProperty("salary") String salary,
                                            @JsonProperty("note") List<String> notes,
                                            @JsonProperty("rating") String rating,
                                            @JsonProperty("reflection") List<String> reflections,
                                            @JsonProperty("status") String status,
                                            @JsonProperty("interviewDate") String interviewDate,
                                            @JsonProperty("contact") List<String> contact,
                                            @JsonProperty("documents") List<String> documents) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
        if (programmingLanguages != null) {
            this.programmingLanguages.addAll(programmingLanguages);
        }
        if (qualifications != null) {
            this.qualifications.addAll(qualifications);
        }
        this.location = location;
        this.salary = salary;
        if (notes != null) {
            this.notes.addAll(notes);
        }
        this.rating = rating;
        if (reflections != null) {
            this.reflections.addAll(reflections);
        }
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
        reviews.addAll(source.getReviews().stream()
                .map(Review::toString)
                .collect(Collectors.toList()));
        programmingLanguages.addAll(source.getProgrammingLanguages().stream()
                            .map(ProgrammingLanguage::toString)
                            .collect(Collectors.toList()));
        qualifications.addAll(source.getQualifications().stream()
                        .map(Qualification::toString)
                        .collect(Collectors.toList()));
        location = source.getLocation().value;
        salary = source.getSalary().value;
        notes.addAll(source.getNotes().stream()
                .map(Note::toString)
                .collect(Collectors.toList()));
        rating = source.getRating().value;
        reflections.addAll(source.getReflections().stream()
                .map(Reflection::toString)
                .collect(Collectors.toList()));
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
            documents.add(source.getDocuments().getResumeLink().value);
            documents.add(source.getDocuments().getCoverLetterLink().value);
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
        if (!CompanyName.isValidCompanyName(companyName)) {
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

        final List<Review> reviewList = new ArrayList<>();
        for (String review : reviews) {
            reviewList.add(new Review(review));
        }
        final Set<Review> modelReviews = new HashSet<>(reviewList);

        final List<ProgrammingLanguage> programmingLanguageList = new ArrayList<>();
        for (String programmingLanguage : programmingLanguages) {
            programmingLanguageList.add(new ProgrammingLanguage(programmingLanguage));
        }
        final Set<ProgrammingLanguage> modelProgrammingLanguages = new HashSet<>(programmingLanguageList);

        final List<Qualification> qualificationList = new ArrayList<>();
        for (String qualification : qualifications) {
            qualificationList.add(new Qualification(qualification));
        }
        final Set<Qualification> modelQualifications = new HashSet<>(qualificationList);

        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        final List<Note> noteList = new ArrayList<>();
        for (String note : notes) {
            noteList.add(new Note(note));
        }
        final Set<Note> modelNotes = new HashSet<>(noteList);

        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final List<Reflection> reflectionList = new ArrayList<>();
        for (String reflection : reflections) {
            reflectionList.add(new Reflection(reflection));
        }
        final Set<Reflection> modelReflections = new HashSet<>(reflectionList);

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
                if (!ResumeLink.isValidResumeLink(documents.get(0))) {
                    throw new IllegalValueException(ResumeLink.MESSAGE_CONSTRAINTS);
                }
                final ResumeLink modelResumeLink = new ResumeLink(documents.get(0));

                if (!CoverLetterLink.isValidCoverLetterLink(documents.get(1))) {
                    throw new IllegalValueException(CoverLetterLink.MESSAGE_CONSTRAINTS);
                }
                final CoverLetterLink modelCoverLetterLink = new CoverLetterLink(documents.get(1));
                final Documents modelDocuments = new Documents(modelResumeLink, modelCoverLetterLink);

                return new InternshipApplication(modelCompanyName, modelJobTitle, modelReviews,
                        modelProgrammingLanguages, modelQualifications, modelLocation, modelSalary, modelNotes,
                        modelRating, modelReflections, modelContact, modelStatus, modelInterviewDate, modelDocuments);
            }

            return new InternshipApplication(modelCompanyName, modelJobTitle, modelReviews,
                    modelProgrammingLanguages, modelQualifications, modelLocation, modelSalary, modelNotes,
                    modelRating, modelReflections, modelContact, modelStatus, modelInterviewDate, null);
        }

        return new InternshipApplication(modelCompanyName, modelJobTitle, modelReviews,
                modelProgrammingLanguages, modelQualifications, modelLocation, modelSalary, modelNotes,
                modelRating, modelReflections, null, modelStatus, modelInterviewDate, null);
    }
}
