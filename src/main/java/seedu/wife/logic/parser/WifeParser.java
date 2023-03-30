package seedu.wife.logic.parser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.foodcommands.AddCommand;
import seedu.wife.logic.commands.foodcommands.DecreaseCommand;
import seedu.wife.logic.commands.foodcommands.DeleteByTagCommand;
import seedu.wife.logic.commands.foodcommands.DeleteCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.logic.commands.foodcommands.FindCommand;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand;
import seedu.wife.logic.commands.foodcommands.ListByTagCommand;
import seedu.wife.logic.commands.foodcommands.ListCommand;
import seedu.wife.logic.commands.foodcommands.SortByExpiryCommand;
import seedu.wife.logic.commands.foodcommands.ViewCommand;
import seedu.wife.logic.commands.generalcommands.ClearCommand;
import seedu.wife.logic.commands.generalcommands.ExitCommand;
import seedu.wife.logic.commands.generalcommands.HelpCommand;
import seedu.wife.logic.commands.tagcommands.CreateTagCommand;
import seedu.wife.logic.commands.tagcommands.DeleteTagCommand;
import seedu.wife.logic.commands.tagcommands.ListTagCommand;
import seedu.wife.logic.commands.tagcommands.TagFoodCommand;
import seedu.wife.logic.commands.tagcommands.UntagFoodCommand;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.logic.parser.foodcommandparser.AddCommandParser;
import seedu.wife.logic.parser.foodcommandparser.DecreaseCommandParser;
import seedu.wife.logic.parser.foodcommandparser.DeleteByTagCommandParser;
import seedu.wife.logic.parser.foodcommandparser.DeleteCommandParser;
import seedu.wife.logic.parser.foodcommandparser.EditCommandParser;
import seedu.wife.logic.parser.foodcommandparser.FindCommandParser;
import seedu.wife.logic.parser.foodcommandparser.IncreaseCommandParser;
import seedu.wife.logic.parser.foodcommandparser.ListByTagCommandParser;
import seedu.wife.logic.parser.foodcommandparser.ViewCommandParser;
import seedu.wife.logic.parser.tagcommandparser.CreateTagCommandParser;
import seedu.wife.logic.parser.tagcommandparser.DeleteTagCommandParser;
import seedu.wife.logic.parser.tagcommandparser.TagFoodCommandParser;
import seedu.wife.logic.parser.tagcommandparser.UntagFoodCommandParser;

/**
 * Parses user input.
 */
public class WifeParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
        "(?<commandWord>\\S+)(?<arguments>.*)"
    );

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE)
            );
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case CreateTagCommand.COMMAND_WORD:
            return new CreateTagCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case DeleteByTagCommand.COMMAND_WORD:
            return new DeleteByTagCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();
        case ListByTagCommand.COMMAND_WORD:
            return new ListByTagCommandParser().parse(arguments);
        case SortByExpiryCommand.COMMAND_WORD:
            return new SortByExpiryCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case TagFoodCommand.COMMAND_WORD:
            return new TagFoodCommandParser().parse(arguments);
        case UntagFoodCommand.COMMAND_WORD:
            return new UntagFoodCommandParser().parse(arguments);
        case IncreaseCommand.COMMAND_WORD:
            return new IncreaseCommandParser().parse(arguments);
        case DecreaseCommand.COMMAND_WORD:
            return new DecreaseCommandParser().parse(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
