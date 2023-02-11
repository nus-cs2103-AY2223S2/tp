package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ELDERLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VOLUNTEER_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPairCommand;

public class AddPairCommandParserTest {
    private AddPairCommandParser parser = new AddPairCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // todo (yong jing)
        /*
        Person amy = new PersonBuilder().withName("Amy").build();
        Pair expectedPair = new PairBuilder(PAIR1).withElderly(amy).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ELDERLY_DESC_AMY + VOLUNTEER_DESC_BOB,
                new AddPairCommand(expectedPair));

        // multiple elderly - last elderly accepted
        assertParseSuccess(parser, ELDERLY_DESC_BOB + ELDERLY_DESC_AMY + VOLUNTEER_DESC_BOB,
                new AddPairCommand(expectedPair));

        // multiple volunteers - last volunteer accepted
        assertParseSuccess(parser, ELDERLY_DESC_AMY + VOLUNTEER_DESC_AMY + VOLUNTEER_DESC_BOB,
                new AddPairCommand(expectedPair));
         */
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPairCommand.MESSAGE_USAGE);

        // missing elderly prefix
        assertParseFailure(parser, ELDERLY_DESC_AMY,
                expectedMessage);

        // missing volunteer prefix
        assertParseFailure(parser, VOLUNTEER_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_NAME_AMY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // todo (yong jing)
        // invalid elderly
        //assertParseFailure(parser, INVALID_ELDERLY_DESC + VOLUNTEER_DESC_BOB);

        // invalid volunteer
        //assertParseFailure(parser, ELDERLY_DESC_AMY + INVALID_VOLUNTEER_DESC);
    }
}
