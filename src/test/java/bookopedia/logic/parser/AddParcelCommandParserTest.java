package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.commands.CommandTestUtil.*;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookopedia.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import bookopedia.logic.commands.EditCommand;
import bookopedia.model.parcel.Parcel;
import bookopedia.testutil.EditPersonDescriptorBuilder;
import org.junit.jupiter.api.Test;

import bookopedia.logic.commands.AddParcelCommand;
import bookopedia.commons.core.index.Index;

public class AddParcelCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParcelCommand.MESSAGE_USAGE);

    private AddParcelCommandParser parser = new AddParcelCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PARCEL_DESC_SHOPEE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid parcel name
        assertParseFailure(parser, "1" + INVALID_PARCEL_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PARCEL_DESC_LAZADA;

        Parcel newParcel = new Parcel(VALID_PARCEL_LAZADA);
        AddParcelCommand expectedCommand = new AddParcelCommand(targetIndex, newParcel);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
