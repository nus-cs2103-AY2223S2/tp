package seedu.vms.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.model.IdData;
import seedu.vms.model.patient.AddressBook;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.ReadOnlyAddressBook;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String DUPLICATE_ID = "Patients list contains duplicate ID(s).";

    private final List<JsonAdaptedPatientData> datas = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given patients.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("datas") List<JsonAdaptedPatientData> datas) {
        this.datas.addAll(datas);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        datas.addAll(source.getMapView()
                .values()
                .stream()
                .map(JsonAdaptedPatientData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPatientData jsonAdaptedPatientData : datas) {
            IdData<Patient> patientData = jsonAdaptedPatientData.toModelType();
            if (addressBook.contains(patientData.getId())) {
                throw new IllegalValueException(DUPLICATE_ID);
            }
            try {
                addressBook.add(patientData);
            } catch (LimitExceededException limitEx) {
                // TODO: better message
                throw new IllegalValueException("ID limit reached");
            }
        }
        return addressBook;
    }

}
