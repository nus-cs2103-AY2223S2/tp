package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.ANG;
import static seedu.address.testutil.TypicalPersons.BART;
import static seedu.address.testutil.TypicalPersons.CLARK;
import static seedu.address.testutil.TypicalPersons.DONG;
import static seedu.address.testutil.TypicalPersons.EDWARD;
import static seedu.address.testutil.TypicalPersons.FORD;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;
import static seedu.address.testutil.TypicalUser.LINUS;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;


public class ViewCommandTest {

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());

    @Test
    public void execute_byIndex_correctPersonQueried() throws CommandException {
        ViewCommand command1 = new ViewCommand(null, new ContactIndex(2));
        ViewCommandResult commandResult1 = command1.execute(model);
        assertEquals(Optional.of(ANG), commandResult1.getDisplayPerson());

        ViewCommand command2 = new ViewCommand(null, new ContactIndex(3));
        ViewCommandResult commandResult2 = command2.execute(model);
        assertEquals(Optional.of(BART), commandResult2.getDisplayPerson());

        ViewCommand command3 = new ViewCommand(null, new ContactIndex(4));
        ViewCommandResult commandResult3 = command3.execute(model);
        assertEquals(Optional.of(CLARK), commandResult3.getDisplayPerson());
    }

    @Test
    public void execute_byName_correctPersonQueried() throws CommandException {
        ViewCommand command1 = new ViewCommand("Dong Yu Lang", null);
        ViewCommand command2 = new ViewCommand("Edward Richards", null);
        ViewCommand command3 = new ViewCommand("Ford Canning", null);

        ViewCommandResult result1 = command1.execute(model);
        ViewCommandResult result2 = command2.execute(model);
        ViewCommandResult result3 = command3.execute(model);

        assertEquals(Optional.of(DONG), result1.getDisplayPerson());
        assertEquals(Optional.of(EDWARD), result2.getDisplayPerson());
        assertEquals(Optional.of(FORD), result3.getDisplayPerson());

        assertNotEquals("No such name found!", result1.getFeedbackToUser());
    }

    @Test
    public void execute_noArguments_userQueried() throws CommandException {
        ViewCommand command = new ViewCommand(null, null);
        ViewCommandResult result = command.execute(model);
        assertEquals(Optional.of(LINUS), result.getDisplayPerson());
    }

    @Test
    public void execute_noSuchName_userProfileDisplayed() throws CommandException {
        ViewCommand command1 = new ViewCommand("Lagrange", null);
        ViewCommand command2 = new ViewCommand("Alan Turing", null);
        ViewCommand command3 = new ViewCommand("Pierre de Fermat", null);

        ViewCommandResult result1 = command1.execute(model);
        ViewCommandResult result2 = command2.execute(model);
        ViewCommandResult result3 = command3.execute(model);

        assertEquals(Optional.of(LINUS), result1.getDisplayPerson());
        assertEquals(Optional.of(LINUS), result2.getDisplayPerson());
        assertEquals(Optional.of(LINUS), result3.getDisplayPerson());
    }

    @Test
    public void execute_noSuchName_invalidMessageDisplayed() throws CommandException {
        ViewCommand command1 = new ViewCommand("Bolzano Weierstrass", null);
        ViewCommand command2 = new ViewCommand("Tony Hoare", null);
        ViewCommand command3 = new ViewCommand("John Nash", null);

        ViewCommandResult result1 = command1.execute(model);
        ViewCommandResult result2 = command2.execute(model);
        ViewCommandResult result3 = command3.execute(model);

        assertEquals("No such person found!", result1.getFeedbackToUser());
        assertEquals("No such person found!", result2.getFeedbackToUser());
        assertEquals("No such person found!", result3.getFeedbackToUser());
    }

    @Test
    public void compare_commandEquality_true() {
        ViewCommand command1 = new ViewCommand("Lisa Meitner", null);
        ViewCommand command2 = new ViewCommand("Lisa Meitner", null);
        assertEquals(command1, command2);
        assertEquals(command1, command1);
        assertEquals(command2, command2);
    }

    @Test
    public void compare_viewCommandInequality_false() {
        ViewCommand command1 = new ViewCommand("Lisa Meitner", null);
        ViewCommand command2 = new ViewCommand("Lisa Meitner", new ContactIndex(1));
        assertNotEquals(command1, command2);

        ViewCommand command3 = new ViewCommand(null, new ContactIndex(1));
        ViewCommand command4 = new ViewCommand(null, null);
        assertNotEquals(command3, command1);
        assertNotEquals(command3, command2);
        assertNotEquals(command3, command4);

        ViewCommand command5 = new ViewCommand("Marie Curie", null);
        ViewCommand command6 = new ViewCommand(null, new ContactIndex(2));
        assertNotEquals(command5, command1);
        assertNotEquals(command5, command1);
        assertNotEquals(command6, command2);
        assertNotEquals(command6, command1);
    }

    @Test
    public void compare_otherCommandInequality_false() {
        ViewCommand viewCommand = new ViewCommand("Lisa Meitner", null);
        AddCommand addCommand = new AddCommand(ALBERT);
        DeleteCommand deleteCommand = new DeleteCommand(new ContactIndex(1));
        assertNotEquals(addCommand, viewCommand);
        assertNotEquals(deleteCommand, viewCommand);

    }

    @Test
    public void compare_foreignObjectInequality_false() {
        ViewCommand viewCommand = new ViewCommand("Lisa Meitner", null);
        assertNotEquals("Hello", viewCommand);
    }

}
