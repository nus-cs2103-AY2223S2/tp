package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.HMHero;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;

public class SummaryCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listHasApplicantsWithInterviewDate_outputsCorrectCalculation() {
        Model expectedModel = new ModelManager(new HMHero(model.getAddressBook()), new UserPrefs());
        SummaryCommand summaryCommand = new SummaryCommand();
        CommandResult expectedResult = summaryCommand.execute(expectedModel);
        assertCommandSuccess(summaryCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_listHasNoApplicantsWithInterview_showDefaultMessage() {
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(model);
        model.addPerson(TypicalPersons.ALICE);
        model.addPerson(TypicalPersons.DANIEL);
        SummaryCommand summaryCommand = new SummaryCommand();
        assertEquals(SummaryCommand.MESSAGE_NO_INTERVIEW, summaryCommand.getSuccessMessage(model));
    }
}
