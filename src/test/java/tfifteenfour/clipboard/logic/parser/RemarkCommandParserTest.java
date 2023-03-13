package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_REMARK;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tfifteenfour.clipboard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.RemarkCommand;
import tfifteenfour.clipboard.model.student.Remark;

class RemarkCommandParserTest {
    private static final String EMPTY_REMARK = "";
    private static final String REMARK_STUB = "Some remark";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //no index specified
        assertParseFailure(parser, REMARK_STUB, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyRemark_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + "";
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, new Remark(EMPTY_REMARK));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_nonEmptyRemark_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + REMARK_STUB;
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, new Remark(REMARK_STUB));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
