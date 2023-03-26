package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DIAGNOSIS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DIAGNOSIS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalPatients;


public class AddPatientCommandParserTest {
    private AddPatientCommandParser parser = new AddPatientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple heights - last height accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_AMY + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_AMY + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple diagnosis - last diagnosis accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_AMY + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple status - last status accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_AMY + STATUS_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));

        // multiple remark - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_AMY
                + REMARK_DESC_BOB + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatient));


        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(TypicalPatients.BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + DIAGNOSIS_DESC_BOB + STATUS_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddPatientCommand(expectedPatientMultipleTags));

    }
}
