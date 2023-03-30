package seedu.patientist.model.person.patient;

import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;

import seedu.patientist.model.Model;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.ward.Ward;


/**
 * Checks the tag of all personnel to see if they belong to a particular ward
 */
public class PatientInWardPredicate implements Predicate<Person> {
    private final Model model;
    private final String keyword;

    /**
     * Constructor for PatientInWardPredicate.
     */
    public PatientInWardPredicate(Model model, String keyword) {
        requireAllNonNull(model, keyword);
        this.model = model;
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Patient)) {
            return false;
        }
        Ward ward = model.getWard(keyword);

        if (ward == null) {
            return false;
        }

        return ward.containsPatient((Patient) person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientInWardPredicate // instanceof handles nulls
                    && keyword.equals(((PatientInWardPredicate) other).keyword));
    }
}
