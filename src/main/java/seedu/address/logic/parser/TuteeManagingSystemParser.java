package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TuteeManagingSystemParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Map<String, CommandSupplier> commandMap = Map.ofEntries(
        Map.entry(AddCommand.COMMAND_WORD, new AddCommandParser()),
        Map.entry(ClearCommand.COMMAND_WORD, args -> new ClearCommand()),
        Map.entry(DeleteCommand.COMMAND_WORD, new DeleteCommandParser()),
        Map.entry(EditCommand.COMMAND_WORD, new EditCommandParser()),
        Map.entry(FilterCommand.COMMAND_WORD, new FilterCommandParser()),
        Map.entry(FindCommand.COMMAND_WORD, new FindCommandParser()),
        Map.entry(ExitCommand.COMMAND_WORD, args -> new ExitCommand()),
        Map.entry(HelpCommand.COMMAND_WORD, args -> new HelpCommand()),
        Map.entry(ListCommand.COMMAND_WORD, args -> new ListCommand()),
        Map.entry(MarkCommand.COMMAND_WORD, new MarkCommandParser()),
        Map.entry(QueryCommand.COMMAND_WORD, new QueryCommandParser()),
        Map.entry(RemarkCommand.COMMAND_WORD, new RemarkCommandParser()),
        Map.entry(UnmarkCommand.COMMAND_WORD, new UnmarkCommandParser())
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandMap.containsKey(commandWord)) {
            return commandMap.get(commandWord).parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
