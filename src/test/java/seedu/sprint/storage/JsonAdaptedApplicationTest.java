package seedu.sprint.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sprint.storage.JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.sprint.testutil.Assert.assertThrows;
import static seedu.sprint.testutil.TypicalApplications.ANT_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.exceptions.IllegalValueException;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;

public class JsonAdaptedApplicationTest {
    private static final String INVALID_ROLE = "SWE Intern :)";
    private static final String INVALID_COMPANY_NAME = "Am@zon";
    private static final String INVALID_COMPANY_EMAIL = "example.com";
    private static final String INVALID_STATUS = "-";
    private static final String INVALID_TASK_DESCRIPTION = "#technical interview";
    private static final String INVALID_TASK_DEADLINE = "0000-12-12";
    private static final String INVALID_TAG = "#MUST GET";
    private static final String VALID_ROLE = ANT_GROUP.getRole().toString();
    private static final String VALID_COMPANY_NAME = ANT_GROUP.getCompanyName().toString();
    private static final String VALID_COMPANY_EMAIL = ANT_GROUP.getCompanyEmail().toString();
    private static final String VALID_STATUS = ANT_GROUP.getStatus().toString();
    private static final String VALID_TASK_DESCRIPTION = ANT_GROUP.getTask().getDescription().toString();

    private static final String VALID_TASK_DEADLINE = ANT_GROUP.getTask().getDeadline().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = ANT_GROUP.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicationDetails_returnsApplication() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(ANT_GROUP);
        assertEquals(ANT_GROUP, application.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(INVALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(null, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidCompanyEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, INVALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = CompanyEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompanyEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, null,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        INVALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        null, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, INVALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, null, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, INVALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, null, VALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, INVALID_TASK_DEADLINE, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY_NAME, VALID_COMPANY_EMAIL,
                        VALID_STATUS, VALID_ROLE, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, invalidTags);
        assertThrows(IllegalValueException.class, application::toModelType);
    }
}
