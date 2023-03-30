package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.commands.AddExpenseCommand;
import seedu.address.logic.commands.AddRecurringExpenseCommand;
import seedu.address.logic.commands.CategorySummaryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCategoryCommand;
import seedu.address.logic.commands.DeleteExpenseCommand;
import seedu.address.logic.commands.DeleteRecurringExpenseCommand;
import seedu.address.logic.commands.EditCategory;
import seedu.address.logic.commands.EditExpenseCommand;
import seedu.address.logic.commands.EditRecurringExpenseManagerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCategoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SetBudgetCommand;
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

        case ListCommand.COMMAND_WORD:
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
            return new AddCategoryParser().parse(arguments);

        case DeleteCategoryCommand.COMMAND_WORD:
            return new DeleteCategoryParser().parse(arguments);

        case EditCategory.COMMAND_WORD:
            return new EditCategoryParser().parse(arguments);

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
            return new AddRecurringExpenseParser().parse(arguments);

        case DeleteRecurringExpenseCommand.COMMAND_WORD:
            return new DeleteRecurringExpenseParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
