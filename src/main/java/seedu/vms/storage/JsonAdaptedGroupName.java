package seedu.vms.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;


/**
 * A JSON friendly version of {@link GroupName}.
 */
public class JsonAdaptedGroupName {
    private final String name;


    /**
     * Constructs a {@code JsonAdaptedGroupName}.
     */
    @JsonCreator
    public JsonAdaptedGroupName(String name) {
        this.name = name;
    }


    /**
     * Converts the model type {@code GroupName} to a
     * {@code JsonAdaptedGroupName}.
     */
    public static JsonAdaptedGroupName fromModelType(GroupName grpName) {
        return new JsonAdaptedGroupName(grpName.getName());
    }


    /**
     * Converts a collection of {@code GroupName} to a list of
     * {@code JsonAdaptedGroupName}.
     */
    public static List<JsonAdaptedGroupName> fromModelCollection(Collection<GroupName> grpNames) {
        return grpNames.stream()
                .map(JsonAdaptedGroupName::fromModelType)
                .collect(Collectors.toList());
    }


    @JsonValue
    public String getName() {
        return name;
    }


    /**
     * Returns the {@code GroupName} that this {@code JsonAdaptedGroupName}
     * represents.
     *
     * @throws IllegalValueException if the name is not value.
     */
    public GroupName toModelType() throws IllegalValueException {
        if (!GroupName.isValidName(name)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(name);
    }


    /**
     * Converts a collection of {@code JsonAdaptedGroupName} to the
     * {@code GroupName} that the represent.
     *
     * @throws IllegalValueException if any {@code JsonAdaptedGroupName} is not
     *      able to be converted.
     */
    public static HashSet<GroupName> toModelSet(Collection<JsonAdaptedGroupName> reqSet)
                throws IllegalValueException {
        HashSet<GroupName> modelReqSet = new HashSet<>();
        for (JsonAdaptedGroupName req : reqSet) {
            modelReqSet.add(req.toModelType());
        }
        return modelReqSet;
    }
}
