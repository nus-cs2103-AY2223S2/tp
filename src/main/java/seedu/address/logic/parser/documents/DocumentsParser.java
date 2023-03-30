package seedu.address.logic.parser.documents;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.documents.AddDocumentsCommand;
import seedu.address.logic.commands.documents.DeleteDocumentsCommand;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for commands related to documents.
 */
public class DocumentsParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseDocumentsCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddDocumentsCommand.COMMAND_WORD:
            return new AddDocumentsCommandParser().parse(arguments);

        case EditDocumentsCommand.COMMAND_WORD:
            return new EditDocumentsCommandParser().parse(arguments);

        case DeleteDocumentsCommand.COMMAND_WORD:
            return new DeleteDocumentsCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
