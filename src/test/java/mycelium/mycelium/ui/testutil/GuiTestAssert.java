package mycelium.mycelium.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import guitests.guihandles.ClientListCardHandle;
import guitests.guihandles.ProjectListCardHandle;
import guitests.guihandles.SpecialProjectListCardHandle;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedProject}.
     */
    public static void assertCardDisplaysSpecialProject(Project expectedProject, SpecialProjectListCardHandle actualCard) {
        assertEquals(expectedProject.getName().toString(), actualCard.getName());
        assertEquals(expectedProject.getStatus().toString(), actualCard.getStatus());
        assertEquals(expectedProject.getClientEmail().value, actualCard.getClientEmail());
        assertEquals(
            expectedProject
                .getDeadline()
                .map(d -> d.format(Project.DATE_FMT))
                .orElse("No Deadline"),
            actualCard.getDeadline());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedSpecialProject}.
     */
    public static void assertCardDisplaysProject(Project expectedProject, ProjectListCardHandle actualCard) {
        assertEquals(expectedProject.getName().toString(), actualCard.getName());
        assertEquals(expectedProject.getStatus().toString(), actualCard.getStatus());
        assertEquals(expectedProject.getClientEmail().value, actualCard.getClientEmail());
        assertEquals(expectedProject.getSource().map(NonEmptyString::getValue).orElse("Unknown"),
                actualCard.getSource());
        assertEquals(expectedProject.getAcceptedOn().format(Project.DATE_FMT), actualCard.getAcceptedOn());
        assertEquals(
                expectedProject
                        .getDeadline()
                        .map(d -> d.format(Project.DATE_FMT))
                        .orElse("No Deadline"),
                actualCard.getDeadline());
        assertEquals(expectedProject.getDescription().orElse("No description given"), actualCard.getDescription());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedClient}.
     */
    public static void assertCardDisplaysClient(Client expectedProject, ClientListCardHandle actualCard) {
        assertEquals(expectedProject.getName().toString(), actualCard.getName());
        assertEquals(expectedProject.getEmail().value, actualCard.getEmail());
        assertEquals(expectedProject.getSource().map(NonEmptyString::getValue).orElse("No source"),
            actualCard.getSource());
        assertEquals(expectedProject.getYearOfBirth().map(YearOfBirth::toString).orElse("No year of birth"),
            actualCard.getYearOfBirth());
        assertEquals(expectedProject.getMobileNumber().map(Phone::toString).orElse("No mobile number"),
            actualCard.getMobileNumber());
    }
}
