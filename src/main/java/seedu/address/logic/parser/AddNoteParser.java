package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_EXTERNAL;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Note;

/**
 * Parser for notes (TODO: add more note options)
 */
public class AddNoteParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNote
     * and returns an AddNote object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        //newArgs to trim first word when more commands added to switch-case in AddressBookParser
        String newArgs = args.trim().replaceFirst("Note", "");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_EXTERNAL);
        System.out.println(argMultimap.getPreamble().isEmpty());
        //Make the user not create lab and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NOTE_CONTENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteCommand.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_NOTE_CONTENT) || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseNoteContent(argMultimap.getValue(PREFIX_NOTE_CONTENT).get());

        Note note = new Note(name);
        return new AddNoteCommand(note);
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
