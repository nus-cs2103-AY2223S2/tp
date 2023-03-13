package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELDERLY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELDERLY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKLEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showElderlyAtIndex;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Elderly;
import seedu.address.testutil.EditElderlyDescriptorBuilder;
import seedu.address.testutil.ElderlyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditElderlyCommand.
 */
public class EditElderlyCommandTest {

    private final Model model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredElderlyList_success() {
        Elderly editedElderly = new ElderlyBuilder().build();
        EditElderlyDescriptor descriptor = new EditElderlyDescriptorBuilder(editedElderly).build();
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditElderlyCommand.MESSAGE_EDIT_ELDERLY_SUCCESS,
                editedElderly);

        Model expectedModel = new ModelManager(new FriendlyLink(model.getFriendlyLink()), new UserPrefs());
        expectedModel.setElderly(model.getFilteredElderlyList().get(0), editedElderly);

        assertCommandSuccess(editElderlyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredElderlyList_success() {
        Index indexLastElderly = Index.fromOneBased(model.getFilteredElderlyList().size());
        Elderly lastElderly = model.getFilteredElderlyList().get(indexLastElderly.getZeroBased());

        ElderlyBuilder elderlyInList = new ElderlyBuilder(lastElderly);
        Elderly editedElderly = elderlyInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withRiskLevel(VALID_RISKLEVEL_BOB).withTags(VALID_TAG_SINGLE).build();

        EditElderlyDescriptor descriptor = new EditElderlyDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withRiskLevel(VALID_RISKLEVEL_BOB)
                .withTags(VALID_TAG_SINGLE).build();
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(indexLastElderly, descriptor);

        String expectedMessage = String.format(EditElderlyCommand.MESSAGE_EDIT_ELDERLY_SUCCESS,
                editedElderly);

        Model expectedModel = new ModelManager(new FriendlyLink(model.getFriendlyLink()), new UserPrefs());
        expectedModel.setElderly(lastElderly, editedElderly);

        assertCommandSuccess(editElderlyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredElderlyList_failure() {
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(INDEX_FIRST_PERSON,
                new EditElderlyDescriptor());

        String expectedMessage = Messages.MESSAGE_NOT_EDITED;

        assertCommandFailure(editElderlyCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredElderlyList_success() {
        showElderlyAtIndex(model, INDEX_FIRST_PERSON);

        Elderly elderlyInFilteredList = model.getFilteredElderlyList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Elderly editedElderly = new ElderlyBuilder(elderlyInFilteredList).withName(VALID_NAME_BOB).build();
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(INDEX_FIRST_PERSON,
                new EditElderlyDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditElderlyCommand.MESSAGE_EDIT_ELDERLY_SUCCESS,
                editedElderly);

        Model expectedModel = new ModelManager(new FriendlyLink(model.getFriendlyLink()), new UserPrefs());
        expectedModel.setElderly(model.getFilteredElderlyList().get(0), editedElderly);

        assertCommandSuccess(editElderlyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateElderlyUnfilteredElderlyList_failure() {
        Elderly firstElderly = model.getFilteredElderlyList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditElderlyDescriptor descriptor = new EditElderlyDescriptorBuilder(firstElderly).build();
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editElderlyCommand, model, Messages.MESSAGE_DUPLICATE_ELDERLY);
    }

    @Test
    public void execute_duplicateElderlyFilteredElderlyList_failure() {
        showElderlyAtIndex(model, INDEX_FIRST_PERSON);

        // edit elderly in filtered list into a duplicate in address book
        Elderly elderlyInList = model.getFriendlyLink().getElderlyList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(INDEX_FIRST_PERSON,
                new EditElderlyDescriptorBuilder(elderlyInList).build());

        assertCommandFailure(editElderlyCommand, model, Messages.MESSAGE_DUPLICATE_ELDERLY);
    }

    @Test
    public void execute_invalidElderlyIndexUnfilteredElderlyList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredElderlyList().size() + 1);
        EditElderlyDescriptor descriptor = new EditElderlyDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(outOfBoundIndex,
                descriptor);

        assertCommandFailure(editElderlyCommand, model, Messages.MESSAGE_INVALID_ELDERLY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address PERSON
     */
    @Test
    public void execute_invalidElderlyIndexFilteredList_failure() {
        showElderlyAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriendlyLink().getElderlyList().size());

        EditElderlyCommand editElderlyCommand = new EditElderlyCommand(outOfBoundIndex,
                new EditElderlyDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editElderlyCommand, model, Messages.MESSAGE_INVALID_ELDERLY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditElderlyCommand standardCommand = new EditElderlyCommand(INDEX_FIRST_PERSON, DESC_ELDERLY_AMY);

        // same values -> returns true
        EditElderlyDescriptor copyDescriptor = new EditElderlyDescriptor(DESC_ELDERLY_AMY);
        EditElderlyCommand commandWithSameValues = new EditElderlyCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditElderlyCommand(INDEX_SECOND_PERSON, DESC_ELDERLY_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditElderlyCommand(INDEX_FIRST_PERSON, DESC_ELDERLY_BOB));
    }

}
