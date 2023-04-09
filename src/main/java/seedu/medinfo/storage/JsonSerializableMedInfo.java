package seedu.medinfo.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.medinfo.commons.exceptions.IllegalValueException;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.exceptions.WardNotFoundException;

/**
 * An Immutable MedInfo that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableMedInfo {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_WARD = "Ward list contains duplicate ward(s).";
    public static final String MESSAGE_MISSING_WARD = "Patient(s) linked to wards that do not exist.";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedWard> wards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMedInfo} with the given patients.
     */
    @JsonCreator
    public JsonSerializableMedInfo(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                   @JsonProperty("wards") List<JsonAdaptedWard> wards) {
        this.patients.addAll(patients);
        this.wards.addAll(wards);
    }

    /**
     * Converts a given {@code ReadOnlyMedInfo} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMedInfo}.
     */
    public JsonSerializableMedInfo(ReadOnlyMedInfo source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        wards.addAll(source.getWardList().stream().map(JsonAdaptedWard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this medinfo book into the model's {@code MedInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MedInfo toModelType() throws IllegalValueException, CommandException {
        MedInfo medInfo = new MedInfo();
        for (JsonAdaptedWard jsonAdaptedWard : wards) {
            Ward ward = jsonAdaptedWard.toModelType();
            if (!medInfo.hasWard(ward)) {
                medInfo.addWard(ward);
            }
        }

        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (medInfo.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            try {
                medInfo.addPatient(patient);
            } catch (WardNotFoundException e) {
                throw new IllegalValueException(MESSAGE_MISSING_WARD);
            }
        }
        return medInfo;
    }

}
