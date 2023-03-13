package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;

class SampleDataUtilTest {

    @Test
    void getSampleClients() {
        Client[] sampleClients = SampleDataUtil.getSampleClients();
        assertEquals(6, sampleClients.length);

        // check if each sample client has valid fields
        for (Client client : sampleClients) {
            assertNotNull(client.getName());
            assertNotNull(client.getPhone());
            assertNotNull(client.getEmail());
            assertNotNull(client.getAddress());
            assertFalse(client.getTags().isEmpty());
        }
    }

    @Test
    void getSampleAddressBook() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        ObservableList<Client> sampleClients;
        sampleClients = FXCollections.observableList(Arrays.asList(SampleDataUtil.getSampleClients()));

        // check if each sample client is present in the sample address book
        for (Client sampleClient : sampleClients) {
            assertTrue(sampleAddressBook.getClientList().contains(sampleClient));
        }
    }
}
