package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;


public class TagCommandParserTest {
    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(TagCommand.MESSAGE_NO_TAGS));
    }

    @Test
    public void parse_emptyArgWithPrefix_throwsParseException() {
        assertParseFailure(parser, "1 g/", String.format(TagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, " g/", String.format(TagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, "1 m/", String.format(TagCommand.MESSAGE_NO_TAGS));
        assertParseFailure(parser, " m/", String.format(TagCommand.MESSAGE_NO_TAGS));
    }

    @Test
    public void parse_validArgs_returnsTagCommand() {
        createModuleValidArgsReturnsTagCommand(
                new ContactIndex(1),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    }},
                "1 m/CS1234");

        createModuleValidArgsReturnsTagCommand(
                new ContactIndex(3),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    add(new ModuleTag("CS2345"));
                }},
                "3 m/CS1234 m/CS2345");

        createGroupValidArgsReturnsTagCommand(
                new ContactIndex(2),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Enemy"));
                }},
                "2 g/Enemy");

        createGroupValidArgsReturnsTagCommand(
                new ContactIndex(4),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Bro"));
                    add(new GroupTag("Family"));
                }},
                "4 g/Bro g/Family");
    }

    @Test
    public void parse_invalidContactIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a m/CS2100"));
    }

    @Test
    public void parse_bothTagsInputted_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("m/CS2100 g/TA"));
        assertParseFailure(parser, " m/ g/", String.format(TagCommand.MESSAGE_BOTH_TAGS_INPUTTED));
    }



    private void createModuleValidArgsReturnsTagCommand(ContactIndex index, Set<ModuleTag> modules, String userInput) {
        TagCommand expectedTagCommand = new TagCommand(index, modules, TagType.MODULE);

        assertParseSuccess(parser, userInput, expectedTagCommand);
    }

    private void createGroupValidArgsReturnsTagCommand(ContactIndex index, Set<GroupTag> groups, String userInput) {
        TagCommand expectedTagCommand = new TagCommand(index, groups, TagType.GROUP);

        assertParseSuccess(parser, userInput, expectedTagCommand);
    }

}
