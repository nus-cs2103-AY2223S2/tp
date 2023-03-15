package mycelium.mycelium.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class AddProjectCommandTest {
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
        Project project = new ProjectBuilder().build();
        CommandResult res = new AddProjectCommand(project).execute(model);

        assertEquals(String.format(AddProjectCommand.MESSAGE_SUCCESS, project), res.getFeedbackToUser());
        assertTrue(model.hasProject(project));
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() throws Exception {
        Project project = new ProjectBuilder().build();
        AddProjectCommand cmd = new AddProjectCommand(project);

        // Execute this command once. Then we execute it again.
        cmd.execute(model);
        assertTrue(model.hasProject(project));

        Assert.assertThrows(CommandException.class, () -> cmd.execute(model));
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
