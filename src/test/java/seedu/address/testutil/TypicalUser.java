package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * A utility class containing a list of {@code User} singleton object to be used in tests.
 */
public class TypicalUser {
    public static final User LINUS = new User(new Name("Linus Richards"),
            new Phone("90102030"),
            new Email("linusrichards@gmail.com"),
            new Address("National University of Singapore"),
            new TelegramHandle("@linusrichards"),
            new ContactIndex(0),
            new HashSet<>() {{
                add(new GroupTag("User"));
            }},
            new HashSet<>() {{
                add(new ModuleTag("CS2100"));
                add(new ModuleTag("CS2101"));
                add(new ModuleTag("CS2102"));
                add(new ModuleTag("CS2103"));
                add(new ModuleTag("CS2104"));
                add(new ModuleTag("CS2105"));
            }});

    /**
     * Gets a typical user as a Person object.
     * @return A typical user.
     */
    public static User getTypicalUser() {
        return LINUS;
    }
}
