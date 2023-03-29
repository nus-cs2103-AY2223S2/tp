package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandFailure;
import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class DeleteProjectCommandTest {
    // TODO add more tests in the manner of DeleteCommandTest.java! The tests we
    // have now are very basic, and I have written them in a rush.

    private static final Function<String, CommandResult> buildCommandResult = (msg)
        -> new CommandResult(msg, new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT));

    private ModelManager model = new ModelManager();

    @Test
    public void execute_validName_success() throws Exception {
        var project = new ProjectBuilder().build();
        model.addProject(project);
        assertTrue(model.hasProject(project));

        var cmd = new DeleteProjectCommand(project.getName());
        var expMsg = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, project);
        var expRes = buildCommandResult.apply(expMsg);
        var expModel = new ModelManager(); // empty model

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_nonExistentName_throwsCommandException() {
        DeleteProjectCommand cmd = new DeleteProjectCommand(NonEmptyString.of("Team Fortress 3"));
        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PROJECT);
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteBing = new DeleteProjectCommand(NonEmptyString.of("Bing"));
        DeleteProjectCommand deleteBard = new DeleteProjectCommand(NonEmptyString.of("Bard"));

        Map<String, Pair<DeleteProjectCommand, DeleteProjectCommand>> equalTests = Map.of(
            "same object", Pair.of(deleteBing, deleteBing),
            "same name, different object", Pair.of(deleteBing, new DeleteProjectCommand(NonEmptyString.of("Bing")))
        );
        equalTests.forEach((desc, tt) -> {
            assertEquals(tt.first, tt.second, "While testing case: " + desc);
        });

        Map<String, Pair<DeleteProjectCommand, Object>> unequalTests = Map.of(
            "different type", Pair.of(deleteBing, new Object()),
            "different name", Pair.of(deleteBing, deleteBard),
            "null", Pair.of(deleteBing, null)
        );
        unequalTests.forEach((desc, tt) -> {
            assertFalse(tt.first.equals(tt.second), "While testing case: " + desc);
        });
    }
}
