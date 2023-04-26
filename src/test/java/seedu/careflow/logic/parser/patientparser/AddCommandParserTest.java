package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.testutil.PatientBuilder;


class AddCommandParserTest {
    private final AddCommandParser addCommandParser = new AddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder().build();
        AddCommand addPatientCommand = new AddCommand(expectedPatient);
        assertParseSuccess(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + "-g female -ic A7654321B -da penicillin -ec 88888888",
                addPatientCommand);
    }

    @Test
    void parse_allRequiredFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder().withDrugAllergy(null).withEmergencyContact(null).build();
        AddCommand addPatientCommand = new AddCommand(expectedPatient);
        assertParseSuccess(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + "-g female -ic A7654321B",
                addPatientCommand);
    }

    @Test
    void parse_invalidPhone_failure() {
        assertParseFailure(addCommandParser,
                " -n Amy Bee -ph abc123 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + "-g female -ic A7654321B -da penicillin -ec 88888888",
                "Phone numbers must consist of numerical digits "
                        + "and be between 3 and 20 digits long, with no special characters allowed.");
    }

    @Test
    void parse_invalidGender_failure() {
        assertParseFailure(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + "-g abc -ic A7654321B -da penicillin -ec 88888888",
                "Gender should only be either female or male, "
                        + "it should not be blank");
    }

    @Test
    void parse_invalidEmergencyContact_failure() {
        assertParseFailure(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + "-g female -ic A7654321B -da penicillin -ec abc",
                "Phone numbers must consist of numerical digits"
                        + " and be between 3 and 20 digits long, with no special characters allowed.");
    }

    @Test
    void parse_missingDobField_failure() {
        assertParseFailure(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 "
                        + "-g female -ic A7654321B -da penicillin -ec 88888888",
                "Invalid command format! \n"
                        + "p add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: p add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 28-05-2001 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552");
    }

    @Test
    void parse_missingGender_failure() {
        assertParseFailure(addCommandParser,
                " -n Amy Bee -ph 85355255 -em amy@gmail.com "
                        + "-ad 123, Jurong West Ave 6, #08-111 -dob 01-01-1990 "
                        + " -ic A7654321B -da penicillin -ec 88888888",
                "Invalid command format! \n"
                        + "p add: Adds a patient to patient records.\n"
                        + "Parameters: -n NAME -ph PHONE -em EMAIL "
                        + "-ad ADDRESS -dob DOB -g GENDER -ic IC -da DRUG_ALLERGY "
                        + "-ec EMERGENCY_CONTACT_NUMBER\n"
                        + "Example: p add -n Tom Smith -ph 84356788 -em tsmith@gmail.com "
                        + "-ad 969 Lock Street #04-32 -dob 28-05-2001 -g MALE -ic T0278234M "
                        + "-da Aspirin -ec 93746552"
        );
    }
}
