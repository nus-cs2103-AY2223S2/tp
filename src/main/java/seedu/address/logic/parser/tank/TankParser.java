package seedu.address.logic.parser.tank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tank.TankAddCommand;
import seedu.address.logic.commands.tank.TankCommand;
import seedu.address.logic.commands.tank.TankDeleteCommand;
import seedu.address.logic.commands.tank.TankViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for {@code Tank} commands.
 */
public class TankParser {

    /**
     * Used for separation of fish command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<tankCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into {@code Task} command for execution.
     *
     * @param userInput User input string excluding the "Task" command word.
     * @return The {@code Task} command based on the user input.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TankCommand.MESSAGE_USAGE));
        }

        final String tankCommandWord = matcher.group("tankCommandWord");
        final String arguments = matcher.group("arguments");
        switch (tankCommandWord) {
        case TankAddCommand.TANK_COMMAND_WORD:
            return new TankAddCommandParser().parse(arguments);
        case TankDeleteCommand.TANK_COMMAND_WORD:
            return new TankDeleteCommandParser().parse(arguments);
        case TankViewCommand.TANK_COMMAND_WORD:
            return new TankViewCommandParser().parse(arguments);
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    TankCommand.MESSAGE_USAGE));
        }
    }

}

