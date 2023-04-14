package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SCHEDULED;
import static seedu.address.testutil.PersonWithTime.getWithTimeAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.TimeComparator;


public class ListTimeTest {
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getWithTimeAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_sortListByTime_success() {
        expectedModel.updateScheduledList(PREDICATE_SCHEDULED);
        assertCommandSuccess(new ListTime(), model, ListTime.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_timeOrder_success() {
        FilteredList<Person> filteredPersons =
            (FilteredList<Person>) expectedModel.getFilteredPersonList();
        filteredPersons.setPredicate(PREDICATE_SCHEDULED);
        SortedList<Person> newSortedList = new SortedList<>(filteredPersons, new TimeComparator());
        ObservableList<Person> persons =
            FXCollections.observableArrayList(expectedModel.getAddressBook().getPersonList());
        persons.setAll(newSortedList);
        model.updateScheduledList(PREDICATE_SCHEDULED);
        assertEquals(model.getFilteredPersonList(), new FilteredList<>(persons));
    }
}
