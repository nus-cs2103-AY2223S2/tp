package seedu.vms.testutil;

import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.vms.model.patient.AddressBook;
import seedu.vms.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

        public static final Patient ALICE = new PatientBuilder()
                        .withName("Alice Pauline")
                        .withPhone("94351253")
                        .build();
        public static final Patient BENSON = new PatientBuilder()
                        .withName("Benson Meier")
                        .withPhone("98765432")
                        .build();
        public static final Patient CARL = new PatientBuilder()
                        .withName("Carl Kurz")
                        .withPhone("95352563")
                        .build();
        public static final Patient DANIEL = new PatientBuilder()
                        .withName("Daniel Meier")
                        .withPhone("87652533")
                        .build();
        public static final Patient ELLE = new PatientBuilder()
                        .withName("Elle Meyer")
                        .withPhone("9482224")
                        .build();
        public static final Patient FIONA = new PatientBuilder()
                        .withName("Fiona Kunz")
                        .withPhone("9482427")
                        .build();
        public static final Patient GEORGE = new PatientBuilder()
                        .withName("George Best")
                        .withPhone("9482442")
                        .build();

        // Manually added
        public static final Patient HOON = new PatientBuilder()
                        .withName("Hoon Meier")
                        .withPhone("8482424")
                        .build();
        public static final Patient IDA = new PatientBuilder()
                        .withName("Ida Mueller")
                        .withPhone("8482131")
                        .build();

        // Manually added - Patient's details found in {@code CommandTestUtil}
        public static final Patient AMY = new PatientBuilder()
                        .withName(VALID_NAME_AMY)
                        .withPhone(VALID_PHONE_AMY)
                        .build();
        public static final Patient BOB = new PatientBuilder()
                        .withName(VALID_NAME_BOB)
                        .withPhone(VALID_PHONE_BOB)
                        .build();

        public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

        private TypicalPatients() {} // prevents instantiation

        /**
         * Returns an {@code AddressBook} with all the typical patients.
         */
        public static AddressBook getTypicalAddressBook() {
                AddressBook ab = new AddressBook();
                for (Patient patient : getTypicalPatients()) {
                        ab.add(patient);
                }
                return ab;
        }

        public static List<Patient> getTypicalPatients() {
                return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
        }
}
