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
        public final int expected;
        public final String desc;

        Case(String a, String b, int expected, String desc) {
            this.a = a;
            this.b = b;
            this.expected = expected;
            this.desc = desc;
        }
    }

    @Test
    void delta() {
        Case[] tests = {
            new Case("uwu", "owo", 2, "replace only"),
            new Case("horse", "ros", 3, "replace with insert"),
            new Case("intention", "execution", 5, "replace with insert"),
            new Case("uwu", "", 3, "RHS empty"),
            new Case("", "uwu", 3, "LHS empty"),
            new Case("   ", "uwu", 3, "whitespace"),
            new Case("uwu", "uwu", 0, "same strings")
        };

        for (Case test : tests) {
            int actual = Fuzzy.delta(test.a, test.b);
            assertEquals(test.expected, actual, "While testing case: " + test.desc);
        }
    }
}
