package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.ContactContainsDescriptionPredicate;
import seedu.address.model.person.ContactContainsEmailPredicate;
import seedu.address.model.person.ContactContainsPhoneNumberPredicate;
import seedu.address.model.person.ContactContainsTagPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;


public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_multipleArgs_returnsError() {
        assertParseFailure(parser, "filter n/Alex t/family",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser does not work when multiple name arguments are given
     */
    @Test
    public void parse_multipleNameArgs_returnsError() {
        assertParseFailure(parser, "filter n/Alex n/Megan",
               String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser does not work when multiple phone arguments are given
     */
    @Test
    public void parse_multiplePhoneArgs_returnsError() {
        assertParseFailure(parser, "filter p/90069637 p/91899654",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser does not work when multiple email arguments are given
     */
    @Test
    public void parse_multipleEmailArgs_returnsError() {
        assertParseFailure(parser, "filter e/gg@gmail.com e/what@hotmail.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser does not work when multiple description arguments are given
     */
    @Test
    public void parse_multipleDescArgs_returnsError() {
        assertParseFailure(parser, "filter d/what is this d/no idea",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser works with a single name argument that can
     * either have 1 name or multiple different names
     */
    @Test
    public void parse_singleNameArg_returnsFilterCommand() {
        List<String> firstKeywordList = new ArrayList<String>(Arrays.asList("Alex"));
        List<String> secondKeywordList = new ArrayList<String>(Arrays.asList("Alex", "Bernice"));
        FilterCommand expectedFilterCommand = new FilterCommand(new NameContainsKeywordsPredicate(firstKeywordList));
        FilterCommand expectedSecondFilterCommand = new FilterCommand(
                new NameContainsKeywordsPredicate(secondKeywordList));
        assertParseSuccess(parser, "filter n/Alex", expectedFilterCommand);
        assertParseSuccess(parser, "filter n/Alex Bernice", expectedSecondFilterCommand);
    }

    /**
     * Test if the parser works with a single phone argument
     */
    @Test
    public void parse_singlePhoneArg_returnsFilterCommand() {
        FilterCommand expectedFilterCommand = new FilterCommand(
                new ContactContainsPhoneNumberPredicate("90069637"));
        assertParseSuccess(parser, "filter p/90069637", expectedFilterCommand);
    }

    /**
     * Test if the parser fails with a single phone argument if
     * multiple phone numbers are given
     */
    @Test
    public void parse_singlePhoneArgMultipleNum_returnsError() {
        assertParseFailure(parser, "filter p/90069637 p/99999999",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser works with a single email address
     */
    @Test
    public void parse_singleEmailArg_returnsFilterCommand() {
        FilterCommand expectedFilterCommand = new FilterCommand(
                new ContactContainsEmailPredicate("gg@gmail.com"));
        assertParseSuccess(parser, "filter e/gg@gmail.com", expectedFilterCommand);
    }

    /**
     * Test if the parser fails with a single email address
     * if multiple emails are given
     */
    @Test
    public void parse_singleEmailArgMultipleEmails_returnsError() {
        assertParseFailure(parser, "filter e/gg@gmail.com what@gmail.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Test if the parser works with a single description argument
     */
    @Test
    public void parse_singleDescriptionArg_returnsFilterCommand() {
        FilterCommand expectedFilterCommand = new FilterCommand(
                new ContactContainsDescriptionPredicate("Testing 123 hehe"));
        assertParseSuccess(parser, "filter d/Testing 123 hehe", expectedFilterCommand);
    }

    /**
     * Test if the parser works with multiple tags passed as arguments
     */
    @Test
    public void parse_multipleTagArgs_returnsFilterCommand() {
        Set<Tag> multipleTags = new HashSet<>();
        multipleTags.add(new Tag("family"));
        multipleTags.add(new Tag("friends"));
        FilterCommand expectedFilterCommand = new FilterCommand(new ContactContainsTagPredicate(multipleTags));
        assertParseSuccess(parser, "filter t/family t/friends", expectedFilterCommand);
    }

    /**
     * Test if the parser fails to run when no arguments are given
     */
    @Test
    public void parse_emptyArgs_returnsError() {
        assertParseFailure(parser, "filter ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
