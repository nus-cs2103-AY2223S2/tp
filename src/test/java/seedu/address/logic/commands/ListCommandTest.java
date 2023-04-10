package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showElderlyAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPairAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showVolunteerAtIndex;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = getTypicalModelManager();
        expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(getTypicalFriendlyLink())
                .build();
    }

    @Test
    public void executeShowAll_listIsNotFiltered_showsSameList() {
        ListCommand listCommand = new ListCommand("");
        assertCommandSuccess(listCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeShowAll_listIsFiltered_showsEverything() {
        ListCommand listCommand = new ListCommand("");
        showElderlyAtIndex(model, INDEX_FIRST_PERSON);
        showVolunteerAtIndex(model, INDEX_FIRST_PERSON);
        showPairAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(listCommand, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeShowPaired_listsAreNotFiltered_showsFilteredPairedLists() {
        ListCommand listCommand = new ListCommand("paired");
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_LIST_PAIRED, 2, 3, 3);
        Predicate<Elderly> elderlyPredicate = elderly -> {
            String name = elderly.getName().fullName;
            return name.contains("Alice") || name.contains("Benson") || name.contains("Carl");
        };
        Predicate<Volunteer> volunteerPredicate = volunteer -> {
            String name = volunteer.getName().fullName;
            return name.contains("Daniel") || name.contains("Elle");
        };
        expectedModel.updateFilteredElderlyList(elderlyPredicate);
        expectedModel.updateFilteredVolunteerList(volunteerPredicate);
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeShowPaired_listsAreFiltered_showsFilteredPairedLists() {
        ListCommand listCommand = new ListCommand("paired");
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_LIST_PAIRED, 2, 3, 3);

        showElderlyAtIndex(model, INDEX_FIRST_PERSON);
        showVolunteerAtIndex(model, INDEX_FIRST_PERSON);
        showPairAtIndex(model, INDEX_FIRST_PERSON);

        Predicate<Elderly> elderlyPredicate = elderly -> {
            String name = elderly.getName().fullName;
            return name.contains("Alice") || name.contains("Benson") || name.contains("Carl");
        };
        Predicate<Volunteer> volunteerPredicate = volunteer -> {
            String name = volunteer.getName().fullName;
            return name.contains("Daniel") || name.contains("Elle");
        };
        expectedModel.updateFilteredElderlyList(elderlyPredicate);
        expectedModel.updateFilteredVolunteerList(volunteerPredicate);
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeShowUnpaired_listsAreNotFiltered_showsFilteredUnpairedLists() {
        ListCommand listCommand = new ListCommand("unpaired");
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_LIST_UNPAIRED, 5, 4, 3);
        Predicate<Elderly> elderlyPredicate = elderly -> {
            String name = elderly.getName().fullName;
            return !(name.contains("Alice") || name.contains("Benson") || name.contains("Carl"));
        };
        Predicate<Volunteer> volunteerPredicate = volunteer -> {
            String name = volunteer.getName().fullName;
            return !(name.contains("Daniel") || name.contains("Elle"));
        };
        expectedModel.updateFilteredElderlyList(elderlyPredicate);
        expectedModel.updateFilteredVolunteerList(volunteerPredicate);
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeShowUnpaired_listsAreFiltered_showsFilteredUnpairedLists() {
        ListCommand listCommand = new ListCommand("unpaired");
        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_LIST_UNPAIRED, 5, 4, 3);

        showElderlyAtIndex(model, INDEX_FIRST_PERSON);
        showVolunteerAtIndex(model, INDEX_FIRST_PERSON);
        showPairAtIndex(model, INDEX_FIRST_PERSON);

        Predicate<Elderly> elderlyPredicate = elderly -> {
            String name = elderly.getName().fullName;
            return !(name.contains("Alice") || name.contains("Benson") || name.contains("Carl"));
        };
        Predicate<Volunteer> volunteerPredicate = volunteer -> {
            String name = volunteer.getName().fullName;
            return !(name.contains("Daniel") || name.contains("Elle"));
        };
        expectedModel.updateFilteredElderlyList(elderlyPredicate);
        expectedModel.updateFilteredVolunteerList(volunteerPredicate);
        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }
}
