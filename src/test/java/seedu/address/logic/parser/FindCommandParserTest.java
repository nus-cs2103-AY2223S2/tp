package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList("CS2040S", "ST2334"));
        assertParseSuccess(parser, "CS2040S ST2334", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2040S \n \t ST2334 \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidFormat = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // No empty args
        assertParseFailure(parser, PREAMBLE_WHITESPACE, invalidFormat);

        // Missing module code and invalid lecture name format
        assertParseFailure(parser, INVALID_LECTURE_NAME_DESC, invalidFormat);

        // Invalid module code format
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // Valid module code present but invalid lecture name format
        assertParseFailure(parser, MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC, LectureName.MESSAGE_CONSTRAINTS);
    }
}
