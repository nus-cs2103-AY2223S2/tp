package seedu.connectus.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;

public class TelegramTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Telegram.of(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Telegram.of("inva...liddooo123"));
    }

    @Test
    public void constructor_invalidUsernameTooShort_example() {
        var username = "invd";
        assertFalse(Telegram.isValid(username));
    }

    @Test
    public void constructor_working_example() {
        var username = "va_lid_username";
        assertTrue(Telegram.isValid(username));
        assertEquals(Telegram.of(username).toString(), username);
    }

    @Test
    public void telegram_getUserLink_returnsExpectedLink() {
        String expectedLink = "tg://resolve?domain=johndoe";
        Telegram telegram = new Telegram("johndoe");

        String actualLink = telegram.getUserLink();

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLink_withUser_returnsExpectedLink() {
        String expectedLink = "tg://resolve?domain=johndoe";
        Person person = new Person(new Name("John Doe"));
        person.setSocialMedia(new SocialMedia(null, Telegram.of("johndoe"), null));

        String actualLink = Telegram.getUserLink(person);

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void getUserLink_withNullUser_returnsEmptyString() {
        Person person = new Person(new Name("John Doe"));

        String actualLink = Telegram.getUserLink(person);

        assertEquals("", actualLink);
    }

    @Test
    public void getUserLink_withNullSocialMedia_returnsEmptyString() {
        Person person = new Person(new Name("John Doe"));
        person.setSocialMedia(new SocialMedia(null, null, null));

        String actualLink = Telegram.getUserLink(person);

        assertEquals("", actualLink);
    }
}
