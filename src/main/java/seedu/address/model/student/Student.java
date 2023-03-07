package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueHomeworkList homeworkList = new UniqueHomeworkList();
    private final UniqueLessonsList lessonsList = new UniqueLessonsList();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Homework> homeworkList,
                   List<Lesson> lessonList) {
        requireAllNonNull(name, phone, email, address, tags, homeworkList, lessonList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.homeworkList.setHomeworks(homeworkList);
        this.lessonsList.setLessons(lessonList);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return set of tags
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of homework
     */
    public List<Homework> getHomeworkList() {
        return homeworkList.asUnmodifiableObservableList();
    }
    /**
     * Returns an immutable lessons list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of lessons
     */
    public List<Lesson> getLessonsList() {
        return lessonsList.asUnmodifiableObservableList();
    }


    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of completed homework
     */
    public List<Homework> getCompletedHomeworkList() {
        List<Homework> completedHomeworkList = new ArrayList<>();

        // filter homework list for completed homework
        for (Homework hw : homeworkList) {
            if (hw.isCompleted()) {
                completedHomeworkList.add(hw);
            }
        }
        return Collections.unmodifiableList(completedHomeworkList);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     *
     * @return list of pending homework
     */
    public List<Homework> getPendingHomeworkList() {
        List<Homework> pendingHomeworkList = new ArrayList<>();

        // filter homework list for pending homework
        for (Homework hw : homeworkList) {
            if (!hw.isCompleted()) {
                pendingHomeworkList.add(hw);
            }
        }
        return Collections.unmodifiableList(pendingHomeworkList);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    public List<Homework> getFilteredHomeworkList(Predicate<Homework> predicate) {
        List<Homework> filteredHomeworkList = new ArrayList<>();

        // filter homework list for homework that matches predicate
        for (Homework hw : homeworkList) {
            if (predicate.test(hw)) {
                filteredHomeworkList.add(hw);
            }
        }

        return Collections.unmodifiableList(filteredHomeworkList);
    }

    /**
     * Adds a homework to the homework list.
     *
     * @param homework homework to be added
     */
    public void addHomework(Homework homework) {
        // check for duplicate homework
        for (Homework hw : this.homeworkList) {
            if (hw.equals(homework)) {
                return;
            }
        }

        this.homeworkList.add(homework);
    }

    /**
     * Returns a homework from the homework list.
     *
     * @param index index of homework to be returned
     * @return homework
     */
    public Homework getHomework(Index index) {
        return this.homeworkList.getHomework(index.getZeroBased());
    }

    /**
     * Deletes a homework from the homework list.
     *
     * @param index index of homework to be deleted
     */
    public void deleteHomework(Index index) {
        Homework homeworkToDelete = this.homeworkList.getHomework(index.getZeroBased());
        this.homeworkList.remove(homeworkToDelete);
    }

    /**
     * Marks a homework as done from the homework list.
     *
     * @param index index of homework to be marked as done
     */
    public void markHomeworkAsDone(Index index) {
        Homework homeworkToMarkAsDone = this.homeworkList.getHomework(index.getZeroBased());
        homeworkToMarkAsDone.markAsDone();
    }

    /**
     * Marks a homework as undone from the homework list.
     *
     * @param index index of homework to be marked as undone
     */
    public void markHomeworkAsUndone(Index index) {
        Homework homeworkToMarkAsUndone = this.homeworkList.getHomework(index.getZeroBased());
        homeworkToMarkAsUndone.markAsUndone();
    }
    /**
     * Returns a homework from the homework list.
     *
     * @param index index of homework to be returned
     * @return homework
     */

    public Lesson getLesson(Index index) {
        return this.lessonsList.getLesson(index.getZeroBased());
    }

    /**
     * Deletes a homework from the homework list.
     *
     * @param index index of homework to be deleted
     */
    public void deleteLesson(Index index) {
        Lesson lessonToDelete = this.lessonsList.getLesson(index.getZeroBased());
        this.lessonsList.remove(lessonToDelete);
    }
    /**
     * Adds a lesson to the lesson list
     * @param lesson the lesson to be added
     */
    public void addLesson(Lesson lesson) {
        for (Lesson l : this.lessonsList) {
            if (l.equals(lesson)) {
                return;
            }
        }
        this.lessonsList.add(lesson);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    @SafeVarargs
    public final List<Lesson> getFilteredLessonsList(Predicate<Lesson>... predicates) {
        List<Lesson> filteredLessonsList = new ArrayList<>();

        for (Lesson l : lessonsList) {
            boolean allPredicatesMatch = true;
            for (Predicate<Lesson> predicate : predicates) {
                if (!predicate.test(l)) {
                    allPredicatesMatch = false;
                    break;
                }
            }
            if (allPredicatesMatch) {
                filteredLessonsList.add(l);
            }
        }

        return Collections.unmodifiableList(filteredLessonsList);
    }



    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson other person to compare to
     * @return boolean
     */
    public boolean isSameStudent(Student otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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

        // state check
        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getHomeworkList().equals(getHomeworkList());
    }

    /**
     * Returns a hash code for the person.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, homeworkList, lessonsList);
    }

    /**
     * Returns a string representation of the person.
     * Format: name; phone; email; address; tags; assignments
     *
     * @return string representation of the person
     */
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
                .append("; Assignments: ")
                .append(getHomeworkList());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public void updateFilteredHomeworkList(Predicate<Homework> homeworkStatusPredicate) {
        homeworkList.updateFilteredHomeworkList(homeworkStatusPredicate);
    }

    public boolean hasLesson() {
        return this.lessonsList.hasLesson();
    }
}
