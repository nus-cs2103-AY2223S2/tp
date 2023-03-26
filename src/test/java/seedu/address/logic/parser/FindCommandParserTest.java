package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.PredicateKey;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.NAME, (Arrays.asList("Alice", "bob"))));
        assertParseSuccess(parser, " n/Alice n/bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.ADDRESS, Arrays.asList("Serangoon", "Central")));
        assertParseSuccess(parser, " a/Serangoon a/Central", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n a/Serangoon \n \t a/Central  \t", expectedFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.EMAIL, Arrays.asList("@gmail", ".com")));
        assertParseSuccess(parser, " e/@gmail e/.com", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n e/@gmail \n \t e/.com  \t", expectedFindCommand);
    }

    @Test
    public void parse_validGenderArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.GENDER, Arrays.asList("ma", "le")));
        assertParseSuccess(parser, " g/ma g/le", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n g/ma \n \t g/le  \t", expectedFindCommand);
    }

    @Test
    public void parse_validMajorArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.MAJOR, Arrays.asList("comsci", "psych")));
        assertParseSuccess(parser, " m/comsci m/psych", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n m/comsci \n \t m/psych  \t", expectedFindCommand);
    }

    @Test
    public void parse_validModulesArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.MODULES, Arrays.asList("CS2103T", "CS2040S")));
        assertParseSuccess(parser, " mt/CS2103T mt/CS2040S", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n mt/CS2103T \n \t mt/CS2040S  \t", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.PHONE, Arrays.asList("123123", "9542")));
        assertParseSuccess(parser, " p/123123 p/9542", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n p/123123 \n \t p/9542  \t", expectedFindCommand);
    }

    @Test
    public void parse_validRaceArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.RACE, Arrays.asList("Chi", "Malay")));
        assertParseSuccess(parser, " r/Chi r/Malay", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n r/Chi \n \t r/Malay  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate()
                        .withField(PredicateKey.TAG, Arrays.asList("123123", "9542")));
        assertParseSuccess(parser, " t/123123 t/9542", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/123123 \n \t t/9542  \t", expectedFindCommand);
    }
}
