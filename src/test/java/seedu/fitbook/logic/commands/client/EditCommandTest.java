package seedu.fitbook.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.ClearCommand;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.EditCommand;
import seedu.fitbook.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.testutil.client.ClientBuilder;
import seedu.fitbook.testutil.client.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the FitBookModel) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);

        FitBookModel expectedFitBookModel = new FitBookModelManager(new FitBook(model.getFitBook()),
                new FitBookExerciseRoutine(model.getFitBookExerciseRoutine()), new UserPrefs());
        expectedFitBookModel.setClient(model.getFilteredClientList().get(0), editedClient);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);

        FitBookModel expectedFitBookModel = new FitBookModelManager(new FitBook(model.getFitBook()),
                new FitBookExerciseRoutine(model.getFitBookExerciseRoutine()), new UserPrefs());
        expectedFitBookModel.setClient(lastClient, editedClient);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);

        FitBookModel expectedFitBookModel = new FitBookModelManager(new FitBook(model.getFitBook()),
                new FitBookExerciseRoutine(model.getFitBookExerciseRoutine()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST_PERSON);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedClient);

        FitBookModel expectedFitBookModel = new FitBookModelManager(new FitBook(model.getFitBook()),
                new FitBookExerciseRoutine(model.getFitBookExerciseRoutine()), new UserPrefs());
        expectedFitBookModel.setClient(model.getFilteredClientList().get(0), editedClient);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST_PERSON);

        // edit client in filtered list into a duplicate in FitBook
        Client clientInList = model.getFitBook().getClientList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder(clientInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of FitBook
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        CommandTestUtil.showClientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of FitBook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitBook().getClientList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditClientDescriptor copyDescriptor = new EditClientDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, CommandTestUtil.DESC_BOB)));
    }

}
