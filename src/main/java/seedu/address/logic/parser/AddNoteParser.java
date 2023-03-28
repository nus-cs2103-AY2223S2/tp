package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EVENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EXTERNAL;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddNoteToEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Note;

/**
 * Parser notes from commands
 */
public class AddNoteParser implements Parser<AddNoteToEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNote
     * and returns an AddNote object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteToEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String newArgs = args.trim().replaceFirst("Note", "");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(newArgs, PREFIX_NOTE_EXTERNAL, PREFIX_NOTE_CONTENT,
                        PREFIX_NOTE_EVENT_TYPE, PREFIX_NOTE_EVENT_NAME);
        if (arePrefixesAbsent(argMultimap, PREFIX_NOTE_CONTENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteToEventCommand.MESSAGE_USAGE));
        }

        // The case of adding note without event
        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE_EVENT_TYPE, PREFIX_NOTE_EVENT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteToEventCommand.MESSAGE_USAGE));
        }
        String name = ParserUtil.parseNoteContent(argMultimap.getValue(PREFIX_NOTE_CONTENT).get());
        String eventName = argMultimap.getValue(PREFIX_NOTE_EVENT_NAME).get();
        String eventType = argMultimap.getValue(PREFIX_NOTE_EVENT_TYPE).get();
        Note note = new Note(name);
        return new AddNoteToEventCommand(note, eventName, eventType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes is contained in the string
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
