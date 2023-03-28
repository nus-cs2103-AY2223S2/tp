package seedu.dengue.model.person;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.dengue.logic.parser.exceptions.ParseException;
//import seedu.dengue.model.predicate.FindPredicate;
//import seedu.dengue.model.variant.Variant;
//import seedu.dengue.testutil.PersonBuilder;

public class FindPredicateTest {

//    @Test
//    public void test_nameContainsKeywords_returnsTrue() {
//        // One keyword
//        Optional<SubPostal> emptySubPostal = Optional.empty();
//        Optional<Name> emptyName = Optional.empty();
//        Optional<Age> emptyAge = Optional.empty();
//        Optional<Date> emptyDate = Optional.empty();
//        Set<Variant> emptyVariants = new HashSet<>();
//        Optional<Name> testName = Optional.of(new Name("ALICE"));
//
//        //one keyword all case
//        FindPredicate predicate = new FindPredicate(
//                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants);
//        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//
//        testName = Optional.of(new Name("ALICE BOB"));
//        // Multiple keywords
//        predicate = new FindPredicate(
//                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants);
//        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//
//        testName = Optional.of(new Name("ALicE BoB"));
//        // Mixed-case keywords
//        predicate = new FindPredicate(
//                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants);
//        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//    }

//    @Test
//    public void test_nameDoesNotContainKeywords_returnsFalse() throws ParseException {
//
//        Optional<SubPostal> emptySubPostal = Optional.empty();
//        Optional<Name> emptyName = Optional.empty();
//        Optional<Age> emptyAge = Optional.empty();
//        Optional<Date> emptyDate = Optional.empty();
//        Set<Variant> emptyVariants = new HashSet<>();
//
//        // Zero keywords
//        FindPredicate predicate = new FindPredicate(
//                emptyName, emptySubPostal, emptyAge, emptyDate, emptyVariants);
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
//
//        // Non-matching keyword
//        Optional<Name> testName = Optional.of(new Name("Carol"));
//        predicate = new FindPredicate(
//                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants);
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//    }
}
