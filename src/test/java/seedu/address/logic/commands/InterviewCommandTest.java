package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.HMHero;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class InterviewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allPersonsListed_success() {
        Model expectedModel = new ModelManager(new HMHero(model.getAddressBook()), new UserPrefs());
        InterviewCommand interviewCommand = new InterviewCommand();
        CommandResult expectedResult = interviewCommand.execute(expectedModel);
        assertCommandSuccess(interviewCommand, model, expectedResult, expectedModel);
    }

}
