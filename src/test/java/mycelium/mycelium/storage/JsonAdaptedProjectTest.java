package mycelium.mycelium.storage;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Project project = new ProjectBuilder().withDeadline(LocalDate.now()).build();
        JsonAdaptedProject jsonAdaptedProject = new JsonAdaptedProject(project);
        assertEquals(project, jsonAdaptedProject.toModelType());
    }

    @Test
    public void toModelType_nullFields_throwsIllegalValueException() {
        UnaryOperator<String>
            withErr = (fieldName)
                -> String.format(JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT, fieldName);
        Map<String, JsonAdaptedProject> cases = Map.of(
            withErr.apply("name"),
            new JsonAdaptedProject(null,
                ProjectBuilder.DEFAULT_STATUS,
                "chungus@chungus.org",
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null),
            withErr.apply("status"),
            new JsonAdaptedProject("owo",
                null,
                "chungus@chungus.org",
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null),
            withErr.apply("clientEmail"),
            new JsonAdaptedProject("owo",
                ProjectBuilder.DEFAULT_STATUS,
                null,
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null),
            withErr.apply("source"),
            new JsonAdaptedProject("owo",
                ProjectBuilder.DEFAULT_STATUS,
                "chungus@chungus.org",
                null,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null),
            withErr.apply("description"),
            new JsonAdaptedProject("owo",
                ProjectBuilder.DEFAULT_STATUS,
                "chungus@chungus.org",
                ProjectBuilder.DEFAULT_SOURCE,
                null,
                ProjectBuilder.DEFAULT_ACCEPTED_ON,
                null),
            withErr.apply("acceptedOn"),
            new JsonAdaptedProject("owo",
                ProjectBuilder.DEFAULT_STATUS,
                "chungus@chungus.org",
                ProjectBuilder.DEFAULT_SOURCE,
                ProjectBuilder.DEFAULT_DESCRIPTION,
                null,
                null)
        );
        cases.forEach((errMsg, project) -> {
            assertThrows(IllegalValueException.class, errMsg, project::toModelType);
        });
    }
}
