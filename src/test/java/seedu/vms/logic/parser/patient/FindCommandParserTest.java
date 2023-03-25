package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.commands.CommandTestUtil.FIND_AMY;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.patient.FindCommand;
import seedu.vms.logic.commands.patient.FindCommand.FindPatientDescriptor;
import seedu.vms.model.patient.predicates.NameContainsKeywordsPredicate;
import seedu.vms.testutil.FindPatientDescriptorBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameFlag_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice")));

        // name flag
        assertParseSuccess(parser, "--n Alice", expectedFindCommand);
    }

    @Test
    public void parse_validAllFlag_returnsFindCommand() {
        FindPatientDescriptor expectedFindPatientDescriptor = new FindPatientDescriptorBuilder(FIND_AMY).build();
        FindCommand expectedFindCommand = new FindCommand(expectedFindPatientDescriptor);

        // AMY Flags
        assertParseSuccess(parser, "--n Amy Bee --p 11111111 --d 1998-05-23 --b A+ --a gluten", expectedFindCommand);
    }
}
