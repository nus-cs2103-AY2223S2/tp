package seedu.sprint.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.testutil.TypicalApplications.BYTEDANCE;
import static seedu.sprint.testutil.TypicalApplications.GOOGLE;
import static seedu.sprint.testutil.TypicalApplications.MICROSOFT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sprint.testutil.InternshipBookBuilder;

/**
     * Reused from <a href="https://github.com/se-edu/addressbook-level4">AB4</a> with some additional testcases
     *
     * @author se-edu
     */
public class VersionedInternshipBookTest {
    private final ReadOnlyInternshipBook emptyInternshipBook = new InternshipBookBuilder().build();
    private final ReadOnlyInternshipBook internshipBookWithAlice = new InternshipBookBuilder()
            .withApplication(GOOGLE).build();
    private final ReadOnlyInternshipBook internshipBookWithBenson = new InternshipBookBuilder()
            .withApplication(BYTEDANCE).build();
    private final ReadOnlyInternshipBook internshipBookWithCarl = new InternshipBookBuilder()
            .withApplication(MICROSOFT).build();
    private final ReadOnlyInternshipBook internshipBookWithMultipleValues = new InternshipBookBuilder()
            .withApplication(GOOGLE).withApplication(BYTEDANCE).withApplication(MICROSOFT).build();

    @Test
    public void commit_singleInternshipBook_noStatesRemovedCurrentStateSaved() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(emptyInternshipBook);

        versionedInternshipBook.commit();
        assertInternshipBookListStatus(versionedInternshipBook,
                Collections.singletonList(emptyInternshipBook),
                emptyInternshipBook,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleInternshipBookPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);

        versionedInternshipBook.commit();
        assertInternshipBookListStatus(versionedInternshipBook,
                Arrays.asList(emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson),
                internshipBookWithBenson,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleInternshipBookPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 2);

        versionedInternshipBook.commit();
        assertInternshipBookListStatus(versionedInternshipBook,
                Collections.singletonList(emptyInternshipBook),
                emptyInternshipBook,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleInternshipBookPointerAtEndOfStateList_returnsTrue() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);

