package seedu.address.model.person.doctor;

import java.util.HashSet;
import java.util.List;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.patient.PatientStub;
import seedu.address.model.tag.Tag;
/**
 * Represents a Doctor with pre-filled attributes.
 */
public class DoctorStub extends Doctor {
    /**
     * Constructor for {@code DoctorStub}
     */
    public DoctorStub() {
        super(new Name("John Doe"), new Phone("88887777"),
                new Email("prawn@gmail.com"), new Specialty("GP"), new Yoe("1"),
                new HashSet<>(List.of(new Tag("Friend"), new Tag("College"))),
                new HashSet<>(List.of(new PatientStub())));
    }

}
