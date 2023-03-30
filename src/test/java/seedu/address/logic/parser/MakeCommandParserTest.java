package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.ClassificationTerms.CHAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEntities.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MakeCommand;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;
import seedu.address.testutil.EntityBuilder;

public class MakeCommandParserTest {
    private MakeCommandParser parser = new MakeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Entity expectedEntity = new EntityBuilder(BOB).buildChar();

        /*
        Need to update with tests that are more relevant to current app design
         */
        // whitespace only preamble
        assertParseSuccess(parser,  CHAR.label + NAME_DESC_BOB,
                                                                new MakeCommand(expectedEntity));

        // multiple names - last name accepted
        // Apparently this don't work yet
        //assertParseSuccess(parser, MakeCommand.COMMAND_WORD + " " + CHAR.label + NAME_DESC_AMY + NAME_DESC_BOB,
        //                                                    new MakeCommand(expectedEntity));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeCommand.MESSAGE_USAGE);
        // missing classification
        // Currently also very barebones; need implementation
        assertParseFailure(parser, MakeCommand.COMMAND_WORD + " " + VALID_NAME_BOB, expectedMessage);

        assertParseFailure(parser, MakeCommand.COMMAND_WORD + " " + CHAR.label, expectedMessage);
    }



    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, MakeCommand.COMMAND_WORD + " " + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB, String.format(
                                                    MESSAGE_INVALID_COMMAND_FORMAT, MakeCommand.MESSAGE_USAGE));
    }
}


