package seedu.fitbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_ROUTINE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.logic.commands.AddRoutineCommand;
import seedu.fitbook.logic.commands.ClearCommand;
import seedu.fitbook.logic.commands.DeleteCommand;
import seedu.fitbook.logic.commands.EditCommand;
import seedu.fitbook.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbook.logic.commands.EditRoutineCommand;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.logic.commands.ExitCommand;
import seedu.fitbook.logic.commands.ExportCommand;
import seedu.fitbook.logic.commands.FindCommand;
import seedu.fitbook.logic.commands.HelpCommand;
import seedu.fitbook.logic.commands.ListClientsCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.predicate.NameContainsKeywordsPredicate;
import seedu.fitbook.model.client.predicate.PhoneContainsKeywordsPredicate;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.testutil.client.ClientBuilder;
import seedu.fitbook.testutil.client.ClientUtil;
import seedu.fitbook.testutil.client.EditClientDescriptorBuilder;
import seedu.fitbook.testutil.routine.EditRoutineDescriptorBuilder;
import seedu.fitbook.testutil.routine.RoutineBuilder;
import seedu.fitbook.testutil.routine.RoutineUtil;

public class FitBookParserTest {

    private final FitBookParser parser = new FitBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddCommand(client), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_export() throws Exception {
        assertTrue(parser.parseCommand(ExportCommand.COMMAND_WORD) instanceof ExportCommand);
        assertTrue(parser.parseCommand(ExportCommand.COMMAND_WORD + " 3") instanceof ExportCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> nameKeywords = Arrays.asList("alex");
        List<String> phoneKeywords = Arrays.asList("123");
        List<Predicate<Client>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(nameKeywords));
        predicates.add(new PhoneContainsKeywordsPredicate(phoneKeywords));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + nameKeywords.stream().collect(Collectors.joining(" "))
                        + " p/" + phoneKeywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicates), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListClientsCommand.COMMAND_WORD) instanceof ListClientsCommand);
        assertTrue(parser.parseCommand(ListClientsCommand.COMMAND_WORD + " 3") instanceof ListClientsCommand);
    }

    @Test
    public void parseCommand_editRoutineForExercise() throws Exception {
        Routine routine = new RoutineBuilder().build();
        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder(routine).build();
        descriptor.setRoutineNameNull();
        EditRoutineCommand command = (EditRoutineCommand) parser.parseCommand(EditRoutineCommand.COMMAND_WORD
                + " "
                + INDEX_FIRST_ROUTINE.getOneBased()
                + " "
                + RoutineUtil.getEditRoutineDescriptorDetailsForExercise(descriptor));
        assertEquals(new EditRoutineCommand(INDEX_FIRST_ROUTINE, descriptor), command);
    }

    @Test
    public void parseCommand_editRoutineForRoutineName() throws Exception {
        Routine routine = new RoutineBuilder().build();
        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder(routine).build();
        descriptor.setExerciseNull();
        descriptor.setExerciseIndexNull();
        EditRoutineCommand command = (EditRoutineCommand) parser.parseCommand(EditRoutineCommand.COMMAND_WORD
                + " "
                + INDEX_FIRST_ROUTINE.getOneBased()
                + " "
                + RoutineUtil.getEditRoutineDescriptorDetailsForRoutineName(descriptor));
        assertEquals(new EditRoutineCommand(INDEX_FIRST_ROUTINE, descriptor), command);
    }

    @Test
    public void parseCommand_addRoutine() throws Exception {
        Routine routine = new RoutineBuilder().build();
        AddRoutineCommand command = (AddRoutineCommand) parser.parseCommand(RoutineUtil.getAddRoutineCommand(routine));
        assertEquals(new AddRoutineCommand(routine), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
