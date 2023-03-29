package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandFailure;
import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.BING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.UserPrefs;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.testutil.UpdateProjectDescriptorBuilder;

public class UpdateProjectCommandTest {
    private static final UpdateProjectCommand.UpdateProjectDescriptor EMPTY_DESC =
        new UpdateProjectCommand.UpdateProjectDescriptor();
    // The two sample inputs below change "foobar" to "barfoo"
    private static final NonEmptyString SAMPLE_NAME = new NonEmptyString("foobar");
    private static final NonEmptyString SAMPLE_NEW_NAME = new NonEmptyString("barfoo");

    private static final Function<String, CommandResult> buildCommandResult = (msg)
        -> new CommandResult(msg, new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT));

    private static final UpdateProjectCommand.UpdateProjectDescriptor SAMPLE_DESC =
        new UpdateProjectDescriptorBuilder()
            .withName(SAMPLE_NEW_NAME)
            .build();

    // Use an empty address book here
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());


    @Test
    public void execute_nullModel_throwsNullPointerException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    @Test
    public void execute_projectNotExist_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PROJECT);
    }

    @Test
    public void execute_duplicateName_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        // Add both projects in
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());
        model.addProject(new ProjectBuilder().withName(SAMPLE_NEW_NAME).build());

        assertCommandFailure(cmd, model, UpdateProjectCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void execute_noChanges_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, EMPTY_DESC);
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());

        assertCommandFailure(cmd, model, UpdateProjectCommand.MESSAGE_NOT_UPDATED);
    }

    @Test
    public void execute_validArgs_success() throws CommandException {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());

        var updatedProject = new ProjectBuilder().withName(SAMPLE_NEW_NAME).build();
        var expMsg = String.format(UpdateProjectCommand.MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject);
        var expRes = buildCommandResult.apply(expMsg);
        var expModel = new ModelManager();
        expModel.addProject(updatedProject);

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_changeMultipleFields_success() throws CommandException {
        // Now we'll insert BING, change it to BARD, then assert that we have BARD and no BING
        var cmd = new UpdateProjectCommand(BING.getName(), new UpdateProjectDescriptorBuilder(BARD).build());
        model.addProject(BING);
        cmd.execute(model);

        // TODO: refactor to use `assertCommandSuccess`
        assertFalse(model.hasProject(BING));
        assertTrue(model.hasProject(BARD));
    }

    @Test
    public void execute_keepNameChangeField_success() throws CommandException {
        var newDeadline = "30/12/1970";
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withDeadline(newDeadline).build());
        model.addProject(BING);

        var updatedProject = new ProjectBuilder(BING).withDeadline(newDeadline).build();
        var expMsg = String.format(UpdateProjectCommand.MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject);
        var expRes = buildCommandResult.apply(expMsg);
        var expModel = new ModelManager();
        expModel.addProject(updatedProject);

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_changeNameOnly_success() throws CommandException {
        var newName = "billabong";
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withName(newName).build());
        model.addProject(BING);

        var updatedProject = new ProjectBuilder(BING).withName(newName).build();
        var expMsg = String.format(UpdateProjectCommand.MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject);
        var expRes = buildCommandResult.apply(expMsg);
        var expModel = new ModelManager();
        expModel.addProject(updatedProject);

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_nameRemainsTheSame_throwsCommandException() {
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withName(BING.getName()).build());
        model.addProject(BING);
        assertCommandFailure(cmd, model, UpdateProjectCommand.MESSAGE_NOT_UPDATED);
    }

    @Test
    public void execute_allFieldsRemainTheSame_throwsCommandException() {
        var cmd = new UpdateProjectCommand(BING.getName(), new UpdateProjectDescriptorBuilder(BING).build());
        model.addProject(BING);
        assertCommandFailure(cmd, model, UpdateProjectCommand.MESSAGE_NOT_UPDATED);
    }

    @Test
    public void execute_allFieldsSameExceptName_success() throws CommandException {
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder(BING).withName(BARD.getName()).build());
        model.addProject(BING);
        cmd.execute(model);

        // TODO: refactor to use `assertCommandSuccess`
        assertFalse(model.hasProject(BING)); // BING should not be in the model
        assertTrue(model.hasProject(BARD)); // BARD should be in the model
    }
}
