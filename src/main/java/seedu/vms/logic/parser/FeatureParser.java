package seedu.vms.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.basic.HelpCommand;
import seedu.vms.logic.parser.exceptions.ParseException;


/** A parser to parse the command of a feature. */
public abstract class FeatureParser implements Parser<Command> {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    @Override
    public Command parse(String commandInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        return parseCommand(commandWord, arguments);
    }


    protected abstract Command parseCommand(String commandWord, String argument) throws ParseException;
}
