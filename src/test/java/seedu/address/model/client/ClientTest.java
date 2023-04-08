package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.model.client.policy.UniquePolicyList;
import seedu.address.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(ALICE.isSameClient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameClient(null));

        // same name, all other attributes different -> returns true
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameClient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Client editedBob = new ClientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameClient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB;
        Client trailingBob = new ClientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameClient(trailingBob));
    }

    @Test
    void getFilteredPolicyList() {
        Client client = new ClientBuilder().build();
        assertEquals(FXCollections.observableArrayList(), client.getFilteredPolicyList());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different client -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE, editedAlice);

    }

    @Test
    void getPolicyList() {
        Client client = new ClientBuilder().build();
        assertEquals(new UniquePolicyList(), client.getPolicyList());
    }

    @Test
    void testHashCode() {
        Client client = new ClientBuilder(ALICE).build();
        assertEquals(client.hashCode(), ALICE.hashCode());
    }

    @Test
    void testClone() {
        Client clientBeforeClone = new ClientBuilder(BOB).build();
        Client clientAfterClone = clientBeforeClone.cloneClient();
        assertTrue(clientBeforeClone.isSameClient(clientAfterClone));
        assertEquals(clientBeforeClone, clientAfterClone);
        assertNotSame(clientBeforeClone, clientAfterClone);
    }

}
