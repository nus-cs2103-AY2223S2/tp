package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.tag.Language;
import seedu.address.model.tag.LanguageContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();


    @Test
    public void parse_validArgs_returnsListCommand() {
        Set<Tag> testTags = new HashSet<>();
        Set<Language> testLanguages = new HashSet<>();

        ListCommand expectedListCommand =
                new ListCommand(new TagContainsKeywordsPredicate(testTags),
                        new LanguageContainsKeywordsPredicate(testLanguages),
                        false);
        // empty argument
        assertParseSuccess(parser, "", expectedListCommand);

        testTags.add(new Tag("friend"));

        testLanguages.add(new Language("python"));

        // no leading and trailing whitespaces
        expectedListCommand =
                new ListCommand(new TagContainsKeywordsPredicate(testTags),
                        new LanguageContainsKeywordsPredicate(testLanguages),
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
