package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Person;

/**
 * Comparator for the class {@Person} that compares by its {@Date} attribute
 */
public class PersonDateComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return new DateComparator().compare(p1.getDate(), p2.getDate());
    }

}
