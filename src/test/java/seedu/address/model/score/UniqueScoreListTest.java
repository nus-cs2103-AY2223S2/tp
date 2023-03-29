package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalScores.SCORE_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.score.exceptions.DuplicateScoreException;
import seedu.address.model.score.exceptions.ScoreNotFoundException;

public class UniqueScoreListTest {
    private final UniqueScoreList uniqueScoreList = new UniqueScoreList();

    @Test
    public void contains_nullScore_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScoreList.contains(null));
    }

    @Test
    public void contains_scoreNotInList_returnFalse() {
        assertFalse(uniqueScoreList.contains(SCORE_1));
    }

    @Test
    public void contains_scoreInList_returnTrue() {
        uniqueScoreList.add(SCORE_1);
        assertTrue(uniqueScoreList.contains(SCORE_1));
    }

    @Test
    public void add_nullScore_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScoreList.add(null));
    }

    @Test
    public void add_duplicateScore_throwsDuplicateScoreException() {
        uniqueScoreList.add(SCORE_1);
        assertThrows(DuplicateScoreException.class, () -> uniqueScoreList.add(SCORE_1));
    }

    @Test
    public void remove_nullScore_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScoreList.remove(null));
    }

    @Test
    public void remove_scoreDoesNotExist_throwsScoreNotFoundException() {
        assertThrows(ScoreNotFoundException.class, () -> uniqueScoreList.remove(SCORE_1));
    }

    @Test
    public void remove_existingScore_removesScore() {
        uniqueScoreList.add(SCORE_1);
        uniqueScoreList.remove(SCORE_1);
        UniqueScoreList expectedUniqueScoreList = new UniqueScoreList();
        assertTrue(uniqueScoreList.equals(expectedUniqueScoreList));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueScoreList.asUnmodifiableObservableList()
                .remove(0));
    }

    @Test
    public void add_score_increasesSize() {
        int sizeBefore = uniqueScoreList.size();
        uniqueScoreList.add(SCORE_1);
        int sizeAfter = uniqueScoreList.size();
        assertTrue(sizeBefore + 1 == sizeAfter);
    }

    @Test
    public void remove_score_decreasesSize() {
        uniqueScoreList.add(SCORE_1);
        int sizeBefore = uniqueScoreList.size();
        uniqueScoreList.remove(SCORE_1);
        int sizeAfter = uniqueScoreList.size();
        assertTrue(sizeBefore - 1 == sizeAfter);
    }

    @Test
    public void get_invalidScoreIndex_throwsIndexOutofBoundsException() {
        uniqueScoreList.add(SCORE_1);
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueScoreList.get(INDEX_SECOND_STUDENT.getZeroBased()));
    }
}
