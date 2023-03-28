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

import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_V1_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_V1_1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2108_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_V1;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_V1;

/**
 * A utility class containing a list of {@code User} singleton object to be used in tests.
 */
public class TypicalUser {
    public static final User LINUS = new User(new Name("Linus Richards"),
            new Phone("90102030"),
            new Email("linusrichards@gmail.com"),
            new Address("NUS"),
            new TelegramHandle("@linusrichards"),
            new ContactIndex(0),
            new HashSet<>() {{
                add(new GroupTag("User"));
            }},
            new HashSet<>() {{
                add(CS2109S_V1);
                add(CS2101_V1_1);
                add(CS2103T_V1_1);
                add(CS2108_V1);
                add(MA3252_V1);
                add(MA2104_V1);
            }});

    /**
     * Gets a typical user as a Person object.
     * @return A typical user.
     */
    public static User getTypicalUser() {
        return LINUS;
    }
}
