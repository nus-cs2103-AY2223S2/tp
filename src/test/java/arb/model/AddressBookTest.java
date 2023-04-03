package arb.model;

import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalClients.ALICE;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.model.client.exceptions.DuplicateClientException;
import arb.model.project.Project;
import arb.model.project.exceptions.DuplicateProjectException;
import arb.model.tag.TagMapping;
import arb.testutil.ClientBuilder;
import arb.testutil.ProjectBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getClientList());
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
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);

        AddressBookStub newData = new AddressBookStub(newClients, Collections.emptyList(), Collections.emptyList());

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .build();
        List<Project> newProjects = Arrays.asList(SKY_PAINTING, editedSkyPainting);

        AddressBookStub newData = new AddressBookStub(Collections.emptyList(), newProjects, Collections.emptyList());

        assertThrows(DuplicateProjectException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient((Client) null));
    }

    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProject(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasProject_projectNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProject(SKY_PAINTING));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        assertTrue(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasProject_projectInAddressBook_returnsTrue() {
        addressBook.addProject(SKY_PAINTING);
        assertTrue(addressBook.hasProject(SKY_PAINTING));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasClient(editedAlice));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProject(SKY_PAINTING);
        Project editedSkyPainting = new ProjectBuilder(SKY_PAINTING).withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .build();
        assertTrue(addressBook.hasProject(editedSkyPainting));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProjectList().remove(0));
    }

    @Test
    public void getTagMappingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTagMappingList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients list and projects list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<TagMapping> tagMappings = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients, Collection<Project> projects, Collection<TagMapping> tagMappings) {
            this.clients.setAll(clients);
            this.projects.setAll(projects);
            this.tagMappings.setAll(tagMappings);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<TagMapping> getTagMappingList() {
            return tagMappings;
        }
    }

}
