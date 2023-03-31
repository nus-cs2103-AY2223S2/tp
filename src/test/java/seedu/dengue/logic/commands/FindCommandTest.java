package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.dengue.testutil.TypicalPersons.ELLE;
//import static seedu.dengue.testutil.TypicalPersons.FIONA;
//import static seedu.dengue.testutil.TypicalPersons.GEORGE;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

//import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Test
    public void execute_oneKeywords_noPersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Optional<SubPostal> emptySubPostal = Optional.empty();
        Optional<Name> testName = Optional.of(new Name("abcdefghijklmnop"));
        Optional<Age> emptyAge = Optional.empty();
        Optional<Date> emptyDate = Optional.empty();
        Set<Variant> emptyVariants = new HashSet<>();
        Range<Date> emptyDateRange = ContinuousData.generateRange(new StartDate(emptyDate), new EndDate(emptyDate));
        Range<Age> emptyAgeRange = ContinuousData.generateRange(new StartAge(emptyAge), new EndAge(emptyAge));
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    //    @Test
    //    public void execute_multipleKeywords_multiplePersonsFound() {
    //        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //        Optional<SubPostal> testSubPostal = Optional.of(new SubPostal("94"));
    //        Optional<Name> emptyName = Optional.empty();
    //        Optional<Age> emptyAge = Optional.empty();
    //        Optional<Date> emptyDate = Optional.empty();
    //        Set<Variant> emptyVariants = new HashSet<>();
    //        FindPredicate predicate = new FindPredicate(
    //                emptyName, testSubPostal, emptyAge, emptyDate, emptyVariants);
    //        FindCommand command = new FindCommand(predicate);
    //        expectedModel.updateFilteredPersonList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    //    }
}
