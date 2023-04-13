package mycelium.mycelium.storage;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.commons.util.JsonUtil;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.testutil.ProjectBuilder;

public class JsonAdaptedProjectTest {
    @Test
    public void basicSerialization() throws JsonProcessingException, IOException {
        // Here, we just serialize a project and assert that deserializing it produces the same project
        Project project = new ProjectBuilder().build(); // create a default project
        JsonAdaptedProject se = new JsonAdaptedProject(project);
        String jsonStr = JsonUtil.toJsonString(se);
        JsonAdaptedProject de = JsonUtil.fromJsonString(jsonStr, JsonAdaptedProject.class);
        assertEquals(se, de);
    }

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        Project project = new ProjectBuilder().build();
        JsonAdaptedProject jsonAdaptedProject = new JsonAdaptedProject(project);
        assertEquals(project, jsonAdaptedProject.toModelType());
    }

    @Test
    public void toModelType_nonNullDeadline_returnsProject() throws Exception {
        // The motivation for this test case is that the default project has a null deadline, but
        // we want to test that everything is OK if the deadline is non-null.
        Project project = new ProjectBuilder().withDeadline(LocalDate.now()).build();
        JsonAdaptedProject jsonAdaptedProject = new JsonAdaptedProject(project);
        assertEquals(project, jsonAdaptedProject.toModelType());
    }

    @Test
    public void toModelType_invalidNullFields_throwsIllegalValueException() {
        // Convenience lambda to build the expected error message
        UnaryOperator<String> withErr = (fieldName)
            -> String.format(JsonAdaptedEntity.MISSING_FIELD_MESSAGE_FORMAT, Project.class.getSimpleName(), fieldName);

        // A bunch of test cases with varying null fields. It is rather verbose, but there is no working
        // around it, since we would like to simulate Jackson's behavior of instantiating this class.
        Map<String, JsonAdaptedProject> cases = Map.of(
            withErr.apply("name"), // name is null
            new JsonAdaptedProject(null,
                ProjectBuilder.DEFAULT_STATUS,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                LocalDate.now()),

            withErr.apply("status"), // status is null
            new JsonAdaptedProject(ProjectBuilder.DEFAULT_NAME,
                null,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                LocalDate.now()),

            withErr.apply("clientEmail"), // clientEmail is null
            new JsonAdaptedProject(ProjectBuilder.DEFAULT_NAME,
                ProjectBuilder.DEFAULT_STATUS,
                null,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                LocalDate.now()),

            withErr.apply("acceptedOn"), // acceptedOn is null
            new JsonAdaptedProject(ProjectBuilder.DEFAULT_NAME,
                ProjectBuilder.DEFAULT_STATUS,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                null,
                LocalDate.now())
        );

        cases.forEach((errMsg, project) -> {
            assertThrows(IllegalValueException.class, errMsg, project::toModelType);
        });
    }

    @Test
    public void toModelType_validNullFields_returnsProject() throws Exception {
        Map<String, JsonAdaptedProject> cases = Map.of(
            "source is null",
            new JsonAdaptedProject(
                ProjectBuilder.DEFAULT_NAME,
                ProjectBuilder.DEFAULT_STATUS,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                null,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                LocalDate.now()),

            "description is null",
            new JsonAdaptedProject(
                ProjectBuilder.DEFAULT_NAME,
                ProjectBuilder.DEFAULT_STATUS,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                ProjectBuilder.DEFAULT_SOURCE,
                null,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                LocalDate.now()),

            "deadline is null",
            new JsonAdaptedProject(
                ProjectBuilder.DEFAULT_NAME,
                ProjectBuilder.DEFAULT_STATUS,
                ProjectBuilder.DEFAULT_CLIENT_EMAIL,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null)
        );

        Project defaultProject = new ProjectBuilder().build();
        for (var tt : cases.entrySet()) {
            var desc = tt.getKey();
            var proj = tt.getValue();
            assertTrue(proj.toModelType().isSame(defaultProject), "While testing case: " + desc);
        }
    }
}
