package teambuilder.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import teambuilder.commons.core.Memento;

public class HistoryUtilTest {
    /**
     * HistoryUtil has a single permanent instance. Hence, the order of tests can affect the result of tests.
     */

    @Test
    public void compileTest() {
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void singleInstanceTest() {
        HistoryUtil one = HistoryUtil.getInstance();
        HistoryUtil two = HistoryUtil.getInstance();
        HistoryUtil three = HistoryUtil.getInstance();
        assertTrue(one == two && two == three && two != null);
    }

    @Test
    public void getLastUndoDescription_hasLastUndo_descriptReturned() {
        HistoryUtil history = getFreshHistoryUtil();
        history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);
        Optional<String> description = history.undo();
        assertTrue(description.get() == FilledMomentoStub.DESC);
    }

    @Test
    public void undo_infiniteEmptyUndo_alwaysFalse() {
        HistoryUtil history = getFreshHistoryUtil();
        boolean isSuccessful = true;
        for (int i = 0; i < 100; i++) {
            isSuccessful = history.undo().isPresent();
            assertFalse(isSuccessful);
        }
        assertFalse(isSuccessful);
    }

    @Test
    public void undo_infiniteUndoThenStoreAndUndo_failureThenSuccess() {
        HistoryUtil history = getFreshHistoryUtil();
        boolean isSuccessful = true;
        for (int i = 0; i < 100; i++) {
            isSuccessful = history.undo().isPresent();
            assertFalse(isSuccessful);
        }
        history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);
        isSuccessful = history.undo().isPresent();
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_nonMultipleStoreThenUndoAll_alwaysSuccess() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 13; i++) {
            history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);
        }
        boolean isSuccessful = false;
        for (int i = 0; i < 10; i++) {
            isSuccessful = history.undo().isPresent();
            assertTrue(isSuccessful);
        }
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_afterStoringMaxPlusOneMemo_success() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 11; i++) {
            history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);
        }
        boolean isSuccessful = history.undo().isPresent();
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_twiceWithOneMemo_successThenFailure() {
        HistoryUtil history = getFreshHistoryUtil();
        history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);

        boolean isFirstSuccessful = history.undo().isPresent();
        assertTrue(isFirstSuccessful);
        boolean isSecondSuccessful = history.undo().isPresent();
        assertFalse(isSecondSuccessful);
    }

    @Test
    public void store_infiniteStores_noExceptionThrown() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 100; i++) {
            history.storePast(new FilledMomentoStub(), FilledMomentoStub.DESC);
        }
    }

    private HistoryUtil getFreshHistoryUtil() {
        HistoryUtil history = HistoryUtil.getInstance();
        while (history.undo().isPresent()) {
        }
        ;
        return history;
    }

    /**
     * Momento that returns true on restore and also already has a description.
     */
    private class FilledMomentoStub implements Memento {
        private static final String DESC = "THIS SHOULD APPEAR!";

        @Override
        public boolean restore() {
            return true;
        }

        @Override
        public Memento getUpdatedMemento() {
            return new FilledMomentoStub();
        }

    }
}
