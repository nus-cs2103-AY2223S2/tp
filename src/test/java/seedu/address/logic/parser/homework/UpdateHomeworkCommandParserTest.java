package seedu.address.logic.parser.homework;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.homework.UpdateHomeworkCommand;
import seedu.address.logic.parser.AddressBookParser;


public class UpdateHomeworkCommandParserTest {
    private UpdateHomeworkCommandParser parser = new UpdateHomeworkCommandParser();
    private AddressBookParser mainParser = new AddressBookParser();

    @Test
    public void parse_compulsoryArgsNotPresent_failure() {
        //Index missing
        String input1 = " name/" + VALID_NAME_AMY
                + " homework/Maths";
        String expected1 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        UpdateHomeworkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input1, expected1);

        //Index missing
        String input2 = " index/1"
                + " homework/Maths";
        String expected2 = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        UpdateHomeworkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input2, expected2);

    }

    @Test
    public void parse_allOptionalArgsAbsent_failure() {
        String input = " name/" + VALID_NAME_AMY
                + " index/1";
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        UpdateHomeworkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input, expected);
    }
}
