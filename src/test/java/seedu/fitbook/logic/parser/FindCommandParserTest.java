package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.fitbook.commons.core.Messages.MESSAGE_NO_PREFIX;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.model.client.Client;
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
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "alex yeoh",
                String.format(MESSAGE_NO_PREFIX, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "b/911",
                String.format(MESSAGE_INVALID_PREFIX, FindCommand.PREFIX_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        List<String> nameKeywords = Arrays.asList("Alice");
        List<Predicate<Client>> namePredicate = new ArrayList<>();
        namePredicate.add(new NameContainsKeywordsPredicate(nameKeywords));
        FindCommand expectedFindNameCommand =
                new FindCommand(namePredicate);
        assertParseSuccess(parser, " n/Alice", expectedFindNameCommand);

        List<String> phoneKeywords = Arrays.asList("91234567");
        List<Predicate<Client>> phonePredicates = new ArrayList<>();
        phonePredicates.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        FindCommand expectedFindPhoneCommand =
                new FindCommand(phonePredicates);
        assertParseSuccess(parser, " p/91234567", expectedFindPhoneCommand);

        List<String> emailKeywords = Arrays.asList("aliceb@example.com");
        List<Predicate<Client>> emailPredicates = new ArrayList<>();
        emailPredicates.add(new EmailContainsKeywordsPredicate(emailKeywords));
        FindCommand expectedFindEmailCommand =
                new FindCommand(emailPredicates);
        assertParseSuccess(parser, " e/aliceb@example.com", expectedFindEmailCommand);

        List<String> addressKeywords = Arrays.asList("30 Winchester Avenue");
        List<Predicate<Client>> addressPredicates = new ArrayList<>();
        addressPredicates.add(new AddressContainsKeywordsPredicate(addressKeywords));
        FindCommand expectedFindAddressCommand =
                new FindCommand(addressPredicates);
        assertParseSuccess(parser, " a/30 Winchester Avenue", expectedFindAddressCommand);

        List<String> tagKeywords = Arrays.asList("friends");
        List<Predicate<Client>> tagPredicates = new ArrayList<>();
        tagPredicates.add(new TagContainsKeywordsPredicate(tagKeywords));
        FindCommand expectedFindTagCommand =
                new FindCommand(tagPredicates);
        assertParseSuccess(parser, " t/friends", expectedFindTagCommand);

        List<String> weightKeywords = Arrays.asList("40");
        List<Predicate<Client>> weightPredicates = new ArrayList<>();
        weightPredicates.add(new WeightContainsKeywordsPredicate(weightKeywords));
        FindCommand expectedFindWeightCommand =
                new FindCommand(weightPredicates);
        assertParseSuccess(parser, " w/40", expectedFindWeightCommand);

        List<String> genderKeywords = Arrays.asList("M");
        List<Predicate<Client>> genderPredicates = new ArrayList<>();
        genderPredicates.add(new GenderContainsKeywordsPredicate(genderKeywords));
        FindCommand expectedFindGenderCommand =
                new FindCommand(genderPredicates);
        assertParseSuccess(parser, " g/M", expectedFindGenderCommand);

        List<String> calorieKeywords = Arrays.asList("1000");
        List<Predicate<Client>> caloriePredicates = new ArrayList<>();
        caloriePredicates.add(new CalorieContainsKeywordsPredicate(calorieKeywords));
        FindCommand expectedFindCalorieCommand =
                new FindCommand(caloriePredicates);
        assertParseSuccess(parser, " cal/1000", expectedFindCalorieCommand);

        List<String> appointmentKeywords = Arrays.asList("12-12-2020");
        List<Predicate<Client>> appointmentPredicates = new ArrayList<>();
        appointmentPredicates.add(new AppointmentContainsKeywordsPredicate(appointmentKeywords));
        FindCommand expectedFindAppointmentCommand =
                new FindCommand(appointmentPredicates);
        assertParseSuccess(parser, " app/12-12-2020", expectedFindAppointmentCommand);
    }
}
