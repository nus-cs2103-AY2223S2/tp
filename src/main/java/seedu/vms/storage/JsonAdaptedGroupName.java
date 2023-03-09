package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;

public class JsonAdaptedGroupName {
    private final String name;


    @JsonCreator
    public JsonAdaptedGroupName(String name) {
        this.name = name;
    }


    public static JsonAdaptedGroupName fromModelType(GroupName grpName) {
        return new JsonAdaptedGroupName(grpName.getName());
    }


    @JsonValue
    public String getName() {
        return name;
    }


    public GroupName toModelType() throws IllegalValueException {
        if (!GroupName.isValidName(name)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINT);
        }
        return new GroupName(name);
    }
}
