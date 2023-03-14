package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.FieldsMatchRegexPredicate;

public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "zfs/abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_someArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new FieldsMatchRegexPredicate(
                        Collections.singletonList(".*l.*"),
                        Collections.singletonList(".*95.*"),
                        new ArrayList<>(),
                        Collections.singletonList(".*[0-9].*"),
                        new ArrayList<>()
                ));
        assertParseSuccess(parser, " n/l p/95 a/[0-9]", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/l \n \t p/95 a/[0-9]  \t", expectedFilterCommand);
    }

    @Test
    public void parse_multiArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new FieldsMatchRegexPredicate(
                        Arrays.asList(".*l.*", ".*Ge.*"),
                        Arrays.asList(".*95.*", ".*94.*"),
                        Collections.singletonList(".*@.*"),
                        Collections.singletonList(".*[0-9].*"),
                        Arrays.asList(".*fri.*", ".*col.*")
                ));
        assertParseSuccess(parser, " e/@ n/l p/95 t/fri a/[0-9] p/94 n/Ge t/col", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n e/@ n/l \n\n p/95\n \t t/fri a/[0-9] n/Ge p/94 \n t/col \t", expectedFilterCommand);
    }
}
