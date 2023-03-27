package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
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
    public void parse_validArgs_returnsTagCommand() {
        createModuleValidArgsReturnsTagCommand(
                new ContactIndex(1),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    }},
                "1 m/CS1234");

        createGroupValidArgsReturnsTagCommand(
                new ContactIndex(2),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Enemy"));
                }},
                "2 g/Enemy");
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
