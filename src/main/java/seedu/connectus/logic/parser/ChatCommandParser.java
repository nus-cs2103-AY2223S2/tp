package seedu.connectus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.ChatCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.socialmedia.WhatsApp;

/**
 * Parses input arguments and creates a new ChatCommand object
 */
public class ChatCommandParser implements Parser<ChatCommand> {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if user input does not conform the expected format
     */
    public ChatCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_MESSAGE, PREFIX_SOCMED_WHATSAPP);

        Index personIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChatCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_MESSAGE).isEmpty() || argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChatCommand.MESSAGE_USAGE));
        }

        String message = argMultimap.getValue(PREFIX_MESSAGE).get();

        return new ChatCommand(personIndex, message, (p) -> WhatsApp.getUserLinkWithPreparedMessage(p, message));
    }

}
