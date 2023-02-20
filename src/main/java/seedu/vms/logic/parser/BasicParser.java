package seedu.vms.logic.parser;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.ExitCommand;
import seedu.vms.logic.commands.HelpCommand;
import seedu.vms.logic.parser.exceptions.ParseException;

public class BasicParser extends FeatureArgumentParser {
    @Override
    public Command parseCommand(String commandWord, String arguments) throws ParseException {
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
