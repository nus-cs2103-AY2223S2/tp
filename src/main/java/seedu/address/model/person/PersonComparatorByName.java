package seedu.address.model.person;

import java.util.Comparator;

public class PersonComparatorByName implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        String name1 = person1.getName().toString();
        String name2 = person2.getName().toString();
        return name1.compareTo(name2);
    }
}
