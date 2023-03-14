package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.CommandTestUtil.showProjectAtIndex;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.model.AddressBook;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;
import arb.testutil.EditProjectDescriptorBuilder;
import arb.testutil.ProjectBuilder;

public class EditProjectCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Project editedProject = new ProjectBuilder().build();
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(editedProject)
                .build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertCommandSuccess(editProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withTitle(VALID_TITLE_OIL_PAINTING)
                .withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .build();

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withTitle(VALID_TITLE_OIL_PAINTING)
                .withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(lastProject, editedProject);

        assertCommandSuccess(editProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST,
                new EditProjectDescriptor());
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);

        Project projectInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectInFilteredList).withTitle(VALID_TITLE_OIL_PAINTING).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST,
                new EditProjectDescriptorBuilder().withTitle(VALID_TITLE_OIL_PAINTING).build());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertCommandSuccess(editProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_duplicateProjectUnfilteredList_failure() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(firstProject).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_duplicateProjectFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST);

        // edit project in filtered list into a duplicate in address book
        Project projectInList = model.getAddressBook().getProjectList().get(INDEX_SECOND.getZeroBased());
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST,
                new EditProjectDescriptorBuilder(projectInList).build());

        assertCommandFailure(editProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withTitle(VALID_TITLE_OIL_PAINTING).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProjectList().size());

        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex,
                new EditProjectDescriptorBuilder().withTitle(VALID_TITLE_OIL_PAINTING).build());

        assertCommandFailure(editProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_currentListShownClient_failure() {
        Index validIndex = INDEX_FIRST;
        assertCommandFailure(new EditProjectCommand(validIndex, DESC_SKY_PAINTING),
                        ListType.CLIENT, model, Messages.MESSAGE_INVALID_LIST_PROJECT);
    }

    @Test
    public void equals() {
        final EditProjectCommand standardCommand = new EditProjectCommand(INDEX_FIRST, DESC_SKY_PAINTING);

        // same values -> returns true
        EditProjectDescriptor copyDescriptor = new EditProjectDescriptor(DESC_SKY_PAINTING);
        EditProjectCommand commandWithSameValues = new EditProjectCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_SECOND, DESC_SKY_PAINTING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_FIRST, DESC_OIL_PAINTING)));
    }
}
