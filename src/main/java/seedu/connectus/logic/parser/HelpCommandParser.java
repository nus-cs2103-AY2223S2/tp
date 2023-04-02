package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_HELP;

import java.util.HashMap;
import java.util.Map;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.commands.ChatCommand;
import seedu.connectus.logic.commands.ClearCommand;
import seedu.connectus.logic.commands.DeleteCommand;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;
import seedu.connectus.logic.commands.EditCommand;
import seedu.connectus.logic.commands.ExitCommand;
import seedu.connectus.logic.commands.HelpCommand;
import seedu.connectus.logic.commands.ListCommand;
import seedu.connectus.logic.commands.OpenCommand;
import seedu.connectus.logic.commands.SearchCommand;
import seedu.connectus.logic.commands.UpcomingBirthdayCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    //Solution below adapted from https://github.com/AY2223S1-CS2103T-T13-3/tp with modifications.
    private static final Map<String, String> COMMAND_USAGE_MESSAGES = new HashMap<>();

    static {
        COMMAND_USAGE_MESSAGES.put(AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(AddTagToPersonCommand.COMMAND_WORD, AddTagToPersonCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(ClearCommand.COMMAND_WORD, ClearCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(DeleteCommand.COMMAND_WORD, DeleteCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(DeleteTagFromPersonCommand.COMMAND_WORD, DeleteTagFromPersonCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(EditCommand.COMMAND_WORD, EditCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(ExitCommand.COMMAND_WORD, ExitCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(HelpCommand.COMMAND_WORD, HelpCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(ListCommand.COMMAND_WORD, ListCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(SearchCommand.COMMAND_WORD, SearchCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(OpenCommand.COMMAND_WORD, OpenCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(ChatCommand.COMMAND_WORD, ChatCommand.MESSAGE_USAGE);
        COMMAND_USAGE_MESSAGES.put(UpcomingBirthdayCommand.COMMAND_WORD,
                UpcomingBirthdayCommand.MESSAGE_USAGE);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     *
     * If no argument is received, return the HelpCommand that opens up a helpWindow.
     * Else, return HelpCommand that shows specific command usage instructions.
     *
     * @throws ParseException
     */
    public HelpCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new HelpCommand();
        }

        String commandUsage = COMMAND_USAGE_MESSAGES.get(args.trim());
        if (commandUsage == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_HELP, HelpCommand.MESSAGE_USAGE));
        }

        return new HelpCommand(commandUsage);
    }
}
