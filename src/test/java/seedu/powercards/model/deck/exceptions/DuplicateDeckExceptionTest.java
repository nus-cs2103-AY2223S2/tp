package seedu.powercards.model.deck.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DuplicateDeckExceptionTest {

    @Test
    public void constructor_validArguments_success() {
        DuplicateDeckException exception = new DuplicateDeckException();
        assertEquals("Operation would result in duplicate cards", exception.getMessage());
    }
}
