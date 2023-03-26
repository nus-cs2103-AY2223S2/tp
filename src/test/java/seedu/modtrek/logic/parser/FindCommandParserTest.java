package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODEPREFIX_DESC_CS;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_CS1101S;
import static seedu.modtrek.logic.parser.CliSyntax.*;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.model.module.*;
import seedu.modtrek.model.tag.Tag;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgAfterPrefixCode_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_CODE, CodePrefix.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_emptyArgAfterPrefixCredit_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_CREDIT, Credit.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_emptyArgAfterPrefixSemYear_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_SEMYEAR, SemYear.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_emptyArgAfterPrefixGrade_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_GRADE, Grade.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_emptyArgAfterPrefixTag_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_TAG, Tag.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_validArgsModuleCode_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodePredicate("CS1101S",
                        "", "", "", new HashSet<>()));
        assertParseSuccess(parser, "CS1101S", expectedFindCommand);
    }

    @Test
    public void parse_validArgsModuleCodePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodePredicate("CS",
                        "", "", "", new HashSet<>()));
        assertParseSuccess(parser, CODEPREFIX_DESC_CS, expectedFindCommand);
    }

    @Test
    public void parse_validArgsAllPrefix_returnsFindCommand() {
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("Computer Science Foundation"));
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodePredicate("CS", "4", "Y1S1", "A", tags));
        assertParseSuccess(parser, CODEPREFIX_DESC_CS + CREDIT_DESC_CS1101S
                + SEMYEAR_DESC_CS1101S + GRADE_DESC_CS1101S + TAG_DESC_CS1101S, expectedFindCommand);
    }

}
