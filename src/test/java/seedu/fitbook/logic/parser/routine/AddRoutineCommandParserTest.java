package seedu.fitbook.logic.parser.routine;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_SITUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_EXERCISE_NAME_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_ROUTINE_NAME_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_ROUTINE_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_ROUTINE_STRENGTH;
import static seedu.fitbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.fitbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_SITUP;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbook.testutil.routine.TypicalRoutines.CARDIO;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.AddRoutineCommand;
import seedu.fitbook.logic.parser.AddRoutineCommandParser;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.testutil.routine.RoutineBuilder;


public class AddRoutineCommandParserTest {
    private AddRoutineCommandParser parser = new AddRoutineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Routine expectedRoutine = new RoutineBuilder(CARDIO).withExercises(VALID_EXERCISE_PUSHUP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_ROUTINE_CARDIO + EXERCISE_DESC_PUSHUP,
                new AddRoutineCommand(expectedRoutine));

        // multiple routine name - last routine name accepted
        assertParseSuccess(parser, NAME_DESC_ROUTINE_STRENGTH + NAME_DESC_ROUTINE_CARDIO + EXERCISE_DESC_PUSHUP,
                new AddRoutineCommand(expectedRoutine));


        // multiple exercises - all accepted
        Routine expectedRoutineMultipleRoutines = new RoutineBuilder(CARDIO).withExercises(VALID_EXERCISE_PUSHUP,
                VALID_EXERCISE_SITUP).build();
        assertParseSuccess(parser, NAME_DESC_ROUTINE_CARDIO + EXERCISE_DESC_PUSHUP + EXERCISE_DESC_SITUP,
                new AddRoutineCommand(expectedRoutineMultipleRoutines));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoutineCommand.MESSAGE_USAGE);

        // missing routine name prefix
        assertParseFailure(parser, EXERCISE_DESC_PUSHUP + EXERCISE_DESC_SITUP,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid routine name
        assertParseFailure(parser, INVALID_ROUTINE_NAME_DESC + EXERCISE_DESC_SITUP + EXERCISE_DESC_PUSHUP,
                RoutineName.MESSAGE_CONSTRAINTS);

        // invalid exercise
        assertParseFailure(parser, NAME_DESC_ROUTINE_STRENGTH + INVALID_EXERCISE_NAME_DESC,
                Exercise.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NAME_DESC_ROUTINE_STRENGTH + INVALID_EXERCISE_NAME_DESC
                + EXERCISE_DESC_SITUP, Exercise.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ROUTINE_CARDIO
                        + EXERCISE_DESC_PUSHUP + EXERCISE_DESC_SITUP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoutineCommand.MESSAGE_USAGE));
    }
}
