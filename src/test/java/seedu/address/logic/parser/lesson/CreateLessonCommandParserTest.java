package seedu.address.logic.parser.lesson;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class CreateLessonCommandParserTest {

    private CreateLessonCommandParser parser = new CreateLessonCommandParser();

    //    @Test
    //    public void parse_validArgs_returnsCreateLessonCommand() throws ParseException {
    //        List<String> names = Arrays.asList("Alice", "Bob");
    //        String lessonName = "Math";
    //        LocalDateTime startTime = LocalDateTime.parse("2023-04-10T09:00");
    //        LocalDateTime endTime = LocalDateTime.parse("2023-04-10T11:00");
    //        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
    //            + PREFIX_STARTTIME + "2023-04-10T09:00 " + PREFIX_ENDTIME + "2023-04-10T11:00";
    //        CreateLessonCommand expectedCommand = new CreateLessonCommand(names, new NamePredicate(names),
    //            lessonName, startTime, endTime);
    //        assertEquals(expectedCommand, parser.parse(userInput));
    //    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
            + PREFIX_STARTTIME + "2023-04-10T09:00 " + PREFIX_ENDTIME + "2023-04-10T08:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
            + PREFIX_STARTTIME + "2023-04-10T09:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_emptyName_throwsParseException() {
        String userInput = " " + PREFIX_NAME + " " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
            + PREFIX_STARTTIME + "2023-04-10T09:00 " + PREFIX_ENDTIME + "2023-04-10T11:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidLessonName_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + " "
            + PREFIX_STARTTIME + "2023-04-10T09:00 " + PREFIX_ENDTIME + "2023-04-10T11:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidStartTimeFormat_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
            + PREFIX_STARTTIME + "2023-04-10-09:00 " + PREFIX_ENDTIME + "2023-04-10T11:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidEndTimeFormat_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob " + PREFIX_LESSON + "Math "
            + PREFIX_STARTTIME + "2023-04-10T09:00 " + PREFIX_ENDTIME + "2023-04-10-11:00";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
