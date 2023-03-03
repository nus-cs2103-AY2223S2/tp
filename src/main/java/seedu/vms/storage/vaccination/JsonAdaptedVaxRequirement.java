package seedu.vms.storage.vaccination;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.Requirement;


/** JSON friendly version of {@link Requirement} */
public class JsonAdaptedVaxRequirement {
    private final Requirement.RequirementType reqType;
    private final List<String> reqSet;


    /** Constructs a {@code JsonAdaptedVaxRequirement}. */
    @JsonCreator
    public JsonAdaptedVaxRequirement(
                @JsonProperty("reqType") Requirement.RequirementType reqType,
                @JsonProperty("reqSet") List<String> reqSet) {
        this.reqType = reqType;
        this.reqSet = reqSet;
    }


    /**
     * Converts this JSON friendly version to an {@link Requirement}
     * instance.
     *
     * @throws IllegalValueException if reqType or grpSet field is of an illegal value.
     */
    public Requirement toModelType() throws IllegalValueException {
        if (reqType == null) {
            throw new IllegalValueException("Missing requirement type");
        }
        if (reqSet == null || reqSet.isEmpty()) {
            throw new IllegalValueException("Missing or empty group set");
        }
        return new Requirement(reqType, new HashSet<>(reqSet));
    }
}
