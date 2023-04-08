package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.commands.FindCommand;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


    /* Tests the parser when there is no prefix specified for find command. */
    @Test
    public void parse_validArgsWithoutPrefix_returnsFindCommand() {
        // No leading and trailing whitespaces for Company Name keywords
        FindCommand expectedFindCommandForCompanyName =
                new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList("Google", "Meta")));
        assertParseSuccess(parser, "Google Meta", expectedFindCommandForCompanyName);

        // Role keywords
        FindCommand expectedFindCommandForRole =
                new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList("SWE", "Intern")));
        assertParseSuccess(parser, "SWE Intern", expectedFindCommandForRole);

        // Status keywords
        FindCommand expectedFindCommandForStatus =
                new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList("Offered", "Interested")));
        assertParseSuccess(parser, "Offered Interested", expectedFindCommandForStatus);

        // Role, Status and Company Name keywords
        FindCommand expectedFindCommandForRoleStatusAndCompany =
                new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList("Offered", "SWE", "Google")));
        assertParseSuccess(parser, "Offered SWE Google", expectedFindCommandForRoleStatusAndCompany);

        // Multiple whitespaces between keywords
        FindCommand expectedFindCommandWithWhitespaces =
                new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList("Offered", "SWE", "Google")));
        assertParseSuccess(parser, " \n Offered \n \t SWE  \n Google  \t", expectedFindCommandWithWhitespaces);
    }

    /* Tests the parser when there is/are prefix(es) specified for find command. */
    @Test
    public void parse_validArgsWithPrefix_returnsFindCommand() {
        List<String> role = Arrays.asList("Intern");
        List<String> roles = Arrays.asList("Testing", "Intern");

        List<String> companyName = Arrays.asList("Amazon");
        List<String> companyNames = Arrays.asList("Amazon", "Meta");

        List<String> status = Arrays.asList("Interested");
        List<String> statuses = Arrays.asList("Interested", "Offered");

        HashMap<Prefix, List<String>> prefixRoleMap = new HashMap<>() {
            {
                put(PREFIX_ROLE, role);
            }
        };
        HashMap<Prefix, List<String>> prefixCompanyNameMap = new HashMap<>() {
            {
                put(PREFIX_COMPANY_NAME, companyName);
            }
        };
        HashMap<Prefix, List<String>> prefixStatusMap = new HashMap<>() {
            {
                put(PREFIX_STATUS, status);
            }
        };
        HashMap<Prefix, List<String>> prefixRolesMap = new HashMap<>() {
            {
                put(PREFIX_ROLE, roles);
            }
        };
        HashMap<Prefix, List<String>> prefixCompanyNamesMap = new HashMap<>() {
            {
                put(PREFIX_COMPANY_NAME, companyNames);
            }
        };
        HashMap<Prefix, List<String>> prefixStatusesMap = new HashMap<>() {
            {
                put(PREFIX_STATUS, statuses);
            }
        };
        HashMap<Prefix, List<String>> multiplePrefixesMap = new HashMap<>() {
            {
                put(PREFIX_ROLE, roles);
                put(PREFIX_STATUS, statuses);
                put(PREFIX_COMPANY_NAME, companyNames);
            }
        };

        // Find Role keyword
        FindCommand expectedFindCommandForRolePrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixRoleMap));
        assertParseSuccess(parser, " r/Intern", expectedFindCommandForRolePrefix);

        // Find Company Name keyword
        FindCommand expectedFindCommandForCompanyNamePrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixCompanyNameMap));
        assertParseSuccess(parser, " c/Amazon", expectedFindCommandForCompanyNamePrefix);

        // Find Status keyword
        FindCommand expectedFindCommandForStatusPrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixStatusMap));
        assertParseSuccess(parser, " s/Interested", expectedFindCommandForStatusPrefix);

        // Find Role keywords
        FindCommand expectedFindCommandForRolesPrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixRolesMap));
        assertParseSuccess(parser, " r/Testing Intern", expectedFindCommandForRolesPrefix);

        // Find Company Name keywords
        FindCommand expectedFindCommandForCompanyNamesPrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixCompanyNamesMap));
        assertParseSuccess(parser, " c/Amazon Meta", expectedFindCommandForCompanyNamesPrefix);

        // Find Status keywords
        FindCommand expectedFindCommandForStatusesPrefix =
                new FindCommand(new ApplicationContainsKeywordsPredicate(prefixStatusesMap));
        assertParseSuccess(parser, " s/Interested Offered", expectedFindCommandForStatusesPrefix);

        // Find Roles, Company Names and Statuses
        FindCommand expectedFindCommandForMultiplePrefixes =
                new FindCommand(new ApplicationContainsKeywordsPredicate(multiplePrefixesMap));
        assertParseSuccess(parser, " r/Testing Intern c/Amazon Meta s/Interested Offered",
                expectedFindCommandForMultiplePrefixes);
    }
}
