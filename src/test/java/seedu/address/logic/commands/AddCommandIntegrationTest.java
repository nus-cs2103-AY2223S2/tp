package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());
    }

    @Test
    public void execute_newFish_success() {
        Fish validFish = new FishBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalTaskList());
        expectedModel.addFish(validFish);

        assertCommandSuccess(new AddCommand(validFish), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFish), expectedModel);
    }

    @Test
    public void execute_duplicateFish_throwsCommandException() {
        Fish fishInList = model.getAddressBook().getFishList().get(0);
        assertCommandFailure(new AddCommand(fishInList), model, AddCommand.MESSAGE_DUPLICATE_FISH);
    }

}
