package seedu.techtrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.techtrack.model.role.Role;
import seedu.techtrack.testutil.RoleBuilder;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult<String> commandResult = new CommandResult<>("feedback");

        CommandResult<Role> commandResultAlice = new CommandResult<>(new RoleBuilder().withName("Alice").build());
        CommandResult<Role> commandResultBob = new CommandResult<>(new RoleBuilder().withName("Bob").build());

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult<>("feedback")));
        assertTrue(commandResult.equals(new CommandResult<>("feedback", false, false)));
        assertTrue(commandResultAlice.equals(commandResultAlice));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));
        assertTrue(commandResultAlice.equals(new CommandResult<>(new RoleBuilder().withName("Alice").build())));

        // null -> returns false
        assertFalse(commandResult.equals(null));
        assertFalse(commandResultAlice.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));
        assertFalse(commandResultAlice.equals("feedback"));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult<>("different")));
        assertFalse(commandResultAlice.equals(commandResultBob));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult<>("feedback", true, false)));
        assertFalse(commandResultAlice.equals(new CommandResult<>(new RoleBuilder().withName("Alice").build(),
                true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult<>("feedback", false, true)));
        assertFalse(commandResultAlice.equals(new CommandResult<>(new RoleBuilder().withName("Alice").build(),
                false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult<String> commandResult = new CommandResult<>("feedback");

        CommandResult<Role> commandResultAlice = new CommandResult<>(new RoleBuilder().withName("Alice").build());
        CommandResult<Role> commandResultBob = new CommandResult<>(new RoleBuilder().withName("Bob").build());

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult<>("feedback").hashCode());
        assertEquals(commandResultAlice.hashCode(),
                new CommandResult<>(new RoleBuilder().withName("Alice").build()).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult<>("different").hashCode());
        assertNotEquals(commandResultAlice.hashCode(), commandResultBob.hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult<>("feedback", true, false).hashCode());
        assertNotEquals(commandResultAlice.hashCode(),
                new CommandResult<>(new RoleBuilder().withName("Alice").build(), true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult<>("feedback", false, true).hashCode());
        assertNotEquals(commandResultAlice.hashCode(),
                new CommandResult<>(new RoleBuilder().withName("Alice").build(), false, true).hashCode());
    }

}
