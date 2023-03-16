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
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;

/**
 * An Immutable Patientist that is serializable to JSON format.
 */
@JsonRootName(value = "patientist")
class JsonSerializablePatientist {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientist} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePatientist(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPatientist} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientist}.
     */
    public JsonSerializablePatientist(ReadOnlyPatientist source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this patientist book into the model's {@code Patientist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Patientist toModelType() throws IllegalValueException {
        Patientist patientist = new Patientist();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (patientist.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            if (person instanceof Staff) {
                Staff staff = (Staff) person;
                //patientist.addPerson(staff);
            } else {
                Patient patient = (Patient) person;
                //patientist.addPerson(patient);
            }
        }
        return patientist;
    }

}
