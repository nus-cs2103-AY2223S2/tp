package seedu.vms.logic.parser;

import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.vms.logic.commands.AddCommand;
import seedu.vms.logic.commands.ClearCommand;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.DeleteCommand;
import seedu.vms.logic.commands.EditCommand;
import seedu.vms.logic.commands.FindCommand;
import seedu.vms.logic.commands.ListCommand;
import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PatientParser extends FeatureArgumentParser {

    public static final String FEATURE_NAME = "patient";


    @Override
    public Command parseCommand(String commandWord, String arguments) throws ParseException {
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

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
