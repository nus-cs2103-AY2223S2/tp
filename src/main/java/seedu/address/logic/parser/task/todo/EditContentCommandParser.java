package seedu.address.logic.parser.task.todo;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.todo.EditDeadlineCommand;
import seedu.address.logic.commands.task.todo.EditNoteContentCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.todo.ApplicationDeadline;
import seedu.address.model.todo.NoteContent;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;

/**
 * Parses input arguments and creates a new EditNoteContentCommand object
 */
public class EditContentCommandParser implements Parser<EditNoteContentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteContentCommand
     * and returns an EditNoteContentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteContentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_CONTENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteContentCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE_CONTENT)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteContentCommand.MESSAGE_USAGE));
        }

        NoteContent content = ParserUtil.parseContent(
                argMultimap.getValue(PREFIX_NOTE_CONTENT).orElse(null));

        return new EditNoteContentCommand(index, content);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
