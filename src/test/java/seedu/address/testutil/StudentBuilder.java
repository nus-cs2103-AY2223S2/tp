package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PARENT_PHONE = "85647154";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Phone parentPhone;
    private Set<Tag> tags;
    private seedu.address.model.task.UniqueTaskList uniqueTaskList;
    private seedu.address.model.score.UniqueScoreList uniqueScoreList;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        parentPhone = new Phone(DEFAULT_PARENT_PHONE);
        tags = new HashSet<>();
        uniqueTaskList = new seedu.address.model.task.UniqueTaskList();
        uniqueScoreList = new seedu.address.model.score.UniqueScoreList();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(seedu.address.model.student.Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        parentPhone = studentToCopy.getParentPhone();
        tags = new HashSet<>(studentToCopy.getTags());
        uniqueTaskList = studentToCopy.getTaskList();
        uniqueScoreList = studentToCopy.getScoreList();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ParentPhone} of the {@code Student} that we are building.
     */
    public StudentBuilder withParentPhone(String parentPhone) {
        this.parentPhone = new Phone(parentPhone);
        return this;
    }

    /**
     * Sets the {@code UniqueTaskList} of the {@code Student} that we are building.
     */
    public StudentBuilder withTaskList(seedu.address.model.task.UniqueTaskList uniqueTaskList) {
        this.uniqueTaskList = uniqueTaskList;
        return this;
    }

    /**
     * Sets the {@code UniqueScoreList} of the {@code Student} that we are building.
     */
    public StudentBuilder withScoreList(seedu.address.model.score.UniqueScoreList uniqueScoreList) {
        this.uniqueScoreList = uniqueScoreList;
        return this;
    }

    public seedu.address.model.student.Student build() {
        return new seedu.address.model.student.Student(name, phone, email, address, parentPhone, tags);
    }

}
