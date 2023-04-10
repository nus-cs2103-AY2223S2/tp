package seedu.patientist.testutil;

import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_STATUS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.patientist.model.person.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withStatus(VALID_STATUS_AMY).withIdNumber(VALID_PID_AMY).withPriority("LOW").build();

    public static final Patient ADAM = new PatientBuilder().withName("Adam Sandler")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("adam@example.com")
            .withPhone("94351253").withIdNumber("G73829173B").withPriority("LOW")
            .withStatus("Doing fine").build();

    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB).withPriority("LOW")
            .withPhone(VALID_PHONE_BOB).withIdNumber(VALID_PID_BOB).withStatus(VALID_STATUS_BOB)
            .build(); //Default status

    public static final Patient CHARLIE = new PatientBuilder().withName("Charlie Sandler")
            .withAddress("123, abc, #08-111").withEmail("charlie@example.com")
            .withPhone("2136784").withIdNumber("G487659645D").withPriority("LOW")
            .withStatus("Doing good").build();

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ADAM, CHARLIE));
    }
}
