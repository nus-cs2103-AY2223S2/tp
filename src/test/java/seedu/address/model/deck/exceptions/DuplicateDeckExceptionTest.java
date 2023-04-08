package seedu.address.model.deck.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateDeckExceptionTest {

    @Test
    public void constructor_validArguments_success() {
        DuplicateDeckException exception = new DuplicateDeckException();
        assertEquals("Operation would result in duplicate cards", exception.getMessage());
    }
}
