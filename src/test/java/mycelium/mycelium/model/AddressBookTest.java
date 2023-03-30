package mycelium.mycelium.model;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.WEST;
import static mycelium.mycelium.testutil.TypicalEntities.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.testutil.AddressBookBuilder;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.ProjectBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        // Here, we're just checking that all the default lists are empty. This is because they are meant to be
        // populated after initialization by external sources, e.g. JSON files.
        assertEquals(Collections.emptyList(), addressBook.getClientList());
        assertEquals(Collections.emptyList(), addressBook.getProjectList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        List<Client> newClients = List.of(
            new ClientBuilder().build(),
            new ClientBuilder().build()
        );
        AddressBookStub newData = new AddressBookStub(newClients, List.of());

        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> newProjects = List.of(
            new ProjectBuilder().build(),
            new ProjectBuilder().build()
        );
        AddressBookStub newData = new AddressBookStub(List.of(), newProjects);
        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasClient(new ClientBuilder().build()));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        Client john = new ClientBuilder().build();
        addressBook.addClient(john);
        assertTrue(addressBook.hasClient(john));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(WEST);
        Client alsoWest = new ClientBuilder(WEST).withName("Kim Kardashian").build();
        assertTrue(addressBook.hasClient(alsoWest));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProject(BARD));
    }

    @Test
    public void hasProject_projectInAddressBook_returnsTrue() {
        addressBook.addProject(BARD);
        assertTrue(addressBook.hasProject(BARD));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProject(BARD);
        Project alsoBard = new ProjectBuilder(BARD).withClientEmail("chungus@chungus.org").build();
        assertTrue(addressBook.hasProject(alsoBard));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProjectList().remove(0));
    }

    @Test
    public void equalsAndHashCode() {
        AddressBook abA = new AddressBookBuilder().withClient(WEST).withProject(BARD).build();
        AddressBook abB = new AddressBookBuilder().withClient(WEST).withProject(BARD).build();
        // address books have same contents -> returns true
        // Note: assertEquals calls the object's equals() method
        assertEquals(abA, abB);
        assertEquals(abA.hashCode(), abB.hashCode());
        // different address books -> returns false
        assertNotEquals(abA, addressBook);
        assertNotEquals(abA.hashCode(), addressBook.hashCode());
        // null -> returns false
        assertNotEquals(abA, null);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients, Collection<Project> projects) {
            this.clients.setAll(clients);
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }
    }

}
