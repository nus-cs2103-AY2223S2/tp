package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    public void note_emptyInput_createsEmptyObject() {
        Note note = new Note();
        assertEquals(note.EMPTY_CONTENT, note.getContent());
    }

    @Test
    public void note_validInputWithoutPerson_createsNonemptyObject() {
        Note note = new Note("This is my first note!! \n "
                + "Please remind me that I have to send report by next meeting");
        assertNotNull(note.getContent());
    }

    @Test
    public void note_validInputWithPersons_createsNonemptyObject() {
        Note note = new Note("This is my first note!! \n "
                + "Please remind me that I have to send report by next meeting \n"
                + "Relevant personnel: @Jim, @Lin, and @Han.");
        assertNotNull(note.getContent());
        List<String> actualPersonNames = new ArrayList<String>(3);
        actualPersonNames.add("Jim");
        actualPersonNames.add("Lin");
        actualPersonNames.add("Han");
        assertArrayEquals(actualPersonNames.toArray(), note.getReferees().toArray(),
                "Names are not captured correctly");
    }

    @Test
    public void setContent_resetContentWithEmptyString_createsEmptyObject() {
        Note note = new Note("This is a note\n");
        note.setContent("");
        assertEquals(note.EMPTY_CONTENT, note.getContent());
    }

    @Test
    public void clear_clearContentsAndNames_becomesEmpty() {
        Note note = new Note("This is a note\n");
        note.clear();
        assertTrue(note.getReferees().isEmpty());
        assertEquals(note.EMPTY_CONTENT, note.getContent());
    }

    @Test
    public void toString_multiLineExpressions_returnsFormatStrings() {
        Note note = new Note("This is my first note!! \n"
                + "Please remind me that I have to send report to @Jim, @Lin by next meeting \n"
                + "And I need to regrade tutorial for @Han.");

        assertEquals("Notes: \n"
                + "This is my first note!! \n"
                + "Please remind me that I have to send report to @Jim, @Lin by next meeting \n"
                + "And I need to regrade tutorial for @Han."
                + ";\n Relevant personnel: "
                + "Jim Lin Han", note.toString());
    }

    @Test
    public void help_noInput_returnsHelpGuide() {
        Note note = new Note();
        assertNotNull(note.help());
    }
}
