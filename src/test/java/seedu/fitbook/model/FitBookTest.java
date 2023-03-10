package seedu.fitbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.client.TypicalClients.ALICE;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.exceptions.DuplicateClientException;
import seedu.fitbook.testutil.client.ClientBuilder;

public class FitBookTest {

    private final FitBook fitBook = new FitBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fitBook.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFitBook_replacesData() {
        FitBook newData = getTypicalFitBook();
        fitBook.resetData(newData);
        assertEquals(newData, fitBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        FitBookStub newData = new FitBookStub(newClients);

        assertThrows(DuplicateClientException.class, () -> fitBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitBook.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInFitBook_returnsFalse() {
        assertFalse(fitBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInFitBook_returnsTrue() {
        fitBook.addClient(ALICE);
        assertTrue(fitBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInFitBook_returnsTrue() {
        fitBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(fitBook.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fitBook.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyFitBook whose clients list can violate interface constraints.
     */
    private static class FitBookStub implements ReadOnlyFitBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        FitBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }
    }

}
