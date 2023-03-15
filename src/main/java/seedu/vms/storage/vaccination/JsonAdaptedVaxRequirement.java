package seedu.vms.storage.vaccination;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.storage.JsonAdaptedGroupName;


/** JSON friendly version of {@link Requirement} */
public class JsonAdaptedVaxRequirement {
    private final Requirement.RequirementType reqType;
    private final List<JsonAdaptedGroupName> reqSet;


    /** Constructs a {@code JsonAdaptedVaxRequirement}. */
    @JsonCreator
    public JsonAdaptedVaxRequirement(
                @JsonProperty("reqType") Requirement.RequirementType reqType,
                @JsonProperty("reqSet") List<JsonAdaptedGroupName> reqSet) {
        this.reqType = reqType;
        this.reqSet = reqSet;
    }


    /**
     * Converts the specified {@code Requirement} to a
     * {@code JsonAdaptedVaxRequirement}.
     */
    public static JsonAdaptedVaxRequirement fromModelType(Requirement req) {
        Requirement.RequirementType reqType = req.getReqType();
        List<JsonAdaptedGroupName> reqSet = JsonAdaptedGroupName.fromModelCollection(req.getReqSet());
        return new JsonAdaptedVaxRequirement(reqType, reqSet);
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
        return new Requirement(reqType, JsonAdaptedGroupName.toModelSet(reqSet));
    }
}
