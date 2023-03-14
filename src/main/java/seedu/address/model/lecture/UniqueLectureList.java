package seedu.address.model.lecture;

import java.util.List;

import seedu.address.model.UniqueDataList;
import seedu.address.model.lecture.exceptions.DuplicateLectureException;
import seedu.address.model.lecture.exceptions.LectureNotFoundException;

/**
 * A list of lectures that enforces uniqueness between its elements and does not allow nulls.<p>
 *
 * A lecture is considered unique by comparing using {@code Lecture#isSameLecture(Lecture)}. As such, adding and
 * updating of lectures uses {@code Lecture#isSameLecture(Lecture)} for equality so as to ensure that the lecture being
 * added or updated is unique in terms of name in the {@code UniqueLectureList}.<p>
 *
 * However, the removal of a lecture uses {@code Lecture#equals(Object)} so as to ensure that the lecture with exactly
 * the same fields will be removed.<p>
 *
 * Supports a minimal set of list operations.<p>
 *
 * @see Lecture#isSameLecture(Lecture)
 */
public class UniqueLectureList extends UniqueDataList<Lecture> {

    /**
     * Constructs a {@code UniqueLectureList}.
     */
    public UniqueLectureList() {
        super((a, b) -> a.isSameLecture(b), DuplicateLectureException::new, LectureNotFoundException::new);
    }

    /**
     * Replaces the lecture {@code target} in the list with {@code editedLecture}.<p>
     * {@code target} must exist in the list.<p>
     * {@code editedLecture} must not have the same name as another existing lecture in the list.
     *
     * @param target The lecture to be replaced.
     * @param editedLecture The lecture that will replace.
     * @throws LectureNotFoundException Indicates that {@code target} does not exist in the list.
     * @throws DuplicateLectureException Indicates that {@code editedLecture} is the same as another existing
     *                                   lecture in the list.
     */
    public void setLecture(Lecture target, Lecture editedLecture) {
        super.setData(target, editedLecture);
    }

    /**
     * Replaces the content of this list with {@code replacement}.
     *
     * @param replacement The list containing the lecture that will replace.
     */
    public void setLectures(UniqueLectureList replacement) {
        super.setAllData(replacement);
    }

    /**
     * Replaces the contents of this list with {@code lectures}.<p>
     * {@code lectures} must not contain duplicate lectures.
     *
     * @param lectures The list containing the lectures that will replace.
     */
    public void setLectures(List<Lecture> lectures) {
        super.setAllData(lectures);
    }
}
