package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

public class AddTagToPersonCommandParserTest {

    private final AddTagToPersonCommandParser parser = new AddTagToPersonCommandParser();

    @Test
    public void parse_validArgs_returnsCommand() {
        var tags = new HashSet<Tag>();
        tags.add(new Tag("tagg"));
        var modules = new HashSet<Module>();
        modules.add(new Module("MOD1234"));
        assertParseSuccess(parser, "1 t/tagg mod/MOD1234",
            new AddTagToPersonCommand(INDEX_FIRST_PERSON, new AddTagToPersonCommand.AddTagDescriptor(tags, modules)));
    }

    @Test
    public void parse_unrelatedArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noTagSpecified_throwsParseException() {
        assertParseFailure(parser, "1 t/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 t/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 t/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noModuleSpecified_throwsParseException() {
        assertParseFailure(parser, "1 mod/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nothingSpecified_throwsParseException() {
        assertParseFailure(parser, "1 mod/ t/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/  t/ ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 mod/\t t/\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE));
    }
}
