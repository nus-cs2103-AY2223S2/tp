package seedu.techtrack.logic.parser;

import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.techtrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.commands.CompanyCommand;
import seedu.techtrack.model.role.CompanyContainsKeywordsPredicate;

public class CompanyCommandParserTest {

    private CompanyCommandParser parser = new CompanyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CompanyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        CompanyCommand expectedCompanyCommand =
                new CompanyCommand(new CompanyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedCompanyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedCompanyCommand);
    }
}
