package seedu.address.logic.commands;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandParamTest {
    @Test
    void from_emptyTokenEmptyPrefixes_returnsEmpty() {
        // setup
        String[] tokens = new String[] {};
        Optional<Map<String, String>> prefixes = Optional.empty();

        // execute
        CommandParam param = CommandParam.from(tokens, prefixes);

        // verify
        Assertions.assertFalse(param.getUnnamedValue().isPresent());
        Assertions.assertFalse(param.getNamedValues().isPresent());
    }
}
