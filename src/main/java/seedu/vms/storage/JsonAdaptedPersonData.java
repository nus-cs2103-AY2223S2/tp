package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.person.Person;

public class JsonAdaptedPersonData {
    private final boolean isActive;
    private final int id;
    private final JsonAdaptedPerson person;


    @JsonCreator
    public JsonAdaptedPersonData(
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("id") int id,
            @JsonProperty("person") JsonAdaptedPerson person) {
        this.isActive = isActive;
        this.id = id;
        this.person = person;
    }


    public JsonAdaptedPersonData(IdData<Person> personData) {
        isActive = personData.isActive();
        id = personData.getId();
        person = new JsonAdaptedPerson(personData.getValue());
    }


    public IdData<Person> toModelType() throws IllegalValueException {
        return new IdData<>(isActive, id, person.toModelType());
    }
}
