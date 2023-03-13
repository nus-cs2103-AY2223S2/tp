package seedu.address.model.person.parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;

/**
 * A Parent Class that creates a Parent / Next-of-kin who is a person
 */
public class Parent extends Person {
    private final Age age;
    private final Image image;
    private final IndexNumber indexNumber;
    private final Relationship relationship;
    private final Class sc;
    private final List<Student> children = new ArrayList<>();

    /**
     * A parent / next-of-kin (NOK) constructor.
     *
     * @param name Name of Parent / NOK.
     * @param relationship Relationship of Parent object (Parent status / NOK status).
     * @param age Age of Parent / NOK.
     * @param image Image of Parent / NOK.
     * @param email Email address of Parent / NOK.
     * @param phone Phone number of Parent / NOK.
     * @param address Residential address of Parent / NOK.
     * @param tags Tags given to Parent object.
     */
    public Parent(Class sc, IndexNumber indexNumber, Name name, Relationship relationship, Age age, Image image,
                  Email email, Phone phone, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.indexNumber = indexNumber;
        this.sc = sc;
        this.age = age;
        this.image = image;
        this.relationship = relationship;
    }

    /**
     * A method that returns the Student's Index Number.
     *
     * @return Student's Index Number.
     */
    public IndexNumber getIndexNumber() {
        return indexNumber;
    }

    /**
     * A method that returns the Student's class.
     *
     * @return Student's class.
     */
    public Class getStudentClass() {
        return sc;
    }

    /**
     * A method that returns the Age of parent / NOK.
     *
     * @return Parent / NOK age.
     */
    public Age getAge() {
        return age;
    }

    /**
     * A method that returns the Image of parent / NOK.
     *
     * @return Parent / NOK Image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * A method that returns the Relationship of parent / NOK.
     *
     * @return Parent / NOK relationship to Student.
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * A method that returns a list of students the parent / NOK is related to.
     *
     * @return All students that the Parent / NOK is in-charge of.
     */
    public List<Student> getChildren() {
        return children;
    }

    /**
     * A method that adds a student to the parent / NOK.
     *
     * @param student Student who is related to this Parent / NOK.
     */
    public void addStudent(Student student) {
        children.add(student);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Parent)) {
            return false;
        }

        Parent otherParent = (Parent) other;
        return otherParent.getName().equals(getName())
                && otherParent.getAge().equals(getAge())
                && otherParent.getImage().equals(getImage())
                && otherParent.getPhone().equals(getPhone())
                && otherParent.getEmail().equals(getEmail())
                && otherParent.getAddress().equals(getAddress())
                && otherParent.getTags().equals(getTags())
                && otherParent.getChildren().equals(getChildren());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getName())
                .append("; Parent Age: ")
                .append(getAge())
                .append("; Address: ")
                .append(getAddress())
                .append("; Image Path: ")
                .append(getImage())
                .append("; Parent Email: ")
                .append(getEmail())
                .append("; Parent Phone: ")
                .append(getPhone())
                .append("; ")
                .append(getRelationship())
                .append(" of:");

        List<Student> children = getChildren();
        for (Student child : children) {
            builder.append(" ")
                    .append(child);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
