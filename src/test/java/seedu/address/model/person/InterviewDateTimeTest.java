package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class InterviewDateTimeTest {
    private static final String templateDateTimeString = "12-02-2023 18:00";
    private static InterviewDateTime template;

    static {
        try {
            template = new InterviewDateTime(templateDateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createInterviewDateTime_emptyString_returnsNullInterviewDateTime() throws NullPointerException {
        assertThrows(NullPointerException.class, () -> new InterviewDateTime(InterviewDateTime.EMPTY_DATE_TIME)
                .toString());
    }

    @Test
    public void createInterviewDateTime_validFormat() throws ParseException {
        InterviewDateTime interviewDateTime = new InterviewDateTime(templateDateTimeString);
        assertEquals(interviewDateTime, template);
    }
}
