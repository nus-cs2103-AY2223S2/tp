package mycelium.mycelium.ui.project;


import static mycelium.mycelium.ui.testUtil.GuiTestAssert.assertCardDisplaysProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import guitests.guihandles.ProjectListCardHandle;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.ui.GuiUnitTest;
import org.junit.jupiter.api.Test;


public class ProjectListCardTest extends GuiUnitTest {

    @Test
    public void display() {
        Project project = new ProjectBuilder().build();
        ProjectListCard projectListCard = new ProjectListCard(project, 1);
        uiPartExtension.setUiPart(projectListCard);
        assertCardDisplay(projectListCard, project, 1);
    }

    @Test
    public void equals() {
        Project project = new ProjectBuilder().build();
        ProjectListCard projectCard = new ProjectListCard(project, 0);

        // same person, same index -> returns true
        ProjectListCard copy = new ProjectListCard(project, 0);
        assertTrue(projectCard.equals(copy));

        // same object -> returns true
        assertTrue(projectCard.equals(projectCard));

        // null -> returns false
        assertFalse(projectCard.equals(null));

        // different types -> returns false
        assertFalse(projectCard.equals(0));

        // different person, same index -> returns false
        Project differentPerson = new ProjectBuilder().withName("differentName").build();
        assertFalse(projectCard.equals(new ProjectListCard(differentPerson, 0)));

        // same person, different index -> returns false
        assertFalse(projectCard.equals(new ProjectListCard(project, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedPerson} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ProjectListCard projectListCard, Project expectedProject, int expectedId) {
        guiRobot.pauseForHuman();

        ProjectListCardHandle projectListCardHandle = new ProjectListCardHandle(projectListCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", projectListCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysProject(expectedProject, projectListCardHandle);
    }
}