package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.ConflictingExamsException;
import seedu.address.model.student.exceptions.DuplicateEntryException;
import seedu.address.model.student.exceptions.DuplicateExamsException;
import seedu.address.model.student.exceptions.EntryNotFoundException;

/**
 * A list of exams that enforces uniqueness between its elements and does not allow nulls.
 * An exam is considered unique by comparing using {@code Exam#isSameExam(Exam)}.
 * As such, adding and updating of exam uses Exam#isSameExam(Exam) for equality
 * so as to ensure that the Exam being added or updated is unique in terms of identity in the
 * UniqueExamList. However, the removal of a homework uses Exam#equals(Object) so as to ensure
 * that the Exam with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueExamList implements Iterable<Exam> {
    private final ObservableList<Exam> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exam> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Exam as the given argument.
     *
     * @param toCheck the Exam to be checked
     * @return true if the list contains the Exam
     */
    public boolean contains(Exam toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExam);
    }

    /**
     * Adds an Exam to the list.
     * The Exam must not already exist in the list.
     *
     * @param toAdd the Exam to be added
     */
    public void add(Exam toAdd) throws DuplicateExamsException, ConflictingExamsException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExamsException();
        }
        for (Exam exam : internalList) {
            if (exam.isSameTimeExam(toAdd)) {
                throw new ConflictingExamsException();
            }
        }
        internalList.add(toAdd);
        internalList.sort((l1, l2) -> l1.getStartTime().compareTo(l2.getStartTime()));
    }

    /**
     * Replaces the Exam {@code target} in the list with {@code editedExam}.
     * {@code target} must exist in the list.
     * The Exam identity of {@code editedHomework} must not be the same as another existing homework in the list.
     */
    public void setExam(Integer target, Exam editedExam) {
        requireAllNonNull(target, editedExam);
        if (contains(editedExam)) {
            throw new DuplicateEntryException();
        }
        Exam originalExam = internalList.get(target);
        internalList.set(target, editedExam);
        if (!this.validExams()) {
            internalList.set(target, originalExam);
            throw new ConflictingExamsException();
        }
    }

    /**
     * Removes the equivalent Exam from the list.
     * The Exam must exist in the list.
     *
     * @param toRemove the Exam to be removed
     */
    public void remove(Exam toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate Exams.
     *
     * @param replacement the homeworks to be added to the list
     */
    public void setExams(UniqueExamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Overloaded method that accepts a List of Exams item
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate Exams.
     *
     * @param replacement the Exams to be added to the list
     */
    public void setExams(List<Exam> replacement) {
        requireAllNonNull(replacement);
        if (!examsAreUnique(replacement)) {
            throw new DuplicateEntryException();
        }

        internalList.setAll(replacement);
    }

    /**
     * Returns the exam object of index 'index'.
     *
     * @param index of the Exam to be returned
     * @return the Exam at that index in the exam list.
     */
    public Exam getExam(int index) {
        return internalList.get(index);
    }

    /**
     * Returns the Exam list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exam> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return the iterator over the Exam objects in this list.
     */
    @Override
    public Iterator<Exam> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if {@code exams} contains only unique Exams.
     *
     * @param exams a list of exams to be checked
     * @return true if {@code exams} contains only unique homeworks
     */
    private boolean examsAreUnique(List<Exam> exams) {
        for (int i = 0; i < exams.size() - 1; i++) {
            for (int j = i + 1; j < exams.size(); j++) {
                if (exams.get(i).isSameExam(exams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean examsAreCompatible(List<Exam> exams) {
        for (int i = 0; i < exams.size() - 1; i++) {
            for (int j = i + 1; j < exams.size(); j++) {
                if (exams.get(i).isSameTimeExam(exams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validExams() {
        return examsAreUnique(internalList) && examsAreCompatible(internalList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueExamList // instanceof handles nulls
                && internalList.equals(((UniqueExamList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns a list of past exams.
     * The list is sorted by start time and capped at 3 exams.
     *
     * @return a list of past exams.
     */
    public ObservableList<Exam> getPastExams() {
        ObservableList<Exam> pastExams = FXCollections.observableArrayList();
        for (Exam exam : internalList) {
            if (exam.isPastExam()) {
                pastExams.add(exam);
            }
        }
        return pastExams;
    }

    /**
     * Returns a list of upcoming exams.
     * The list is sorted by start time and capped at 3 exams.
     *
     * @return a list of upcoming exams.
     */
    public ObservableList<Exam> getUpcomingExams() {
        List<Exam> upcomingExams = FXCollections.observableArrayList();
        for (Exam exam : internalList) {
            if (!exam.isPastExam()) {
                upcomingExams.add(exam);
            }
        }
        return FXCollections.observableArrayList(upcomingExams);
    }

    /**
     * Returns true if the list contains an exam with the same time as the lesson.
     * @param lesson the lesson to be checked
     * @return true if the list contains an exam with the same time as the lesson.
     */
    public boolean hasConflictingExamTime(Lesson lesson) {
        for (Exam exam : internalList) {
            if (exam.isSameTimeLesson(lesson)) {
                return true;
            }
        }
        return false;
    }
}
