package bookopedia.logic.commands;

import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.UserPrefs;
import bookopedia.model.person.Person;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCommand(null));
    }

    @Test
    public void execute_withIndexGreaterThanSizeOfList_throwsCommandException() {
        List<Person> listOfTypicalPersons = model.getFilteredPersonList();
        Index invalidIndex = Index.fromZeroBased(listOfTypicalPersons.size());
        ViewCommand testView = new ViewCommand(invalidIndex);
        assertThrows(CommandException.class, () -> testView.execute(model));
    }

    @Test
    public void execute_withIndexWithinList_viewSuccessful() throws CommandException {
        List<Person> listOfTypicalPersons = model.getFilteredPersonList();
        Index validIndex = Index.fromZeroBased(0);
        Person testTypicalPerson = listOfTypicalPersons.get(validIndex.getZeroBased());
        ViewCommand testView = new ViewCommand(validIndex);
        CommandResult outputFromTestView = testView.execute(model);
        //this is temporary again...
        assertTrue(outputFromTestView.equals(new CommandResult(ViewCommand.MESSAGE_SUCCESS,
                false, false, true, testTypicalPerson, validIndex.getZeroBased())));
    }

    @Test
    public void execute_successMessageCorrect() throws CommandException {
        List<Person> listOfTypicalPersons = model.getFilteredPersonList();
        Index validIndex = Index.fromZeroBased(0);
        Person testTypicalPerson = listOfTypicalPersons.get(validIndex.getZeroBased());
        ViewCommand testView = new ViewCommand(validIndex);
        CommandResult outputFromTestView = testView.execute(model);
        assertEquals(outputFromTestView.getFeedbackToUser(), testView.MESSAGE_SUCCESS);
    }
}
