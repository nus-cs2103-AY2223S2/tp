package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class DeleteMultipleIndexCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    @Test
    void execute_deleteMultipleValidIndex_success() {
        List<Person> list = model.getFilteredPersonList();
        int size = list.size();
        System.out.println(size);
        ArrayList<Index> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indexes.add(Index.fromZeroBased(i));
        }
        DeleteMultipleIndexCommand deleteCommand = new DeleteMultipleIndexCommand(indexes);

        String expectedMessage = String.format(DeleteMultipleIndexCommand.MESSAGE_DELETE_PERSON_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new UserData());
        expectedModel.deleteMultiplePersons(new ArrayList<>(list));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
