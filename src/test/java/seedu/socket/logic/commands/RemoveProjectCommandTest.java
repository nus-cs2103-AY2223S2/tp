package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalProjects.getTypicalSocket;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.RemoveProjectCommand.RemoveProjectDescriptor;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.Socket;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.project.Project;
import seedu.socket.testutil.ProjectBuilder;
import seedu.socket.testutil.RemoveProjectDescriptorBuilder;
import seedu.socket.testutil.TypicalProjects;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemoveProjectCommand.
 */
public class RemoveProjectCommandTest {

    private Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
    private Model modelWithProjects = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());
        ProjectBuilder projectInList = new ProjectBuilder()
                .withName("Charlie Project")
                .withRepoHost("")
                .withRepoName("")
                .withProjectDeadline("")
                .withProjectMeeting("");
        Project removedFieldProject = projectInList.withMembers(lastProject.getMembers()).build();

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(lastProject)
                .withRepoHost("benson-meier")
                .withRepoName("CharlieRepo")
                .withDeadline("03/01/23-2359")
                .withMeeting("03/01/23-1000").build();

        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(RemoveProjectCommand.MESSAGE_REMOVE_FIELD_SUCCESS, removedFieldProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, removedFieldProject);

        assertCommandSuccess(removeProjectCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test when user only inputs index
     */
    @Test
    public void execute_allFieldsNotSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());
        ProjectBuilder projectInList = new ProjectBuilder()
                .withName("Charlie Project")
                .withRepoHost("")
                .withRepoName("")
                .withProjectDeadline("")
                .withProjectMeeting("");

        Project removedFieldProject = projectInList.withMembers(lastProject.getMembers()).build();

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(lastProject)
                .withRepoHost("")
                .withRepoName("")
                .withDeadline("")
                .withMeeting("").build();

        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(RemoveProjectCommand.MESSAGE_REMOVE_FIELD_SUCCESS, removedFieldProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, removedFieldProject);

        assertCommandSuccess(removeProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder();
        Project removedFieldProject = projectInList
                .withName("Charlie Project")
                .withRepoHost("")
                .withRepoName("")
                .withProjectDeadline("03/01/23-2359")
                .withProjectMeeting("03/01/23-1000").withMembers(lastProject.getMembers()).build();

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(lastProject)
                .withRepoHost("benson-meier")
                .withRepoName("CharlieRepo").build();
        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(RemoveProjectCommand.MESSAGE_REMOVE_FIELD_SUCCESS, removedFieldProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, removedFieldProject);

        assertCommandSuccess(removeProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsNotSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder();
        Project removedFieldProject = projectInList
                .withName("Charlie Project")
                .withRepoHost("")
                .withRepoName("")
                .withProjectDeadline("03/01/23-2359")
                .withProjectMeeting("03/01/23-1000").withMembers(lastProject.getMembers()).build();

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(lastProject)
                .withRepoHost("")
                .withRepoName("").build();
        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(RemoveProjectCommand.MESSAGE_REMOVE_FIELD_SUCCESS, removedFieldProject);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, removedFieldProject);

        assertCommandSuccess(removeProjectCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests when field descriptions in the project don't match field descriptions given by user.
     */
    @Test
    public void execute_allDifferentFieldNotMatchUnfilteredList_failure() {
        Index indexSecondProject = Index.fromOneBased(model.getFilteredProjectList().size() - 1);
        Project lastProject = model.getFilteredProjectList().get(indexSecondProject.getZeroBased());
        ProjectBuilder projectInList = new ProjectBuilder()
                .withName("Bravo Project")
                .withRepoHost("")
                .withRepoName("")
                .withProjectDeadline("")
                .withProjectMeeting("");
        Project removedFieldProject = projectInList.withMembers(lastProject.getMembers()).build();

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(lastProject)
                .withRepoHost("benson-meier")
                .withRepoName("CharlieRepo")
                .withDeadline("03/01/23-2359")
                .withMeeting("03/01/23-1000").build();

        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(indexSecondProject, descriptor);

        Model expectedModel = new ModelManager(new Socket(model.getSocket()), new UserPrefs());
        expectedModel.setProject(lastProject, removedFieldProject);

        assertCommandFailure(removeProjectCommand, model, RemoveProjectCommand.MESSAGE_REMOVE_FIELD_NOT_MATCH);
    }

    @Test
    public void execute_specificFieldNotMatchUnfilteredList_failure() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withProject(firstProject)
                .withRepoName("CharlieRepo")
                .withRepoHost("benson-meier").build();
        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(INDEX_FIRST_PROJECT, descriptor);

        assertCommandFailure(removeProjectCommand, model, RemoveCommand.MESSAGE_REMOVE_FIELD_NOT_MATCH);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder().build();
        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(removeProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of project list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSocket().getProjectList().size());

        RemoveProjectCommand removeProjectCommand = new RemoveProjectCommand(outOfBoundIndex,
                new RemoveProjectDescriptorBuilder().build());

        assertCommandFailure(removeProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void testEquals() {
        final RemoveProjectCommand standardCommand = new RemoveProjectCommand(INDEX_FIRST_PERSON,
                new RemoveProjectDescriptorBuilder()
                        .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                        .withMeeting(VALID_PROJECT_MEETING_ALPHA).build());

        // same values -> returns true
        RemoveProjectCommand.RemoveProjectDescriptor copyDescriptor =
                new RemoveProjectCommand.RemoveProjectDescriptor(new RemoveProjectDescriptorBuilder()
                        .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                        .withMeeting(VALID_PROJECT_MEETING_ALPHA).build());
        RemoveProjectCommand commandWithSameValues = new RemoveProjectCommand(INDEX_FIRST_PROJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveProjectCommand(INDEX_SECOND_PROJECT,
                new RemoveProjectDescriptorBuilder()
                        .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                        .withMeeting(VALID_PROJECT_MEETING_ALPHA).build())));

        // different descriptor same field -> returns true
        assertTrue(standardCommand.equals(new RemoveProjectCommand(INDEX_FIRST_PROJECT,
                new RemoveProjectDescriptorBuilder()
                        .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
                        .withMeeting(VALID_PROJECT_MEETING_ALPHA).build())));
    }
}

