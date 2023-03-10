package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.student.Remark;

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
}
