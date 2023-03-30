package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryArrayListTest {

    private CommandHistoryArrayList commandHistoryArrayList;

    @BeforeEach
    public void setUp() {
        ArrayList<String> emptyArrayList = new ArrayList<>();
        commandHistoryArrayList = new CommandHistoryArrayList(emptyArrayList);
    }

    @Test
    public void getPreviousUserInput_noPreviousUserInput_returnsNull() {
        assertNull(commandHistoryArrayList.getPreviousUserInput(""));
    }

    @Test
    public void getNextUserInput_noNextUserInput_returnsNull() {
        assertNull(commandHistoryArrayList.getNextUserInput());
    }

    @Test
    public void getPreviousAndNextUserInput_success() {
        String originalString = "original";

        commandHistoryArrayList.commitUserInput("123");
        commandHistoryArrayList.commitUserInput("456");
        commandHistoryArrayList.commitUserInput("789");

        assertEquals(commandHistoryArrayList.getPreviousUserInput(originalString), "789");
        assertEquals(commandHistoryArrayList.getPreviousUserInput("dummy text aaa"), "456");
        assertEquals(commandHistoryArrayList.getPreviousUserInput("dummy text bbb"), "123");
        assertNull(commandHistoryArrayList.getPreviousUserInput("dummy text ccc"));

        assertEquals(commandHistoryArrayList.getNextUserInput(), "456");
        assertEquals(commandHistoryArrayList.getNextUserInput(), "789");
        assertEquals(commandHistoryArrayList.getNextUserInput(), originalString);
        assertNull(commandHistoryArrayList.getNextUserInput());
    }
}
