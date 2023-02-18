package seedu.address.testutil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents the user of the Address Book.
 */
public final class UserStub extends User {

    // Singleton Object
    private static UserStub emptyUserStub = null;
    private static UserStub userStub;
    private static boolean isInitialised = false;

    //Data Fields
    private TelegramHandle telegramHandle;
    private Set<ModuleTag> moduleTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    private UserStub(Name name, Phone phone, Email email, Address address,
                 TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        super(name, phone, email, address, telegramHandle, groupTags, moduleTags);
        requireAllNonNull(telegramHandle, moduleTags);
        this.telegramHandle = telegramHandle;
        this.moduleTags.addAll(moduleTags);
    }

    public static UserStub getSingletonUser(Name name, Phone phone, Email email, Address address,
                TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        if (!isInitialised) {
            isInitialised = true;
            userStub = new UserStub(name, phone, email, address, telegramHandle, groupTags, moduleTags);
        }
        return userStub;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Set<ModuleTag> getModuleTags() {
        return Collections.unmodifiableSet(moduleTags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.getName(),
                super.getPhone(),
                super.getEmail(),
                super.getAddress(),
                super.getGroupTags(),
                telegramHandle, moduleTags);
    }

    @Override
    public String toString() {
        String basicInformation = super.toString();
        String extraInformation = "UserStub{"
                + "telegramHandle= " + telegramHandle
                + ", moduleTags= " + moduleTags + '}';
        return String.format("%s\n%s", basicInformation, extraInformation);
    }
}

