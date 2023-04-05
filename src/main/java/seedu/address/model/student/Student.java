package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.score.Score;
import seedu.address.model.score.UniqueScoreList;
import seedu.address.model.score.UniqueScoreList.ScoreSummary;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;


/**
 * Represents a Student in the mathutoring.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Phone parentPhone;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueTaskList uniqueTaskList;
    private final UniqueScoreList uniqueScoreList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Phone parentPhone, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.parentPhone = parentPhone;
        this.tags.addAll(tags);
        this.uniqueTaskList = new UniqueTaskList();
        this.uniqueScoreList = new UniqueScoreList();
    }

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Phone parentPhone, Set<Tag> tags,
                   UniqueTaskList uniqueTaskList, UniqueScoreList uniqueScoreList) {
        requireAllNonNull(name, phone, email, address, tags, uniqueTaskList, uniqueScoreList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.parentPhone = parentPhone;
        this.tags.addAll(tags);
        this.uniqueTaskList = uniqueTaskList;
        this.uniqueScoreList = uniqueScoreList;
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

    public UniqueTaskList getTaskList() {
        return uniqueTaskList;
    }

    public ObservableList<Task> getTaskListAsObservableList() {
        return uniqueTaskList.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    //=========== Task ================================================================================

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list of this student.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return uniqueTaskList.contains(task);
    }

    /**
     * Adds a task to this student's task list.
     * The task must not already exist in their task list.
     */
    public void addTask(Task t) {
        requireNonNull(t);
        uniqueTaskList.add(t);
    }

    /**
     * Replaces the given task {@code target} in this student's task list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        uniqueTaskList.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this student's task list.
     * {@code key} must exist in the task list.
     */
    public void removeTask(Task key) {
        requireNonNull(key);
        uniqueTaskList.remove(key);
    }

    /**
     * Marks {@code task} in this student's task list as complete.
     * {@code task} must exist in the task list.
     */
    public void markTaskAsComplete(Task task) {
        requireNonNull(task);
        uniqueTaskList.markTaskAsComplete(task);
    }

    /**
     * Marks {@code task} in this student's task list as inprogress.
     * {@code task} must exist in the task list.
     */
    public void markTaskAsInProgress(Task task) {
        requireNonNull(task);
        uniqueTaskList.markTaskAsInProgress(task);
    }

    /**
     * Marks {@code task} in this student's task list as late.
     * {@code task} must exist in the task list.
     */
    public void markTaskAsLate(Task task) {
        requireNonNull(task);
        uniqueTaskList.markTaskAsLate(task);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     */
    public ObservableList<Task> getFilteredTaskList() {
        FilteredList<Task> filteredTasks = new FilteredList<>(this.getTaskListAsObservableList());
        return filteredTasks;
    }

    //=========== Score ================================================================================

    /**
     * Returns true if a score with the same identity as {@code score} exists in the score list of this student.
     */
    public boolean hasScore(Score score) {
        requireNonNull(score);
        return uniqueScoreList.contains(score);
    }

    /**
     * Adds a score to this student's score list.
     * The score must not already exist in their score list.
     */
    public void addScore(Score score) {
        uniqueScoreList.add(score);
    }

    public ObservableList<Score> getScoreListAsObservableList() {
        return uniqueScoreList.asUnmodifiableObservableList();
    }

    public UniqueScoreList getScoreList() {
        return uniqueScoreList;
    }

    /**
     * Removes {@code key} from this student's score list.
     * {@code key} must exist in the score list.
     */
    public void removeScore(Score key) {
        uniqueScoreList.remove(key);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Score}
     */
    public ObservableList<Score> getFilteredScoreList() {
        FilteredList<Score> filteredScores = new FilteredList<>(this.getScoreListAsObservableList());
        return filteredScores;
    }

    /**
     * Gets the sorted score list with recent score at front.
     * @return A view of list of sorted scores.
     */
    public ObservableList<Score> getSortedScoreList() {
        ObservableList<Score> sortedScoreList = this.uniqueScoreList.getSortedScoreList();
        return sortedScoreList;
    }

    /**
     * Gets the recent 5 scores with recent score at back.
     * @return A view of list of recent 5 scores.
     */
    public ObservableList<Score> getRecentScoreList() {
        ObservableList<Score> recentScoreList = this.uniqueScoreList.getRecentScoreList();
        return recentScoreList;
    }

    /**
     * Gets the summary statistic of recent 5 scores.
     * @return A view of list of recent 5 scores' summary statistic.
     */
    public ObservableList<ScoreSummary> getScoreSummary() {
        ObservableList<ScoreSummary> scoreSummary = this.uniqueScoreList.getScoreSummary();
        return scoreSummary;
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

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getParentPhone().equals(getParentPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags());
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
