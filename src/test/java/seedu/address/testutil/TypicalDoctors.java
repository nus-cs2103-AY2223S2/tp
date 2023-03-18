package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.doctor.Doctor;

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
            .withEmail("johnd@example.com").withPhone("98765432").withSpecialty("General Medicine")
            .withYoe("5").withTags("owesMoney", "friends").build();

    public static final Doctor CARL = new DoctorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("carlk@example.com").withSpecialty("General Medicine").withYoe("5").build();

    public static final Doctor DANIEL = new DoctorBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("danielm@example.com").withSpecialty("Neurosurgery").withYoe("10")
            .withTags("HoD").build();

    // Manually added
    public static final Doctor ELLA = new DoctorBuilder().withName("Ella Belle").withPhone("87345633")
            .withEmail("ellab@example.com").withSpecialty("Lung Specialist").withYoe("5")
            .withTags("Department Head").build();
    public static final Doctor FIONA = new DoctorBuilder().withName("Fiona Ellen").withPhone("8398631")
            .withEmail("fiona.ellen@example.com").withSpecialty("Orthopaedic").withYoe("15")
            .withTags("Surgeon").build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical doctors.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Doctor doctor : getTypicalDoctors()) {
            ab.addDoctor(doctor);
        }
        return ab;
    }

    public static List<Doctor> getTypicalDoctors() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }
}
