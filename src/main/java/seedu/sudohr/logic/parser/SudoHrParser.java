package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.sudohr.logic.commands.ClearCommand;
import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.ExitCommand;
import seedu.sudohr.logic.commands.HelpCommand;
import seedu.sudohr.logic.commands.ShowAllCommand;
import seedu.sudohr.logic.commands.department.AddDepartmentCommand;
import seedu.sudohr.logic.commands.department.AddEmployeeToDepartmentCommand;
import seedu.sudohr.logic.commands.department.DeleteDepartmentCommand;
import seedu.sudohr.logic.commands.department.EditDepartmentCommand;
import seedu.sudohr.logic.commands.department.FindDepartmentCommand;
import seedu.sudohr.logic.commands.department.ListDepartmentCommand;
import seedu.sudohr.logic.commands.department.ListDepartmentHeadcountCommand;
import seedu.sudohr.logic.commands.department.ListEmployeeDepartmentCommand;
import seedu.sudohr.logic.commands.department.ListEmployeesInDepartmentCommand;
import seedu.sudohr.logic.commands.department.RemoveEmployeeFromDepartmentCommand;
import seedu.sudohr.logic.commands.employee.AddCommand;
import seedu.sudohr.logic.commands.employee.DeleteCommand;
import seedu.sudohr.logic.commands.employee.EditCommand;
import seedu.sudohr.logic.commands.employee.FindByIdCommand;
import seedu.sudohr.logic.commands.employee.FindCommand;
import seedu.sudohr.logic.commands.employee.ListCommand;
import seedu.sudohr.logic.commands.leave.AddEmployeeToLeaveCommand;
import seedu.sudohr.logic.commands.leave.AddEmployeeToLeaveFromToCommand;
import seedu.sudohr.logic.commands.leave.DeleteEmployeeFromLeaveCommand;
import seedu.sudohr.logic.commands.leave.ListEmployeeInLeaveCommand;
import seedu.sudohr.logic.commands.leave.ListLeaveCommand;
import seedu.sudohr.logic.commands.leave.ListLeavesByEmployeeCommand;
import seedu.sudohr.logic.parser.department.AddDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.AddEmployeeToDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.DeleteDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.EditDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.FindDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.ListDepartmentHeadcountCommandParser;
import seedu.sudohr.logic.parser.department.ListEmployeeDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.ListEmployeesInDepartmentCommandParser;
import seedu.sudohr.logic.parser.department.RemoveEmployeeFromDepartmentCommandParser;
import seedu.sudohr.logic.parser.employee.AddCommandParser;
import seedu.sudohr.logic.parser.employee.DeleteCommandParser;
import seedu.sudohr.logic.parser.employee.EditCommandParser;
import seedu.sudohr.logic.parser.employee.FindByIdCommandParser;
import seedu.sudohr.logic.parser.employee.FindCommandParser;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.logic.parser.leave.AddEmployeeToLeaveCommandParser;
import seedu.sudohr.logic.parser.leave.AddEmployeeToLeaveFromToCommandParser;
import seedu.sudohr.logic.parser.leave.DeleteEmployeeFromLeaveCommandParser;
import seedu.sudohr.logic.parser.leave.ListEmployeeInLeaveCommandParser;
import seedu.sudohr.logic.parser.leave.ListLeavesByEmployeeCommandParser;


/**
 * Parses user input.
 */
public class SudoHrParser {

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

        case AddDepartmentCommand.COMMAND_WORD:
            return new AddDepartmentCommandParser().parse(arguments);

        case EditDepartmentCommand.COMMAND_WORD:
            return new EditDepartmentCommandParser().parse(arguments);

        case DeleteDepartmentCommand.COMMAND_WORD:
            return new DeleteDepartmentCommandParser().parse(arguments);

        case AddEmployeeToDepartmentCommand.COMMAND_WORD:
            return new AddEmployeeToDepartmentCommandParser().parse(arguments);

        case ListDepartmentCommand.COMMAND_WORD:
            return new ListDepartmentCommand();

        case ListEmployeeDepartmentCommand.COMMAND_WORD:
            return new ListEmployeeDepartmentCommandParser().parse(arguments);

        case ListEmployeesInDepartmentCommand.COMMAND_WORD:
            return new ListEmployeesInDepartmentCommandParser().parse(arguments);

        case ListDepartmentHeadcountCommand.COMMAND_WORD:
            return new ListDepartmentHeadcountCommandParser().parse(arguments);

        case FindDepartmentCommand.COMMAND_WORD:
            return new FindDepartmentCommandParser().parse(arguments);

        case RemoveEmployeeFromDepartmentCommand.COMMAND_WORD:
            return new RemoveEmployeeFromDepartmentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindByIdCommand.COMMAND_WORD:
            return new FindByIdCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddEmployeeToLeaveCommand.COMMAND_WORD:
            return new AddEmployeeToLeaveCommandParser().parse(arguments);

        case DeleteEmployeeFromLeaveCommand.COMMAND_WORD:
            return new DeleteEmployeeFromLeaveCommandParser().parse(arguments);

        case ListEmployeeInLeaveCommand.COMMAND_WORD:
            return new ListEmployeeInLeaveCommandParser().parse(arguments);

        case ListLeavesByEmployeeCommand.COMMAND_WORD:
            return new ListLeavesByEmployeeCommandParser().parse(arguments);
        case AddEmployeeToLeaveFromToCommand.COMMAND_WORD:
            return new AddEmployeeToLeaveFromToCommandParser().parse(arguments);

        case ListLeaveCommand.COMMAND_WORD:
            return new ListLeaveCommand();
        case ShowAllCommand.COMMAND_WORD:
            return new ShowAllCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
