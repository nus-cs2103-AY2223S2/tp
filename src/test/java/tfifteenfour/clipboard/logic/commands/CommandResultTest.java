// package tfifteenfour.clipboard.logic.commands;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;

// public class CommandResultTest {
//     @Test
//     public void equals() {
//         CommandResult commandResult = new CommandResult(null, "feedback", false);

//         // same values -> returns true
//         assertTrue(commandResult.equals(new CommandResult(null, "feedback", false)));

//         // same object -> returns true
//         assertTrue(commandResult.equals(commandResult));

//         // null -> returns false
//         assertFalse(commandResult.equals(null));

//         // different types -> returns false
//         assertFalse(commandResult.equals(0.5f));

//         // different feedbackToUser value -> returns false
//         assertFalse(commandResult.equals(new CommandResult(null, "different", false)));

//         // different hasChangedRosterState value -> returns false
//         assertFalse(commandResult.equals(new CommandResult(null, "feedback", true)));
//     }

//     @Test
//     public void hashcode() {
//         CommandResult commandResult = new CommandResult(null, "feedback", false);

//         // same values -> returns same hashcode
//         assertEquals(commandResult.hashCode(), new CommandResult(null, "feedback", false).hashCode());

//         // different feedbackToUser value -> returns different hashcode
//         assertNotEquals(commandResult.hashCode(), new CommandResult(null, "different", false).hashCode());

//         // different hasChangedRosterState value -> returns different hashcode
//         assertNotEquals(commandResult.hashCode(), new CommandResult(null, "feedback", true).hashCode());
//     }
// }
