package seedu.address.model.lecture;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.lecture.exceptions.DuplicateLectureException;
import seedu.address.model.lecture.exceptions.LectureNotFoundException;

/**
 * A list of lectures that enforces uniqueness between its elements and does not allow nulls.
 * A lecture is considered unique by comparing using {@code Lecture#isSameLecture(Lecture)}. As such, adding and
 * updating of lectures uses Lecture#isSameLecture(Lecture) for equality so as to ensure that the lecture being added or
 * updated is unique in terms of code in the UniqueLectureList. However, the removal of a lecture uses
 * Lecture#equals(Object) so as to ensure that the lecture with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lecture#isSameLecture(Lecture)
 */
public class UniqueLectureList extends UniqueDataList<Lecture> {

    public UniqueLectureList() {
        super((a, b) -> a.isSameLecture(b), DuplicateLectureException::new, LectureNotFoundException::new);
    }

    /**
     * Returns true if the list contains an equivalent lecture as the given argument.
     */
    @Override
    public boolean contains(Lecture toCheck) {
        return super.contains(toCheck);
    }

    /**
     * Adds a lecture to the list.
     * The lecture must not already exist in the list.
     */
    @Override
    public void add(Lecture toAdd) {
        super.add(toAdd);
    }

    /**
     * Replaces the lecture {@code target} in the list with {@code editedLecture}.
     * {@code target} must exist in the list.
     * The name of {@code editedLecture} must not be the same as another existing lecture in the list.
     */
    public void setLecture(Lecture target, Lecture editedLecture) {
        super.setData(target, editedLecture);
    }

    /**
     * Removes the equivalent lecture from the list.
     * The lecture must exist in the list.
     */
    @Override
    public void remove(Lecture toRemove) {
        super.remove(toRemove);
    }

    public void setLectures(UniqueLectureList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code lectures}.
     * {@code lectures} must not contain duplicate lectures.
     */
    public void setLectures(List<Lecture> lectures) {
        super.setAllData(lectures);
    }
}
