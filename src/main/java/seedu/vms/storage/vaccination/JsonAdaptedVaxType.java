package seedu.vms.storage.vaccination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeBuilder;
import seedu.vms.model.vaccination.VaxTypeManager;


/** A JSON friendly version of {@link VaxType}. */
public class JsonAdaptedVaxType {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Vaccination type [%s] is missing";

    private final GroupName name;
    private final List<String> groups;
    private final Integer minAge;
    private final Integer maxAge;
    private final Integer minSpacing;
    private final List<JsonAdaptedVaxRequirement> allergyReqs;
    private final List<JsonAdaptedVaxRequirement> historyReqs;


    /** Constructs a {@code JsonAdaptedVaxType}. */
    @JsonCreator
    public JsonAdaptedVaxType(
                @JsonProperty("name") GroupName name,
                @JsonProperty("groups") List<String> groups,
                @JsonProperty("minAge") Integer minAge,
                @JsonProperty("maxAge") Integer maxAge,
                @JsonProperty("minSpacing") Integer minSpacing,
                @JsonProperty("allergyReqs") List<JsonAdaptedVaxRequirement> allergyReqs,
                @JsonProperty("historyReqs") List<JsonAdaptedVaxRequirement> historyReqs) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.allergyReqs = allergyReqs;
        this.historyReqs = historyReqs;
    }


    /**
     * Converts the specified {@code VaxType} to a
     * {@code JsonAdaptedVaxType}.
     */
    public static JsonAdaptedVaxType fromModelType(VaxType vaxType) {
        GroupName name = new GroupName(vaxType.getName());
        List<String> groups = List.copyOf(vaxType.getGroups());
        Integer minAge = vaxType.getMinAge();
        Integer maxAge = vaxType.getMaxAge();
        Integer minSpacing = vaxType.getMinSpacing();
        List<JsonAdaptedVaxRequirement> allergyReqs = convertToAdaptedReq(vaxType.getAllergyReqs());
        List<JsonAdaptedVaxRequirement> historyReqs = convertToAdaptedReq(vaxType.getHistoryReqs());

        return new JsonAdaptedVaxType(name, groups, minAge, maxAge, minSpacing, allergyReqs, historyReqs);
    }


    private static List<JsonAdaptedVaxRequirement> convertToAdaptedReq(List<Requirement> reqs) {
        return reqs.stream()
                .map(req -> JsonAdaptedVaxRequirement.fromModelType(req))
                .collect(Collectors.toList());
    }


    /**
     * Converts this JSON friendly version to an {@link VaxType} instance. The
     * type is added in to the vaccination type map in the process.
     *
     * @throws IllegalValueException if name field is missing.
     */
    public VaxType toModelType(VaxTypeManager manager) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "NAME"));
        }
        VaxTypeBuilder builder = VaxTypeBuilder.of(name);

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

        if (allergyReqs != null) {
            builder = builder.setAllergyReqs(toReqList(allergyReqs));
        }

        if (historyReqs != null) {
            builder = builder.setHistoryReqs(toReqList(historyReqs));
        }

        return builder.create(manager);
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
