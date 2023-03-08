package seedu.address.model.person.student;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

/**
 * A class representing a Student who is a Person
 */
public class Student extends Person {

    /**
     * @param name
     * @param indexNumber
     * @param email
     * @param address
     * @param tags
     */
    private final IndexNumber indexNumber;
    private final Sex sex;
    private final ParentName parentName;
    private final Age age;
    private final Image image;
    private final Cca cca;
    private final StudentClass sc;
    private Attendance attendance;
    private Homework homework;
    private Test test;

    /**
     * A student class constructor
     * @param name
     * @param sc
     * @param indexNumber
     * @param sex
     * @param parentName
     * @param age
     * @param image
     * @param email
     * @param phone
     * @param cca
     * @param address
     * @param attendance
     * @param homework
     * @param test
     * @param tags
     */
    public Student(Name name, StudentClass sc, IndexNumber indexNumber, Sex sex, ParentName parentName, Age age,
                   Image image, Email email, Phone phone, Cca cca, Address address, Attendance attendance,
                   Homework homework, Test test, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.indexNumber = indexNumber;
        this.sex = sex;
        this.parentName = parentName;
        this.age = age;
        this.image = image;
        this.cca = cca;
        this.sc = sc;
        this.attendance = attendance;
        this.homework = homework;
        this.test = test;
    }

    public IndexNumber getIndexNumber() {
        return indexNumber;
    }
    public Sex getSex() {
        return sex;
    }
    public ParentName getParentName() {
        return parentName;
    }
    public Age getAge() {
        return age;
    }
    public Image getImage() {
        return image;
    }
    public Cca getCca() {
        return cca;
    }
    public StudentClass getStudentClass() {

        return sc;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getStudentClass().equals(getStudentClass())
                && otherStudent.getIndexNumber().equals(getIndexNumber())
                && otherStudent.getSex().equals(getSex())
                && otherStudent.getParentName().equals(getParentName())
                && otherStudent.getAge().equals(getAge())
                && otherStudent.getImage().equals(getImage())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getCca().equals(getCca())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags());
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Student Class: ")
                .append(getStudentClass())
                .append("; Index Number: ")
                .append(getIndexNumber())
                .append("; Sex: ")
                .append(getSex())
                .append("; Parent Name: ")
                .append(getParentName())
                .append("; Student Age: ")
                .append(getAge())
                .append("; Image Path: ")
                .append(getImage())
                .append("; Student Email: ")
                .append(getEmail())
                .append("; Student Phone: ")
                .append(getPhone())
                .append("; CCA: ")
                .append(getCca());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}
