package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents the user of the Address Book.
 */
public class User extends Person {

    //Data Fields
    private TelegramHandle telegramHandle;
    private Set<ModuleTag> moduleTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Phone phone, Email email, Address address,
                TelegramHandle telegramHandle, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        super(name, phone, email, address, groupTags);
        requireAllNonNull(telegramHandle, moduleTags);
        this.telegramHandle = telegramHandle;
        this.moduleTags.addAll(moduleTags);
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
        String extraInformation = "User{"
                + "telegramHandle= " + telegramHandle
                + ", moduleTags= " + moduleTags + '}';
        return String.format("%s\n%s", basicInformation, extraInformation);
    }
}
