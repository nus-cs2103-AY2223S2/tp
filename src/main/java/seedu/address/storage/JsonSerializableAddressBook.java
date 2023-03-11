package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Role;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Roles list contains duplicate role(s).";

    private final List<JsonAdaptedRole> roles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given roles.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("roles") List<JsonAdaptedRole> roles) {
        this.roles.addAll(roles);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        roles.addAll(source.getRoleList().stream().map(JsonAdaptedRole::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedRole jsonAdaptedRole : roles) {
            Role role = jsonAdaptedRole.toModelType();
            if (addressBook.hasRole(role)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addRole(role);
        }
        return addressBook;
    }

}
