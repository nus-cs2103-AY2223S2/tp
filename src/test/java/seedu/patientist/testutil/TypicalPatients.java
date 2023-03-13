package seedu.patientist.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ADAM = new PatientBuilder().withName("Adam Sandler")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("adam@example.com")
            .withPhone("94351253").withId("G73829173B")
            .withTags("Block1WardA", "Staff").withStatus("Doing fine").build();

    public static final Patient BOB = new PatientBuilder().withName("Bob Tan")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("bob@example.com")
            .withPhone("83829551").withId("Y78932734N")
            .withTags("Block2WardC", "Patient").build(); //Default status

    public static Patientist getTypicalPatientist() {
        Patientist pt = new Patientist();
        for (Patient pat : getTypicalPatients()) {
            pt.addPerson(pat);
        }
        return pt;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ADAM, BOB));
    }
}
