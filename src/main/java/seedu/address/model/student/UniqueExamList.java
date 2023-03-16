package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicateEntryException;
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
    public void add(Exam toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Exam {@code target} in the list with {@code editedExam}.
     * {@code target} must exist in the list.
     * The Exam identity of {@code editedHomework} must not be the same as another existing homework in the list.
     */
    public void setExam(Exam target, Exam editedExam) {
        requireAllNonNull(target, editedExam);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (!target.isSameExam(editedExam) && contains(editedExam)) {
            throw new DuplicateEntryException();
        }

        internalList.set(index, editedExam);
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
     * Updates the list of exams to only contain exams that fufil a predicate.
     *
     * @param examStatusPredicate predicate object encapsulating qualifier
     */
    public void updateFilteredExamList(Predicate<Exam> examStatusPredicate) {
        requireNonNull(examStatusPredicate);
        internalList.removeIf(examStatusPredicate.negate());
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
        upcomingExams.sort(Comparator.comparing(Exam::getStartTime));
        if (upcomingExams.size() > 3) {
            upcomingExams = upcomingExams.subList(0, 3);
        }
        return FXCollections.observableArrayList(upcomingExams);
    }
}
