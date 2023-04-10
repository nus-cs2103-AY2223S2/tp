package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ContentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        List<String> l2 = new ArrayList<>();
        l1.add("2");
        ContentContainsKeywordsPredicate k1 = new ContentContainsKeywordsPredicate(l1);
        ContentContainsKeywordsPredicate k2 = new ContentContainsKeywordsPredicate(l2);

        assertTrue(k1.equals(k1));
        assertFalse(k1.equals(k2));
    }
}
