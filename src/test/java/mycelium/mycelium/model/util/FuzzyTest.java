package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class FuzzyTest {
    /**
     * A test case for the delta function.
     */
    private static class TestCase {
        public final String query;
        public final String target;
        public final double expectedDelta;

        TestCase(String query, String target, double expectedDelta) {
            this.query = query;
            this.target = target;
            this.expectedDelta = expectedDelta;
        }
    }

    @Test
    void delta() {
        TestCase[] testCases = {
            new TestCase("foo", "bar", 0), // completely no match
            new TestCase("foo", "foo", 1), // exact match
            new TestCase("", "foo", 0), // empty query
            new TestCase("foo", "", 0), // empty target
            new TestCase("foobar", "", 0), // query longer than target
            new TestCase("foo", "barfoo", 1 / (double) (3 + 1)), // leading fluff
            new TestCase("foo", "foobar", 1 / (double) (3 + 1)), // trailing fluff
            new TestCase("foo", "barfoobar", 1 / (double) (6 + 1)), // leading and trailing fluff
            new TestCase("foo", "fooooo", 1 / (double) (3 + 1)), // repeated characters
            new TestCase("foo", "fxoxxo", 1 / (double) (2 + 4 + 1)), // interleaved characters
        };
        for (TestCase test : testCases) {
            double actual = Fuzzy.delta(test.query, test.target);
            assertEquals(test.expectedDelta, actual, 0.0001, "While testing case: " + test.query + ", " + test.target);
        }
    }

    @Test
    void levenshtein() {
        TestCase[] testCases = {
            new TestCase("uwu", "owo", 1 / (double) 3),
            new TestCase("horse", "ros", 2 / (double) 5),
            new TestCase("intention", "execution", 4 / (double) 9),
            new TestCase("uwu", "", 0),
            new TestCase("", "uwu", 0),
            new TestCase("   ", "uwu", 0),
            new TestCase("foo", "baz bat", 0),
            new TestCase("uwu", "uwu", 1)
        };

        for (TestCase test : testCases) {
            double actual = Fuzzy.levenshtein(test.query, test.target);
            assertEquals(test.expectedDelta, actual, 0.0001, "While testing case: " + test.query + ", " + test.target);
        }
    }
}
