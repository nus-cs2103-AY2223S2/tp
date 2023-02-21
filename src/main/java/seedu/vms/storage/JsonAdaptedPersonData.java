package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.person.Person;


/**
 * Jackson-friendly version of {@code IdData<Person>}.
 */
public class JsonAdaptedPersonData {
    private final boolean isActive;
    private final int id;
    private final JsonAdaptedPerson person;


    /**
     * Constructs a {@code JsonAdaptedPersonData} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPersonData(
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("id") int id,
            @JsonProperty("person") JsonAdaptedPerson person) {
        this.isActive = isActive;
        this.id = id;
        this.person = person;
    }


    /**
     * Converts a given {@code IdData<Person>} into this class for Jackson use.
     */
    public JsonAdaptedPersonData(IdData<Person> personData) {
        isActive = personData.isActive();
        id = personData.getId();
        person = new JsonAdaptedPerson(personData.getValue());
    }


    /**
     * Converts this Jackson-friendly adapted person data object into the model's {@code IdData<Person>} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person data.
     */
    public IdData<Person> toModelType() throws IllegalValueException {
        return new IdData<>(isActive, id, person.toModelType());
    }
}
