package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;

import java.util.stream.Stream;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.AddExerciseCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.routines.Exercise;


/**
 * Parses input arguments and creates a new AddExerciseCommand object
 */
public class AddExerciseCommandParser implements Parser<AddExerciseCommand> {
    private Index index;

    /**
     * Parses the given {@code String} of arguments in the context of the AddExerciseCommand
     * and returns an AddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public AddExerciseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE);
        if (!arePrefixesPresent(argMultimap, PREFIX_EXERCISE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExerciseCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddExerciseCommand.MESSAGE_USAGE), pe);
        }

        Exercise exercise = ParserUtil.parseExercise(argMultimap.getValue(PREFIX_EXERCISE).get());

        return new AddExerciseCommand(index, exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
