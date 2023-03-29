package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Person;

/**
 * Comparator for the class {@Person} that compares by its {@Name} attribute
 */
public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return new NameComparator().compare(p1.getName(), p2.getName());
    }

}
