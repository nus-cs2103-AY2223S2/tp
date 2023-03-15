package seedu.address.logic.commands.tank;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.Tank;
import seedu.address.testutil.TankBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code TankAddCommand}.
 */
public class TankAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(), getTypicalTankList());
    }

    @Test
    public void execute_newTank_success() {
        Tank validTank = new TankBuilder().build();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                model.getTankList());
        expectedModel.addTank(validTank);

        assertCommandSuccess(new TankAddCommand(validTank), model,
                String.format(TankAddCommand.MESSAGE_SUCCESS, validTank), expectedModel);
    }

    @Test
    public void execute_duplicateTank_throwsCommandException() {
        Tank fishInList = model.getTankList().getTankList().get(0);
        assertCommandFailure(new TankAddCommand(fishInList), model, TankAddCommand.MESSAGE_DUPLICATE_TANK);
    }

}
