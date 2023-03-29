package seedu.dengue.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class DengueHotspotTracker implements ReadOnlyDengueHotspotTracker {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public DengueHotspotTracker() {}

    /**
     * Creates an DengueHotspotTracker using the Persons in the {@code toBeCopied}
     */
    public DengueHotspotTracker(ReadOnlyDengueHotspotTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code DengueHotspotTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyDengueHotspotTracker newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Dengue Hotspot Tracker.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the Dengue Hotspot Tracker.
     * The person must not already exist in the Dengue Hotspot Tracker.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the Dengue Hotspot Tracker.
     * The person identity of {@code editedPerson} must not be the same as another existing person in
     * the Dengue Hotspot Tracker.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code DengueHotspotTracker}.
     * {@code key} must exist in the Dengue Hotspot Tracker.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DengueHotspotTracker // instanceof handles nulls
                && persons.equals(((DengueHotspotTracker) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    @Override
    public DengueHotspotTracker generateDeepCopy() {
        DengueHotspotTracker copy = new DengueHotspotTracker();
        ObservableList<Person> personList = this.getPersonList()
                .stream()
                .map(p -> p.getCopy())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        copy.setPersons(personList);
        return copy;

    }
}
