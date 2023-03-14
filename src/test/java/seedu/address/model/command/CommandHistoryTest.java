package seedu.address.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import seedu.address.model.command.exceptions.OutOfBoundsCommandHistoryException;

@TestMethodOrder(OrderAnnotation.class)
public class CommandHistoryTest {
    @Test
    @Order(1)
    public void traversePrevEmptyHistory_throwsOutOfBoundsCommandHistoryException() {
        assertThrows(OutOfBoundsCommandHistoryException.class, CommandHistory::prev);
    }

    @Test
    @Order(2)
    public void traverseNextEmptyHistory_throwsOutOfBoundsCommandHistoryException() {
        assertThrows(OutOfBoundsCommandHistoryException.class, CommandHistory::next);
    }

    @Test
    @Order(3)
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
