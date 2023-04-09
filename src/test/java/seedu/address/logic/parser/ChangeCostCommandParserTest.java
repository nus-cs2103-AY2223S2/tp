package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ChangeCostCommand;


public class ChangeCostCommandParserTest {
    private ChangeCostCommandParser parser = new ChangeCostCommandParser();

    //@Test
    //public void parse_validArgs_returnsChangeCostCommand() {
    //    assertParseSuccess(parser, "1 0.5 10.0", new ChangeCostCommand(INDEX_FIRST_PET, 0.5, 10.0));
    //}

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeCostCommand.MESSAGE_USAGE));
    }
}
