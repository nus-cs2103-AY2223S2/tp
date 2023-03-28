package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MasterDeckParserTest {

    private final MasterDeckParser parser = new MasterDeckParser();

    @Test
    public void parseCommand_add() throws Exception { // edit in the future as deck needs to be selected
        // Card card = new CardBuilder().build();
        // AddCardCommand command = (AddCardCommand)
        // parser.parseCommandWhenDeckNotSelected(CardUtil.getAddCommand(card));
        // assertEquals(new AddCardCommand(card), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommandInMainUnselectedMode(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommandInMainUnselectedMode(ClearCommand.COMMAND_WORD
                + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception { // edit in the future as deck needs to be selected
        // DeleteCardCommand command = (DeleteCardCommand) parser.parseCommandWhenDeckNotSelected(
        //        DeleteCardCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        // assertEquals(new DeleteCardCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception { // edit in the future as deck needs to be selected
        // Card card = new CardBuilder().build();
        // EditCardDescriptor descriptor = new EditCardDescriptorBuilder(card).build();
        // EditCardCommand command =
        // (EditCardCommand) parser.parseCommandWhenDeckNotSelected(EditCardCommand.COMMAND_WORD
        //        + " " + INDEX_FIRST_PERSON.getOneBased() + " "
        //        + CardUtil.getEditPersonDescriptorDetails(descriptor));
        // assertEquals(new EditCardCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommandInMainUnselectedMode(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommandInMainUnselectedMode(ExitCommand.COMMAND_WORD
                + " 3") instanceof ExitCommand);
    }
    /*
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommandInMainUnselectedMode(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(keywords), command);
    }
    */

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommandInMainUnselectedMode(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommandInMainUnselectedMode(HelpCommand.COMMAND_WORD
                + " 3") instanceof HelpCommand);
    }
    /*
    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommandInMainUnselectedMode(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommandInMainUnselectedMode(ListCommand.COMMAND_WORD
                + " 3") instanceof ListCommand);
    }
    */
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommandInMainUnselectedMode(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommandInMainUnselectedMode("unknownCommand"));
    }
}
