package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_BOB;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;
import static seedu.address.testutil.TypicalPatients.getUnassignedPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;


/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctors {

    public static final Doctor ALICE = new DoctorBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withSpecialty("General Medicine")
            .withYoe("5")
            .build();
    public static final Doctor BENSON = new DoctorBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432").withSpecialty("Anaesthesia")
            .withYoe("7").withTags("colleagues", "friends").build();

    public static final Doctor CARL = new DoctorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("carlk@example.com").withSpecialty("Orthopaedic").withYoe("9").build();

    public static final Doctor DANIEL = new DoctorBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("danielm@example.com").withSpecialty("Neurosurgery").withYoe("25")
            .withTags("HoD").withPatients(ZAYDEN).build();

    // Manually added
    public static final Doctor ELLA = new DoctorBuilder().withName("Ella Belle").withPhone("87345633")
            .withEmail("ellab@example.com").withSpecialty("Lung Specialist").withYoe("5")
            .withTags("DepartmentHead").build();
    public static final Doctor FIONA = new DoctorBuilder().withName("Fiona Ellen").withPhone("8398631")
            .withEmail("fiona.ellen@example.com").withSpecialty("Orthopaedic").withYoe("15")
            .withTags("Surgeon").build();

    // Manually added - Doctor's details found in {@code CommandTestUtil}
    public static final Doctor AMY = new DoctorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withSpecialty(VALID_SPECIALTY_AMY).withYoe(VALID_YOE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Doctor BOB = new DoctorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withSpecialty(VALID_SPECIALTY_BOB).withYoe(VALID_YOE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical doctors.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Doctor doctor : getTypicalDoctors()) {
            ab.addDoctor(doctor);
            for (Patient patient : doctor.getPatients()) {
                patient.assignDoctor(doctor);
                ab.addPatient(patient);
            }
        }
        Patient unassignedPatient = getUnassignedPatient();
        ab.addPatient(unassignedPatient);
        return ab;
    }

    public static List<Doctor> getTypicalDoctors() {
        return new ArrayList<>(Arrays.asList(
                new DoctorBuilder(ALICE).build(),
                new DoctorBuilder(BENSON).build(),
                new DoctorBuilder(DANIEL).build(),
                new DoctorBuilder(CARL).build()));
    }
}
