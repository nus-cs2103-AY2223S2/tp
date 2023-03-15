package seedu.address.model.person.parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;

/**
 * A Parent Class that creates a Parent / Next-of-kin who is also a Person
 */
public class Parent extends Person {
    private final Age age;
    private final Image image;
    private final List<Student> children = new ArrayList<>();

    /**
     * A constructor that creates a Parent Object with particulars about the Parent.
     *
     * @param name Parent's / NOK's name.
     * @param age Parent's / NOK's age.
     * @param image Image of Parent / NOK.
     * @param email Email Address of Parent / NOK.
     * @param phone Phone number of Parent / NOK.
     * @param address Residential Address of Parent / NOK.
     * @param tags Tags given to Parent.
     */
    public Parent(Name name, Age age, Image image,
                  Email email, Phone phone, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.age = age;
        this.image = image;
    }

    /**
     * A method that returns the Age of Parent / NOK.
     *
     * @return Age of Parent / NOK.
     */
    public Age getAge() {
        return age;
    }

    /**
     * A method that returns the Image of Parent / NOK.
     *
     * @return Image of Parent / NOK.
     */
    public Image getImage() {
        return image;
    }

    /**
     * A method that returns a list of Students the Parent / NOK is related to.
     *
     * @return All Students that the Parent / NOK has relations to.
     */
    public List<Student> getChildren() {
        return children;
    }

    /**
     * A method that adds a Student to the Parent / NOK.
     *
     * @param student Student who is related to this Parent object.
     */
    public void addStudent(Student student) {
        children.add(student);
    }

    /**
     * A method that removes a Student from the Parent / NOK.
     *
     * @param student Student who is related to this Parent object.
     */
    public void removeStudent(Student student) {
        if (children.contains(student)) {
            children.remove(student);
        }
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
                .append("; Parent/NOK of:");

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
