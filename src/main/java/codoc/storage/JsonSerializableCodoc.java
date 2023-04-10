package codoc.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import codoc.commons.exceptions.IllegalValueException;
import codoc.model.Codoc;
import codoc.model.ReadOnlyCodoc;
import codoc.model.person.Person;

/**
 * An Immutable Codoc that is serializable to JSON format.
 */
@JsonRootName(value = "codoc")
class JsonSerializableCodoc {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCodoc} with the given persons.
     */
    @JsonCreator
    public JsonSerializableCodoc(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyCodoc} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCodoc}.
     */
    public JsonSerializableCodoc(ReadOnlyCodoc source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this CoDoc into the model's {@code Codoc} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Codoc toModelType() throws IllegalValueException {
        Codoc codoc = new Codoc();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (codoc.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            codoc.addPerson(person);
        }
        return codoc;
    }

}
