package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SetBudgetCommand;
import seedu.address.logic.commands.add.AddCategoryCommand;
import seedu.address.logic.commands.add.AddExpenseCommand;
import seedu.address.logic.commands.add.AddRecurringExpenseCommand;
import seedu.address.logic.commands.delete.DeleteCategoryCommand;
import seedu.address.logic.commands.delete.DeleteExpenseCommand;
import seedu.address.logic.commands.delete.DeleteRecurringExpenseCommand;
import seedu.address.logic.commands.edit.EditCategoryCommand;
import seedu.address.logic.commands.edit.EditExpenseCommand;
import seedu.address.logic.commands.edit.EditRecurringExpenseManagerCommand;
import seedu.address.logic.commands.general.CategorySummaryCommand;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.FindCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.commands.list.ListCategoryCommand;
import seedu.address.logic.commands.list.ListExpensesCommand;
import seedu.address.logic.commands.list.ListRecurringExpensesCommand;
import seedu.address.logic.parser.add.AddCategoryCommandParser;
import seedu.address.logic.parser.add.AddExpenseCommandParser;
import seedu.address.logic.parser.add.AddRecurringExpenseCommandParser;
import seedu.address.logic.parser.delete.DeleteCategoryCommandParser;
import seedu.address.logic.parser.delete.DeleteExpenseCommandParser;
import seedu.address.logic.parser.delete.DeleteRecurringExpenseCommandParser;
import seedu.address.logic.parser.edit.EditCategoryCommandParser;
import seedu.address.logic.parser.edit.EditExpenseCommandParser;
import seedu.address.logic.parser.edit.EditRecurringExpenseManagerCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

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
