package seedu.address.logic.commands;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * TODO: write more tests for CommandParam.
 */
public class CommandParamTest {
    @Test
    void from_emptyTokenEmptyPrefixes_returnsEmpty() {
        // setup
        Deque<String> tokens = new ArrayDeque<>();
        Optional<Set<String>> prefixes = Optional.empty();

        // execute
        CommandParam param = CommandParam.from(tokens, prefixes);

        // verify
        Assertions.assertFalse(param.getUnnamedValue().isPresent());
        Assertions.assertFalse(param.getNamedValues().isPresent());
    }

    @Test
    void from_emptyTokenNonEmptyPrefixes_returnsEmpty() {
        // setup
        Deque<String> tokens = new ArrayDeque<>();
        Optional<Set<String>> prefixes = Optional.of(Set.of("p/"));

        // execute
        CommandParam param = CommandParam.from(tokens, prefixes);

        // verify
        Assertions.assertFalse(param.getUnnamedValue().isPresent());
        Assertions.assertFalse(param.getNamedValues().isPresent());
    }

    @Test
    void from_nonEmptyTokenEmptyPrefixes_returnsUnnamedValue() {
        // setup
        Deque<String> tokens = new ArrayDeque<>();
        tokens.add("token");
        tokens.add("token2");
        Optional<Set<String>> prefixes = Optional.empty();

        // execute
        CommandParam param = CommandParam.from(tokens, prefixes);

        // verify
        Assertions.assertTrue(param.getUnnamedValue().isPresent());
        Assertions.assertEquals("token token2", param.getUnnamedValue().get());
        Assertions.assertFalse(param.getNamedValues().isPresent());
    }

    @Test
    void from_nonEmptyTokenNonEmptyPrefixes_returnsNamedValues() {
        // setup
        String[] rawTokens = new String[]{
            "token", "p/", "prefix", "token2", "q/", "prefix2",
            };
        Deque<String> tokens = new ArrayDeque<>(Arrays.asList(rawTokens));
        Optional<Set<String>> prefixes = Optional.of(Set.of("p/", "q/"));

        // execute
        CommandParam param = CommandParam.from(tokens, prefixes);

        // verify
        Assertions.assertTrue(param.getUnnamedValue().isPresent());
        Assertions.assertEquals("token", param.getUnnamedValue().get());

        Assertions.assertTrue(param.getNamedValues().isPresent());
        Map<String, Optional<String>> namedValues = param.getNamedValues().get();

        Assertions.assertEquals(2, namedValues.size());
        Assertions.assertTrue(namedValues.containsKey("p/"));
        Assertions.assertTrue(namedValues.containsKey("q/"));
        Assertions.assertEquals("prefix token2", namedValues.get("p/").get());
        Assertions.assertEquals("prefix2", namedValues.get("q/").get());
    }
}
