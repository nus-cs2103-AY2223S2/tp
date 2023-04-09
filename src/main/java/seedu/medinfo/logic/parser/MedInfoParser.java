package seedu.medinfo.logic.parser;

import static seedu.medinfo.commons.core.Messages.MESSAGE_ABORT_DELETE;
import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seedu.medinfo.logic.commands.AddCommand;
import seedu.medinfo.logic.commands.AddWardCommand;
import seedu.medinfo.logic.commands.ClearCommand;
import seedu.medinfo.logic.commands.Command;
import seedu.medinfo.logic.commands.DeleteCommand;
import seedu.medinfo.logic.commands.DeleteWardCommand;
import seedu.medinfo.logic.commands.EditCommand;
import seedu.medinfo.logic.commands.EditWardCommand;
import seedu.medinfo.logic.commands.ExitCommand;
import seedu.medinfo.logic.commands.FindCommand;
import seedu.medinfo.logic.commands.HelpCommand;
import seedu.medinfo.logic.commands.ListCommand;
import seedu.medinfo.logic.commands.SortCommand;
import seedu.medinfo.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MedInfoParser {

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

        // for confirmation window
        Alert confirmationDialog;
        Optional<ButtonType> result;

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete the patient?");
            result = confirmationDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return new DeleteCommandParser().parse(arguments);
            } else {
                throw new ParseException(MESSAGE_ABORT_DELETE); // cancel the deletion command
            }

        case ClearCommand.COMMAND_WORD:
            confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to clear ALL patients and wards?");
            result = confirmationDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return new ClearCommand();
            } else {
                throw new ParseException(MESSAGE_ABORT_DELETE); // cancel the deletion command
            }

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddWardCommand.COMMAND_WORD:
            return new AddWardCommandParser().parse(arguments);

        case EditWardCommand.COMMAND_WORD:
            return new EditWardCommandParser().parse(arguments);

        case DeleteWardCommand.COMMAND_WORD:
            confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete the ward?");
            result = confirmationDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return new DeleteWardCommandParser().parse(arguments);
            } else {
                throw new ParseException(MESSAGE_ABORT_DELETE); // cancel the deletion command
            }

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
