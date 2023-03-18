package seedu.address.testutil;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.StorageStub;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * A utility class to help with building ModelManager objects.
 * Example usage: <br>
 *     {@code ModelManager mm = new ModelManagerBuilder().withFriendlyLink(friendlyLink).build();}
 */
public class ModelManagerBuilder {

    private FriendlyLink friendlyLink;
    private UserPrefs userPrefs;

    /**
     *
     */
    public ModelManagerBuilder() {
        friendlyLink = new FriendlyLink();
        userPrefs = new UserPrefs();
    }

    /**
     * Constructs a ModelManagerBuilder using an existing ModelManager.
     *
     * @param modelManagerToCopy ModelManager to copy.
     */
    public ModelManagerBuilder(ModelManager modelManagerToCopy) {
        this.friendlyLink = new FriendlyLink(modelManagerToCopy.getFriendlyLink());
        this.userPrefs = new UserPrefs(modelManagerToCopy.getUserPrefs());
    }

    /**
     * Sets the {@code FriendlyLink} of the {@code ModelManager} that we are building.
     */
    public ModelManagerBuilder withFriendlyLink(FriendlyLink friendlyLinkToCopy) {
        this.friendlyLink = new FriendlyLink(friendlyLinkToCopy);
        return this;
    }

    /**
     * Sets the {@code UserPrefs} of the {@code ModelManager} that we are building.
     */
    public ModelManagerBuilder withUserPrefs(ReadOnlyUserPrefs userPrefsToCopy) {
        this.userPrefs = new UserPrefs(userPrefsToCopy);
        return this;
    }

    /**
     * Builds the {@code ModelManager}.
     */
    public ModelManager build() {
        StorageStubWithFriendlyLinkAndUserPrefs storageStub =
                new StorageStubWithFriendlyLinkAndUserPrefs(friendlyLink, userPrefs);
        return new ModelManager(storageStub, userPrefs);
    }

    private static class StorageStubWithFriendlyLinkAndUserPrefs extends StorageStub {

        private final FriendlyLink friendlyLink;
        private final UserPrefs userPrefs;
        public StorageStubWithFriendlyLinkAndUserPrefs(FriendlyLink friendlyLink, UserPrefs userPrefs) {
            this.friendlyLink = friendlyLink;
            this.userPrefs = userPrefs;
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
            return Optional.of(userPrefs);
        }

        @Override
        public FriendlyLink read() throws DataConversionException, IOException {
            return friendlyLink;
        }
    }
}
