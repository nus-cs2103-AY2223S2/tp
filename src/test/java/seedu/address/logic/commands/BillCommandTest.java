package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

class BillCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()-> new BillCommand(null));
    }

    @Test
    public void execute_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        BillCommand billCommand = new BillCommand(new Nric(VALID_NRIC_AMY));

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, ((Patient) targetPerson)
                .getPrescriptions().stream().map(prescription -> prescription.getCost().getValue())
                .reduce(Float.valueOf(0), (x, y) -> x + y));

        assertCommandSuccess(billCommand, model, expectedMessage, model);
    }
}
