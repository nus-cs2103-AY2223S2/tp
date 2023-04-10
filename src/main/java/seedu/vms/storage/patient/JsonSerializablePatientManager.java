package seedu.vms.storage.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.model.IdData;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;

/**
 * An Immutable PatientManager that is serializable to JSON format.
 */
@JsonRootName(value = "patientmanager")
public class JsonSerializablePatientManager {

    public static final String DUPLICATE_ID = "Patients list contains duplicate ID(s).";

    private final List<JsonAdaptedPatientData> datas = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientManager} with the given patients.
     */
    @JsonCreator
    public JsonSerializablePatientManager(@JsonProperty("datas") List<JsonAdaptedPatientData> datas) {
        this.datas.addAll(datas);
    }

    /**
     * Converts a given {@code ReadOnlyPatientManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientManager}.
     */
    public JsonSerializablePatientManager(ReadOnlyPatientManager source) {
        datas.addAll(source.getMapView()
                .values()
                .stream()
                .map(JsonAdaptedPatientData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this patient manager into the model's {@code PatientManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PatientManager toModelType() throws IllegalValueException {
        PatientManager patientManager = new PatientManager();
        for (JsonAdaptedPatientData jsonAdaptedPatientData : datas) {
            IdData<Patient> patientData = jsonAdaptedPatientData.toModelType();
            if (patientManager.contains(patientData.getId())) {
                throw new IllegalValueException(DUPLICATE_ID);
            }
            try {
                patientManager.add(patientData);
            } catch (LimitExceededException limitEx) {
                throw new IllegalValueException("ID limit reached");
            } catch (IllegalArgumentException illArgEx) {
                throw new IllegalValueException(illArgEx.getMessage());
            }
        }
        return patientManager;
    }

}
