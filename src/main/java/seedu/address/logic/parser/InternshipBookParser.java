package seedu.address.logic.parser;

//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddApplicationCommand;
import seedu.address.logic.commands.DeleteApplicationCommand;
//import seedu.address.logic.commands.EditApplicationCommand;
//import seedu.address.logic.commands.HelpApplicationCommand;
import seedu.address.logic.commands.ApplicationCommand;
import seedu.address.logic.commands.FindApplicationCommand;
import seedu.address.logic.commands.ListApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
//Uncomment the corresponding import statement once you've implemented your own feature.

/**
 * Parses user input.
 */
public class InternshipBookParser {

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
    public ApplicationCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        /*
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            HelpApplicationCommand.MESSAGE_USAGE));
        }
        Noah uncomment this once you've implemented HelpCommand.
        */
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddApplicationCommand.COMMAND_WORD:
            return new AddApplicationCommandParser().parse(arguments);

        case ListApplicationCommand.COMMAND_WORD:
            return new ListApplicationCommand();

        case FindApplicationCommand.COMMAND_WORD:
            return new FindApplicationCommandParser().parse(arguments);

        /*
        case EditApplicationCommand.COMMAND_WORD:
            return new EditApplicationCommandParser().parse(arguments);
        */
        case DeleteApplicationCommand.COMMAND_WORD:
            return new DeleteApplicationCommandParser().parse(arguments);
        /*
        case HelpApplicationCommand.COMMAND_WORD:
            return new HelpApplicationCommand();

        Same as for import statements, uncomment the corresponding part when you're done.
         */

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
