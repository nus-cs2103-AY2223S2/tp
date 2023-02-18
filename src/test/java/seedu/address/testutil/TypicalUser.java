package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * A utility class containing a list of {@code User} singleton object to be used in tests.
 */
public class TypicalUser {
    public static final UserStub LINUS = UserStub.getSingletonUser(new Name("Linus Richards"),
            new Phone("90102030"),
            new Email("linusrichards@gmail.com"),
            new Address("National University of Singapore"),
            new TelegramHandle("@linusrichards"),
            new HashSet<>() {{
                add(new GroupTag("User"));
            }},
            new HashSet<>() {{
                add(new ModuleTag("CS2101"));
                add(new ModuleTag("CS2102"));
                add(new ModuleTag("CS2103T"));
                add(new ModuleTag("CS2105"));
                add(new ModuleTag("CS2106"));
            }});

    /**
     * Gets a typical user as a Person object.
     * @return A typical user.
     */
    public static UserStub getTypicalUser() {
        return LINUS;
    }
}
