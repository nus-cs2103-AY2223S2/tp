package seedu.techtrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.techtrack.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.techtrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.techtrack.logic.commands.CommandTestUtil.showRoleAtIndex;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.logic.commands.EditCommand.EditRoleDescriptor;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.UserPrefs;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.testutil.EditRoleDescriptorBuilder;
import seedu.techtrack.testutil.RoleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Role editedRole = new RoleBuilder().build();
        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(editedRole).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new RoleBook(model.getRoleBook()), new UserPrefs());
        expectedModel.setRole(model.getFilteredRoleList().get(0), editedRole);
        System.out.println(expectedModel.getFilteredRoleList().get(0));
        System.out.println(editedRole);
        System.out.println(expectedMessage);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRole = Index.fromOneBased(model.getFilteredRoleList().size());
        Role lastRole = model.getFilteredRoleList().get(indexLastRole.getZeroBased());

        RoleBuilder roleInList = new RoleBuilder(lastRole);
        Role editedRole = roleInList.withName(VALID_NAME_BOB).withPhone(VALID_CONTACT_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_CONTACT_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastRole, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new RoleBook(model.getRoleBook()), new UserPrefs());
        expectedModel.setRole(lastRole, editedRole);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditRoleDescriptor());
        Role editedRole = model.getFilteredRoleList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new RoleBook(model.getRoleBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);

        Role roleInFilteredList = model.getFilteredRoleList().get(INDEX_FIRST_PERSON.getZeroBased());
        Role editedRole = new RoleBuilder(roleInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditRoleDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new RoleBook(model.getRoleBook()), new UserPrefs());
        expectedModel.setRole(model.getFilteredRoleList().get(0), editedRole);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRoleUnfilteredList_failure() {
        Role firstRole = model.getFilteredRoleList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(firstRole).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateRoleFilteredList_failure() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);

        // edit role in filtered list into a duplicate in address book
        Role roleInList = model.getRoleBook().getRoleList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditRoleDescriptorBuilder(roleInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidRoleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoleList().size() + 1);
        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidRoleIndexFilteredList_failure() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRoleBook().getRoleList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditRoleDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditRoleDescriptor copyDescriptor = new EditRoleDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
