package mycelium.mycelium.model.client;

import static mycelium.mycelium.testutil.TypicalPersons.FUTA;
import static mycelium.mycelium.testutil.TypicalPersons.RANTARO;
import static mycelium.mycelium.testutil.TypicalPersons.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(WEST.isSame(WEST));
        // null -> returns false
        assertFalse(WEST.isSame(null));
        // same name, diff email -> returns false
        assertFalse(FUTA.isSame(RANTARO));
        // TODO -> handle case for different name, same email
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client westCopy = new ClientBuilder(WEST).build();
        assertTrue(WEST.equals(westCopy));
        // same object -> returns true
        assertTrue(WEST.equals(WEST));
        // null -> returns false
        assertFalse(WEST.equals(null));
        // different type -> returns false
        assertFalse(WEST.equals("I love it"));
        // different client -> returns false
        assertFalse(WEST.equals(FUTA));
        // TODO -> handle case for different name, same email
    }

    @Test
    public void fillCompulsoryFieldsOnly() {
        Name name = new Name("Walter Hartwell White");
        Email email = new Email("albqq@gmail.com");
        Client client = new Client(name, email);
        assertEquals(client.getName(), new Name("Walter Hartwell White"));
        assertEquals(client.getEmail(), new Email("albqq@gmail.com"));
        assertEquals(client.getYearOfBirth(), Optional.empty());
        assertEquals(client.getSource(), Optional.empty());
        assertEquals(client.getMobileNumber(), Optional.empty());
    }

    @Test
    public void toString_works() {
        Client client = new ClientBuilder().withName("Jamal").withEmail("jamal@hogriders.org").build();
        assertEquals(client.toString(), "Jamal (jamal@hogriders.org)");
    }
}
