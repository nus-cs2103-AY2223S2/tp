package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class DisplayGroupCommandTest {

    private String expectedOutput = "Listed all groups";
    

    @Test
    public void checkMessageSuccess() {
        assertEquals(DisplayGroupCommand.MESSAGE_SUCCESS, expectedOutput);
    }

}
