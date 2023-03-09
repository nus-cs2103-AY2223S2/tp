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
import seedu.address.model.person.Sex;
import seedu.address.model.person.student.Student;
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
    private final Sex sex;
    private final Age age;
    private final Image image;
    private final Relationship relationship;
    private final List<Student> children = new ArrayList<>();

    /**
     * A parent constructor
     * @param name
     * @param relationship
     * @param sex
     * @param age
     * @param image
     * @param email
     * @param phone
     * @param address
     * @param tags
     */
    public Parent(Name name, Relationship relationship, Sex sex, Age age, Image image, Email email, Phone phone,
                    Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.sex = sex;
        this.age = age;
        this.image = image;
        this.relationship = relationship;
    }

    /**
     * A method that returns the Sex of parent
     * @return Sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * A method that returns the Age of parent
     * @return Age
     */
    public Age getAge() {
        return age;
    }

    /**
     * A method that returns the Image of parent
     * @return Image
     */
    public Image getImage() {
        return image;
    }

    /**
     * A method that returns the Relationship of parent
     * @return Relationship
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * A method that returns a list of students
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
                && otherParent.getSex().equals(getSex())
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
                .append("; Sex: ")
                .append(getSex())
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
