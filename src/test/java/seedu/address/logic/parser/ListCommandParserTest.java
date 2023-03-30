package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Empty args
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, new ListCommand());

        // Has args
        assertParseSuccess(parser, PREAMBLE_NON_EMPTY, new ListCommand());

        // Show all modules
        assertParseSuccess(parser, PREFIX_ROOT.toString(), new ListCommand(true));

        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);

        // Valid module code
        assertParseSuccess(parser, MODULE_CODE_DESC_2103, new ListCommand(moduleCode));

        // Valid module code and lecture name
        assertParseSuccess(parser, MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
            new ListCommand(moduleCode, new LectureName(VALID_LECTURE_NAME_L1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidFormat = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

        // Missing module code and invalid lecture name format
        assertParseFailure(parser, INVALID_LECTURE_NAME_DESC, invalidFormat);

        // Invalid module code format
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // Valid module code present but invalid lecture name format
        assertParseFailure(parser, MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC, LectureName.MESSAGE_CONSTRAINTS);
    }

}
