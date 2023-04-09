package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
import static seedu.dengue.testutil.TypicalPersons.AMISH;
import static seedu.dengue.testutil.TypicalPersons.AMY;
import static seedu.dengue.testutil.TypicalPersons.BECCA;
import static seedu.dengue.testutil.TypicalPersons.BENSON;
import static seedu.dengue.testutil.TypicalPersons.DANIEL;
import static seedu.dengue.testutil.TypicalPersons.FIONA;
import static seedu.dengue.testutil.TypicalPersons.HOON;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.predicate.FindPredicate;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.model.variant.Variant;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    private final Optional<Name> emptyName = Optional.empty();
    private final Optional<SubPostal> emptySubPostal = Optional.empty();
    private final Optional<Age> emptyAge = Optional.empty();
    private final Optional<Date> emptyDate = Optional.empty();
    private final Set<Variant> emptyVariants = new HashSet<>();
    private final Range<Date> emptyDateRange = ContinuousData.generateRange(
            new StartDate(Optional.empty()), new EndDate(Optional.empty()));
    private final Range<Age> emptyAgeRange = ContinuousData.generateRange(
            new StartAge(Optional.empty()), new EndAge(Optional.empty()));
    private Optional<Name> testName;
    private Optional<SubPostal> testSubPostal;
    private Optional<Age> testAge;
    private Optional<Date> testDate;
    private Set<Variant> testVariants;
    private Range<Date> testDateRange;
    private Range<Age> testAgeRange;

    public FindCommandTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        this.testName = Optional.of(new Name("ALICE"));
        this.testSubPostal = Optional.of(new SubPostal("s12"));
        this.testAge = Optional.of(new Age("21"));
        this.testDate = Optional.of(new Date("2023-03-05"));
        this.testVariants = new HashSet<>();
        testVariants.add(new Variant("DENV1"));
        this.testDateRange = ContinuousData.generateRange(
                new StartDate(Optional.of(new Date("2023-03-04"))),
                new EndDate(Optional.of(new Date("2023-03-05"))));
        this.testAgeRange = ContinuousData.generateRange(
                new StartAge(Optional.of(new Age("20"))),
                new EndAge(Optional.of(new Age("22"))));
    }

    //Testing each prefix no one is found

    @Test
    public void execute_prefixName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        testName = Optional.of(new Name("abcdef"));
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixPostal_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        testSubPostal = Optional.of(new SubPostal("s910233"));
        FindPredicate predicate = new FindPredicate(
                emptyName, testSubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixDate_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        testDate = Optional.of(new Date("1980-12-02"));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, testDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixAge_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        testAge = Optional.of(new Age("2"));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, testAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixMultipleVariants_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        HashSet<Variant> testVariants = new HashSet<>();
        testVariants.add(new Variant("DENV4"));
        testVariants.add(new Variant("DENV3"));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, testVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    //Testing each prefix one person found

    @Test
    public void execute_prefixName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixPostal_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, testSubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixDate_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, testDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixOneVariant_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, testVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixMultipleVariants_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        HashSet<Variant> testVariants = new HashSet<>();
        testVariants.add(new Variant("DENV2"));
        testVariants.add(new Variant("DENV1"));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, testVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(AMISH);
        model.addPerson(AMISH);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMISH), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixAge_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, testAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixDateRange_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, testAge, emptyDate, emptyVariants, testDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixAgeRange_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, testAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    //Testing each prefix multiple persons found

    @Test
    public void execute_prefixName_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Optional<Name> testName = Optional.of(new Name("Meier"));
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixPostal_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Optional<SubPostal> testSubPostal = Optional.of(new SubPostal("43"));
        FindPredicate predicate = new FindPredicate(
                emptyName, testSubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(HOON);
        model.addPerson(HOON);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, HOON), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixDate_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, testDate, emptyVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(AMY);
        model.addPerson(AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AMY), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixAge_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, testAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(AMY);
        model.addPerson(AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AMY), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixOneVariant_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, testVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(AMISH);
        model.addPerson(AMISH);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AMISH), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixMultipleVariants_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        HashSet<Variant> testVariants = new HashSet<>();
        testVariants.add(new Variant("DENV2"));
        testVariants.add(new Variant("DENV1"));
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, testVariants, emptyDateRange, emptyAgeRange);
        expectedModel.addPerson(AMISH);
        model.addPerson(AMISH);
        expectedModel.addPerson(BECCA);
        model.addPerson(BECCA);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMISH, BECCA), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixDateRange_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, testDateRange, emptyAgeRange);
        expectedModel.addPerson(AMY);
        model.addPerson(AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AMY), model.getFilteredPersonList());
    }

    @Test
    public void execute_prefixAgeRange_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicate predicate = new FindPredicate(
                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, testAgeRange);
        expectedModel.addPerson(AMY);
        model.addPerson(AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, AMY), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePrefixes_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPredicate predicate = new FindPredicate(
                testName, testSubPostal, testAge, testDate, testVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }
}
