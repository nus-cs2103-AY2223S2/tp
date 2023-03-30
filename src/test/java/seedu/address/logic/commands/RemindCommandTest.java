package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.DeadlineWithinThreeDaysPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {

    private Model model = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());

    @Test
    public void equals() {
        DeadlineWithinThreeDaysPredicate firstPredicate =
                new DeadlineWithinThreeDaysPredicate();
        DeadlineWithinThreeDaysPredicate secondPredicate =
                new DeadlineWithinThreeDaysPredicate();

        RemindCommand remindFirstCommand = new RemindCommand(firstPredicate);
        RemindCommand remindSecondCommand = new RemindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values -> returns true
        RemindCommand remindFirstCommandCopy = new RemindCommand(firstPredicate);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }

    @Test
    public void execute() {
        String expectedMessage = String.format("Here are all the upcoming reminders!");
        DeadlineWithinThreeDaysPredicate predicate = new DeadlineWithinThreeDaysPredicate();
        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


}
