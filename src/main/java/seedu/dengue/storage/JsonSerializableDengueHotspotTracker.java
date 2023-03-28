package seedu.dengue.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.Person;

/**
 * An Immutable Dengue Hotspot Tracker that is serializable to JSON format.
 */
@JsonRootName(value = "denguehotspottracker")
class JsonSerializableDengueHotspotTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDengueHotspotTracker} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDengueHotspotTracker(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyDengueHotspotTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDengueHotspotTracker}.
     */
    public JsonSerializableDengueHotspotTracker(ReadOnlyDengueHotspotTracker source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Dengue Hotspot Tracker into the model's {@code DengueHotspotTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DengueHotspotTracker toModelType() throws IllegalValueException {
        DengueHotspotTracker dengueHotspotTracker = new DengueHotspotTracker();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (dengueHotspotTracker.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            dengueHotspotTracker.addPerson(person);
        }
        return dengueHotspotTracker;
    }

}
