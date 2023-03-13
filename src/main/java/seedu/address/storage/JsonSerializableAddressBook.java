package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "docedex")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Doctors list contains duplicate doctor(s).";

    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given doctors.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("doctors") List<JsonAdaptedDoctor> doctors) {
        this.doctors.addAll(doctors);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        doctors.addAll(source.getDoctorList().stream()
                .map(JsonSerializableAddressBook::convertToJsonAdaptedDoctor).collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code Doctor} into a JsonAdaptedDoctor.
     *
     * @param doctor a doctor object.
     * @return a JsonAdaptedDoctor.
     */
    private static JsonAdaptedDoctor convertToJsonAdaptedDoctor(Doctor doctor) {
        return new JsonAdaptedDoctor(doctor);
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (addressBook.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            addressBook.addDoctor(doctor);
        }
        return addressBook;
    }

}
