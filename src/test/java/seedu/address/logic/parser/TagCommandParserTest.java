package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.tag.ModuleTag;


public class TagCommandParserTest {
    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(TagCommand.MESSAGE_NO_TAGS));
    }

    @Test
    public void parse_validArgs_returnsTagCommand() {
        createValidArgsReturnsTagCommand(
                new ContactIndex(1),
                new HashSet<ModuleTag>() {{
                    add(new ModuleTag("CS1234"));
                    }},
                "1 m/CS1234");

    }

    public void createValidArgsReturnsTagCommand(ContactIndex index, Set<ModuleTag> modules, String userInput) {
        TagCommand expectedTagCommand = new TagCommand(index, modules);

        assertParseSuccess(parser, userInput, expectedTagCommand);
    }



}
