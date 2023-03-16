package seedu.address.storage.parents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.storage.person.JsonAdaptedParent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable Parents that is serializable to JSON format.
 */
@JsonRootName(value = "parents")
public class JsonSerializableParents {
    public static final String MESSAGE_DUPLICATE_PARENT = "Parents list contains duplicate student(s).";

    private final List<JsonAdaptedParent> parents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableParents} with the given parents.
     */
    @JsonCreator
    public JsonSerializableParents(@JsonProperty("parents") List<JsonAdaptedParent> parents) {
        this.parents.addAll(parents);
    }

    /**
     * Converts a given {@code ReadOnlyParents} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableParents}.
     */
    public JsonSerializableParents(ReadOnlyParents source) {
        parents.addAll(source.getParentList().stream().map(JsonAdaptedParent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tea pet into the model's {@code Parents} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Parents toModelType() throws IllegalValueException {
        Parents parents = new Parents();
        for (JsonAdaptedParent jsonAdaptedParent : this.parents) {
            Parent parent = jsonAdaptedParent.toModelType();
            if (parents.hasParent(parent)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARENT);
            }
            parents.addParent(parent);
        }
        return parents;
    }
}