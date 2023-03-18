package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that any of a {@code Person}'s {@code Note} matches the keyword given.
 */

public class NoteContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public NoteContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getNotes().stream().anyMatch(note -> StringUtil.containsWordIgnoreCase(note.noteName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NoteContainsKeywordsPredicate
                && keyword.equals(((NoteContainsKeywordsPredicate) other).keyword));
    }
}
