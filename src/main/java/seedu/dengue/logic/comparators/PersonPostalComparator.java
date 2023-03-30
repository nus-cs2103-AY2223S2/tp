package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Person;

/**
 * Comparator for the class {@Person} that compares by its {@Age} attribute
 */
public class PersonPostalComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return new PostalComparator().compare(p1.getPostal(), p2.getPostal());
    }

}
