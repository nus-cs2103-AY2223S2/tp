package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private final String input1 = "add n/google r/backend s/new d/2023-02-01";
    private final String input2 = "edit 1 n/Google";
    private final String input3 = "blah";

    @Test
    public void emptyCommandHistory_getOlderInput_returnsEmptyString() {
        CommandHistory commandHistory = new CommandHistory();

        // List in commandHistory should be initialised with empty string
        assertEquals("", commandHistory.getOlderInput());

        // Once first element in commandHistory has been reached, further getOlderInput should still return the first
        // element.
        for (int i = 0; i < 3; i++) {
            assertEquals("", commandHistory.getOlderInput());
        }
    }

    @Test
    public void emptyCommandHistory_getNewerInput_returnsEmptyString() {
        CommandHistory commandHistory = new CommandHistory();

        // List in commandHistory should be initialised with empty string
        assertEquals("", commandHistory.getNewerInput());

        // Once last element in commandHistory has been reached, further getNewerInput should still return the last
        // element.
        for (int i = 0; i < 3; i++) {
            assertEquals("", commandHistory.getNewerInput());
        }
    }

    @Test
    public void commandHistory_withInputs_test() {
        CommandHistory commandHistory = this.createSampleCommandHistory();

        assertEquals(this.input3, commandHistory.getOlderInput());
        assertEquals(this.input2, commandHistory.getOlderInput());
        assertEquals(this.input1, commandHistory.getOlderInput());

        // When index of list is at first element, getOlderInput will still return first element.
        assertEquals(this.input1, commandHistory.getOlderInput());

        assertEquals(this.input2, commandHistory.getNewerInput());
        assertEquals(this.input3, commandHistory.getNewerInput());

        // When getNewerInput has returned all added inputs, it will return the empty string added when CommandHistory
        // was made.
        assertEquals("", commandHistory.getNewerInput());

        // When index of list at last element, getNewerInput will still return last element.
        assertEquals("", commandHistory.getNewerInput());
    }

    private CommandHistory createSampleCommandHistory() {
        CommandHistory commandHistory = new CommandHistory();

        commandHistory.addInput(this.input1);
        commandHistory.addInput(this.input2);
        commandHistory.addInput(this.input3);

        return commandHistory;
    }
}
