package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents the user of the Address Book.
 */
public class User extends Person {

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Phone phone, Email email, Address address,
                   TelegramHandle telegramHandle, ContactIndex contactIndex,
                Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        super(name, phone, email, address, telegramHandle, contactIndex,
                groupTags, moduleTags);
    }
}
