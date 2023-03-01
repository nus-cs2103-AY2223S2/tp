package arb.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import arb.testutil.ProjectBuilder;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    public void equals() {

        Project defaultProject = new ProjectBuilder().build();
        Project defaultCopy = new ProjectBuilder().build();

        Project sky = new ProjectBuilder().withTitle("Sky").withDeadline("2023-03-05").build();

        assertFalse(defaultProject.equals(null)); // null
        assertFalse(defaultProject.equals(3)); // different type
        assertFalse(defaultProject.equals(sky)); // different project

        Project editedSky;
        editedSky = new ProjectBuilder(sky).withTitle("Night sky").build();
        assertFalse(sky.equals(editedSky)); // changed title
        editedSky = new ProjectBuilder(sky).withDeadline("2023-03-01").build();
        assertFalse(sky.equals(editedSky)); // changed deadline

        assertTrue(defaultProject.equals(defaultProject)); // Same instance
        assertTrue(defaultProject.equals(defaultCopy)); // Same details

    }
}
