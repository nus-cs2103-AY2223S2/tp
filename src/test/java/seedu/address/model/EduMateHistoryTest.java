package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EduMateHistoryTest {
    private final EduMateHistory eduMateHistory = new EduMateHistory();

    @Test
    public void getPreviousCommand_nothingInList() {
        assertTrue(eduMateHistory.getPreviousCommand(true) == null);
        assertTrue(eduMateHistory.getPreviousCommand(false) == null);
    }

    @Test
    public void getPreviousCommand_somethingInList() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("help");

        eduMateHistory.setList(arr);
        assertTrue(eduMateHistory.getPreviousCommand(true).equals("help"));
        assertTrue(eduMateHistory.getPreviousCommand(false).equals(""));
    }

    @Test
    public void getCurrentCommand_pressUpOnce() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("help");

        eduMateHistory.setList(arr);
        assertFalse(eduMateHistory.isUpPressedBefore());
        assertTrue(eduMateHistory.getPreviousCommand(true).equals("help"));
        assertTrue(eduMateHistory.isUpPressedBefore());

        assertTrue(eduMateHistory.getCurrentCommand().equals("help"));
        eduMateHistory.getPreviousCommand(false);
    }

    @Test
    public void equals() {
        assertEquals(eduMateHistory, eduMateHistory);
        assertFalse(eduMateHistory.equals(5));
    }

}
