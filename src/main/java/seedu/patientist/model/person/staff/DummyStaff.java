package seedu.patientist.model.person.staff;

import java.util.HashSet;

import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;

/**
 * Creates a dummy staff instance to check for equality of {@code IdNumber}.
 */
public class DummyStaff extends Staff {
    /**
     * Instantiates a dummy staff object.
     *
     * @param idNumber The id number we are checking equality for.
     */
    public DummyStaff(IdNumber idNumber) {
        super(new Name("dummy"), new Phone("12345678"), new Email("dummy@example.com"),
                idNumber, new Address("dummy"), new HashSet<>());
    }
}
