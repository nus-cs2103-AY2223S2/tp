package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class FuzzyTest {

    /**
     * A test case for the delta function.
     */
    private static class Case {
        public final String a;
        public final String b;
        public final int expectedDelta;
        public final double expectedPercent;

        Case(String a, String b, int expectedDelta, double expectedPercent) {
            this.a = a;
            this.b = b;
            this.expectedDelta = expectedDelta;
            this.expectedPercent = expectedPercent;
        }
    }

    @Test
    void delta() {
        // NOTE: we are testing both delta and percent here, because they are so closely related.
        Case[] tests = {
            new Case("uwu", "owo", 2, 2 / (double) 3),
            new Case("horse", "ros", 3, 3 / (double) 5),
            new Case("intention", "execution", 5, 5 / (double) 9),
            new Case("uwu", "", 3, 1),
            new Case("", "uwu", 3, 1),
            new Case("   ", "uwu", 3, 1),
            new Case("foo", "baz bat", 7, 1),
            new Case("uwu", "uwu", 0, 0)
        };

        for (Case test : tests) {
            int actual = Fuzzy.delta(test.a, test.b);
            assertEquals(test.expectedDelta, actual, "While testing case: " + test.a + ", " + test.b);
        }
    }
}
