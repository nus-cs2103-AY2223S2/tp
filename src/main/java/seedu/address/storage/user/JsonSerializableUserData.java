package seedu.address.storage.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.user.UserData;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "userdata")
public class JsonSerializableUserData {

    private final JsonAdaptedUser user;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUserData(@JsonProperty("user") JsonAdaptedUser user) {
        this.user = user;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableUserData(ReadOnlyUserData source) {
        this.user = new JsonAdaptedUser(source.getData().getValue());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UserData toModelType() throws IllegalValueException {
        UserData userData = new UserData();
        userData.setUser(this.user.toModelType());
        return userData;
    }

}
