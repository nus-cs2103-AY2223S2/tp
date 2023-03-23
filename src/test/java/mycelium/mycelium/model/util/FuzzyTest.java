package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class FuzzyTest {
    /**
     * A test case for the delta function.
     */
    private static class TestCase {
        public final String s1;
        public final String s2;
        public final int expectedDelta;
        public final double expectedRatio;

        TestCase(String s1, String s2, int expectedDelta, double expectedRatio) {
            this.s1 = s1;
            this.s2 = s2;
            this.expectedDelta = expectedDelta;
            this.expectedRatio = expectedRatio;
        }
    }

    private static final TestCase[] testCases = {
        new TestCase("uwu", "owo", 2, 1 / (double) 3),
        new TestCase("horse", "ros", 3, 2 / (double) 5),
        new TestCase("intention", "execution", 5, 4 / (double) 9),
        new TestCase("uwu", "", 3, 0),
        new TestCase("", "uwu", 3, 0),
        new TestCase("   ", "uwu", 3, 0),
        new TestCase("foo", "baz bat", 7, 0),
        new TestCase("uwu", "uwu", 0, 1)
    };

    @Test
    void delta() {
        for (TestCase test : testCases) {
            int actual = Fuzzy.delta(test.s1, test.s2);
            assertEquals(test.expectedDelta, actual, "While testing case: " + test.s1 + ", " + test.s2);
        }
    }

    @Test
    void ratio() {
        for (TestCase test : testCases) {
            double actual = Fuzzy.ratio(test.s1, test.s2);
            assertEquals(test.expectedRatio, actual, 0.0001, "While testing case: " + test.s1 + ", " + test.s2);
        }
    }
}
