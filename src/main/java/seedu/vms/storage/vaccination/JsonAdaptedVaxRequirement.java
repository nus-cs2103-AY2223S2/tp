package seedu.vms.storage.vaccination;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.model.vaccination.VaxRequirement;

public class JsonAdaptedVaxRequirement {
    private final boolean isExclusion;
    private final List<String> grpSet;


    @JsonCreator
    public JsonAdaptedVaxRequirement(
                @JsonProperty("isExclusion") boolean isExclusion,
                @JsonProperty("grpSet") List<String> grpSet) {
        this.isExclusion = isExclusion;
        this.grpSet = grpSet;
    }


    public VaxRequirement toModelType() {
        return new VaxRequirement(isExclusion, new HashSet<>(grpSet));
    }
}
