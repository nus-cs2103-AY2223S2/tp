package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.logic.commands.patientcommands.ClearCommand;
import seedu.careflow.logic.commands.patientcommands.DeleteCommand;
import seedu.careflow.logic.commands.patientcommands.FindCommand;
import seedu.careflow.logic.commands.patientcommands.ListCommand;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand;
import seedu.careflow.logic.parser.exceptions.ParseException;

/**
 * Parses user input
 */
public class PatientParser {

    public static final String OPERATION_TYPE = "p";

    /**
     * Parses user input into command for execution.
     * @param commandWord user command
     * @param arguments arguments specific to the command
     * @return the command based on the command word
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
