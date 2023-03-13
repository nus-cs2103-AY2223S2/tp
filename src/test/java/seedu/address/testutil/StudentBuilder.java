package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Homework> homeworkList;
    private List<Lesson> lessonList;
    private List<Exam> examList;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        homeworkList = new ArrayList<>();
        lessonList = new ArrayList<>();
        examList = new ArrayList<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        homeworkList = new ArrayList<>(studentToCopy.getHomeworkList());
        lessonList = new ArrayList<>(studentToCopy.getLessonsList());
        examList = new ArrayList<>(studentToCopy.getExamList());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Homework} of the {@code Person} that we are building.
     *
     * @param homeworkList The homework list of the student.
     *                     {@code homeworkList} is a list of {@code Homework} objects.
     */
    public StudentBuilder withHomeworkList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
        return this;
    }

    /**
     * Sets the {@code Lesson} of the {@code Person} that we are building.
     */
    public StudentBuilder withLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
        return this;
    }
    /**
     * Builds a student with the given details.
     * @return a student with the given details.
     */
    public Student build() {
        return new Student(name, phone, email, address, tags, homeworkList, lessonList, examList);
    }
}
