package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.predicates.FullMatchKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_returnsFindCommand() {
        ArgumentMultimap emptyArgMultimap = ArgumentTokenizer.tokenize("");
        FindCommand expectedFindCommand = new FindCommand(new FullMatchKeywordsPredicate(emptyArgMultimap));
        assertParseSuccess(parser, "     ", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String args = " n/Alice n/Bob"; // needs leading space.
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_EDUCATION, PREFIX_REMARK, PREFIX_MODULE, PREFIX_TAG, PREFIX_TELEGRAM);
        FindCommand expectedFindCommand =
                new FindCommand(new FullMatchKeywordsPredicate(argMultimap));
        assertParseSuccess(parser, args, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, args.replaceAll(" ", " \n \t "), expectedFindCommand);
    }

}
