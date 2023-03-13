package mycelium.mycelium.ui.testUtil;

import guitests.guihandles.ProjectListCardHandle;
import mycelium.mycelium.model.project.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(ProjectListCardHandle expectedCard, ProjectListCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getStatus(), actualCard.getStatus());
        assertEquals(expectedCard.getClientEmail(), actualCard.getClientEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getSource(), actualCard.getSource());
        assertEquals(expectedCard.getAcceptedOn(), actualCard.getAcceptedOn());
        assertEquals(expectedCard.getDeadline(), actualCard.getDeadline());
        assertEquals(expectedCard.getDescription(), actualCard.getDescription());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysProject(Project expectedProject, ProjectListCardHandle actualCard) {
        assertEquals(expectedProject.getName(), actualCard.getName());
        assertEquals(expectedProject.getStatus().toString(), actualCard.getStatus());
        assertEquals(expectedProject.getClientEmail().value, actualCard.getClientEmail());
        assertEquals(expectedProject.getSource().orElse("Unknown"), actualCard.getSource());
        assertEquals(expectedProject.getAcceptedOn().toString(), actualCard.getAcceptedOn());
        if (expectedProject.getDeadline().isEmpty()) {
            assertEquals("No Deadline", actualCard.getDeadline());
        } else {
            assertEquals(expectedProject.getDeadline().toString(), actualCard.getDeadline());
        }
        assertEquals(expectedProject.getDescription().orElse("No description given"), actualCard.getDescription());
    }
}
