package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.commands.CommandTestUtil.DELIVERY_STATUS_OTW;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_DELIVERY_STATUS_DESC;
import static bookopedia.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookopedia.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.MarkCommand;
import bookopedia.model.DeliveryStatus;

public class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DELIVERY_STATUS_OTW, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid delivery status
        assertParseFailure(parser, "1" + INVALID_DELIVERY_STATUS_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_lowercaseOtw_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "otw";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.OTW);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    public void parse_mixedcaseOtw_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "oTw";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.OTW);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_uppercaseOtw_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "OTW";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.OTW);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_lowercasePending_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "pending";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.PENDING);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedcasePending_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "peNDing";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.PENDING);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_uppercasePending_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "PENDING";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.PENDING);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_lowercaseDone_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "done";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.DONE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedcaseDone_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "dONe";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.DONE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_uppercaseDone_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "DONE";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.DONE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_lowercaseFailed_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "failed";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.FAILED);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedcaseFailed_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "faILed";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.FAILED);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_uppercaseFailed_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "FAILED";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.FAILED);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_lowercaseReturn_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "return";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.RETURN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_mixedcaseReturn_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "reTUrn";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.RETURN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_uppercaseReturn_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + CliSyntax.PREFIX_STATUS + "RETURN";

        MarkCommand expectedCommand = new MarkCommand(targetIndex, DeliveryStatus.RETURN);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
