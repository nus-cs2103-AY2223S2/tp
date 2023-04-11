package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.Test;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {
    public static final String DEFAULT_NAME = "Tan Ah Cow";
    public static final String DEFAULT_INDEXNUMBER = "13";

    public static final String DEFAULT_SEX = "M";
    public static final String DEFAULT_PARENTNAME = "Tan Ah Niu";
    public static final String DEFAULT_PARENTPHONE = "91234567";
    public static final String DEFAULT_RELATIONSHIP = "Father";
    public static final String DEFAULT_ADDRESS = "Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456)";
    public static final String DEFAULT_AGE = "14";
    public static final String DEFAULT_IMAGEPARENT = "Insert parent image here!";
    public static final String DEFAULT_PARENTAGE = "41";
    public static final String DEFAULT_STUDENTPHONE = "91234567";
    public static final String DEFAULT_CCA = "Captain Ball";
    public static final String DEFAULT_PARENTEMAIL = "Insert parent email here!";
    public static final String DEFAULT_PARENTADDRESS = "Insert Address here!";
    public static final String DEFAULT_CLASS = "1A";
    public static final String DEFAULT_STUDENTEMAIL = "tanahcow@gmail.com";
    public static final String DEFAULT_ATTENDANCE = "01/01/2019";
    public static final String DEFAULT_COMMENT = "Insert student comment here!";
    private Name name;
    private Name parentName;
    private IndexNumber indexNumber;
    private Sex sex;
    private Parent parent;
    private Age age;
    private Age parentAge;
    private Phone studentPhone;
    private Phone parentPhone;
    private Cca cca;
    private Image studentImage;
    private Image parentImage;
    private Email parentEmail;
    private Set<Tag> tags;
    private Address parentAddress;
    private Student student;
    private Class studentClass;
    private Relationship relationship;
    private Email studentEmail;
    private Address studentAddress;
    private Set<Attendance> studentAttendance;
    private Set<Homework> homework;
    private Set<Test> test;
    private Comment comment;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        parentName = new Name(DEFAULT_PARENTNAME);
        indexNumber = new IndexNumber(DEFAULT_INDEXNUMBER);
        sex = new Sex(DEFAULT_SEX);
        age = new Age(DEFAULT_AGE);
        parentAge = new Age(DEFAULT_PARENTAGE);
        studentPhone = new Phone(DEFAULT_STUDENTPHONE);
        parentPhone = new Phone(DEFAULT_PARENTPHONE);
        cca = new Cca(DEFAULT_CCA);
        parentImage = new Image(DEFAULT_IMAGEPARENT);
        parentEmail = new Email(DEFAULT_PARENTEMAIL);
        parentAddress = new Address(DEFAULT_PARENTADDRESS);
        tags = new HashSet<>();
        relationship = new Relationship(DEFAULT_RELATIONSHIP);
        studentEmail = new Email(DEFAULT_STUDENTEMAIL);
        studentAddress = new Address(DEFAULT_ADDRESS);
        studentAttendance = new HashSet<>();
        studentAttendance.add(new Attendance(DEFAULT_ATTENDANCE));
        homework = new HashSet<>();
        test = new HashSet<>();
        comment = new Comment(DEFAULT_COMMENT);
        studentClass = Class.of(DEFAULT_CLASS);
        parent = new Parent(parentName, parentAge, parentImage, parentEmail, parentPhone, parentAddress, tags);

    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentClass = studentToCopy.getStudentClass();
        indexNumber = studentToCopy.getIndexNumber();
        sex = studentToCopy.getSex();
        parentName = studentToCopy.getParentName();
        parentPhone = studentToCopy.getParentNumber();
        relationship = studentToCopy.getRls();
        age = studentToCopy.getAge();
        studentImage = studentToCopy.getImage();
        studentEmail = studentToCopy.getEmail();
        studentPhone = studentToCopy.getPhone();
        cca = studentToCopy.getCca();
        studentAddress = studentToCopy.getAddress();
        studentAttendance = studentToCopy.getAttendance();
        homework = new HashSet<>(studentToCopy.getHomework());
        test = new HashSet<>(studentToCopy.getTest());
        tags = new HashSet<>(studentToCopy.getTags());
        comment = studentToCopy.getComment();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.studentAddress = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.studentEmail = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.studentPhone = new Phone(phone);
        return this;
    }
    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }
    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withImage(String image) {
        this.studentImage = new Image(image);
        return this;
    }
    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withClass(String studentClass) {
        this.studentClass = Class.of(studentClass);
        return this;
    }
    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withIndexNumber(String indexNumber) {
        this.indexNumber = new IndexNumber(indexNumber);
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
     * Sets the {@code Comment} of the {@code Person} that we are building.
     * @param comment
     * @return
     */
    public StudentBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    /**
     * Sets the {@code Test} of the {@code Person} that we are building.
     * @return Student
     */
    public Student build() {
        return new Student(name, studentClass, indexNumber, sex, parentName, parentPhone, relationship, age,
                studentImage, studentEmail, studentPhone, cca, studentAddress, studentAttendance, homework, test,
                tags, comment);
    }
}
