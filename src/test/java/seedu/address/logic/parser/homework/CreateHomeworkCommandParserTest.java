package seedu.address.logic.parser.homework;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.homework.CreateHomeworkCommand;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;




public class CreateHomeworkCommandParserTest {

    public static final String VALID_DEADLINE = "2027-04-31T12:00";
    private static final String VALID_HOMEWORK_NAME = "Biology";
    private CreateHomeworkCommandParser parser = new CreateHomeworkCommandParser();
    private AddressBookParser mainParser = new AddressBookParser();

    public void clearTutorPro() {
        try {
            mainParser.parseCommand("clear");
        } catch (ParseException e) {
            //Clear command will not cause a ParseException
        }
    }
    @Test
    public void parse_fieldsMissing_exceptionThrown() throws ParseException {
        clearTutorPro();
        //Add a new student
        mainParser.parseCommand("new-student name/" + VALID_NAME_AMY
                + " phone/" + VALID_PHONE_AMY
                + " email/" + VALID_EMAIL_AMY
                + " address/" + VALID_ADDRESS_AMY
        );

        //Deadline missing
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                         CreateHomeworkCommand.MESSAGE_USAGE);
        String input1 = "name/Amy"
                    + " homework/" + VALID_HOMEWORK_NAME;
        assertParseFailure(parser, input1, expected);

        //Homework Name missing
        String input2 = "name/Amy"
                + " deadline/" + VALID_DEADLINE;
        assertParseFailure(parser, input2, expected);
    }

    @Test
    public void parse_allFieldsInAnyOrder_success() throws ParseException {
        clearTutorPro();
        //Add a new student
        mainParser.parseCommand("new-student name/" + VALID_NAME_AMY
                                         + " phone/" + VALID_PHONE_AMY
                                         + " email/" + VALID_EMAIL_AMY
                                         + " address/" + VALID_ADDRESS_AMY
        );

        //Ideal ordering: name-hw-deadline
        String idealInput = "new-homework name/Amy homework/Biology deadline/" + VALID_DEADLINE;
        CreateHomeworkCommand expected = (CreateHomeworkCommand) mainParser.parseCommand(idealInput);

        //hw-deadline-name
        String input1 = " homework/" + VALID_HOMEWORK_NAME
                + " deadline/" + VALID_DEADLINE
                + " name/Amy";
        assertParseSuccess(parser, input1, expected);

        //deadline-hw-name
        String input2 = " deadline/" + VALID_DEADLINE
                + " homework/" + VALID_HOMEWORK_NAME
                + " name/Amy";
        assertParseSuccess(parser, input2, expected);


        //hw-name-deadline
        String input3 = " homework/" + VALID_HOMEWORK_NAME
                + " name/Amy"
                + " deadline/" + VALID_DEADLINE;
        assertParseSuccess(parser, input3, expected);

        //deadline-name-hw
        String input4 = " deadline/" + VALID_DEADLINE
                + " name/Amy"
                + " homework/" + VALID_HOMEWORK_NAME;

        assertParseSuccess(parser, input4, expected);

        //name-deadline-hw
        String input5 = " name/Amy"
                + " deadline/" + VALID_DEADLINE
                + " homework/" + VALID_HOMEWORK_NAME;
        assertParseSuccess(parser, input5, expected);
    }

    @Test
    public void parse_studentDoesNotExist_failure() {
        clearTutorPro();

        //Add homework to student that does not exist
        assertParseFailure(parser,
                           "name/Roy homework/Biology deadline/" + VALID_DEADLINE,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   CreateHomeworkCommand.MESSAGE_USAGE));
    }
}
