package seedu.vms.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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


    public static List<JsonAdaptedGroupName> fromModelCollection(Collection<GroupName> grpNames) {
        return grpNames.stream()
                .map(JsonAdaptedGroupName::fromModelType)
                .collect(Collectors.toList());
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


    public static HashSet<GroupName> toModelSet(Collection<JsonAdaptedGroupName> reqSet)
                throws IllegalValueException {
        HashSet<GroupName> modelReqSet = new HashSet<>();
        for (JsonAdaptedGroupName req : reqSet) {
            modelReqSet.add(req.toModelType());
        }
        return modelReqSet;
    }
}
