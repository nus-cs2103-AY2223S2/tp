package seedu.vms.logic.parser.basic;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.basic.ExitCommand;
import seedu.vms.logic.commands.basic.HelpCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/** Parser for base level commands. */
public class BasicParser extends FeatureParser {
    @Override
    public Command parseCommand(String commandWord, ArgumentMultimap args) throws ParseException {
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
