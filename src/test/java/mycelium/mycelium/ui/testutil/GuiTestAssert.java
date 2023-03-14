package mycelium.mycelium.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import guitests.guihandles.ClientListCardHandle;
import guitests.guihandles.ProjectListCardHandle;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.project.Project;

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
        assertEquals(expectedProject.getDeadline().map(Date::toString).orElse("No Deadline"), actualCard.getDeadline());
        assertEquals(expectedProject.getDescription().orElse("No description given"), actualCard.getDescription());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysClient(Client expectedProject, ClientListCardHandle actualCard) {
        assertEquals(expectedProject.getName().toString(), actualCard.getName());
        assertEquals(expectedProject.getEmail().value, actualCard.getEmail());
        assertEquals(expectedProject.getSource().orElse("No source"), actualCard.getSource());
        assertEquals(expectedProject.getYearOfBirth().map(YearOfBirth::toString).orElse("No year of birth"),
                actualCard.getYearOfBirth());
        assertEquals(expectedProject.getMobileNumber().map(Phone::toString).orElse("No mobile number"),
                actualCard.getMobileNumber());
    }
}
