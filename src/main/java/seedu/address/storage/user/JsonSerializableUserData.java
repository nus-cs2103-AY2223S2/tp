package seedu.address.storage.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.user.UserData;
import seedu.address.model.user.User;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "userdata")
public class JsonSerializableUserData {

    private final List<JsonAdaptedUser> users = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUserData(@JsonProperty("users") List<JsonAdaptedUser> users) {
        this.users.addAll(users);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableUserData(ReadOnlyUserData source) {
        this.users.addAll(source.getUser().stream().map(JsonAdaptedUser::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UserData toModelType() throws IllegalValueException {
        UserData userData = new UserData();
        for (JsonAdaptedUser jsonAdaptedUser : this.users) {
            User user = jsonAdaptedUser.toModelType();
            ArrayList<User> userList = new ArrayList<>();
            userList.add(user);
            userData.setUserList(userList);
        }
        return userData;
    }

}
