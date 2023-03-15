package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.DESC_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showSupplierAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.OrderList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.supplier.Supplier;
import trackr.testutil.EditSupplierDescriptorBuilder;
import trackr.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditSupplierCommand.
 */
public class EditSupplierCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Supplier editedSupplier = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(editedSupplier).build();
        EditSupplierCommand editCommand = new EditSupplierCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage = String.format(EditSupplierCommand.MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setSupplier(model.getFilteredSupplierList().get(0), editedSupplier);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSupplier = Index.fromOneBased(model.getFilteredSupplierList().size());
        Supplier lastSupplier = model.getFilteredSupplierList().get(indexLastSupplier.getZeroBased());

        SupplierBuilder supplierInList = new SupplierBuilder(lastSupplier);
        Supplier editedSupplier = supplierInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditSupplierCommand editCommand = new EditSupplierCommand(indexLastSupplier, descriptor);

        String expectedMessage = String.format(EditSupplierCommand.MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setSupplier(lastSupplier, editedSupplier);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditSupplierCommand editCommand = new EditSupplierCommand(INDEX_FIRST_OBJECT, new EditSupplierDescriptor());
        Supplier editedSupplier = model.getFilteredSupplierList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage = String.format(EditSupplierCommand.MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showSupplierAtIndex(model, INDEX_FIRST_OBJECT);

        Supplier supplierInFilteredList = model.getFilteredSupplierList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Supplier editedSupplier = new SupplierBuilder(supplierInFilteredList).withName(VALID_NAME_BOB).build();
        EditSupplierCommand editCommand = new EditSupplierCommand(INDEX_FIRST_OBJECT,
                new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditSupplierCommand.MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());
        expectedModel.setSupplier(model.getFilteredSupplierList().get(0), editedSupplier);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSupplierUnfilteredList_failure() {
        Supplier firstSupplier = model.getFilteredSupplierList().get(INDEX_FIRST_OBJECT.getZeroBased());
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(firstSupplier).build();
        EditSupplierCommand editCommand = new EditSupplierCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editCommand, model, EditSupplierCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }

    @Test
    public void execute_duplicateSupplierFilteredList_failure() {
        showSupplierAtIndex(model, INDEX_FIRST_OBJECT);

        // edit supplier in filtered list into a duplicate in address book
        Supplier supplierInList = model.getSupplierList().getSupplierList().get(INDEX_SECOND_OBJECT.getZeroBased());
        EditSupplierCommand editCommand = new EditSupplierCommand(INDEX_FIRST_OBJECT,
                new EditSupplierDescriptorBuilder(supplierInList).build());

        assertCommandFailure(editCommand, model, EditSupplierCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }

    @Test
    public void execute_invalidSupplierIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSupplierList().size() + 1);
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditSupplierCommand editCommand = new EditSupplierCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidSupplierIndexFilteredList_failure() {
        showSupplierAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSupplierList().getSupplierList().size());

        EditSupplierCommand editCommand = new EditSupplierCommand(outOfBoundIndex,
                new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditSupplierCommand standardCommand = new EditSupplierCommand(INDEX_FIRST_OBJECT, DESC_AMY);

        // same values -> returns true
        EditSupplierDescriptor copyDescriptor = new EditSupplierDescriptor(DESC_AMY);
        EditSupplierCommand commandWithSameValues = new EditSupplierCommand(INDEX_FIRST_OBJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditSupplierCommand(INDEX_SECOND_OBJECT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSupplierCommand(INDEX_FIRST_OBJECT, DESC_BOB)));
    }

}
