package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.commands.patient.ClearCommand;
import seedu.vms.logic.commands.patient.DeleteCommand;
import seedu.vms.logic.commands.patient.DetailCommand;
import seedu.vms.logic.commands.patient.EditCommand;
import seedu.vms.logic.commands.patient.FindCommand;
import seedu.vms.logic.commands.patient.ListCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses patient feature commands.
 */
public class PatientParser extends FeatureParser {

    public static final String FEATURE_NAME = "patient";

    @Override
    public Command parseCommand(String commandWord, ArgumentMultimap arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case DetailCommand.COMMAND_WORD:
            return new DetailCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
