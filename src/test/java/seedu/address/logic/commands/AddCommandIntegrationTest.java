package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fish.Fish;
import seedu.address.testutil.FishBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(), getTypicalTankList(),
                getTypicalFullReadingLevels());
    }

    @Test
    public void execute_newFish_success() {
        Fish validFish = new FishBuilder().build();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(), getTypicalTankList(),
                getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.addFish(validFish);

        assertCommandSuccess(new FishAddCommand(validFish, Index.fromOneBased(1)), model,
                String.format(FishAddCommand.MESSAGE_SUCCESS, validFish), expectedModel);
    }

    @Test
    public void execute_duplicateFish_throwsCommandException() {
        Fish fishInList = model.getAddressBook().getFishList().get(0);
        assertCommandFailure(new FishAddCommand(fishInList, Index.fromOneBased(1)), model,
                FishAddCommand.MESSAGE_DUPLICATE_FISH);
    }

}
