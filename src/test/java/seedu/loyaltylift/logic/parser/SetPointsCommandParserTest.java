package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.SetPointsCommand;
import seedu.loyaltylift.model.customer.Points;

public class SetPointsCommandParserTest {
    private SetPointsCommandParser parser = new SetPointsCommandParser();
    private final Integer nonEmptyPoints = 100;

    @Test
    public void parse_indexSpecified_success() {
        // must have points, /pt with no integer afterwards will not be parsed successfully
        Index targetIndex = INDEX_FIRST_CUSTOMER;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_POINTS + nonEmptyPoints;
        SetPointsCommand expectedCommand = new SetPointsCommand(INDEX_FIRST_CUSTOMER, new Points(nonEmptyPoints));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPointsCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, SetPointsCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, SetPointsCommand.COMMAND_WORD + " " + nonEmptyPoints, expectedMessage);
    }
}
