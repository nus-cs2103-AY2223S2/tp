package seedu.address.model.person;

import java.util.Comparator;

/**
 * Comparator for Person
 */
public class PersonComparatorByName implements Comparator<Person> {
    /**
     * Comparator for Person
     * @param person1 the first object to be compared.
     * @param person2 the second object to be compared.
     * @return Comparing result.
     */
    @Override
    public int compare(Person person1, Person person2) {
        String name1 = person1.getName().toString();
        String name2 = person2.getName().toString();
        return name1.compareTo(name2);
    }
}
