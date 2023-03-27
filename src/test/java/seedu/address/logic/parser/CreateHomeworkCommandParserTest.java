package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.homework.CreateHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.homework.CreateHomeworkCommandParser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;


public class CreateHomeworkCommandParserTest {

    private static final String VALID_HOMEWORK_NAME = "Biology";
    private CreateHomeworkCommandParser homeworkCommandParser = new CreateHomeworkCommandParser();
    private AddressBookParser mainParser = new AddressBookParser();

    private void clearTutorPro() {
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
        mainParser.parseCommand( "new-student name/" + VALID_NAME_AMY
                + " phone/" + VALID_PHONE_AMY
                + " email/" + VALID_EMAIL_AMY
                + " address/" + VALID_ADDRESS_AMY
        );

        //Deadline missing
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                         CreateHomeworkCommand.MESSAGE_USAGE);
        String input1 = "name/Amy"
                    + " homework/" + VALID_HOMEWORK_NAME;
        assertParseFailure(homeworkCommandParser, input1, expected);

        //Homework Name missing
        String input2 = "name/Amy"
                + " deadline/2023-04-31T12:00";
        assertParseFailure(homeworkCommandParser, input2, expected);
    }

    @Test
    public void parse_allFieldsInAnyOrder_success() throws ParseException {
        clearTutorPro();
        //Add a new student
        mainParser.parseCommand( "new-student name/" + VALID_NAME_AMY
                                         + " phone/" + VALID_PHONE_AMY
                                         + " email/" + VALID_EMAIL_AMY
                                         + " address/" + VALID_ADDRESS_AMY
        );

        //Ideal ordering: name-hw-deadline
        String idealInput = "new-homework"
                + " name/Amy"
                + " homework/" + VALID_HOMEWORK_NAME
                + " deadline/2023-04-31T12:00";
        idealInput = "new-homework name/Amy homework/Biology deadline/2023-04-31T12:00";
        CreateHomeworkCommand expected = (CreateHomeworkCommand) mainParser.parseCommand(idealInput);

        //hw-deadline-name
        String input1 = " homework/" + VALID_HOMEWORK_NAME
                + " deadline/2023-04-31T12:00"
                + " name/Amy";
        assertParseSuccess(homeworkCommandParser, input1, expected);

        //deadline-hw-name
        String input2 = " deadline/2023-04-31T12:00"
                + " homework/" + VALID_HOMEWORK_NAME
                + " name/Amy";
        assertParseSuccess(homeworkCommandParser, input2, expected);


        //hw-name-deadline
        String input3 = " homework/" + VALID_HOMEWORK_NAME
                + " name/Amy"
                + " deadline/2023-04-31T12:00";
        assertParseSuccess(homeworkCommandParser, input3, expected);

        //deadline-name-hw
        String input4 = " deadline/2023-04-31T12:00"
                + " name/Amy"
                + " homework/" + VALID_HOMEWORK_NAME;

        assertParseSuccess(homeworkCommandParser, input4, expected);

        //name-deadline-hw
        String input5 = " name/Amy"
                + " deadline/2023-04-31T12:00"
                + " homework/" + VALID_HOMEWORK_NAME;
        assertParseSuccess(homeworkCommandParser, input5, expected);
    }

    @Test
    public void parse_studentDoesNotExist_failure() {
        clearTutorPro();

        //Add homework to student that does not exist
        assertParseFailure(homeworkCommandParser,
                           "name/Roy homework/Biology deadline/2026-08-31",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   CreateHomeworkCommand.MESSAGE_USAGE));
    }
}
