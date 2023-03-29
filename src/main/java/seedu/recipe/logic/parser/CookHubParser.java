package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.commands.ClearCommand;
import seedu.recipe.logic.commands.Command;
import seedu.recipe.logic.commands.DeleteCommand;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.ExitCommand;
import seedu.recipe.logic.commands.FavoritesCommand;
import seedu.recipe.logic.commands.FilterPriceCommand;
import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.commands.GroceriesCommand;
import seedu.recipe.logic.commands.HelpCommand;
import seedu.recipe.logic.commands.ListCommand;
import seedu.recipe.logic.commands.OnlyCommand;
import seedu.recipe.logic.commands.SortCommand;
import seedu.recipe.logic.commands.StarCommand;
import seedu.recipe.logic.commands.UnstarCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CookHubParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case OnlyCommand.COMMAND_WORD:
            return new OnlyCommandParser().parse(arguments);

        case StarCommand.COMMAND_WORD:
            return new StarCommandParser().parse(arguments);

        case UnstarCommand.COMMAND_WORD:
            return new UnstarCommandParser().parse(arguments);

        case FavoritesCommand.COMMAND_WORD:
            return new FavoritesCommand();

        case FilterPriceCommand.COMMAND_WORD:
            return new FilterPriceCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case GroceriesCommand.COMMAND_WORD:
            return new GroceriesCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
