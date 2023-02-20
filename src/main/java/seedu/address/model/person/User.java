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
    private static User user = null;
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

        if (user == null) {
            user = new User(name, phone, email, address, telegramHandle, groupTags, moduleTags);
        }

        return user;
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

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }
}