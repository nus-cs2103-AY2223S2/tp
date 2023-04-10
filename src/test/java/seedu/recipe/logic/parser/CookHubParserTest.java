package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.commands.ClearCommand;
import seedu.recipe.logic.commands.DeleteCommand;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.logic.commands.ExitCommand;
import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.commands.HelpCommand;
import seedu.recipe.logic.commands.ListCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.TitleContainsKeywordsPredicate;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;
import seedu.recipe.testutil.RecipeBuilder;
import seedu.recipe.testutil.RecipeUtil;

public class CookHubParserTest {

    private final CookHubParser parser = new CookHubParser();

    @Test
    public void parseCommand_add() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(RecipeUtil.getAddCommand(recipe));
        assertEquals(new AddCommand(recipe), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_RECIPE.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_RECIPE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(recipe).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_RECIPE.getOneBased() + " " + RecipeUtil.getEditRecipeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_RECIPE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " t/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new TitleContainsKeywordsPredicate(keywords)), command);
    }

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
