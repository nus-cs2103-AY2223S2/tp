package seedu.dengue.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.Person;

/**
 * An Immutable Dengue Hotspot Tracker that is serializable to JSON format.
 */
class CsvSerializableDengueHotspotTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<BeansAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code CsvSerializableDengueHotspotTracker} with the given persons.
     */
    public CsvSerializableDengueHotspotTracker(List<BeansAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyDengueHotspotTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code CsvSerializableDengueHotspotTracker}.
     */
    public CsvSerializableDengueHotspotTracker(ReadOnlyDengueHotspotTracker source) {
        persons.addAll(source.getPersonList().stream().map(BeansAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Dengue Hotspot Tracker into the model's {@code DengueHotspotTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DengueHotspotTracker toModelType() throws IllegalValueException {
        DengueHotspotTracker dengueHotspotTracker = new DengueHotspotTracker();
        for (BeansAdaptedPerson beansAdaptedPerson : persons) {
            Person person = beansAdaptedPerson.toModelType();
            if (dengueHotspotTracker.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            dengueHotspotTracker.addPerson(person);
        }
        return dengueHotspotTracker;
    }

}
