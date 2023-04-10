package seedu.vms.storage.vaccination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeBuilder;
import seedu.vms.storage.JsonAdaptedAge;
import seedu.vms.storage.JsonAdaptedGroupName;


/** A JSON friendly version of {@link VaxType}. */
public class JsonAdaptedVaxType {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Vaccination type [%s] is missing";

    private final JsonAdaptedGroupName name;
    private final List<JsonAdaptedGroupName> groups;
    private final JsonAdaptedAge minAge;
    private final JsonAdaptedAge maxAge;
    private final List<JsonAdaptedGroupName> ingredients;
    private final List<JsonAdaptedVaxRequirement> historyReqs;


    /** Constructs a {@code JsonAdaptedVaxType}. */
    @JsonCreator
    public JsonAdaptedVaxType(
                @JsonProperty("name") JsonAdaptedGroupName name,
                @JsonProperty("groups") List<JsonAdaptedGroupName> groups,
                @JsonProperty("minAge") JsonAdaptedAge minAge,
                @JsonProperty("maxAge") JsonAdaptedAge maxAge,
                @JsonProperty("ingredients") List<JsonAdaptedGroupName> ingredients,
                @JsonProperty("historyReqs") List<JsonAdaptedVaxRequirement> historyReqs) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.ingredients = ingredients;
        this.historyReqs = historyReqs;
    }


    /**
     * Converts the specified {@code VaxType} to a
     * {@code JsonAdaptedVaxType}.
     */
    public static JsonAdaptedVaxType fromModelType(VaxType vaxType) {
        JsonAdaptedGroupName name = JsonAdaptedGroupName.fromModelType(vaxType.getGroupName());
        List<JsonAdaptedGroupName> groups = JsonAdaptedGroupName.fromModelCollection(vaxType.getGroups());
        JsonAdaptedAge minAge = JsonAdaptedAge.fromModelType(vaxType.getMinAge());
        JsonAdaptedAge maxAge = JsonAdaptedAge.fromModelType(vaxType.getMaxAge());
        List<JsonAdaptedGroupName> allergyReqs = JsonAdaptedGroupName.fromModelCollection(vaxType.getIngredients());
        List<JsonAdaptedVaxRequirement> historyReqs = convertToAdaptedReq(vaxType.getHistoryReqs());

        return new JsonAdaptedVaxType(name, groups, minAge, maxAge, allergyReqs, historyReqs);
    }


    private static List<JsonAdaptedVaxRequirement> convertToAdaptedReq(List<Requirement> reqs) {
        return reqs.stream()
                .map(req -> JsonAdaptedVaxRequirement.fromModelType(req))
                .collect(Collectors.toList());
    }


    /**
     * Converts this JSON friendly version to an {@link VaxType} instance.
     *
     * @throws IllegalValueException if name field is missing.
     */
    public VaxType toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "NAME"));
        }
        VaxTypeBuilder builder = VaxTypeBuilder.of();

        if (groups != null) {
            builder = builder.setGroups(JsonAdaptedGroupName.toModelSet(groups));
        }

        if (minAge != null) {
            builder = builder.setMinAge(minAge.toModelType());
        }

        if (maxAge != null) {
            builder = builder.setMaxAge(maxAge.toModelType());
        }

        if (ingredients != null) {
            builder = builder.setIngredients(JsonAdaptedGroupName.toModelSet(ingredients));
        }

        if (historyReqs != null) {
            builder = builder.setHistoryReqs(toReqList(historyReqs));
        }

        return builder.create(name.toModelType());
    }


    private List<Requirement> toReqList(List<JsonAdaptedVaxRequirement> adaptedList)
                throws IllegalValueException {
        ArrayList<Requirement> reqs = new ArrayList<>();
        for (JsonAdaptedVaxRequirement adapted : adaptedList) {
            reqs.add(adapted.toModelType());
        }
        return reqs;
    }
}
