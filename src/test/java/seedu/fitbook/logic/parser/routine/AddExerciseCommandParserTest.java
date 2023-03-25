package seedu.fitbook.logic.parser.routine;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_PUSHUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.EXERCISE_DESC_SITUP;
import static seedu.fitbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_INDEX;
import static seedu.fitbook.logic.commands.CommandTestUtil.VALID_EXERCISE_PUSHUP;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.AddExerciseCommand;
import seedu.fitbook.logic.parser.AddExerciseCommandParser;
import seedu.fitbook.model.routines.Exercise;

public class AddExerciseCommandParserTest {
    private AddExerciseCommandParser parser = new AddExerciseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index index = Index.fromOneBased(1);
        Exercise exercise = new Exercise(VALID_EXERCISE_PUSHUP);
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_EXERCISE_INDEX + EXERCISE_DESC_PUSHUP,
                new AddExerciseCommand(index, exercise));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExerciseCommand.MESSAGE_USAGE);

        // missing routine index prefix
        assertParseFailure(parser, EXERCISE_DESC_SITUP, expectedMessage);
    }

}
