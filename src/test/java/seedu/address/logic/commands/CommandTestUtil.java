package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.BOB;
import static seedu.address.testutil.TypicalVolunteers.ALICE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditElderlyCommand.EditElderlyDescriptor;
import seedu.address.logic.commands.EditVolunteerCommand.EditVolunteerDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.predicates.AddressContainsKeywordPredicate;
import seedu.address.model.person.predicates.AgeIsEqualPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordPredicate;
import seedu.address.model.person.predicates.PhoneContainsDigitsPredicate;
import seedu.address.model.person.predicates.RiskLevelIsEqualPredicate;
import seedu.address.model.person.predicates.TagIsEqualPredicate;
import seedu.address.testutil.EditElderlyDescriptorBuilder;
import seedu.address.testutil.EditVolunteerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHARLIE = "Charlie Tan";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_NRIC_AMY = "S1234567A";
    public static final String VALID_NRIC_BOB = "T2345678C";
    public static final String VALID_NRIC_CHARLIE = "T3456789D";

    public static final String VALID_AGE_AMY = "68";
    public static final String VALID_AGE_BOB = "85";

    public static final String VALID_RISKLEVEL_AMY = "LOW";
    public static final String VALID_RISKLEVEL_BOB = "MEDIUM";

    public static final String VALID_TAG_SINGLE = "single";
    public static final String VALID_TAG_STRONG = "strong";

    public static final String INVALID_NRIC = "S1234325252B"; // nric has exactly 8 characters

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;

    public static final String RISK_DESC_AMY = " " + PREFIX_RISK + VALID_RISKLEVEL_AMY;
    public static final String RISK_DESC_BOB = " " + PREFIX_RISK + VALID_RISKLEVEL_BOB;

    public static final String TAG_DESC_SINGLE = " " + PREFIX_TAG + VALID_TAG_SINGLE;
    public static final String TAG_DESC_STRONG = " " + PREFIX_TAG + VALID_TAG_STRONG;

    public static final String NRIC_ELDERLY_DESC_AMY = " " + PREFIX_NRIC_ELDERLY + VALID_NRIC_AMY;
    public static final String NRIC_ELDERLY_DESC_BOB = " " + PREFIX_NRIC_ELDERLY + VALID_NRIC_BOB;
    public static final String NRIC_VOLUNTEER_DESC_AMY = " " + PREFIX_NRIC_VOLUNTEER + VALID_NRIC_AMY;
    public static final String NRIC_VOLUNTEER_DESC_BOB = " " + PREFIX_NRIC_VOLUNTEER + VALID_NRIC_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_NRIC_DESC = " " + PREFIX_NRIC + INVALID_NRIC;
    public static final String INVALID_VOLUNTEER_NRIC_DESC = " " + PREFIX_NRIC_VOLUNTEER + INVALID_NRIC;
    public static final String INVALID_ELDERLY_NRIC_DESC = " " + PREFIX_NRIC_ELDERLY + INVALID_NRIC;
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "1835";
    public static final String INVALID_RISK_DESC = " " + PREFIX_RISK + "safe";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "single*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditElderlyDescriptor DESC_ELDERLY_AMY;
    public static final EditElderlyDescriptor DESC_ELDERLY_BOB;

    public static final EditVolunteerDescriptor DESC_VOLUNTEER_AMY;
    public static final EditVolunteerDescriptor DESC_VOLUNTEER_BOB;

    public static final NameContainsKeywordPredicate<Person> PREDICATE_HAS_NAME =
            new NameContainsKeywordPredicate<>(ALICE.getName().fullName);
    public static final EmailContainsKeywordPredicate<Person> PREDICATE_HAS_EMAIL =
            new EmailContainsKeywordPredicate<>(ALICE.getEmail().value);
    public static final PhoneContainsDigitsPredicate<Person> PREDICATE_HAS_DIGITS =
            new PhoneContainsDigitsPredicate<>(ALICE.getPhone().value);
    public static final AddressContainsKeywordPredicate<Person> PREDICATE_HAS_ADDRESS =
            new AddressContainsKeywordPredicate<>(ALICE.getAddress().value);
    public static final AgeIsEqualPredicate<Person> PREDICATE_HAS_AGE =
            new AgeIsEqualPredicate<>(ALICE.getAge().value);
    public static final NricContainsKeywordPredicate<Person> PREDICATE_HAS_NRIC =
            new NricContainsKeywordPredicate<>(ALICE.getNric().value);
    public static final RiskLevelIsEqualPredicate<Elderly> PREDICATE_HAS_RISKLEVEL =
            new RiskLevelIsEqualPredicate<>(BOB.getRiskLevel().riskStatus);
    public static final TagIsEqualPredicate<Person> PREDICATE_HAS_TAG =
            new TagIsEqualPredicate<>(ALICE.getTags().iterator().next().tagName);

    static {
        DESC_ELDERLY_AMY = new EditElderlyDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withNric(VALID_NRIC_AMY).withAge(VALID_AGE_AMY).withRiskLevel(VALID_RISKLEVEL_AMY)
                .withTags(VALID_TAG_STRONG).build();

        DESC_ELDERLY_BOB = new EditElderlyDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withNric(VALID_NRIC_BOB).withAge(VALID_AGE_BOB).withRiskLevel(VALID_RISKLEVEL_BOB)
                .withTags(VALID_TAG_SINGLE, VALID_TAG_STRONG).build();

        DESC_VOLUNTEER_AMY = new EditVolunteerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withNric(VALID_NRIC_AMY).withAge(VALID_AGE_AMY)
                .withTags(VALID_TAG_STRONG).build();

        DESC_VOLUNTEER_BOB = new EditVolunteerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withNric(VALID_NRIC_BOB).withAge(VALID_AGE_BOB)
                .withTags(VALID_TAG_SINGLE, VALID_TAG_STRONG).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered volunteer, elderly and pair lists in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FriendlyLink expectedFriendlyLink = new FriendlyLink(actualModel.getFriendlyLink());
        List<Elderly> expectedFilteredElderlyList = new ArrayList<>(actualModel.getFilteredElderlyList());
        List<Volunteer> expectedFilteredVolunteerList = new ArrayList<>(actualModel.getFilteredVolunteerList());
        List<Pair> expectedFilteredPairList = new ArrayList<>(actualModel.getFilteredPairList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFriendlyLink, actualModel.getFriendlyLink());
        assertEquals(expectedFilteredElderlyList, actualModel.getFilteredElderlyList());
        assertEquals(expectedFilteredVolunteerList, actualModel.getFilteredVolunteerList());
        assertEquals(expectedFilteredPairList, actualModel.getFilteredPairList());
    }

    /**
     * Updates {@code model}'s filtered volunteer list to show only the volunteer at the given {@code targetIndex}.
     */
    public static void showVolunteerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredVolunteerList().size());

        Volunteer volunteer = model.getFilteredVolunteerList().get(targetIndex.getZeroBased());
        final String[] splitName = volunteer.getName().fullName.split("\\s+");
        model.updateFilteredVolunteerList(new NameContainsKeywordsPredicate<>(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredVolunteerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showElderlyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredElderlyList().size());

        Elderly elderly = model.getFilteredElderlyList().get(targetIndex.getZeroBased());
        final String[] splitName = elderly.getName().fullName.split("\\s+");
        model.updateFilteredElderlyList(new NameContainsKeywordsPredicate<>(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredElderlyList().size());
    }
}
