package seedu.address.logic.commands.parent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.DuplicateParentException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.testutil.TypicalParents;

class ParentAddCommandTest {
    private Parents parentList = TypicalParents.getTypicalPowerConnectParents();
    private Model model = new ModelManager(parentList, new UserPrefs());
    private Parent parent1 = TypicalParents.AMY; // new parent to add into list
    private Parent parent2 = TypicalParents.ALICE; // existing parent in list
    private Parent parent3 = TypicalParents.IDA; // new parent to add into list
    @Test
    public void executeTest() {
        // adding an existing parent to the parent list
        assertTrue(model.hasParent(parent2));
        assertThrows(DuplicateParentException.class, () -> parentList.addParent(parent2));
        assertThrows(DuplicateParentException.class, () -> model.addParent(parent2));

        // adding AMY into the parent list when AMY is not INSIDE it
        assertFalse(model.hasParent(parent1));
        assertDoesNotThrow(() -> parentList.addParent(parent1));
        assertDoesNotThrow(() -> model.addParent(parent1));

        // adding AMY into the parent list when AMY is already inside it
        assertTrue(model.hasParent(parent1));
        assertThrows(DuplicateParentException.class, () -> model.addParent(parent1));
        assertThrows(DuplicateParentException.class, () -> parentList.addParent(parent1));

        // Testing ParentAddCommand with valid input and success
        ParentAddCommand testCommand = new ParentAddCommand(parent3);
        String expectedSuccessMessage = String.format(ParentAddCommand.MESSAGE_SUCCESS, parent3.toString());
        ModelManager expectedModel = new ModelManager(model.getParents(), new UserPrefs());
        expectedModel.addParent(parent3);
        assertCommandSuccess(testCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void equalsTest() {
        ParentAddCommand addCommand1 = new ParentAddCommand(parent1);
        ParentAddCommand addCommand2 = new ParentAddCommand(parent2);
        ParentAddCommand addCommand3 = new ParentAddCommand(parent1);
        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));
        // same parent input -> returns true
        assertTrue(addCommand1.equals(addCommand3));
        // different parent input -> returns false
        assertFalse(addCommand1.equals(addCommand2));
        // null input -> returns false
        assertFalse(addCommand1.equals(null));
    }
}