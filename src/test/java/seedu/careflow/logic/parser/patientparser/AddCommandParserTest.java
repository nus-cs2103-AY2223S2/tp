package seedu.careflow.logic.parser.patientparser;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.model.person.Patient;
import seedu.careflow.testutil.PatientBuilder;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

class AddCommandParserTest {
    private final AddCommandParser addCommandParser = new AddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder().build();
        AddCommand addPatientCommand = new AddCommand(expectedPatient);
        assertParseSuccess(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g male -ic T3871918C -da Aspirin -ec 93746552",
                addPatientCommand);
    }

    @Test
    void parse_invalidPhone_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph abc123 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g male -ic T3871918C -da Aspirin -ec 93746552",
                "Phone numbers should only contain numbers, "
                        + "and it should be at least 3 digits long.");
    }

    @Test
    void parse_invalidGender_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g abc -ic T3871918C -da Aspirin -ec 93746552",
                "Gender should only be either female or male, "
                        + "it should not be blank");
    }

    @Test
    void parse_invalidEmergencyContact_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g male -ic T3871918C -da Aspirin -ec abc",
                "Phone numbers should only contain numbers, "
                        + "and it should be at least 3 digits long.");
    }

    @Test
    void parse_missingDobField_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 "
                        + "-g male -ic T3871918C -da Aspirin -ec 93746552",
                "Invalid command format! \n"
                        + "add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 2001-05-28 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552");
    }

    @Test
    void parse_missingGender_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + " -ic T3871918C -da Aspirin -ec 93746552",
                "Invalid command format! \n"
                        + "add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 2001-05-28 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552"
                );
    }

    @Test
    void parse_missingDrugAllergy_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g male -ic T3871918C -ec 93746552",
                "Invalid command format! \n"
                        + "add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 2001-05-28 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552"
                );
    }

    @Test
    void parse_missingEmergencyContact_failure() {
        assertParseFailure(addCommandParser,
                " -n John Doe -ph 98765432 -em johnd@example.com "
                        + "-ad John Street, Block 123, #01-01 -dob 21-01-2000 "
                        + "-g male -ic T3871918C -da Aspirin",
                "Invalid command format! \n"
                        + "add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 2001-05-28 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552"
                );
    }
}
