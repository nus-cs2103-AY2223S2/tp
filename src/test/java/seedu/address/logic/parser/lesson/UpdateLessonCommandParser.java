package seedu.address.logic.parser.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.lesson.UpdateLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;

class UpdateLessonCommandParserTest {
    private final UpdateLessonCommandParser parser = new UpdateLessonCommandParser();

    @Test
    void parse_allFieldsPresent_success() throws ParseException {
        // All fields provided
        assertEquals(parser.parse(" name/John index/1 lesson/Math start/2023-05-21T10:00 "
                + "end/2023-05-21 1100"),
            new UpdateLessonCommand(List.of("John"), Index.fromOneBased(1),
                new NamePredicate(List.of("John")),
                Optional.of("Math"),
                Optional.of(LocalDateTime.of(2023, 5, 21, 10, 0)),
                Optional.of(LocalDateTime.of(2023, 5, 21, 11, 0))));
    }

    @Test
    void parse_optionalFieldsMissing_success() throws ParseException {
        // Lesson name and end time missing
        assertEquals(parser.parse(" name/John index/1 start/2023-05-21T10:00"),
            new UpdateLessonCommand(List.of("John"), Index.fromOneBased(1),
                new NamePredicate(List.of("John")),
                Optional.empty(),
                Optional.of(LocalDateTime.of(2023, 5, 21, 10, 0)),
                Optional.empty()));

        // Start time and end time missing
        Optional<LocalDateTime> start = Optional.empty();
        Optional<LocalDateTime> end = Optional.empty();
        assertEquals(parser.parse(" name/John index/1 lesson/Math"),
            new UpdateLessonCommand(List.of("John"), Index.fromOneBased(1),
                new NamePredicate(List.of("John")),
                Optional.of("Math"),
                start,
                end));
    }

    @Test
    void parse_missingCompulsoryField_failure() {
        // Missing index
        assertThrows(ParseException.class, () -> parser.parse(" name/John name/Doe lesson/Math"
            + "startTime/2023-05-21T10:00 endTime/2023-05-21T11:00"));

        // Missing name
        assertThrows(ParseException.class, () -> parser.parse(" index/1 lesson/Math startTime/2023-05-21T10:00"
            + "endTime/2023-05-21T11:00"));
    }

    @Test
    void parse_invalidValue_failure() {
        // Invalid index
        assertThrows(ParseException.class, () -> parser.parse(" name/John name/Doe index/0"
            + "lesson/Math startTime/2023-05-21T10:00 endTime/2023-05-21T11:00"));

        // Invalid date
        assertThrows(ParseException.class, () -> parser.parse(" name/John name/Doe index/1 lesson/Math"
            + "startTime/2023-13-21T10:00 endTime/2023-05-21T11:00"));
    }
}
