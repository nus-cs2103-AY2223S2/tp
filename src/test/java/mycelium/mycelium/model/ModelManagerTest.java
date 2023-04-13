package mycelium.mycelium.model;

import static mycelium.mycelium.commons.util.DateUtil.isBeforeToday;
import static mycelium.mycelium.commons.util.DateUtil.isWithinThisAndNextWeek;
import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.BING;
import static mycelium.mycelium.testutil.TypicalEntities.BOSE;
import static mycelium.mycelium.testutil.TypicalEntities.FUTA;
import static mycelium.mycelium.testutil.TypicalEntities.RANTARO;
import static mycelium.mycelium.testutil.TypicalEntities.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.GuiSettings;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.exceptions.DuplicateClientException;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.project.exceptions.DuplicateProjectException;
import mycelium.mycelium.testutil.AddressBookBuilder;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.testutil.ProjectUtil;


public class ModelManagerTest {
    private static final int NUM_OF_VALID_STATUSES = ProjectStatus.values().length;

    private ModelManager modelManager = new ModelManager();


    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        Assertions.assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        Assertions.assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClient(WEST));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        modelManager.addClient(WEST);
        assertTrue(modelManager.hasClient(WEST));
    }

    @Test
    public void getUniqueClient_addressBookIsEmpty_returnsEmpty() {
        assertTrue(modelManager.getUniqueClient(c -> c.getName().equals(new Name("Jamal"))).isEmpty());
    }

    @Test
    public void getUniqueClient_clientNotInAddressBook_returnsEmpty() {
        modelManager.addClient(WEST);
        assertTrue(modelManager.getUniqueClient(c -> c.getName().equals(new Name("Jamal"))).isEmpty());
    }

    @Test
    public void getUniqueClient_clientInAddressBook_returnsClient() {
        modelManager.addClient(WEST);
        assertEquals(modelManager.getUniqueClient(c -> c.equals(WEST)).get(), WEST);
    }

    @Test
    public void deleteClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteClient(null));
    }

    @Test
    public void deleteClient_clientNotInAddressBook_nothingHappens() {
        Client client = new ClientBuilder().build();
        assertFalse(modelManager.hasClient(client));
        modelManager.deleteClient(client);
        assertFalse(modelManager.hasClient(client));
    }

    @Test
    public void deleteClient_clientInAddressBook_success() {
        Client client = new ClientBuilder().build();
        modelManager.addClient(client);
        modelManager.deleteClient(client);
        assertFalse(modelManager.hasClient(client));
    }

    @Test
    public void addClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addClient(null));
    }

    @Test
    public void addClient_clientInAddressBook_throwsDuplicateClientException() {
        Client client = new ClientBuilder().build();
        modelManager.addClient(client);
        assertThrows(DuplicateClientException.class, () -> modelManager.addClient(client));
    }

    @Test
    public void addClient_clientWithSameIdentityFieldsInAddressBook_throwsDuplicateClientException() {
        Client client = new ClientBuilder().build();
        modelManager.addClient(client);
        Client editedClient = new ClientBuilder(client).withEmail("John@gmail.com").build();
        assertThrows(DuplicateClientException.class, () -> modelManager.addClient(editedClient));
    }

    @Test
    public void addClient_clientNotInAddressBook_success() {
        Client client = new ClientBuilder().build();
        modelManager.addClient(client);
        assertTrue(modelManager.hasClient(client));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasProject(new ProjectBuilder().build()));
    }

    @Test
    public void hasProject_projectInAddressBook_returnsTrue() {
        modelManager.addProject(BARD);

        Map<String, Project> cases = Map.of(
            "same reference", BARD,
            "same fields", new ProjectBuilder(BARD).build(),
            "same name diff email", new ProjectBuilder(BARD).withClientEmail("jamal@supercell.com").build()
        );

        cases.forEach((desc, tt) -> {
            assertTrue(modelManager.hasProject(tt), "While testing case: " + desc);
        });
    }

    @Test
    public void getUniqueProject_addressBookIsEmpty_returnsEmpty() {
        assertTrue(modelManager.getUniqueProject(p -> p.getName().equals("Bing")).isEmpty());
    }

    @Test
    public void getUniqueProject_projectNotInAddressBook_returnsEmpty() {
        modelManager.addProject(BARD);
        assertTrue(modelManager.getUniqueProject(p -> p.getName().equals("Bing")).isEmpty());
    }

    @Test
    public void getUniqueProject_projectInAddressBook_returnsProject() {
        modelManager.addProject(BARD);
        assertEquals(modelManager.getUniqueProject(p -> p.equals(BARD)).get(), BARD);
    }

    @Test
    public void deleteProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteProject(null));
    }

    @Test
    public void deleteProject_projectNotInAddressBook_nothingHappens() {
        assertFalse(modelManager.hasProject(BARD));
        modelManager.deleteProject(BARD);
        assertFalse(modelManager.hasProject(BARD));
    }

    @Test
    public void deleteProject_projectInAddressBook_success() {
        modelManager.addProject(BARD);
        assertTrue(modelManager.hasProject(BARD));
        modelManager.deleteProject(BARD);
        assertFalse(modelManager.hasProject(BARD));
    }

    @Test
    public void addProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addProject(null));
    }

    @Test
    public void addProject_projectInAddressBook_throwsDuplicateProjectException() {
        modelManager.addProject(BARD);
        assertThrows(DuplicateProjectException.class, () -> modelManager.addProject(BARD));
    }

    @Test
    public void addProject_projectWithSameIdentityFieldsInAddressBook_throwsDuplicateProjectException() {
        modelManager.addProject(BARD);
        Project alsoBard = new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build();
        assertThrows(DuplicateProjectException.class, () -> modelManager.addProject(alsoBard));
    }

    @Test
    public void addProject_projectNotInAddressBook_success() {
        modelManager.addProject(BARD);
        assertTrue(modelManager.hasProject(BARD));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProjectList().remove(0));
    }

    @Test
    public void getDueProjectList_alwaysContainsProjectsWithDeadline_success() {
        assertEquals(0, modelManager.getDueProjectList().stream().filter(p -> p.getDeadline().isEmpty()).count());
    }

    @Test
    public void getDueProjectList_alwaysContainsUnfinishedProjects_success() {
        assertEquals(0, modelManager.getDueProjectList().stream().filter(
            p -> p.getStatus() == ProjectStatus.DONE).count());
    }

    @Test
    public void getDueProjectList_alwaysContainsProjectsWithDeadlinesWithinTwoWeeks_success() {
        assertEquals(0, modelManager.getDueProjectList().stream().filter(
            p -> !isWithinThisAndNextWeek(p.getDeadline().get())).count());
    }

    @Test
    public void getDueProjectList_neverContainsOverdueProjects_success() {
        assertEquals(0, modelManager.getDueProjectList().stream().filter(
            p -> isBeforeToday(p.getDeadline().get())).count());
    }

    @Test
    public void getOverdueProjectList_neverContainsDueProjects_success() {
        assertEquals(0, modelManager.getOverdueProjectList().stream().filter(
            p -> !isBeforeToday(p.getDeadline().get()) || p.getStatus() == ProjectStatus.DONE).count());
    }

    @Test
    public void getDueProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getDueProjectList().remove(0));
    }

    @Test
    public void getOverdueProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getOverdueProjectList().remove(0));
    }

    @Test
    public void getProjectStatistics_containsThreeKeys_success() {
        assertEquals(NUM_OF_VALID_STATUSES, modelManager.getProjectStatistics().size());
    }

    @Test
    public void getProjectStatistics_containsKeysWithPositiveValues_success() {
        assertTrue(ProjectUtil.containsPositiveValues(modelManager));
    }


    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder()
            .withClient(WEST)
            .withClient(RANTARO)
            .withClient(FUTA)
            .withProject(BARD)
            .withProject(BING)
            .withProject(BOSE)
            .build();

        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different addressBook -> returns false
        assertNotEquals(modelManager, new ModelManager(differentAddressBook, userPrefs));
        assertNotEquals(modelManager.hashCode(), new ModelManager(differentAddressBook, userPrefs).hashCode());

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(addressBook, differentUserPrefs));
    }
}
