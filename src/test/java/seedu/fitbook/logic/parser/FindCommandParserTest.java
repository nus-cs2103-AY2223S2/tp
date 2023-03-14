package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_ARGS;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_PREFIX;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.model.client.predicate.AddressContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.AppointmentContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.CalorieContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.EmailContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.GenderContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.NameContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.PhoneContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.TagContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.WeightContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_NO_ARGS, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "alex yeoh",
                String.format(MESSAGE_NO_PREFIX, FindCommand.PREFIX_USAGE));
        assertParseFailure(parser, "b/911",
                String.format(MESSAGE_INVALID_PREFIX, FindCommand.PREFIX_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindNameCommand);

        FindCommand expectedFindPhoneCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("91234567")));
        assertParseSuccess(parser, "p/91234567", expectedFindPhoneCommand);

        FindCommand expectedFindEmailCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("aliceb@example.com")));
        assertParseSuccess(parser, "e/aliceb@example.com", expectedFindEmailCommand);

        FindCommand expectedFindAddressCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("30 Winchester Avenue")));
        assertParseSuccess(parser, "a/30 Winchester Avenue", expectedFindAddressCommand);

        FindCommand expectedFindTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends")));
        assertParseSuccess(parser, "t/friends", expectedFindTagCommand);

        FindCommand expectedFindWeightCommand =
                new FindCommand(new WeightContainsKeywordsPredicate(Arrays.asList("40")));
        assertParseSuccess(parser, "w/40", expectedFindWeightCommand);

        FindCommand expectedFindGenderCommand =
                new FindCommand(new GenderContainsKeywordsPredicate(Arrays.asList("M")));
        assertParseSuccess(parser, "g/M", expectedFindGenderCommand);

        FindCommand expectedFindCalorieCommand =
                new FindCommand(new CalorieContainsKeywordsPredicate(Arrays.asList("1000")));
        assertParseSuccess(parser, "cal/1000", expectedFindCalorieCommand);

        FindCommand expectedFindAppointmentCommand =
                new FindCommand(new AppointmentContainsKeywordsPredicate(Arrays.asList("12-12-2019")));
        assertParseSuccess(parser, "app/12-12-2019", expectedFindAppointmentCommand);
    }

}
