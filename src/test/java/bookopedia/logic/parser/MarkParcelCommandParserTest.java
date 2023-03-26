package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_PARCEL_STATUS_DESC;
import static bookopedia.logic.commands.CommandTestUtil.PARCEL_STATUS_FRAGILE;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookopedia.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.MarkParcelCommand;
import bookopedia.model.ParcelStatus;

public class MarkParcelCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkParcelCommand.MESSAGE_USAGE);

    private MarkParcelCommandParser parser = new MarkParcelCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PARCEL_STATUS_FRAGILE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PARCEL_STATUS_FRAGILE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PARCEL_STATUS_FRAGILE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid parcel status
        assertParseFailure(parser, "1 pc/1 " + INVALID_PARCEL_STATUS_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidParcelIndex_failure() {
        // invalid parcel index
        assertParseFailure(parser, "1 pc/aaa s/fragile", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyParcelStatus_failure() {
        // invalid parcel index
        assertParseFailure(parser, "1 pc/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_fragile_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + "fragile";

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.FRAGILE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_upperCaseFragile_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + ParcelStatus.FRAGILE;

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.FRAGILE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedCaseFragile_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + "fragILE";

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.FRAGILE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_bulky_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + "bulky";

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.BULKY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_upperCaseBulky_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + ParcelStatus.BULKY;

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.BULKY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedCaseBulky_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + targetIndex.getOneBased()
                + " " + CliSyntax.PREFIX_STATUS + "buLKY";

        MarkParcelCommand expectedCommand = new MarkParcelCommand(targetIndex, targetIndex, ParcelStatus.BULKY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
