package seedu.patientist.model.person.patient;

import java.util.HashSet;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;

/**
 * Creates a dummy patient instance to check for equality of {@code IdNumber}.
 */
public class DummyPatient extends Patient {
    /**
     * Instantiates a dummy patient object.
     *
     * @param idNumber The id number we are checking equality for.
     */
    public DummyPatient(IdNumber idNumber) {
        super(new Email("dummy@example.com"), new Name("dummy"), new Phone("12345678"),
                idNumber, new Address("dummy"), new HashSet<>());
    }
}
