package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODEPREFIX_DESC_CS;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_CS1101S;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.CodePrefix;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.ModuleCodePredicate;
import seedu.modtrek.model.module.SemYear;
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
                new FindCommand(new ModuleCodePredicate(true, "CS1101S", new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()), new ArrayList<>());
        assertParseSuccess(parser, "CS1101S", expectedFindCommand);
    }

    @Test
    public void parse_validArgsModuleCodePrefix_returnsFindCommand() {
        HashSet<String> codePrefixes = new HashSet<>();
        codePrefixes.add("CS");
        List<String> filtersList = new ArrayList<>();
        filtersList.add("/m CS");
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodePredicate(false, "", codePrefixes,
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()), filtersList);
        assertParseSuccess(parser, CODEPREFIX_DESC_CS, expectedFindCommand);
    }

    @Test
    public void invalidFindByModuleCodeThrowsException() {
        String invalidFindCommandString = " CS";
        assertParseFailure(parser, invalidFindCommandString, Code.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgsAllPrefix_returnsFindCommand() {
        HashSet<String> codePrefixes = new HashSet<>();
        codePrefixes.add("CS");
        HashSet<Credit> credits = new HashSet<>();
        credits.add(new Credit("4"));
        HashSet<SemYear> semYears = new HashSet<>();
        semYears.add(new SemYear("Y1S1"));
        HashSet<Grade> grades = new HashSet<>();
        grades.add(new Grade("A"));
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("Computer Science Foundation"));
        List<String> filtersList = new ArrayList<>();
        filtersList.add("/m CS");
        filtersList.add("/c 4");
        filtersList.add("/y Y1S1");
        filtersList.add("/g A");
        filtersList.add("/t CSF");
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodePredicate(false, "",
                        codePrefixes, credits, semYears, grades, tags), filtersList);
        assertParseSuccess(parser, CODEPREFIX_DESC_CS + CREDIT_DESC_CS1101S
                + SEMYEAR_DESC_CS1101S + GRADE_DESC_CS1101S + TAG_DESC_CS1101S, expectedFindCommand);
    }

}
