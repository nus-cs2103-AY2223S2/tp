package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.commands.SearchCommand;
import seedu.connectus.model.person.FieldsContainKeywordsPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SearchCommand.MESSAGE_NO_KEYWORDS + "\n" + SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        FieldsContainKeywordsPredicate predicate;

        // no leading and trailing whitespaces for predicate keyword
        SearchCommand expectedSearchCommand =
                new SearchCommand(new FieldsContainKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedSearchCommand);

        //search name with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setName("lice yu");
        assertParseSuccess(parser, "n/lice yu", new SearchCommand(predicate));
        assertParseSuccess(parser, "n/  \t lice yu    ", new SearchCommand(predicate));

        predicate = new FieldsContainKeywordsPredicate();
        predicate.setName("Alic");
        assertParseSuccess(parser, "n/Alic", new SearchCommand(predicate));
        assertParseSuccess(parser, "n/\n   Alic  ", new SearchCommand(predicate));

        //search name with multiple keywords
        predicate.setName("yu");
        assertParseSuccess(parser, "n/\nAlic n/\nyu", new SearchCommand(predicate));
        assertParseSuccess(parser, "n/\n   Alic  \t n/ yu    ", new SearchCommand(predicate));

        //search phone with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setPhone("98334");
        assertParseSuccess(parser, "p/98334", new SearchCommand(predicate));
        assertParseSuccess(parser, "p/  \t 98334    ", new SearchCommand(predicate));

        //search phone with multiple keywords
        predicate.setPhone("98724");
        assertParseSuccess(parser, "p/98334 p/98724", new SearchCommand(predicate));
        assertParseSuccess(parser, "p/ 98334   \t  p/ \n   98724", new SearchCommand(predicate));

        //search email with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setEmail("lice");
        assertParseSuccess(parser, "e/lice", new SearchCommand(predicate));
        assertParseSuccess(parser, "e/  \t lice    ", new SearchCommand(predicate));

        //search email with multiple keywords
        predicate.setEmail("gmail.com");
        assertParseSuccess(parser, "e/lice e/gmail.com", new SearchCommand(predicate));
        assertParseSuccess(parser, "e/ lice   \t  e/ \n   gmail.com", new SearchCommand(predicate));

        //search address with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setAddress("blk");
        assertParseSuccess(parser, "a/blk", new SearchCommand(predicate));
        assertParseSuccess(parser, "a/  \t blk    ", new SearchCommand(predicate));

        //search address with multiple keywords
        predicate.setAddress("clementi");
        assertParseSuccess(parser, "a/blk a/clementi", new SearchCommand(predicate));
        assertParseSuccess(parser, "a/ blk   \t  a/ \n   clementi", new SearchCommand(predicate));

        //search instagram with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setInstagram("ale");
        assertParseSuccess(parser, "ig/ale", new SearchCommand(predicate));
        assertParseSuccess(parser, "ig/  \t ale    ", new SearchCommand(predicate));

        //search instagram with multiple keywords
        predicate.setInstagram("ex");
        assertParseSuccess(parser, "ig/ale ig/ex", new SearchCommand(predicate));
        assertParseSuccess(parser, "ig/ ale   \t  ig/ \n   ex", new SearchCommand(predicate));

        //search whatsapp with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setWhatsapp("942");
        assertParseSuccess(parser, "wa/942", new SearchCommand(predicate));
        assertParseSuccess(parser, "wa/  \t 942    ", new SearchCommand(predicate));

        //search whatsapp with multiple keywords
        predicate.setWhatsapp("234");
        assertParseSuccess(parser, "wa/942 wa/234", new SearchCommand(predicate));
        assertParseSuccess(parser, "wa/ 942   \t  wa/ \n   234", new SearchCommand(predicate));

        //search telegram with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setTelegram("ale");
        assertParseSuccess(parser, "tg/ale", new SearchCommand(predicate));
        assertParseSuccess(parser, "tg/  \t ale    ", new SearchCommand(predicate));

        //search telegram with multiple keywords
        predicate.setTelegram("ex");
        assertParseSuccess(parser, "tg/ale tg/ex", new SearchCommand(predicate));
        assertParseSuccess(parser, "tg/ ale   \t  tg/ \n   ex", new SearchCommand(predicate));

        //search remark with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setRemarks(Collections.singleton("friend"));
        assertParseSuccess(parser, "r/friend", new SearchCommand(predicate));
        assertParseSuccess(parser, "r/  \t friend    ", new SearchCommand(predicate));

        //search remarks with multiple keywords
        Set<String> set = new HashSet<>();
        set.add("friend");
        set.add("classmate");
        predicate.setRemarks(set);
        assertParseSuccess(parser, "r/friend r/classmate", new SearchCommand(predicate));
        assertParseSuccess(parser, "r/ friend   \t  r/ \n   classmate", new SearchCommand(predicate));

        //search birthday with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setBirthday("jan");
        assertParseSuccess(parser, "b/jan", new SearchCommand(predicate));
        assertParseSuccess(parser, "b/  \t jan    ", new SearchCommand(predicate));

        //search birthday with multiple keywords
        predicate.setBirthday("2003");
        assertParseSuccess(parser, "b/jan b/2003", new SearchCommand(predicate));
        assertParseSuccess(parser, "b/ jan   \t  b/ \n   2003", new SearchCommand(predicate));

        //search modules with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setModules(Collections.singleton("CS"));
        assertParseSuccess(parser, "mod/CS", new SearchCommand(predicate));
        assertParseSuccess(parser, "mod/  \t CS    ", new SearchCommand(predicate));

        //search modules with multiple keywords
        set = new HashSet<>();
        set.add("CS");
        set.add("ES");
        predicate.setModules(set);
        assertParseSuccess(parser, "mod/CS mod/ES", new SearchCommand(predicate));
        assertParseSuccess(parser, "mod/ CS   \t  mod/ \n   ES", new SearchCommand(predicate));

        //search ccas with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setCcas(Collections.singleton("chess"));
        assertParseSuccess(parser, "cca/chess", new SearchCommand(predicate));
        assertParseSuccess(parser, "cca/  \t chess    ", new SearchCommand(predicate));

        //search ccas with multiple keywords
        set = new HashSet<>();
        set.add("chess");
        set.add("gardening");
        predicate.setCcas(set);
        assertParseSuccess(parser, "cca/chess cca/gardening", new SearchCommand(predicate));
        assertParseSuccess(parser, "cca/ chess   \t  cca/ \n   gardening", new SearchCommand(predicate));

        //search ccaPositions with one keyword
        predicate = new FieldsContainKeywordsPredicate();
        predicate.setCcaPositions(Collections.singleton("Comp"));
        assertParseSuccess(parser, "ccapos/Comp", new SearchCommand(predicate));
        assertParseSuccess(parser, "ccapos/  \t Comp    ", new SearchCommand(predicate));

        //search ccaPositions with multiple keywords
        set = new HashSet<>();
        set.add("Comp");
        set.add("Sci");
        predicate.setCcaPositions(set);
        assertParseSuccess(parser, "ccapos/Comp ccapos/Sci", new SearchCommand(predicate));
        assertParseSuccess(parser, "ccapos/ Comp   \t  ccapos/ \n   Sci", new SearchCommand(predicate));
    }

}
