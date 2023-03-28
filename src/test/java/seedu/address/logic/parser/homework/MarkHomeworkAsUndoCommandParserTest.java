package seedu.address.logic.parser.homework;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.homework.DeleteHomeworkCommandParserTest.VALID_DEADLINE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.homework.MarkHomeworkAsUndoCommand;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;



public class MarkHomeworkAsUndoCommandParserTest {
    private MarkHomeworkAsUndoCommandParser parser = new MarkHomeworkAsUndoCommandParser();
    private AddressBookParser mainParser = new AddressBookParser();

    @Test
    public void parse_allFieldsNotPresent_failure() {
        String input1 = " name/Amy";
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        MarkHomeworkAsUndoCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input1, expected);

        String input2 = " index/1";
        assertParseFailure(parser, input2, expected);
    }

    @Test
    public void parse_indexIsNonPositive_failure() {
        //Add a new student
        try {
            mainParser.parseCommand("new-student name/" + VALID_NAME_AMY
                                            + " phone/" + VALID_PHONE_AMY
                                            + " email/" + VALID_EMAIL_AMY
                                            + " address/" + VALID_ADDRESS_AMY
            );
        } catch (ParseException e) {
            //The above command will not throw any ParseException.
            throw new RuntimeException(e);
        }

        //Add 4 homeworks
        for (int i = 1; i < 5; i++) {
            String input = "new-homework"
                    + " name/" + VALID_NAME_AMY
                    + " homework/" + "Biology Chapter " + i
                    + " deadline/" + VALID_DEADLINE;
            try {
                mainParser.parseCommand(input);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

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
