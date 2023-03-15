package mycelium.mycelium.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class DeleteProjectCommandTest {
    // TODO add more tests in the manner of DeleteCommandTest.java! The tests we
    // have now are very basic, and I have written them in a rush.

    private ModelManager model = new ModelManager();

    @Test
    public void execute_validName_success() throws Exception {
        Project project = new ProjectBuilder().build();
        model.addProject(project);

        assertTrue(model.hasProject(project));

        DeleteProjectCommand cmd = new DeleteProjectCommand(project.getName());
        CommandResult res = cmd.execute(model);

        assertFalse(model.hasProject(project));
        assertEquals(String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, project),
            res.getFeedbackToUser());
    }

    @Test
    public void execute_nonExistentName_throwsCommandException() {
        DeleteProjectCommand cmd = new DeleteProjectCommand("Team Fortress 3");

        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteBing = new DeleteProjectCommand("Bing");
        DeleteProjectCommand deleteBard = new DeleteProjectCommand("Bard");

        Map<String, Pair<DeleteProjectCommand, DeleteProjectCommand>> equalTests = Map.of(
            "same object", Pair.of(deleteBing, deleteBing),
            "same name, different object", Pair.of(deleteBing, new DeleteProjectCommand("Bing"))
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
