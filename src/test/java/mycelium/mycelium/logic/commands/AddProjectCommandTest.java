package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandFailure;
import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class AddProjectCommandTest {
    private static final Function<String, CommandResult> buildCommandResult = (msg)
        -> new CommandResult(msg, new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT));

    /**
     * We don't use a stub for the model, and directly use the ModelManager
     * class. This is because the ModelManager already has unit and integration
     * tests.
     */
    private ModelManager model = new ModelManager();

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddProjectCommand(null));
    }

    @Test
    public void execute_projectAcceptedByModel_addSuccessful() throws Exception {
        var project = new ProjectBuilder().build();
        var cmd = new AddProjectCommand(project);
        var expRes = buildCommandResult.apply(String.format(AddProjectCommand.MESSAGE_SUCCESS, project));
        var expModel = new ModelManager();
        expModel.addProject(project);

        assertCommandSuccess(cmd, model, expRes, expModel);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() throws Exception {
        var project = new ProjectBuilder().build();
        var cmd = new AddProjectCommand(project);
        model.addProject(project);
        assertTrue(model.hasProject(project));

        assertCommandFailure(cmd, model, AddProjectCommand.MESSAGE_DUPLICATE_PROJECT);
    }

    @Test
    public void equals() {
        Project bing = new ProjectBuilder().withName("Bing").build();
        Project bard = new ProjectBuilder().withName("Bard").build();

        AddProjectCommand addBing = new AddProjectCommand(bing);
        AddProjectCommand addBard = new AddProjectCommand(bard);

        Map<String, Pair<AddProjectCommand, AddProjectCommand>> equalTests = Map.of(
            "same object",
            Pair.of(addBing, addBing),
            "same fields, different object",
            Pair.of(addBing, new AddProjectCommand(bing)),
            "same name, different fields",
            Pair.of(addBing,
                new AddProjectCommand(new ProjectBuilder().withName("Bing")
                    .withClientEmail("hogriders@coc.org")
                    .build()))
        );

        equalTests.forEach((desc, tt) -> {
            assertEquals(tt.first, tt.second, "While testing case: " + desc);
        });

        Map<String, Pair<AddProjectCommand, Object>> unequalTests = Map.of(
            "different name", Pair.of(addBing, addBard),
            "null", Pair.of(addBing, null),
            "different type", Pair.of(addBing, 1)
        );

        unequalTests.forEach((desc, tt) -> {
            assertNotEquals(tt.first, tt.second, "While testing case: " + desc);
        });
    }
}
