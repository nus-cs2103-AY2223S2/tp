package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskList;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String gender;
    private final String phone;
    private final String email;
    private final String company;
    private final String location;
    private final String occupation;
    private final String jobTitle;
    private final String address;
    private final String remark;
    private final String task;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final JsonAdaptedLeadStatus leadStatus;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("gender") String gender,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("company") String company,
            @JsonProperty("location") String location,
            @JsonProperty("occupation") String occupation,
            @JsonProperty("jobTitle") String jobTitle,
            @JsonProperty("remark") String remark,
            @JsonProperty("task") String task,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("leadStatus") JsonAdaptedLeadStatus leadStatus) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.location = location;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.address = address;
        this.remark = remark;
        this.task = task;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.leadStatus = leadStatus;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        gender = source.getGender().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        company = source.getCompany().value;
        location = source.getLocation().value;
        occupation = source.getOccupation().value;
        jobTitle = source.getJobTitle().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        task = source.getTasks().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        leadStatus = new JsonAdaptedLeadStatus(source.getStatus());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (leadStatus == null) { // lead status must exist for a person to proceed
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LeadStatus.class.getSimpleName()));
        }
        final LeadStatus modelLeadStatus = leadStatus.toModelType();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

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
        if (!Company.isValidCompanyName(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (occupation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Occupation.class.getSimpleName()));
        }
        if (!Occupation.isValidOccupation(occupation)) {
            throw new IllegalValueException(Occupation.MESSAGE_CONSTRAINTS);
        }
        final Occupation modelOccupation = new Occupation(occupation);

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        if (task == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.class.getSimpleName()));
        }
        final TaskList modelTasks = new TaskList().add(new Task(task));

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelGender, modelPhone, modelEmail, modelCompany, modelLocation,
                modelOccupation, modelJobTitle, modelAddress, modelRemark, modelTags, modelTasks, modelLeadStatus);
    }
}
