package seedu.dengue.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.logic.commands.ClearCommand;
import seedu.dengue.logic.commands.DeleteCommand;
import seedu.dengue.logic.commands.EditCommand;
import seedu.dengue.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.dengue.logic.commands.ExitCommand;
//import seedu.dengue.logic.commands.FindCommand;
import seedu.dengue.logic.commands.HelpCommand;
import seedu.dengue.logic.commands.ListCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
//import seedu.dengue.model.person.Age;
//import seedu.dengue.model.person.Date;
//import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
//import seedu.dengue.model.person.SubPostal;
//import seedu.dengue.model.predicate.FindPredicate;
//import seedu.dengue.model.variant.Variant;
import seedu.dengue.testutil.EditPersonDescriptorBuilder;
import seedu.dengue.testutil.PersonBuilder;
import seedu.dengue.testutil.PersonUtil;

public class DengueHotspotTrackerParserTest {

    private final DengueHotspotTrackerParser parser = new DengueHotspotTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    //    @Test
    //    public void parseCommand_find() throws Exception {
    //        Optional<SubPostal> emptySubPostal = Optional.empty();
    //        Optional<Name> testName = Optional.of(new Name("Baron"));
    //        Optional<Age> emptyAge = Optional.empty();
    //        Optional<Date> emptyDate = Optional.empty();
    //        Set<Variant> emptyVariants = new HashSet<>();
    //        FindCommand command = (FindCommand) parser.parseCommand(
    //                FindCommand.COMMAND_WORD + " n/Baron");
    //        assertEquals(new FindCommand(new FindPredicate(
    //                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants)), command);
    //    }
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
