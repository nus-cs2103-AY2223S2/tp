package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Note;

/**
 * Parses input arguments and creates a new EditEventCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_CONTENT, PREFIX_NOTE_INDEX, PREFIX_NOTE_EVENT_TYPE,
                        PREFIX_NOTE_EVENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE_CONTENT, PREFIX_NOTE_INDEX, PREFIX_NOTE_EVENT_TYPE,
                PREFIX_NOTE_EVENT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditNoteCommand.MESSAGE_USAGE) + "\n"
                    + EditNoteCommand.MESSAGE_EXAMPLE);
        }
        String content = argMultimap.getValue(PREFIX_NOTE_CONTENT).get();
        if (content.length() > Note.LENGTH_LIMIT) {
            throw new ParseException(String.format("Note is too long! Shrink it to under %1$s", Note.LENGTH_LIMIT));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_NOTE_INDEX).get());
        String eventName = argMultimap.getValue(PREFIX_NOTE_EVENT_NAME).get();
        String eventType = argMultimap.getValue(PREFIX_NOTE_EVENT_TYPE).get();
        Note note = new Note(content);
        return new EditNoteCommand(index, note, eventName, eventType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

