package seedu.socket.logic.parser;

import static seedu.socket.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.ListCommand;
import seedu.socket.model.person.predicate.ListCommandLanguagePredicate;
import seedu.socket.model.person.predicate.ListCommandTagPredicate;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();


    @Test
    public void parse_validArgs_returnsListCommand() {
        Set<Tag> testTags = new HashSet<>();
        Set<Language> testLanguages = new HashSet<>();

        ListCommand expectedListCommand =
                new ListCommand(new ListCommandTagPredicate(testTags),
                        new ListCommandLanguagePredicate(testLanguages),
                        false);
        // empty argument
        assertParseSuccess(parser, "", expectedListCommand);

        testTags.add(new Tag("friend"));

        testLanguages.add(new Language("python"));

        // no leading and trailing whitespaces
        expectedListCommand =
                new ListCommand(new ListCommandTagPredicate(testTags),
                        new ListCommandLanguagePredicate(testLanguages),
                        true);
        assertParseSuccess(parser, " t/friend l/python", expectedListCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/friend \n \t l/python  \t", expectedListCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_LANGUAGE_DESC, Language.MESSAGE_CONSTRAINTS); // invalid language
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid language followed by valid tag
        assertParseFailure(parser, INVALID_LANGUAGE_DESC + " t/friends", Language.MESSAGE_CONSTRAINTS);
        // invalid tag followed by valid language
        assertParseFailure(parser, INVALID_TAG_DESC + " l/python", Tag.MESSAGE_CONSTRAINTS);
        // valid language followed by invalid tag
        assertParseFailure(parser, "l/python" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        // valid tag followed by invalid language
        assertParseFailure(parser, "t/friends" + INVALID_LANGUAGE_DESC, Language.MESSAGE_CONSTRAINTS);
    }

}
