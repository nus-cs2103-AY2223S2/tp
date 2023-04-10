package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.model.applicant.Name;
import seedu.address.testutil.ApplicantBuilder;

public class AddApplicantCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicantCommand.MESSAGE_USAGE);

    private AddApplicantCommandParser parser = new AddApplicantCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // only one applicant
        assertParseSuccess(parser, "1 " + VALID_APPLICANT_NAME_CHRIS_DESC,
                new AddApplicantCommand(
                        INDEX_FIRST_LISTING,
                        new ApplicantBuilder().withName(VALID_APPLICANT_NAME_CHRIS).build()));

        // multiple applicants - only accept the last one
        assertParseSuccess(parser, "1 " + VALID_APPLICANT_NAME_CHRIS_DESC + VALID_APPLICANT_NAME_BENEDICT_DESC,
                new AddApplicantCommand(
                        INDEX_FIRST_LISTING,
                        new ApplicantBuilder().withName(VALID_APPLICANT_NAME_BENEDICT).build()));

    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, VALID_APPLICANT_NAME_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "a" + VALID_APPLICANT_NAME_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + VALID_APPLICANT_NAME_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + VALID_APPLICANT_NAME_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 i/ string" + VALID_APPLICANT_NAME_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingApplicant_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidId_failure() {
        assertParseFailure(parser, "1" + INVALID_APPLICANT_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_APPLICANT, Name.MESSAGE_CONSTRAINTS);
    }
}
