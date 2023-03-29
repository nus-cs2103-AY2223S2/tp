package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_KNOWN_COMMANDS;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.Storage;

/**
 * Parses user input into export and import command.
 */

public class ArchiveParser {
    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final Storage storage;

    public ArchiveParser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Check whether user input is related to archive command
     * @param userInput input provided by user
     * @return a boolean that shows whether user input is related to archive command
     * @throws ParseException
     */
    public boolean isArchiveCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        if (commandWord.equals(ExportCommand.COMMAND_WORD) || commandWord.equals(ImportCommand.COMMAND_WORD)) {
            return true;
        }
        return false;
    }

    /**
     * Parses user input into archive command for execution.
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
        switch (commandWord) {
        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments, storage);
        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments, storage);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND + MESSAGE_KNOWN_COMMANDS);
        }
    }
}
