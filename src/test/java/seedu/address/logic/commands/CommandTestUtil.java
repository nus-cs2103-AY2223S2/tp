package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.BOB;
import static seedu.address.testutil.TypicalVolunteers.ALICE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.predicates.AddressContainsKeywordPredicate;
import seedu.address.model.person.predicates.AvailableDatesWithinRangePredicate;
import seedu.address.model.person.predicates.BirthDateEqualPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordPredicate;
import seedu.address.model.person.predicates.MedicalQualificationContainsKeywordPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordPredicate;
import seedu.address.model.person.predicates.PhoneContainsDigitsPredicate;
import seedu.address.model.person.predicates.RegionIsEqualPredicate;
import seedu.address.model.person.predicates.RiskLevelIsEqualPredicate;
import seedu.address.model.person.predicates.SkillLevelIsEqualPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordPredicate;
import seedu.address.testutil.EditDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_NRIC_AMY = "S1234567A";
    public static final String VALID_NRIC_BOB = "T2345678C";
    public static final String VALID_NRIC_CHARLIE = "T3456789D";

    public static final String VALID_BIRTH_DATE_AMY = "1980-03-01";
    public static final String VALID_BIRTH_DATE_BOB = "1950-04-01";

    public static final String VALID_REGION_AMY = "NORTH";
    public static final String VALID_REGION_BOB = "CENTRAL";

    public static final String VALID_RISK_LEVEL_AMY = "LOW";
    public static final String VALID_RISK_LEVEL_BOB = "MEDIUM";

    public static final String VALID_TAG_SINGLE = "single";

    public static final String VALID_START_DATE_ONE = "2022-02-11";
    public static final String VALID_END_DATE_ONE = "2022-02-15";

    public static final String VALID_START_DATE_TWO = "2023-02-11";
    public static final String VALID_END_DATE_TWO = "2023-02-15";

    public static final String VALID_TAG_STRONG = "strong";

    public static final String INVALID_NRIC = "S1234325252B"; // nric has exactly 8 characters

    public static final String INVALID_DATE = "0231-9312-12";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String BIRTH_DATE_DESC_AMY = " " + PREFIX_BIRTH_DATE + VALID_BIRTH_DATE_AMY;
    public static final String BIRTH_DATE_DESC_BOB = " " + PREFIX_BIRTH_DATE + VALID_BIRTH_DATE_BOB;

    public static final String REGION_DESC_AMY = " " + PREFIX_REGION + VALID_REGION_AMY;
    public static final String REGION_DESC_BOB = " " + PREFIX_REGION + VALID_REGION_BOB;

    public static final String RISK_DESC_AMY = " " + PREFIX_RISK + VALID_RISK_LEVEL_AMY;
    public static final String RISK_DESC_BOB = " " + PREFIX_RISK + VALID_RISK_LEVEL_BOB;

    public static final String TAG_DESC_SINGLE = " " + PREFIX_TAG + VALID_TAG_SINGLE;
    public static final String TAG_DESC_STRONG = " " + PREFIX_TAG + VALID_TAG_STRONG;

    public static final String AVAILABLE_DATES_ONE = " " + PREFIX_AVAILABILITY + VALID_START_DATE_ONE + ","
            + VALID_END_DATE_ONE;
    public static final String AVAILABLE_DATES_TWO = " " + PREFIX_AVAILABILITY + VALID_START_DATE_TWO + ","
            + VALID_END_DATE_TWO;

    public static final String INVALID_AVAILABLE_DATE = " " + PREFIX_AVAILABILITY + INVALID_DATE + ","
            + VALID_END_DATE_TWO;
    public static final String NRIC_ELDERLY_DESC_AMY = " " + PREFIX_NRIC_ELDERLY + VALID_NRIC_AMY;
    public static final String NRIC_ELDERLY_DESC_BOB = " " + PREFIX_NRIC_ELDERLY + VALID_NRIC_BOB;
    public static final String NRIC_VOLUNTEER_DESC_AMY = " " + PREFIX_NRIC_VOLUNTEER + VALID_NRIC_AMY;
    public static final String NRIC_VOLUNTEER_DESC_BOB = " " + PREFIX_NRIC_VOLUNTEER + VALID_NRIC_BOB;
    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_NRIC_DESC = " " + PREFIX_NRIC + INVALID_NRIC;
    public static final String INVALID_VOLUNTEER_NRIC_DESC = " " + PREFIX_NRIC_VOLUNTEER + INVALID_NRIC;
    public static final String INVALID_ELDERLY_NRIC_DESC = " " + PREFIX_NRIC_ELDERLY + INVALID_NRIC;
    public static final String INVALID_BIRTH_DATE_DESC = " " + PREFIX_BIRTH_DATE + "something";
    public static final String INVALID_REGION_DESC = " " + PREFIX_REGION + "south";
    public static final String INVALID_RISK_DESC = " " + PREFIX_RISK + "safe";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "single*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditDescriptor DESC_AMY;
    public static final EditDescriptor DESC_BOB;

    public static final NameContainsKeywordPredicate<Person> PREDICATE_HAS_NAME =
            new NameContainsKeywordPredicate<>(ALICE.getName().fullName);
    public static final EmailContainsKeywordPredicate<Person> PREDICATE_HAS_EMAIL =
            new EmailContainsKeywordPredicate<>(ALICE.getEmail().value);
    public static final PhoneContainsDigitsPredicate<Person> PREDICATE_HAS_DIGITS =
            new PhoneContainsDigitsPredicate<>(ALICE.getPhone().value);
    public static final AddressContainsKeywordPredicate<Person> PREDICATE_HAS_ADDRESS =
            new AddressContainsKeywordPredicate<>(ALICE.getAddress().value);
    public static final BirthDateEqualPredicate<Person> PREDICATE_HAS_BIRTHDATE =
            new BirthDateEqualPredicate<>(ALICE.getBirthDate().birthDate.toString());
    public static final NricContainsKeywordPredicate<Person> PREDICATE_HAS_NRIC =
            new NricContainsKeywordPredicate<>(ALICE.getNric().value);
    public static final RiskLevelIsEqualPredicate<Elderly> PREDICATE_HAS_RISKLEVEL =
            new RiskLevelIsEqualPredicate<>(BOB.getRiskLevel().riskStatus);
    public static final RegionIsEqualPredicate<Person> PREDICATE_HAS_REGION =
            new RegionIsEqualPredicate<>(ALICE.getRegion().region);
    public static final MedicalQualificationContainsKeywordPredicate<Volunteer> PREDICATE_HAS_MEDICAL_QUALIFICATION =
            new MedicalQualificationContainsKeywordPredicate<>(ALICE.getMedicalTags().iterator().next().tagName);
    public static final SkillLevelIsEqualPredicate<Volunteer> PREDICATE_HAS_SKILL_LEVEL =
            new SkillLevelIsEqualPredicate<>(ALICE.getMedicalTags().iterator().next().getQualificationLevel());
    public static final AvailableDatesWithinRangePredicate<Person> PREDICATE_HAS_AVAILABLE_DATE =
            new AvailableDatesWithinRangePredicate<>(ALICE.getAvailableDates().iterator().next().getStartDate(),
                    ALICE.getAvailableDates().iterator().next().getEndDate());
    public static final TagContainsKeywordPredicate<Person> PREDICATE_HAS_TAG =
            new TagContainsKeywordPredicate<>(ALICE.getTags().iterator().next().tagName);

    static {
        DESC_AMY = new EditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withNric(VALID_NRIC_AMY).withBirthDate(VALID_BIRTH_DATE_AMY).withRiskLevel(VALID_RISK_LEVEL_AMY)
                .withTags(VALID_TAG_STRONG).build();

        DESC_BOB = new EditDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withNric(VALID_NRIC_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).withRiskLevel(VALID_RISK_LEVEL_BOB)
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
     * Updates {@code model}'s filtered volunteer list to show only the volunteer at the given {@code targetIndex}
     * in the {@code model}'s FriendlyLink.
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
     * {@code model}'s FriendlyLink.
     */
    public static void showElderlyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredElderlyList().size());

        Elderly elderly = model.getFilteredElderlyList().get(targetIndex.getZeroBased());
        final String[] splitName = elderly.getName().fullName.split("\\s+");
        model.updateFilteredElderlyList(new NameContainsKeywordsPredicate<>(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredElderlyList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the pair at the given {@code targetIndex} in the
     * {@code model}'s FriendlyLink.
     */
    public static void showPairAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPairList().size());

        Pair pair = model.getFilteredPairList().get(targetIndex.getZeroBased());
        model.updateFilteredPairList(pairElement -> pairElement.equals(pair));

        assertEquals(1, model.getFilteredPairList().size());
    }
}
