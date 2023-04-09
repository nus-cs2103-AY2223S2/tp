package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.quickcontacts.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.ExportPersonsCommand;

public class ExportPersonsParserTest {
    private final ExportPersonsParser parser = new ExportPersonsParser();

    @Test
    public void parse_success() {
        String input = " " + PREFIX_PERSON + INDEX_FIRST_PERSON.getOneBased();
        assertParseSuccess(parser, input, new ExportPersonsCommand(List.of(INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_noPersons_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportPersonsCommand.MESSAGE_USAGE));
    }
}
