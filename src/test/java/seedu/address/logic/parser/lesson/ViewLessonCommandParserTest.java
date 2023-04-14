package seedu.address.logic.parser.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lesson.ViewLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.LessonBelongsToDatePredicate;
import seedu.address.model.student.LessonDonePredicate;
import seedu.address.model.student.LessonSubjectPredicate;
import seedu.address.model.student.NamePredicate;

class ViewLessonCommandParserTest {
    private final ViewLessonCommandParser parser = new ViewLessonCommandParser();

    @Test
    public void parse_validArgs_returnsViewLessonCommand() throws ParseException {
        // No filters provided
        assertEquals(parser.parse(""), new ViewLessonCommand(Collections.emptyList(), PREDICATE_SHOW_ALL_STUDENTS,
            lesson -> true, lesson -> true, true));

        // Only name provided
        List<String> nameKeywords = Arrays.asList("John", "Doe");
        assertEquals(parser.parse(" name/John name/Doe"), new ViewLessonCommand(nameKeywords,
            new NamePredicate(nameKeywords), lesson -> true, lesson -> true, false));

        // All filters provided
        List<String> names = Arrays.asList("John", "Doe");
        assertEquals(parser.parse(" name/John name/Doe date/2023-05-21 lesson/Math done/done"),
            new ViewLessonCommand(names, new NamePredicate(names), new LessonBelongsToDatePredicate(LocalDate.of(2023,
                5, 21)), new LessonSubjectPredicate("Math"), new LessonDonePredicate("done"), false));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid done keyword
        assertThrows(ParseException.class, () -> parser.parse(" done/invalid"));

        // Invalid date format
        assertThrows(ParseException.class, () -> parser.parse(" d/2023-13-32"));

        // Invalid prefix
        assertThrows(ParseException.class, () -> parser.parse(" x/John"));
    }
}
