package bookopedia.logic.commands;

import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import bookopedia.model.parcel.Parcel;
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
        StringBuilder parcelsToPrint = new StringBuilder("");
        for (Parcel p : testTypicalPerson.getParcels()) {
            parcelsToPrint.append(p.toString());
            parcelsToPrint.append("\n");
        }
        //this is temporary again...
        assertTrue(outputFromTestView.getFeedbackToUser().equals(testTypicalPerson.getName().toString() + "\n"
                + testTypicalPerson.getAddress().toString() + "\n"
                + testTypicalPerson.getEmail().toString() + "\n"
                + testTypicalPerson.getPhone().toString() + "\n"
                + "Parcels:" + "\n"
                + parcelsToPrint + "\n"
                + testTypicalPerson.getDeliveryStatus().toString() + "\n"));
    }
}
