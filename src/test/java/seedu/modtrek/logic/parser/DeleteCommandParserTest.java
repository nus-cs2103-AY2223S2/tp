package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_MA2002;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.model.module.Code;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private final DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS1101S.getCode());
        assertParseSuccess(parser, CODE_DESC_CS1101S, new DeleteCommand(false, codesToDelete));
    }

    @Test
    public void parse_validMultipleArgs_returnsDeleteCommand() {
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS1101S.getCode());
        codesToDelete.add(MA2002.getCode());
        assertParseSuccess(parser, CODE_DESC_CS1101S + CODE_DESC_MA2002, new DeleteCommand(false, codesToDelete));
    }

    @Test
    public void parse_validArgsAll_returnsDeleteCommand() {
        Set<Code> codesToDelete = new HashSet<>();
        assertParseSuccess(parser, "all", new DeleteCommand(true, codesToDelete));
    }

    @Test
    public void parse_detailMissing_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_CODE, Code.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
