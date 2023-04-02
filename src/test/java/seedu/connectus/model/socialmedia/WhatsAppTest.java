package seedu.connectus.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.connectus.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;

public class WhatsAppTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> WhatsApp.of(null));
    }

    @Test
    public void constructor_invalidWhatsApp_throwsIllegalArgumentException() {
        String invalidWhatsApp = " ";
        assertThrows(IllegalArgumentException.class, () -> WhatsApp.of(invalidWhatsApp));
    }

    @Test
    public void isValidWhatsApp() {
        // null phone number
        assertThrows(NullPointerException.class, () -> WhatsApp.isValidWhatsApp(null));

        // invalid phone numbers
        assertFalse(WhatsApp.isValidWhatsApp("")); // empty string
        assertFalse(WhatsApp.isValidWhatsApp(" ")); // spaces only
        assertFalse(WhatsApp.isValidWhatsApp("91")); // less than 3 numbers
        assertFalse(WhatsApp.isValidWhatsApp("phone")); // non-numeric
        assertFalse(WhatsApp.isValidWhatsApp("9011p041")); // alphabets within digits
        assertFalse(WhatsApp.isValidWhatsApp("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(WhatsApp.isValidWhatsApp("911")); // exactly 3 numbers
        assertTrue(WhatsApp.isValidWhatsApp("93121534"));
        assertTrue(WhatsApp.isValidWhatsApp("124293842033123")); // long phone numbers
    }

    @Test
    public void getUserLinkSingapore_WhatsApp_returnsExpectedLink() {
        String expectedLink = "whatsapp://send?phone=6591234567";
        WhatsApp whatsapp = new WhatsApp("91234567");

        String actualLink = whatsapp.getUserLink();

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLinkForeign_WhatsApp_returnsExpectedLink() {
        String expectedLink = "whatsapp://send?phone=60123456789";
        WhatsApp whatsapp = new WhatsApp("60123456789");

        String actualLink = whatsapp.getUserLink();

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLinkSingapore_withUser_returnsExpectedLink() {
        String expectedLink = "whatsapp://send?phone=6591234567";
        Person person = new Person(new Name("John Doe"));
        person.setSocialMedia(new SocialMedia(null, null, WhatsApp.of("91234567")));

        String actualLink = WhatsApp.getUserLink(person);

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLinkForeign_withUser_returnsExpectedLink() {
        String expectedLink = "whatsapp://send?phone=60123456789";
        Person person = new Person(new Name("John Doe"));
        person.setSocialMedia(new SocialMedia(null, null, WhatsApp.of("60123456789")));

        String actualLink = WhatsApp.getUserLink(person);

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLink_withNullUser_returnsEmptyString() {
        Person person = new Person(new Name("John Doe"));

        String actualLink = WhatsApp.getUserLink(person);

        assertEquals("", actualLink);
    }

    @Test
    public void getUserLink_withNullSocialMedia_returnsEmptyString() {
        Person person = new Person(new Name("John Doe"));
        person.setSocialMedia(new SocialMedia(null, null, null));

        String actualLink = WhatsApp.getUserLink(person);

        assertEquals("", actualLink);
    }
}
