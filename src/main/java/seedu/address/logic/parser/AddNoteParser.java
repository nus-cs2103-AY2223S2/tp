package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Note;

/**
 * Parser for notes from commands
 */
public class AddNoteParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNote
     * and returns an AddNote object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String newArgs = args.trim().replaceFirst("Note", "");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_EXTERNAL, PREFIX_NOTE_CONTENT,
                        PREFIX_TUTORIAL, PREFIX_LAB);

        if (!arePrefixesAbsent(argMultimap, PREFIX_NOTE_CONTENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteCommand.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_NOTE_CONTENT) || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteCommand.MESSAGE_USAGE));
        }

        Optional<String> tutorialName = argMultimap.getValue(PREFIX_TUTORIAL);
        Optional<String> labName = argMultimap.getValue(PREFIX_LAB);
        String eventName = !tutorialName.isEmpty() ? tutorialName.get() : labName.get();
        String eventType = !tutorialName.isEmpty() ? "tutorial" : "lab";

        String name = ParserUtil.parseNoteContent(argMultimap.getValue(PREFIX_NOTE_CONTENT).get());

        Note note = new Note(name);
        return new AddNoteCommand(note, eventName, eventType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains command to add students (cannot add student and lab
     * using the same command.)
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
