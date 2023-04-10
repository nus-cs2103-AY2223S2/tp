package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HMHero;
import seedu.address.model.ReadOnlyHMHero;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableHMHero {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHMHero} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHMHero(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyHMHero} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHMHero}.
     */
    public JsonSerializableHMHero(ReadOnlyHMHero source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code HMHero} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public HMHero toModelType() throws IllegalValueException {
        HMHero HMHero = new HMHero();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (HMHero.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            HMHero.addPerson(person);
        }
        return HMHero;
    }

}
