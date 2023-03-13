package seedu.fitbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE_NUMBER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;

import java.util.stream.Stream;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.EditRoutineCommand;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.routines.Exercise;

/**
 * Parses input arguments and creates a new EditRoutineCommand object
 */
public class EditRoutineCommandParser implements Parser<EditRoutineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditRoutineCommand
     * and returns an EditRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRoutineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_EXERCISE, PREFIX_EXERCISE_NUMBER);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRoutineCommand.MESSAGE_USAGE), pe);
        }

        checkRoutineCommandConflictFormat(argMultimap);

        EditRoutineDescriptor editRoutineDescriptor = new EditRoutineDescriptor();
        if (argMultimap.getValue(PREFIX_ROUTINE).isPresent()) {
            editRoutineDescriptor.setRoutineName(ParserUtil
                    .parseRoutineName(argMultimap.getValue(PREFIX_ROUTINE).get()));
        }

        if (argMultimap.getValue(PREFIX_EXERCISE).isPresent()
                || argMultimap.getValue(PREFIX_EXERCISE_NUMBER).isPresent()) {
            editRoutineDescriptor = parseExerciseForEdit(argMultimap, editRoutineDescriptor);
        }

        if (!editRoutineDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRoutineCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRoutineCommand(index, editRoutineDescriptor);
    }

    /**
     * Parses {@code Exercises exercise} into a {@code Exercise} if {@code exercise}'s part is non-empty.
     */
    private EditRoutineDescriptor parseExerciseForEdit(ArgumentMultimap argMultimap,
            EditRoutineDescriptor editRoutineDescriptor) throws ParseException {
        if (argMultimap.getValue(PREFIX_EXERCISE_NUMBER).isPresent()
                && argMultimap.getValue(PREFIX_EXERCISE).isPresent()) {
            String exerciseNumber = argMultimap.getValue(PREFIX_EXERCISE_NUMBER).get();
            String exerciseName = argMultimap.getValue(PREFIX_EXERCISE).get();
            EditRoutineDescriptor currEditRoutineDescriptor = new EditRoutineDescriptor(editRoutineDescriptor);
            try {
                Exercise exercise = new Exercise(exerciseName);
                Index exerciseNumberIndex = ParserUtil.parseIndex(exerciseNumber);
                currEditRoutineDescriptor.setExercise(exercise);
                currEditRoutineDescriptor.setExerciseIndex(exerciseNumberIndex);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX,
                        EditRoutineCommand.MESSAGE_USAGE), pe);
            } catch (IllegalArgumentException pe) {
                throw new ParseException(Exercise.MESSAGE_CONSTRAINTS, pe);
            }
            return currEditRoutineDescriptor;
        } else {
            checkExerciseFormatPresent(argMultimap);
            return new EditRoutineDescriptor();
        }
    }

    /**
     * Checks if {@code exercise}'s format part is non-empty.
     */
    private void checkExerciseFormatPresent(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_EXERCISE_NUMBER, PREFIX_EXERCISE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRoutineCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if {@code exercise}'s format consists of mix exercise and routineName parts.
     */
    private void checkRoutineCommandConflictFormat(ArgumentMultimap argMultimap) throws ParseException {
        boolean isMixCommand = argMultimap.getValue(PREFIX_ROUTINE).isPresent()
                && (argMultimap.getValue(PREFIX_EXERCISE).isPresent()
                || argMultimap.getValue(PREFIX_EXERCISE_NUMBER).isPresent());
        if (isMixCommand) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRoutineCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
