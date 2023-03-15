package seedu.address.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.command.exceptions.OutOfBoundsCommandHistoryException;

public class CommandHistoryTest {
    @BeforeEach
    public void setUp() {
        CommandHistory.clear();
    }

    @Test
    public void traversePrevEmptyHistory_throwsOutOfBoundsCommandHistoryException() {
        assertThrows(OutOfBoundsCommandHistoryException.class, CommandHistory::prev);
    }

    @Test
    public void traverseNextEmptyHistory_throwsOutOfBoundsCommandHistoryException() {
        assertThrows(OutOfBoundsCommandHistoryException.class, CommandHistory::next);
    }

    @Test
    public void moreThanMaximumNumberOfCommands_overwriteEarliestCommand() {
        String expected = "expectedCommand";
        CommandHistory.push("dummyCommand");
        CommandHistory.push(expected);

        for (int i = 0; i < CommandHistory.MAXIMUM - 2; ++i) {
            CommandHistory.push(String.valueOf(i));
        }

        CommandHistory.push("testcommand");

        for (int i = 0; i < CommandHistory.MAXIMUM - 2; ++i) {
            CommandHistory.prev();
        }

        assertEquals(CommandHistory.prev(), expected);

    }
}
