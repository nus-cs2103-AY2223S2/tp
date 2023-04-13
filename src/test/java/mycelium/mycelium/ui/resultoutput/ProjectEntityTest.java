package mycelium.mycelium.ui.resultoutput;


import static mycelium.mycelium.ui.testutil.GuiTestAssert.assertCardDisplaysProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ProjectListCardHandle;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.ui.GuiUnitTest;
import mycelium.mycelium.ui.entitypanel.ProjectEntity;

public class ProjectEntityTest extends GuiUnitTest {

    @Test
    public void display() {
        Project project = new ProjectBuilder().build();
        ProjectEntity projectListCard = new ProjectEntity(project, 1);
        uiPartExtension.setUiPart(projectListCard);
        assertCardDisplay(projectListCard, project, 1);
    }

    @Test
    public void equals() {
        Project project = new ProjectBuilder().build();
        ProjectEntity projectCard = new ProjectEntity(project, 0);

        // same project, same index -> returns true
        ProjectEntity copy = new ProjectEntity(project, 0);
        assertTrue(projectCard.equals(copy));

        // same object -> returns true
        assertTrue(projectCard.equals(projectCard));

        // null -> returns false
        assertFalse(projectCard.equals(null));

        // different types -> returns false
        assertFalse(projectCard.equals(0));

        // different project, same index -> returns false
        Project differentProject = new ProjectBuilder().withName("differentName").build();
        assertFalse(projectCard.equals(new ProjectEntity(differentProject, 0)));

        // same person, different index -> returns false
        assertFalse(projectCard.equals(new ProjectEntity(project, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedPerson} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ProjectEntity projectListCard, Project expectedProject, int expectedId) {
        guiRobot.pauseForHuman();

        ProjectListCardHandle projectListCardHandle = new ProjectListCardHandle(projectListCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", projectListCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysProject(expectedProject, projectListCardHandle);
    }
}
