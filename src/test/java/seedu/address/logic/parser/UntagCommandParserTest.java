package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UntagCommand;
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
    public void parse_validArgs_returnsUntagCommand() {
        createModuleValidArgsReturnsUntagCommand(
                new ContactIndex(1),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    }},
                "1 m/CS1234");
        createGroupValidArgsReturnsUntagCommand(
                new ContactIndex(2),
                new HashSet<GroupTag>() {{
                    add(new GroupTag("Friend"));
                }},
                "2 g/Friend");
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
