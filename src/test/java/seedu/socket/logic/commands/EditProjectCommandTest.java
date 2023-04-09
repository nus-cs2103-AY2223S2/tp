package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_CHARLIE;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_RANDOM;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalProjects.getTypicalSocket;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.Socket;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.testutil.EditProjectDescriptorBuilder;
import seedu.socket.testutil.ProjectBuilder;
import seedu.socket.testutil.TypicalProjects;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditProjectCommandTest {

    private Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
    private Model modelWithProjects = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Set<Person> members = model.getFilteredProjectList().get(0).getMembers();
        Project editedProject = new ProjectBuilder().withMembers(members).build();
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(editedProject).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);
        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);
        assertCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withName(VALID_PROJECT_NAME_CHARLIE)
                .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
                .withProjectDeadline(VALID_PROJECT_DEADLINE_BRAVO).build();
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_CHARLIE)
                .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
                .withDeadline(VALID_PROJECT_DEADLINE_BRAVO).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, editedProject);

        assertCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectCommand.EditProjectDescriptor());
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());

        assertCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project editedProject = new ProjectBuilder(projectInFilteredList).withName(VALID_PROJECT_NAME_RANDOM).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder().withName(VALID_PROJECT_NAME_RANDOM).build());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_duplicateProjectUnfilteredList_failure() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(firstProject).build();
        EditProjectCommand editCommand = new EditProjectCommand(INDEX_SECOND_PROJECT, descriptor);

        assertCommandFailure(editCommand, model, EditProjectCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_duplicateProjectFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        // edit project in filtered list into a duplicate in address book
        Project projectInList = model.getSocket().getProjectList().get(INDEX_SECOND_PROJECT.getZeroBased());
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder(projectInList).build());

        assertCommandFailure(editProjectCommand, model, EditProjectCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        EditProjectCommand.EditProjectDescriptor descriptor =
                new EditProjectDescriptorBuilder().withName(VALID_PROJECT_NAME_ALPHA).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSocket().getProjectList().size());

        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex,
                new EditProjectDescriptorBuilder().withName(VALID_PROJECT_NAME_ALPHA).build());

        assertCommandFailure(editProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void testEquals() {
        final EditProjectCommand standardCommand = new EditProjectCommand(INDEX_FIRST_PERSON,
                new EditProjectDescriptorBuilder()
                        .withName(VALID_PROJECT_NAME_ALPHA)
                        .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                        .withMeeting(VALID_PROJECT_MEETING_ALPHA).build());

        // same values -> returns true
        EditProjectCommand.EditProjectDescriptor copyDescriptor =
                new EditProjectCommand.EditProjectDescriptor(new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_ALPHA)
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build());
        EditProjectCommand commandWithSameValues = new EditProjectCommand(INDEX_FIRST_PROJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_SECOND_PROJECT,
                new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_ALPHA)
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build())));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_BRAVO)
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build())));
    }

}
