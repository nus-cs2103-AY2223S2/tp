package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipTodoBuilder;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for {@code Note}.
 */
public class NoteTest {

    private final Note noteTester = new NoteBuilder().build();

    @Test
    public void getJsonDate_getSuccessful() {
        assertEquals(noteTester.getJsonDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
    }

    @Test
    public void getDateString_getSuccessful() {
        assertEquals(noteTester.getDateString(),
                DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(LocalDate.now()));
    }

    @Test
    public void setNote_setSuccessful() {
        noteTester.setNote(new NoteContent("This is only a small test!"));
        assertEquals(noteTester.getNote().content, "This is only a small test!");
    }

    @Test
    public void hashCode_sameHashGenerated() {
        assertEquals(noteTester.hashCode(), Objects.hash(noteTester.getNote(), noteTester.getDate()));
    }

    @Test
    public void equals_alertDifferentClass() {
        assertNotEquals(noteTester, new InternshipTodoBuilder().build());
    }
}
