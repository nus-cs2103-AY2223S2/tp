package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Phone parentPhone;
    private final Set<Tag> tags = new HashSet<>();
    private final TaskList taskList = new TaskList();
    private final ScoreList scoreList = new ScoreList();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Phone parentPhone, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.parentPhone = parentPhone;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Phone getParentPhone() {
        return parentPhone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.contains(task);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        taskList.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code taskList}.
     * {@code key} must exist in the list.
     */
    public void removeTask(Task key) {
        taskList.remove(key);
    }

    //=========== Score ================================================================================
    /**
     * Returns true if a score with the same identity as {@code score} exists in the person.
     */
    public boolean hasScore(Score score) {
        requireNonNull(score);
        return scoreList.contains(score);
    }

    /**
     * Returns true if a score with the same identity as {@code score} exists in the address book.
     */
    public void addScore(Score score) {
        scoreList.add(score);
    }

    public ObservableList<Score> getScoreList() {
        return scoreList.asUnmodifiableObservableList();
    }

    public ScoreList scoreList() {
        return scoreList;
    }
    /**
     * Replaces the given score {@code target} in the list with {@code editedScore}.
     * {@code target} must exist in the list.
     * The score identity of {@code editedScore} must not be the same as another existing score in the list.
     */
    public void setScore(Score target, Score editedScore) {
        requireNonNull(editedScore);

        scoreList.setScore(target, editedScore);
    }

    /**
     * Removes {@code key} from this {@code scoreList}.
     * {@code key} must exist in the list.
     */
    public void removeScore(Score key) {
        scoreList.remove(key);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Score} backed by the internal list of
     * {@code versionedAddressBook}
     */
    public ObservableList<Score> getFilteredScoreList() {
        FilteredList<Score> filteredScores = new FilteredList<>(this.getScoreList());
        return filteredScores;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, parentPhone, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; ParentPhone: ")
                .append(getParentPhone());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
