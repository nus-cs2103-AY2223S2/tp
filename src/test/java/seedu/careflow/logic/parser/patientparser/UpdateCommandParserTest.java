package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.UpdateCommand;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;
import seedu.careflow.model.patient.Name;
import seedu.careflow.testutil.EditPatientDescriptorBuilder;

class UpdateCommandParserTest {

    private final UpdateCommandParser updateCommandParser = new UpdateCommandParser();

    @Test
    void parse_changeAllFields_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withName("Roy Balakrishnan")
                .withPhone("97658843")
                .withEmail("royb@gmail.com")
                .withAddress("Blk 93, Sunshine Avenue")
                .withDob("15-03-1995")
                .withGender("Male")
                .withIc("T0247896C")
                .withDrugAllergy("Aspirin")
                .withEmergencyContact("96705899")
                .build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan "
                        + "-n Roy Balakrishnan -ph 97658843 -em royb@gmail.com -ad Blk 93, Sunshine Avenue "
                        + "-dob 15-03-1995 -g Male -ic T0247896C -da Aspirin -ec 96705899", updatePatientCommand);
    }


    @Test
    void parse_changeOnlyName_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withName("Roy B").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -n Roy B", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidName_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -n ", "Names should "
                + "only contain alphanumeric characters, spaces,"
                + " and special character like \" . \", \" - \", \" \' \""
                + " it should have a length between 1 and 50 characters and it must not be left blank");
    }

    @Test
    void parse_changeOnlyPhone_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withPhone("97658843").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -ph 97658843", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidPhone_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -ph abc",
                "Phone numbers must consist of numerical digits"
                        + " and be between 3 and 20 digits long, with no special characters allowed.");
    }

    @Test
    void parse_changeOnlyEmail_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withEmail("royb@gmail.com").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -em royb@gmail.com",
                updatePatientCommand);
    }

    @Test
    void parse_changeInvalidEmail_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -em 9999999",
                "Emails should be of the format local-part@domain and adhere "
                        + "to the following constraints:\n"
                        + "1. The local-part should only contain alphanumeric "
                        + "characters and these special characters, excluding the parentheses, (+_.-). "
                        + "The local-part may not start or end with any special characters.\n"
                        + "2. This is followed by a '@' and then a domain name. The domain name is made up of "
                        + "domain labels separated by periods.\n"
                        + "The domain name must:\n"
                        + "    - end with a domain label at least 2 characters long\n"
                        + "    - have each domain label start and end with alphanumeric characters\n"
                        + "    - have each domain label consist of alphanumeric characters, separated "
                        + "only by hyphens, if any.");
    }

    @Test
    void parse_changeOnlyAddress_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withAddress("Blk 93, Sunshine Avenue").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -ad Blk 93, Sunshine Avenue",
                updatePatientCommand);
    }

    @Test
    void parse_changeOnlyDob_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withDob("15-03-1995").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -dob 15-03-1995", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidDob_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -dob 999999",
                "Date of birth should only contain numeric characters."
                        + " The format of date should be dd/mm/yyyy or dd.mm.yyyy or dd-mm-yyyy"
                        + " and the date should fall between 01/01/1900 and current date");
    }

    @Test
    void parse_changeOnlyGender_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withGender("Male").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -g Male", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidGender_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -g abc", "Gender "
                + "should only be either female or male, it should not be blank");
    }

    @Test
    void parse_changeOnlyIc_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withIc("T0247896C").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -ic T0247896C", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidIc_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -ic 123",
                "The IC number begins with a letter followed "
                        + "by 7 digits and concludes with another letter");
    }

    @Test
    void parse_changeOnlyDrugAllergy_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withDrugAllergy("Aspirin").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -da Aspirin", updatePatientCommand);
    }

    //    @Test
    //    void parse_changeInvalidDrugAllergy_failure() {
    //        assertParseFailure(updateCommandParser, "Roy Balakrishnan -da 123", " ");
    //    }

    @Test
    void parse_changeOnlyEmergencyContact_success() {
        Name name = new Name("Roy Balakrishnan");
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withEmergencyContact("96705899").build();
        UpdateCommand updatePatientCommand = new UpdateCommand(name, descriptor);
        assertParseSuccess(updateCommandParser, "Roy Balakrishnan -ec 96705899", updatePatientCommand);
    }

    @Test
    void parse_changeInvalidEmergencyContact_failure() {
        assertParseFailure(updateCommandParser, "Roy Balakrishnan -ec abc",
                "Phone numbers must consist of numerical digits"
                        + " and be between 3 and 20 digits long, with no special characters allowed.");
    }
}