        assertTrue(versionedInternshipBook.canUndo());
    }

    @Test
    public void canUndo_multipleInternshipBookPointerAtStartOfStateList_returnsTrue() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 1);

        assertTrue(versionedInternshipBook.canUndo());
    }

    @Test
    public void canUndo_singleInternshipBook_returnsFalse() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(emptyInternshipBook);

        assertFalse(versionedInternshipBook.canUndo());
    }

    @Test
    public void canUndo_multipleInternshipBookPointerAtStartOfStateList_returnsFalse() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 2);

        assertFalse(versionedInternshipBook.canUndo());
    }

    @Test
    public void canRedo_multipleInternshipBookPointerNotAtEndOfStateList_returnsTrue() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 1);

        assertTrue(versionedInternshipBook.canRedo());
    }

    @Test
    public void canRedo_multipleInternshipBookPointerAtStartOfStateList_returnsTrue() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 2);

        assertTrue(versionedInternshipBook.canRedo());
    }

    @Test
    public void canRedo_singleInternshipBook_returnsFalse() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(emptyInternshipBook);

        assertFalse(versionedInternshipBook.canRedo());
    }

    @Test
    public void canRedo_multipleInternshipBookPointerAtEndOfStateList_returnsFalse() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);

        assertFalse(versionedInternshipBook.canRedo());
    }

    @Test
    public void undo_multipleInternshipBookPointerAtEndOfStateList_success() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);

        versionedInternshipBook.undo();
        assertInternshipBookListStatus(versionedInternshipBook,
                Collections.singletonList(emptyInternshipBook),
                internshipBookWithAlice,
                Collections.singletonList(internshipBookWithBenson));
    }

    @Test
    public void undo_multipleInternshipBookPointerNotAtStartOfStateList_success() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 1);

        versionedInternshipBook.undo();
        assertInternshipBookListStatus(versionedInternshipBook,
                Collections.emptyList(),
                emptyInternshipBook,
                Arrays.asList(internshipBookWithAlice, internshipBookWithBenson));
    }

    @Test
    public void undo_singleInternshipBook_throwsNoUndoableStateException() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(emptyInternshipBook);

        assertThrows(VersionedInternshipBook.NoUndoableStateException.class, versionedInternshipBook::undo);
    }

    @Test
    public void undo_multipleInternshipBookPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 2);

        assertThrows(VersionedInternshipBook.NoUndoableStateException.class, versionedInternshipBook::undo);
    }

    @Test
    public void redo_multipleInternshipBookPointerNotAtEndOfStateList_success() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 1);

        versionedInternshipBook.redo();
        assertInternshipBookListStatus(versionedInternshipBook,
                Arrays.asList(emptyInternshipBook, internshipBookWithAlice),
                internshipBookWithBenson,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleInternshipBookPointerAtStartOfStateList_success() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 2);

        versionedInternshipBook.redo();
        assertInternshipBookListStatus(versionedInternshipBook,
                Collections.singletonList(emptyInternshipBook),
                internshipBookWithAlice,
                Collections.singletonList(internshipBookWithBenson));
    }

    @Test
    public void redo_singleInternshipBook_throwsNoRedoableStateException() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(emptyInternshipBook);

        assertThrows(VersionedInternshipBook.NoRedoableStateException.class, versionedInternshipBook::redo);
    }

    @Test
    public void redo_multipleInternshipBookPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                emptyInternshipBook, internshipBookWithAlice, internshipBookWithBenson);

        assertThrows(VersionedInternshipBook.NoRedoableStateException.class, versionedInternshipBook::redo);
    }


    @Test
    public void equals() {
        VersionedInternshipBook versionedInternshipBook = prepareInternshipBookList(
                internshipBookWithAlice, internshipBookWithBenson);

        // same values -> returns true
        VersionedInternshipBook copy = prepareInternshipBookList(internshipBookWithAlice, internshipBookWithBenson);
        assertTrue(versionedInternshipBook.equals(copy));

        // same object -> returns true
        assertTrue(versionedInternshipBook.equals(versionedInternshipBook));

        // null -> returns false
        assertFalse(versionedInternshipBook.equals(null));

        // different types -> returns false
        assertFalse(versionedInternshipBook.equals(1));

        // different state list -> returns false
        VersionedInternshipBook differentInternshipBookList = prepareInternshipBookList(
                internshipBookWithBenson, internshipBookWithCarl);
        assertFalse(versionedInternshipBook.equals(differentInternshipBookList));

        // different current pointer index -> returns false
        VersionedInternshipBook differentCurrentStatePointer = prepareInternshipBookList(
                internshipBookWithAlice, internshipBookWithBenson);
        shiftCurrentStatePointerLeftwards(versionedInternshipBook, 1);
        assertFalse(versionedInternshipBook.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedInternshipBook} is currently pointing at
     * {@code expectedCurrentState}, states before {@code versionedInternshipBook#currentStatePointer}
     * is equal to {@code expectedStatesBeforePointer},and states after
     * {@code versionedInternshipBook#currentStatePointer} is equal to {@code expectedStatesAfterPointer}.
     */
    private void assertInternshipBookListStatus(VersionedInternshipBook versionedInternshipBook,
                                                List<ReadOnlyInternshipBook> expectedStatesBeforePointer,
                                                ReadOnlyInternshipBook expectedCurrentState,
                                                List<ReadOnlyInternshipBook> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new InternshipBook(versionedInternshipBook), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedInternshipBook.canUndo()) {
            versionedInternshipBook.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyInternshipBook expectedInternshipBook : expectedStatesBeforePointer) {
            assertEquals(expectedInternshipBook, new InternshipBook(versionedInternshipBook));
            versionedInternshipBook.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyInternshipBook expectedInternshipBook : expectedStatesAfterPointer) {
            versionedInternshipBook.redo();
            assertEquals(expectedInternshipBook, new InternshipBook(versionedInternshipBook));
        }

        // check that there are no more states after pointer
        assertFalse(versionedInternshipBook.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedInternshipBook.undo());
    }

    /**
     * Creates and returns a {@code VersionedInternshipBook} with the {@code internshipBookStates} added into it,
     * and the {@code VersionedInternshipBook#currentStatePointer} at the end of list.
     */
    private VersionedInternshipBook prepareInternshipBookList(ReadOnlyInternshipBook... internshipBookStates) {
        assertFalse(internshipBookStates.length == 0);

        VersionedInternshipBook versionedInternshipBook = new VersionedInternshipBook(internshipBookStates[0]);
        for (int i = 1; i < internshipBookStates.length; i++) {
            versionedInternshipBook.resetData(internshipBookStates[i]);
            versionedInternshipBook.commit();
        }

        return versionedInternshipBook;
    }

    /**
     * Shifts the {@code versionedInternshipBook#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedInternshipBook versionedInternshipBook, int count) {
        for (int i = 0; i < count; i++) {
            versionedInternshipBook.undo();
        }
    }
}
