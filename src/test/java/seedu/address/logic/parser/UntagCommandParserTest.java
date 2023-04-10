package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class UntagCommandParserTest {
    private final UntagCommandParser parser = new UntagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(UntagCommand.MESSAGE_NO_TAGS));
    }

    @Test
    public void parse_emptyArgWithPrefix_throwsParseException() {
        assertParseFailure(parser, "1 g/", String.format(UntagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, " g/", String.format(UntagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, "1 m/", String.format(UntagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, " m/", String.format(UntagCommand.MESSAGE_NO_TAGS));
    }

    @Test
    public void parse_validArgs_returnsUntagCommand() {
        createModuleValidArgsReturnsUntagCommand(
                new ContactIndex(1),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    }},
                "1 m/CS1234");

        createModuleValidArgsReturnsUntagCommand(
                new ContactIndex(3),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS2345"));
                    add(new ModuleTag("CS3456"));
                }},
                "3 m/CS2345 m/CS3456");
        createGroupValidArgsReturnsUntagCommand(
                new ContactIndex(2),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Friend"));
                }},
                "2 g/Friend");

        createGroupValidArgsReturnsUntagCommand(
                new ContactIndex(4),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Controller"));
                    add(new GroupTag("Mouse"));
                }},
                "4 g/Controller g/Mouse");
    }

    @Test
    public void parse_bothTagsInputted_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("m/CS2100 g/TA"));
        assertParseFailure(parser, " m/ g/", String.format(UntagCommand.MESSAGE_BOTH_TAGS_INPUTTED));
    }

    private void createModuleValidArgsReturnsUntagCommand(ContactIndex index, Set<ModuleTag> modules,
                                                          String userInput) {
        UntagCommand expectedUntagCommand = new UntagCommand(index, modules, TagType.MODULE);

        assertParseSuccess(parser, userInput, expectedUntagCommand);
    }

    private void createGroupValidArgsReturnsUntagCommand(ContactIndex index, Set<GroupTag> groups, String userInput) {
        UntagCommand expectedUntagCommand = new UntagCommand(index, groups, TagType.GROUP);

        assertParseSuccess(parser, userInput, expectedUntagCommand);
    }
}
