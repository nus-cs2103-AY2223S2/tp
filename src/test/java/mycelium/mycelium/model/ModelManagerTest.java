package mycelium.mycelium.model;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalPersons.ALICE;
import static mycelium.mycelium.testutil.TypicalPersons.BENSON;
import static mycelium.mycelium.testutil.TypicalPersons.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.GuiSettings;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.exceptions.DuplicateClientException;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.NameContainsKeywordsPredicate;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.exceptions.DuplicateProjectException;
import mycelium.mycelium.testutil.AddressBookBuilder;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.ProjectBuilder;

public class ModelManagerTest {

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
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
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
        Project project = new ProjectBuilder().build();
        modelManager.addProject(project);

        Map<String, Project> cases = Map.ofEntries(
            Map.entry("same reference", project),
            Map.entry("same fields", new ProjectBuilder().withName(project.getName()).build()),
            Map.entry("same name diff email", new ProjectBuilder().withClientEmail("chungus@chungus.org").build())
        );

        cases.forEach((desc, tt) -> {
            assertTrue(modelManager.hasProject(project), "While testing case: " + desc);
        });
    }

    @Test
    public void getUniqueProject_addressBookIsEmpty_returnsEmpty() {
        assertTrue(modelManager.getUniqueProject(p -> p.getName().equals("Bing")).isEmpty());
    }

    @Test
    public void getUniqueProject_projectNotInAddressBook_returnsEmpty() {
        modelManager.addProject(new ProjectBuilder().withName("Bard").build());
        assertTrue(modelManager.getUniqueProject(p -> p.getName().equals("Bing")).isEmpty());
    }

    @Test
    public void getUniqueProject_projectInAddressBook_returnsProject() {
        Project project = new ProjectBuilder().withName("Bing").build();
        modelManager.addProject(project);
        assertEquals(modelManager.getUniqueProject(p -> p.equals(project)).get(), project);
    }

    @Test
    public void deleteProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteProject(null));
    }

    @Test
    public void deleteProject_projectNotInAddressBook_nothingHappens() {
        Project project = new ProjectBuilder().build();
        assertFalse(modelManager.hasProject(project));
        modelManager.deleteProject(project);
        assertFalse(modelManager.hasProject(project));
    }

    @Test
    public void deleteProject_projectInAddressBook_success() {
        Project project = new ProjectBuilder().build();
        modelManager.addProject(project);
        modelManager.deleteProject(project);
        assertFalse(modelManager.hasProject(project));
    }

    @Test
    public void addProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addProject(null));
    }

    @Test
    public void addProject_projectInAddressBook_throwsDuplicateProjectException() {
        Project project = new ProjectBuilder().build();
        modelManager.addProject(project);
        assertThrows(DuplicateProjectException.class, () -> modelManager.addProject(project));
    }

    @Test
    public void addProject_projectWithSameIdentityFieldsInAddressBook_throwsDuplicateProjectException() {
        Project project = new ProjectBuilder().build();
        modelManager.addProject(project);
        Project editedProject = new ProjectBuilder(project).withClientEmail("chungus@chungus.org").build();
        assertThrows(DuplicateProjectException.class, () -> modelManager.addProject(editedProject));
    }

    @Test
    public void addProject_projectNotInAddressBook_success() {
        Project project = new ProjectBuilder().build();
        modelManager.addProject(project);
        assertTrue(modelManager.hasProject(project));
    }

    @Test
    public void getFilteredProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProjectList().remove(0));
    }

    @Test
    public void equals() {
        // TODO account for Project and Client too
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
