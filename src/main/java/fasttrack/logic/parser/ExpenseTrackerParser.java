package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fasttrack.logic.commands.Command;
import fasttrack.logic.commands.SetBudgetCommand;
import fasttrack.logic.commands.add.AddCategoryCommand;
import fasttrack.logic.commands.add.AddExpenseCommand;
import fasttrack.logic.commands.add.AddRecurringExpenseCommand;
import fasttrack.logic.commands.delete.DeleteCategoryCommand;
import fasttrack.logic.commands.delete.DeleteExpenseCommand;
import fasttrack.logic.commands.delete.DeleteRecurringExpenseCommand;
import fasttrack.logic.commands.edit.EditCategoryCommand;
import fasttrack.logic.commands.edit.EditExpenseCommand;
import fasttrack.logic.commands.edit.EditRecurringExpenseManagerCommand;
import fasttrack.logic.commands.general.CategorySummaryCommand;
import fasttrack.logic.commands.general.ClearCommand;
import fasttrack.logic.commands.general.ExitCommand;
import fasttrack.logic.commands.general.FindCommand;
import fasttrack.logic.commands.general.HelpCommand;
import fasttrack.logic.commands.list.ListCategoryCommand;
import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.logic.commands.list.ListRecurringExpensesCommand;
import fasttrack.logic.parser.add.AddCategoryCommandParser;
import fasttrack.logic.parser.add.AddExpenseCommandParser;
import fasttrack.logic.parser.add.AddRecurringExpenseCommandParser;
import fasttrack.logic.parser.delete.DeleteCategoryCommandParser;
import fasttrack.logic.parser.delete.DeleteExpenseCommandParser;
import fasttrack.logic.parser.delete.DeleteRecurringExpenseCommandParser;
import fasttrack.logic.parser.edit.EditCategoryCommandParser;
import fasttrack.logic.parser.edit.EditExpenseCommandParser;
import fasttrack.logic.parser.edit.EditRecurringExpenseManagerCommandParser;
import fasttrack.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ExpenseTrackerParser {

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

        case AddExpenseCommand.COMMAND_WORD:
            return new AddExpenseCommandParser().parse(arguments);

        case DeleteExpenseCommand.COMMAND_WORD:
            return new DeleteExpenseCommandParser().parse(arguments);

        case ListExpensesCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ListCategoryCommand.COMMAND_WORD:
            return new ListCategoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddCategoryCommand.COMMAND_WORD:
            return new AddCategoryCommandParser().parse(arguments);

        case DeleteCategoryCommand.COMMAND_WORD:
            return new DeleteCategoryCommandParser().parse(arguments);

        case EditCategoryCommand.COMMAND_WORD:
            return new EditCategoryCommandParser().parse(arguments);

        case EditExpenseCommand.COMMAND_WORD:
            return new EditExpenseCommandParser().parse(arguments);

        case SetBudgetCommand.COMMAND_WORD:
            return new SetBudgetParser().parse(arguments);

        case CategorySummaryCommand.COMMAND_WORD:
            return new CategorySummaryParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case EditRecurringExpenseManagerCommand.COMMAND_WORD:
            return new EditRecurringExpenseManagerCommandParser().parse(arguments);

        case AddRecurringExpenseCommand.COMMAND_WORD:
            return new AddRecurringExpenseCommandParser().parse(arguments);

        case DeleteRecurringExpenseCommand.COMMAND_WORD:
            return new DeleteRecurringExpenseCommandParser().parse(arguments);

        case ListRecurringExpensesCommand.COMMAND_WORD:
            return new ListRecurringExpensesCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
