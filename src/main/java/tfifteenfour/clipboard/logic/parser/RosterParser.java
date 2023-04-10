package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.BackCommand;
import tfifteenfour.clipboard.logic.commands.ClearCommand;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CopyCommand;
import tfifteenfour.clipboard.logic.commands.ExitCommand;
import tfifteenfour.clipboard.logic.commands.HelpCommand;
import tfifteenfour.clipboard.logic.commands.HomeCommand;
import tfifteenfour.clipboard.logic.commands.RemarkCommand;
import tfifteenfour.clipboard.logic.commands.SelectCommand;
import tfifteenfour.clipboard.logic.commands.UndoCommand;
import tfifteenfour.clipboard.logic.commands.UploadCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.AttendanceCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkAbsentCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkPresentCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.SessionCommand;
import tfifteenfour.clipboard.logic.commands.deletecommand.DeleteCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.findcommand.FindCommand;
import tfifteenfour.clipboard.logic.commands.sortcommand.SortCommand;
import tfifteenfour.clipboard.logic.commands.taskcommand.AssignCommand;
import tfifteenfour.clipboard.logic.commands.taskcommand.TaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.Model;


/**
 * Parses user input.
 */
public class RosterParser {

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
    public static Command parseCommand(String userInput, Model model)
            throws ParseException, CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        CurrentSelection currentSelection = model.getCurrentSelection();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser(currentSelection).parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser(currentSelection).parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser(currentSelection).parse(arguments);

        case UploadCommand.COMMAND_WORD:
            return new UploadCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser(currentSelection).parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case BackCommand.COMMAND_WORD:
            return new BackCommand(currentSelection);

        case SessionCommand.COMMAND_WORD:
            return new SessionCommandParser().parse(arguments);

        case MarkPresentCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case MarkAbsentCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case AttendanceCommand.COMMAND_WORD:
            return new AttendanceCommand();

        case TaskCommand.COMMAND_WORD:
            return new TaskCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        case CopyCommand.COMMAND_WORD:
            return new CopyCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
