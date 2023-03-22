package arb.storage;

import static arb.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalProjects.PORTRAIT_PROJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import arb.commons.exceptions.IllegalValueException;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.testutil.ProjectBuilder;

class JsonAdaptedProjectTest {

    private static final String INVALID_TITLE = "S@y";
    private static final String INVALID_DEADLINE = "abed";
    private static final String VALID_TITLE = PORTRAIT_PROJECT.getTitle().toString();
    private static final String VALID_DEADLINE = PORTRAIT_PROJECT.getDeadline().toString();
    // Feb 1 2025 14:00 PM
    private static final String VALID_STATUS = PORTRAIT_PROJECT.getStatus().toString();
    private static final String VALID_PRICE = PORTRAIT_PROJECT.getPrice().toString();
    // Price: $2
    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(PORTRAIT_PROJECT);
        assertEquals(PORTRAIT_PROJECT, project.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_TITLE, VALID_DEADLINE, VALID_STATUS, VALID_PRICE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_DEADLINE, VALID_STATUS, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_TITLE, INVALID_DEADLINE, VALID_STATUS, VALID_PRICE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, null, VALID_STATUS, "2");
        Project expectedProject = new ProjectBuilder(PORTRAIT_PROJECT).withDeadline(null).build();
        System.out.println(VALID_DEADLINE);
        System.out.println(VALID_PRICE);
        System.out.println(expectedProject);
        System.out.println(project.toModelType());
        assertEquals(expectedProject, project.toModelType());
    }
}
