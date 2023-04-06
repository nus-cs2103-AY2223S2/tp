package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.MedicineContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiplePrefixes_throwsParseException() {
        assertParseFailure(parser, " n/ m/ Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_PREFIX_MESSAGE));
        assertParseFailure(parser, " n/ i/ Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_PREFIX_MESSAGE));
        assertParseFailure(parser, " n/ i/ m/ t/ tokyo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_PREFIX_MESSAGE));
        assertParseFailure(parser, " n/ i/ m/ Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_PREFIX_MESSAGE));
    }


    @Test
    public void parse_validArgs_returnsFindNameCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n n/Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindAddressCommand() throws ParseException {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new MedicineContainsKeywordsPredicate(Arrays.asList("Aspirin")));

        assertParseSuccess(parser, " m/Aspirin", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n m/Aspirin \n \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindNricCommand() {
        // no leading and trailing whitespaces
        FindCommand nricFindCommand =
                new FindCommand(new NricContainsKeywordsPredicate(Arrays.asList("S0123456D")));
        assertParseSuccess(parser, " i/S0123456D", nricFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n i/S0123456D \n \t", nricFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("Diabetic")));
        assertParseSuccess(parser, " t/Diabetic", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n t/Diabetic \n \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindDoctorCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new DoctorContainsKeywordsPredicate(Arrays.asList("Alex", "Ben")));
        assertParseSuccess(parser, " ad/Alex Ben", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n ad/Alex \n \t Ben  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " a/     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " a/    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " t/    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
