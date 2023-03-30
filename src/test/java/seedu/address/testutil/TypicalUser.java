package seedu.address.testutil;

import static seedu.address.model.tag.util.TypicalModuleTag.CFG1002_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2103T_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2108_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2109S_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA2104_HA;
import static seedu.address.model.tag.util.TypicalModuleTag.MA3252_HA;

import java.util.HashSet;

import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;

/**
 * A utility class containing a list of {@code User} singleton object to be used in tests.
 */
public class TypicalUser {
    public static final User LINUS = new User(new Name("Linus Richards"),
            new Phone("90102030"),
            new Email("linusrichards@gmail.com"),
            new Station("NUS"),
            new TelegramHandle("@linusrichards"),
            new ContactIndex(0),
            new HashSet<>() {{
                add(new GroupTag("User"));
            }},
            new HashSet<>() {{
                add(CS2109S_HA);
                add(CS2101_HA);
                add(CS2103T_HA);
                add(CS2108_HA);
                add(MA3252_HA);
                add(MA2104_HA);
                add(CFG1002_F);
            }});

    /**
     * Gets a typical user as a Person object.
     * @return A typical user.
     */
    public static User getTypicalUser() {
        return LINUS;
    }
}
