package seedu.address.model.person;

import java.util.Objects;
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
                   TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        super(name, phone, email, address, telegramHandle, groupTags, moduleTags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.getName(),
                super.getPhone(),
                super.getEmail(),
                super.getAddress(),
                super.getGroupTags(),
                super.getTelegramHandle(),
                super.getModuleTags());
    }

    @Override
    public String toString() {
        return String.format("Username: %s\nEmail: %s\nTelegram: %s\nModules: %s\nPhone Number: %s\n",
                super.getName(),
                super.getEmail(),
                super.getTelegramHandle(),
                super.getModuleTags(),
                super.getPhone());
    }
}
