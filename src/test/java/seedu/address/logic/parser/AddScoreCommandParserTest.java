package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_FULL_1;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_FULL_DATE_IN_FUTURE;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_FULL_INVALID_DATE_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_FULL_MISSING_LABEL_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_FULL_WRONG_VALUE;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_MISSING_ALL_VARIABLES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCORE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.model.score.Date;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;
import seedu.address.testutil.ScoreBuilder;

class AddScoreCommandParserTest {

    private AddScoreCommandParser parser = new AddScoreCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedIndex = INDEX_FIRST_SCORE;
        Score expectedScore = new ScoreBuilder().build();

        assertParseSuccess(parser, "1" + SCORE_FULL_1,
                new AddScoreCommand(expectedIndex, expectedScore));
    }

    @Test
    public void parse_indexMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, SCORE_FULL_1, expectedMessage);
    }

    @Test
    public void parse_labelFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        // missing score label prefix
        assertParseFailure(parser, "1" + SCORE_FULL_MISSING_LABEL_PREFIX, expectedMessage);
    }

    @Test
    public void parse_valueFieldMissing_failure() {
        String expectedMessage = String.format(ScoreValue.MESSAGE_CONSTRAINTS);

        // wrong value
        assertParseFailure(parser, "1" + SCORE_FULL_WRONG_VALUE, expectedMessage);
    }

    @Test
    public void parse_invalidDate_failure() {
        String expectedMessage = String.format(Date.MESSAGE_CONSTRAINTS);

        // invalid date format
        assertParseFailure(parser, "1" + SCORE_FULL_INVALID_DATE_FORMAT, expectedMessage);
    }

    @Test
    public void parse_futureDate_failure() {
        String expectedMessage = String.format(Date.MESSAGE_INVALID_DATE);

        // invalid date format
        assertParseFailure(parser, "1" + SCORE_FULL_DATE_IN_FUTURE, expectedMessage);
    }

    @Test
    public void parse_missingAllVariables() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        // missing score date
        assertParseFailure(parser, "1" + SCORE_MISSING_ALL_VARIABLES, expectedMessage);
    }
}
