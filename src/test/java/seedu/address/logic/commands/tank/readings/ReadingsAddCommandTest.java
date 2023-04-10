package seedu.address.logic.commands.tank.readings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

class ReadingsAddCommandTest {

    private Tank tank = new Tank(new TankName("tank"), new AddressBook(), new UniqueIndividualReadingLevels());
    private AmmoniaLevel validAmmonia = new AmmoniaLevel("0.2", "01/04/2023 11:11", tank);
    private Temperature validTemp = new Temperature("25", "01/04/2023 11:11", tank);
    private PH validPH = new PH("7", "01/04/2023 11:11", tank);

    @Test
    public void constructor_nullTankIndex_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReadingsAddCommand(validAmmonia,
                validPH, validTemp, null));
    }

    @Test
    public void execute_addValidReadings_success() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        model.addReadingsToIndividualReadingLevels(validAmmonia, validPH, validTemp,
                model.getTankList().getTankList().get(0));
        Model actualModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        CommandResult result = new ReadingsAddCommand(validAmmonia, validPH, validTemp, Index.fromZeroBased(0))
                .execute(actualModel);
        assertEquals(String.format(ReadingsAddCommand.MESSAGE_SUCCESS, validAmmonia, validPH, validTemp),
                result.getFeedbackToUser());
    }

}
