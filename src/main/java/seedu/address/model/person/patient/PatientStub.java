package seedu.address.model.person.patient;

import java.util.HashSet;
import java.util.List;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient with pre-filled attributes.
 */
public class PatientStub extends Patient {
    /**
     * Constructor for {@code PatientStub}
     */
    public PatientStub() {
        super(new Name("John Doe"), new Phone("88887777"),
                new Email("prawn@gmail.com"), new Height("1.7"), new Weight("63.4"), new Diagnosis("None"),
                new Status("Inpatient"), new Remark("Compliant"),
                new HashSet<>(List.of(new Tag("Friend"), new Tag("College"))));
    }

}

