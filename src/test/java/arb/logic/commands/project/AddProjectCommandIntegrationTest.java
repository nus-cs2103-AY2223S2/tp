package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;
import arb.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddProjectCommand}.
 */
public class AddProjectCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void executeSuccess_newProject_withCurrentListTypeClient() {
        Project validProject = new ProjectBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addProject(validProject);

        assertCommandSuccess(new AddProjectCommand(validProject, Optional.empty()), ListType.CLIENT,
                ListType.PROJECT, model, String.format(AddProjectCommand.MESSAGE_SUCCESS, validProject),
                expectedModel);
    }

    @Test
    public void executeSuccess_newProject_withCurrentListTypeProject() {
        Project validProject = new ProjectBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addProject(validProject);

        assertCommandSuccess(new AddProjectCommand(validProject, Optional.empty()), ListType.PROJECT,
                ListType.PROJECT, model, String.format(AddProjectCommand.MESSAGE_SUCCESS, validProject),
                expectedModel);
    }

    @Test
    public void executeSuccess_newProject_withCurrentListTypeTag() {
        Project validProject = new ProjectBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addProject(validProject);

        assertCommandSuccess(new AddProjectCommand(validProject, Optional.empty()), ListType.TAG,
                ListType.PROJECT, model, String.format(AddProjectCommand.MESSAGE_SUCCESS, validProject),
                expectedModel);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project projectInList = model.getAddressBook().getProjectList().get(0);
        assertCommandFailure(new AddProjectCommand(projectInList, Optional.empty()), ListType.CLIENT, model,
                AddProjectCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_linkedClientNotFoundInAddressbook_throwsCommandException() {
        Project project = new ProjectBuilder().build();
        String notFoundInAddressbookName = "not";
        assertCommandFailure(new AddProjectCommand(project, Optional.of(notFoundInAddressbookName)), ListType.PROJECT,
                model, String.format(AddProjectCommand.MESSAGE_CANNOT_FIND_CLIENT, notFoundInAddressbookName));
    }

}
