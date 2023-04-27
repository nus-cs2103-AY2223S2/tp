package seedu.wife.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.AddCommand;
import seedu.wife.logic.commands.foodcommands.DeleteCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.logic.commands.foodcommands.FindCommand;
import seedu.wife.logic.commands.foodcommands.ListCommand;
import seedu.wife.logic.commands.foodcommands.SortByExpiryCommand;
import seedu.wife.logic.commands.generalcommands.ClearCommand;
import seedu.wife.logic.commands.generalcommands.ExitCommand;
import seedu.wife.logic.commands.generalcommands.HelpCommand;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.NameContainsKeywordsPredicate;
import seedu.wife.testutil.EditFoodDescriptorBuilder;
import seedu.wife.testutil.FoodBuilder;
import seedu.wife.testutil.FoodUtil;

public class WifeParserTest {

    private final WifeParser parser = new WifeParser();

    @Test
    public void parseCommand_add() throws Exception {
        Food food = new FoodBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FoodUtil.getAddCommand(food));
        assertEquals(new AddCommand(food), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(
            parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand
        );
        assertTrue(
            parser.parseCommand(
                ClearCommand.COMMAND_WORD + " 3"
            ) instanceof ClearCommand
        );
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FOOD.getOneBased()
        );
        assertEquals(new DeleteCommand(INDEX_FIRST_FOOD), command);
    }


    @Test
    public void parseCommand_edit() throws Exception {
        Food food = new FoodBuilder().build();
        EditCommand.EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(food).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FOOD.getOneBased() + " " + FoodUtil.getEditFoodDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FOOD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(
            parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand
        );
        assertTrue(
            parser.parseCommand(
                ExitCommand.COMMAND_WORD + " 3"
            ) instanceof ExitCommand
        );
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD
            + " "
            + keywords.stream().collect(Collectors.joining(" "))
        );
        assertEquals(
            new FindCommand(new NameContainsKeywordsPredicate(keywords)),
            command
        );
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(
            parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand
        );
        assertTrue(
            parser.parseCommand(
                HelpCommand.COMMAND_WORD + " 3"
            ) instanceof HelpCommand
        );
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(
            parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand
        );
        assertTrue(
            parser.parseCommand(
                ListCommand.COMMAND_WORD + " 3"
            ) instanceof ListCommand
        );
    }

    @Test
    public void parseCommand_sortByExpiry() throws Exception {
        assertTrue(
            parser.parseCommand(SortByExpiryCommand.COMMAND_WORD) instanceof SortByExpiryCommand
        );
        assertTrue(
            parser.parseCommand(
                SortByExpiryCommand.COMMAND_WORD
            ) instanceof SortByExpiryCommand
        );
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
            ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand("")
        );
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
            ParseException.class,
            MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand")
        );
    }
}
