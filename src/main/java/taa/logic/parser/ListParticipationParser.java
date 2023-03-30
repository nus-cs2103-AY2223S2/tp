package taa.logic.parser;

import static java.util.Objects.requireNonNull;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.ListParticipationCommand;
import taa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListParticipationCommand object
 */
public class ListParticipationParser implements Parser<ListParticipationCommand> {
    @Override
    public ListParticipationCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListParticipationCommand.MESSAGE_USAGE), pe);
        }

        return new ListParticipationCommand(index);
    }
}
