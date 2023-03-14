package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_AGE;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_DIGITS;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_RISKLEVEL;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_HAS_TAG;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TypicalVolunteers.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;

public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
    private final List<?> emptyList = Collections.emptyList();
    private final List<Predicate<Person>> listWithOnePersonPredicate =
            Collections.singletonList(PREDICATE_HAS_NAME);
    private final List<Predicate<Person>> listWithTwoPersonPredicate =
            Arrays.asList(PREDICATE_HAS_EMAIL, PREDICATE_HAS_NRIC);
    private final List<Predicate<Person>> listWithFourPersonPredicate =
            Arrays.asList(PREDICATE_HAS_ADDRESS, PREDICATE_HAS_TAG, PREDICATE_HAS_AGE, PREDICATE_HAS_DIGITS);
    private final List<Predicate<Person>> listWithAllPersonPredicate =
            Arrays.asList(PREDICATE_HAS_NAME, PREDICATE_HAS_ADDRESS, PREDICATE_HAS_NRIC,
                    PREDICATE_HAS_DIGITS, PREDICATE_HAS_AGE, PREDICATE_HAS_TAG, PREDICATE_HAS_EMAIL);
    private final List<Predicate<Elderly>> listWithAllElderlyOnlyPredicate =
            Collections.singletonList(PREDICATE_HAS_RISKLEVEL);

    @SuppressWarnings("unchecked")
    @Test
    public void equals() {
        FindCommand firstFindCommand = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);
        FindCommand secondFindCommand = new FindCommand(listWithAllPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);
        FindCommand thirdFindCommand = new FindCommand(listWithAllPersonPredicate,
                listWithAllElderlyOnlyPredicate, (List<Predicate<Volunteer>>) emptyList);

        // same object -> returns true
        assertEquals(firstFindCommand, firstFindCommand);
        assertEquals(secondFindCommand, secondFindCommand);
        assertEquals(thirdFindCommand, thirdFindCommand);

        // same values -> returns true
        FindCommand firstFindCommandCopy = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);
        assertEquals(firstFindCommand, firstFindCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstFindCommand);

        // null -> returns false
        assertNotEquals(null, firstFindCommand);

        // different predicate lists -> returns false
        assertNotEquals(firstFindCommand, secondFindCommand);
        assertNotEquals(firstFindCommand, thirdFindCommand);
        assertNotEquals(thirdFindCommand, secondFindCommand);
    }

    @Test
    public void execute_oneFieldSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 1, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // TODO: uncomment after updatefilteredPairList is done
        //assertEquals(Collections.emptyList(), model.getFilteredPairList());
    }

    @Test
    public void execute_twoFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 1, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithTwoPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // TODO: uncomment after updatefilteredPairList is done
        //assertEquals(Collections.emptyList(), model.getFilteredPairList());
    }

    @Test
    public void execute_fourFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 1, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithFourPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // TODO: uncomment after updatefilteredPairList is done
        //assertEquals(Collections.emptyList(), model.getFilteredPairList());
    }

    // TODO: all volunteer field specified after adding medical qualification attribute

    @Test
    public void execute_allElderlyFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 0, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithAllPersonPredicate,
                listWithAllElderlyOnlyPredicate, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(unused -> false);
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // TODO: uncomment after updatefilteredPairList is done
        //assertEquals(Collections.emptyList(), model.getFilteredPairList());
    }
}
