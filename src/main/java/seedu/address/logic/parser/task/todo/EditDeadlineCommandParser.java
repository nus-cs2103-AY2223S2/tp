package seedu.address.logic.parser.task.todo;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStatusCommand;
import seedu.address.logic.commands.task.todo.EditDeadlineCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.todo.ApplicationDeadline;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

/**
 * Parses input arguments and creates a new EditDeadlineCommand object
 */
public class EditDeadlineCommandParser implements Parser<EditDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDeadlineCommand
     * and returns an EditDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDeadlineCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDeadlineCommand.MESSAGE_USAGE));
        }

        ApplicationDeadline deadline = ParserUtil.parseDeadline(
                argMultimap.getValue(PREFIX_DEADLINE).orElse(null));

        return new EditDeadlineCommand(index, deadline);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
