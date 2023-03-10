package seedu.fitbook.logic.parser;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.logic.commands.EditRoutineCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.routines.Exercise;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;

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
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_EXERCISE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRoutineCommand.MESSAGE_USAGE), pe);
        }

        EditRoutineDescriptor editRoutineDescriptor = new EditRoutineDescriptor();
        if (argMultimap.getValue(PREFIX_ROUTINE).isPresent()) {
            editRoutineDescriptor.setRoutineName(ParserUtil
                    .parseRoutineName(argMultimap.getValue(PREFIX_ROUTINE).get()));
        }
        parseExercisesForEdit(argMultimap.getAllValues(PREFIX_EXERCISE))
                .ifPresent(editRoutineDescriptor::setExercises);
        if (!editRoutineDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRoutineCommand.MESSAGE_NOT_EDITED);
        }
        return new EditRoutineCommand(index, editRoutineDescriptor);
    }

    /**
     * Parses {@code Collection<String> exercises} into a {@code Set<Exercise>} if {@code exercises} is non-empty.
     * If {@code exercises} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Exercise>} containing zero exercises.
     */
    private Optional<Set<Exercise>> parseExercisesForEdit(Collection<String> exercises) throws ParseException {
        assert exercises != null;

        if (exercises.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> exerciseSet = exercises.size() == 1 && exercises.contains("")
                ? Collections.emptySet() : exercises;
        return Optional.of(ParserUtil.parseExercises(exerciseSet));
    }
}
