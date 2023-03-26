package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_LISTED_OVERVIEW;
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
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;
import static seedu.address.testutil.TypicalElderly.BOB;
import static seedu.address.testutil.TypicalVolunteers.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;

public class FindCommandTest {
    private final Model model = getTypicalModelManager();
    private final Model expectedModel = getTypicalModelManager();
    private final List<?> emptyList = Collections.emptyList();
    private final List<Predicate<Person>> listWithOnePersonPredicate =
            Collections.singletonList(PREDICATE_HAS_NAME);
    private final List<Predicate<Person>> listWithTwoPersonPredicate =
            Arrays.asList(PREDICATE_HAS_EMAIL, PREDICATE_HAS_NRIC);
    private final List<Predicate<Person>> listWithFourPersonPredicate =
            Arrays.asList(PREDICATE_HAS_ADDRESS, PREDICATE_HAS_TAG, PREDICATE_HAS_BIRTHDATE, PREDICATE_HAS_DIGITS);
    private final List<Predicate<Person>> listWithAllPersonPredicate =
            Arrays.asList(PREDICATE_HAS_NAME, PREDICATE_HAS_ADDRESS, PREDICATE_HAS_NRIC,
                    PREDICATE_HAS_DIGITS, PREDICATE_HAS_BIRTHDATE, PREDICATE_HAS_TAG, PREDICATE_HAS_EMAIL,
                    PREDICATE_HAS_REGION, PREDICATE_HAS_AVAILABLE_DATE);
    private final List<Predicate<Elderly>> listWithAllElderlyOnlyPredicate =
            Collections.singletonList(PREDICATE_HAS_RISKLEVEL);
    private final List<Predicate<Volunteer>> listWithAllVolunteerOnlyPredicate =
            Arrays.asList(PREDICATE_HAS_MEDICAL_QUALIFICATION, PREDICATE_HAS_SKILL_LEVEL);

    @SuppressWarnings("unchecked")
    @Test
    public void equals() {
        FindCommand firstFindCommand = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, listWithAllVolunteerOnlyPredicate);
        FindCommand secondFindCommand = new FindCommand(listWithAllPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);
        FindCommand thirdFindCommand = new FindCommand(listWithAllPersonPredicate,
                listWithAllElderlyOnlyPredicate, listWithAllVolunteerOnlyPredicate);

        // same object -> returns true
        assertEquals(firstFindCommand, firstFindCommand);
        assertEquals(secondFindCommand, secondFindCommand);
        assertEquals(thirdFindCommand, thirdFindCommand);

        // same values -> returns true
        FindCommand firstFindCommandCopy = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, listWithAllVolunteerOnlyPredicate);
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
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 1, 1);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithOnePersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(pair ->
                pair.getElderly().getName().fullName.equals(ALICE.getName().fullName));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_twoFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 0, 0);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithTwoPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(unused -> false);
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_fourFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 1, 1);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithFourPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(pair ->
                pair.getElderly().getName().fullName.equals(ALICE.getName().fullName));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    // TODO: all volunteer field specified after adding medical qualification attribute

    @Test
    public void execute_allElderlyFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 0, 0);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithAllPersonPredicate,
                listWithAllElderlyOnlyPredicate, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(unused -> false);
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allVolunteerFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 0, 0);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand(listWithAllPersonPredicate,
                (List<Predicate<Elderly>>) emptyList, listWithAllVolunteerOnlyPredicate);

        expectedModel.updateFilteredElderlyList(unused -> false);
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> false);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_elderlyOnlyFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 7, 2, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand((List<Predicate<Person>>) emptyList,
                listWithAllElderlyOnlyPredicate, (List<Predicate<Volunteer>>) emptyList);

        expectedModel.updateFilteredElderlyList(elderly ->
                elderly.getRiskLevel().equals(BOB.getRiskLevel()));
        expectedModel.updateFilteredVolunteerList(unused -> true);
        expectedModel.updateFilteredPairList(unused -> true);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_volunteerOnlyFieldsSpecified_success() {
        String expectedMessage = String.format(MESSAGE_LISTED_OVERVIEW, 1, 7, 3);

        @SuppressWarnings("unchecked")
        FindCommand command = new FindCommand((List<Predicate<Person>>) emptyList,
                (List<Predicate<Elderly>>) emptyList, listWithAllVolunteerOnlyPredicate);

        expectedModel.updateFilteredElderlyList(unused -> true);
        expectedModel.updateFilteredVolunteerList(volunteer ->
                volunteer.getName().fullName.equals(ALICE.getName().fullName));
        expectedModel.updateFilteredPairList(unused -> true);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
