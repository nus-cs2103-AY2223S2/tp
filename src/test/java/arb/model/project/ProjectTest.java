package arb.model.project;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_OIL_PAINTING;
import static arb.testutil.TypicalProjects.OIL_PAINTING;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void equals() {

        Project defaultProject = new ProjectBuilder().build();
        Project defaultCopy = new ProjectBuilder().build();

        Project sky = new ProjectBuilder().withTitle("Sky").withDeadline("5pm 2023-03-05").build();

        assertFalse(defaultProject.equals(null)); // null
        assertFalse(defaultProject.equals(3)); // different type
        assertFalse(defaultProject.equals(sky)); // different project

        Project editedSky;
        editedSky = new ProjectBuilder(sky).withTitle("Night sky").build();
        assertFalse(sky.equals(editedSky)); // changed title
        editedSky = new ProjectBuilder(sky).withDeadline("5pm 2023-03-01").build();
        assertFalse(sky.equals(editedSky)); // changed deadline
        editedSky = new ProjectBuilder(sky).build();
        editedSky.markAsDone();
        assertFalse(sky.equals(editedSky)); // changed status

        assertTrue(defaultProject.equals(defaultProject)); // Same instance
        assertTrue(defaultProject.equals(defaultCopy)); // Same details

        Project defaultProjectWihoutDeadline = new ProjectBuilder().withDeadline(null).build();
        Project defaultCopyWithoutDeadline = new ProjectBuilder().withDeadline(null).build();

        Project skyWithoutDeadline = new ProjectBuilder().withTitle("Sky").withDeadline(null).build();

        assertFalse(defaultProjectWihoutDeadline.equals(null)); // null
        assertFalse(defaultProjectWihoutDeadline.equals(3)); // different type
        assertFalse(defaultProjectWihoutDeadline.equals(skyWithoutDeadline)); //different project

        assertFalse(defaultProjectWihoutDeadline.equals(defaultProject)); // different deadlines

        Project editedSkyWithDeadline = new ProjectBuilder(skyWithoutDeadline).withDeadline("5pm 2023-03-01").build();
        editedSky = new ProjectBuilder(sky).withDeadline("5pm 2023-03-01").build();

        assertTrue(editedSkyWithDeadline.equals(editedSky));
        assertTrue(defaultProjectWihoutDeadline.equals(defaultCopyWithoutDeadline));
        assertTrue(defaultCopyWithoutDeadline.equals(defaultProjectWihoutDeadline));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(SKY_PAINTING.isSameProject(SKY_PAINTING));

        // null -> returns false
        assertFalse(SKY_PAINTING.isSameProject(null));

        // same name, all other attributes different -> returns true
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        editedSkyPainting.markAsDone();
        assertTrue(SKY_PAINTING.isSameProject(editedSkyPainting));

        // different name, all other attributes same -> returns false
        editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withTitle(VALID_TITLE_OIL_PAINTING).build();
        assertFalse(SKY_PAINTING.isSameProject(editedSkyPainting));

        // name differs in case, all other attributes same -> returns false
        Project editedOilPainting = new ProjectBuilder(OIL_PAINTING)
                .withTitle(VALID_TITLE_OIL_PAINTING.toLowerCase()).build();
        assertFalse(OIL_PAINTING.isSameProject(editedOilPainting));

        // name has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_OIL_PAINTING + " ";
        editedOilPainting = new ProjectBuilder(OIL_PAINTING).withTitle(titleWithTrailingSpaces).build();
        assertFalse(OIL_PAINTING.isSameProject(editedOilPainting));
    }
}
