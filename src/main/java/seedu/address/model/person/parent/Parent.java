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
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.StudentClass;
import seedu.address.model.tag.Tag;

/**
 * A Parent Class that creates a parent who is a person
 */
public class Parent extends Person {
    /**
     * @param name
     * @param indexNumber
     * @param email
     * @param address
     * @param tags
     */
    private final Age age;
    private final Image image;
    private final IndexNumber indexNumber;
    private final Relationship relationship;
    private final StudentClass sc;
    private final List<Student> children = new ArrayList<>();

    /**
     * A parent / next-of-kin (NOK) constructor
     *
     * @param name
     * @param relationship
     * @param age
     * @param image
     * @param email
     * @param phone
     * @param address
     * @param tags
     */
    public Parent(StudentClass sc, IndexNumber indexNumber, Name name, Relationship relationship, Age age, Image image,
                  Email email, Phone phone, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.indexNumber = indexNumber;
        this.sc = sc;
        this.age = age;
        this.image = image;
        this.relationship = relationship;
    }

    /**
     * A method that returns the Age of parent / NOK.
     * @return Age
     */
    public Age getAge() {
        return age;
    }

    /**
     * A method that returns the Image of parent / NOK.
     * @return Image
     */
    public Image getImage() {
        return image;
    }

    /**
     * A method that returns the Relationship of parent / NOK.
     * @return Relationship
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * A method that returns a list of students the parent / NOK is related to.
     * @return List of Students
     */
    public List<Student> getChildren() {
        return children;
    }

    /**
     * A method that adds a children to the parent
     * @param child
     */
    public void addChildren(Student child) {
        children.add(child);
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
                && otherParent.getTags().equals(getTags());
                //&& otherParent.getChildren().equals(getChildren());
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

        /*
        List<Student> children = getChildren();
        for (Student child : children) {
            builder.append(" ")
                    .append(child);
        }
         */

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
