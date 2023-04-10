package seedu.patientist.model.person.patient;

import java.util.function.Predicate;

import seedu.patientist.model.person.Person;

/**
 * Test if Person object is a patients
 */
public class IsPatientPredicate implements Predicate<Person> {
    @Override
    public boolean test(Person person) {
        return person instanceof Patient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof IsPatientPredicate); // instanceof handles nulls
    }
}
