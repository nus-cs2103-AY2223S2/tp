package seedu.address.model.person.student;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Student extends Person {

    /**
     *
     *
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
    private final CCA cca;
    private final StudentClass sc;
    private Attendance attendance;
    private Homework homework;
    private Test test;


    public Student(Name name, StudentClass sc, IndexNumber indexNumber, Sex sex, ParentName parentName, Age age, Image image, Email email, Phone phone,
                   CCA cca, Address address, Attendance attendance, Homework homework, Test test, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.indexNumber = indexNumber;
        this.sex = sex;
        this.parentName = parentName;
        this.age = age;
        this.image = image;
        this.cca  = cca;
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
    public CCA getCCA() {
        return cca;
    }
    public StudentClass getStudentClass() { return sc; }

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
                && otherStudent.getCCA().equals(getCCA())
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
                .append(getCCA());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}
