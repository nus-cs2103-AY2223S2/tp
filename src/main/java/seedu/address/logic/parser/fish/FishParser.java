package seedu.address.logic.parser.fish;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.logic.commands.fish.FishCommand;
import seedu.address.logic.commands.fish.FishDeleteCommand;
import seedu.address.logic.commands.fish.FishViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for {@code Fish} commands.
 */
public class FishParser {

    /**
     * Used for separation of fish command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<fishCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into {@code Fish} command for execution.
     *
     * @param userInput User input string excluding the "Fish" command word.
     * @return The {@code Fish} command based on the user input.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FishCommand.MESSAGE_USAGE));
        }

        final String fishCommandWord = matcher.group("fishCommandWord");
        final String arguments = matcher.group("arguments");
        switch (fishCommandWord) {
        case FishAddCommand.FISH_COMMAND_WORD:
            return new FishAddCommandParser().parse(arguments);
        case FishDeleteCommand.FISH_COMMAND_WORD:
            return new FishDeleteCommandParser().parse(arguments);
        case FishViewCommand.FISH_COMMAND_WORD:
            return new FishViewCommandParser().parse(arguments);
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        FishCommand.MESSAGE_USAGE));
        }
    }

}
