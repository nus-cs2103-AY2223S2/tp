package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalShop;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.person.Customer;

/**
 * @@author bakano98 reused
 * Solution adapted from
 * https://github.com/AY2122S2-CS2103T-T13-4/tp/commit/c896383a4b216daac2882ebc1b232684329dc59e
 */

public class RedoCommandTest {
    private static final StackUndoRedo EMPTY_STACK = new StackUndoRedo();

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalShop());
    private final DeleteCustomerCommand deleteCommandOne = new DeleteCustomerCommand(1);
    private final DeleteCustomerCommand deleteCommandTwo = new DeleteCustomerCommand(2);

    @Test
    public void execute() throws Exception {
        deleteCommandOne.setData(EMPTY_STACK);

        StackUndoRedo undoRedoStack = prepareStack(
                 Collections.emptyList(), Arrays.asList(deleteCommandOne));
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.setData(undoRedoStack);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalShop());


        Customer firstCustomer = expectedModel.getFilteredCustomerList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.deleteCustomer(firstCustomer);

        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no command in redoStack
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_FAILURE);
    }

    public static StackUndoRedo prepareStack(List<RedoableCommand> undoElements,
                                             List<RedoableCommand> redoElements) {
        StackUndoRedo undoRedoStack = new StackUndoRedo();
        undoElements.forEach(undoRedoStack::push);

        Collections.reverse(redoElements);
        redoElements.forEach(undoRedoStack::push);
        redoElements.forEach(unused -> undoRedoStack.popUndo());

        return undoRedoStack;
    }
}

