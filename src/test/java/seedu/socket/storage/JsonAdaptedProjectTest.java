package seedu.socket.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.socket.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalProjects.ALPHA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.exceptions.IllegalValueException;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;

class JsonAdaptedProjectTest {
    private static final String INVALID_PROJECT_NAME = "R@chel";
    private static final String INVALID_PROJECT_REPO_HOST = "-rachel";
    private static final String INVALID_PROJECT_REPO_NAME = "..";
    private static final String INVALID_PROJECT_DEADLINE = " ";

    private static final String VALID_PROJECT_NAME = ALPHA.getName().toString();
    private static final String VALID_PROJECT_REPO_HOST = ALPHA.getRepoHost().toString();
    private static final String VALID_PROJECT_REPO_NAME = ALPHA.getRepoName().toString();
    private static final String VALID_PROJECT_DEADLINE = ALPHA.getDeadline().toString();

    private static final List<JsonAdaptedPerson> VALID_PERSONS = ALPHA.getMembers().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(ALPHA);
        assertEquals(ALPHA, project.toModelType());
    }

    @Test
    public void toModelType_invalidProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_PROJECT_NAME, VALID_PROJECT_REPO_HOST, VALID_PROJECT_REPO_NAME,
                VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(null, VALID_PROJECT_REPO_HOST, VALID_PROJECT_REPO_NAME,
                VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidProjectRepoHost_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, INVALID_PROJECT_REPO_HOST, VALID_PROJECT_REPO_NAME,
                        VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = ProjectRepoHost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectRepoHost_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, null, VALID_PROJECT_REPO_NAME,
                        VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectRepoHost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidProjectRepoName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_PROJECT_REPO_HOST, INVALID_PROJECT_REPO_NAME,
                        VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = ProjectRepoName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectRepoName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_PROJECT_REPO_HOST, null,
                        VALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectRepoName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidProjectDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_PROJECT_REPO_HOST, VALID_PROJECT_REPO_NAME,
                        INVALID_PROJECT_DEADLINE, VALID_PERSONS);
        String expectedMessage = ProjectDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_PROJECT_REPO_HOST, VALID_PROJECT_REPO_NAME,
                        null, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

}