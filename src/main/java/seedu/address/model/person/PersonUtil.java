package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.util.Pair;

/**
 * A container for App specific utility functions
 */
public class PersonUtil {

    public static final Pair<Integer, Integer> NO_DUPLICATES_PAIR = new Pair<>(-1, -1);

    /**
     * Returns true if given {@code Person}s share either email or phone, which should be unique for each person.
     * Other fields can be duplicated, which is unlikely but possible.
     */
    public static boolean isDuplicated(Person personOne, Person personTwo) {
        if (personOne == personTwo) {
            return true;
        }

        if (personOne == null) {
            return personTwo == null;
        }

        if (personTwo == null) {
            return false;
        }

        return personOne.getEmail().equals(personTwo.getEmail()) || personOne.getPhone().equals(personTwo.getPhone());
    }

    /**
     * Returns the duplicated field and value of said field if the two {@code Person}s given are duplicates. Returns an
     * empty {@code String} otherwise.
     */
    public static String findDuplicateFieldString(Person personOne, Person personTwo) {
        requireAllNonNull(personOne, personTwo);

        if (personOne.getEmail().equals(personTwo.getEmail())) {
            return "Email: " + personOne.getEmail().toString();
        } else if (personOne.getPhone().equals(personTwo.getPhone())) {
            return "Phone: " + personOne.getPhone().toString();
        } else {
            return "";
        }
    }

    /**
     * Returns a {@code Pair} of indexes referring to the first duplicates found within a {@code List} of
     * {@code Person}s.
     * If no duplicates are found, a {@code Pair} containing -1 as both key and value are returned.
     */
    public static Pair<Integer, Integer> findDuplicates(List<Person> personList) {
        HashMap<Phone, Integer> hmPhone = new HashMap<>();
        HashMap<Email, Integer> hmEmail = new HashMap<>();
        for (int i = 0; i < personList.size(); i++) {
            Person curPerson = personList.get(i);
            Optional<Integer> duplicateEntry = Optional.ofNullable(hmPhone.put(curPerson.getPhone(), i));
            if (duplicateEntry.isPresent()) {
                int duplicated = duplicateEntry.get();
                return new Pair<>(duplicated, i);
            }

            duplicateEntry = Optional.ofNullable(hmEmail.put(curPerson.getEmail(), i));
            if (duplicateEntry.isPresent()) {
                int duplicated = duplicateEntry.get();
                return new Pair<>(duplicated, i);
            }
        }
        return new Pair<>(-1, -1);
    }

    public static boolean hasDuplicates(List<Person> personList) {
        return !findDuplicates(personList).equals(NO_DUPLICATES_PAIR);
    }




}
