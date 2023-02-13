package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.person.Person;

/**
 * An Immutable FriendlyLink that is serializable to JSON format.
 */
@JsonRootName(value = "friendlylink")
class JsonSerializableFriendlyLink {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriendlyLink} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFriendlyLink(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyFriendlyLink} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriendlyLink}.
     */
    public JsonSerializableFriendlyLink(ReadOnlyFriendlyLink source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code FriendlyLink} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendlyLink toModelType() throws IllegalValueException {
        FriendlyLink friendlyLink = new FriendlyLink();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (friendlyLink.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            friendlyLink.addPerson(person);
        }
        return friendlyLink;
    }

}
