package seedu.address.logic.parser.homework;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddressBookParser;

public class DeleteHomeworkCommandParserTest {
    public static final String VALID_DEADLINE = "2027-04-31T12:00";
    private AddressBookParser mainParser = new AddressBookParser();
    private DeleteHomeworkCommandParser parser = new DeleteHomeworkCommandParser();
    @Test
    public void parse_indexIsNonPositive_failure() {
        //Negative index
        String input1 = " name/" + VALID_NAME_AMY
                + " index/-1";
        assertParseFailure(parser, input1, MESSAGE_INVALID_INDEX);

        //Zero index
        String input2 = " name/" + VALID_NAME_AMY
                + " index/0";
        assertParseFailure(parser, input2, MESSAGE_INVALID_INDEX);

        //Non-numeric index
        String input3 = " name/" + VALID_NAME_AMY
                + " index/abc";
        assertParseFailure(parser, input3, MESSAGE_INVALID_INDEX);

    }
}
