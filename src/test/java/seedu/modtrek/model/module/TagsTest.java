package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.tag.Tag;

public class TagsTest {
    @Test
    public void testToString() {
        assertEquals(new Tags(new HashSet<>()).toString(), "NO TAGS");
        assertEquals(new Tags(Set.of(new Tag("CSF"), new Tag("CSBD"))).toString(), "CSF, CSBD");
    }

    @Test
    public void compareTo() {
        assertTrue(new Tags(new HashSet<>()).compareTo(new Tags(new HashSet<>())) == 0);
        assertTrue(new Tags(Set.of(new Tag("CSF"))).compareTo(new Tags(new HashSet<>())) == -1);
        assertTrue(new Tags(new HashSet<>()).compareTo(new Tags(Set.of(new Tag("CSF")))) == 1);
        assertTrue(new Tags(Set.of(new Tag("ULR")))
                .compareTo(new Tags(Set.of(new Tag("CSF")))) != 0);
    }

    @Test
    public void getAllShortFormTagsTest() {
        assertEquals(Tags.getAllShortFormTags().size(), 13);
    }

    @Test
    public void containsTest() {
        assertTrue(new Tags(new HashSet<>()).contains(new Tags(new HashSet<>())));
        assertFalse(new Tags(Set.of(new Tag("CSF"))).contains(new Tags(new HashSet<>())));
        assertTrue(new Tags(Set.of(new Tag("ULR"), new Tag("CSF")))
                .contains(new Tags(Set.of(new Tag("CSF")))));
    }

    @Test
    public void equals() {
        assertTrue(new Tags(new HashSet<>()).equals(new Tags(new HashSet<>())));
        assertFalse(new Tags(Set.of(new Tag("CSF"))).equals(new Tags(new HashSet<>())));
        assertFalse(new Tags(new HashSet<>()).equals(new Tags(Set.of(new Tag("CSF")))));
        assertFalse(new Tags(Set.of(new Tag("ULR")))
                .equals(new Tags(Set.of(new Tag("CSF")))));
        assertFalse(new Tags(new HashSet<>()).equals(new HashSet<>()));
    }

}
