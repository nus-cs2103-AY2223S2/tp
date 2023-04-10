package fasttrack.logic.parser.edit;

import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.edit.EditCategoryCommand;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;

public class EditCategoryCommandParserTest {

    public static final String MESSAGE_INVALID_INDEX = "The index provided is invalid.";
    private final EditCategoryCommandParser parser = new EditCategoryCommandParser();

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_noArgumentsExceptIndex() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditCategoryCommand expectedCommand = new EditCategoryCommand(toUseForExpected, null, null);
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_editCategoryNameParsed_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditCategoryCommand expectedCommand = new EditCategoryCommand(toUseForExpected, "changedName", null);
        assertParseSuccess(parser, "1 c/changedName", expectedCommand);
    }

    @Test
    public void parse_editCategorySummary_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditCategoryCommand expectedCommand = new EditCategoryCommand(toUseForExpected, null, "changedSummary");
        assertParseSuccess(parser, "1 s/changedSummary", expectedCommand);
    }

    @Test
    public void parse_editBothNameSummary_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditCategoryCommand expectedCommand = new EditCategoryCommand(toUseForExpected, "changedName",
                "changedSummary");
        assertParseSuccess(parser, "1 c/changedName s/changedSummary", expectedCommand);
    }

    @Test
    public void parse_editBothNameSummarySwapPos_success() throws ParseException {
        Index toUseForExpected = ParserUtil.parseIndex("1");
        EditCategoryCommand expectedCommand = new EditCategoryCommand(toUseForExpected, "changedName",
                "changedSummary");
        assertParseSuccess(parser, "1 s/changedSummary c/changedName", expectedCommand);
    }

}
