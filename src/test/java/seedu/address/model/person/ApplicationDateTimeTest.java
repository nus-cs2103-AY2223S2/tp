package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

class ApplicationDateTimeTest {
    private static final ApplicationDateTime truncatedApplicationDateTime =
            new ApplicationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    private static final ApplicationDateTime notTruncatedapplicationDateTime =
            new ApplicationDateTime(LocalDateTime.now());


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationDateTime(null));
    }

    @Test
    public void getApplicationDate() {
        ApplicationDateTime sameApplicationDateTimeTruncated =
                new ApplicationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        ApplicationDateTime otherApplicationDateTime =
                new ApplicationDateTime(LocalDateTime.of(2023, 05, 05, 18, 00));

        // if LocalDateTime is same --> equals
        assertEquals(truncatedApplicationDateTime.getApplicationDateTime(),
                sameApplicationDateTimeTruncated.getApplicationDateTime());

        // if LocalDateTime is different --> not equals
        assertNotEquals(truncatedApplicationDateTime.getApplicationDateTime(),
                otherApplicationDateTime.getApplicationDateTime());

        // if LocalDateTime is not truncated --> not equals
        assertNotEquals(truncatedApplicationDateTime.getApplicationDateTime(),
                notTruncatedapplicationDateTime.getApplicationDateTime());
    }

    @Test
    public void equals() {
        ApplicationDateTime otherApplicationDateTime =
                new ApplicationDateTime(LocalDateTime.of(2023, 05, 05, 18, 00));

        // different LocalDateTime --> not equals
        assertNotEquals(truncatedApplicationDateTime, otherApplicationDateTime);

        // same LocalDateTime --> trivially equals to each other
        assertEquals(truncatedApplicationDateTime, truncatedApplicationDateTime);

        // same LocalDateTime but one is not truncated --> not equals to each other due to milliseconds difference
        assertNotEquals(truncatedApplicationDateTime, notTruncatedapplicationDateTime);
    }

}
