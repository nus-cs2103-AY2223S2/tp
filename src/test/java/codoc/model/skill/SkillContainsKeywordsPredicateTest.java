package codoc.model.skill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import codoc.testutil.PersonBuilder;

public class SkillContainsKeywordsPredicateTest {

    @Test
    public void test_keywords_returnsTrue() {
        // Single skill that match
        SkillContainsKeywordsPredicate predicate = new SkillContainsKeywordsPredicate(List.of("jAvA"));
        assertTrue(predicate.test(new PersonBuilder().withSkills("JaVa").build()));

        // Multiple skill where all match
        predicate = new SkillContainsKeywordsPredicate(List.of("PyTHON", "jAvA"));
        assertTrue(predicate.test(new PersonBuilder().withSkills("JaVa", "PYTHON").build()));

        // Single skill that partially match
        predicate = new SkillContainsKeywordsPredicate(List.of("jAvA"));
        assertTrue(predicate.test(new PersonBuilder().withSkills("JaVaScript").build()));

    }

    @Test
    public void test_keywords_returnsFalse() {
        // Multiple skill where at least one has no match
        SkillContainsKeywordsPredicate predicate = new SkillContainsKeywordsPredicate(List.of("PyTHON", "JaVaScript"));
        assertFalse(predicate.test(new PersonBuilder().withSkills("JaVa", "PYTHON").build()));
    }
}
