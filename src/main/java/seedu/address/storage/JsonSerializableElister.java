package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Elister;
import seedu.address.model.ReadOnlyElister;
import seedu.address.model.person.Person;

/**
 * An Immutable Elister that is serializable to JSON format.
 */
@JsonRootName(value = "elister")
class JsonSerializableElister {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableElister} with the given persons.
     */
    @JsonCreator
    public JsonSerializableElister(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyElister} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableElister}.
     */
    public JsonSerializableElister(ReadOnlyElister source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this E-Lister into the model's {@code Elister} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Elister toModelType() throws IllegalValueException {
        Elister elister = new Elister();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (elister.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            elister.addPerson(person);
        }
        return elister;
    }

}
