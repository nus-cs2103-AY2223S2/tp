package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPersons.*;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        SortCommand sortFirstCommand = new SortCommand("NAME", "asc");
        SortCommand sortSecondCommand = new SortCommand("nAme", "Asc");

        sortFirstCommand.execute(model);
        ObservableList<Person> testModel =  model.getFilteredPersonList();

        sortSecondCommand.execute(expectedModel);
        ObservableList<Person> testSecondModel = expectedModel.getFilteredPersonList();

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        assertEquals(testModel, testSecondModel);

    }



    @Test
    public void execute_sort() {
        SortCommand command = new SortCommand("name", "desc");
        command.execute(model);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE), model.getFilteredPersonList());
    }


}
