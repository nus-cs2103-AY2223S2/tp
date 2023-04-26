package seedu.address.logic.commands.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CommandExceptionTest {
    private static final String MESSAGE = "Invalid Command!";

    @Test
    public void constructor() {
        CommandException ce = new CommandException(MESSAGE);
        assertEquals(MESSAGE, ce.getMessage());
    }

    @Test
    public void constructorWithCause() {
        CommandException ce = new CommandException(MESSAGE, new Throwable());
        assertEquals(MESSAGE, ce.getMessage());
    }
}
