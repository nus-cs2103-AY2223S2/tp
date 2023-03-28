package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BY_TAG_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BY_TAG_PREFIX;
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
        String modA = "CS2040S";
        String modB = "ST2334";

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(modA, modB), false);
        assertParseSuccess(parser, String.format("%s, %s", modA, modB), expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, String.format(" \n %s, \n \t %s \t", modA, modB), expectedFindCommand);
    }

    @Test
    public void parse_validArgsForModules_returnsFindCommand() {
        String modA = "CS2040S";
        String modB = "ST2334";

        // Find by valid tag
        assertParseSuccess(parser,
                String.format("%s, %s, %s", modA, modB, VALID_BY_TAG_PREFIX),
                new FindCommand(Arrays.asList(modA, modB), true));

        // Find by invalid tag: Treated as a keyword
        assertParseSuccess(parser,
                String.format("%s, %s, %s", modA, modB, INVALID_BY_TAG_PREFIX),
                new FindCommand(Arrays.asList(modA, modB, INVALID_BY_TAG_PREFIX), false));
    }

    @Test
    public void parse_validArgsForLectures_returnsFindCommand() {
        String lecA = "Week 1";
        String lecB = "Week 2";

        // Find by lecture name
        assertParseSuccess(parser,
                String.format("%s, %s", lecA, lecB) + MODULE_CODE_DESC_2103,
                new FindCommand(Arrays.asList(lecA, lecB), false));

        // Find by valid tag
        assertParseSuccess(parser,
                String.format("%s, %s, %s", lecA, lecB, VALID_BY_TAG_PREFIX) + MODULE_CODE_DESC_2103,
                new FindCommand(Arrays.asList(lecA, lecB), true));

        // Find by invalid tag
        assertParseSuccess(parser,
                String.format("%s, %s, %s", lecA, lecB, INVALID_BY_TAG_PREFIX) + MODULE_CODE_DESC_2103,
                new FindCommand(Arrays.asList(lecA, lecB, INVALID_BY_TAG_PREFIX), false));
    }

    @Test
    public void parse_validArgsForVideos_returnsFindCommand() {
        String vidA = "Vid1";
        String vidB = "Vid2";

        // Find by video name
        assertParseSuccess(parser,
                String.format("%s, %s", vidA, vidB)
                + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
                    new FindCommand(Arrays.asList(vidA, vidB), false));

        // Find by valid tag
        assertParseSuccess(parser,
                String.format("%s, %s, %s", vidA, vidB, VALID_BY_TAG_PREFIX)
                + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
                    new FindCommand(Arrays.asList(vidA, vidB), true));

        // Find by invalid tag
        assertParseSuccess(parser,
                String.format("%s, %s, %s", vidA, vidB, INVALID_BY_TAG_PREFIX)
                + MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
                    new FindCommand(Arrays.asList(vidA, vidB, INVALID_BY_TAG_PREFIX), false));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidFormat = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // No empty keywords
        assertParseFailure(parser, PREAMBLE_WHITESPACE, invalidFormat);

        // Keyword present, missing module code and invalid lecture name format
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INVALID_LECTURE_NAME_DESC, invalidFormat);

        // Keyword present, invalid module code format
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // Keyword present, valid module code present but invalid lecture name format
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_2103 + INVALID_LECTURE_NAME_DESC,
                LectureName.MESSAGE_CONSTRAINTS);
    }
}
