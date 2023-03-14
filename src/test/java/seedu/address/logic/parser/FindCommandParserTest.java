package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_AGE;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_DIGITS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_RISKLEVEL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<Predicate<Person>> sharedPredicateList = Arrays.asList(PREDICATE_HAS_NAME, PREDICATE_HAS_ADDRESS,
                PREDICATE_HAS_NRIC, PREDICATE_HAS_DIGITS, PREDICATE_HAS_AGE, PREDICATE_HAS_EMAIL, PREDICATE_HAS_TAG);
        List<Predicate<Elderly>> elderlyPredicateList = Collections.singletonList(PREDICATE_HAS_RISKLEVEL);

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(sharedPredicateList, elderlyPredicateList, Collections.emptyList());
        String userInput = " n/Alice Pauline a/123, Jurong West Ave 6, #08-111 e/alice@example.com "
                + "p/94351253 nr/S9673908G ag/20 t/friends r/medium";
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple whitespaces between fields
        userInput = " n/Alice Pauline     a/123, Jurong West Ave 6, #08-111 e/alice@example.com "
                + "p/94351253 nr/S9673908G          ag/20 t/friends        r/medium";
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }
}
