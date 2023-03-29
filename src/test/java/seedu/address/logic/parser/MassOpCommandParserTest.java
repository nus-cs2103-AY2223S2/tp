package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MassOpCommand;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.tag.Tag;

public class MassOpCommandParserTest {

    private final MassOpCommandParser parser = new MassOpCommandParser();

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "zfs/abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassOpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_someArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        MassOpCommand expectedMassOpCommand =
                new MassOpCommand(new FieldsMatchRegexPredicate(
                        Collections.singletonList(".*l.*"),
                        Collections.singletonList(".*95.*"),
                        new ArrayList<>(),
                        Collections.singletonList(".*[0-9].*"),
                        new ArrayList<>(),
                        new ArrayList<>()
                ), new Tag("friends"), false);
        assertParseSuccess(parser, "tag friends n/l p/95 a/[0-9]", expectedMassOpCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "tag friends \n n/l \n \t p/95 a/[0-9]  \t", expectedMassOpCommand);
    }

//    @Test
//    public void parse_multiArgs_returnsFilterCommand() {
//        // no leading and trailing whitespaces
//        MassOpCommand expectedMassOpCommand =
//                new MassOpCommand(new FieldsMatchRegexPredicate(
//                        Arrays.asList(".*l.*", ".*Ge.*"),
//                        Arrays.asList(".*95.*", ".*94.*"),
//                        Collections.singletonList(".*@.*"),
//                        Collections.singletonList(".*[0-9].*"),
//                        new ArrayList<>(),
//                        Arrays.asList(".*fri.*", ".*col.*")
//                ), new Tag("friends"), true);
//        assertParseSuccess(parser, "delete friends e/@ n/l p/95 t/fri a/[0-9] p/94 n/Ge t/col", expectedMassOpCommand);
//
//        // multiple whitespaces between keywords
//        assertParseSuccess(parser,
//                "delete friends \n e/@ n/l \n\n p/95\n \t t/fri a/[0-9] n/Ge p/94 \n t/col \t",
//                expectedMassOpCommand);
//    }
}
