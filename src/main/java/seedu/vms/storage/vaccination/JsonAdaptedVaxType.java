package seedu.vms.storage.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.VaxType;


public class JsonAdaptedVaxType {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Vaccination type [%s] is missing";

    private final String name;
    private final List<String> groups;
    private final Integer minAge;
    private final Integer maxAge;
    private final Integer minSpacing;
    private final List<JsonAdaptedVaxRequirement> requirements;


    @JsonCreator
    public JsonAdaptedVaxType(
                @JsonProperty("name") String name,
                @JsonProperty("groups") List<String> groups,
                @JsonProperty("minAge") Integer minAge,
                @JsonProperty("maxAge") Integer maxAge,
                @JsonProperty("minSpacing") Integer minSpacing,
                @JsonProperty("requirements") List<JsonAdaptedVaxRequirement> requirements) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.requirements = requirements;
    }


    public VaxType toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "NAME"));
        }
        VaxType.Builder builder = VaxType.Builder.of(name);

        if (groups != null) {
            builder = builder.setGroups(new HashSet<>(groups));
        }

        if (minAge != null) {
            builder = builder.setMinAge(minAge);
        }

        if (maxAge != null) {
            builder = builder.setMaxAge(maxAge);
        }

        if (minSpacing != null) {
            builder = builder.setMinSpacing(minSpacing);
        }

        if (requirements != null) {
            builder = builder.setRequirements(requirements
                    .stream()
                    .map(adapted -> adapted.toModelType())
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}
