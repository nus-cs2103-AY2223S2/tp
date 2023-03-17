package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.QuestionContainsKeywordsPredicate;

public class MasterDeckParserTest {

    private final MasterDeckParser parser = new MasterDeckParser();

    @Test
    public void parseCommand_add() throws Exception { // edit in the future as deck needs to be selected
        // Card card = new CardBuilder().build();
        // AddCommand command = (AddCommand) parser.parseCommandWhenDeckNotSelected(CardUtil.getAddCommand(card));
        // assertEquals(new AddCommand(card), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommandWhenDeckNotSelected(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommandWhenDeckNotSelected(ClearCommand.COMMAND_WORD
                + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception { // edit in the future as deck needs to be selected
        // DeleteCommand command = (DeleteCommand) parser.parseCommandWhenDeckNotSelected(
        //        DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        // assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception { // edit in the future as deck needs to be selected
        // Card card = new CardBuilder().build();
        // EditCardDescriptor descriptor = new EditCardDescriptorBuilder(card).build();
        // EditCommand command = (EditCommand) parser.parseCommandWhenDeckNotSelected(EditCommand.COMMAND_WORD
        //        + " " + INDEX_FIRST_PERSON.getOneBased() + " "
        //        + CardUtil.getEditPersonDescriptorDetails(descriptor));
        // assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommandWhenDeckNotSelected(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommandWhenDeckNotSelected(ExitCommand.COMMAND_WORD
                + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommandWhenDeckNotSelected(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new QuestionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommandWhenDeckNotSelected(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommandWhenDeckNotSelected(HelpCommand.COMMAND_WORD
                + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommandWhenDeckNotSelected(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommandWhenDeckNotSelected(ListCommand.COMMAND_WORD
                + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommandWhenDeckNotSelected(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommandWhenDeckNotSelected("unknownCommand"));
    }
}
