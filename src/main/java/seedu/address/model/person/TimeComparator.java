package seedu.address.model.person;

import java.util.Comparator;

public class TimeComparator implements Comparator<Person> {
  @Override
  public int compare(Person person1, Person person2) {
    if(person1.hasTime() & person2.hasTime()) {
      return person1.getTime().compareTo(person2.getTime());
    } else {
      return 0;
    }
  }
}
