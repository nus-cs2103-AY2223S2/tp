package seedu.address.model.socialmedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SocialMediaTest {
    @Test
    public void constructor_working_example() {
        var instagram = Instagram.of("johndoe");
        var telegram = Telegram.of("johndoetg");
        var socialMedia = SocialMedia.create().withInstagram(instagram).withTelegram(telegram);
        assertNotNull(socialMedia);
        assertEquals(socialMedia.getInstagram(), instagram);
        assertEquals(socialMedia.getTelegram(), telegram);
    }
}
