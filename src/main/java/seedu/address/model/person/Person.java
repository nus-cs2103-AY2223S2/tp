package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Status;
import seedu.address.model.person.status.LeadStatus;
import seedu.address.model.person.status.LeadStatusName;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Gender gender;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Company company;
    private final Location location;
    private final Occupation occupation;
    private final JobTitle jobTitle;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final LeadStatus status;
    private final TaskList tasks;

    /**
     * Every field must be present and not null. By default, the LeadStatus is "Uncontacted".
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Company company, Location location,
                  Occupation occupation, JobTitle jobTitle, Address address, Remark remark,
                  Set<Tag> tags, TaskList tasks) {
        requireAllNonNull(name, gender, phone, email, company, location,
                occupation, jobTitle, address, tags, remark, tasks);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.location = location;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = new LeadStatus(LeadStatusName.UNCONTACTED.getLabel());
        this.tasks = tasks;
    }


    /**
     * Special constructor for constructing a Txn owner, only name field important
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Company company, Location location,
                  Occupation occupation, JobTitle jobTitle, Address address, Remark remark) {
        requireAllNonNull(name, gender, phone, email, company, location,
                occupation, jobTitle, address, remark);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.location = location;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.remark = remark;
        this.status = new LeadStatus(LeadStatusName.UNCONTACTED.getLabel());
        this.tasks = new TaskList();
    }


    /**
     * Constructor for a person with a given LeadStatus.
     */
    public Person(Name name, Gender gender, Phone phone, Email email, Company company, Location location,
                  Occupation occupation, JobTitle jobTitle, Address address, Remark remark,
                  Set<Tag> tags, TaskList tasks,
                  LeadStatus status) {
        requireAllNonNull(name, gender, phone, email, company, location,
                occupation, jobTitle, address, tags, remark, tasks, status);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.location = location;
        this.occupation = occupation;
        this.jobTitle = jobTitle;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = status;
        this.tasks = tasks;

    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Company getCompany() {
        return company;
    }

    public Location getLocation() {
        return location;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public LeadStatus getStatus() {
        return status;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Returns the value of a person's attribute
     *
     * @param attribute Attribute of a contact.
     * @return value of attribute.
     */
    public String getAttribute(String attribute) throws IllegalValueException {
        switch(attribute) {
        case "name":
            return name.toString();
        case "gender":
            return gender.toString();
        case "phone number":
            return phone.toString();
        case "email":
            return email.toString();
        case "address":
            return address.toString();
        case "company":
            return company.toString();
        case "location":
            return location.toString();
        case "occupation":
            return occupation.toString();
        case "job title":
            return jobTitle.toString();
        case "status":
            return status.getStatusName().getLabel();
        default:
            throw new IllegalValueException("Attribute does not exist!");
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the String representation of the time elapsed since the status has changed.
     */
    public String getLastUpdate() {
        return Status.formatDuration(status.getDurationSinceLastUpdate()) + " ago";
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getLocation().equals(getLocation())
                && otherPerson.getOccupation().equals(getOccupation())
                && otherPerson.getJobTitle().equals(getJobTitle())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, company, location, occupation, jobTitle, address, tags);
    }

    /**
     * Returns the appended string of person object delimited by a " "
     * @return String
     */
    public String toStringSpaceDelimited() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Gender: ")
                .append(getGender())
                .append(" Email: ")
                .append(getEmail())
                .append(" Company: ")
                .append(getCompany())
                .append(" Location: ")
                .append(getLocation())
                .append(" Occupation: ")
                .append(getOccupation())
                .append(" Job Title: ")
                .append(getJobTitle())
                .append(" Address: ")
                .append(getAddress())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Task: ")
                .append(getTasks());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append(" Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Gender: ")
                .append(getGender())
                .append("; Email: ")
                .append(getEmail())
                .append("; Company: ")
                .append(getCompany())
                .append("; Location: ")
                .append(getLocation())
                .append("; Occupation: ")
                .append(getOccupation())
                .append("; Job Title: ")
                .append(getJobTitle())
                .append("; Address: ")
                .append(getAddress())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Task: ")
                .append(getTasks());

        Set<Tag> tags = getTags();

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
