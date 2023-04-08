package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.FindPredicate;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.model.variant.Variant;
import seedu.dengue.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BENSON = "Benson Meier";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CARL = "Carl Kurz";
    public static final String VALID_NAME_DANIEL = "Daniel Meier";
    public static final String VALID_NAME_ELLE = "Elle Meyer";
    public static final String VALID_NAME_FIONA = "Fiona Kunz";
    public static final String VALID_NAME_GEORGE = "George Best";
    public static final String VALID_NAME_HOON = "Hoon Meier";
    public static final String VALID_NAME_IDA = "Ida Mueller";
    public static final String VALID_POSTAL_ALICE = "123456";
    public static final String VALID_POSTAL_AMY = "111111";
    public static final String VALID_POSTAL_BENSON = "234567";
    public static final String VALID_POSTAL_BOB = "222222";
    public static final String VALID_POSTAL_CARL = "654321";
    public static final String VALID_POSTAL_DANIEL = "816253";
    public static final String VALID_POSTAL_ELLE = "012345";
    public static final String VALID_POSTAL_FIONA = "432156";
    public static final String VALID_POSTAL_GEORGE = "321235";
    public static final String VALID_POSTAL_HOON = "435316";
    public static final String VALID_POSTAL_IDA = "613473";
    public static final String VALID_DATE_ALICE = "2023-03-05";
    public static final String VALID_DATE_AMY = "2001-01-01";
    public static final String VALID_DATE_BENSON = "2022-03-05";
    public static final String VALID_DATE_BOB = "2002-02-02";
    public static final String VALID_DATE_CARL = "2023-02-02";
    public static final String VALID_DATE_DANIEL = "2023-01-01";
    public static final String VALID_DATE_ELLE = "2022-12-31";
    public static final String VALID_DATE_FIONA = "2022-01-05";
    public static final String VALID_DATE_GEORGE = "2023-01-05";
    public static final String VALID_DATE_HOON = "1850-01-02";
    public static final String VALID_DATE_IDA = "1996-10-01";
    public static final String VALID_AGE_ALICE = "21";
    public static final String VALID_AGE_AMY = "11";
    public static final String VALID_AGE_BENSON = "85";
    public static final String VALID_AGE_BOB = "22";
    public static final String VALID_AGE_CARL = "35";
    public static final String VALID_AGE_DANIEL = "143";
    public static final String VALID_AGE_ELLE = "65";
    public static final String VALID_AGE_FIONA = "5";
    public static final String VALID_AGE_GEORGE = "12";
    public static final String VALID_AGE_HOON = "1";
    public static final String VALID_AGE_IDA = "27";
    public static final String VALID_VARIANT_DENV1_UPPERCASE = "DENV1";
    public static final String VALID_VARIANT_DENV2_UPPERCASE = "DENV2";
    public static final String VALID_VARIANT_DENV3_UPPERCASE = "DENV3";
    public static final String VALID_VARIANT_DENV4_UPPERCASE = "DENV4";
    public static final String VALID_VARIANT_DENV1_LOWERCASE = "denv4";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String POSTAL_DESC_AMY = " " + PREFIX_POSTAL + VALID_POSTAL_AMY;
    public static final String POSTAL_DESC_BOB = " " + PREFIX_POSTAL + VALID_POSTAL_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String VARIANT_DESC_DENV2 = " " + PREFIX_VARIANT + VALID_VARIANT_DENV2_UPPERCASE;
    public static final String VARIANT_DESC_DENV1 = " " + PREFIX_VARIANT + VALID_VARIANT_DENV1_UPPERCASE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_POSTAL_DESC = " " + PREFIX_POSTAL + "911a"; // 'a' not allowed in postals
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE; // empty string not allowed for age
    public static final String INVALID_VARIANT_DESC = " " + PREFIX_VARIANT + "severe"; // "severe" is not a variant

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPostal(VALID_POSTAL_AMY).withDate(VALID_DATE_AMY).withAddress(VALID_AGE_AMY)
                .withVariants(VALID_VARIANT_DENV2_UPPERCASE).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPostal(VALID_POSTAL_BOB).withDate(VALID_DATE_BOB).withAddress(VALID_AGE_BOB)
                .withVariants(VALID_VARIANT_DENV1_UPPERCASE, VALID_VARIANT_DENV2_UPPERCASE).build();
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
     * - the Dengue Hotspot Tracker, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        DengueHotspotTracker expectedDengueHotspotTracker = new DengueHotspotTracker(actualModel
                .getDengueHotspotTracker());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDengueHotspotTracker, actualModel.getDengueHotspotTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s Dengue Hotspot Tracker.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) throws ParseException {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> testName = Optional.of(person.getName());
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> emptyDateRange = ContinuousData.generateRange(new StartDate(emptyDate), new EndDate(emptyDate));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        model.updateFilteredPersonList(
                new FindPredicate(testName, emptySubPostal, emptyAge, emptyDate, emptyVariants,
                        emptyDateRange, emptyAgeRange));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
