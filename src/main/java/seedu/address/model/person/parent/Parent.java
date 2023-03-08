package seedu.address.model.person.parent;

import seedu.address.model.person.*;
import seedu.address.model.person.student.*;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Parent extends Person {
    /**
     *
     *
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

    public Parent(Name name, Relationship relationship, Sex sex, Age age, Image image, Email email, Phone phone,
                    Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.sex = sex;
        this.age = age;
        this.image = image;
        this.relationship = relationship;
    }

    public Sex getSex() {
        return sex;
    }
    public Age getAge() {
        return age;
    }
    public Image getImage() {
        return image;
    }
    public Relationship getRelationship() {
        return relationship;
    }

    public List<Student> getChildren() {
        return children;
    }

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
