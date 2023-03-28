package seedu.patientist.model.person.staff;

import seedu.patientist.model.person.*;

import java.util.HashSet;

public class DummyStaff extends Staff {
    public DummyStaff(IdNumber idNumber) {
        super(new Name("dummy"), new Phone("12345678"), new Email("dummy@example.com"),
                idNumber, new Address("dummy"), new HashSet<>());
    }
}
