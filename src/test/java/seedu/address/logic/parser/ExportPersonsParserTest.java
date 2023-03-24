package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportPersonsCommand;

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

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "", ExportPersonsCommand.INDEX_NOT_FOUND);
    }

}
