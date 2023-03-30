package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_AVAILABLE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_BIRTHDATE;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_DIGITS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_MEDICAL_QUALIFICATION;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_REGION;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_RISKLEVEL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_SKILL_LEVEL;
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
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_NO_FIELD_PROVIDED + "\n" + FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<Predicate<Person>> sharedPredicateList = Arrays.asList(PREDICATE_HAS_NAME, PREDICATE_HAS_ADDRESS,
                PREDICATE_HAS_NRIC, PREDICATE_HAS_DIGITS, PREDICATE_HAS_BIRTHDATE,
                PREDICATE_HAS_EMAIL, PREDICATE_HAS_REGION,
                PREDICATE_HAS_TAG, PREDICATE_HAS_AVAILABLE_DATE);
        List<Predicate<Elderly>> elderlyPredicateList = Collections.singletonList(PREDICATE_HAS_RISKLEVEL);
        List<Predicate<Volunteer>> volunteerPredicateList =
                Arrays.asList(PREDICATE_HAS_MEDICAL_QUALIFICATION, PREDICATE_HAS_SKILL_LEVEL);
        List<Predicate<Volunteer>> onlyMedicalQualificationList = Arrays.asList(PREDICATE_HAS_MEDICAL_QUALIFICATION);

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(sharedPredicateList, elderlyPredicateList, Collections.emptyList());
        String userInput = " n/Alice Pauline a/123, Jurong West Ave 6, #08-111 ic/S9673908G p/94351253 "
                + "bd/1990-01-01 e/alice@example.com t/friends r/medium re/central dr/2023-05-01, 2023-05-12";
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // multiple whitespaces between fields
        userInput = " n/Alice Pauline        a/123, Jurong West Ave 6, #08-111     ic/S9673908G p/94351253 "
                + "bd/1990-01-01    e/alice@example.com t/friends    r/medium re/central   dr/2023-05-01, 2023-05-12";
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // fields in any order
        userInput = " r/medium n/Alice Pauline dr/2023-05-01, 2023-05-12 re/central ic/S9673908G "
                + "bd/1990-01-01 e/alice@example.com p/94351253 t/friends a/123, Jurong West Ave 6, #08-111";
        assertParseSuccess(parser, userInput, expectedFindCommand);

        // all possible fields
        expectedFindCommand = new FindCommand(sharedPredicateList, elderlyPredicateList, volunteerPredicateList);
        userInput = " n/Alice Pauline a/123, Jurong West Ave 6, #08-111 ic/S9673908G p/94351253 "
                + "bd/1990-01-01 e/alice@example.com t/friends r/medium re/central dr/2023-05-01, 2023-05-12 "
                + "mt/cpr,basic";

        // medical qualification without skill level
        expectedFindCommand = new FindCommand(Collections.emptyList(),
                Collections.emptyList(), onlyMedicalQualificationList);
        userInput = " mt/cpr";
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {
        // not dates
        String expectedCommandResult = AvailableDate.MESSAGE_CONSTRAINTS;
        String userInput = " dr/hello, world";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // single invalid value
        expectedCommandResult = AvailableDate.INVALID_NUMBER_OF_DATES;
        userInput = " dr/john";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // invalid start date
        expectedCommandResult = AvailableDate.INVALID_DATES_SPECIFIED;
        userInput = " dr/2023-02-31, 2023-05-14";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // invalid end date
        expectedCommandResult = AvailableDate.INVALID_DATES_SPECIFIED;
        userInput = " dr/2023-05-23, 2024-02-31";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // start date after end date
        expectedCommandResult = AvailableDate.INVALID_DATES_SPECIFIED;
        userInput = " dr/2025-05-23, 2024-05-14";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // invalid region
        expectedCommandResult = Region.MESSAGE_CONSTRAINTS;
        userInput = " re/south";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // invalid risk level
        expectedCommandResult = RiskLevel.MESSAGE_CONSTRAINTS;
        userInput = " r/extreme";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // invalid birth date
        expectedCommandResult = BirthDate.MESSAGE_CONSTRAINTS;
        userInput = " bd/19 july 2000";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty medical tag
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Medical tag name");
        userInput = " mt/  , basic";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty tag
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Tag");
        userInput = " t/    ";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty name
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Name");
        userInput = " n/";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty nric
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "NRIC");
        userInput = " ic/";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty phone number
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Phone number");
        userInput = " p/   ";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty address
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Address");
        userInput = " a/        ";
        assertParseFailure(parser, userInput, expectedCommandResult);

        // empty email
        expectedCommandResult = String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Email");
        userInput = " e/";
        assertParseFailure(parser, userInput, expectedCommandResult);
    }
}
