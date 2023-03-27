package seedu.address.logic.parser.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.homework.DeleteHomeworkCommand;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

public class DeleteHomeworkCommandParserTest {
    public static final String VALID_DEADLINE = "2027-04-31T12:00";

//    A Model Stub
    Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
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

//    @Test
//    public void parse_studentDoesNotExist_failure() {
//        clearTutorPro();
//        addStudent();
//
//        //Add 4 homeworks
//        for (int i = 1; i < 5; i++) {
//            addHomework(i);
//        }
//
//        //Use a student that does not exist.
//        String input = " name/James"
//                + " index/1";
//        String expected = "No student found: **James**.\nPlease check the name entered";
//
//        try {
//            DeleteHomeworkCommand cmd = parser.parse(input);
//            CommandException actual = assertThrows(CommandException.class,
//                         () -> cmd.execute(model));
//            assertEquals(expected, actual.getMessage());
//        } catch (ParseException e) {
////            This input will not throw any ParseException.
//            throw new RuntimeException(e);
//        }
//
//    }


//    @Test
//    public void parse_indexOutOfBounds_failure() {
////        clearTutorPro();
//        addStudent();
//
//        //Add 4 homeworks
//        for (int i = 1; i < 5; i++) {
//            addHomework(i);
//        }
//
//        String input = " name/Amy"
//                + " index/5";
//        String expected = MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX;
//
//        try {
//            DeleteHomeworkCommand cmd = parser.parse(input);
//            CommandException actual = assertThrows(CommandException.class,
//                                                   () -> cmd.execute(model));
//            assertEquals(expected, actual.getMessage());
//        } catch (ParseException e) {
////            This input will not throw any ParseException.
//            throw new RuntimeException(e);
//        }
//    }

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
