package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;

public class DeleteAllCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_deleteAll_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteAllCommand.MESSAGE_SUCCESS, ResultType.HOME);
        assertCommandSuccess(new DeleteAllCommand(DeleteAllCommand.CONFIRMATION_CODE),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_wrongCode_throwsCommandException() {
        DeleteAllCommand deleteAllCommand = new DeleteAllCommand("hello");
        assertThrows(CommandException.class, DeleteAllCommand.MESSAGE_INCORRECT_CODE, () ->
                deleteAllCommand.execute(model));
    }

}
