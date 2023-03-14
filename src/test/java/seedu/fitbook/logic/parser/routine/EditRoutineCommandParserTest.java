package seedu.fitbook.logic.parser.routine;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_INDEX;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_EXERCISE_INDEX_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_EXERCISE_NAME_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.INVALID_ROUTINE_NAME_DESC;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_ROUTINE_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.NAME_DESC_ROUTINE_STRENGTH;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_INDEX;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_CARDIO;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_ROUTINE_NAME_STRENGTH;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_ROUTINE;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_THIRD_ROUTINE;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.EditRoutineCommand;
import seedu.fitbook.logic.commands.EditRoutineCommand.EditRoutineDescriptor;
import seedu.fitbook.logic.parser.EditRoutineCommandParser;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.testutil.routine.EditRoutineDescriptorBuilder;

public class EditRoutineCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoutineCommand.MESSAGE_USAGE);

    private EditRoutineCommandParser parser = new EditRoutineCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ROUTINE_NAME_CARDIO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditRoutineCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ROUTINE_CARDIO, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ROUTINE_CARDIO, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid routine name
        assertParseFailure(parser, "1" + INVALID_ROUTINE_NAME_DESC, RoutineName.MESSAGE_CONSTRAINTS);

        // invalid exercise name followed by valid exercise index
        assertParseFailure(parser, "1" + INVALID_EXERCISE_NAME_DESC + EXERCISE_DESC_INDEX,
                Exercise.MESSAGE_CONSTRAINTS);

        // invalid exercise index followed by valid exercise name
        assertParseFailure(parser, "1" + INVALID_EXERCISE_INDEX_DESC + EXERCISE_DESC_PUSHUP,
                MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ROUTINE;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_INDEX + EXERCISE_DESC_PUSHUP;

        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withExercise(VALID_EXERCISE_PUSHUP).withExercisesIndex(VALID_EXERCISE_INDEX).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ROUTINE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ROUTINE_CARDIO;

        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withRoutineName(VALID_ROUTINE_NAME_CARDIO).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // routine name
        Index targetIndex = INDEX_FIRST_ROUTINE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ROUTINE_CARDIO;
        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withRoutineName(VALID_ROUTINE_NAME_CARDIO).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // exercises
        userInput = targetIndex.getOneBased() + EXERCISE_DESC_INDEX + EXERCISE_DESC_PUSHUP;
        descriptor = new EditRoutineDescriptorBuilder().withExercise(VALID_EXERCISE_PUSHUP)
                .withExercisesIndex(VALID_EXERCISE_INDEX).build();
        expectedCommand = new EditRoutineCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ROUTINE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ROUTINE_CARDIO + NAME_DESC_ROUTINE_STRENGTH;

        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withRoutineName(VALID_ROUTINE_NAME_STRENGTH).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_changeExercise_success() {
        Index targetIndex = INDEX_THIRD_ROUTINE;
        String userInput = targetIndex.getOneBased() + EXERCISE_DESC_INDEX + EXERCISE_DESC_PUSHUP;

        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withExercise(VALID_EXERCISE_PUSHUP).withExercisesIndex(VALID_EXERCISE_INDEX).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_changeRoutineName_success() {
        Index targetIndex = INDEX_THIRD_ROUTINE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ROUTINE_CARDIO;

        EditRoutineDescriptor descriptor = new EditRoutineDescriptorBuilder()
                .withRoutineName(VALID_ROUTINE_NAME_CARDIO).build();
        EditRoutineCommand expectedCommand = new EditRoutineCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
