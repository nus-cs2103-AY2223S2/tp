package seedu.address.logic.parser.task.note;

import seedu.address.logic.commands.task.note.NoteCommand;
import seedu.address.logic.commands.task.todo.TodoCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.JobTitle;
import seedu.address.model.todo.ApplicationDeadline;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;
import seedu.address.model.todo.NoteContent;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns an NoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE_CONTENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE_CONTENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        }

        NoteContent content = ParserUtil.parseContent(argMultimap.getValue(PREFIX_NOTE_CONTENT).orElse(null));


        Note note = new Note(content);

        return new NoteCommand(note);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
