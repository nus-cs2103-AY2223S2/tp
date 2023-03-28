package seedu.address.logic.parser.homework;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;


public class DeleteHomeworkCommandParserTest {
    public static final String VALID_DEADLINE = "2027-04-31T12:00";
    private AddressBookParser mainParser = new AddressBookParser();
    private DeleteHomeworkCommandParser parser = new DeleteHomeworkCommandParser();

    public void addStudent() {
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
    }

    public void addHomework(int idx) {
        String input = "new-homework"
                + " name/" + VALID_NAME_AMY
                + " homework/" + "Biology Chapter " + idx
                + " deadline/" + VALID_DEADLINE;
        try {
            mainParser.parseCommand(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTutorPro() {
        try {
            mainParser.parseCommand("clear");
        } catch (ParseException e) {
            //Clear command will not cause a ParseException
            throw new RuntimeException(e);
        }
    }


    @Test
    public void parse_indexIsNonPositive_failure() {
        clearTutorPro();
        addStudent();

        //Add 4 homeworks
        for (int i = 1; i < 5; i++) {
            addHomework(i);
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
