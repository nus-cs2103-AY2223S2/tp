package teambuilder.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import teambuilder.commons.core.Momento;

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
    public void getLastUndoDescription_noLastUndo_emptyStringReturned() {
        HistoryUtil history = getFreshHistoryUtil();
        assertTrue(history.getLastUndoDescription() == "");

    }

    @Test
    public void getLastUndoDescription_hasLastUndo_descriptReturned() {
        HistoryUtil history = getFreshHistoryUtil();
        history.store(new FilledMomentoStub());
        history.undo();
        assertTrue(history.getLastUndoDescription() == FilledMomentoStub.DESC);
    }

    @Test
    public void undo_infiniteEmptyUndo_alwaysFalse() {
        HistoryUtil history = getFreshHistoryUtil();
        boolean isSuccessful = true;
        for (int i = 0; i < 100; i++) {
            isSuccessful = history.undo();
            assertFalse(isSuccessful);
        }
        assertFalse(isSuccessful);
    }

    @Test
    public void undo_infiniteUndoThenStoreAndUndo_failureThenSuccess() {
        HistoryUtil history = getFreshHistoryUtil();
        boolean isSuccessful = true;
        for (int i = 0; i < 100; i++) {
            isSuccessful = history.undo();
            assertFalse(isSuccessful);
        }
        history.store(new FilledMomentoStub());
        isSuccessful = history.undo();
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_nonMultipleStoreThenUndoAll_alwaysSuccess() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 13; i++) {
            history.store(new FilledMomentoStub());
        }
        boolean isSuccessful = false;
        for (int i = 0; i < 10; i++) {
            isSuccessful = history.undo();
            assertTrue(isSuccessful);
        }
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_afterStoringMaxPlusOneMemo_success() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 11; i++) {
            history.store(new FilledMomentoStub());
        }
        boolean isSuccessful = history.undo();
        assertTrue(isSuccessful);
    }

    @Test
    public void undo_twiceWithOneMemo_successThenFailure() {
        HistoryUtil history = getFreshHistoryUtil();
        history.store(new FilledMomentoStub());

        boolean isFirstSuccessful = history.undo();
        assertTrue(isFirstSuccessful);
        boolean isSecondSuccessful = history.undo();
        assertFalse(isSecondSuccessful);
    }


    @Test
    public void store_infiniteStores_noExceptionThrown() {
        HistoryUtil history = getFreshHistoryUtil();
        for (int i = 0; i < 100; i++) {
            history.store(new FilledMomentoStub());
        }
    }


    private HistoryUtil getFreshHistoryUtil() {
        HistoryUtil history = HistoryUtil.getInstance();
        while (history.undo()) {
        }
        ;
        history.store(new EmptyMomentoStub());
        history.undo();
        return history;
    }

    /**
     * Momento that returns true on restore and also already has a description.
     */
    private class FilledMomentoStub implements Momento {
        private static final String DESC = "THIS IS TRUE";

        @Override
        public boolean restore() {
            return true;
        }

        @Override
        public void setDescription(String desc) {

        }

        @Override
        public String toString() {
            return DESC;
        }
    }

    /**
     * Momento that returns true on restore and but has a null description.
     */
    private class EmptyMomentoStub implements Momento {
        private String desc = null;

        @Override
        public boolean restore() {
            return true;
        }

        @Override
        public void setDescription(String desc) {

        }

        @Override
        public String toString() {
            return desc;
        }
    }
}
