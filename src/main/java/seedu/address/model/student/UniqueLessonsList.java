package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.ConflictingLessonsException;
import seedu.address.model.student.exceptions.DuplicateEntryException;
import seedu.address.model.student.exceptions.DuplicateLessonException;
import seedu.address.model.student.exceptions.LessonNotFoundException;

/**
 * A list of lessons that enforces uniqueness between its elements and does not allow nulls.
 * A lesson is considered unique by comparing using {@code Lesson#isSameLesson(Lesson)}.
 * As such, adding and updating of lessons uses Lesson#isSameLesson(Lesson) for equality so as to ensure
 * that the lesson being added or updated is unique in terms of identity in the UniqueLessonList.
 * However, the removal of a lesson uses Lesson#equals(Object) so as to ensure that the lesson with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lesson#isSameLesson(Lesson)
 */
public class UniqueLessonsList implements Iterable<Lesson> {

    private final ObservableList<Lesson> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a lesson that has time conflict as the given argument.
     *
     * @param toCheck the lesson to be checked
     * @return true if the list contains a lesson with conflicting timeslot
     */
    public boolean contains(Lesson toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a lesson to the list.
     * The lesson must not already exist in the list.
     *
     * @param toAdd the lesson to be added
     */
    public void add(Lesson toAdd) throws DuplicateLessonException, ConflictingLessonsException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLessonException();
        }
        for (Lesson lesson : internalList) {
            if (lesson.isSameTimeLesson(toAdd)) {
                throw new ConflictingLessonsException();
            }
        }
        internalList.add(toAdd);
        internalList.sort(Comparator.comparing(Lesson::getStartTime));
    }

    /**
     * Removes the equivalent lesson from the list.
     * The lesson must exist in the list.
     *
     * @param toRemove the homework to be removed
     */
    public void remove(Lesson toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LessonNotFoundException();
        }
        internalList.sort(Comparator.comparing(Lesson::getStartTime));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @param index of the lesson to be returned
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public Lesson getLesson(int index) {
        return internalList.get(index);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain conflicting lessons.
     *
     * @param lessons the lessons to be added to the list
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsAreCompatible(lessons)) {
            throw new ConflictingLessonsException();
        }
        internalList.setAll(lessons);
        internalList.sort(Comparator.comparing(Lesson::getStartTime));
    }

    public boolean validLessons() {
        return lessonsAreCompatible(internalList);
    }

    public void setLesson(Integer target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);
        if (target >= internalList.size()) {
            throw new LessonNotFoundException();
        }
        if (contains(editedLesson)) {
            throw new DuplicateEntryException();
        }
        Lesson originalLesson = internalList.get(target);
        internalList.set(target, editedLesson);
        if (!this.validLessons()) {
            internalList.set(target, originalLesson);
            throw new ConflictingLessonsException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lesson> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    @Override
    public Iterator<Lesson> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if no lessons in {@code lessons} have conflicting time slots
     *
     * @param lessons the lessons to be checked.
     * @return true if {@code lessons} contains only lessons with no conflicting timeslots.
     */
    private boolean lessonsAreCompatible(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isSameTimeLesson(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks the number of lessons in the list
     * @return an int that is the number of lessons in the list
     */
    public int size() {
        return this.internalList.size();
    }

    public List<Lesson> getSortedLessonsList() {
        List<Lesson> tempList = internalList;
        tempList.sort(Comparator.comparing(Lesson::getStartTime));
        return tempList;
    }

    /**
     * Returns a list of lessons that are upcoming.
     *
     * @return a list of lessons that are upcoming.
     */
    public ObservableList<Lesson> getPastLessons() {
        ObservableList<Lesson> pastLessons = FXCollections.observableArrayList();
        for (Lesson lesson : internalList) {
            if (lesson.isPastLesson()) {
                pastLessons.add(lesson);
            }
        }
        return pastLessons;
    }

    /**
     * Returns a list of lessons that are upcoming.
     *
     * @return a list of lessons that are upcoming.
     */
    public ObservableList<Lesson> getUpcomingLessons() {
        ObservableList<Lesson> upcomingLessons = FXCollections.observableArrayList();
        for (Lesson lesson : internalList) {
            if (!lesson.isPastLesson()) {
                upcomingLessons.add(lesson);
            }
        }
        return upcomingLessons;
    }

    public boolean hasLesson() {
        return this.internalList.size() != 0;
    }

    /**
     * Returns true if the list contains a lesson that has time conflict as the given argument.
     * @param lesson the lesson to be checked
     * @return true if the list contains a lesson with conflicting timeslot
     */
    public boolean hasConflictingLessonTime(Lesson lesson) {
        for (Lesson l : internalList) {
            if (l.isSameTimeLesson(lesson)) {
                return true;
            }
        }
        return false;
    }
}
