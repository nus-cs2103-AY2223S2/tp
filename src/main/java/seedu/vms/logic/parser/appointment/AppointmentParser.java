package seedu.vms.logic.parser.appointment;

import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.appointment.AddCommand;
import seedu.vms.logic.commands.appointment.DeleteCommand;
import seedu.vms.logic.commands.appointment.EditCommand;
import seedu.vms.logic.commands.appointment.FindCommand;
import seedu.vms.logic.commands.appointment.ListCommand;
import seedu.vms.logic.commands.appointment.MarkCommand;
import seedu.vms.logic.commands.appointment.UnmarkCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses appointment feature commands.
 */
public class AppointmentParser extends FeatureParser {

    public static final String FEATURE_NAME = "appointment";


    @Override
    public Command parseCommand(String commandWord, ArgumentMultimap arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
