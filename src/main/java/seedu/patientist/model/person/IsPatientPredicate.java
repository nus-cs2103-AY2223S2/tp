package seedu.patientist.model.person;

import seedu.patientist.model.person.patient.Patient;

import java.util.function.Predicate;

public class IsPatientPredicate implements Predicate<Person> {
    @Override
    public boolean test(Person person) {
        return person instanceof Patient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof NameContainsKeywordsPredicate); // instanceof handles nulls
    }
}
