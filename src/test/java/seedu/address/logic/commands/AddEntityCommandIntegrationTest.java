package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.Entity;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddEntityCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Entity validEntity = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validEntity);

        assertCommandSuccess(new AddEntityCommand(validEntity), model,
            String.format(AddEntityCommand.MESSAGE_SUCCESS, validEntity), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Entity entityInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddEntityCommand(entityInList), model, AddEntityCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
