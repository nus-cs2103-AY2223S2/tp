package seedu.dengue.model.person;


public class AgeComparator extends GeneralComparator<Age> {
    public int compare(Age a1, Age a2) {
        int firstAge = Integer.valueOf(a1.value);
        int secondAge = Integer.valueOf(a2.value);
        return firstAge - secondAge;
    }
}
