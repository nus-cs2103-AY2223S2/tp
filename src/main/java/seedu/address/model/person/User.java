package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents the user of the Address Book.
 */
public class User extends Person {

    // Singleton Object
    private static final User USER = null;

    /**
     * Every field must be present and not null.
     */
    protected User(Name name, Phone phone, Email email, Address address,
                   TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        super(name, phone, email, address, telegramHandle, groupTags, moduleTags);
        requireAllNonNull(telegramHandle, moduleTags);
    }

    public static User getSingletonUser(Name name, Phone phone, Email email, Address address,
        TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        if (USER == null) {
            return new User(name, phone, email, address, telegramHandle, groupTags, moduleTags);
        }
        return USER;
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
        String basicInformation = super.toString();
        return String.format("%s\n%s", "USER: ", basicInformation);
    }
}
