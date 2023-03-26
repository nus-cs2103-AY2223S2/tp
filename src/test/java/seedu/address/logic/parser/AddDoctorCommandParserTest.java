package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALTY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALTY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.YOE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YOE_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDoctors.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.TypicalDoctors;


public class AddDoctorCommandParserTest {
    private AddDoctorCommandParser parser = new AddDoctorCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Doctor expectedDoctor = new DoctorBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_BOB + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_BOB
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_BOB
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_BOB
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));

        // multiple specialties - last specialty accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_AMY + SPECIALTY_DESC_BOB + YOE_DESC_BOB
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));

        // multiple years of experience - last year of experience accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_AMY + YOE_DESC_BOB
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctor));


        // multiple tags - all accepted
        Doctor expectedDoctorMultipleTags = new DoctorBuilder(TypicalDoctors.BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SPECIALTY_DESC_BOB + YOE_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new AddDoctorCommand(expectedDoctorMultipleTags));

    }
}
