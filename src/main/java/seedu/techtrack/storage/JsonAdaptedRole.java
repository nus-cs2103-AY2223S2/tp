package seedu.techtrack.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.techtrack.commons.exceptions.IllegalValueException;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;

/**
 * Jackson-friendly version of {@link Role}.
 */
class JsonAdaptedRole {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Role's %s field is missing!";

    private final String name;
    private final String contact;
    private final String email;
    private final String company;
    private final String jobDescription;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String salary;
    private final String deadline;
    private final String website;
    private final String experience;

    /**
     * Constructs a {@code JsonAdaptedRole} with the given role details.
     */
    @JsonCreator

    public JsonAdaptedRole(@JsonProperty("name") String name, @JsonProperty("phone") String contact,
                @JsonProperty("email") String email, @JsonProperty("address") String company,
                @JsonProperty("JobDescription") String jd, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                @JsonProperty("website") String website,
                @JsonProperty("salary") String salary, @JsonProperty("deadline") String deadline,
                           @JsonProperty("experience") String experience) {

        this.name = name;
        this.contact = contact;
        this.email = email;
        this.company = company;
        this.jobDescription = jd;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.salary = salary;
        this.deadline = deadline;
        this.website = website;
        this.experience = experience;
    }

    /**
     * Converts a given {@code Role} into this class for Jackson use.
     */
    public JsonAdaptedRole(Role source) {
        name = source.getName().fullName;
        contact = source.getContact().value;
        email = source.getEmail().value;
        company = source.getCompany().value;
        jobDescription = source.getJobDescription().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        salary = source.getSalary().salary;
        deadline = source.getDeadline().deadline;
        website = source.getWebsite().value;
        experience = source.getExperience().value;
    }

    /**
     * Converts this Jackson-friendly adapted role object into the model's {@code Role} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted role.
     */
    public Role toModelType() throws IllegalValueException {
        final List<Tag> roleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            roleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (contact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName()));
        }
        if (!Contact.isValidContact(contact)) {
            throw new IllegalValueException(Contact.MESSAGE_CONSTRAINTS);
        }
        final Contact modelContact = new Contact(contact);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (jobDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidJobDescription(jobDescription)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelJobDescription = new JobDescription(jobDescription);

        final Set<Tag> modelTags = new HashSet<>(roleTags);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.getMessageConstraint());
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (website == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Website.class.getSimpleName()));
        }
        if (!Website.isValidWebsite(website)) {
            throw new IllegalValueException(Website.MESSAGE_CONSTRAINTS);
        }
        final Website modelWebsite = new Website(website);

        if (experience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Experience.class.getSimpleName()));
        }
        if (!Experience.isValidExperience(experience)) {
            throw new IllegalValueException(Experience.MESSAGE_CONSTRAINTS);
        }
        final Experience modelExperience = new Experience(experience);

        return new Role(modelName, modelContact, modelEmail, modelCompany, modelJobDescription, modelTags,
                modelWebsite, modelSalary, modelDeadline, modelExperience);
    }

}
