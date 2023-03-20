package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_WITHOUT_HASHCODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_WITH_HASHCODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_WITH_ID_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_WITHOUT_HASHCODE_BENEDICT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_WITH_HASHCODE_BENEDICT_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DeleteApplicantCommandParser.HASHCODE_MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalListings.SOFTWARE_DEVELOPER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;

public class DeleteApplicantCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicantCommand.MESSAGE_USAGE);

    private DeleteApplicantCommandParser parser = new DeleteApplicantCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Listing expectedListing =
                new ListingBuilder(SOFTWARE_DEVELOPER).build();

        // all fields present (with hash)
        assertParseSuccess(parser, "1 " + VALID_ID_WITH_HASHCODE_BENEDICT_DESC,
                new DeleteApplicantCommand(INDEX_FIRST_LISTING, VALID_APPLICANT_NAME_WITH_ID_BENEDICT));

        // all fields present (without hash)
        assertParseSuccess(parser, "1 " + VALID_ID_WITHOUT_HASHCODE_BENEDICT_DESC,
                new DeleteApplicantCommand(INDEX_FIRST_LISTING, VALID_APPLICANT_NAME_BENEDICT));

        // multiple id present, choose last one
        assertParseSuccess(parser,
                "1" + VALID_ID_WITHOUT_HASHCODE_BENEDICT_DESC + VALID_ID_WITH_HASHCODE_BENEDICT_DESC,
                new DeleteApplicantCommand(INDEX_FIRST_LISTING, VALID_APPLICANT_NAME_WITH_ID_BENEDICT));
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, VALID_ID_WITH_HASHCODE_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "a" + VALID_ID_WITH_HASHCODE_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + VALID_ID_WITH_HASHCODE_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + VALID_ID_WITH_HASHCODE_BENEDICT_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 i/ string " + VALID_ID_WITH_HASHCODE_BENEDICT_DESC,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingId_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidId_failure() {
        assertParseFailure(parser, "1" + INVALID_ID_WITH_HASHCODE_DESC, HASHCODE_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ID_WITHOUT_HASHCODE_DESC, Name.MESSAGE_CONSTRAINTS);
    }
}
