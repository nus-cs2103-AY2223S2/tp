package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StateHistory;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Tag;

public class RedoCommandTest {

    @Test
    public void execute_noCommands_success() {
        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, 0, 1);
        Model model = new ModelManager();
        StateHistory history = new StateHistory(model);
        Model expectedModel = new ModelManager();
        RedoCommand redoCommand = new RedoCommand(1);
        redoCommand.setStateHistory(history);
        assertCommandSuccess(redoCommand, model, new CommandResult(expectedMessage, false, false), expectedModel);
    }

    @Test
    public void execute_addDeleteCommands_success() throws CommandException {
        Command[] commands = new Command[6];
        commands[0] = new AddCommand(DANIEL);
        commands[1] = new AddCommand(GEORGE);
        commands[2] = new AddCommand(ALICE);
        commands[3] = new DeleteCommand(Index.fromZeroBased(0));
        commands[4] = new AddCommand(CARL);
        commands[5] = new AddCommand(ELLE);

        Model expectedModel = new ModelManager();
        for (int i = 0; i <= commands.length; i++) {
            String expectedMessage =
                    String.format(RedoCommand.MESSAGE_SUCCESS, i, i);

            Model model = new ModelManager();
            StateHistory history = new StateHistory(model);
            for (Command command : commands) {
                CommandResult rs = command.execute(model);
                history.offerCommand(command, model, rs);
            }
            history.undo(commands.length);
            RedoCommand redoCommand = new RedoCommand(i);
            redoCommand.setStateHistory(history);
            assertCommandSuccess(redoCommand, model, new CommandResult(expectedMessage, false, false), expectedModel);

            if (i < commands.length) {
                commands[i].execute(expectedModel);
            }
        }
    }

    private Command[] predicateCommands() {
        Command[] commands = new Command[18];
        commands[0] = new AddCommand(DANIEL.deepCopy());
        commands[1] = new AddCommand(GEORGE.deepCopy());
        commands[2] = new FindCommand(new NameContainsKeywordsPredicate(Collections.singletonList("GEORGE")));
        commands[3] = new AddCommand(ALICE.deepCopy());
        commands[4] = new DeleteCommand(Index.fromZeroBased(0));
        commands[5] = new AddCommand(CARL.deepCopy());
        commands[6] = new ListCommand();
        commands[7] = new AddCommand(FIONA.deepCopy());
        commands[8] = new AddCommand(ELLE.deepCopy());
        commands[9] = new FilterCommand(new FieldsMatchRegexPredicate(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList("friends")
        ));
        commands[10] = new FreezeCommand();
        commands[11] = new AddCommand(BENSON.deepCopy());
        commands[12] = new ListCommand();
        commands[13] = new TagCommand(Index.fromZeroBased(3), new Tag("friends"));
        commands[14] = new FilterCommand(new FieldsMatchRegexPredicate(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList("friends")
        ));
        commands[15] = new FreezeCommand();
        commands[16] = new DeleteTagCommand(Index.fromZeroBased(2), new Tag("friends"));
        commands[17] = new UnfreezeCommand();
        return commands;
    }

    @Test
    public void execute_predicateCommands_success() throws CommandException {
        Command[] referenceCommands = predicateCommands();

        Model expectedModel = new ModelManager();
        for (int i = 0; i <= referenceCommands.length; i++) {
            Command[] commands = predicateCommands();

            String expectedMessage =
                    String.format(RedoCommand.MESSAGE_SUCCESS, i, i);

            Model model = new ModelManager();
            StateHistory history = new StateHistory(model);
            for (Command command : commands) {
                CommandResult rs = command.execute(model);
                history.offerCommand(command, model, rs);
            }
            history.undo(commands.length);
            RedoCommand redoCommand = new RedoCommand(i);
            redoCommand.setStateHistory(history);
            assertCommandSuccess(redoCommand, model, new CommandResult(expectedMessage, false, false), expectedModel);

            if (i < referenceCommands.length) {
                referenceCommands[i].execute(expectedModel);
            }
        }
    }

}
