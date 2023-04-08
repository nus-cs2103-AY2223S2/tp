package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NoteContainsKeywordsPredicateTest {
    @Test
    public void test_personHasNote_shouldReturnTrue() {
        NoteContainsKeywordsPredicate pred = new NoteContainsKeywordsPredicate("hello");
        Person person = new PersonBuilder().withNotes("hello").build();

        assertTrue(pred.test(person));
    }

    @Test
    public void test_personDoesNotHaveNote_shouldReturnFalse() {
        NoteContainsKeywordsPredicate pred = new NoteContainsKeywordsPredicate("hello");
        Person person = new PersonBuilder().build();

        assertFalse(pred.test(person));
    }

    @Test
    public void test_keywordIsNotWholeWord_shouldReturnFalse() {
        NoteContainsKeywordsPredicate pred = new NoteContainsKeywordsPredicate("ello");
        Person person = new PersonBuilder().withNotes("hello").build();

        assertFalse(pred.test(person));
    }

    @Test
    public void equals() {
        NoteContainsKeywordsPredicate pred1 = new NoteContainsKeywordsPredicate("hello");
        NoteContainsKeywordsPredicate pred2 = new NoteContainsKeywordsPredicate("world");
        NoteContainsKeywordsPredicate pred3 = new NoteContainsKeywordsPredicate("hello");

        assertTrue(pred1.equals(pred1));
        assertTrue(pred1.equals(pred3));
        assertFalse(pred1.equals(pred2));
        assertFalse(pred1.equals(null));
    }
}
