package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.DESC_AMY;
import static arb.logic.commands.CommandTestUtil.DESC_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.CommandTestUtil.showClientAtIndex;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.logic.commands.client.EditClientCommand.EditClientDescriptor;
import arb.model.AddressBook;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.client.Client;
import arb.testutil.ClientBuilder;
import arb.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, ListType.CLIENT, ListType.CLIENT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditClientCommand editClientCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editClientCommand, ListType.CLIENT, ListType.CLIENT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST, new EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editClientCommand, ListType.CLIENT, ListType.CLIENT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, ListType.CLIENT, ListType.CLIENT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editClientCommand, ListType.CLIENT, model,
                Messages.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND.getZeroBased());
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editClientCommand, ListType.CLIENT, model,
                Messages.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editClientCommand, ListType.CLIENT, model,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editClientCommand, ListType.CLIENT, model,
                Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditClientDescriptor copyDescriptor = new EditClientDescriptor(DESC_AMY);
        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_FIRST, DESC_BOB)));
    }

}
