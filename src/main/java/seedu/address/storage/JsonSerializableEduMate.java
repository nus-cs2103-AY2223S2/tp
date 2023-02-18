package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.person.Person;

/**
 * An Immutable EduMate that is serializable to JSON format.
 */
@JsonRootName(value = "edumate")
class JsonSerializableEduMate {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final JsonAdaptedUser user;

    /**
     * Constructs a {@code JsonSerializableEduMate} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEduMate(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("user") JsonAdaptedUser user) {
        this.persons.addAll(persons);
        this.user = user;
    }

    /**
     * Converts a given {@code ReadOnlyEduMate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEduMate}.
     */
    public JsonSerializableEduMate(ReadOnlyEduMate source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        user = new JsonAdaptedUser(source.getUser());
    }

    /**
     * Converts this address book into the model's {@code EduMate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EduMate toModelType() throws IllegalValueException {
        EduMate eduMate = new EduMate();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (eduMate.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            eduMate.addPerson(person);
        }
        eduMate.setUser(user.toModelType());
        return eduMate;
    }

}
