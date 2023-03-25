package seedu.patientist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.ward.Ward;

/**
 * An Immutable Patientist that is serializable to JSON format.
 */
@JsonRootName(value = "patientist")
class JsonSerializablePatientist {

    public static final String MESSAGE_DUPLICATE_WARD = "Persons list contains duplicate ward(s).";

    private final List<JsonAdaptedWard> wards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientist} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePatientist(@JsonProperty("wards") List<JsonAdaptedWard> wards) {
        this.wards.addAll(wards);
    }

    /**
     * Converts a given {@code ReadOnlyPatientist} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientist}.
     */
    public JsonSerializablePatientist(ReadOnlyPatientist source) {
        wards.addAll(source.getWardList().stream().map(JsonAdaptedWard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this patientist book into the model's {@code Patientist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Patientist toModelType() throws IllegalValueException {
        Patientist patientist = new Patientist();
        for (JsonAdaptedWard jsonAdaptedWard : wards) {
            Ward ward = jsonAdaptedWard.toModelType();
            if (patientist.hasWard(ward)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_WARD);
            }
            patientist.addWard(ward);
        }
        return patientist;
    }

}
