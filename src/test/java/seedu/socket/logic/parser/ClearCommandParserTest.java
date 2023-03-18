package seedu.socket.logic.parser;

import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.ClearCommand;
import seedu.socket.model.person.predicate.TagContainsKeywordsPredicate;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_emptyArg_returnsClearCommand() {
        List<String> emptyKeywords = Collections.emptyList();
        ClearCommand command = new ClearCommand(new TagContainsKeywordsPredicate(emptyKeywords));
        assertParseSuccess(parser, "", command);
    }
}
