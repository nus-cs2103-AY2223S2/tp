package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_INDEX, PREFIX_NOTE_EVENT_TYPE,
                        PREFIX_NOTE_EVENT_NAME);
        if (arePrefixesAbsent(argMultimap, PREFIX_NOTE_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteNoteCommand.MESSAGE_USAGE) + "\n"
                    + DeleteNoteCommand.MESSAGE_EXAMPLE);
        }

        // The case of adding note without event
        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE_EVENT_TYPE, PREFIX_NOTE_EVENT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteNoteCommand.MESSAGE_USAGE) + "\n"
                    + DeleteNoteCommand.MESSAGE_EXAMPLE);
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_NOTE_INDEX).get());
        String eventName = argMultimap.getValue(PREFIX_NOTE_EVENT_NAME).get();
        String eventType = argMultimap.getValue(PREFIX_NOTE_EVENT_TYPE).get();
        return new DeleteNoteCommand(index, eventName, eventType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains command to add students (cannot add
     * student and tutorial
     * using the same command.)
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

