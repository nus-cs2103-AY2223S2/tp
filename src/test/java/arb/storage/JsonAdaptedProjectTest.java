package arb.storage;

import static arb.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalProjects.PORTRAIT_PROJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.commons.exceptions.IllegalValueException;
import arb.model.AddressBook;
import arb.model.client.Client;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.testutil.ClientBuilder;
import arb.testutil.ProjectBuilder;

class JsonAdaptedProjectTest {

    private static final String INVALID_TITLE = "S@y";
    private static final String INVALID_DEADLINE = "abed";
    private static final String INVALID_TAG = "#painting";
    private static final String INVALID_PRICE = "abc";
    private static final String CLIENT_NAME_NOT_FOUND = "Hoon Meier";

    private static final String VALID_TITLE = PORTRAIT_PROJECT.getTitle().toString();
    private static final String VALID_DEADLINE = PORTRAIT_PROJECT.getDeadline().toString();
    private static final String VALID_PRICE = PORTRAIT_PROJECT.getPrice().getPrice();
    private static final String VALID_STATUS = Boolean.toString(PORTRAIT_PROJECT.getStatus().getStatus());
    private static final String VALID_LINKED_CLIENT = "Alice Pauline";
    private static final List<JsonAdaptedTag> VALID_TAGS = PORTRAIT_PROJECT.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final Client client = new ClientBuilder().withName(VALID_LINKED_CLIENT).build();

    private static AddressBook ab;

    @BeforeEach
    public void resetAddressbook() {
        ab = new AddressBook();
        ab.addClient(client);
    }

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(PORTRAIT_PROJECT);
        assertEquals(PORTRAIT_PROJECT, project.toModelType(ab));
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_TITLE, VALID_DEADLINE, VALID_STATUS, VALID_PRICE,
                VALID_LINKED_CLIENT, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_DEADLINE, VALID_STATUS,
                VALID_PRICE, VALID_LINKED_CLIENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_TITLE, INVALID_DEADLINE, VALID_STATUS, VALID_PRICE,
                VALID_LINKED_CLIENT, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_nullDeadline_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, null, VALID_STATUS, VALID_PRICE,
                VALID_LINKED_CLIENT, VALID_TAGS);
        Project expectedProject = new ProjectBuilder(PORTRAIT_PROJECT).withDeadline(null)
                .withLinkedClient(client).build();
        assertEquals(expectedProject, project.toModelType(ab));
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, null, VALID_PRICE,
                VALID_LINKED_CLIENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS,
                INVALID_PRICE, VALID_LINKED_CLIENT, VALID_TAGS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_nullPrice_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS,
                null, VALID_LINKED_CLIENT, VALID_TAGS);
        Project expectedProject = new ProjectBuilder(PORTRAIT_PROJECT).withPrice(null)
                .withLinkedClient(client).build();
        assertEquals(expectedProject, project.toModelType(ab));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS, VALID_PRICE, null, invalidTags);
        assertThrows(IllegalValueException.class, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_invalidLinkedClientName_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS,
                VALID_PRICE, INVALID_TITLE, VALID_TAGS);
        assertThrows(IllegalValueException.class, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_clientNameNotFound_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS,
                VALID_PRICE, CLIENT_NAME_NOT_FOUND, VALID_TAGS);
        assertThrows(IllegalValueException.class, () -> project.toModelType(ab));
    }

    @Test
    public void toModelType_nullClientName_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_TITLE, VALID_DEADLINE, VALID_STATUS,
                VALID_PRICE, null, VALID_TAGS);
        assertEquals(PORTRAIT_PROJECT, project.toModelType(ab));
    }
}
