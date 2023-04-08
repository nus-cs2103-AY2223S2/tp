package seedu.techtrack.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.techtrack.commons.exceptions.IllegalValueException;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.role.Role;

/**
 * An Immutable RoleBook that is serializable to JSON format.
 */
@JsonRootName(value = "RoleBook")
class JsonSerializableRoleBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Roles list contains duplicate role(s).";

    private final List<JsonAdaptedRole> roles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoleBook} with the given roles.
     */
    @JsonCreator
    public JsonSerializableRoleBook(@JsonProperty("roles") List<JsonAdaptedRole> roles) {
        this.roles.addAll(roles);
    }

    /**
     * Converts a given {@code ReadOnlyRoleBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoleBook}.
     */
    public JsonSerializableRoleBook(ReadOnlyRoleBook source) {
        roles.addAll(source.getRoleList().stream().map(JsonAdaptedRole::new).collect(Collectors.toList()));
    }

    /**
     * Converts this role book into the model's {@code RoleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RoleBook toModelType() throws IllegalValueException {
        RoleBook roleBook = new RoleBook();
        for (JsonAdaptedRole jsonAdaptedRole : roles) {
            Role role = jsonAdaptedRole.toModelType();
            if (roleBook.hasRole(role)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            roleBook.addRole(role);
        }
        return roleBook;
    }

}
